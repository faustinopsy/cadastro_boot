package br.senai.sp.informatica.cadastro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class AppConfig {
	@Autowired
	private Environment environmente;
	
	@Bean(name = "DataSource")
	public DriverManagerDataSource datasource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environmente.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(environmente.getProperty("spring.datasource.url"));
		dataSource.setUsername(environmente.getProperty("spring.datasource.username"));
		dataSource.setPassword(environmente.getProperty("spring.datasource.password"));
		return dataSource;
	}
	
}
