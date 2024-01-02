package io.github.barbosa.hotel.repository;

import io.github.barbosa.hotel.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository; // Importando JpaRepository
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
