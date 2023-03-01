package br.edu.ifrs.resource_sharing.core.daos;

import br.edu.ifrs.resource_sharing.app.commands.Migrator;
import br.edu.ifrs.resource_sharing.app.http.controllers.dto.InstituicaoRequest;
import br.edu.ifrs.resource_sharing.app.http.controllers.dto.RecursoRequest;
import br.edu.ifrs.resource_sharing.infra.db.daos.InstituicaoDAO;
import br.edu.ifrs.resource_sharing.infra.db.daos.RecursoDAO;
import br.edu.ifrs.resource_sharing.core.mappers.IntituicaoMapper;
import br.edu.ifrs.resource_sharing.core.mappers.RecursoMapper;
import br.edu.ifrs.resource_sharing.core.entities.institution.Instituicao;
import br.edu.ifrs.resource_sharing.core.entities.institution.Recurso;
import br.edu.ifrs.resource_sharing.infra.db.ConnectionProvider;
import br.edu.ifrs.resource_sharing.infra.db.OracleConnectionProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecursoDAOTest {
	static ConnectionProvider cp = new OracleConnectionProvider();
	static Migrator migrator = new Migrator(cp);
	static RecursoDAO recursoDAO;
	static Instituicao instituicao;

	@BeforeAll
	static void setUp() {
		migrator.up();
		recursoDAO = new RecursoDAO(cp, new RecursoMapper());
		InstituicaoDAO dao = new InstituicaoDAO(cp, new IntituicaoMapper());
		InstituicaoRequest request = new InstituicaoRequest("IFRS");
		instituicao = dao.registrar(request);
	}

	@AfterEach
	void tearDown() {
		migrator.down();
	}

	RecursoRequest.RecursoRequestBuilder request() {
		return RecursoRequest.builder()
			.capacidadeMb(BigDecimal.valueOf(16000.42))
			.idInstituicao(instituicao.getId());
	}

	@Test
	@DisplayName("Deve salvar 3 novos recursos")
	void salva3() {
		RecursoRequest recursoRequest = request().quantidade(3).build();
		List<Recurso> recursos  = recursoDAO.salva(recursoRequest);
		assertEquals(3, recursos.size());
	}
}
