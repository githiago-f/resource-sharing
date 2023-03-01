package br.edu.ifrs.resource_sharing.app.http.controllers.dto;

import lombok.Data;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Data
public class ProgramaRequest {
	private String nome;
	private BigDecimal consumoMb;

	public ProgramaRequest(String nome, BigDecimal consumoMb) {
		Assert.hasText(nome, "Nome não deve ser vazio");
		Assert.notNull(consumoMb, "Consumo deve ser fornecido");
		this.nome = nome;
		this.consumoMb = consumoMb;
	}
}
