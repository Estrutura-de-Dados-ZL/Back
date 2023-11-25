package ed.trab.ecommerce.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import ed.trab.ecommerce.models.TipoProduto;
import ed.trab.ecommerce.services.TipoProdutoService;

@RequestMapping("tipoProduto")
@RestController
@CrossOrigin(origins = { "http://localhost:4200", "https://ed-front.vercel.app" })
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
    public void saveTipoProduto(@RequestBody Map<String, TipoProduto> tipoProduto) throws ResponseStatusException {
        try {
            TipoProduto tipoProdutoModel = tipoProdutoService.toModel(tipoProduto);
            tipoProdutoService.saveTipoProduto(tipoProdutoModel);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "TipoProduto já existe");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTipoProdutoById(@PathVariable Long id, @RequestBody Map<String, TipoProduto> tipoProduto)
            throws RuntimeException, ResponseStatusException {
        try {
            TipoProduto tipoProdutoModel = tipoProdutoService.toModel(tipoProduto);
            tipoProdutoService.updateTipoProdutoById(id, tipoProdutoModel);
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

    @GetMapping("/descricao/{descricao}")
    @ResponseStatus(HttpStatus.OK)
    public List<TipoProduto> getTipoProdutoByDescricao(@PathVariable String descricao) throws ResponseStatusException {
        try {
            return tipoProdutoService.getTipoProdutoByDesricao(descricao);
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
