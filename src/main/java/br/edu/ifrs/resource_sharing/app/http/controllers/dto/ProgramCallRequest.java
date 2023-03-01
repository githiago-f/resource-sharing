package br.edu.ifrs.resource_sharing.app.http.controllers.dto;

import lombok.Data;
import org.springframework.util.Assert;

@Data
public class ProgramCallRequest {
	private String programa;
	private Integer aluno;
	private String comando;

	public ProgramCallRequest(String programa, Integer aluno, String comando) {
		Assert.hasText(programa, "Nome do programa não fornecido");
		Assert.isTrue(aluno > 0, "Id do aluno deve ser informado");
		Assert.hasText(comando, "Comando deve ser inserido");
		this.programa = programa;
		this.aluno = aluno;
		this.comando = comando;
	}
}
