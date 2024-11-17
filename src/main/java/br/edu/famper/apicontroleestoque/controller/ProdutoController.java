package br.edu.famper.apicontroleestoque.controller;

import br.edu.famper.apicontroleestoque.dto.ProdutoDto;
import br.edu.famper.apicontroleestoque.exception.ResourceNotFoundException;
import br.edu.famper.apicontroleestoque.model.Produto;
import br.edu.famper.apicontroleestoque.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/produto")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Fornecedor",
        description = "Operation for Products")
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all products from DB",
            description = "Fetches all products from DB and return " +
                    "in JSON Array"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "404", description = "not found")
    })
    public List<ProdutoDto> getAllProdutos() {
        log.info("Buscando todos os os produtos");
        return produtoService.getAllProdutos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get one product from DB",
            description = "Fetches one product from DB and return " +
                    "in JSON Object"
    )
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "404", description = "not found")
    })
    public ResponseEntity<ProdutoDto> getProdutoById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando Produto por id: {}", id);
        return ResponseEntity.ok().body(produtoService.getProdutoById(id));
    }

    @PostMapping
    @Operation(summary = "Save product",
            description = "Save a product in database"
    )
    public Produto createProduto(@RequestBody ProdutoDto produtoDto) throws ResourceNotFoundException {
        log.info("Cadastro produto: {}", produtoDto);
        return produtoService.saveProduto(produtoDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update product",
            description = "Update a product in databse"
    )
    public ResponseEntity<ProdutoDto> updateProduto(@PathVariable(name = "id") Long id,@RequestBody ProdutoDto produtoDto) throws ResourceNotFoundException {
       log.info("Atualizando produto: {}", produtoDto);
       return ResponseEntity.ok(produtoService.editProduto(id,produtoDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove product",
            description = "Remove a product in database"
    )
    public Map<String, Boolean> deleteProduto(@PathVariable(name = "id") Long id) throws Exception {
        log.info("Deletando produto: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", produtoService.deleteProduto(id));
        return response;
    }


}
