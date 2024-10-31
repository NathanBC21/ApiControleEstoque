package br.edu.famper.apicontroleestoque.repository;

import br.edu.famper.apicontroleestoque.model.SaidaProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaidaProdutoRepository extends JpaRepository<SaidaProduto, Integer> {
}
