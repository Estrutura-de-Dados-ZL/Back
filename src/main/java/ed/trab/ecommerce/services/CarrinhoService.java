package ed.trab.ecommerce.services;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Service;

import ed.trab.ecommerce.models.Carrinho;
import ed.trab.ecommerce.models.Produto;

@Service
public class CarrinhoService {
    
    Carrinho carrinho = new Carrinho();
    HashMap<String, Integer> hash = new HashMap<>();

    public void adicionar(Produto produto){
        carrinho.pilha.push(produto);

    }

    public void remover() throws RuntimeException{
        if(carrinho.pilha.isEmpty()){
            throw new RuntimeException("O carrinho não possui itens.");
        }
        carrinho.pilha.pop();
    }

    public Queue<Produto> checkout() throws RuntimeException{
        if(carrinho.pilha.isEmpty()){
            throw new RuntimeException("O carrinho não possui itens.");
        }

        Queue<Produto> fila = new LinkedList<>();

        while(!carrinho.pilha.isEmpty()){
            if(temNoEstoque(carrinho.pilha.peek())){
                fila.add(carrinho.pilha.pop());
            }else{
                throw new RuntimeException(String.format("%s fora de estoque.", carrinho.pilha.peek().getNome()));
            }
        }
       
        return fila;
    }

    private boolean temNoEstoque(Produto produto){
        if(hash.get(produto.getNome()) >= 1){
            if(hash.get(produto.getNome()) < produto.getQuantidade()){
                hash.put(produto.getNome(), hash.get(produto.getNome()) + 1);
                return true;
            }else{
                return false;
            }
            
        }else{
            if(hash.get(produto.getNome()) < produto.getQuantidade()){
                hash.put(produto.getNome(), 1);
                return true;
            }else{
                return false;
            }
        }

    }

}
