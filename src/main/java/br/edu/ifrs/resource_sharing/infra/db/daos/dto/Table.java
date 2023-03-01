package br.edu.ifrs.resource_sharing.infra.db.daos.dto;

public enum Table {
	INSTITUICOES("TRAB_INSTITUICOES"),
	RECURSOS("TRAB_RECURSOS"),
	PROGRAMAS("TRAB_PROGRAMAS"),
	INSTANCIAS("TRAB_INSTANCIAS"),
	FILAS("TRAB_FILAS"),
	ALUNOS("TRAB_ALUNOS"),
	ALUNOS_FILA("TRAB_ALUNOS_FILA"),
	ALUNOS_FILA_INSTANCIA("TRAB_ALUNO_FILA_INSTANCIA");

	private final String tableName;

	Table(String name) {
		tableName = name;
	}

	public String getTableName() {
		return tableName;
	}

	@Override
	public String toString() {
		return this.getTableName();
	}
}
