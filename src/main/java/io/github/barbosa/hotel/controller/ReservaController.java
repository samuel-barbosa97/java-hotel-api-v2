package io.github.barbosa.hotel.controller;

import io.github.barbosa.hotel.model.Reserva;
import io.github.barbosa.hotel.service.ReservaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private static final Logger logger = LoggerFactory.getLogger(ReservaController.class);

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<Reserva> criarReserva(@RequestBody Reserva reserva) {
        try {
            logger.info("Recebendo solicitação para criar reserva: {}", reserva);

            Reserva reservaCriada = reservaService.criarReserva(reserva);

            logger.info("Reserva criada com sucesso. Detalhes: ID={}, Data de Início={}, Data de Fim={}",
                    reservaCriada.getId(), reservaCriada.getDataInicio(), reservaCriada.getDataFim());

            return new ResponseEntity<>(reservaCriada, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Erro ao criar reserva", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> obterTodasReservas() {
        try {
            logger.info("Recebendo solicitação para obter todas as reservas.");

            List<Reserva> reservas = reservaService.obterTodasReservas();

            logger.info("Reservas obtidas com sucesso. Total de reservas: {}", reservas.size());

            return new ResponseEntity<>(reservas, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao obter todas as reservas", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obterReservaPorId(@PathVariable Long id) {
        try {
            logger.info("Recebendo solicitação para obter reserva por ID: {}", id);

            Reserva reserva = reservaService.obterReservaPorId(id);

            if (reserva != null) {
                logger.info("Reserva encontrada. Detalhes: ID={}, Data de Início={}, Data de Fim={}",
                        reserva.getId(), reserva.getDataInicio(), reserva.getDataFim());
                return new ResponseEntity<>(reserva, HttpStatus.OK);
            } else {
                logger.info("Reserva não encontrada com ID: {}", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Erro ao obter reserva por ID", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> atualizarReserva(@PathVariable Long id, @RequestBody Reserva reserva) {
        try {
            logger.info("Recebendo solicitação para atualizar reserva com ID {}: {}", id, reserva);

            Reserva reservaAtualizada = reservaService.atualizarReserva(id, reserva);

            if (reservaAtualizada != null) {
                logger.info("Reserva atualizada com sucesso. Detalhes: ID={}, Data de Início={}, Data de Fim={}",
                        reservaAtualizada.getId(), reservaAtualizada.getDataInicio(), reservaAtualizada.getDataFim());
                return new ResponseEntity<>(reservaAtualizada, HttpStatus.OK);
            } else {
                logger.info("Reserva não encontrada com ID: {}", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Erro ao atualizar reserva", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReserva(@PathVariable Long id) {
        try {
            logger.info("Recebendo solicitação para deletar reserva com ID: {}", id);

            if (reservaService.deletarReserva(id)) {
                logger.info("Reserva deletada com sucesso. ID: {}", id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                logger.info("Reserva não encontrada com ID: {}", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Erro ao deletar reserva", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
