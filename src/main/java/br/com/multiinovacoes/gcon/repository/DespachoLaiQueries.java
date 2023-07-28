package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import br.com.multiinovacoes.gcon.model.AtendimentoLai;
import br.com.multiinovacoes.gcon.model.DespachoLai;

public interface DespachoLaiQueries {
	
	public List<DespachoLai> consultaAtendimentoLai(AtendimentoLai atendimentoLai);
	
	public DespachoLai consultaDespacho(Long codigoDespacho);
	
	public String consultar(String campo, Long id);


}
