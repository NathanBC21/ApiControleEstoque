package br.edu.famper.apicontroleestoque.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "fornecedor")
@Data

public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "cpf_cnpj", length = 100)
    private String cpf_cnpj;

    @Column(name = "telefone", length = 15)
    private String telefone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "endereco", length = 100)
    private String endereco;

    @ManyToMany(mappedBy = "fornecedor", targetEntity = Produto.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Produto> produtos;
}
