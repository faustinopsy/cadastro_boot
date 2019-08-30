package br.senai.sp.informatica.cadastro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.informatica.cadastro.model.Cliente;
import br.senai.sp.informatica.cadastro.service.ClienteService;
import br.senai.sp.informatica.cadastro.service.ServicoService;

@RestController
@RequestMapping("/api")
public class ClienteController {
    @Autowired
	private ClienteService clienteService;

    @Autowired
    private ServicoService servicoService;
    
    
    
    @PostMapping("/cadastra")
public ResponseEntity<Object> cadastra(@RequestBody Cliente cliente){
    	clienteService.salvar(cliente);
    	return ResponseEntity.ok().build();
}
    @GetMapping("/listaCliente")
    public ResponseEntity<List<Cliente>> listaCliente(){
    	
    	return ResponseEntity.ok(clienteService.getClientes());
    	
    }
    @GetMapping("/editaCliente/{id}")
    public ResponseEntity<Object> editaCliente(@PathVariable("id") int idCliente){
    	Cliente cliente = clienteService.getCliente(idCliente);
    	
    	if(cliente != null) {
    		return ResponseEntity.ok(cliente);
    	}else {
    		return ResponseEntity.notFound().build();
    	}
    }
    @PostMapping("/removeCliente")
    public ResponseEntity<Object> removeCliente(@RequestBody int[] lista){
    	if(clienteService.removeCliente(lista)) {
    		return ResponseEntity.ok().build();
    				}else {
    					return ResponseEntity.unprocessableEntity().build();
    				}
    }
    
    @RequestMapping("/carregaServicos/{idCliente}")
    public ResponseEntity<Object> carregaSerrvicos(@PathVariable("idCliente")int idCliente){
    	Cliente cliente = clienteService.getCliente(idCliente);
    	
    	if(cliente !=null) {
    		return ResponseEntity.ok(servicoService.getServicos(cliente));
    	}else {
    		return ResponseEntity.notFound().build();
    	}
    	
    }
}
