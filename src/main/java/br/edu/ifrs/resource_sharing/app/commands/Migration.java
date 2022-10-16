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
import static br.edu.ifrs.resource_sharing.infra.fs.CustomFileReader.readFile;

@Component
public class Migration {
    private final Logger logger = LoggerFactory.getLogger(
            Migration.class.getSimpleName()
    );

    private final ConnectionProvider provider;
    private final String v1Schema, rProcedures, rViews, v1Drop;

    @Autowired
    public Migration(ConnectionProvider provider) {
        v1Schema = "sql-scripts/V1__schema.sql";
		rViews = "sql-scripts/R__Create_all_views.sql";
		rProcedures = "sql-scripts/R__Create_procedures.sql";
		v1Drop = "sql-scripts/R__Drop_All.sql";
        this.provider = provider;
    }

	private void createTables(Connection connection) {
		String[] lines = readFile(v1Schema, ";");
		assert lines != null;
		for(String line : lines) {
			try(Statement stm = connection.createStatement()) {
				stm.execute(line);
			} catch(SQLException e) {
				String message = e.getMessage(),
						used = "already used", exists = "already exists";
				if(message.contains(used) || message.contains(exists)) {
					continue;
				}
				logger.error("Erro ao executar comando sql", e);
			}
		}
	}

	private void createViewsAndProcedures(Connection connection) {
		String[] lines = readFile(rViews, ";");
		assert lines != null;
		for(String line : lines) {
			try(Statement stm = connection.createStatement()) {
				stm.execute(line);
			} catch(SQLException e) {
				logger.error("Erro ao executar comando sql", e);
			}
		}
		lines = readFile(rProcedures, "/");
		assert lines != null;
		for(String line : lines) {
			try(Statement stm = connection.createStatement()) {
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
		createViewsAndProcedures(connection);
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
		String[] commands = readFile(v1Drop, ";");
		assert commands != null;
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
