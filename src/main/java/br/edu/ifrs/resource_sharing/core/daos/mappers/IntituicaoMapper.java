package br.edu.ifrs.resource_sharing.core.daos.mappers;

import br.edu.ifrs.resource_sharing.core.entities.institution.Instituicao;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class IntituicaoMapper {
	private final Logger logger = LoggerFactory.getLogger(
			IntituicaoMapper.class.getSimpleName()
	);

	@Nullable
	public Instituicao elementToEntity(@NotNull ResultSet resultSet) {
		try {
			return Instituicao.builder()
					.id(resultSet.getInt(1))
					.nome(resultSet.getString(2))
					.build();
		} catch(SQLException e) {
			logger.error("Erro ao carregar a instância", e);
			return null;
		}
	}

	public List<Instituicao> mapElements(@NotNull ResultSet resultSet)
			throws SQLException {
		List<Instituicao> list = new ArrayList<>();
		while(resultSet.next()) {
			list.add(elementToEntity(resultSet));
		}
		return list;
	}
}
