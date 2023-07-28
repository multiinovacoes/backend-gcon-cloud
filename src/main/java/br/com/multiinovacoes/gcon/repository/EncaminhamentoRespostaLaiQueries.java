package br.com.multiinovacoes.gcon.repository;

import br.com.multiinovacoes.gcon.model.EncaminhamentoRespostaLai;

public interface EncaminhamentoRespostaLaiQueries {
	
	public EncaminhamentoRespostaLai consultaEncaminhamentoResposta(Long codigoEncaminhamentoResposta);
	
	public String consultar(Long idAtendimento);

}
