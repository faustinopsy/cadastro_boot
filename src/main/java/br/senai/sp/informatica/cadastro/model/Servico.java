package br.senai.sp.informatica.cadastro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
public class Servico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idServico;
	private String nome;
	private boolean desativado;
	@Transient
	private boolean selecionado;
	
	
}	