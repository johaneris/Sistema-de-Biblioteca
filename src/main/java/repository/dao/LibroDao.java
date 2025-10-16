package repository.dao;

import entities.Libro;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.hibernate.annotations.DialectOverride;
import repository.ILibro;

import java.util.List;
import java.util.Optional;

public class LibroDao {
    private final EntityManager em;
    public LibroDao(EntityManager em) {this.em = em;}

    @Override
    public Libro guardar(Libro libro){
        if (libro==null){
            throw new IllegalArgumentException("Libro no puede ser nulo.");
        }
        EntityTransaction tx = em.getTransaction();
        try{
            if (libro.getId() == null){
                tx.begin();
                em.persist(libro);
                tx.commit();
                return libro;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Libro> listar(){return em.createNamedQuery("FROM Libro", Libro.class).getResultList();}

    @Override
    public Optional<Autor> buscarId(Long id){
        if (id==null){return Optional.empty();}
        Libro lb = em.find(Libro.class, id);
        return Optional.ofNullable(lb);
    }
}
