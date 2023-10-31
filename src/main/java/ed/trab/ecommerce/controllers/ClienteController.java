package ed.trab.ecommerce.controllers;

import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ed.trab.ecommerce.models.Cliente;
import ed.trab.ecommerce.services.ClienteService;

@RequestMapping("cliente")
public class ClienteController {

    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public Stack<Cliente> getCliente() {
        return clienteService.getCliente();
    }

    @PostMapping
    public void saveCliente(Cliente cliente) {
        clienteService.saveCliente(cliente);
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(Long id) {
        return clienteService.getClienteById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteClienteById(Long id) {
        clienteService.deleteClienteById(id);
    }
}
