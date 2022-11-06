package br.edu.ifrs.resource_sharing.app.http.controllers;

import br.edu.ifrs.resource_sharing.app.http.controllers.dto.RecursoRequest;
import br.edu.ifrs.resource_sharing.infra.db.daos.RecursoDAO;
import br.edu.ifrs.resource_sharing.core.entities.institution.Recurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = { "/api/{idInstituicao}/recurso" })
public class RecursoController {
	private final RecursoDAO recursoDAO;

	@Autowired
	public RecursoController(RecursoDAO recursoDAO) {
		this.recursoDAO = recursoDAO;
	}

	@PostMapping
	public ResponseEntity<List<Recurso>> criarRecurso(
			@RequestParam Integer idInstituicao,
			@RequestBody RecursoRequest request
	) {
		request.setIdInstituicao(idInstituicao);
		return ResponseEntity.ok(recursoDAO.salva(request));
	}
}
