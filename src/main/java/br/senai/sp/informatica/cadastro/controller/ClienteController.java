package br.senai.sp.informatica.cadastro.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
import br.senai.sp.informatica.cadastro.model.valueobject.ListaDeServicos;
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
public ResponseEntity<Object> cadastra(@RequestBody @Valid Cliente cliente, BindingResult result){
    	if(result.hasErrors()) {
    		return ResponseEntity.unprocessableEntity().build();
    	}else {
    	
    	clienteService.salvar(cliente);
    	return ResponseEntity.ok().build();
    	}
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
    
    @PostMapping("/selecionaServico")
    public ResponseEntity<Object> selecionaServico(@RequestBody ListaDeServicos lista){
    	
    	Cliente cliente =clienteService.getCliente(lista.getIdCliente());
    	
    	
    	if(cliente != null) {
    		if(cliente.getServicos()==null)
    			cliente.setServicos(new ArrayList<>());
    		
    		List<Servico> aDeletar = cliente.getServicos()
.stream().filter(servicoDoCliente -> !Arrays.stream(lista.getServicos())
		         .filter(servicosEnviados -> 
		         servicosEnviados.getIdServico() == servicoDoCliente.getIdServico())
		         .findFirst().get().isSelecionado())
				
				.collect(Collectors.toList());
			
    		aDeletar.stream().forEach(servico -> 
    		cliente.getServicos().removeIf(servicoDoCliente -> 
    		servicoDoCliente.getIdServico()==servico.getIdServico())); 
    		
    		
    		List<Servico> aIncluir = Arrays.stream(lista.getServicos())
    				.filter(Servico::isSelecionado)
    				.filter(servicoSelecionado -> !cliente.getServicos()
    						.contains(servicoSelecionado))
    				.collect(Collectors.toList());
    		
    		aIncluir.stream().forEach(servico -> cliente.getServicos().add(servico));
    		clienteService.salvar(cliente);
    		
    		
    		return ResponseEntity.ok().build();
    	}else {
    		
    		
    		return ResponseEntity.unprocessableEntity().build();
    	}
    	
    	
    }
    
}
