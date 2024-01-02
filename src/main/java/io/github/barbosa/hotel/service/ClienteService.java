package io.github.barbosa.hotel.service;

import io.github.barbosa.hotel.model.Cliente;
import io.github.barbosa.hotel.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> obterTodosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obterClientePorId(Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        return optionalCliente.orElse(null);
    }

    public Cliente atualizarCliente(Long id, Cliente cliente) {
        if (clienteRepository.existsById(id)) {
            cliente.setId(id);
            return clienteRepository.save(cliente);
        } else {
            return null;
        }
    }

    public boolean deletarCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
