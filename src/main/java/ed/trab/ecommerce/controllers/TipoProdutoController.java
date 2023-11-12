package ed.trab.ecommerce.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ed.trab.ecommerce.models.TipoProduto;
import ed.trab.ecommerce.services.TipoProdutoService;

@RequestMapping("tipoProduto")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TipoProdutoController {
    private TipoProdutoService tipoProdutoService;

    @Autowired
    public TipoProdutoController(TipoProdutoService tipoProdutoService) {
        this.tipoProdutoService = tipoProdutoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TipoProduto> getTipoProduto() throws ResponseStatusException {
        try {
            return tipoProdutoService.getTipoProduto();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TipoProdutos não encontrados");
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTipoProduto(TipoProduto tipoProduto) throws ResponseStatusException {
        try {
            tipoProdutoService.saveTipoProduto(tipoProduto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "TipoProduto já existe");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTipoProdutoById(@PathVariable Long id, TipoProduto tipoProduto)
            throws RuntimeException, ResponseStatusException {
        try {
            tipoProdutoService.updateTipoProdutoById(id, tipoProduto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "TipoProduto não pode ser atualizado");
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TipoProduto getTipoProdutoById(@PathVariable Long id) throws ResponseStatusException {
        try {
            return tipoProdutoService.getTipoProdutoById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TipoProduto não encontrado");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTipoProdutoById(@PathVariable Long id) throws ResponseStatusException {
        try {
            tipoProdutoService.deleteTipoProdutoById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TipoProduto não encontrado");
        }
    }
}
