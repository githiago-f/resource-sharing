package br.edu.ifrs.resource_sharing.app.http.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Data
@Builder
public class RecursoRequest {
	private Integer quantidade = 1;
	private Integer idInstituicao;
	private final BigDecimal capacidadeMb;

	public RecursoRequest(Integer quantidade, Integer idInstituicao, BigDecimal capacidadeMb) {
		Assert.isTrue(quantidade >= 1, "Um numero positivo deve ser enviado");
		Assert.isTrue(capacidadeMb.compareTo(BigDecimal.ZERO)>0, "Capacidade inválida!");

		this.quantidade = quantidade;
		this.idInstituicao = idInstituicao;
		this.capacidadeMb = capacidadeMb;
	}
}
