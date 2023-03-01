package br.edu.ifrs.resource_sharing.core.entities.software;

import br.edu.ifrs.resource_sharing.core.entities.institution.Recurso;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Instancia {
	private Integer id;

	private Programa programa;
	private Recurso recurso;
}
