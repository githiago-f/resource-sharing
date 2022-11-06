package br.edu.ifrs.resource_sharing.core.mappers;

import br.edu.ifrs.resource_sharing.core.entities.institution.Instituicao;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class IntituicaoMapper implements Mapper<Instituicao> {
	private final Logger logger = LoggerFactory.getLogger(
		IntituicaoMapper.class.getSimpleName()
	);

	@Nullable
	public Instituicao elementToEntity(@NotNull ResultSet resultSet) {
		try {
			int id = resultSet.getInt(1);
			String nome = resultSet.getString(2);
			return new Instituicao(id, nome);
		} catch (SQLException e) {
			logger.error("Erro ao carregar a instância", e);
			return null;
		}
	}
}
