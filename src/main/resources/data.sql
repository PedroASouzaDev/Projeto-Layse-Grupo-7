-- DATA SQL: Inserções de exemplo para popular o banco em memória
-- Ajuste ids/valores conforme necessário. Este arquivo é executado automaticamente pelo Spring Boot.

INSERT INTO categorias (nome) VALUES ('Tecnologia');
INSERT INTO categorias (nome) VALUES ('Música');
INSERT INTO categorias (nome) VALUES ('Esportes');
INSERT INTO categorias (nome) VALUES ('Workshop');
INSERT INTO categorias (nome) VALUES ('Literatura');

INSERT INTO usuario (nome, type) VALUES ('João Silva', 'PAR');
INSERT INTO usuario (nome, type) VALUES ('Maria Oliveira', 'ORG');
INSERT INTO usuario (nome, type) VALUES ('Pedro Santos', 'PAR');
INSERT INTO usuario (nome, type) VALUES ('Ana Costa', 'ORG');

INSERT INTO evento (nome, data_inicio, data_fim, valor_ingresso) VALUES ('Conferência Java Aurora', '2025-11-10', '2025-11-12', 150.00);
INSERT INTO evento (nome, data_inicio, data_fim, valor_ingresso) VALUES ('Show de Rock Aurora', '2025-12-05', '2025-12-05', 80.00);
INSERT INTO evento (nome, data_inicio, data_fim, valor_ingresso) VALUES ('Festival de Música Eletrônica', '2025-01-15', '2025-01-16', 120.00);
INSERT INTO evento (nome, data_inicio, data_fim, valor_ingresso) VALUES ('Campeonato de Futebol 2025', '2025-02-20', '2025-02-22', 50.00);
INSERT INTO evento (nome, data_inicio, data_fim, valor_ingresso) VALUES ('Workshop de Python Avançado', '2025-03-10', '2025-03-10', 200.00);
INSERT INTO evento (nome, data_inicio, data_fim, valor_ingresso) VALUES ('Maratona de Corrida Aurora', '2025-04-05', '2025-04-05', 30.00);
INSERT INTO evento (nome, data_inicio, data_fim, valor_ingresso) VALUES ('Exibição de Filmes Independentes', '2025-05-12', '2025-05-15', 25.00);

-- IDs devem ser ajustados conforme inserção automática. Exemplo abaixo assume ordem de inserção:
-- Evento 1: João Silva (id=1), Maria Oliveira (id=2), Pedro Santos (id=3), Ana Costa (id=4)
-- Evento 1: Conferência Java Aurora (id=1), Evento 2: Show de Rock Aurora (id=2)
INSERT INTO evento_participante (evento_id, participante_id) VALUES (1, 1);
INSERT INTO evento_participante (evento_id, participante_id) VALUES (1, 3);
INSERT INTO evento_participante (evento_id, participante_id) VALUES (2, 3);
INSERT INTO evento_participante (evento_id, participante_id) VALUES (1, 2);
INSERT INTO evento_participante (evento_id, participante_id) VALUES (1, 4);
INSERT INTO evento_participante (evento_id, participante_id) VALUES (2, 1);
INSERT INTO evento_participante (evento_id, participante_id) VALUES (2, 2);
INSERT INTO evento_participante (evento_id, participante_id) VALUES (2, 4);
INSERT INTO evento_participante (evento_id, participante_id) VALUES (3, 1);
INSERT INTO evento_participante (evento_id, participante_id) VALUES (3, 2);
INSERT INTO evento_participante (evento_id, participante_id) VALUES (3, 3);
INSERT INTO evento_participante (evento_id, participante_id) VALUES (4, 1);
INSERT INTO evento_participante (evento_id, participante_id) VALUES (4, 4);

INSERT INTO evento_organizador (evento_id, organizador_id) VALUES (1, 2);
INSERT INTO evento_organizador (evento_id, organizador_id) VALUES (2, 4);
INSERT INTO evento_organizador (evento_id, organizador_id) VALUES (3, 1);
INSERT INTO evento_organizador (evento_id, organizador_id) VALUES (4, 3);
INSERT INTO evento_organizador (evento_id, organizador_id) VALUES (5, 1);
INSERT INTO evento_organizador (evento_id, organizador_id) VALUES (6, 2);
INSERT INTO evento_organizador (evento_id, organizador_id) VALUES (7, 4);

INSERT INTO evento_categoria (evento_id, categoria_id) VALUES (1, 1);
INSERT INTO evento_categoria (evento_id, categoria_id) VALUES (1, 2);
INSERT INTO evento_categoria (evento_id, categoria_id) VALUES (1, 3);
INSERT INTO evento_categoria (evento_id, categoria_id) VALUES (1, 4);
INSERT INTO evento_categoria (evento_id, categoria_id) VALUES (1, 5);
INSERT INTO evento_categoria (evento_id, categoria_id) VALUES (2, 2);
INSERT INTO evento_categoria (evento_id, categoria_id) VALUES (3, 2);
INSERT INTO evento_categoria (evento_id, categoria_id) VALUES (3, 4);
INSERT INTO evento_categoria (evento_id, categoria_id) VALUES (4, 1);
INSERT INTO evento_categoria (evento_id, categoria_id) VALUES (5, 1);
INSERT INTO evento_categoria (evento_id, categoria_id) VALUES (5, 3);
INSERT INTO evento_categoria (evento_id, categoria_id) VALUES (6, 1);
INSERT INTO evento_categoria (evento_id, categoria_id) VALUES (7, 2);

INSERT INTO feedbacks (evento_id, participante_id, nota, comentario) VALUES (1, 1, 1, 'uma bosta!');
INSERT INTO feedbacks (evento_id, participante_id, nota, comentario) VALUES (1, 3, 5, 'Bom, mas poderia ser melhor organizado.');
INSERT INTO feedbacks (evento_id, participante_id, nota, comentario) VALUES (2, 1, 4, 'Ótimo show, muito animado!');
INSERT INTO feedbacks (evento_id, participante_id, nota, comentario) VALUES (2, 2, 5, 'Incrível, a banda foi sensacional!');
INSERT INTO feedbacks (evento_id, participante_id, nota, comentario) VALUES (3, 3, 3, 'Música boa, mas o local poderia ser melhor.');
INSERT INTO feedbacks (evento_id, participante_id, nota, comentario) VALUES (4, 4, 2, 'O campeonato foi interessante, mas faltou organização.');
INSERT INTO feedbacks (evento_id, participante_id, nota, comentario) VALUES (5, 1, 5, 'Workshop excelente, aprendi muito!');
INSERT INTO feedbacks (evento_id, participante_id, nota, comentario) VALUES (6, 2, 4, 'Maratona divertida, mas o percurso poderia ser mais desafiador.');
INSERT INTO feedbacks (evento_id, participante_id, nota, comentario) VALUES (7, 3, 5, 'Filmes incríveis, uma experiência única!');

-- Exemplo de participantes presentes no evento 1 (Conferência Java Aurora)
INSERT INTO evento_participantes_presentes (evento_id, usuario_id) VALUES (1, 1);
INSERT INTO evento_participantes_presentes (evento_id, usuario_id) VALUES (1, 2);
INSERT INTO evento_participantes_presentes (evento_id, usuario_id) VALUES (1, 3);
INSERT INTO evento_participantes_presentes (evento_id, usuario_id) VALUES (1, 4);
-- Exemplo de participantes presentes no evento 2 (Show de Rock Aurora)
INSERT INTO evento_participantes_presentes (evento_id, usuario_id) VALUES (2, 1);
INSERT INTO evento_participantes_presentes (evento_id, usuario_id) VALUES (2, 2);
INSERT INTO evento_participantes_presentes (evento_id, usuario_id) VALUES (2, 3);
INSERT INTO evento_participantes_presentes (evento_id, usuario_id) VALUES (2, 4);
-- Exemplo de participantes presentes no evento 3 (Festival de Música Eletrônica)
INSERT INTO evento_participantes_presentes (evento_id, usuario_id) VALUES (3, 1);
INSERT INTO evento_participantes_presentes (evento_id, usuario_id) VALUES (3, 2);
INSERT INTO evento_participantes_presentes (evento_id, usuario_id) VALUES (3, 3);
-- Exemplo de participantes presentes no evento 4 (Campeonato de Futebol 2025)
INSERT INTO evento_participantes_presentes (evento_id, usuario_id) VALUES (4, 1);
INSERT INTO evento_participantes_presentes (evento_id, usuario_id) VALUES (4, 4);
-- Exemplo de participantes presentes no evento 5 (Workshop de Python Avançado)
INSERT INTO evento_participantes_presentes (evento_id, usuario_id) VALUES (5, 1);
-- Exemplo de participantes presentes no evento 6 (Maratona de Corrida Aurora)
INSERT INTO evento_participantes_presentes (evento_id, usuario_id) VALUES (6, 2);
-- Exemplo de participantes presentes no evento 7 (Exibição de Filmes Independentes)
INSERT INTO evento_participantes_presentes (evento_id, usuario_id) VALUES (7, 3);
-- Adicionando todos os inscritos como presentes nos eventos 5, 6 e 7
INSERT INTO evento_participantes_presentes (evento_id, usuario_id) VALUES (5, 3);
INSERT INTO evento_participantes_presentes (evento_id, usuario_id) VALUES (6, 1);
INSERT INTO evento_participantes_presentes (evento_id, usuario_id) VALUES (7, 1);

