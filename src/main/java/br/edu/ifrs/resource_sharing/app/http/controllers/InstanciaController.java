package br.edu.ifrs.resource_sharing.app.http.controllers;

import br.edu.ifrs.resource_sharing.core.entities.software.Instancia;
import br.edu.ifrs.resource_sharing.core.entities.software.Programa;
import br.edu.ifrs.resource_sharing.core.entities.software.queue.AlunoFila;
import br.edu.ifrs.resource_sharing.infra.db.daos.InstanciaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = { "/api/instancias" })
public class InstanciaController {
	private final InstanciaDAO instanciaDAO;

	@Autowired
	public InstanciaController(InstanciaDAO instanciaDAO) {
		this.instanciaDAO = instanciaDAO;
	}

	@GetMapping(value = {"{idPrograma}"})
	public ResponseEntity<List<Instancia>> listarInstancias(Integer idPrograma) {
		Programa programa = Programa.builder().id(idPrograma).build();
		List<Instancia> instancias = instanciaDAO.buscarPorPrograma(programa);
		return ResponseEntity.ok(instancias);
	}

	@PostMapping(value = { "{idIntancia}" })
	public ResponseEntity<AlunoFila> terminarComando(
			@PathVariable Integer idInstancia) {
		AlunoFila proximoComando = instanciaDAO.concluirComandos(idInstancia);
		return ResponseEntity.ok(proximoComando);
	}
}
