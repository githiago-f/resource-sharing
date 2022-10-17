create or replace FUNCTION
  trab_registrar_programa(
    p_nome IN trab_programas.nome%type,
    p_mem_consumida IN trab_programas.mem_consumida_apr%type
  ) RETURN NUMBER
IS
    v_id_created trab_programas.id_programa%type;
BEGIN
  v_id_created := TRAB_S_PROGRAMAS.nextval;
  INSERT INTO trab_programas VALUES (v_id_created, p_nome, p_mem_consumida);
  RETURN v_id_created;
END;
/

CREATE OR REPLACE FUNCTION
  trab_registra_instituicao(
    p_nome_instituicao IN trab_instituicoes.nome%type
  ) RETURN NUMBER
IS
    v_id_instituicao NUMBER;
BEGIN
  v_id_instituicao := TRAB_S_INSTITUICAO.nextval;
  INSERT INTO trab_instituicoes VALUES (v_id_instituicao, p_nome_instituicao);
  RETURN v_id_instituicao;
END;
/

CREATE OR REPLACE FUNCTION
    trab_recursos_por_instituicao(
        p_id_instituicao IN NUMBER
    ) RETURN sys_refcursor
IS
    l_return sys_refcursor;
BEGIN
    open l_return for
        SELECT id_recurso, capacidade FROM trab_recursos
        WHERE id_instituicao = p_id_instituicao;
    return l_return;
END;
/

/*
  Como programas nao sao para uma unica instituicao,
  sera possivel compartilhar entre as instituicoes
  as instancias:

  Instituicao A tem Recurso

  Aluno da Instituicao B precisa de: Programa
  Instituicao B nao tem Recurso livre -> não pode instanciar o programa
  Instituicao A fornece o Recurso para a nova instancia.
*/
CREATE OR REPLACE FUNCTION
  trab_quick_instancia() RETURN sys_refcursor
IS
BEGIN

END;
/
