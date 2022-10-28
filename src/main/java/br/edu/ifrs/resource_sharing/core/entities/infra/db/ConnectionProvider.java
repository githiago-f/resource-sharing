package br.edu.ifrs.resource_sharing.core.entities.infra.db;

import java.sql.Connection;

public interface ConnectionProvider {
	Connection getConnection();
}
