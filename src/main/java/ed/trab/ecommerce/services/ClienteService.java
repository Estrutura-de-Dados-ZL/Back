package ed.trab.ecommerce.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ed.trab.ecommerce.models.Cliente;
import ed.trab.ecommerce.repositories.ClienteRepository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente toModel(Map<String, Cliente> cliente) {
        return cliente.get("cliente");
    }

    public void saveCliente(@RequestBody Cliente cliente) {
        this.clienteRepository.save(cliente);
    }

    public List<Cliente> getCliente() {
        return this.clienteRepository.findAll();
    }

    public Cliente getClienteById(Long id) {
        return this.clienteRepository.findById(id).get();
    }

    public void updateClienteById(Long id, @RequestBody Cliente cliente) throws RuntimeException {
        if (this.clienteRepository.existsById(id)) {
            this.clienteRepository.save(cliente);
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }
    }

    public List<Cliente> getClienteByNome(String nome) {
        return this.clienteRepository.findByNome(nome);
    }

    public void deleteClienteById(Long id) {
        this.clienteRepository.deleteById(id);
    }
}
