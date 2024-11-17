package br.edu.famper.apicontroleestoque.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaDto {
    @Schema(description = "Nome da Categoria",
    example = "Teclado",
    title = "nome",
    maxLength = 20)
    private String nome;


}
