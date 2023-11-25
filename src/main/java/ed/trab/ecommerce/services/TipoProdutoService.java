package ed.trab.ecommerce.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ed.trab.ecommerce.models.TipoProduto;
import ed.trab.ecommerce.repositories.TipoProdutoRepository;

@Service
public class TipoProdutoService {
    private TipoProdutoRepository tipoProdutoRepository;

    @Autowired
    public TipoProdutoService(TipoProdutoRepository tipoProdutoRepository) {
        this.tipoProdutoRepository = tipoProdutoRepository;
    }

    public TipoProduto toModel(Map<String, TipoProduto> tipoProduto) {
        return tipoProduto.get("tipoProduto");
    }

    public void saveTipoProduto(TipoProduto tipoProduto) {
        this.tipoProdutoRepository.save(tipoProduto);
    }

    public List<TipoProduto> getTipoProduto() {
        return this.tipoProdutoRepository.findAll();
    }

    public TipoProduto getTipoProdutoById(Long id) {
        return this.tipoProdutoRepository.findById(id).get();
    }

    public void updateTipoProdutoById(Long id, TipoProduto tipoProduto) throws RuntimeException {
        if (this.tipoProdutoRepository.existsById(id)) {
            this.tipoProdutoRepository.save(tipoProduto);
        } else {
            throw new RuntimeException("TipoProduto não encontrado");
        }
    }

    public List<TipoProduto> getTipoProdutoByDesricao(String descricao) {
        return this.tipoProdutoRepository.findByDescricao(descricao);
    }

    public void deleteTipoProdutoById(Long id) {
        this.tipoProdutoRepository.deleteById(id);
    }
}