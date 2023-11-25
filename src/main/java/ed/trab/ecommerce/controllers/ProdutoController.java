package ed.trab.ecommerce.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ed.trab.ecommerce.models.Produto;
import ed.trab.ecommerce.services.ProdutoService;
import ed.trab.ecommerce.services.TipoProdutoService;

@RequestMapping("produto")
@RestController
@CrossOrigin(origins = { "http://localhost:4200", "https://ed-front.vercel.app" })
public class ProdutoController {

    private ProdutoService produtoService;

    private TipoProdutoService tipoProdutoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService, TipoProdutoService tipoProdutoService) {
        this.produtoService = produtoService;
        this.tipoProdutoService = tipoProdutoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Produto> getProduto() throws ResponseStatusException {
        try {
            return produtoService.getProduto();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produtos não encontrados");
        }
    }

    @PostMapping("/{tipoProdutoId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProduto(@RequestBody Map<String, Produto> produto, @PathVariable Long tipoProdutoId)
            throws ResponseStatusException {
        try {
            Produto produtoResponse = this.produtoService.toModel(produto);
            produtoResponse.setTipoProduto(tipoProdutoService.getTipoProdutoById(tipoProdutoId));
            produtoService.saveProduto(produtoResponse);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto já existe");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProdutoById(@PathVariable Long id, @RequestBody Map<String, Produto> produto)
            throws RuntimeException, ResponseStatusException {
        try {
            Produto produtoResponse = produtoService.toModel(produto);
            produtoService.updateProdutoById(id, produtoResponse);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não pode ser atualizado");
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Produto getProdutoById(@PathVariable Long id) throws ResponseStatusException {
        try {
            return produtoService.getProdutoById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
    }

    @GetMapping("/{tipoProdutoId}/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public List<Produto> getProdutoByNomeTipo(@PathVariable Long tipoProdutoId, @PathVariable String nome)
            throws ResponseStatusException {
        try {
            return produtoService.getProdutoByNomeTipo(nome, tipoProdutoId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
    }

    @GetMapping("/nome/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public List<Produto> getProdutoByNome(@PathVariable String nome) throws ResponseStatusException {
        try {
            return produtoService.getProdutoByNome(nome);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProdutoById(@PathVariable Long id) throws ResponseStatusException {
        try {
            produtoService.deleteProdutoById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
    }
}
