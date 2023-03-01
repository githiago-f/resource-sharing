package br.edu.ifrs.resource_sharing.infra.db.daos.sqls;

public class InstanciaSQL {
	public static String TERMINAR_COMANDO =
			"{CALL trab_concluir_comando_na_instancia(" +
					":id_instancia, " +
					":proximo_comando)}";
	public static String BUSCA_POR_PROGRAMA = "SELECT " +
			"ID_INSTANCIA, ID_RECURSO, ID_PROGRAMA " +
			"FROM TRAB_INSTANCIAS WHERE ID_PROGRAMA = ?";
}
