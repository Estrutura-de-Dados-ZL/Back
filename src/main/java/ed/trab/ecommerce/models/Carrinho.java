package ed.trab.ecommerce.models;

import java.util.Stack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carrinho {
    
    public Stack<Produto> pilha = new Stack<>();

}
