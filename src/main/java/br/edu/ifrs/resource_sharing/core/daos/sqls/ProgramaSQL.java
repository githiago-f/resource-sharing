package br.edu.ifrs.resource_sharing.core.daos.sqls;

import br.edu.ifrs.resource_sharing.core.daos.dto.Table;

public class ProgramaSQL {
	public static String CREATE =
			"INSERT INTO " + Table.PROGRAMAS + " VALUES (DEFAULT, ?, ?);";
	public static String RUN =
			"{CALL trab_push_comando(:programa, :aluno, to_clob(:comando))}";
	public static String KILL = "{CALL trab_mata_processos_nao_usados()}";
}
