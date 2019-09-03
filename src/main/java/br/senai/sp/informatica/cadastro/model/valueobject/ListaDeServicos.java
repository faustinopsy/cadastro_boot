package br.senai.sp.informatica.cadastro.model.valueobject;

import br.senai.sp.informatica.cadastro.model.Servico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaDeServicos {
	private int idCliente;
	private Servico[] servicos;
}
