package br.edu.ifrs.resource_sharing;

import br.edu.ifrs.resource_sharing.app.commands.Migration;
import br.edu.ifrs.resource_sharing.infra.db.ConnectionProvider;
import br.edu.ifrs.resource_sharing.infra.db.OracleConnectionProvider;

public class CLI {
	public static void main(String[] args) {
		ConnectionProvider cp = new OracleConnectionProvider();
		Migration migration = new Migration(cp);
		migration.up();
		migration.down();
	}
}
