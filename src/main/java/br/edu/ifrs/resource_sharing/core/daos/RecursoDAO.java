package br.edu.ifrs.resource_sharing.core.daos;

import br.edu.ifrs.resource_sharing.app.http.controllers.dto.RecursoRequest;
import br.edu.ifrs.resource_sharing.core.daos.sqls.RecursosSQL;
import br.edu.ifrs.resource_sharing.core.entities.institution.Recurso;
import br.edu.ifrs.resource_sharing.core.entities.infra.db.ConnectionProvider;
import oracle.jdbc.OracleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class RecursoDAO {
	private final Logger logger = LoggerFactory.getLogger(
			RecursoDAO.class.getSimpleName()
	);
	private final ConnectionProvider connectionProvider;

	@Autowired
	public RecursoDAO(ConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
	}

	public void salva(RecursoRequest request) {
		try (Connection cn = connectionProvider.getConnection();
			 CallableStatement call = cn.prepareCall(RecursosSQL.INSERT)
		) {
			call.setInt("qtd", request.getQuantidade());
			call.setBigDecimal("capacidade_md", request.getCapacidadeMb());
			call.setInt("instituicao", request.getIdInstituicao());

			call.registerOutParameter(1, OracleTypes.CURSOR);

			call.execute();
		} catch (SQLException e) {
			logger.error("Erro ao criar recurso: ", e);
		}
	}
}
