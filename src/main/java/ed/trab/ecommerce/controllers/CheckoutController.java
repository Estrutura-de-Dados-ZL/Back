package ed.trab.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ed.trab.ecommerce.models.Checkout;
import ed.trab.ecommerce.services.CheckoutService;

@RequestMapping("checkout")
@RestController
@CrossOrigin(origins = { "http://localhost:4200", "https://ed-front.vercel.app" })
public class CheckoutController {
    
    @Autowired
    private CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService){
        this.checkoutService = checkoutService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Checkout> getCheckout() throws ResponseStatusException {
        try {
            return checkoutService.getCheckout();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Checkouts não encontrados");
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCheckout(@Param(value = "checkout") Checkout checkout) throws ResponseStatusException {
        try {
            checkoutService.saveCheckout(checkout);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Checkout já existe");
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Checkout getCheckoutById(@PathVariable Long id) throws ResponseStatusException {
        try {
            return checkoutService.getcheckoutById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Checkout não encontrado");
        }
    }
}
