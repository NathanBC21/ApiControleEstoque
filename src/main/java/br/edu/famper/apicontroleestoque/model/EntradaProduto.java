package br.edu.famper.apicontroleestoque.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "EntradaProduto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class EntradaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(name = "notaFiscal", length = 20)
    private String notaFiscal;

    @Column(name = "valorUnitario")
    private Float valorUnitario;

    @Column(name = "valorTotal")
    private Float valorTotal;

    @Column(name = "saldo")
    private Float saldo;


}
