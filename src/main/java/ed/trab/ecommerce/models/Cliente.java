package ed.trab.ecommerce.models;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.validation.annotation.Validated;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@Table(name = "clientes")
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    private String cnpj;

    private String tipoPessoa;

    private String telefone;

    private String email;

    private String rua;

    private int numero;

    private String cep;

    private String complemento;
}
