package br.edu.ifrs.resource_sharing.core.mappers;

import br.edu.ifrs.resource_sharing.core.entities.institution.Recurso;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RecursoMapper implements Mapper<Recurso> {
	private final Logger logger = LoggerFactory.getLogger(
		RecursoMapper.class.getSimpleName()
	);

	public Recurso elementToEntity(ResultSet resultSet) {
		try {
			int id = resultSet.getInt(1);
			BigDecimal capacidade = resultSet.getBigDecimal(2);
			return new Recurso(id, capacidade);
		} catch (SQLException e) {
			logger.error("Erro ao carregar o recurso", e);
			return null;
		}
	}
}
