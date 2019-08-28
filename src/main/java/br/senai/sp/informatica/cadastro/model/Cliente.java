package br.senai.sp.informatica.cadastro.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCliente;
	private String nome;
	private String endereco;
	private String telefone;
	private String email;
	private boolean desativado;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "ServicosPrestados",
	joinColumns = { @JoinColumn(name="idCliente")},
	inverseJoinColumns = { @JoinColumn(name="idServico")})
	private List<Servico> servicos;
	
}
