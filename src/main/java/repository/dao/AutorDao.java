package repository.dao;

import entities.Autor;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import repository.IAutor;

import java.util.List;
import java.util.Optional;

public class AutorDao {
    private final EntityManager em;

    public AutorDao(EntityManager em){
        this.em = em;
    }

    @Override
    public Autor guardar(Autor autor) {
        if (autor == null){
            throw new IllegalArgumentException("El Autor no puede ser null");
        }
        EntityTransaction tx = em.getTransaction();
        try {
            if (autor.getId() == null){
                tx.begin();
                em.persist(autor);
                tx.commit();
                return  autor;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Autor> listar() {
        return em.createQuery("FROM Autor", Autor.class).getResultList();
    }

    @Override
    public Optional<Autor> buscarId(Long id){
        if (id == null) return Optional.empty();
        Autor au = em.find(Autor.class, id);
        return Optional.ofNullable(au);
    }
}
