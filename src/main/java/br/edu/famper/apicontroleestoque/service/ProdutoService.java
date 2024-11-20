package br.edu.famper.apicontroleestoque.service;

import br.edu.famper.apicontroleestoque.dto.ProdutoDto;
import br.edu.famper.apicontroleestoque.exception.ResourceNotFoundException;
import br.edu.famper.apicontroleestoque.model.Categoria;
import br.edu.famper.apicontroleestoque.model.Fornecedor;
import br.edu.famper.apicontroleestoque.model.Produto;
import br.edu.famper.apicontroleestoque.repository.CategoriaRepository;
import br.edu.famper.apicontroleestoque.repository.FornecedorRepository;
import br.edu.famper.apicontroleestoque.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<ProdutoDto> getAllProdutos() {
        log.info("Buscando todos os produtos");
        return produtoRepository
                .findAll()
                .stream()
                .map(produto -> ProdutoDto
                        .builder()
                        .codigo(produto.getCodigo())
                        .descricao(produto.getDescricao())
                        .marca(produto.getMarca())
                        .modelo(produto.getModelo())
                        .categoriaId(produto.getCategoria().getId())
                        .fornecedorIds(produto.getFornecedor().stream()
                                .map(Fornecedor::getId)
                                .collect(Collectors.toSet()))
                        .build()
                )
                .toList();
    }


    public ProdutoDto getProdutoById(Long id) throws ResourceNotFoundException {
        log.info("Buscando produto por ID: {}", id);
        Produto prod = produtoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + id));
        return ProdutoDto.builder()
                .codigo(prod.getCodigo())
                .descricao(prod.getDescricao())
                .marca(prod.getMarca())
                .modelo(prod.getModelo())
                .categoriaId(prod.getCategoria().getId())
                .fornecedorIds(prod.getFornecedor().stream()
                        .map(Fornecedor::getId)
                        .collect(Collectors.toSet()))
                .build();
    }


    @Transactional
    public Produto saveProduto(ProdutoDto produtoDto) throws ResourceNotFoundException {
        log.info("Salvando produto: {}", produtoDto);

        Optional<Produto> produtoExistente = produtoRepository.findBycodigo(produtoDto.getCodigo());

        if (produtoExistente == null) {
            throw new ResourceNotFoundException ("Produto com código " + produtoDto.getCodigo() + " já existe.");

        }

        Produto produto = new Produto();
        produto.setCodigo(produtoDto.getCodigo());
        produto.setDescricao(produtoDto.getDescricao());
        produto.setMarca(produtoDto.getMarca());
        produto.setModelo(produtoDto.getModelo());

        Categoria categoria = categoriaRepository.findById(produtoDto.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com o ID: " + produtoDto.getCategoriaId()));
        produto.setCategoria(categoria);

        if (produtoDto.getFornecedorIds() != null && !produtoDto.getFornecedorIds().isEmpty()) {
            Set<Fornecedor> fornecedor = produtoDto.getFornecedorIds().stream()
                    .map(id -> {
                        try {
                            return fornecedorRepository.findById(id)
                                    .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado com ID: " + id));
                        } catch (ResourceNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toSet());
            produto.setFornecedor(fornecedor);
        }

        return produtoRepository.save(produto);
    }


    public ProdutoDto editProduto(Long id, ProdutoDto produtoDto) {
        Produto produto = produtoRepository.findById(id).orElseThrow();
        produto.setCodigo(produtoDto.getCodigo());
        produto.setDescricao(produtoDto.getDescricao());
        produto.setMarca(produtoDto.getMarca());
        produto.setModelo(produtoDto.getModelo());
        Produto produtoEdited = produtoRepository.save(produto);
        return new ProdutoDto()
                .builder()
                .codigo(produtoEdited.getCodigo())
                .descricao(produtoEdited.getDescricao())
                .marca(produtoEdited.getMarca())
                .modelo(produtoEdited.getModelo())
                .build();
    }

    public boolean deleteProduto(Long id) throws ResourceNotFoundException {
        log.info("Tentando Deletar produto com ID: {}", id);
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + id));
        produtoRepository.delete(produto);
        log.info("Produto deletado com uscesso: {}", id);
        return true;

    }
}
