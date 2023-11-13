package ed.trab.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ed.trab.ecommerce.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p FROM Produto p WHERE p.tipoProduto.id = :tipoProdutoId AND LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Produto> findByNomeTipo(@Param("nome") String nome, @Param("tipoProdutoId") Long tipo);

    @Query("SELECT p FROM Produto p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Produto> findByNomeTipo(@Param("nome") String nome);

    @Query("SELECT p FROM Produto p WHERE p.tipoProduto.id = :tipoProdutoId ")
    List<Produto> findByNomeTipo(@Param("tipoProdutoId") Long tipo);
}
