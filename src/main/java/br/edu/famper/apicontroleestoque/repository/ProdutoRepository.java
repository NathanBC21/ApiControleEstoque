package br.edu.famper.apicontroleestoque.repository;

import br.edu.famper.apicontroleestoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
}
