INSERT INTO Especialidade(nomeEspecialidade) VALUES
('Odontologia'),
('Pediatria'),
('Oftalmologia'),
('Dermatologia'),
('Ortopedia');

INSERT INTO Paciente VALUES
('111.222.333-44', 'Roberto'),
('222.333.444-55', 'Ivone'),
('333.444.555-66', 'José'),
('444.555.666-77', 'Igor');

INSERT INTO Profissional(nomeProfissional) VALUES
('Vanessa'),
('Edgar'),
('Maria'),
('Vinicius');

INSERT INTO Profissional_Especialidade(profissionais_idProfissional, especialidades_idEspecialidade) VALUES
(1, 1),
(1, 3),
(2, 5),
(3, 2),
(3, 4),
(3, 5);

INSERT INTO Operacao(nomeOperacao, descricaoOperacao, duracaoMinutosOperacao, especialidade_idEspecialidade) VALUES
('Exame de vista', 'Exame para identificação de possíveis problemas ou doenças nos olhos do paciente', 30, 3),
('Limpeza bucal', 'Limpeza na boca do paciente', 30, 1),
('Consulta dermatológica', 'Consulta médica para análise da pele do paciente', 20, 4);
