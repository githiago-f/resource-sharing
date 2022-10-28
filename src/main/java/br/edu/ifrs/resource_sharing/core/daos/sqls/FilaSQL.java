package br.edu.ifrs.resource_sharing.core.daos.sqls;

public class FilaSQL {
	public String ALUNOS_NA_FILA = "{EXEC ? := trab_alunos_na_fila(:id_fila)}";
}
