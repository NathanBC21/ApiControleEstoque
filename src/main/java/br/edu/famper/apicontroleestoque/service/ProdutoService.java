package br.edu.famper.apicontroleestoque.service;

import br.edu.famper.apicontroleestoque.dto.ProdutoDto;
import br.edu.famper.apicontroleestoque.model.Produto;
import br.edu.famper.apicontroleestoque.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDto> getAllProdutos() {
        return produtoRepository
                .findAll()
                .stream()
                .map(produto -> ProdutoDto
                        .builder()
                        .codigo(produto.getCodigo())
                        .descricao(produto.getDescricao())
                        .marca(produto.getMarca())
                        .modelo(produto.getModelo())
                        .build()
                )
                .toList();
    }


    public ProdutoDto getProdutoById(Long id) {
        Produto prod = produtoRepository.findById(id).orElseThrow();
        return new ProdutoDto()
                .builder()
                .codigo(prod.getCodigo())
                .descricao(prod.getDescricao())
                .marca(prod.getMarca())
                .modelo(prod.getModelo())
                .build();
    }

    public Produto saveProduto(ProdutoDto produtoDto) {
        Produto produto = new Produto();
        produto.setCodigo(produtoDto.getCodigo());
        produto.setDescricao(produtoDto.getDescricao());
        produto.setMarca(produtoDto.getMarca());
        produto.setModelo(produtoDto.getModelo());
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

    public boolean deleteProduto(Long id) {
        try {
            Produto produto = produtoRepository.findById(id).orElseThrow();
            produtoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
