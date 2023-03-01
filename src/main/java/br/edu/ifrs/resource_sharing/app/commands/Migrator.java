package br.edu.ifrs.resource_sharing.app.commands;

import br.edu.ifrs.resource_sharing.infra.db.ConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static br.edu.ifrs.resource_sharing.infra.fs.CustomFileReader.readFile;

@Component
public class Migrator {
    private final Logger logger = LoggerFactory.getLogger(
            Migrator.class.getSimpleName()
    );

    private final ConnectionProvider provider;
    private final String
			v1Schema, rFuctions, rProcedures, rViews, v1Drop, rTriggers, v2Populate;

    @Autowired
    public Migrator(ConnectionProvider provider) {
        v1Schema = "sql-scripts/V1__schema.sql";
		rViews = "sql-scripts/R__Create_all_views.sql";
		rProcedures = "sql-scripts/R__Create_procedures.sql";
		v1Drop = "sql-scripts/R__Drop_All.sql";
		rFuctions = "sql-scripts/R__Create_functions.sql";
		rTriggers = "sql-scripts/R__Create_triggers.sql";
		v2Populate = "sql-scripts/V2__Populate.sql";
        this.provider = provider;
    }

	private void createTables(Connection connection) {
		List<String> lines = readFile(v1Schema, ";");
		for(String line : lines) {
			try(Statement stm = connection.createStatement()) {
				stm.execute(line);
			} catch(SQLException e) {
				String message = e.getMessage(),
					used = "already used", exists = "already exists";
				if(message.contains(used) || message.contains(exists)) {
					continue;
				}
				if(message.contains("invalid datatype")) {
					logger.error("Command: " + line);
				}
				logger.error("Erro ao executar comando sql", e);
			}
		}
	}

	private void createProcedures(Connection connection) {
		List<String> lines = readFile(rViews, ";");
		lines.addAll(readFile(rProcedures, "/"));
		lines.addAll(readFile(rFuctions, "/"));
		lines.addAll(readFile(rTriggers, "/"));

		for(String line : lines) {
			try(Statement stm = connection.createStatement()) {
				stm.execute(line);
			} catch(SQLException e) {
				logger.error("Erro ao executar comando sql", e);
			} catch(Exception e) {
				logger.error("Erro: ", e);
			}
		}
	}

	private void populate(Connection connection) {
		List<String> lines = readFile(v2Populate, ";");
		for (String line : lines) {
			try (Statement stm = connection.createStatement()) {
				stm.execute(line);
			} catch(SQLException e) {
				logger.error("Erro ao executar comando sql", e);
			}
		}
	}

    @PostConstruct
    public void up() {
        logger.info("Executando migração");
        Connection connection = provider.getConnection();
		createTables(connection);
		createProcedures(connection);
		populate(connection);
		try {
			if(!connection.isClosed())
				connection.close();
		} catch (SQLException e) {
			logger.error("Erro ao realizar migração", e);
			System.exit(e.getErrorCode());
		}
		logger.info("Migração concluída");
    }

	@PreDestroy
	public void down() {
		logger.info("Revertendo migração");
		Connection connection = provider.getConnection();
		List<String> commands = readFile(v1Drop, ";");
		for(String command : commands) {
			try(Statement stm = connection.createStatement()) {
				stm.execute(command);
			} catch(SQLException e) {
				logger.error("Erro ao executar comando sql", e);
			}
		}
		try {
			if(!connection.isClosed())
				connection.close();
		} catch (SQLException e) {
			logger.error("Erro ao remover migrações", e);
			System.exit(e.getErrorCode());
		}
		logger.info("Reversão concluída");
	}
}
