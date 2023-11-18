package ed.trab.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ed.trab.ecommerce.models.Checkout;
import ed.trab.ecommerce.repositories.CheckoutRepository;

@Service
public class CheckoutService {
    
    @Autowired
    private CheckoutRepository checkoutRepository;

    public void saveCheckout(Checkout checkout){
        calculaValorTotal(checkout);
        this.checkoutRepository.save(checkout);
    }

    public List<Checkout> getCheckout() {
        return this.checkoutRepository.findAll();
    }

    public Checkout getcheckoutById(Long id) {
        return this.checkoutRepository.findById(id).get();
    }

    private void calculaValorTotal(Checkout checkout){
        checkout.getLista().forEach(produto -> checkout.setValorTotal(checkout.getValorTotal() + produto.getValor()));
    }
}
