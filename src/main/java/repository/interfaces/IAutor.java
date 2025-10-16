package repository.interfaces;

import entities.Autor;

import java.util.List;
import java.util.Optional;

public interface IAutor
{
    Autor save(Autor autor);
    Optional<Autor> findById(Long id);
    List<Autor> findAll();
}
