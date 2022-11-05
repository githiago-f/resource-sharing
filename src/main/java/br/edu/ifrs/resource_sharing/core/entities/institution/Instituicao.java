package br.edu.ifrs.resource_sharing.core.entities.institution;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class Instituicao {
	private Integer id;
	private String nome;

	private List<Recurso> recursos;

	public Instituicao(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
		recursos = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Instituicao{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				'}';
	}
}
