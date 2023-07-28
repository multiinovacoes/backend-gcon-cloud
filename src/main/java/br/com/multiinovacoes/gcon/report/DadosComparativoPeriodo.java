package br.com.multiinovacoes.gcon.report;

import java.util.List;

public class DadosComparativoPeriodo {
	
	
	List<DadosGrafico> listaManifestacoes;
	
	List<DadosGrafico> listaNaturezaPeriodo1;
	
	List<DadosGrafico> listaNaturezaPeriodo2;
	
	
	public DadosComparativoPeriodo() {
	}


	public DadosComparativoPeriodo(List<DadosGrafico> listaManifestacoes, List<DadosGrafico> listaNaturezaPeriodo1,
			List<DadosGrafico> listaNaturezaPeriodo2) {
		super();
		this.listaManifestacoes = listaManifestacoes;
		this.listaNaturezaPeriodo1 = listaNaturezaPeriodo1;
		this.listaNaturezaPeriodo2 = listaNaturezaPeriodo2;
	}


	public List<DadosGrafico> getListaManifestacoes() {
		return listaManifestacoes;
	}


	public void setListaManifestacoes(List<DadosGrafico> listaManifestacoes) {
		this.listaManifestacoes = listaManifestacoes;
	}


	public List<DadosGrafico> getListaNaturezaPeriodo1() {
		return listaNaturezaPeriodo1;
	}


	public void setListaNaturezaPeriodo1(List<DadosGrafico> listaNaturezaPeriodo1) {
		this.listaNaturezaPeriodo1 = listaNaturezaPeriodo1;
	}


	public List<DadosGrafico> getListaNaturezaPeriodo2() {
		return listaNaturezaPeriodo2;
	}


	public void setListaNaturezaPeriodo2(List<DadosGrafico> listaNaturezaPeriodo2) {
		this.listaNaturezaPeriodo2 = listaNaturezaPeriodo2;
	}



	

}
