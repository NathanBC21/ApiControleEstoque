package br.edu.famper.apicontroleestoque.service;

import br.edu.famper.apicontroleestoque.dto.CategoriaDto;
import br.edu.famper.apicontroleestoque.model.Categoria;
import br.edu.famper.apicontroleestoque.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDto> getAllCategories() {
        return categoriaRepository
                .findAll()
                .stream()
                .map(categoria -> CategoriaDto
                        .builder()
                        .nome(categoria.getNome())
                        .build()
                )
                .toList();
    }

    public CategoriaDto getCategoriaById(Long id) {
        Categoria cat = categoriaRepository.findById(id).orElseThrow();
        return new CategoriaDto()
                .builder()
                .nome(cat.getNome())
                .build();
    }

    public Categoria saveCategoria(CategoriaDto categoriaDto) {
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaDto.getNome());
        return categoriaRepository.save(categoria);
    }

    public CategoriaDto editCategoria(Long id, CategoriaDto categoriaDto) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow();
        categoria.setNome(categoriaDto.getNome());
        Categoria categoriaEdited = categoriaRepository.save(categoria);
        return new CategoriaDto()
                .builder()
                .nome(categoriaEdited.getNome())
                .build();
    }


    public Boolean deleteCategoria(Long id) {
        try {
            Categoria categoria = categoriaRepository.findById(id).orElseThrow();
            categoriaRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
