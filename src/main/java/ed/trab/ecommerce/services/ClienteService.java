package ed.trab.ecommerce.services;

import java.util.List;
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

    public List<Cliente> getCliente() {
        return this.clienteRepository.findAll();
    }

    public Cliente getClienteById(Long id) {
        return this.clienteRepository.findById(id).get();
    }

    public void updateClienteById(Long id, Cliente cliente) throws RuntimeException {
        if (this.clienteRepository.existsById(id)) {
            this.clienteRepository.save(cliente);
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }
    }

    public void deleteClienteById(Long id) {
        this.clienteRepository.deleteById(id);
    }
}
