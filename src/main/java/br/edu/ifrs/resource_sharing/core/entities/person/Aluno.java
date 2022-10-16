package br.edu.ifrs.resource_sharing.core.entities.person;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
	private Integer id;
	private String nome;
	private String email;
	private String senha;
}
