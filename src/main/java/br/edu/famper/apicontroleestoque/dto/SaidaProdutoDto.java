package br.edu.famper.apicontroleestoque.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaidaProdutoDto {
    @Schema(description = "Codigo da Saida",
            example = "123",
            title = "codigo",
            maxLength = 100)
    private String codigo;

    @Schema(description = "Descricao do produto",
            example = "Teclado",
            title = "descricao",
            maxLength = 50)
    private String descricao;

    @Schema(description = "Operador da saida",
            example = "Marcio",
            title = "operador",
            maxLength = 80)
    private String operador;

    @Schema(description = "Quantidade da saida",
            example = "15",
            title = "quantidade",
            maxLength = 10)
    private int quantidade;

    @Schema(description = "Data da saida",
            example = "2024-11-16",
            title = "data",
            maxLength = 10)
    private Date data;

    @Schema(description = "Colaborador",
            example = "Douglas",
            title = "colaborador",
            maxLength = 50)
    private String colaborador;

    @Schema(description = "Setor",
            example = "Marketing",
            title = "setor",
            maxLength = 20)
    private String setor;

    @Schema(description = "Saldo em estoque",
            example = "50",
            title = "saldo",
            maxLength = 10)
    private Float saldo;

}
