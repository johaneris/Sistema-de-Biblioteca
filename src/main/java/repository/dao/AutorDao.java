package repository.dao;

import entities.Autor;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import repository.interfaces.IAutor;

import java.util.List;
import java.util.Optional;

public class AutorDao implements IAutor {
    private final EntityManager em;
    public AutorDao(EntityManager em) { this.em = em; }

    @Override
    public Autor save(Autor autor) {
        if (autor == null) return null;
        if (autor.getId() == null) {
            em.persist(autor);
            return autor;
        } else {
            return em.merge(autor);
        }
    }

    @Override
    public List<Autor> findAll()
    {
        return em.createQuery("SELECT a FROM Autor a ORDER BY a.nombre", Autor.class).getResultList();
    }

    @Override
    public Optional<Autor> findById(Long id) {
        return Optional.ofNullable(em.find(Autor.class, id));
    }
}
