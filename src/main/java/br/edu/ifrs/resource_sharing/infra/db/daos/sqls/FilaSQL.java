package br.edu.ifrs.resource_sharing.infra.db.daos.sqls;

public class FilaSQL {
	public static String ALUNOS_NA_FILA = "{EXEC ? := trab_alunos_na_fila(:id_fila)}";
	public static String COMANDO_POR_ID = "SELECT ID_USUARIO, ID_FILA, DATA_ENTRADA, COMANDO " +
			"FROM TRAB_ALUNOS_FILA WHERE ID_ALUNOS_FILA = ?";
}
