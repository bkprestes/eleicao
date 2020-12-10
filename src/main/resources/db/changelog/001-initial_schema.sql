
CREATE SCHEMA eleicao;

CREATE TABLE eleicao.pessoa (
	id serial NOT NULL,
	nome text NOT NULL,
	cpf text NOT NULL,
	CONSTRAINT pk_pessoa PRIMARY KEY (id),
	CONSTRAINT uk_pessoa_cpf UNIQUE (cpf)

);

CREATE TABLE eleicao.eleicao (
	id serial NOT NULL,
	nome text NOT NULL,
	data date NOT NULL,
	CONSTRAINT pk_eleicao PRIMARY KEY (id),
	CONSTRAINT uk_eleicao_nome UNIQUE (nome)

);

CREATE TABLE eleicao.eleitor (
	id serial NOT NULL,
	id_pessoa integer NOT NULL,
	id_eleicao integer NOT NULL,
	CONSTRAINT pk_eleitor PRIMARY KEY (id),
	CONSTRAINT uk_eleitor_eleicao UNIQUE (id_eleicao,id_pessoa)

);

CREATE TABLE eleicao.candidato (
	id serial NOT NULL,
	id_pessoa integer NOT NULL,
	id_eleicao integer NOT NULL,
	codigo varchar(5) NOT NULL,
	CONSTRAINT pk_candidato PRIMARY KEY (id),
	CONSTRAINT uk_candidato_codigo UNIQUE (codigo,id_eleicao),
	CONSTRAINT uk_candidato_id_pessoa UNIQUE (id_pessoa)

);

CREATE TABLE eleicao.voto (
	id serial NOT NULL,
	id_eleicao integer NOT NULL,
	id_eleitor integer NOT NULL,
	id_candidato integer NOT NULL,
	data_hora timestamp NOT NULL,
	CONSTRAINT pk_voto PRIMARY KEY (id),
	CONSTRAINT uk_voto_eleicao UNIQUE (id_eleicao,id_eleitor)

);

ALTER TABLE eleicao.eleitor ADD CONSTRAINT fk_eleitor_id_pessoa FOREIGN KEY (id_pessoa)
REFERENCES eleicao.pessoa (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE eleicao.eleitor ADD CONSTRAINT fk_eleitor_id_eleicao FOREIGN KEY (id_eleicao)
REFERENCES eleicao.eleicao (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE eleicao.candidato ADD CONSTRAINT fk_candidato_id_pessoa FOREIGN KEY (id_pessoa)
REFERENCES eleicao.pessoa (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE eleicao.candidato ADD CONSTRAINT fk_candidato_id_eleicao FOREIGN KEY (id_eleicao)
REFERENCES eleicao.eleicao (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE eleicao.voto ADD CONSTRAINT fk_voto_id_eleicao FOREIGN KEY (id_eleicao)
REFERENCES eleicao.eleicao (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE eleicao.voto ADD CONSTRAINT fk_voto_id_eleitor FOREIGN KEY (id_eleitor)
REFERENCES eleicao.eleitor (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE eleicao.voto ADD CONSTRAINT fk_voto_id_candidato FOREIGN KEY (id_candidato)
REFERENCES eleicao.candidato (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;


