package repository.interfaces;

import entities.Libro;

import java.util.List;

public interface ILibro {
    Libro save(Libro libro);
    List<Libro> findAll();
    List<Libro> findAllWithAutorAndCategorias();
}
