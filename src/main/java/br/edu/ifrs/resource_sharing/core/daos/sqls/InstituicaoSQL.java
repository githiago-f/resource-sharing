package br.edu.ifrs.resource_sharing.core.daos.sqls;

import br.edu.ifrs.resource_sharing.core.daos.dto.Table;

public class InstituicaoSQL {
	public static String CREATE =
			"INSERT INTO " + Table.INSTITUICOES + " VALUES (DEFAULT, ?);";
	public static String ADC_RECURSO = "{CALL trab_adc_recursos_instituicao()}";
}
