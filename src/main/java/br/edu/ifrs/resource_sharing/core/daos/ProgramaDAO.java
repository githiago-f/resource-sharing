package br.edu.ifrs.resource_sharing.core.daos;

import br.edu.ifrs.resource_sharing.app.http.controllers.dto.ProgramCallRequest;
import br.edu.ifrs.resource_sharing.app.http.controllers.dto.ProgramaRequest;
import br.edu.ifrs.resource_sharing.core.daos.mappers.Mapper;
import br.edu.ifrs.resource_sharing.core.daos.mappers.ProgramaMapper;
import br.edu.ifrs.resource_sharing.core.daos.sqls.ProgramaSQL;
import br.edu.ifrs.resource_sharing.core.daos.sqls.RecursosSQL;
import br.edu.ifrs.resource_sharing.core.entities.software.Programa;
import br.edu.ifrs.resource_sharing.infra.db.ConnectionProvider;
import oracle.jdbc.OracleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProgramaDAO {
	private static final Logger logger = LoggerFactory.getLogger(
		ProgramaDAO.class.getSimpleName()
	);
	private final ConnectionProvider provider;
	private final Mapper<Programa> mapper;

	@Autowired
	public ProgramaDAO(ConnectionProvider provider, Mapper<Programa> mapper) {
		this.provider = provider;
		this.mapper = mapper;
	}

	public Programa salvar(ProgramaRequest request) {
		try(Connection cn = provider.getConnection();
			CallableStatement call = cn.prepareCall(ProgramaSQL.CREATE)
		) {
			call.setString(1, request.getNome());
			call.setBigDecimal(2, request.getConsumoMb());

			call.registerOutParameter(3, Types.NUMERIC);

			boolean inserido = call.executeUpdate() > 0;
			Assert.isTrue(inserido, "Nenhuma linha alterada");

			int id = call.getInt(3);
			return new Programa(id, request.getNome(), request.getConsumoMb());
		} catch (SQLException e) {
			logger.error("Erro ao inserir programa: ", e);
		}
		return null;
	}

	public void usarPrograma(ProgramCallRequest request) {
		String sql = ProgramaSQL.RUN;
		try(Connection cn = provider.getConnection();
			CallableStatement call = cn.prepareCall(sql)
		) {
			call.setString("programa", request.getPrograma());
			call.setInt("aluno", request.getAluno());
			call.setString("comando", request.getComando());
			call.executeUpdate();
		} catch (SQLException e) {
			logger.error("Erro ao executar programa: ", e);
		}
	}

	public void matarPrograma() {
	}

	public List<Programa> buscaTodos() {
		try (Connection cn = provider.getConnection();
			 Statement stm = cn.createStatement();
			 ResultSet data = stm.executeQuery(ProgramaSQL.FIND)
		) {
			return mapper.mapElements(data);
		} catch (SQLException e) {
			logger.error("Erro ao criar recurso: ", e);
		}
		return new ArrayList<>();
	}
}
