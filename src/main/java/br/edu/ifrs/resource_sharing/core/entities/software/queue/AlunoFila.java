package br.edu.ifrs.resource_sharing.core.entities.software.queue;

import br.edu.ifrs.resource_sharing.core.entities.person.Aluno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoFila {
	private Integer id;
	private Date dataEntrada;
	private Date dataSaida;
	private String comando;

	private Aluno aluno;
	private Fila fila;
}
