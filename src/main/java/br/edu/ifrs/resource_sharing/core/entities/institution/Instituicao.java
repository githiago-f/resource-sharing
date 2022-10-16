package br.edu.ifrs.resource_sharing.core.entities.institution;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Instituicao {
	private Integer id;
	private String nome;

	private List<Recurso> recursos;
}
