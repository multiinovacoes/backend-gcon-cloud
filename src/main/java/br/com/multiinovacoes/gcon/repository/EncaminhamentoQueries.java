package br.com.multiinovacoes.gcon.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import br.com.multiinovacoes.gcon.model.Encaminhamento;
import br.com.multiinovacoes.gcon.report.DadosEncaminhamento;
import br.com.multiinovacoes.gcon.report.DadosEncaminhamentoTratar;

public interface EncaminhamentoQueries {
	
	public List<Encaminhamento> consultaAtendimento(Long codigoAtendimento);
	
	public Encaminhamento consultaEncaminhamento(Long codigoEncaminhamento);
	
	public String consultar(String campo, Long id);
	
	public List<Encaminhamento> consultaEncaminhamentosRecebidos(Long orgao, Pageable pageable, Integer totalRegistros);
	
	public List<Encaminhamento> consultaEncaminhadosVencidos(Long orgao, Pageable page, Integer totalRegistros);
	
	public List<Encaminhamento> consultaEncaminhamentosVencer(Long orgao, Pageable pageable, Integer totalRegistros);
	
	public List<DadosEncaminhamento> encaminhamentosNaoRespondidos(Long orgao, Long setor, Date dataInicial, Date dataFinal, String prazo);
	
	public List<DadosEncaminhamentoTratar> tratarEncaminhamentos(Long orgao, Long setor);


}
