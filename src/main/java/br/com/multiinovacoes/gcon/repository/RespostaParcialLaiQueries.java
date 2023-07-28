package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import br.com.multiinovacoes.gcon.model.RespostaParcialLai;

public interface RespostaParcialLaiQueries {
	
	public List<RespostaParcialLai> consultaAtendimento(Long codigoAtendimento);
	
	public RespostaParcialLai consultaResposta(Long codigoResposta);
	
	public String consultar(String campo, Long id);


}
