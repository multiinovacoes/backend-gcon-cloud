package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import br.com.multiinovacoes.gcon.model.EncaminhamentoLai;

public interface EncaminhamentoLaiQueries {
	
	public List<EncaminhamentoLai> consultaAtendimento(Long codigoAtendimento);
	
	public EncaminhamentoLai consultaEncaminhamento(Long codigoEncaminhamento);
	
	public String consultar(String campo, Long id);
	
	public List<EncaminhamentoLai> consultaEncaminhamentosRecebidos(Long orgao, Pageable pageable, Integer totalRegistros);
	
	public List<EncaminhamentoLai> consultaPedidosEncaminhadosLai(Long orgao, Pageable page, Integer totalRegistros);
	
	public List<EncaminhamentoLai> consultaPedidosEncaminhadosLaiVencidos(Long orgao, Pageable page, Integer totalRegistros);
	
	
	
	

}
