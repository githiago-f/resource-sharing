package br.edu.ifrs.resource_sharing.core.mappers;

import br.edu.ifrs.resource_sharing.core.entities.person.Aluno;
import br.edu.ifrs.resource_sharing.core.entities.software.queue.AlunoFila;
import br.edu.ifrs.resource_sharing.core.entities.software.queue.Fila;
import br.edu.ifrs.resource_sharing.infra.db.daos.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AlunoFilaMapper implements Mapper<AlunoFila> {
	private final PersonDAO personDAO;

	@Autowired
	public AlunoFilaMapper(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@Override
	public AlunoFila elementToEntity(ResultSet resultSet) {
		try {
			Aluno aluno = personDAO.buscaPorId(resultSet.getInt(1));
			Fila fila = Fila.builder().id(resultSet.getInt(2)).build();
			return AlunoFila.builder()
					.aluno(aluno)
					.fila(fila)
					.dataEntrada(resultSet.getDate(3))
					.comando(resultSet.getClob(4).toString())
					.build();
		} catch (SQLException e) {
			logger.error("Erro ao construir entidade aluno-fila: ", e);
		}
		return null;
	}
}
