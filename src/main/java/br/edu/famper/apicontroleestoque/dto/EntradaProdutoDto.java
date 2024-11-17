package br.edu.famper.apicontroleestoque.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntradaProdutoDto {
    @Schema(description = "Código da Entrada",
    example = "123456789",
    title = "código",
    maxLength = 50)
    private String codigo;

    @Schema(description = "Descrição do Produto",
    example = "Teclado Red Dragon" ,
    title = "descrição",
    maxLength = 100)
    private String descricao;

    @Schema(description = "Nome do Operador",
    example = "Carlos",
    title = "operador",
    maxLength = 50)
    private String operador;

    @Schema(description = "Quantidade",
    example = "12",
    title = "quantidade",
    maxLength = 50)
    private int quantidade;

    @Schema(description = "Data",
    example = "2024-12-12",
    title = "data",
    maxLength = 10)
    private Date data;

    @Schema(description = "Nota Fiscal",
    example = "12345678912",
    title = "nota fiscal",
    maxLength = 11)
    private String notaFiscal;

    @Schema(description = "Valor Unitário",
    example = "125,50",
    title = "valor unitário",
    maxLength = 100)
    private Float valorUnitario;

    @Schema(description = "Valor Total",
    example = "125,50",
    title = "valor total",
    maxLength = 100)
    private Float valorTotal;

    @Schema(description = "Saldo",
    example = "300",
    title = "saldo",
    maxLength = 100)
    private Float saldo;
}
