package br.edu.ifrs.resource_sharing.infra.db.daos;

import br.edu.ifrs.resource_sharing.app.http.controllers.dto.AlunoRequest;
import br.edu.ifrs.resource_sharing.app.http.controllers.dto.CredenciaisLogin;
import br.edu.ifrs.resource_sharing.core.mappers.Mapper;
import br.edu.ifrs.resource_sharing.infra.db.daos.sqls.PersonSQL;
import br.edu.ifrs.resource_sharing.core.entities.person.Aluno;
import br.edu.ifrs.resource_sharing.infra.db.ConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.sql.*;

@Component
public class PersonDAO {
	private final Logger logger = LoggerFactory.getLogger(
		PersonDAO.class.getSimpleName()
	);
	private final ConnectionProvider cnProvider;
	private final Mapper<Aluno> mapper;

	@Autowired
	public PersonDAO(ConnectionProvider cnProvider, Mapper<Aluno> mapper) {
		this.cnProvider = cnProvider;
		this.mapper = mapper;
	}

	public Aluno login(CredenciaisLogin credenciais) {
		try(Connection cn = cnProvider.getConnection();
			CallableStatement call = cn.prepareCall(PersonSQL.LOGIN)) {
			call.setString(2, credenciais.getEmail());
			call.setString(3, credenciais.getSenha());
			call.registerOutParameter(1, Types.REF_CURSOR);
			call.executeUpdate();

			try(ResultSet rset = (ResultSet) call.getObject(1)) {
				Assert.isTrue(rset.next(), "Nenhum aluno retornado");
				return mapper.elementToEntity(rset);
			}
		} catch (SQLException e) {
			logger.error("Erro ao efetuar login: ", e);
		}
		return null;
	}

	public Aluno register(AlunoRequest data) {
		try(Connection cn = cnProvider.getConnection();
			CallableStatement call = cn.prepareCall(PersonSQL.CREATE)) {
			call.setString(1, data.getNome());
			call.setString(2, data.getEmail());
			call.setString(3, data.getSenha());
			call.registerOutParameter(1, Types.NUMERIC);

			boolean affectedRow = call.executeUpdate() > 0;
			Assert.isTrue(affectedRow, "Nenhum aluno foi cadastrado");

			int id = call.getInt(2);
			return new Aluno(id, data.getNome(), data.getEmail());
		}catch (SQLException e) {
			logger.error("Erro ao registrar aluno: ", e);
		}
		return null;
	}

	public Aluno buscaPorId(int id) {
		try(Connection cn = cnProvider.getConnection();
			PreparedStatement stm = cn.prepareStatement(PersonSQL.POR_ID)) {
			stm.setInt(1, id);
			try(ResultSet result = stm.executeQuery()) {
				return mapper.elementToEntity(result);
			}
		} catch (SQLException e) {
			logger.error("Error ao buscar aluno por id: ", e);
		}
		return null;
	}
}
