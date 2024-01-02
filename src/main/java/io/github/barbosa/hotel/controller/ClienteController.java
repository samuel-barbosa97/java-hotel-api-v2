package io.github.barbosa.hotel.controller;

import io.github.barbosa.hotel.model.Cliente;
import io.github.barbosa.hotel.service.ClienteService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@AllArgsConstructor
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        logger.info("Recebendo solicitação para cadastrar cliente: {}", cliente);
        Cliente clienteCadastrado = clienteService.cadastrarCliente(cliente);
        logger.info("Cliente cadastrado com sucesso: {}", clienteCadastrado);
        return new ResponseEntity<>(clienteCadastrado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obterTodosClientes() {
        logger.info("Recebendo solicitação para obter todos os clientes.");
        List<Cliente> clientes = clienteService.obterTodosClientes();
        logger.info("Clientes obtidos com sucesso: {}", clientes);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obterClientePorId(@PathVariable Long id) {
        logger.info("Recebendo solicitação para obter cliente por ID: {}", id);
        Cliente cliente = clienteService.obterClientePorId(id);
        if (cliente != null) {
            logger.info("Cliente encontrado: {}", cliente);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            logger.info("Cliente não encontrado com ID: {}", id);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
