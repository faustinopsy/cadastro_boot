package br.senai.sp.informatica.cadastro.model.valueobject;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

	private String accessToken;
	private String tokenType= "Bearer ";
	
	public JwtAuthenticationResponse(String accessToken) {
		super();
		this.accessToken = accessToken;
		
	}
	
	
}
