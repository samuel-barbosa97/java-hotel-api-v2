package io.github.barbosa.hotel.controller;

import io.github.barbosa.hotel.model.Cliente;
import io.github.barbosa.hotel.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        try {
            logger.info("Recebendo solicitação para cadastrar cliente: {}", cliente);
            Cliente clienteCadastrado = clienteService.cadastrarCliente(cliente);
            logger.info("Cliente cadastrado com sucesso: {}", clienteCadastrado);
            return new ResponseEntity<>(clienteCadastrado, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Erro ao cadastrar cliente", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obterTodosClientes() {
        try {
            logger.info("Recebendo solicitação para obter todos os clientes.");
            List<Cliente> clientes = clienteService.obterTodosClientes();
            logger.info("Clientes obtidos com sucesso: {}", clientes);
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao obter todos os clientes", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obterClientePorId(@PathVariable Long id) {
        try {
            logger.info("Recebendo solicitação para obter cliente por ID: {}", id);
            Cliente cliente = clienteService.obterClientePorId(id);
            if (cliente != null) {
                logger.info("Cliente encontrado: {}", cliente);
                return new ResponseEntity<>(cliente, HttpStatus.OK);
            } else {
                logger.info("Cliente não encontrado com ID: {}", id);
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Erro ao obter cliente por ID", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        try {
            logger.info("Recebendo solicitação para atualizar cliente com ID {}: {}", id, cliente);
            Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente);
            if (clienteAtualizado != null) {
                logger.info("Cliente atualizado com sucesso: {}", clienteAtualizado);
                return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
            } else {
                logger.info("Cliente não encontrado com ID: {}", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Erro ao atualizar cliente", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deletarCliente(@PathVariable Long id) {
        try {
            logger.info("Recebendo solicitação para deletar cliente com ID: {}", id);
            if (clienteService.deletarCliente(id)) {
                logger.info("Cliente deletado com sucesso.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                logger.info("Cliente não encontrado com ID: {}", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Erro ao deletar cliente", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
