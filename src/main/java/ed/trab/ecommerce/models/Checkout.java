package ed.trab.ecommerce.models;

import java.util.List;
import java.util.Queue;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "checkout")
public class Checkout {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    private double valorTotal;

    @ManyToMany
    List<Produto> lista;
}
