package br.edu.famper.apicontroleestoque.service;

import br.edu.famper.apicontroleestoque.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EntradaProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
}
