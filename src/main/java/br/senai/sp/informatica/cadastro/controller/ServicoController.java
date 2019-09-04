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
import br.senai.sp.informatica.cadastro.model.Servico;
import br.senai.sp.informatica.cadastro.service.ServicoService;

@RestController
@RequestMapping("/api")
public class ServicoController {
	@Autowired
	private ServicoService servicoService;
	
	@PostMapping("/salvaServico")
	public ResponseEntity<Object> cadastra(@RequestBody @Valid Servico servico, BindingResult result){
		if(result.hasErrors()) {
    		return ResponseEntity.unprocessableEntity().build();
    	}else {
		
		servicoService.salvar(servico);
	    return ResponseEntity.ok().build();
	}
}
	@GetMapping("/listaServico")
    public ResponseEntity<List<Servico>> listaServico(){
    	
    	return ResponseEntity.ok(servicoService.getServicos());
    	
    }    
	@GetMapping("/editaServico/{id}")
    public ResponseEntity<Object> editaServico(@PathVariable("id") int idServico){
		Servico servico = servicoService.getServico(idServico);
    	
    	if(servico != null) {
    		return ResponseEntity.ok(servico);
    	}else {
    		return ResponseEntity.notFound().build();
    	}
    }
	@PostMapping("/removeServico/{idServico}")
    public ResponseEntity<Object> removeCliente(@PathVariable("idServico") int idServico){
    	if(servicoService.removeServico(idServico)) {
    		return ResponseEntity.ok().build();
    				}else {
    					return ResponseEntity.unprocessableEntity().build();
    				}
    }   
	    
}
