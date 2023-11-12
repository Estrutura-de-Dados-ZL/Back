package ed.trab.ecommerce.services;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.springframework.stereotype.Service;

import ed.trab.ecommerce.models.Produto;

@Service
public class CarrinhoService {

    HashMap<String, Integer> hash = new HashMap<>();

    public Queue<Produto> checkout(Produto[] pilhaParametro) throws RuntimeException{
        Stack<Produto> pilha = new Stack<>();
        for (Produto p : pilhaParametro) {
            pilha.push(p);
        }
        if(pilha.isEmpty()){
            throw new RuntimeException("O carrinho não possui itens.");
        }

        Queue<Produto> fila = new LinkedList<>();

        while(!pilha.isEmpty()){
            if(temNoEstoque(pilha.peek())){
                fila.add(pilha.pop());
            }else{
                throw new RuntimeException(String.format("%s fora de estoque.", pilha.peek().getNome()));
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
