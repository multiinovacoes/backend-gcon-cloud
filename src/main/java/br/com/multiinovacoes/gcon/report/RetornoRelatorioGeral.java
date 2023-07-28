package br.com.multiinovacoes.gcon.report;

import java.util.List;

public class RetornoRelatorioGeral {
	
	private List<String> colunas;
	
	private List<DadosRelatorioGeral> listaDadosGeral;
	
	public RetornoRelatorioGeral() {
		// TODO Auto-generated constructor stub
	}

	public RetornoRelatorioGeral(List<String> colunas, List<DadosRelatorioGeral> listaDadosGeral) {
		super();
		this.colunas = colunas;
		this.listaDadosGeral = listaDadosGeral;
	}

	public List<String> getColunas() {
		return colunas;
	}

	public void setColunas(List<String> colunas) {
		this.colunas = colunas;
	}

	public List<DadosRelatorioGeral> getListaDadosGeral() {
		return listaDadosGeral;
	}

	public void setListaDadosGeral(List<DadosRelatorioGeral> listaDadosGeral) {
		this.listaDadosGeral = listaDadosGeral;
	}
	
	


}
