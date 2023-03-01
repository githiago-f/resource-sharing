CREATE OR REPLACE VIEW v_instancias_ultimo_uso AS
  SELECT
    ins.id_instancia as id_instancia,
    to_char(afi.terminou_em, 'YYYY/MM/DD HH24:mi') ultimo_uso,
    round(to_number(sysdate - afi.terminou_em)*1440) minutos_sem_uso
  FROM trab_instancias ins
  JOIN trab_aluno_fila_instancia afi ON afi.id_instancia = ins.id_instancia
  ORDER BY afi.terminou_em DESC;

CREATE OR REPLACE VIEW v_recursos_disponiveis AS
  SELECT
    re.id_recurso as recurso,
    re.id_instituicao as instituicao,
    re.capacidade as capacidade,
    COUNT(ins.id_instancia) as instancias,
    SUM(pr.mem_consumida_apr) as mem_em_uso
  FROM trab_recursos re
  LEFT JOIN trab_instancias ins ON ins.id_recurso = re.id_recurso
  LEFT JOIN trab_programas pr ON pr.id_programa = ins.id_programa
  GROUP BY re.id_recurso, re.id_instituicao, re.capacidade
  HAVING SUM(pr.mem_consumida_apr) < re.capacidade
         OR SUM(pr.mem_consumida_apr) IS NULL;

CREATE OR REPLACE VIEW v_instancias_por_fila AS
  SELECT
    ID_FILA,
    ID_PROGRAMA,
    id_usuario USUARIOS
  FROM trab_instancias
    LEFT JOIN TRAB_FILAS USING(ID_INSTANCIA)
    JOIN TRAB_ALUNOS_FILA USING(id_fila)
  WHERE DATA_SAIDA IS NULL
  ORDER BY USUARIOS;

CREATE OR REPLACE VIEW v_alunos_ainda_na_fila AS
  SELECT
    ID_ALUNOS_FILA,
    id_instancia
  FROM TRAB_ALUNOS_FILA
    JOIN TRAB_FILAS USING(ID_FILA)
  WHERE DATA_SAIDA IS NULL
  ORDER BY DATA_ENTRADA DESC;
