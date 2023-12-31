package io.github.barbosa.hotel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reservas") // Especificando o nome da tabela
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataInicio;
    private LocalDate dataFim;

    @ManyToOne
    @JoinColumn(name = "cliente_id") // Adicionando uma coluna de chave estrangeira para representar o cliente
    private Cliente cliente;

    private String tipoQuarto;
    private boolean pagamentoConfirmado;
}
