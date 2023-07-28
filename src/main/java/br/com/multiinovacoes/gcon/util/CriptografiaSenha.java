package br.com.multiinovacoes.gcon.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriptografiaSenha {
	
	private CriptografiaSenha() {
	}
	
	public static String getSenhaCodificada(String senha) {
		String encoded = new BCryptPasswordEncoder().encode(senha);
		return encoded;
	}

}
