package br.edu.ifrs.resource_sharing.core.mappers;

import br.edu.ifrs.resource_sharing.infra.db.daos.dto.MenuDTO;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MenuMapper implements Mapper<MenuDTO> {
	@Override
	public MenuDTO elementToEntity(ResultSet resultSet) {
		try {
			return new MenuDTO(
					resultSet.getString(1),
					resultSet.getString(2),
					HttpMethod.valueOf(resultSet.getString(3))
			);
		} catch (SQLException e) {
			logger.error("Erro ao mapear o menu: ", e);
		}
		return null;
	}
}
