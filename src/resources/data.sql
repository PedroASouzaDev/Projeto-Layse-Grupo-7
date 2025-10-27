-- DATA SQL: Inserções de exemplo para popular o banco em memória
-- Ajuste ids/valores conforme necessário. Este arquivo é executado automaticamente pelo Spring Boot.

-- Categorias
INSERT INTO categorias (id, nome) VALUES (1, 'Tecnologia');
INSERT INTO categorias (id, nome) VALUES (2, 'Música');
INSERT INTO categorias (id, nome) VALUES (3, 'Esportes');

-- Usuários (tabela única para Usuario com coluna discriminação 'type')
-- type = 'PAR' -> Participante, type = 'ORG' -> Organizador
INSERT INTO usuario (id, nome, type) VALUES (1, 'João Silva', 'PAR');
INSERT INTO usuario (id, nome, type) VALUES (2, 'Maria Oliveira', 'ORG');
INSERT INTO usuario (id, nome, type) VALUES (3, 'Pedro Santos', 'PAR');
INSERT INTO usuario (id, nome, type) VALUES (4, 'Ana Costa', 'ORG');

-- Eventos
INSERT INTO evento (id, nome, data_inicio, data_fim, valor_ingresso) VALUES (1, 'Conferência Java Aurora', '2025-11-10', '2025-11-12', 150.00);
INSERT INTO evento (id, nome, data_inicio, data_fim, valor_ingresso) VALUES (2, 'Show de Rock Aurora', '2025-12-05', '2025-12-05', 80.00);

-- Associação Evento <-> Participante (evento_participante)
INSERT INTO evento_participante (evento_id, participante_id) VALUES (1, 1);
INSERT INTO evento_participante (evento_id, participante_id) VALUES (1, 3);
INSERT INTO evento_participante (evento_id, participante_id) VALUES (2, 3);

-- Associação Evento <-> Organizador (evento_organizador)
INSERT INTO evento_organizador (evento_id, organizador_id) VALUES (1, 2);
INSERT INTO evento_organizador (evento_id, organizador_id) VALUES (2, 4);

-- Associação Evento <-> Categoria (evento_categoria)
INSERT INTO evento_categoria (evento_id, categoria_id) VALUES (1, 1);
INSERT INTO evento_categoria (evento_id, categoria_id) VALUES (2, 2);

-- Feedbacks
INSERT INTO feedbacks (id, evento_id, participante_id) VALUES (1, 1, 1);
INSERT INTO feedbacks (id, evento_id, participante_id) VALUES (2, 1, 3);

-- Observações:
-- 1) As colunas de data e valor seguem o padrão gerado pelo Spring Boot (ex.: data_inicio, data_fim, valor_ingresso).
-- 2) Se seu projeto usar nomes diferentes de colunas, ajuste os nomes acima para coincidir com o schema gerado.
-- 3) IDs são fixos para facilitar testes; remova ids se preferir AUTO_INCREMENT automático.
