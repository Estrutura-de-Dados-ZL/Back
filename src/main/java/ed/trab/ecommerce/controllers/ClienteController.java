package ed.trab.ecommerce.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ed.trab.ecommerce.models.Cliente;
import ed.trab.ecommerce.services.ClienteService;

@RequestMapping("cliente")
@RestController
@CrossOrigin(origins = { "http://localhost:4200", "https://ed-front.vercel.app" })
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
    public void saveCliente(@RequestBody Map<String, Cliente> cliente) throws ResponseStatusException {
        try {
            Cliente clienteResponse = clienteService.toModel(cliente);
            clienteService.saveCliente(clienteResponse);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente já existe");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateClienteById(@PathVariable Long id, @RequestBody Map<String, Cliente> cliente) throws RuntimeException, ResponseStatusException {
        try {
            Cliente clienteResponse = clienteService.toModel(cliente);
            clienteService.updateClienteById(id, clienteResponse);
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

    @GetMapping("/nome/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente getClienteByNome(@PathVariable String nome) throws ResponseStatusException {
        try {
            return clienteService.getClienteByNome(nome);
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
