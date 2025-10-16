package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "categorias", uniqueConstraints = @UniqueConstraint(columnNames = "nombre"))
@Getter @Setter

public class Categoría {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150)
    private String nombre;

    @ManyToMany (mappedBy = "categorias")
    private Set<Categoría> categoría = new HashSet<>();

    public Categoría() {}

    public Categoría(String nombre) {this.nombre = nombre;}

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Categoría)) return false;
        Categoría that = (Categoría) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, nombre);
    }
}
