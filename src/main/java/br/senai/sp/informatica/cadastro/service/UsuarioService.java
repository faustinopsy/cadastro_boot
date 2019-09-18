package br.senai.sp.informatica.cadastro.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.senai.sp.informatica.cadastro.component.SecurityFacade;
import br.senai.sp.informatica.cadastro.model.Autorizacao;
import br.senai.sp.informatica.cadastro.model.Usuario;
import br.senai.sp.informatica.cadastro.repo.AutorizacaoRepo;
import br.senai.sp.informatica.cadastro.repo.UsuarioRepo;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepo repo;
	@Autowired
	private AutorizacaoRepo auth;
	@Autowired
	private SecurityFacade security;
	/*
	 * 
	 * @echo off
start node_modules\.bin\serve -s webapp
start chrome http://localhost:5000
exit
	 */
	public void salvar(@Valid Usuario usuario) {
		Usuario old_usuario;
		
		if(!usuario.getOld_nome().equalsIgnoreCase(usuario.getNome())) {
			old_usuario=getUsuario(usuario.getOld_nome());
			removeUsuario(usuario.getOld_nome());
		}else {
			old_usuario= getUsuario(usuario.getNome());
		}
		
		auth.save(new Autorizacao(usuario.getNome(),
				usuario.isAdministrador() ?"ROLE_ADMIN" : "ROLE_USER"));
		
		if(old_usuario != null) {
			usuario.setSenha(old_usuario.getSenha());
		}else {
			usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		}
		repo.save(usuario);
	}

	
	public List<Usuario> getUsuario(){
		return repo.findAll()
				.stream().filter(usuario -> usuario.isHabilitado())
				.filter(usuario -> !usuario.getNome().equals(security.getUserName()))
				.map(usuario -> atribuiPerfil(usuario))
				.collect(Collectors.toList());
		
	}
private Usuario atribuiPerfil(Usuario usuario) {
	Autorizacao autorizacao = getAutorizacao(usuario.getNome());
	if(autorizacao !=null) {
		usuario.setAdministrador(autorizacao.getPerfil().endsWith("ADMIN"));
	}else {
		usuario.setAdministrador(false);
	}
	return usuario;
}
	public Usuario getUsuario(String Nome) {
		Usuario usuario =repo.findById(Nome).orElse(null);
		if(usuario !=null) {
			usuario= atribuiPerfil(usuario);
		}
		return usuario;
	}

	
	
	public  boolean removeUsuario(String Nome) {
		Usuario usuario = repo.findById(Nome).orElse(null);
		if(usuario != null) {
			
			Autorizacao autorizacao = getAutorizacao(Nome);
			if(autorizacao != null) auth.delete(autorizacao);
			
			repo.delete(usuario);
			return true;
		}else {
	return false;
}
}


	public Autorizacao getAutorizacao(String nome) {
		return auth.findById(nome).orElse(null);
	}

	public GrantedAuthority getAutorizacoes(String nome) {
		Autorizacao autorizacao =getAutorizacao(nome);
		
		return autorizacao != null? () -> autorizacao.getPerfil() : null;
	}

	
}
