DROP TRIGGER cria_fila_depois_de_criar_instancia;
DROP TRIGGER remove_aluno_apos_executar_comando;

DROP TABLE trab_aluno_fila_instancia CASCADE CONSTRAINTS;
DROP TABLE trab_alunos_fila CASCADE CONSTRAINTS;
DROP TABLE trab_filas CASCADE CONSTRAINTS;
DROP TABLE trab_instancias CASCADE CONSTRAINTS;
DROP TABLE trab_recursos CASCADE CONSTRAINTS;
DROP TABLE trab_programas CASCADE CONSTRAINTS;
DROP TABLE trab_instituicoes CASCADE CONSTRAINTS;
DROP TABLE trab_alunos CASCADE CONSTRAINTS;
DROP TABLE TRAB_MENU;

DROP SEQUENCE trab_s_instituicao;
DROP SEQUENCE trab_s_recurso;
DROP SEQUENCE trab_s_programas;
DROP SEQUENCE trab_s_instancia;
DROP SEQUENCE trab_s_fila;
DROP SEQUENCE trab_s_aluno;
DROP SEQUENCE trab_s_alunos_fila;
DROP SEQUENCE trab_s_menu;

DROP PROCEDURE trab_mata_processos_nao_usados;
DROP PROCEDURE trab_push_comando;
DROP PROCEDURE trab_concluir_comando_na_instancia;

DROP FUNCTION trab_login;
DROP FUNCTION trab_adc_recursos_instituicao;
DROP FUNCTION trab_alunos_na_fila;
DROP FUNCTION trab_instancia_em_qqr_recurso;

DROP VIEW v_instancias_ultimo_uso;
DROP VIEW v_recursos_disponiveis;
DROP VIEW v_instancias_por_fila;
DROP VIEW v_alunos_ainda_na_fila;
