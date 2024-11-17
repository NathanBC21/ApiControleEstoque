package br.edu.famper.apicontroleestoque.dto;

import br.edu.famper.apicontroleestoque.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FornecedorDto {
    @Schema(description = "Nome do fornecedor",
            example = "Antônio",
            title = "nome",
            maxLength = 80)
    private String nome;

    @Schema(description = "CPF/CNPJ do fornecedor",
            example = "999.999.999/99 or 99.999.999.999-99",
            title = "CPF/CNPJ",
            maxLength = 13)
    private String cpf_cnpj;

    @Schema(description = "Telefone do fornecedor",
            example = "5546999123456",
            title = "telefone",
            maxLength = 13)
    private String telefone;

    @Schema(description = "Email do fornecedor",
            example = "forncedor@gmail.com",
            title = "email",
            maxLength = 80)
    private String email;

    @Schema(description = "Endereco do fornecedor",
            example = "Rua Melancia, Bairro do Limoeiro, N 15",
            title = "endereco",
            maxLength = 100)
    private String endereco;

}
