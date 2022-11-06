package br.edu.ifrs.resource_sharing.core.mappers;

import br.edu.ifrs.resource_sharing.core.entities.person.Aluno;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AlunoMapper implements Mapper<Aluno> {
	@Override
	public Aluno elementToEntity(ResultSet resultSet) {
		try {
			return new Aluno(
				resultSet.getInt(1),
				resultSet.getString(2),
				resultSet.getString(3)
			);
		} catch (SQLException e) {
			logger.error("Não foi possivel mapear o aluno: ", e);
		}
		return null;
	}
}
