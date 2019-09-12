package br.senai.sp.informatica.cadastro;

import javax.swing.JOptionPane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CadastroBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroBootApplication.class, args);
		
		//String senha =JOptionPane.showInputDialog("Informe a senha");
		//System.out.println(new BCryptPasswordEncoder().encode(senha));
	}

}
