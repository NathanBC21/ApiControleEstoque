package br.edu.famper.apicontroleestoque.repository;

import br.edu.famper.apicontroleestoque.model.EntradaProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaProdutoRepository extends JpaRepository<EntradaProduto, Integer> {
}
