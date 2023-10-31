package ed.trab.ecommerce.services;

import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ed.trab.ecommerce.models.Cliente;
import ed.trab.ecommerce.repositories.ClienteRepository;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void saveCliente(Cliente cliente) {
        this.clienteRepository.save(cliente);
    }

    public Stack<Cliente> getCliente() {
        return (Stack<Cliente>) this.clienteRepository.findAll();
    }

    public Cliente getClienteById(Long id) {
        return this.clienteRepository.findById(id).get();
    }

    public void deleteClienteById(Long id) {
        
        this.clienteRepository.deleteById(id);
    }
}
