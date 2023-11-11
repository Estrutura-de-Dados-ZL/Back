package ed.trab.ecommerce.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ed.trab.ecommerce.models.Cliente;
import ed.trab.ecommerce.services.ClienteService;

@RequestMapping("cliente")
@RestController
public class ClienteController {

    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> getCliente() throws ResponseStatusException {
        try {
            return clienteService.getCliente();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Clientes não encontrados");
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCliente(Cliente cliente) throws ResponseStatusException {
        try {
            clienteService.saveCliente(cliente);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente já existe");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateClienteById(@PathVariable Long id, Cliente cliente) throws RuntimeException, ResponseStatusException {
        try {
            clienteService.updateClienteById(id, cliente);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não pode ser atualizado");
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente getClienteById(@PathVariable Long id) throws ResponseStatusException {
        try {
            return clienteService.getClienteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClienteById(@PathVariable Long id) throws ResponseStatusException {
        try {
            clienteService.deleteClienteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }
    }
}
