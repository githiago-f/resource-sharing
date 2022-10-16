package br.edu.ifrs.resource_sharing.core.entities.institution;

import br.edu.ifrs.resource_sharing.core.entities.software.Instancia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recurso {
	private Integer id;
	private BigDecimal capacidadeMb;

	private List<Instancia> instancias = new ArrayList<>();
}
