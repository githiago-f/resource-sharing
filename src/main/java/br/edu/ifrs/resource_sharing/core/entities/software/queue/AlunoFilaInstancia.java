package br.edu.ifrs.resource_sharing.core.entities.software.queue;

import br.edu.ifrs.resource_sharing.core.entities.software.Instancia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoFilaInstancia {
	private Date iniciouEm;
	private Date terminouEm;

	private AlunoFila alunoFila;
	private Instancia instancia;
}
