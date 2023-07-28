package br.com.multiinovacoes.gcon.model.request;

import java.io.Serializable;

public class JwtRequest implements Serializable {

private static final long serialVersionUID = 5926468583005150707L;

private String cpf;
private String senha;

public JwtRequest()
{
}

public JwtRequest(String cpf, String senha) {
	this.setCpf(cpf);
	this.setSenha(senha);
}

public String getCpf() {
	return cpf;
}

public void setCpf(String cpf) {
	this.cpf = cpf;
}

public String getSenha() {
	return senha;
}

public void setSenha(String senha) {
	this.senha = senha;
}


}
