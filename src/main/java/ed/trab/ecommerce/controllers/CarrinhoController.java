package ed.trab.ecommerce.controllers;

import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ed.trab.ecommerce.models.Produto;
import ed.trab.ecommerce.services.CarrinhoService;

@RequestMapping("carrinho")
@RestController
public class CarrinhoController {
    
    private CarrinhoService carrinhoService;

    @Autowired
    public CarrinhoController(CarrinhoService carrinhoService){
        this.carrinhoService = carrinhoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(Produto produto) throws ResponseStatusException{
        try {
            carrinhoService.adicionar(produto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno, tente novamente.");
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void remover() throws ResponseStatusException{
        try {
            carrinhoService.remover();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno, tente novamente.");
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Queue<Produto> checkout() throws ResponseStatusException{
        try {
            return carrinhoService.checkout();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carrinho não contem itens.");
        }
    }
    
}
