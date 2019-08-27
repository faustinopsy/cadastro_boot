package br.senai.sp.informatica.cadastro.model;

import lombok.Data;

@Data
public class servico {
	private Integer idServico;
	private String nome;
	private boolean desativado;
	private boolean selecionado;
	
	
}	
