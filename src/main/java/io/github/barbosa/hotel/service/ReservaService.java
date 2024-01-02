package io.github.barbosa.hotel.service;

import io.github.barbosa.hotel.model.Reserva;
import io.github.barbosa.hotel.repository.ReservaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private static final Logger logger = LoggerFactory.getLogger(ReservaService.class);

    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva criarReserva(Reserva reserva) {
        try {
            logger.info("Criando reserva: {}", reserva);

            Reserva reservaCriada = reservaRepository.save(reserva);

            logger.info("Reserva criada com sucesso. Detalhes: ID={}, Data de Início={}, Data de Fim={}",
                    reservaCriada.getId(), reservaCriada.getDataInicio(), reservaCriada.getDataFim());

            return reservaCriada;
        } catch (Exception e) {
            logger.error("Erro ao criar reserva", e);
            throw new RuntimeException("Erro ao criar reserva", e);
        }
    }

    public List<Reserva> obterTodasReservas() {
        try {
            logger.info("Obtendo todas as reservas.");

            List<Reserva> reservas = reservaRepository.findAll();

            logger.info("Reservas obtidas com sucesso. Total de reservas: {}", reservas.size());

            return reservas;
        } catch (Exception e) {
            logger.error("Erro ao obter todas as reservas", e);
            throw new RuntimeException("Erro ao obter todas as reservas", e);
        }
    }

    public Reserva obterReservaPorId(Long id) {
        try {
            logger.info("Obtendo reserva por ID: {}", id);

            Optional<Reserva> optionalReserva = reservaRepository.findById(id);

            Reserva reserva = optionalReserva.orElse(null);

            if (reserva != null) {
                logger.info("Reserva encontrada. Detalhes: ID={}, Data de Início={}, Data de Fim={}",
                        reserva.getId(), reserva.getDataInicio(), reserva.getDataFim());
            } else {
                logger.info("Reserva não encontrada com ID: {}", id);
            }

            return reserva;
        } catch (Exception e) {
            logger.error("Erro ao obter reserva por ID", e);
            throw new RuntimeException("Erro ao obter reserva por ID", e);
        }
    }

    public Reserva atualizarReserva(Long id, Reserva reserva) {
        try {
            logger.info("Atualizando reserva com ID {}: {}", id, reserva);

            if (reservaRepository.existsById(id)) {
                reserva.setId(id);
                Reserva reservaAtualizada = reservaRepository.save(reserva);

                logger.info("Reserva atualizada com sucesso. Detalhes: ID={}, Data de Início={}, Data de Fim={}",
                        reservaAtualizada.getId(), reservaAtualizada.getDataInicio(), reservaAtualizada.getDataFim());

                return reservaAtualizada;
            } else {
                logger.info("Reserva não encontrada com ID: {}", id);
                return null;
            }
        } catch (Exception e) {
            logger.error("Erro ao atualizar reserva", e);
            throw new RuntimeException("Erro ao atualizar reserva", e);
        }
    }

    public boolean deletarReserva(Long id) {
        try {
            logger.info("Deletando reserva com ID: {}", id);

            if (reservaRepository.existsById(id)) {
                reservaRepository.deleteById(id);

                logger.info("Reserva deletada com sucesso. ID: {}", id);

                return true;
            } else {
                logger.info("Reserva não encontrada com ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            logger.error("Erro ao deletar reserva", e);
            throw new RuntimeException("Erro ao deletar reserva", e);
        }
    }
}
