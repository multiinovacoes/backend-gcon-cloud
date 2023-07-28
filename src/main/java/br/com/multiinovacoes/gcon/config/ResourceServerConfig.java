package br.com.multiinovacoes.gcon.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SuppressWarnings("deprecation")
@Profile("oauth-security")
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	 private static final String[] AUTH_WHITELIST = {
	            // -- swagger ui
	            "/v2/api-docs",
	            "/swagger-resources",
	            "/swagger-resources/**",
	            "/configuration/ui",
	            "/configuration/security",
	            "/swagger-ui.html",
	            "/webjars/**"
	            // other public endpoints of your API may be appended to this array
	    };

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors().and().authorizeRequests()
		        .antMatchers(AUTH_WHITELIST).permitAll()  
				.antMatchers("/gcon/**").permitAll()
				.antMatchers("/lai/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.csrf().disable();
	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.stateless(true);
	}
	
	@Bean
	public MethodSecurityExpressionHandler createExpressionHandler() {
		return new OAuth2MethodSecurityExpressionHandler();
	}
	
	@Bean
	public CorsFilter corsFilter() {
		
		CorsConfiguration config = new CorsConfiguration();
		
		config.setAllowCredentials(true);
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		config.setMaxAge(3600L);
		config.setAllowedOrigins(Arrays.asList("https://portalouvidoria.com.br:8501", "https://portalouvidoria.com.br:8502", "https://portalouvidoria.com.br:8503", "https://portalouvidoria.com.br:8507", 
				"https://portalouvidoria.com.br:8506", "https://portalouvidoria.com.br:8508", "https://portalouvidoria.com.br:8509", "https://portalouvidoria.com.br:8515", "https://portalouvidoria.com.br:8516", "http://localhost:4200", "http://localhost:8500"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", config);
	    
	    return new CorsFilter(source);
	    
		
	}
}
