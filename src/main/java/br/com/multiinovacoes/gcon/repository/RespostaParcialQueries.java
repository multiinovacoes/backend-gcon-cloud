package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import br.com.multiinovacoes.gcon.model.RespostaParcial;

public interface RespostaParcialQueries {
	
	public List<RespostaParcial> consultaAtendimento(Long codigoAtendimento);
	
	public RespostaParcial consultaResposta(Long codigoResposta);
	
	public String consultar(String campo, Long id);


}
