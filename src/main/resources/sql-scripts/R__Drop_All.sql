DROP TABLE trab_aluno_fila_instancia CASCADE CONSTRAINTS;
DROP TABLE trab_alunos_fila CASCADE CONSTRAINTS;
DROP TABLE trab_filas CASCADE CONSTRAINTS;
DROP TABLE trab_instancias CASCADE CONSTRAINTS;
DROP TABLE trab_recursos CASCADE CONSTRAINTS;
DROP TABLE trab_programas CASCADE CONSTRAINTS;
DROP TABLE trab_instituicoes CASCADE CONSTRAINTS;
DROP TABLE trab_alunos CASCADE CONSTRAINTS;

DROP SEQUENCE trab_s_instituicao;
DROP SEQUENCE trab_s_recurso;
DROP SEQUENCE trab_s_programas;
DROP SEQUENCE trab_s_instancia;
DROP SEQUENCE trab_s_fila;
DROP SEQUENCE trab_s_aluno;
DROP SEQUENCE trab_s_alunos_fila;

DROP VIEW v_recursos_disponiveis;
DROP VIEW v_instancias_ultimo_uso;

drop procedure TRAB_ADC_RECURSOS_INSTITUICAO;
drop procedure TRAB_CONCLUIR_COMANDO_NA_INSTANCIA;
drop procedure TRAB_INSTANCIA_PROGRAMA_EM_RECURSO_LIVRE;
drop procedure trab_push_comando;

drop function trab_registrar_programa;
drop function trab_registra_instituicao;
drop function trab_recursos_por_instituicao;