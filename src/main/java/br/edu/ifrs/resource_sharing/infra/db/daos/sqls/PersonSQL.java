package br.edu.ifrs.resource_sharing.infra.db.daos.sqls;

import br.edu.ifrs.resource_sharing.infra.db.daos.dto.Table;

public class PersonSQL {
	public static String CREATE =
			"{call INSERT INTO " + Table.ALUNOS +
			" VALUES (DEFAULT, ?, ?, ?) " +
			"RETURNING id_aluno INTO ?}";

	public static String LOGIN = "{CALL ? = trab_login(?, ?)}";

	public static String POR_ID = "SELECT ID_ALUNO, NOME, EMAIL " +
			"FROM TRAB_ALUNOS WHERE ID_ALUNO = ?";
}
