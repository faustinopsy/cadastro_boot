package br.senai.sp.informatica.cadastro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import br.senai.sp.informatica.cadastro.model.validacao.Senha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class Usuario {
	@Id
	@Column(name="username")
	@Size(min=3, max=150, message="O nome deve ter no minimo 3 caracteres e no máximo 150 caracteres")
	private String nome;
	@Senha(message="A senha deve ter 1 numero e 1 letra maisculas e 1 simbolo")
	@Column(name="password")
	private String senha;
	@Transient
	private String old_nome;
	@Column(name="enabled")
	private boolean habilitado;
	public Usuario orElse(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean isAdministrador() {
		// TODO Auto-generated method stub
		return false;
	}
}
