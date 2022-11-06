package br.edu.ifrs.resource_sharing.infra.db.daos.sqls;

import br.edu.ifrs.resource_sharing.infra.db.daos.dto.Table;

public class ProgramaSQL {
	/**
	 * 1 - nome
	 * 2 - MEM_CONSUMIDA_APR
	 * 3 - OUT id_programa
	 */
	public static String CREATE =
			"{call INSERT INTO " + Table.PROGRAMAS + " VALUES (DEFAULT, ?, ?) " +
					"RETURNING id_programa INTO ?}";

	/**
	 * :programa String
	 * :aluno Integer - id
	 * :comando String
	 */
	public static String RUN =
			"{CALL trab_push_comando(:programa, :aluno, to_clob(:comando))}";

	public static String KILL = "{CALL trab_mata_processos_nao_usados()}";

	public static String FIND = "SELECT * FROM " + Table.PROGRAMAS;

	public static String BUSCA_P_ID = "SELECT * FROM " + Table.PROGRAMAS +
			" WHERE ID_PROGRAMA = ?";
}
