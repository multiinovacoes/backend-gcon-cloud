package br.com.multiinovacoes.gcon;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import br.com.multiinovacoes.gcon.config.property.GconProperty;


@SpringBootApplication
@EnableConfigurationProperties(GconProperty.class)
public class ServletInitializer extends SpringBootServletInitializer {

	
	public static void main(String[] args) {
		SpringApplication.run(ServletInitializer.class, args);
    }

@Override
protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(ServletInitializer.class);
     }

@Override
public void onStartup(ServletContext servletContext) throws ServletException{
    super.onStartup(servletContext);

     }
	
	

}
