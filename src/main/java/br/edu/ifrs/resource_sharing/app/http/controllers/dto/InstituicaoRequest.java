package br.edu.ifrs.resource_sharing.app.http.controllers.dto;

import lombok.Data;
import org.springframework.util.Assert;

@Data
public class InstituicaoRequest {
	private String nome;
	public InstituicaoRequest(String nome) {
		Assert.hasText(nome, "Nome não pode ser vazio");
		this.nome = nome;
	}
}
