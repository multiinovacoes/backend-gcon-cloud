package br.com.multiinovacoes.gcon.model.dto;

import java.util.List;

import br.com.multiinovacoes.gcon.model.RespostaSatisfacao;

public class PerguntaSatisfacaoDto {
	
	private Long id;
	
	private String descricao;
	
	private Long idResposta;
	
	public Long getIdResposta() {
		return idResposta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	public void setListaRespostaSatisfacao(List<RespostaSatisfacao> listaRespostaSatisfacao) {
		this.listaRespostaSatisfacao = listaRespostaSatisfacao;
	}



	public void setIdResposta(Long idResposta) {
		this.idResposta = idResposta;
	}

	private List<RespostaSatisfacao> listaRespostaSatisfacao;
	
	public List<RespostaSatisfacao> getListaRespostaSatisfacao() {
		return listaRespostaSatisfacao;
	}


}
