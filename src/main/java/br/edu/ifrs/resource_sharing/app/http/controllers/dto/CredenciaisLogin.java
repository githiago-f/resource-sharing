package br.edu.ifrs.resource_sharing.app.http.controllers.dto;

import lombok.Data;

@Data
public class CredenciaisLogin {
	private String email, senha;
}
