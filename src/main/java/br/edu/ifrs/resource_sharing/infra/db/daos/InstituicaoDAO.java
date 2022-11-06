package br.edu.ifrs.resource_sharing.infra.db.daos;

import br.edu.ifrs.resource_sharing.app.http.controllers.dto.InstituicaoRequest;
import br.edu.ifrs.resource_sharing.core.mappers.Mapper;
import br.edu.ifrs.resource_sharing.infra.db.daos.sqls.InstituicaoSQL;
import br.edu.ifrs.resource_sharing.core.entities.institution.Instituicao;
import br.edu.ifrs.resource_sharing.infra.db.ConnectionProvider;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class InstituicaoDAO {
	private final Logger logger = LoggerFactory.getLogger(
			InstituicaoDAO.class.getSimpleName()
	);
	private final Mapper<Instituicao> mapper;
	private final ConnectionProvider connectionProvider;

	@Autowired
	public InstituicaoDAO(ConnectionProvider cnProvider, Mapper<Instituicao> mapper) {
		this.connectionProvider = cnProvider;
		this.mapper = mapper;
	}

	public Instituicao registrar(@NotNull InstituicaoRequest request) {
		try (Connection cn = connectionProvider.getConnection();
			 CallableStatement stm = cn.prepareCall(InstituicaoSQL.CREATE)
		) {
			stm.setString(1, request.getNome());
			stm.registerOutParameter(2, Types.NUMERIC);

			boolean affectedRow = stm.executeUpdate() > 0;
			Assert.isTrue(affectedRow, "Nenhuma mudança persistida");

			int id = stm.getInt(2);

			return new Instituicao(id, request.getNome());
		} catch (SQLException e) {
			logger.error("Não foi possivel registrar instituicao", e);
		}
		return null;
	}

	public List<Instituicao> buscaTodos() {
		try (Connection cn = connectionProvider.getConnection();
			 Statement stm = cn.createStatement();
			 ResultSet rset = stm.executeQuery(InstituicaoSQL.FIND)
		) {
			Assert.notNull(rset, "Nenhum item retornado");
			return mapper.mapElements(rset);
		} catch (SQLException e) {
			logger.error("Erro ao listar instituições", e);
		}
		return new ArrayList<>();
	}
}
