package repository.dao;

import entities.Categoria;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import repository.ICategoria;

import java.util.List;
import java.util.Optional;

public class CategoriaDao {
    private final EntityManager em;
    public CategoriaDao(EntityManager em) {this.em = em;}

    @Override
    public Categoria guardar(Categoria categoria){
        if (categoria==null){throw new IllegalArgumentException("El Autor no puede ser null");}

        EntityTransaction tx = em.getTransaction();
        try{
            if (categoria.getId() ==null){
                tx.begin();
                em.persist(categoria);
                tx.commit();
                return categoria;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Categoria> listar(){return em.createQuery("FROM Categoria", Categoria.class).getResultList();}

    @Override
    public Optional<Categoria> buscarId(Long id){
        if (id==null){return Optional.empty();}
        Categoria ct = em.find(Categoria.class, id);
        return Optional.ofNullable(ct);
    }

}
