package br.edu.famper.apicontroleestoque.controller;

import br.edu.famper.apicontroleestoque.dto.SaidaProdutoDto;
import br.edu.famper.apicontroleestoque.exception.ResourceNotFoundException;
import br.edu.famper.apicontroleestoque.model.SaidaProduto;
import br.edu.famper.apicontroleestoque.service.SaidaProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/saidaproduto")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "SaidaProduto",
        description = "Operation for Outputs")
public class SaidaProdutoController {

    private final SaidaProdutoService saidaProdutoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all Outputs from DB",
            description = "Fetches all outputs from DB and return " +
                    "in JSON Array"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "400", description = "not found")
    })
    public List<SaidaProdutoDto> getAllSaidas() {
        log.info("Buscando todas as saidas");
        return saidaProdutoService.getAllSaidas();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get one output from DB",
            description = "Fetches one output from DB and return " +
                    "in JSON Objects"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "404", description = "not found")
    })
    public ResponseEntity<SaidaProdutoDto> getSaidasById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando saida por id: {}", id);
        return ResponseEntity.ok().body(saidaProdutoService.getSaidasById(id));
    }

    @PostMapping
    @Operation(summary = "Save output",
            description = "Save a output in database"
    )
    public SaidaProduto createSaida(@RequestBody SaidaProdutoDto saidaProdutoDto) throws ResourceNotFoundException {
        log.info("Cadastro saida: {}", saidaProdutoDto);
        return saidaProdutoService.saveSaida(saidaProdutoDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update output",
            description = "Update a output in databse"
    )
    public ResponseEntity<SaidaProdutoDto> updateSaida(@PathVariable(name = "id") Long id, @RequestBody SaidaProdutoDto saidaProdutoDto) throws ResourceNotFoundException {
        log.info("Atualizando saida: {}", saidaProdutoDto);
        return ResponseEntity.ok(saidaProdutoService.editSaida(id, saidaProdutoDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove output",
            description = "Remove a output in databse"
    )
    public Map<String, Boolean> deleteSaida(@PathVariable(name = "id") Long id) throws Exception {
        log.info("Deletando saida: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", saidaProdutoService.deleteSaida(id));
        return response;
    }
}
