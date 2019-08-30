package br.senai.sp.informatica.cadastro.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.sp.informatica.cadastro.model.Cliente;
import br.senai.sp.informatica.cadastro.repo.ClienteRepo;

@Service
public class ClienteService {
@Autowired
	private ClienteRepo repo;

public void salvar(Cliente cliente) {
	repo.save(cliente);
}

public List<Cliente> getClientes(){
	return repo.findAll()
			.stream().filter(cliente -> !cliente.isDesativado())
			.collect(Collectors.toList());
	
}

public Cliente getCliente(int idCliente) {
	return repo.findById(idCliente)
			.orElse(null);
}

public boolean removeCliente(int[] lista) {
	Arrays.stream(lista).forEach(idCliente -> {
		Cliente cliente = repo.findById(idCliente)
				.orElse(null);
		if(cliente != null) {
			cliente.setDesativado(true);
			repo.save(cliente);
		}
	});
	return true;
}



}
