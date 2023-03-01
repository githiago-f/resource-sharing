package br.edu.ifrs.resource_sharing.app.http.controllers;

import br.edu.ifrs.resource_sharing.app.http.controllers.dto.InstituicaoRequest;
import br.edu.ifrs.resource_sharing.infra.db.daos.InstituicaoDAO;
import br.edu.ifrs.resource_sharing.core.entities.institution.Instituicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/api/instituicoes")
public class InstituicaoController {
	private final InstituicaoDAO instituicaoDAO;

	@Autowired
	public InstituicaoController(InstituicaoDAO instituicaoDAO) {
		this.instituicaoDAO = instituicaoDAO;
	}

	@GetMapping
	public ResponseEntity<List<Instituicao>> index() {
		List<Instituicao> instituicoes = instituicaoDAO.buscaTodos();
		return ResponseEntity.ok().body(instituicoes);
	}

	@PostMapping
	public ResponseEntity<Instituicao> create(
			@RequestBody InstituicaoRequest request) {
		Instituicao instituicao = instituicaoDAO.registrar(request);
		return ResponseEntity.ok().body(instituicao);
	}
}
