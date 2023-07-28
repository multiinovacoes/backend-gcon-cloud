package br.com.multiinovacoes.gcon.report;

import java.util.List;

public class DadosRelatorioUsuario {
	
	private String nome;

	private List<RelatorioUsuario> lista;
	
	public DadosRelatorioUsuario() {
	}

	public DadosRelatorioUsuario(String nome, List<RelatorioUsuario> lista) {
		super();
		this.nome = nome;
		this.lista = lista;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<RelatorioUsuario> getLista() {
		return lista;
	}

	public void setLista(List<RelatorioUsuario> lista) {
		this.lista = lista;
	}
	
	
	
	

}
