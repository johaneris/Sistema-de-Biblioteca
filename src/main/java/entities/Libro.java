package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import java.time.LocalDate;

@Entity
@Table(name = "libros")
@Getter @Setter

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150)
    private String titulo;

    @Column(length = 80)
    private int anioPublicacion;

    @Column(length = 80)
    private String autor;

    @ManyToOne (optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idAutor", nullable = false)
    private Autor autorId ;

    @ManyToMany
    @JoinTable(name =  "libro_categoria", joinColumns = @JoinColumn(name = "libro_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private Set<Categoría> categorias = new HashSet<>();

    public Libro() {}

    public Libro(String titulo, int anioPublicacion, String autor)
    {
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro)) return false;
        Libro libro = (Libro) o;
        return Objects.equals(id, libro.id) && Objects.equals(titulo, libro.titulo);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, titulo);
    }
}
