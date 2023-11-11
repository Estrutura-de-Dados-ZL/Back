package ed.trab.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ed.trab.ecommerce.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
