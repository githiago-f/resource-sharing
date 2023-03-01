package br.edu.ifrs.resource_sharing.app.http.controllers;

import br.edu.ifrs.resource_sharing.app.http.controllers.dto.MessageDTO;
import br.edu.ifrs.resource_sharing.app.http.controllers.dto.ProgramCallRequest;
import br.edu.ifrs.resource_sharing.app.http.controllers.dto.ProgramaRequest;
import br.edu.ifrs.resource_sharing.infra.db.daos.ProgramaDAO;
import br.edu.ifrs.resource_sharing.core.entities.software.Programa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = { "/api/programas" })
public class ProgramaController {
	private final ProgramaDAO programaDAO;

	@Autowired
	public ProgramaController(ProgramaDAO programaDAO) {
		this.programaDAO = programaDAO;
	}

	@PostMapping
	public ResponseEntity<Programa> salvarPrograma(
			@RequestBody ProgramaRequest request) {
		return ResponseEntity.ok(programaDAO.salvar(request));
	}

	@GetMapping
	public ResponseEntity<List<Programa>> listaProgramas() {
		return ResponseEntity.ok(programaDAO.buscaTodos());
	}

	@DeleteMapping
	public ResponseEntity<?> matarPrograma() {
		programaDAO.matarPrograma();
		return ResponseEntity.noContent().build();
	}

	/**
	 * ex.: /api/programas/Java
	 */
	@PostMapping(value = { "/{programa}" })
	public ResponseEntity<MessageDTO> enviarComando(
			@PathVariable String programa,
			@RequestBody ProgramCallRequest request) {
		request.setPrograma(programa);
		programaDAO.usarPrograma(request);
		return ResponseEntity.accepted().body(
			new MessageDTO("Comando enviado à fila do programa!"));
	}
}
