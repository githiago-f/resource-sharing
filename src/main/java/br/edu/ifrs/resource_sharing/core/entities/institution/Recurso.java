package br.edu.ifrs.resource_sharing.core.entities.institution;

import br.edu.ifrs.resource_sharing.core.entities.software.Instancia;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class Recurso {
	private Integer id;
	private BigDecimal capacidadeMb;

	private List<Instancia> instancias;

	public Recurso(Integer id, BigDecimal capacidadeMb) {
		this.id = id;
		this.capacidadeMb = capacidadeMb;
		this.instancias = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Recurso{" +
				"id=" + id +
				", capacidadeMb=" + capacidadeMb +
				'}';
	}
}
