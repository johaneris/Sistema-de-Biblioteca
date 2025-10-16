package repository.dao;

import entities.Libro;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.hibernate.annotations.DialectOverride;
import repository.interfaces.ILibro;

import java.util.List;

public class LibroDao implements ILibro {
    private final EntityManager em;
    public LibroDao(EntityManager em) { this.em = em; }

    @Override
    public Libro save(Libro libro) {
        if (libro == null) return null;
        if (libro.getId() == null) {
            em.persist(libro);
            return libro;
        } else {
            return em.merge(libro);
        }
    }

    @Override
    public List<Libro> findAll() {
        return em.createQuery("SELECT l FROM Libro l ORDER BY l.titulo", Libro.class)
                .getResultList();
    }

    @Override
    public List<Libro> findAllWithAutorAndCategorias() {
        return em.createQuery(
                        "SELECT DISTINCT l FROM Libro l " +
                                "LEFT JOIN FETCH l.autor " +
                                "LEFT JOIN FETCH l.categorias " +
                                "ORDER BY l.titulo", Libro.class)
                .getResultList();
    }
}
