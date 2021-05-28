INSERT INTO eleicao.eleicao (id, nome, "data") VALUES
(100001, 'Uma eleição 1', '12/12/2020'),
(100002, 'Uma eleição 2', '12/24/2020'),
(100003, 'Uma eleição 3', '10/07/2020');

INSERT INTO eleicao.pessoa (id, nome, cpf) VALUES
(100001, 'Pablo Ricardo', '123.456.789-00'),
(100002, 'Rebeca Andrade', '111.222.333-44'),
(100003, 'Miriã Bento', '222.444.555-67');

INSERT INTO eleicao.eleitor (id, id_pessoa, id_eleicao) VALUES
(100001, 100002, 100002),
(100002, 100001, 100002);

INSERT INTO eleicao.candidato (id, id_pessoa, id_eleicao, codigo) VALUES
(100001, 100001, 100002, '5555'),
(100002, 100002, 100002, '7777');

INSERT INTO eleicao.voto (id, id_eleicao, id_eleitor, id_candidato, data_hora) VALUES
(100001, 100002, 100001, 100002, '12/12/2020 10:10'),
(100002, 100002, 100002, 100002, '12/12/2020 10:20');



