package br.edu.ifrs.resource_sharing.core.daos.sqls;

public class RecursosSQL {
	/**
	 * fields qtd = 2, capacidade_md = 3, instituicao = 4
	 * returns c_recursos = 1
	 */
	public static String INSERT =
			"{CALL ? := trab_adc_recursos_instituicao(?, ?, ?)}";
	public static String DESLIGA =
			"{CALL trab_deliga_recursos_inativos()}";
}
