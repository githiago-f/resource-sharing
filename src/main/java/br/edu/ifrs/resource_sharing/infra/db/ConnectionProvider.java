package br.edu.ifrs.resource_sharing.infra.db;

import java.sql.Connection;

public interface ConnectionProvider {
	Connection getConnection();
}
