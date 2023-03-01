package br.edu.ifrs.resource_sharing.core.daos;

import br.edu.ifrs.resource_sharing.app.commands.Migrator;
import br.edu.ifrs.resource_sharing.app.http.controllers.dto.ProgramaRequest;
import br.edu.ifrs.resource_sharing.infra.db.daos.ProgramaDAO;
import br.edu.ifrs.resource_sharing.core.mappers.ProgramaMapper;
import br.edu.ifrs.resource_sharing.core.entities.software.Programa;
import br.edu.ifrs.resource_sharing.infra.db.ConnectionProvider;
import br.edu.ifrs.resource_sharing.infra.db.OracleConnectionProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProgramaDAOTest {
	static ConnectionProvider cp = new OracleConnectionProvider();
	static Migrator migrator = new Migrator(cp);
	static ProgramaDAO programaDAO;

	@BeforeAll
	static void setUp() {
		migrator.up();
		programaDAO = new ProgramaDAO(cp, new ProgramaMapper());
	}

	@AfterAll
	static void tearDown() {
		migrator.down();
	}

	@Test
	void salvar() {
		ProgramaRequest pr = new ProgramaRequest(
			"Tomcat",
			BigDecimal.valueOf(512.00)
		);
		Programa programa = programaDAO.salvar(pr);
		assertEquals(1, programa.getId());
		assertEquals(pr.getNome(), programa.getNome());
	}

	@Test
	void usarPrograma() {
	}

	@Test
	void buscaTodos() {
		List<Programa> programas = programaDAO.buscaTodos();
		assertEquals(1, programas.size());
		assertInstanceOf(Programa.class, programas.get(0));
	}
}
