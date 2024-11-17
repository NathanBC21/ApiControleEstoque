package br.edu.famper.apicontroleestoque.dto;

import br.edu.famper.apicontroleestoque.model.Categoria;
import br.edu.famper.apicontroleestoque.model.Fornecedor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoDto {
    @Schema(description = "Codigo do produto",
            example = "ER123456789PJ",
            title = "codigo",
            maxLength = 80)
    private String codigo;

    @Schema(description = "Descricao do produto",
            example = "Teclado",
            title = "descricao",
            maxLength = 50)
    private String descricao;

    @Schema(description = "Marca do produto",
            example = "Logitech",
            title = "marca",
            maxLength = 50)
    private String marca;

    @Schema(description = "Modelo do produto",
            example = "K120",
            title = "modelo",
            maxLength = 50)
    private String modelo;

}
