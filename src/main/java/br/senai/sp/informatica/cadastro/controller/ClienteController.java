package br.senai.sp.informatica.cadastro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.informatica.cadastro.model.Cliente;
import br.senai.sp.informatica.cadastro.service.ClienteService;

@RestController
@RequestMapping("/api")
public class ClienteController {
    @Autowired
	private ClienteService clienteService;

    @PostMapping("/cadastra")
public ResponseEntity<Object> cadastra(@RequestBody Cliente cliente){
    	clienteService.salvar(cliente);
    	return ResponseEntity.ok().build();
}
    @GetMapping("/listaCliente")
    public ResponseEntity<List<Cliente>> listaCliente(){
    	
    	return ResponseEntity.ok(clienteService.getClientes());
    	
    }
}
