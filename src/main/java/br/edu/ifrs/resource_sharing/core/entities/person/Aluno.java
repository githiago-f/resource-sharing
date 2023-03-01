package br.edu.ifrs.resource_sharing.core.entities.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
	private Integer id;
	private String nome;
	private String email;
}
