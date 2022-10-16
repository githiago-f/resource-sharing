package br.edu.ifrs.resource_sharing.core.daos.mappers;

import br.edu.ifrs.resource_sharing.core.entities.institution.Recurso;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RecursoMapper {
	private final Logger logger = LoggerFactory.getLogger(
			RecursoMapper.class.getSimpleName()
	);

	@Nullable
	public Recurso elementToEntity(@NotNull ResultSet resultSet) {
		try {
			return new Recurso(
				resultSet.getInt(1),
				resultSet.getBigDecimal(2),
				null
			);
		} catch(SQLException e) {
			logger.error("Erro ao carregar a instância", e);
			return null;
		}
	}
}
