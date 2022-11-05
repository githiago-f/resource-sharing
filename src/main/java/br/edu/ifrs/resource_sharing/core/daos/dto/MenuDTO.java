package br.edu.ifrs.resource_sharing.core.daos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpMethod;

@Data
@AllArgsConstructor
public class MenuDTO {
	private String operacao;
	private String link;
	private HttpMethod method;
}
