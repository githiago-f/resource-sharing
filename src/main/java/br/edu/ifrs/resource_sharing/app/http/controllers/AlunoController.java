package br.edu.ifrs.resource_sharing.app.http.controllers;

import br.edu.ifrs.resource_sharing.app.http.controllers.dto.AlunoRequest;
import br.edu.ifrs.resource_sharing.app.http.controllers.dto.CredenciaisLogin;
import br.edu.ifrs.resource_sharing.infra.db.daos.PersonDAO;
import br.edu.ifrs.resource_sharing.core.entities.person.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AlunoController {
	private final PersonDAO alunoDAO;

	@Autowired
	public AlunoController(PersonDAO alunoDAO) {
		this.alunoDAO = alunoDAO;
	}

	@PostMapping("/api/login")
	public ResponseEntity<Aluno> login(@RequestBody CredenciaisLogin cred) {
		return ResponseEntity.ok(alunoDAO.login(cred));
	}

	@PostMapping("/api/alunos")
	public ResponseEntity<Aluno> register(@RequestBody AlunoRequest data) {
		return ResponseEntity.accepted().body(alunoDAO.register(data));
	}
}
