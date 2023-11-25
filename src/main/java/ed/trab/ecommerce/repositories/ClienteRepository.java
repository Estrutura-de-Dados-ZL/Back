package ed.trab.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ed.trab.ecommerce.models.Cliente;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(value = "SELECT cliente FROM Cliente cliente where LOWER(cliente.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Cliente> findByNome(String nome);
}
