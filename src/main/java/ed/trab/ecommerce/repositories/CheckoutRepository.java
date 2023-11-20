package ed.trab.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ed.trab.ecommerce.models.Checkout;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

    @Query(value = "SELECT checkout FROM Checkout checkout join fetch checkout.lista WHERE checkout.id = :id")
    public Optional<Checkout> findByIdFetch(long id);

    @Query(value = "SELECT checkout FROM Checkout checkout join fetch checkout.lista")
    public List<Checkout> findAllFetch();
}
