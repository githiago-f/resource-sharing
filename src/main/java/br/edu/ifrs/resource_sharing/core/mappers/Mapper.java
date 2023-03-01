package br.edu.ifrs.resource_sharing.core.mappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface Mapper<T> {
	Logger logger = LoggerFactory.getLogger("Mapper");

	default List<T> mapElements(ResultSet resultSet) {
		List<T> response = new ArrayList<>();
		try {
			while (resultSet.next()) {
				response.add(elementToEntity(resultSet));
			}
		} catch (SQLException e) {
			logger.error("Não foi possivel ler o resultset: ", e);
		}
		return response;
	}

	T elementToEntity(ResultSet resultSet);
}
