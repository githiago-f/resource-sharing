CREATE OR REPLACE PROCEDURE
  busca_funcoes_menu(p_out_menu OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN p_out_menu FOR
    SELECT TITULO, LINK, METODO FROM TRAB_MENU;
END;
/

CREATE OR REPLACE PROCEDURE trab_mata_processos_nao_usados IS
  CURSOR c_sem_uso IS
    SELECT id_instancia
    FROM v_instancias_ultimo_uso WHERE minutos_sem_uso > 10;
BEGIN
  FOR r_instancia IN c_sem_uso LOOP
    DBMS_OUTPUT.PUT_LINE('Removendo instancia: ' || r_instancia.id_instancia);

    DELETE FROM TRAB_INSTANCIAS
    WHERE id_instancia = r_instancia.id_instancia;
  END LOOP;
END;
/

CREATE OR REPLACE PROCEDURE
  trab_concluir_comando_na_instancia(
    p_id_instancia IN NUMBER,
    out_proximo_comando OUT NUMBER
  )
IS
    v_ultimo_comando DATE;
BEGIN
  BEGIN
    SELECT MAX(iniciou_em) INTO v_ultimo_comando
    FROM trab_aluno_fila_instancia
    WHERE id_instancia = p_id_instancia
      AND terminou_em IS NULL;

    DBMS_OUTPUT.PUT_LINE('Ultimo comando executado: ' || v_ultimo_comando);

    UPDATE trab_aluno_fila_instancia SET terminou_em = sysdate
    WHERE iniciou_em = v_ultimo_comando;

  EXCEPTION
    WHEN NO_DATA_FOUND THEN NULL;
  END;

  BEGIN
    SELECT
      ID_ALUNOS_FILA INTO out_proximo_comando
    FROM v_alunos_ainda_na_fila
    WHERE id_instancia = p_id_instancia
    FETCH FIRST 1 ROW ONLY;

    DBMS_OUTPUT.PUT_LINE('Output proximo comando: ' || out_proximo_comando);

    INSERT INTO TRAB_ALUNO_FILA_INSTANCIA
    (INICIOU_EM, ID_ALUNO_FILA, ID_INSTANCIA, TERMINOU_EM)
    VALUES (DEFAULT, out_proximo_comando, p_id_instancia, NULL);
    COMMIT;

  EXCEPTION
    WHEN NO_DATA_FOUND THEN NULL;
  END;
END;
/

CREATE OR REPLACE PROCEDURE
  trab_push_comando(
    p_id_programa IN NUMBER,
    p_id_aluno IN NUMBER,
    p_comando IN CLOB
  )
IS
  v_programa NUMBER;
  v_id_fila NUMBER;
BEGIN
  SELECT COUNT(1) INTO v_programa
  FROM trab_programas
  WHERE id_programa = p_id_programa;

  IF (v_programa = 0) THEN
    raise_application_error(-20000, 'Programa não habilitado');
  END IF;

  BEGIN
    SELECT id_fila INTO v_id_fila FROM v_instancias_por_fila
    WHERE id_programa = p_id_programa AND ROWNUM = 1;

  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      v_id_fila := trab_instancia_em_qqr_recurso(p_id_programa);
  END;

  INSERT INTO TRAB_ALUNOS_FILA
  (ID_ALUNOS_FILA, ID_USUARIO, ID_FILA, DATA_ENTRADA, DATA_SAIDA, COMANDO)
  VALUES (DEFAULT, p_id_aluno, v_id_fila, DEFAULT, NULL, p_comando);
  COMMIT;
END;
