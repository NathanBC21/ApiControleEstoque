package br.edu.famper.apicontroleestoque.controller;

import br.edu.famper.apicontroleestoque.dto.EntradaProdutoDto;
import br.edu.famper.apicontroleestoque.exception.ResourceNotFoundException;
import br.edu.famper.apicontroleestoque.model.EntradaProduto;
import br.edu.famper.apicontroleestoque.service.EntradaProdutoService;
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
@RequestMapping("/api/entradaproduto")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "EntradaProduto",
        description = "Operation for enterings")
public class EntradaProdutoController {

    private final EntradaProdutoService entradaProdutoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all enterings from DB",
            description = "Fetches all enterings from DB and return " +
                    "in JSON Array"
    )
    @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "successful"),
         @ApiResponse(responseCode = "404", description = "not found")
    })
    public List<EntradaProdutoDto> getAllEnterings() {
        log.info("Buscando todas as entradas de produto");
        return entradaProdutoService.getAllEnterings();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get one entering from DB",
            description = "Fetches one entering from DB and return " +
                    "in JSON Object"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "succe4ssful"),
            @ApiResponse(responseCode = "404", description = "not found")
    })
    public ResponseEntity<EntradaProdutoDto> getEntradaProdutoById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando Entrada por id: {}", id);
        return ResponseEntity.ok().body(EntradaProdutoService.getEntradaProdutoById(id));
    }

    @PostMapping
    @Operation(summary = "Save Entering",
            description = "Save a entering in database"
    )
    public EntradaProduto createEntradaProduto(@RequestBody EntradaProdutoDto entradaProdutoDto) throws ResourceNotFoundException {
        log.info("Cadastro Entrada: {}", entradaProdutoDto);
        return entradaProdutoService.saveEntradaProduto(entradaProdutoDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Upadate entering",
            description = "Update a entering in databse"
    )
    public ResponseEntity<EntradaProdutoDto> updateEntradaProduto(@PathVariable(name = "id") Long id, @RequestBody EntradaProdutoDto entradaProdutoDto) throws ResourceNotFoundException {
        log.info("Atualizando Entrada: {}", entradaProdutoDto);
        return ResponseEntity.ok(entradaProdutoService.editEntradaProduto(id,entradaProdutoDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove entering",
            description = "Remove a entering in database"
    )
    public Map<String, Boolean> deleteEntradaProduto(@PathVariable(name = "id") Long id) throws Exception{
        log.info("Removendo Entrada: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", entradaProdutoService.deleteEntradaProduto(id));
        return response;
    }


}
