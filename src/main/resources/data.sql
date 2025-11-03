-- DATA SQL: Inserções de exemplo para popular o banco em memória
-- Ajuste ids/valores conforme necessário. Este arquivo é executado automaticamente pelo Spring Boot.

INSERT INTO categorias (nome) VALUES ('Tecnologia');
INSERT INTO categorias (nome) VALUES ('Música');
INSERT INTO categorias (nome) VALUES ('Esportes');

INSERT INTO usuario (nome, type) VALUES ('João Silva', 'PAR');
INSERT INTO usuario (nome, type) VALUES ('Maria Oliveira', 'ORG');
INSERT INTO usuario (nome, type) VALUES ('Pedro Santos', 'PAR');
INSERT INTO usuario (nome, type) VALUES ('Ana Costa', 'ORG');

INSERT INTO evento (nome, data_inicio, data_fim, valor_ingresso) VALUES ('Conferência Java Aurora', '2025-11-10', '2025-11-12', 150.00);
INSERT INTO evento (nome, data_inicio, data_fim, valor_ingresso) VALUES ('Show de Rock Aurora', '2025-12-05', '2025-12-05', 80.00);

-- IDs devem ser ajustados conforme inserção automática. Exemplo abaixo assume ordem de inserção:
-- Evento 1: João Silva (id=1), Maria Oliveira (id=2), Pedro Santos (id=3), Ana Costa (id=4)
-- Evento 1: Conferência Java Aurora (id=1), Evento 2: Show de Rock Aurora (id=2)
INSERT INTO evento_participante (evento_id, participante_id) VALUES (1, 1);
INSERT INTO evento_participante (evento_id, participante_id) VALUES (1, 3);
INSERT INTO evento_participante (evento_id, participante_id) VALUES (2, 3);

INSERT INTO evento_organizador (evento_id, organizador_id) VALUES (1, 2);
INSERT INTO evento_organizador (evento_id, organizador_id) VALUES (2, 4);

INSERT INTO evento_categoria (evento_id, categoria_id) VALUES (1, 1);
INSERT INTO evento_categoria (evento_id, categoria_id) VALUES (2, 2);

INSERT INTO feedbacks (evento_id, participante_id) VALUES (1, 1);
INSERT INTO feedbacks (evento_id, participante_id) VALUES (1, 3);

-- 3) IDs agora são automáticos para evitar conflitos com AUTO_INCREMENT.
