package br.edu.ifrs.resource_sharing.core.mappers;

import br.edu.ifrs.resource_sharing.core.entities.software.Programa;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProgramaMapper implements Mapper<Programa> {
	@Override
	public Programa elementToEntity(ResultSet resultSet) {
		try {
			return new Programa(
				resultSet.getInt(1),
				resultSet.getString(2),
				resultSet.getBigDecimal(3)
			);
		} catch (SQLException e) {
			logger.error("Erro ao converter resultset: ", e);
		}
		return null;
	}
}
