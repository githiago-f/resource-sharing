package br.edu.ifrs.resource_sharing.app.http.controllers.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class RecursoRequestTest {
	@Test
	@DisplayName("Deve retornar erro no numero de recursos")
	void naoSalva() {
		Executable recursoRequest = () -> RecursoRequest.builder()
				.capacidadeMb(BigDecimal.valueOf(16000.42))
				.idInstituicao(1)
				.quantidade(0).build();
		assertThrows(IllegalArgumentException.class, recursoRequest);
	}

	@Test
	@DisplayName("Deve retornar erro na capacidade")
	void naoSalva2() {
		Executable recursoRequest = () -> RecursoRequest.builder()
				.capacidadeMb(BigDecimal.ZERO)
				.idInstituicao(1)
				.quantidade(0).build();
		assertThrows(IllegalArgumentException.class, recursoRequest);
	}
}
