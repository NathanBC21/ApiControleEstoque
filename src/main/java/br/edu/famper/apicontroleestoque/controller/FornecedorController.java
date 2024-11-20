package br.edu.famper.apicontroleestoque.controller;

import br.edu.famper.apicontroleestoque.dto.FornecedorDto;
import br.edu.famper.apicontroleestoque.exception.ResourceNotFoundException;
import br.edu.famper.apicontroleestoque.model.Fornecedor;
import br.edu.famper.apicontroleestoque.service.FornecedorService;
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
@RequestMapping("/api/fornecdor")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Fornecedor",
        description = "Operation for suppliers")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all suppliers from DB",
            description = "Fetches all suppliers from DB and return " +
                    "in JSON Array"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "404", description = "not found")
    })
    public List<FornecedorDto> getAllSuppliers() {
        log.info("Buscando todas os fornecedores");
        return fornecedorService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get one supplier from DB",
            description = "Fetches one supplier from DB and return " +
                    "in JSON Object"
    )
    @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "successful"),
          @ApiResponse(responseCode = "404", description = "not found")
    })
    public ResponseEntity<FornecedorDto> getSupplierById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando fornecedor por id: {}", id);
        return ResponseEntity.ok().body(fornecedorService.getFornecedorByid(id));
    }

    @PostMapping
    @Operation(summary = "Save supplier",
            description = "Save a supplier in database"
    )
    public Fornecedor createFornecedor(@RequestBody FornecedorDto fornecedorDto) throws ResourceNotFoundException {
        log.info("Cadastro fornecedor: {}", fornecedorDto);
        return fornecedorService.saveFornecedor(fornecedorDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update supplier",
            description = "Update a supplier in database"
    )
    public ResponseEntity<FornecedorDto> updateFornecedor(@PathVariable(name = "id") Long id, @RequestBody FornecedorDto fornecedorDto) throws ResourceNotFoundException {
        log.info("Atualizando fornecedor: {}", fornecedorDto);
        return ResponseEntity.ok(fornecedorService.editFornecedor(id, fornecedorDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove supplier",
            description = "Remove a supplier in database"
    )

    public Map<String, Boolean> deleteFornecedor(@PathVariable(name = "id") Long id) throws Exception {
        log.info("Deletando fornecedor: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", fornecedorService.deleteFornecedor(id));
        return response;
    }


}
