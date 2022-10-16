package br.edu.ifrs.resource_sharing.core.entities.software.queue;

import br.edu.ifrs.resource_sharing.core.entities.software.Instancia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Fila {
	private Integer id;

	private Instancia instancia;
	private Set<AlunoFila> alunosNaFila;
}
