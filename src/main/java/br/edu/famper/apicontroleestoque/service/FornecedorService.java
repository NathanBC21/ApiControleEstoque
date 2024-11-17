package br.edu.famper.apicontroleestoque.service;

import br.edu.famper.apicontroleestoque.dto.FornecedorDto;
import br.edu.famper.apicontroleestoque.model.Fornecedor;
import br.edu.famper.apicontroleestoque.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FornecedorService {

    @Autowired
    private final FornecedorRepository fornecedorRepository;

    public List<FornecedorDto> getAllSuppliers() {
        return fornecedorRepository
                .findAll()
                .stream()
                .map(fornecedor -> FornecedorDto
                        .builder()
                        .nome(fornecedor.getNome())
                        .cpf_cnpj(fornecedor.getCpf_cnpj())
                        .telefone(fornecedor.getTelefone())
                        .email(fornecedor.getEmail())
                        .endereco(fornecedor.getEndereco())
                        .build()
                )
                .toList();
    }


    public FornecedorDto getFornecedorByid(Long id) {
        Fornecedor fornec = fornecedorRepository.findById(id).orElseThrow();
        return new FornecedorDto()
                .builder()
                .nome(fornec.getNome())
                .cpf_cnpj(fornec.getCpf_cnpj())
                .telefone(fornec.getTelefone())
                .email(fornec.getEmail())
                .endereco(fornec.getEndereco())
                .build();
    }


    public Fornecedor saveFornecedor(FornecedorDto fornecedorDto) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(fornecedorDto.getNome());
        fornecedor.setCpf_cnpj(fornecedorDto.getCpf_cnpj());
        fornecedor.setTelefone(fornecedorDto.getTelefone());
        fornecedor.setEmail(fornecedorDto.getEmail());
        fornecedor.setEndereco(fornecedorDto.getEndereco());
        return fornecedorRepository.save(fornecedor);
    }


    public FornecedorDto editFornecedor(Long id, FornecedorDto fornecedorDto) {
        Fornecedor fornecedor = fornecedorRepository.findById(id).orElseThrow();
        fornecedor.setNome(fornecedorDto.getNome());
        fornecedor.setCpf_cnpj(fornecedorDto.getCpf_cnpj());
        fornecedor.setTelefone(fornecedorDto.getTelefone());
        fornecedor.setEmail(fornecedorDto.getEmail());
        fornecedor.setEndereco(fornecedorDto.getEndereco());
        Fornecedor fornecedorEdit = fornecedorRepository.save(fornecedor);
        return new FornecedorDto()
                .builder()
                .nome(fornecedorEdit.getNome())
                .cpf_cnpj(fornecedorEdit.getCpf_cnpj())
                .telefone(fornecedorEdit.getTelefone())
                .email(fornecedorEdit.getTelefone())
                .endereco(fornecedorEdit.getEndereco())
                .build();
    }


    public Boolean deleteFornecedor(Long id) {
        try {
            Fornecedor fornecedor = fornecedorRepository.findById(id).orElseThrow();
            fornecedorRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
