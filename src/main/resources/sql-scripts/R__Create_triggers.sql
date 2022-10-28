CREATE OR REPLACE TRIGGER
  cria_fila_depois_de_criar_instancia
BEFORE INSERT ON trab_instancias
FOR EACH ROW BEGIN
  INSERT INTO trab_filas VALUES (DEFAULT, :new.id_instancia);
END;
/

CREATE OR REPLACE TRIGGER
  remove_aluno_apos_executar_comando
BEFORE UPDATE OF terminou_em ON trab_aluno_fila_instancia
FOR EACH ROW
BEGIN
  UPDATE TRAB_ALUNOS_FILA
    SET DATA_SAIDA = sysdate
  WHERE ID_ALUNOS_FILA = :new.ID_ALUNO_FILA;
END;
