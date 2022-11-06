package br.edu.ifrs.resource_sharing.infra.db.daos.sqls;

public class RecursosSQL {
	public static final String BUSCA_P_ID = "SELECT ID_RECURSO, CAPACIDADE " +
			"FROM TRAB_RECURSOS WHERE ID_RECURSO = ?";
    /**
	 * fields qtd = 2, capacidade_md = 3, instituicao = 4
	 * returns c_recursos = 1
	 */
	public static String INSERT =
			"{CALL ? := trab_adc_recursos_instituicao(?, ?, ?)}";
	public static String DESLIGA =
			"{CALL trab_deliga_recursos_inativos()}";
}
