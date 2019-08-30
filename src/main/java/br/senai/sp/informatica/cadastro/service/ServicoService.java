package br.senai.sp.informatica.cadastro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.sp.informatica.cadastro.model.Cliente;
import br.senai.sp.informatica.cadastro.model.Servico;
import br.senai.sp.informatica.cadastro.repo.ServicoRepo;

@Service
public class ServicoService {
	@Autowired
	private ServicoRepo repo;
	
	public void salvar(Servico servico) {
		repo.save(servico);
	}
	public  List<Servico> getServicos(){
		return repo.findAll()
				.stream().filter(servico -> !servico.isDesativado())
				.collect(Collectors.toList());
		
	}
	public Servico getServico(int idServico) {
		return repo.findById(idServico)
				.orElse(null);
	}
	public boolean removeServico(int idServico) {
			Servico servico = repo.findById(idServico)
					.orElse(null);
			if(servico != null) {
				servico.setDesativado(true);
				repo.save(servico);
				return true;
			}else {
		return false;
	}
	}
	
	public List<Servico> getServicos(Cliente cliente){
		List<Servico> servicosDoCliente= cliente.getServicos();
		return repo.findAll().stream().filter(servico ->!servico.isDesativado())
				.map(servico -> {
					servico.setSelecionado(
							servicosDoCliente.contains(servico));
					return servico;
				}).collect(Collectors.toList());		
	}
}
