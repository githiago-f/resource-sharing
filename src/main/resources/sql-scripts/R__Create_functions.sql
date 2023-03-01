CREATE OR REPLACE FUNCTION
  trab_login(
    p_email IN varchar,
    p_senha IN varchar
  ) RETURN sys_refcursor
IS
  r_user sys_refcursor;
BEGIN
  OPEN r_user FOR
    SELECT id_aluno, nome, email FROM trab_alunos
    WHERE email = p_email AND senha = p_senha;
  RETURN r_user;
END;
/

CREATE OR REPLACE FUNCTION
  trab_adc_recursos_instituicao(
    p_qtd_recursos IN NUMBER,
    p_capacidade_mb IN NUMBER,
    p_instituicao IN NUMBER
  ) RETURN sys_refcursor
IS
  recursos_inst sys_refcursor;

  v_inst_existe NUMBER;
  v_counter NUMBER := 0;
BEGIN
  SELECT COUNT(1) INTO v_inst_existe
  FROM trab_instituicoes WHERE id_instituicao = p_instituicao;

  IF (v_inst_existe = 0) THEN
    raise_application_error(-20000, 'Instituição não existe.');
  END IF;

  LOOP
    INSERT INTO trab_recursos VALUES (DEFAULT, p_capacidade_mb, p_instituicao);
    v_counter := v_counter + 1;
    IF v_counter >= p_qtd_recursos THEN
      EXIT;
    END IF;
  END LOOP;
  COMMIT;

  OPEN recursos_inst FOR
    SELECT ID_RECURSO, CAPACIDADE FROM trab_recursos
    WHERE id_instituicao = p_instituicao;

  RETURN recursos_inst;
END;
/

CREATE OR REPLACE FUNCTION
  trab_alunos_na_fila(p_id_fila IN NUMBER) RETURN sys_refcursor
IS
    l_return sys_refcursor;
BEGIN
  open l_return for
    SELECT
      taf.ID_FILA,
      taf.COMANDO,
      ta.NOME,
      ta.EMAIL,
      taf.ID_USUARIO
    FROM trab_alunos_fila taf
    LEFT JOIN TRAB_ALUNOS ta ON ta.ID_ALUNO = taf.ID_USUARIO
    WHERE id_fila = p_id_fila AND taf.DATA_SAIDA IS NULL;
  return l_return;
END;
/

CREATE OR REPLACE FUNCTION
  trab_instancia_em_qqr_recurso(p_id_programa IN NUMBER) RETURN NUMBER
IS
  v_programa_mem NUMBER;
  v_total_mem NUMBER;
  v_mem_liv NUMBER;
  v_id_instancia NUMBER := -1;

  v_result_id_fila NUMBER := -1;

  CURSOR c_recursos_livres IS
    SELECT * FROM v_recursos_disponiveis;
BEGIN
  SELECT mem_consumida_apr INTO v_programa_mem
  FROM trab_programas WHERE id_programa = p_id_programa;

  FOR r_recurso_livre IN c_recursos_livres LOOP
    IF (r_recurso_livre.mem_em_uso IS NULL) THEN
      v_mem_liv := 0;
    ELSE v_mem_liv := r_recurso_livre.mem_em_uso;
    END IF;

    v_total_mem := v_mem_liv + v_programa_mem;
    IF v_total_mem <= r_recurso_livre.capacidade
    THEN
      v_id_instancia := TRAB_S_INSTANCIA.nextval;

      INSERT INTO trab_instancias (id_instancia, id_recurso, id_programa)
      VALUES (v_id_instancia, r_recurso_livre.recurso, p_id_programa);

      COMMIT;

      EXIT;
    END IF;
  END LOOP;

  SELECT id_fila INTO v_result_id_fila FROM trab_filas
  WHERE id_instancia = v_id_instancia AND rownum = 1;

  RETURN v_result_id_fila;
END;
