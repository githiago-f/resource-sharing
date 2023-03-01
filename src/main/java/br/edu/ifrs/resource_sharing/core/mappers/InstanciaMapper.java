package br.edu.ifrs.resource_sharing.core.mappers;

import br.edu.ifrs.resource_sharing.core.entities.institution.Recurso;
import br.edu.ifrs.resource_sharing.core.entities.software.Instancia;
import br.edu.ifrs.resource_sharing.core.entities.software.Programa;
import br.edu.ifrs.resource_sharing.infra.db.daos.ProgramaDAO;
import br.edu.ifrs.resource_sharing.infra.db.daos.RecursoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class InstanciaMapper implements Mapper<Instancia> {
	private final ProgramaDAO programaDAO;
	private final RecursoDAO recursoDAO;

	@Autowired
	public InstanciaMapper(ProgramaDAO programaDAO, RecursoDAO recursoDAO) {
		this.programaDAO = programaDAO;
		this.recursoDAO = recursoDAO;
	}

	@Override
	public Instancia elementToEntity(ResultSet resultSet) {
		try {
			Programa programa = programaDAO.buscarPorId(
					resultSet.getInt(2)
			);
			Recurso recurso = recursoDAO.buscaPorId(
					resultSet.getInt(3)
			);
			return Instancia.builder()
					.id(resultSet.getInt(1))
					.programa(programa)
					.recurso(recurso)
					.build();
		}catch (SQLException e) {
			logger.error("Erro ao construir instancia: ", e);
		}
		return null;
	}
}
