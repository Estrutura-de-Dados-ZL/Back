package ed.trab.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ed.trab.ecommerce.models.Checkout;

public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

}
