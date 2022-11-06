package br.edu.ifrs.resource_sharing.infra.db.daos;

import br.edu.ifrs.resource_sharing.infra.db.daos.dto.MenuDTO;
import br.edu.ifrs.resource_sharing.core.mappers.Mapper;
import br.edu.ifrs.resource_sharing.infra.db.ConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MenuDAO {
	private final Logger logger = LoggerFactory.getLogger(
			MenuDAO.class.getSimpleName()
	);
	private final ConnectionProvider cnProvider;
	private final Mapper<MenuDTO> mapper;

	@Autowired
	public MenuDAO(ConnectionProvider cnProvider, Mapper<MenuDTO> mapper) {
		this.cnProvider = cnProvider;
		this.mapper = mapper;
	}

	public List<MenuDTO> getMenu()  {
		try(Connection cn = cnProvider.getConnection();
			CallableStatement stm = cn.prepareCall("{CALL busca_funcoes_menu(?)}")
		) {
			stm.registerOutParameter(1, Types.REF_CURSOR);
			stm.executeUpdate();
			ResultSet result = (ResultSet) stm.getObject(1);
			return mapper.mapElements(result);
		} catch (SQLException e) {
			logger.error("Erro ao construir o menu: ", e);
		}
		return new ArrayList<>();
	}
}
