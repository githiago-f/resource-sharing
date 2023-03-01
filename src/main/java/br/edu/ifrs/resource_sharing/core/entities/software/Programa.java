package br.edu.ifrs.resource_sharing.core.entities.software;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Programa {
	private Integer id;
	private String nome;
	private BigDecimal memoriaConsumidaApr;

	public Programa(Integer id, String nome, BigDecimal memoriaConsumidaApr) {
		this.id = id;
		this.nome = nome;
		this.memoriaConsumidaApr = memoriaConsumidaApr;
	}

	@Override
	public String toString() {
		return "Programa{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", memoriaConsumidaApr=" + memoriaConsumidaApr +
				'}';
	}
}
