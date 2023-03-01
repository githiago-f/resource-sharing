package br.edu.ifrs.resource_sharing.infra.db.daos;

import br.edu.ifrs.resource_sharing.app.http.controllers.dto.RecursoRequest;
import br.edu.ifrs.resource_sharing.core.mappers.Mapper;
import br.edu.ifrs.resource_sharing.infra.db.daos.sqls.RecursosSQL;
import br.edu.ifrs.resource_sharing.core.entities.institution.Recurso;
import br.edu.ifrs.resource_sharing.infra.db.ConnectionProvider;
import oracle.jdbc.OracleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class RecursoDAO {
	private final Logger logger = LoggerFactory.getLogger(
			RecursoDAO.class.getSimpleName()
	);
	private final ConnectionProvider connectionProvider;
	private final Mapper<Recurso> mapper;

	@Autowired
	public RecursoDAO(ConnectionProvider connectionProvider, Mapper<Recurso> mapper) {
		this.connectionProvider = connectionProvider;
		this.mapper = mapper;
	}

	public List<Recurso> salva(RecursoRequest request) {
		try (Connection cn = connectionProvider.getConnection();
			 CallableStatement call = cn.prepareCall(RecursosSQL.INSERT)
		) {
			call.setInt(2, request.getQuantidade());
			call.setBigDecimal(3, request.getCapacidadeMb());
			call.setInt(4, request.getIdInstituicao());

			call.registerOutParameter(1, OracleTypes.CURSOR);

			call.executeUpdate();
			try(ResultSet data = (ResultSet) call.getObject(1)) {
				return mapper.mapElements(data);
			}
		} catch (SQLException e) {
			logger.error("Erro ao criar recurso: ", e);
		}
		return new ArrayList<>();
	}

	public Recurso buscaPorId(Integer id) {
		try (Connection cn = connectionProvider.getConnection();
			 PreparedStatement stm = cn.prepareStatement(RecursosSQL.BUSCA_P_ID);
		) {
			stm.setInt(1, id);
			try (ResultSet data = stm.executeQuery()) {
				return mapper.elementToEntity(data);
			}
		} catch (SQLException e) {
			logger.error("Erro ao ler programa por id: ", e);
		}
		return null;
	}
}
