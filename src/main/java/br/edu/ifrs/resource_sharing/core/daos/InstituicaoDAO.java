package br.edu.ifrs.resource_sharing.core.daos;

import br.edu.ifrs.resource_sharing.app.http.controllers.dto.InstituicaoRequest;
import br.edu.ifrs.resource_sharing.core.daos.dto.Table;
import br.edu.ifrs.resource_sharing.core.daos.mappers.IntituicaoMapper;
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
	private final IntituicaoMapper instituicaoMapper;
	private final ConnectionProvider connectionProvider;

	@Autowired
	public InstituicaoDAO(ConnectionProvider connectionProvider,
						  IntituicaoMapper instituicaoMapper) {
		this.connectionProvider = connectionProvider;
		this.instituicaoMapper = instituicaoMapper;
	}

	public Instituicao registrar(@NotNull InstituicaoRequest request) {
		String callSql = "{CALL trab_registra_instituicao(?, ?)}";
		try (Connection cn = connectionProvider.getConnection();
			 CallableStatement stm = cn.prepareCall(callSql)
		) {
			stm.setString(1, request.getNome());
			stm.registerOutParameter(2, Types.NUMERIC);
			stm.executeUpdate();

			int id = stm.getInt(2);

			return new Instituicao(id, request.getNome(), null);
		} catch (SQLException e) {
			logger.error("Impossível registrar instituicao", e);
		}
		return null;
	}

	public List<Instituicao> findAll() {
		String listSql = "SELECT * FROM " + Table.INSTITUICOES;
		try (Connection cn = connectionProvider.getConnection();
			 Statement stm = cn.createStatement()
		) {
			ResultSet rset = stm.executeQuery(listSql);
			Assert.notNull(rset, "Nenhum item retornado");
			return instituicaoMapper.mapElements(rset);
		} catch (SQLException e) {
			logger.error("Erro ao listar instituições", e);
		}
		return new ArrayList<>();
	}
}


















