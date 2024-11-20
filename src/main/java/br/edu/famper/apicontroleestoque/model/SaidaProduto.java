package br.edu.famper.apicontroleestoque.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "SaidaProduto")
@Data

public class SaidaProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SaidaProd_id")
    private long id;

    @Column(name = "codigo", length = 150)
    private String codigo;

    @Column(name = "descricao", length = 150)
    private String descricao;

    @Column(name = "operador", length = 150)
    private String operador;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "data")
    private Date data;

    @Column(name = "colaborador", length = 150)
    private String colaborador;

    @Column(name = "setor", length = 150)
    private String setor;

    @Column(name = "saldo")
    private Float saldo;

}
