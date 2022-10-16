package br.edu.ifrs.resource_sharing.core.daos;

import br.edu.ifrs.resource_sharing.app.http.controllers.dto.ProgramaRequest;
import br.edu.ifrs.resource_sharing.core.entities.software.Programa;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProgramaDAO {
	public void salvar(ProgramaRequest request) {

	}

	public List<Programa> findAll() {
		return List.of();
	}
}
