package br.senai.sp.informatica.cadastro.component;

import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;

public class JsonError {

	public static String build(BindingResult result) {
		//obtem a lista de atributos com erros
		return new StringBuilder("{\n"+
				result.getFieldErrors().stream()
				.map(erro -> "\"" +erro.getField() + "\" : \""+
				erro.getDefaultMessage()+"\"")
				.collect(Collectors.joining(",\n"))
				+"\n}").toString();
		
		
	}
}
