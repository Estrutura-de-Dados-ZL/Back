package ed.trab.ecommerce.controllers;

import java.util.Map;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ed.trab.ecommerce.models.Produto;
import ed.trab.ecommerce.services.CarrinhoService;

@RequestMapping("carrinho")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CarrinhoController {

    private CarrinhoService carrinhoService;

    @Autowired
    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Queue<Produto> checkout(@RequestBody() Map<String, String> pilha) throws ResponseStatusException {
        try {
            return carrinhoService.checkout(pilha);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carrinho não contem itens.");
        }
    }

}
