package br.edu.ifrs.resource_sharing.app.http.controllers.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Data
@Builder
public class RecursoRequest {
	private Integer quantidade;
	private Integer idInstituicao;
	private final BigDecimal capacidadeMb;

	public RecursoRequest(Integer idInstituicao, BigDecimal capacidadeMb) {
		Assert.isTrue(capacidadeMb.compareTo(BigDecimal.ZERO)>0, "Capacidade inválida!");

		this.quantidade = 1;
		this.idInstituicao = idInstituicao;
		this.capacidadeMb = capacidadeMb;
	}

	public RecursoRequest(Integer quantidade, Integer idInstituicao, BigDecimal capacidadeMb) {
		this(idInstituicao, capacidadeMb);
		
		Assert.isTrue(quantidade >= 1, "Um numero positivo deve ser enviado");
		this.quantidade = quantidade;
	}
}
