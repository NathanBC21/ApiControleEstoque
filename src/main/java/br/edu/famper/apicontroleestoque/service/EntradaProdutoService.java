package br.edu.famper.apicontroleestoque.service;

import br.edu.famper.apicontroleestoque.dto.EntradaProdutoDto;
import br.edu.famper.apicontroleestoque.model.EntradaProduto;
import br.edu.famper.apicontroleestoque.repository.EntradaProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EntradaProdutoService {

    @Autowired
    private static EntradaProdutoRepository entradaProdutoRepository;

    public List<EntradaProdutoDto> getAllEnterings() {
        return entradaProdutoRepository
                .findAll()
                .stream()
                .map(entradaProduto -> EntradaProdutoDto
                        .builder()
                        .codigo(entradaProduto.getCodigo())
                        .descricao(entradaProduto.getDescricao())
                        .operador(entradaProduto.getOperador())
                        .quantidade(entradaProduto.getQuantidade())
                        .data(entradaProduto.getData())
                        .notaFiscal(entradaProduto.getNotaFiscal())
                        .valorUnitario(entradaProduto.getValorUnitario())
                        .valorTotal(entradaProduto.getValorTotal())
                        .saldo(entradaProduto.getSaldo())
                        .build()
                )
                .toList();
    }

    public static EntradaProdutoDto getEntradaProdutoById(Long id) {
        EntradaProduto entradaProd = entradaProdutoRepository.findById(id).orElseThrow();
        return new EntradaProdutoDto()
                .builder()
                .codigo(entradaProd.getCodigo())
                .descricao(entradaProd.getCodigo())
                .descricao(entradaProd.getDescricao())
                .operador(entradaProd.getOperador())
                .quantidade(entradaProd.getQuantidade())
                .data(entradaProd.getData())
                .notaFiscal(entradaProd.getNotaFiscal())
                .valorUnitario(entradaProd.getValorUnitario())
                .valorTotal(entradaProd.getValorTotal())
                .saldo(entradaProd.getSaldo())
                .build();
    }


    public EntradaProduto saveEntradaProduto(EntradaProdutoDto entradaProdutoDto) {
        EntradaProduto entradaProduto = new EntradaProduto();
        entradaProduto.setCodigo(entradaProdutoDto.getCodigo());
        entradaProduto.setDescricao(entradaProdutoDto.getDescricao());
        entradaProduto.setOperador(entradaProdutoDto.getOperador());
        entradaProduto.setQuantidade(entradaProdutoDto.getQuantidade());
        entradaProduto.setData(entradaProdutoDto.getData());
        entradaProduto.setNotaFiscal(entradaProdutoDto.getNotaFiscal());
        entradaProduto.setValorUnitario(entradaProdutoDto.getValorUnitario());
        entradaProduto.setValorTotal(entradaProdutoDto.getValorTotal());
        entradaProduto.setSaldo(entradaProdutoDto.getSaldo());
        return entradaProdutoRepository.save(entradaProduto);
    }


    public EntradaProdutoDto editEntradaProduto(Long id, EntradaProdutoDto entradaProdutoDto) {
        EntradaProduto entradaProduto = entradaProdutoRepository.findById(id).orElseThrow();
        entradaProduto.setCodigo(entradaProdutoDto.getCodigo());
        entradaProduto.setDescricao(entradaProdutoDto.getDescricao());
        entradaProduto.setOperador(entradaProdutoDto.getOperador());
        entradaProduto.setQuantidade(entradaProdutoDto.getQuantidade());
        entradaProduto.setData(entradaProdutoDto.getData());
        entradaProduto.setNotaFiscal(entradaProdutoDto.getNotaFiscal());
        entradaProduto.setValorUnitario(entradaProdutoDto.getValorUnitario());
        entradaProduto.setValorTotal(entradaProdutoDto.getValorTotal());
        entradaProduto.setSaldo(entradaProdutoDto.getSaldo());
        EntradaProduto entradaProdutoEdited = entradaProdutoRepository.save(entradaProduto);
        return new EntradaProdutoDto()
                .builder()
                .codigo(entradaProdutoEdited.getCodigo())
                .descricao(entradaProdutoEdited.getDescricao())
                .operador(entradaProdutoEdited.getOperador())
                .quantidade(entradaProdutoEdited.getQuantidade())
                .data(entradaProdutoEdited.getData())
                .notaFiscal(entradaProdutoEdited.getNotaFiscal())
                .valorUnitario(entradaProdutoEdited.getValorUnitario())
                .valorTotal(entradaProdutoEdited.getValorTotal())
                .saldo(entradaProdutoEdited.getSaldo())
                .build();
    }

    public Boolean deleteEntradaProduto(Long id) {
        try {
            EntradaProduto entradaProduto = entradaProdutoRepository.findById(id).orElseThrow();
            entradaProdutoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
