INSERT INTO trab_menu VALUES(DEFAULT, 'Lista instituições', '/api/instituicoes', 'GET');

INSERT INTO trab_menu VALUES(DEFAULT, 'Lista recursos da instituição', '/api/:idInstituicao/recurso', 'GET');
INSERT INTO trab_menu VALUES(DEFAULT, 'Adiciona recursos à instituição', '/api/:idInstituicao/recurso', 'POST');

INSERT INTO trab_menu VALUES(DEFAULT, 'Listar programas disponiveis', '/api/programas', 'GET');
INSERT INTO trab_menu VALUES(DEFAULT, 'Criar programa', '/api/programas', 'POST');
INSERT INTO trab_menu VALUES(DEFAULT, 'Deletar o programa', '/api/programas', 'DELETE');
INSERT INTO trab_menu VALUES(DEFAULT, 'Usar um programa determinado', '/api/programas/:nomeDoPrograma', 'POST');

INSERT INTO trab_menu VALUES(DEFAULT, 'Pegar instancia de um programa', '/api/instancia/{idPrograma}', 'GET');
INSERT INTO trab_menu VALUES(DEFAULT, 'Concluir execucao', '/api/instancia/{idPrograma}', 'POST');

INSERT INTO trab_menu VALUES(DEFAULT, 'Login de usuario', '/api/login', 'POST');
INSERT INTO trab_menu VALUES(DEFAULT, 'Cadastro de usuarios', '/api/alunos', 'POST');
