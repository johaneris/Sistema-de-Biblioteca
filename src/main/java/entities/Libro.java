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

    @Column(nullable = false, length = 150)
    private String titulo;

    private Integer anioPublicacion;

    @ManyToOne (optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor ;

    @ManyToMany
    @JoinTable(name =  "libro_categoria", joinColumns = @JoinColumn(name = "libro_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private Set<Categoría> categorias = new HashSet<>();

    public Libro() {}
    public Libro(String titulo, int anioPublicacion)
    {
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public Integer getAnioPublicacion() { return anioPublicacion; }
    public void setAnioPublicacion(Integer anioPublicacion) { this.anioPublicacion = anioPublicacion; }
    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }
    public Set<Categoría> getCategorias() { return categorias; }

    public void addCategoria(Categoría c) {
        categorias.add(c);
        c.getLibros().add(this);
    }
    public void removeCategoria(Categoría c) {
        categorias.remove(c);
        c.getLibros().remove(this);
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
