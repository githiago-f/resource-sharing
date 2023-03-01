package br.edu.ifrs.resource_sharing.app.http.controllers;

import br.edu.ifrs.resource_sharing.infra.db.daos.MenuDAO;
import br.edu.ifrs.resource_sharing.infra.db.daos.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MenuController {
	private final MenuDAO menuDAO;

	@Autowired
	public MenuController(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	@GetMapping
	public ResponseEntity<List<MenuDTO>> getMenu() {
		return ResponseEntity.ok(menuDAO.getMenu());
	}
}
