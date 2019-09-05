package br.senai.sp.informatica.cadastro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.informatica.cadastro.model.Cliente;
import br.senai.sp.informatica.cadastro.model.Usuario;
import br.senai.sp.informatica.cadastro.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	 @PostMapping("/salvaUsuario")
	 public ResponseEntity<Object> cadastra(@RequestBody @Valid Usuario usuario, BindingResult result){
	     	if(result.hasErrors()) {
	     		return ResponseEntity.unprocessableEntity().build();
	     	}else {
	     	
	     	usuarioService.salvar(usuario);
	     	return ResponseEntity.ok().build();
	     	}
	 }
	
	 @GetMapping("/listaUsuario")
	    public ResponseEntity<List<Usuario>> listaUsuario(){
	    	
	    	return ResponseEntity.ok(usuarioService.getUsuario());
	    	
	    }
	 
	 @PostMapping("/removeServico/{Nome}")
	    public ResponseEntity<Object> removeUsuario(@PathVariable("Nome") String Nome){
		 //if(UsuarioService.removeUsuario(Nome)) {
	    		return ResponseEntity.ok().build();
	    			//	}else {
	    			//		return ResponseEntity.unprocessableEntity().build();
	    			//	}
	      
	 }
}
