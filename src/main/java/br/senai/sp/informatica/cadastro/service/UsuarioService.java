package br.senai.sp.informatica.cadastro.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.sp.informatica.cadastro.model.Servico;
import br.senai.sp.informatica.cadastro.model.Usuario;
import br.senai.sp.informatica.cadastro.repo.UsuarioRepo;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepo repo;
	
	public void salvar(Usuario usuario) {
		repo.save(usuario);
	}

	
	public List<Usuario> getUsuario(){
		return repo.findAll()
				.stream().filter(usuario -> usuario.isHabilitado())
				.collect(Collectors.toList());
		
	}

	public Usuario getUsuario(String Nome) {
		return repo.getOne(Nome)
				.orElse(null);
	}

	
	
	public boolean removeUsuario(String Nome) {
		Usuario usuario = repo.getOne(Nome)
				.orElse(null);
		if(usuario != null) {
			usuario.setHabilitado(true);
			repo.save(usuario);
			return true;
		}else {
	return false;
}
}


	
}
