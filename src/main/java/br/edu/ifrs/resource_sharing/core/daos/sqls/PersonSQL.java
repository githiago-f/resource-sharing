package br.edu.ifrs.resource_sharing.core.daos.sqls;

import br.edu.ifrs.resource_sharing.core.daos.dto.Table;

public class PersonSQL {
	public static String CREATE =
			"INSERT INTO " + Table.ALUNOS +
					" VALUES (DEFAULT, :nome, :email, :senha)";
}
