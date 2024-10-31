package br.edu.famper.apicontroleestoque.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nome", length =150)
    private String nome;

    @OneToMany(mappedBy = "categoria", targetEntity = Produto.class,
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Produto> produtos;
}
