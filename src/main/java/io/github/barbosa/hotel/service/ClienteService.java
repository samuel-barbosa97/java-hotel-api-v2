package io.github.barbosa.hotel.service;

import io.github.barbosa.hotel.model.Cliente;
import io.github.barbosa.hotel.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(Cliente cliente) {
        try {
            logger.info("Cadastrando cliente: {}", cliente);

            Cliente clienteCadastrado = clienteRepository.save(cliente);

            logger.info("Cliente cadastrado com sucesso. Detalhes: ID={}, Nome={}, Email={}",
                    clienteCadastrado.getId(), clienteCadastrado.getNome(), clienteCadastrado.getEmail());

            return clienteCadastrado;
        } catch (Exception e) {
            logger.error("Erro ao cadastrar cliente", e);
            throw new RuntimeException("Erro ao cadastrar cliente", e);
        }
    }

    public List<Cliente> obterTodosClientes() {
        try {
            logger.info("Obtendo todos os clientes.");

            List<Cliente> clientes = clienteRepository.findAll();

            logger.info("Clientes obtidos com sucesso. Total de clientes: {}", clientes.size());

            return clientes;
        } catch (Exception e) {
            logger.error("Erro ao obter todos os clientes", e);
            throw new RuntimeException("Erro ao obter todos os clientes", e);
        }
    }

    public Cliente obterClientePorId(Long id) {
        try {
            logger.info("Obtendo cliente por ID: {}", id);

            Optional<Cliente> optionalCliente = clienteRepository.findById(id);

            Cliente cliente = optionalCliente.orElse(null);

            if (cliente != null) {
                logger.info("Cliente encontrado. Detalhes: ID={}, Nome={}, Email={}",
                        cliente.getId(), cliente.getNome(), cliente.getEmail());
            } else {
                logger.info("Cliente não encontrado com ID: {}", id);
            }

            return cliente;
        } catch (Exception e) {
            logger.error("Erro ao obter cliente por ID", e);
            throw new RuntimeException("Erro ao obter cliente por ID", e);
        }
    }

    public Cliente atualizarCliente(Long id, Cliente cliente) {
        try {
            logger.info("Atualizando cliente com ID {}: {}", id, cliente);

            if (clienteRepository.existsById(id)) {
                cliente.setId(id);
                Cliente clienteAtualizado = clienteRepository.save(cliente);

                logger.info("Cliente atualizado com sucesso. Detalhes: ID={}, Nome={}, Email={}",
                        clienteAtualizado.getId(), clienteAtualizado.getNome(), clienteAtualizado.getEmail());

                return clienteAtualizado;
            } else {
                logger.info("Cliente não encontrado com ID: {}", id);
                return null;
            }
        } catch (Exception e) {
            logger.error("Erro ao atualizar cliente", e);
            throw new RuntimeException("Erro ao atualizar cliente", e);
        }
    }

    public boolean deletarCliente(Long id) {
        try {
            logger.info("Deletando cliente com ID: {}", id);

            if (clienteRepository.existsById(id)) {
                clienteRepository.deleteById(id);

                logger.info("Cliente deletado com sucesso. ID: {}", id);

                return true;
            } else {
                logger.info("Cliente não encontrado com ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            logger.error("Erro ao deletar cliente", e);
            throw new RuntimeException("Erro ao deletar cliente", e);
        }
    }
}
