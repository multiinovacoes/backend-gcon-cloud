package br.com.multiinovacoes.gcon.report;

import java.util.List;

public class AtendimentoArea {
	
	
	private String area;
	
	
	private List<DetalheAtendimentoArea> listaDetalhe;
	
	
	
	public AtendimentoArea(
		String area,
		List<DetalheAtendimentoArea> listaDetalhe
		) {
		this.area = area;
		this.listaDetalhe = listaDetalhe;
	}


	public String getArea() {
		return area;
	}



	public void setArea(String area) {
		this.area = area;
	}




	public List<DetalheAtendimentoArea> getListaDetalhe() {
		return listaDetalhe;
	}




	public void setListaDetalhe(List<DetalheAtendimentoArea> listaDetalhe) {
		this.listaDetalhe = listaDetalhe;
	}

	
	

}
