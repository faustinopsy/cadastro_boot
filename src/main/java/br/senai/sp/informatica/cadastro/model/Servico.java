package br.senai.sp.informatica.cadastro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Servico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idServico;
	@Size(min=3, max=150, message="O nome do servi�o deve ter no minimo 3 caracteres e no m�ximo 150 caracteres")
	private String nome;
	private boolean desativado;
	@Transient
	private boolean selecionado;
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servico other = (Servico) obj;
		if (idServico == null) {
			if (other.idServico != null)
				return false;
		} else if (!idServico.equals(other.idServico))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idServico == null) ? 0 : idServico.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	
	
}	
