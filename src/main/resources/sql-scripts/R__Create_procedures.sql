CREATE OR REPLACE PROCEDURE
  trab_adc_recursos_instituicao(
    p_qtd_recursos IN NUMBER,
    p_capacidade_mb IN NUMBER,
    p_instituicao IN NUMBER
  )
IS
  v_inst_existe NUMBER;
  v_counter NUMBER;
BEGIN
  SELECT COUNT(*) INTO v_inst_existe
  FROM trab_instituicoes WHERE id_instituicao = p_instituicao;

  IF v_inst_existe <= 0 THEN
    raise_application_error(-20000, 'Instituição não existe.');
  END IF;

  v_counter := 0;
  LOOP
    INSERT INTO trab_recursos VALUES (DEFAULT, p_capacidade_mb, p_instituicao);
    v_counter := v_counter + 1;
    IF v_counter >= p_qtd_recursos THEN
      EXIT;
    END IF;
  END LOOP;
END;
/

CREATE OR REPLACE PROCEDURE
  trab_instancia_programa_em_recurso_livre(
    p_instituicao IN trab_instituicoes.nome%type,
    p_id_programa IN NUMBER
  )
IS
  v_recurso NUMBER;
  v_programa_mem NUMBER;
  v_total_mem NUMBER;
  v_mem_liv NUMBER;
  v_id_instituicao NUMBER;

  CURSOR c_recursos_livres(c_p_id_inst NUMBER) IS
    SELECT * FROM v_recursos_disponiveis
    WHERE instituicao = c_p_id_inst;
BEGIN
  SELECT mem_consumida_apr INTO v_programa_mem
  FROM trab_programas WHERE id_programa = p_id_programa;

  SELECT id_instituicao INTO v_id_instituicao
  FROM trab_instituicoes WHERE nome = p_instituicao;

  FOR r_recurso_livre IN c_recursos_livres(v_id_instituicao) LOOP
    IF r_recurso_livre.mem_em_uso IS NULL THEN
      v_mem_liv := 0;
    ELSE v_mem_liv := r_recurso_livre.mem_em_uso;
    END IF;

    v_total_mem := v_mem_liv + v_programa_mem;
    IF v_total_mem <= r_recurso_livre.capacidade THEN
      DBMS_OUTPUT.PUT_LINE('Instanciado no recurso ' || r_recurso_livre.recurso);
      INSERT INTO trab_instancias (id_instancia, id_recurso, id_programa)
      VALUES (DEFAULT, r_recurso_livre.recurso, p_id_programa);
      EXIT;
    END IF;
  END LOOP;
END;
/

CREATE OR REPLACE PROCEDURE
  trab_concluir_comando_na_instancia(p_id_instancia IN NUMBER)
IS
    ultimo_comando NUMBER;
BEGIN
  SELECT MAX(iniciou_em) INTO ultimo_comando
  FROM trab_aluno_fila_instancia
  WHERE id_instancia = p_id_instancia
    AND terminou_em IS NULL;



  UPDATE trab_aluno_fila_instancia SET terminou_em = sysdate
  WHERE iniciou_em = ultimo_comando;
END;
/

CREATE OR REPLACE PROCEDURE
  trab_push_comando(
    p_id_programa IN NUMBER
  )
IS
  v_programa NUMBER;

  CURSOR c_instancias(programa NUMBER) IS
    SELECT FROM v_instancias_por_fila
    WHERE id_programa = programa;
BEGIN
    -- Selecionar a instância com menos comandos na fila
    -- inserir comando na fila
  SELECT COUNT(1) INTO v_programa
  FROM trab_programas
  WHERE id_programa = p_id_programa;

  IF v_programa = 0 THEN
    raise_application_error(-20000, 'Programa não habilitado');
  END IF;

  FOR instancia IN c_instancias(p_id_programa) LOOP
    -- TODO
  END LOOP;
END;




















