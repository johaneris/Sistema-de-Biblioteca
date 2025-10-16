package run;

import entities.Libro;
import entities.Autor;
import entities.Categoría;
import config.JPAUtil;
import repository.interfaces.IAutor;
import repository.interfaces.ILibro;
import repository.interfaces.ICategoria;
import repository.dao.AutorDao;
import repository.dao.CategoriaDao;
import repository.dao.LibroDao;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args)
    {
        EntityManager em = JPAUtil.getEntityManager();
        IAutor autores = new AutorDao(em);
        ICategoria categorias = new CategoriaDao(em);
        ILibro libros = new LibroDao(em);

        try {
            em.getTransaction().begin();

            // Categorías
            Categoría ficcion = categorias.save(new Categoría("Ficción"));
            Categoría ciencia = categorias.save(new Categoría("Ciencia"));

            // Autores
            Autor Steven = new Autor("Gabriel García Márquez", "Colombia", LocalDate.of(1927,3,6));
            Autor Joha = new Autor("Mary Shelley", "Reino Unido", LocalDate.of(1797,8,30));

            // Libros y relaciones
            Libro cienAnos = new Libro("Cien años de soledad", 1967);
            cienAnos.addCategoria(ficcion);
            Steven.addLibro(cienAnos);

            Libro amorTiempos = new Libro("El amor en los tiempos del cólera", 1985);
            amorTiempos.addCategoria(ficcion);
            Steven.addLibro(amorTiempos);

            Libro frankenstein = new Libro("Frankenstein", 1818);
            frankenstein.addCategoria(ficcion);
            frankenstein.addCategoria(ciencia);
            Joha.addLibro(frankenstein);

            // Persistimos autores (cascade guarda libros)
            autores.save(Steven);
            autores.save(Joha);

            em.getTransaction().commit();

            System.out.println("\n== Libros con su autor y categorías ==");
            for (Libro l : libros.findAllWithAutorAndCategorias()) {
                String autorNombre = l.getAutor() != null ? l.getAutor().getNombre() : "(sin autor)";
                String cats = l.getCategorias().stream().map(Categoría::getNombre).sorted()
                        .reduce((a,b)-> a+", "+b).orElse("(sin categorías)");
                System.out.printf("- %s (%d) — Autor: %s — Categorías: %s%n",
                        l.getTitulo(), l.getAnioPublicacion(), autorNombre, cats);
            }
        }
        finally
        {
            em.close();
            JPAUtil.shutdown();
        }
    }
}
