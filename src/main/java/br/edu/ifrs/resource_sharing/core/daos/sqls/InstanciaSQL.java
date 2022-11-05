package br.edu.ifrs.resource_sharing.core.daos.sqls;

public class InstanciaSQL {
	public static String EXECUTAR =
			"{CALL trab_concluir_comando_na_instancia(" +
					":id_instancia, " +
					":proximo_comando)}";
}
