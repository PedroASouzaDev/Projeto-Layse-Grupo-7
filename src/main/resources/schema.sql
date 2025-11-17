-- SCHEMA SQL: Gerado a partir dos modelos Java

CREATE TABLE categorias (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(255) NOT NULL
);

CREATE TABLE usuario (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	type VARCHAR(10) NOT NULL
);

CREATE TABLE evento (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	data_inicio DATE NOT NULL,
	data_fim DATE NOT NULL,
	valor_ingresso DOUBLE
);

CREATE TABLE evento_participante (
	evento_id BIGINT NOT NULL,
	participante_id BIGINT NOT NULL,
	PRIMARY KEY (evento_id, participante_id),
	FOREIGN KEY (evento_id) REFERENCES evento(id),
	FOREIGN KEY (participante_id) REFERENCES usuario(id)
);

CREATE TABLE evento_organizador (
	evento_id BIGINT NOT NULL,
	organizador_id BIGINT NOT NULL,
	PRIMARY KEY (evento_id, organizador_id),
	FOREIGN KEY (evento_id) REFERENCES evento(id),
	FOREIGN KEY (organizador_id) REFERENCES usuario(id)
);

CREATE TABLE evento_categoria (
	evento_id BIGINT NOT NULL,
	categoria_id BIGINT NOT NULL,
	PRIMARY KEY (evento_id, categoria_id),
	FOREIGN KEY (evento_id) REFERENCES evento(id),
	FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);

CREATE TABLE feedbacks (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	evento_id BIGINT NOT NULL,
	participante_id BIGINT NOT NULL,
	nota BIGINT NOT NULL,
	comentario VARCHAR(500) NOT NULL,
	FOREIGN KEY (evento_id) REFERENCES evento(id),
	FOREIGN KEY (participante_id) REFERENCES usuario(id)
);

CREATE TABLE evento_participantes_presentes (
    evento_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    PRIMARY KEY (evento_id, usuario_id),
    FOREIGN KEY (evento_id) REFERENCES evento(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);