package br.edu.ifrs.resource_sharing.infra.db.daos;

import br.edu.ifrs.resource_sharing.core.entities.software.Instancia;
import br.edu.ifrs.resource_sharing.core.entities.software.Programa;
import br.edu.ifrs.resource_sharing.core.entities.software.queue.AlunoFila;
import br.edu.ifrs.resource_sharing.core.mappers.Mapper;
import br.edu.ifrs.resource_sharing.infra.db.ConnectionProvider;
import br.edu.ifrs.resource_sharing.infra.db.daos.sqls.InstanciaSQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class InstanciaDAO {
	private final Logger logger = LoggerFactory.getLogger(
		InstanciaDAO.class.getSimpleName()
	);
	private final ConnectionProvider cnProvider;
	private final AlunoFilaDAO alunoFilaDAO;
	private final Mapper<Instancia> mapper;

	@Autowired
	public InstanciaDAO(
			ConnectionProvider cnProvider, AlunoFilaDAO alunoFilaDAO, Mapper<Instancia> mapper) {
		this.cnProvider = cnProvider;
		this.alunoFilaDAO = alunoFilaDAO;
		this.mapper = mapper;
	}

	public AlunoFila concluirComandos(Integer instancia) {
		try (Connection cn = cnProvider.getConnection();
			 CallableStatement call = cn.prepareCall(InstanciaSQL.TERMINAR_COMANDO)) {
			call.setInt("id_instancia", instancia);
			call.registerOutParameter("proximo_comando", Types.NUMERIC);
			call.executeUpdate();
			int next = call.getInt("proximo_comando");

			return alunoFilaDAO.buscaComando(next);
		} catch (SQLException e) {
			logger.error("Erro ao concluír o comando: ", e);
		}
		return null;
	}

	public List<Instancia> buscarPorPrograma(Programa programa) {
		String sql = InstanciaSQL.BUSCA_POR_PROGRAMA;
		try(Connection cn = cnProvider.getConnection();
			PreparedStatement stm = cn.prepareStatement(sql)) {
			stm.setInt(1, programa.getId());
			try(ResultSet rset = stm.executeQuery();) {
				return mapper.mapElements(rset);
			}
		}catch (SQLException e) {
			logger.error("Erro ao buscar por instancia: ", e);
		}
		return new ArrayList<>();
	}
}
