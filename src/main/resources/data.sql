INSERT INTO clientes (nome, email, telefone)
VALUES ('João Silva', 'joao@email.com', '(11) 1234-5678'),
       ('Maria Oliveira', 'maria@email.com', '(22) 9876-5432'),
       ('Carlos Santos', 'carlos@email.com', '(33) 5555-1234');

INSERT INTO reservas (data_inicio, data_fim, cliente_id, tipo_quarto, pagamento_confirmado)
VALUES ('2023-01-01', '2023-01-05', 1, 'Luxo', true),
       ('2023-02-10', '2023-02-15', 2, 'Standard', false),
       ('2023-03-20', '2023-03-25', 3, 'Suite', true);
