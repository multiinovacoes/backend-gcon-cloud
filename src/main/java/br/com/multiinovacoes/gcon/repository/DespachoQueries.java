package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import br.com.multiinovacoes.gcon.model.Atendimento;
import br.com.multiinovacoes.gcon.model.Despacho;

public interface DespachoQueries {
	
	public List<Despacho> consultaAtendimento(Atendimento atendimento);
	
	public Despacho consultaDespacho(Long codigoDespacho);
	
	public String consultar(String campo, Long id);


}
