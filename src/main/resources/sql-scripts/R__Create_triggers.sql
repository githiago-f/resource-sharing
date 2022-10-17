CREATE OR REPLACE TRIGGER
  cria_fila_depois_de_criar_instancia
AFTER INSERT ON trab_instancias
FOR EACH ROW
BEGIN
  INSERT INTO trab_filas VALUES (DEFAULT, :new.id_instancia);
END;
/

-- TODO
/