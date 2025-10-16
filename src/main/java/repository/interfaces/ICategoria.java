package repository.interfaces;

import entities.Categoría;

import java.util.List;

public interface ICategoria {
    Categoría save(Categoría categoria);
    List<Categoría> findAll();
}
