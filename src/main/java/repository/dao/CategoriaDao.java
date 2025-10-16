package repository.dao;

import entities.Categoría;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import repository.interfaces.ICategoria;

import java.util.List;


public class CategoriaDao implements ICategoria{
    private final EntityManager em;
    public CategoriaDao(EntityManager em) { this.em = em; }

    @Override
    public Categoría save(Categoría categoria) {
        if (categoria == null) return null;
        if (categoria.getId() == null) {
            em.persist(categoria);
            return categoria;
        } else {
            return em.merge(categoria);
        }
    }

    @Override
    public List<Categoría> findAll() {
        return em.createQuery("SELECT c FROM Categoría c ORDER BY c.nombre", Categoría.class)
                .getResultList();
    }
}
