package br.edu.ifrs.resource_sharing;

import br.edu.ifrs.resource_sharing.app.commands.Migrator;
import br.edu.ifrs.resource_sharing.app.http.controllers.dto.InstituicaoRequest;
import br.edu.ifrs.resource_sharing.app.http.controllers.dto.ProgramaRequest;
import br.edu.ifrs.resource_sharing.app.http.controllers.dto.RecursoRequest;
import br.edu.ifrs.resource_sharing.core.daos.InstituicaoDAO;
import br.edu.ifrs.resource_sharing.core.daos.ProgramaDAO;
import br.edu.ifrs.resource_sharing.core.daos.RecursoDAO;
import br.edu.ifrs.resource_sharing.core.daos.mappers.IntituicaoMapper;
import br.edu.ifrs.resource_sharing.core.daos.mappers.ProgramaMapper;
import br.edu.ifrs.resource_sharing.core.daos.mappers.RecursoMapper;
import br.edu.ifrs.resource_sharing.infra.db.ConnectionProvider;
import br.edu.ifrs.resource_sharing.infra.db.OracleConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class CLI {
	private static final Logger logger = LoggerFactory.getLogger(
			CLI.class.getSimpleName()
	);

	public static void main(String[] args) {
		// setup
		ConnectionProvider cp = new OracleConnectionProvider();

		Migrator migrator = new Migrator(cp);
		migrator.up();

		InstituicaoDAO dao = new InstituicaoDAO(cp, new IntituicaoMapper());
		// end setup

		InstituicaoRequest request = new InstituicaoRequest("IFRS");

		var instituicao = dao.registrar(request);
		assert instituicao != null;

		logger.info("Instituicao persistida: " + instituicao.getNome());

		var instituicoes = dao.buscaTodos();
		for (var inst : instituicoes) {
			System.out.println(inst);
		}




		// tear down
		migrator.down();
	}
}
