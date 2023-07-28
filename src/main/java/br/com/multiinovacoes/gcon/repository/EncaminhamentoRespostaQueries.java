package br.com.multiinovacoes.gcon.repository;

import br.com.multiinovacoes.gcon.model.EncaminhamentoResposta;

public interface EncaminhamentoRespostaQueries {
	
	public EncaminhamentoResposta consultaEncaminhamentoResposta(Long codigoEncaminhamentoResposta);
	
	public String consultar(Long idAtendimento);

}
