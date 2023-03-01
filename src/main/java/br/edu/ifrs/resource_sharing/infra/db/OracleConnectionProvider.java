package br.edu.ifrs.resource_sharing.infra.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class OracleConnectionProvider implements ConnectionProvider {
	private final Logger logger = LoggerFactory.getLogger(
			OracleConnectionProvider.class.getSimpleName()
	);

	private final String CONNECTION_URL, USER_NAME, USER_PASSWORD;

	public OracleConnectionProvider() {
		USER_NAME = USER_PASSWORD = "usr52";
		CONNECTION_URL = "jdbc:oracle:thin:@200.132.53.144:1521/XEPDB1";
	}

	@Override
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return DriverManager.getConnection(
					CONNECTION_URL, USER_NAME, USER_PASSWORD);
		} catch (SQLException | ClassNotFoundException cne) {
			logger.error("Driver não foi encontrado", cne);
			System.exit(12);
		}
		return null;
	}
}
