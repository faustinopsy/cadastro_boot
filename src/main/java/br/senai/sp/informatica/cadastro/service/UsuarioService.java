package br.senai.sp.informatica.cadastro.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.sp.informatica.cadastro.model.Servico;
import br.senai.sp.informatica.cadastro.model.Usuario;
import br.senai.sp.informatica.cadastro.repo.UsuarioRepo;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepo repo;
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
		if(old_usuario != null) {
			usuario.setSenha(old_usuario.getSenha());
		}
		repo.save(usuario);
	}

	
	public List<Usuario> getUsuario(){
		return repo.findAll()
				.stream().filter(usuario -> usuario.isHabilitado())
				.collect(Collectors.toList());
		
	}

	public Usuario getUsuario(String Nome) {
		return repo.findById(Nome)
				.orElse(null);
	}

	
	
	public  boolean removeUsuario(String Nome) {
		Usuario usuario = repo.findById(Nome)
				.orElse(null);
		if(usuario != null) {
			repo.delete(usuario);
			return true;
		}else {
	return false;
}
}


	
}
