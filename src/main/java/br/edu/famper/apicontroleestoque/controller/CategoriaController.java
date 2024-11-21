package br.edu.famper.apicontroleestoque.controller;

import br.edu.famper.apicontroleestoque.dto.CategoriaDto;
import br.edu.famper.apicontroleestoque.exception.ResourceNotFoundException;
import br.edu.famper.apicontroleestoque.model.Categoria;
import br.edu.famper.apicontroleestoque.service.CategoriaService;
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
@RequestMapping("/api/categoria")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Categoria",
description = "Operation for categories")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all cities categories from DB",
    description = "Fetches all categories from DB and return " +
    "in JSON Array"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "404", description = "not found")
    })
    public List<CategoriaDto> getAllCategories() {
        log.info("Buscando todas as categorias");
        return categoriaService.getAllCategories();
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get one category from DB",
    description = "Fetches one catgory from DB and return" +
    "in JSON Object"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "404", description = "not found")
    })
    public ResponseEntity<CategoriaDto> getCategoriaById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando categoria por id: {}", id);
        return ResponseEntity.ok().body(categoriaService.getCategoriaById(id));
    }

    @PostMapping
    @Operation(summary = "Save category",
    description = "Save a category in database"
    )
    public Categoria createCategoria(@RequestBody CategoriaDto categoriaDto) throws ResourceNotFoundException {
        log.info("Cadastro categoria: {}", categoriaDto);
        return categoriaService.saveCategoria(categoriaDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update category",
            description = "Update a category in database"
    )
    public ResponseEntity<CategoriaDto> updateCategoria(@PathVariable(name = "id") Long id, @RequestBody CategoriaDto categoriaDto) throws ResourceNotFoundException {
       log.info("Atualizando categoria: {}", categoriaDto);
       return ResponseEntity.ok(categoriaService.editCategoria(id,categoriaDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove category",
            description = "Remove a category in database"
    )
    public Map<String, Boolean> deleteCategoria(@PathVariable(name = "id") Long id) throws Exception{
        log.info("Deletando categoria: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", categoriaService.deleteCategoria(id));
        return response;
    }
}
