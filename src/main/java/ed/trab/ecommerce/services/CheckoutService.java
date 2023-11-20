package ed.trab.ecommerce.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ed.trab.ecommerce.models.Checkout;
import ed.trab.ecommerce.models.Cliente;
import ed.trab.ecommerce.models.Produto;
import ed.trab.ecommerce.models.TipoProduto;
import ed.trab.ecommerce.repositories.CheckoutRepository;

@Service
public class CheckoutService {
    
    @Autowired
    private CheckoutRepository checkoutRepository;

    private static ProdutoService produtoService;
    private ClienteService clienteService;

    static HashMap<Long, Integer> hash = new HashMap<>();

    CheckoutService(ProdutoService produtoService, ClienteService clienteService) {
        this.produtoService = produtoService;
        this.clienteService = clienteService;
    }

    public Checkout checkout(Map<String, String> pilhaMap, Long idCliente) throws RuntimeException {
        Cliente cliente = clienteService.getClienteById(idCliente);
        Stack<Produto> pilhaInvertida = new Stack<>();
        Stack<Produto> pilha = new Stack<>();
        String pilhaJson = pilhaMap.get("pilha");
        JsonNode jsonNode = parseToJsonNode(pilhaJson);
        percorrerJsonNode(jsonNode, pilhaInvertida);

        if (pilhaInvertida.isEmpty()) {
            throw new RuntimeException("O carrinho não possui itens.");
        }

        while (!pilhaInvertida.isEmpty()) {
            pilha.push(pilhaInvertida.pop());
        }

        Queue<Produto> fila = validaProdutos(pilha);

        atualizaProduto();

        Queue<Produto> filaAtualizada = atualizaFila(fila);
        Checkout checkout = criaCheckout(filaAtualizada, cliente);
        saveCheckout(checkout);
        return checkout;
    }

    private Checkout criaCheckout(Queue<Produto> fila, Cliente cliente){
        List<Produto> listaProdutos = new LinkedList<>(fila);

        Checkout novoCheckout = new Checkout();
        novoCheckout.setCliente(cliente);
        novoCheckout.setLista(listaProdutos);
        calculaValorTotal(novoCheckout, listaProdutos);

        return checkoutRepository.save(novoCheckout);
    }

    private Queue<Produto> validaProdutos(Stack<Produto> pilha) throws RuntimeException {
        Queue<Produto> fila = new LinkedList<>();

        while (!pilha.isEmpty()) {
            Produto produto = pilha.peek();
            Produto produtoEstoque = produtoService.getProdutoById(produto.getId());
            if (temNoEstoque(produtoEstoque)) {
                fila.add(pilha.pop());
            } else {
                hash = new HashMap<>();
                throw new RuntimeException(String.format("%s fora de estoque.",
                        pilha.peek().getNome()));
            }
        }
        return fila;
    }

    private static void atualizaProduto() {
        hash.forEach((id, quantidade) -> {
            Produto produto = produtoService.getProdutoById(id);
            produto.setQuantidade(produto.getQuantidade() - quantidade);
            produtoService.updateProdutoById(id, produto);
        });

        hash = new HashMap<>();
    }

    private static Queue<Produto> atualizaFila(Queue<Produto> fila) {
        Queue<Produto> filaAtualizada = new LinkedList<>();
        fila.forEach(produto -> {
            filaAtualizada.add(produtoService.getProdutoById(produto.getId()));
        });

        return filaAtualizada;
    }

    private boolean temNoEstoque(Produto produto) {
        if (hash.get(produto.getId()) != null) {
            if (hash.get(produto.getId()) < produto.getQuantidade()) {
                hash.put(produto.getId(), hash.get(produto.getId()) + 1);
                return true;
            } else {
                return false;
            }

        } else {
            if (produto.getQuantidade() >= 1) {
                hash.put(produto.getId(), 1);
                return true;
            } else {
                return false;
            }
        }
    }

    private static JsonNode parseToJsonNode(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readTree(json);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Stack<Produto> percorrerJsonNode(JsonNode jsonNode, Stack<Produto> pilha) {
        // Verificar se é um objeto
        if (jsonNode.isObject()) {
            // Iterar sobre os campos do objeto
            jsonNode.fields().forEachRemaining(entry -> {
                if (entry.getKey().equals("top")) {
                    JsonNode currentNode = entry.getValue();
                    while (currentNode != null) {
                        Produto produto = new Produto();
                        TipoProduto tipoProduto = new TipoProduto();

                        currentNode.fields().forEachRemaining(field -> {
                            if (field.getKey().equals("data")) {
                                tipoProduto.setId(field.getValue().get("tipoProduto").get("id").asLong());
                                tipoProduto.setDescricao(field.getValue().get("tipoProduto").get("descricao").asText());

                                produto.setId(field.getValue().get("id").asLong());
                                produto.setNome(field.getValue().get("nome").asText());
                                produto.setDescricao(field.getValue().get("descricao").asText());
                                produto.setValor(field.getValue().get("valor").asDouble());
                                produto.setQuantidade(field.getValue().get("quantidade").asInt());
                                produto.setImagem(field.getValue().get("imagem").asText());
                                produto.setTipoProduto(tipoProduto);

                                pilha.push(produto);
                            }
                        });
                        currentNode = currentNode.get("next");
                    }
                }
            });
        }
        return pilha;
    }

    public void saveCheckout(Checkout checkout){
        this.checkoutRepository.save(checkout);
    }

    public List<Checkout> getCheckout() {
        return this.checkoutRepository.findAllFetch();
    }

    public Optional<Checkout> getcheckoutById(Long id) {
        return this.checkoutRepository.findByIdFetch(id);
    }

    private void calculaValorTotal(Checkout checkout, List<Produto> lista){
        checkout.setValorTotal(lista.stream().mapToDouble(Produto::getValor).sum());
    }
}
