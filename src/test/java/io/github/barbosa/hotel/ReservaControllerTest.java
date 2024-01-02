package io.github.barbosa.hotel;

import io.github.barbosa.hotel.controller.ReservaController;
import io.github.barbosa.hotel.model.Reserva;
import io.github.barbosa.hotel.service.ReservaService;
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

public class ReservaControllerTest {

    @Mock
    private ReservaService reservaService;

    @InjectMocks
    private ReservaController reservaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarReserva_DeveRetornarReservaCriada() {
        // Arrange
        Reserva reserva = new Reserva();
        when(reservaService.criarReserva(reserva)).thenReturn(reserva);

        // Act
        ResponseEntity<Reserva> responseEntity = reservaController.criarReserva(reserva);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(reserva, responseEntity.getBody());
        verify(reservaService, times(1)).criarReserva(reserva);
    }

    @Test
    void criarReserva_QuandoErroNoServico_DeveRetornarInternalServerError() {
        // Arrange
        Reserva reserva = new Reserva();
        when(reservaService.criarReserva(reserva)).thenThrow(new RuntimeException("Erro no serviço"));

        // Act
        ResponseEntity<Reserva> responseEntity = reservaController.criarReserva(reserva);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
        verify(reservaService, times(1)).criarReserva(reserva);
    }

    @Test
    void obterTodasReservas_DeveRetornarListaDeReservas() {
        // Arrange
        List<Reserva> reservas = Arrays.asList(new Reserva(), new Reserva());
        when(reservaService.obterTodasReservas()).thenReturn(reservas);

        // Act
        ResponseEntity<List<Reserva>> responseEntity = reservaController.obterTodasReservas();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reservas, responseEntity.getBody());
        verify(reservaService, times(1)).obterTodasReservas();
    }

    @Test
    void obterReservaPorId_QuandoReservaEncontrada_DeveRetornarReserva() {
        // Arrange
        Long reservaId = 1L;
        Reserva reserva = new Reserva();
        when(reservaService.obterReservaPorId(reservaId)).thenReturn(reserva);

        // Act
        ResponseEntity<Reserva> responseEntity = reservaController.obterReservaPorId(reservaId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reserva, responseEntity.getBody());
        verify(reservaService, times(1)).obterReservaPorId(reservaId);
    }

    @Test
    void obterReservaPorId_QuandoReservaNaoEncontrada_DeveRetornarNotFound() {
        // Arrange
        Long reservaId = 1L;
        when(reservaService.obterReservaPorId(reservaId)).thenReturn(null);

        // Act
        ResponseEntity<Reserva> responseEntity = reservaController.obterReservaPorId(reservaId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
        verify(reservaService, times(1)).obterReservaPorId(reservaId);
    }

    @Test
    void atualizarReserva_DeveRetornarReservaAtualizada() {
        // Arrange
        Long reservaId = 1L;
        Reserva reserva = new Reserva();
        when(reservaService.atualizarReserva(reservaId, reserva)).thenReturn(reserva);

        // Act
        ResponseEntity<Reserva> responseEntity = reservaController.atualizarReserva(reservaId, reserva);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reserva, responseEntity.getBody());
        verify(reservaService, times(1)).atualizarReserva(reservaId, reserva);
    }

    @Test
    void deletarReserva_QuandoReservaEncontrada_DeveRetornarNoContent() {
        // Arrange
        Long reservaId = 1L;
        when(reservaService.deletarReserva(reservaId)).thenReturn(true);

        // Act
        ResponseEntity<Void> responseEntity = reservaController.deletarReserva(reservaId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(reservaService, times(1)).deletarReserva(reservaId);
    }

    @Test
    void deletarReserva_QuandoReservaNaoEncontrada_DeveRetornarNotFound() {
        // Arrange
        Long reservaId = 1L;
        when(reservaService.deletarReserva(reservaId)).thenReturn(false);

        // Act
        ResponseEntity<Void> responseEntity = reservaController.deletarReserva(reservaId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(reservaService, times(1)).deletarReserva(reservaId);
    }
}
