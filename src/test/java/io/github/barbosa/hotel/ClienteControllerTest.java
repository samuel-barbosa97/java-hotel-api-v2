package io.github.barbosa.hotel;

import io.github.barbosa.hotel.controller.ClienteController;
import io.github.barbosa.hotel.model.Cliente;
import io.github.barbosa.hotel.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cadastrarCliente_DeveRetornarClienteCriado() {
        // Arrange
        Cliente cliente = new Cliente();
        when(clienteService.cadastrarCliente(cliente)).thenReturn(cliente);

        // Act
        ResponseEntity<Cliente> responseEntity = clienteController.cadastrarCliente(cliente);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(cliente, responseEntity.getBody());
        verify(clienteService, times(1)).cadastrarCliente(cliente);
    }

    @Test
    void cadastrarCliente_QuandoErroNoServico_DeveRetornarInternalServerError() {
        // Arrange
        Cliente cliente = new Cliente();
        when(clienteService.cadastrarCliente(cliente)).thenThrow(new RuntimeException("Erro no serviço"));

        // Act
        ResponseEntity<Cliente> responseEntity = clienteController.cadastrarCliente(cliente);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
        verify(clienteService, times(1)).cadastrarCliente(cliente);
    }

    @Test
    void obterTodosClientes_DeveRetornarListaDeClientes() {
        // Arrange
        List<Cliente> clientes = Arrays.asList(new Cliente(), new Cliente());
        when(clienteService.obterTodosClientes()).thenReturn(clientes);

        // Act
        ResponseEntity<List<Cliente>> responseEntity = clienteController.obterTodosClientes();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(clientes, responseEntity.getBody());
        verify(clienteService, times(1)).obterTodosClientes();
    }

    @Test
    void obterClientePorId_QuandoClienteEncontrado_DeveRetornarCliente() {
        // Arrange
        Long clienteId = 1L;
        Cliente cliente = new Cliente();
        when(clienteService.obterClientePorId(clienteId)).thenReturn(cliente);

        // Act
        ResponseEntity<Cliente> responseEntity = clienteController.obterClientePorId(clienteId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(cliente, responseEntity.getBody());
        verify(clienteService, times(1)).obterClientePorId(clienteId);
    }

    @Test
    void obterClientePorId_QuandoClienteNaoEncontrado_DeveRetornarNotFound() {
        // Arrange
        Long clienteId = 1L;
        when(clienteService.obterClientePorId(clienteId)).thenReturn(null);

        // Act
        ResponseEntity<Cliente> responseEntity = clienteController.obterClientePorId(clienteId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
        verify(clienteService, times(1)).obterClientePorId(clienteId);
    }

    @Test
    void atualizarCliente_DeveRetornarClienteAtualizado() {
        // Arrange
        Long clienteId = 1L;
        Cliente cliente = new Cliente();
        when(clienteService.atualizarCliente(clienteId, cliente)).thenReturn(cliente);

        // Act
        ResponseEntity<Cliente> responseEntity = clienteController.atualizarCliente(clienteId, cliente);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(cliente, responseEntity.getBody());
        verify(clienteService, times(1)).atualizarCliente(clienteId, cliente);
    }

    @Test
    void deletarCliente_QuandoClienteEncontrado_DeveRetornarNoContent() {
        // Arrange
        Long clienteId = 1L;
        when(clienteService.deletarCliente(clienteId)).thenReturn(true);

        // Act
        ResponseEntity<Cliente> responseEntity = clienteController.deletarCliente(clienteId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(clienteService, times(1)).deletarCliente(clienteId);
    }

    @Test
    void deletarCliente_QuandoClienteNaoEncontrado_DeveRetornarNotFound() {
        // Arrange
        Long clienteId = 1L;
        when(clienteService.deletarCliente(clienteId)).thenReturn(false);

        // Act
        ResponseEntity<Cliente> responseEntity = clienteController.deletarCliente(clienteId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(clienteService, times(1)).deletarCliente(clienteId);
    }
}
