package io.github.barbosa.hotel.repository;

import io.github.barbosa.hotel.model.Cliente;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository; // Importando JpaRepository


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
