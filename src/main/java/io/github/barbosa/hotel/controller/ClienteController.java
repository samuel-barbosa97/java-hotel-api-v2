package io.github.barbosa.hotel.controller;

import io.github.barbosa.hotel.model.Cliente;
import io.github.barbosa.hotel.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@AllArgsConstructor
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        Cliente clienteCadastrado = clienteService.cadastrarCliente(cliente);
        return new ResponseEntity<>(clienteCadastrado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obterTodosClientes(){
        List<Cliente> clientes = clienteService.obterTodosClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
}
