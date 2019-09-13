package br.senai.sp.informatica.cadastro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.informatica.cadastro.component.JsonError;
import br.senai.sp.informatica.cadastro.model.Autorizacao;
import br.senai.sp.informatica.cadastro.model.Cliente;
import br.senai.sp.informatica.cadastro.model.Usuario;
import br.senai.sp.informatica.cadastro.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	@Secured("ROLE_ADMIN")
	 @PostMapping("/salvaUsuario")
	 public ResponseEntity<Object> cadastra(@RequestBody @Valid Usuario usuario, BindingResult result){
	     	if(result.hasErrors()) {
	     		return ResponseEntity.unprocessableEntity()
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.body(JsonError.build(result));
	     	}else {
	     	
	     	usuarioService.salvar(usuario);
	     	return ResponseEntity.ok().build();
	     	}
	 }
	
	@Secured("ROLE_ADMIN")
	 @GetMapping("/listaUsuario")
	    public ResponseEntity<List<Usuario>> listaUsuario(){
	    	
	    	return ResponseEntity.ok(usuarioService.getUsuario());
	    	
	    }
	@Secured("ROLE_ADMIN")
	 @GetMapping("/editaUsuario/{nome}")
	    public ResponseEntity<Object> editaCliente(@PathVariable("nome") String Nome){
	    	Usuario usuario = usuarioService.getUsuario(Nome);
	    	
	    	if(usuario != null) {
	    		usuario.setOld_nome(usuario.getNome());
	    		return ResponseEntity.ok(usuario);
	    	}else {
	    		return ResponseEntity.notFound().build();
	    	}
	    }
	 
	 /*
	 @PostMapping("/removeUsuario/{nome}")
	    public ResponseEntity<Object> removeUsuario(@PathVariable("nome") String Nome){
		 if(usuarioService.removeUsuario(Nome)) {
	    		return ResponseEntity.ok().build();
	    				}else {
	    		return ResponseEntity.unprocessableEntity().build();
	    				}
	      
	 }
	 */
	@Secured("ROLE_ADMIN")
	 @PostMapping("/removeUsuario/{nome}")
	public ResponseEntity<Object> removeCliente(@PathVariable("nome") String nome) {
		if (usuarioService.removeUsuario(nome)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.unprocessableEntity().build();
		}
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping("leAutorizacoes/{nome}")
	public ResponseEntity<Autorizacao> getAutorizacoes(@PathVariable("nome") String nome){
		
		return ResponseEntity.ok(usuarioService.getAutorizacoes(nome));
	}
	
}
