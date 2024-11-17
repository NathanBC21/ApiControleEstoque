package br.edu.famper.apicontroleestoque.service;

import br.edu.famper.apicontroleestoque.dto.SaidaProdutoDto;
import br.edu.famper.apicontroleestoque.model.SaidaProduto;
import br.edu.famper.apicontroleestoque.repository.SaidaProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaidaProdutoService {

    @Autowired
    private final ProdutoService produtoService;
    @Autowired
    private SaidaProdutoRepository saidaProdutoRepository;

    public List<SaidaProdutoDto> getAllSaidas() {
        return saidaProdutoRepository
                .findAll()
                .stream()
                .map(saidaProduto -> SaidaProdutoDto
                        .builder()
                        .codigo(saidaProduto.getCodigo())
                        .descricao(saidaProduto.getDescricao())
                        .operador(saidaProduto.getOperador())
                        .quantidade(saidaProduto.getQuantidade())
                        .data(saidaProduto.getData())
                        .colaborador(saidaProduto.getColaborador())
                        .setor(saidaProduto.getSetor())
                        .saldo(saidaProduto.getSaldo())
                        .build()
                )
                .toList();
    }

    public SaidaProdutoDto getSaidasById(Long id) {
        SaidaProduto saidaProd = saidaProdutoRepository.findById(id).orElseThrow();
        return new SaidaProdutoDto()
                .builder()
                .codigo(saidaProd.getCodigo())
                .descricao(saidaProd.getDescricao())
                .operador(saidaProd.getOperador())
                .quantidade(saidaProd.getQuantidade())
                .data(saidaProd.getData())
                .colaborador(saidaProd.getColaborador())
                .setor(saidaProd.getSetor())
                .saldo(saidaProd.getSaldo())
                .build();
    }

    public SaidaProduto saveSaida(SaidaProdutoDto saidaProdutoDto) {
        SaidaProduto saidaProduto = new SaidaProduto();
        saidaProduto.setCodigo(saidaProduto.getCodigo());
        saidaProduto.setDescricao(saidaProduto.getDescricao());
        saidaProduto.setOperador(saidaProduto.getOperador());
        saidaProduto.setQuantidade(saidaProduto.getQuantidade());
        saidaProduto.setData(saidaProduto.getData());
        saidaProduto.setColaborador(saidaProduto.getColaborador());
        saidaProduto.setSetor(saidaProduto.getSetor());
        saidaProduto.setSaldo(saidaProduto.getSaldo());
        return saidaProdutoRepository.save(saidaProduto);
    }

    public SaidaProdutoDto editSaida(Long id, SaidaProdutoDto saidaProdutoDto) {
        SaidaProduto saidaProduto = saidaProdutoRepository.findById(id).orElseThrow();
                saidaProduto.setCodigo(saidaProdutoDto.getCodigo());
                saidaProduto.setDescricao(saidaProdutoDto.getDescricao());
                saidaProduto.setOperador(saidaProdutoDto.getOperador());
                saidaProduto.setQuantidade(saidaProdutoDto.getQuantidade());
                saidaProduto.setData(saidaProdutoDto.getData());
                saidaProduto.setColaborador(saidaProdutoDto.getColaborador());
                saidaProduto.setSetor(saidaProdutoDto.getSetor());
                saidaProduto.setSaldo(saidaProdutoDto.getSaldo());
                SaidaProduto saidaEdited = saidaProdutoRepository.save(saidaProduto);
                return new SaidaProdutoDto()
                        .builder()
                        .codigo(saidaEdited.getCodigo())
                        .descricao(saidaEdited.getDescricao())
                        .operador(saidaEdited.getOperador())
                        .quantidade(saidaEdited.getQuantidade())
                        .data(saidaEdited.getData())
                        .colaborador(saidaEdited.getColaborador())
                        .setor(saidaEdited.getSetor())
                        .saldo(saidaEdited.getSaldo())
                        .build();

    }

    public boolean deleteSaida(Long id) {
        try {
            SaidaProduto saidaProduto = saidaProdutoRepository.findById(id).orElseThrow();
            saidaProdutoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
