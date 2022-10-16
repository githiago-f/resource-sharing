package br.edu.ifrs.resource_sharing.app.http.controllers.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RecursoRequest {
	private final Integer quantidade;
	private final Integer idInstituicao;
	private final BigDecimal capacidadeMb;
}
