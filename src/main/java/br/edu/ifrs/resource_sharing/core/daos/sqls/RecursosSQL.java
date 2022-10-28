package br.edu.ifrs.resource_sharing.core.daos.sqls;

public class RecursosSQL {
	/**
	 * fields qtd, capacidade_md, instituicao
	 * returns c_recursos
	 */
	public static String INSERT =
			"{EXEC ? := trab_adc_recursos_instituicao(" +
				":qtd, " +
				":capacidade_mb, " +
				":instituicao" +
			")}";
	public static String DESLIGA =
			"{CALL trab_deliga_recursos_inativos()}";
}
