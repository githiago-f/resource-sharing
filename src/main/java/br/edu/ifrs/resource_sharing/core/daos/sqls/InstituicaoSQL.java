package br.edu.ifrs.resource_sharing.core.daos.sqls;

import br.edu.ifrs.resource_sharing.core.daos.dto.Table;

public class InstituicaoSQL {
	/*
	 * IN nome
	 */
	public static String CREATE =
			"{call INSERT INTO " + Table.INSTITUICOES + " VALUES (DEFAULT, ?) " +
					"RETURNING id_instituicao INTO ?}";
	public static String FIND =
			"SELECT * FROM " + Table.INSTITUICOES;
}
