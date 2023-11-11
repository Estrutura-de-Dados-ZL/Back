package ed.trab.ecommerce.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ed.trab.ecommerce.models.Produto;
import ed.trab.ecommerce.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void saveProduto(Produto produto) {
        this.produtoRepository.save(produto);
    }

    public List<Produto> getProduto() {
        return this.produtoRepository.findAll();
    }

    public Produto getProdutoById(Long id) {
        return this.produtoRepository.findById(id).get();
    }

    public void updateProdutoById(Long id, Produto produto) throws RuntimeException {
        if (this.produtoRepository.existsById(id)) {
            this.produtoRepository.save(produto);
        } else {
            throw new RuntimeException("produto não encontrado");
        }
    }

    public void deleteProdutoById(Long id) {
        this.produtoRepository.deleteById(id);
    }
}
