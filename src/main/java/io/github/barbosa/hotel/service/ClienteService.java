package io.github.barbosa.hotel.service;

import io.github.barbosa.hotel.model.Cliente;
import io.github.barbosa.hotel.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
