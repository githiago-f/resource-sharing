INSERT INTO trab_menu VALUES(DEFAULT, 'Lista instituições', '/api/instituicao', 'GET');

INSERT INTO trab_menu VALUES(DEFAULT, 'Lista recursos da instituição', '/api/:idInstituicao/recurso', 'GET');
INSERT INTO trab_menu VALUES(DEFAULT, 'Adiciona recursos à instituição', '/api/:idInstituicao/recurso', 'POST');
