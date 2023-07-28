package br.com.multiinovacoes.gcon.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.multiinovacoes.gcon.model.AtendimentoLai;
import br.com.multiinovacoes.gcon.model.filter.AtendimentoFilter;

public interface AtendimentoLaiQueries {
	
	public Page<AtendimentoLai> consultaNovosPedidosLai(Long orgao, Pageable page, Integer totalRegistros);
	
	public Page<AtendimentoLai> consultaAtendimentosLaiRecursoAberto(Long orgao, Pageable page, Integer totalRegistros);
	
	public Page<AtendimentoLai> consultaPedidosVencidosLai(Long orgao, Pageable page, Integer totalRegistros);
	
	public AtendimentoLai consultaAtendimentolai(Long codigoAtendimento);
	
	public Page<AtendimentoLai> filtrar(Long orgao, AtendimentoFilter filtro, Pageable page);
	
	public Page<AtendimentoLai> consultaAtendimentosLaiRecursoVencido(Long orgao, Pageable page, Integer totalRegistros);

}
