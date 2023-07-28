package br.com.multiinovacoes.gcon.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.request.RelatorioGeralRequest;
import br.com.multiinovacoes.gcon.report.DadosComparativoPeriodo;
import br.com.multiinovacoes.gcon.report.DadosGrafico;
import br.com.multiinovacoes.gcon.report.DadosRelatorio;
import br.com.multiinovacoes.gcon.report.DadosRelatorioUsuario;
import br.com.multiinovacoes.gcon.report.RetornoRelatorioGeral;

@Repository
public interface RelatorioQueries {
	
	public List<DadosGrafico> getRelatorioNatureza(Long orgao, Date dataInicial, Date dataFinal);
	
	public List<DadosGrafico> getRelatorioAssunto(Long orgao, Date dataInicial, Date dataFinal);
	
	public List<DadosGrafico> getRelatorioPriorizacao(Long orgao, Date dataInicial, Date dataFinal);
	
	public List<DadosGrafico> getRelatorioOrigem(Long orgao, Date dataInicial, Date dataFinal);
	
	public List<DadosGrafico> getRelatorioAreaAssunto(Long orgao, Date dataInicial, Date dataFinal);
	
	public List<DadosGrafico> getRelatorioTipoManifestante(Long orgao, Date dataInicial, Date dataFinal);
	
	public DadosRelatorio getRelatorioSecretaria(Long orgao, Date dataInicial, Date dataFinal, Long area);
	
	public DadosRelatorioUsuario getRelatorioUsuario(Long orgao, Date dataInicial, Date dataFinal, Long usuario);
	
	public List<DadosGrafico> getRelatorioMediaResposta(Long orgao, Date dataInicial, Date dataFinal);
	
	public List<DadosGrafico> getRelatorioEficienciaOuvidoria(Long orgao, Date dataInicial, Date dataFinal);
	
	public DadosRelatorio getRelatorioProdutividadeCallCenter(Long orgao, Date dataInicial, Date dataFinal, String area);
	
	public DadosComparativoPeriodo getRelatorioComparativo(Long orgao, Date dataInicial, Date dataFinal, Date dataInicialAnterior, Date dataFinalAnterior);
	
	public RetornoRelatorioGeral getListaGeral(RelatorioGeralRequest request);

}
