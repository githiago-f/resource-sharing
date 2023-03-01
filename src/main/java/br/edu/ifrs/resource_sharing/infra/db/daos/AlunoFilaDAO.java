package br.edu.ifrs.resource_sharing.infra.db.daos;

import br.edu.ifrs.resource_sharing.core.entities.software.queue.AlunoFila;
import br.edu.ifrs.resource_sharing.core.mappers.Mapper;
import br.edu.ifrs.resource_sharing.infra.db.ConnectionProvider;
import br.edu.ifrs.resource_sharing.infra.db.daos.sqls.FilaSQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AlunoFilaDAO {
	private final Logger logger = LoggerFactory.getLogger(
		AlunoFilaDAO.class.getSimpleName()
	);
	private final ConnectionProvider cnProvider;
	private final Mapper<AlunoFila> mapper;

	@Autowired
	public AlunoFilaDAO(ConnectionProvider cnProvider, Mapper<AlunoFila> mapper) {
		this.cnProvider = cnProvider;
		this.mapper = mapper;
	}

	public AlunoFila buscaComando(Integer comando) {
		String sql = FilaSQL.COMANDO_POR_ID;
		try(Connection cn = cnProvider.getConnection();
			PreparedStatement stm = cn.prepareStatement(sql)) {
			stm.setInt(1, comando);
			try(ResultSet result = stm.executeQuery()) {
				Assert.isTrue(result.next(), "Nenhum comando retornado");
				return mapper.elementToEntity(result);
			}
		} catch (SQLException e) {
			logger.error("Erro ao concluír o comando: ", e);
		}
		return null;
	}
}
