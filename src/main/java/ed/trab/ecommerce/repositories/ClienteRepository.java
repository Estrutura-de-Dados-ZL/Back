package ed.trab.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ed.trab.ecommerce.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
