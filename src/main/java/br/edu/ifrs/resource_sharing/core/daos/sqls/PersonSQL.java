package br.edu.ifrs.resource_sharing.core.daos.sqls;

import br.edu.ifrs.resource_sharing.core.daos.dto.Table;

public class PersonSQL {
	public static String CREATE =
			"{call INSERT INTO " + Table.ALUNOS +
			" VALUES (DEFAULT, ?, ?, ?) " +
			"RETURNING id_aluno INTO ?}";

	public static String LOGIN = "{CALL ? = trab_login(?, ?)}";
}
