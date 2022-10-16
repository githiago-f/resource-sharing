package br.edu.ifrs.resource_sharing.core.daos;

import br.edu.ifrs.resource_sharing.app.http.controllers.dto.RecursoRequest;
import br.edu.ifrs.resource_sharing.core.entities.institution.Recurso;
import br.edu.ifrs.resource_sharing.infra.db.ConnectionProvider;
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
		String qtd = "qtd", cap = "cap", inst = "inst";
		String sql = "{CALL trab_adc_recursos_instituicao(:qtd, :cap, :inst)}";
		try (Connection cn = connectionProvider.getConnection();
			 CallableStatement call = cn.prepareCall(sql)
		) {
			call.setInt(qtd, request.getQuantidade());
			call.setBigDecimal(cap, request.getCapacidadeMb());
			call.setInt(inst, request.getIdInstituicao());
			call.execute();
		} catch (SQLException e) {
			logger.error("Erro ao criar recurso: ", e);
		}
	}

	public List<Recurso> busca() {
		String sql = "";
		try (Connection con = connectionProvider.getConnection();
			 Statement stm = con.prepareStatement(sql)
		) {
			List<Recurso> recursos = new ArrayList<>();

		} catch (SQLException e) {
			logger.error("Não foi possivel buscar os recursos: ", e);
		}
		return List.of();
	}
}















