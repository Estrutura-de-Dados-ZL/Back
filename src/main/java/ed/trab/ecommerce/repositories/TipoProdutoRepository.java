package ed.trab.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ed.trab.ecommerce.models.TipoProduto;
import org.springframework.data.jpa.repository.Query;

public interface TipoProdutoRepository extends JpaRepository<TipoProduto, Long> {

    @Query("SELECT t FROM TipoProduto t WHERE LOWER(t.descricao) LIKE LOWER(CONCAT('%', :descricao, '%'))")
    List<TipoProduto> findByDescricao(String descricao);
}
