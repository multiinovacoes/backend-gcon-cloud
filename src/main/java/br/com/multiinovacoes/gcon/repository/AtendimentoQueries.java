package br.com.multiinovacoes.gcon.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.multiinovacoes.gcon.graphics.StackedColumn;
import br.com.multiinovacoes.gcon.model.Atendimento;
import br.com.multiinovacoes.gcon.model.dto.HistoricoUsuarioDto;
import br.com.multiinovacoes.gcon.model.filter.AtendimentoFilter;
import br.com.multiinovacoes.gcon.report.AtendimentoNatureza;
import br.com.multiinovacoes.gcon.report.DadosGrafico;
import br.com.multiinovacoes.gcon.report.DetalheAtendimentoArea;
import br.com.multiinovacoes.gcon.report.RelatorioPeriodo;

public interface AtendimentoQueries {
	
	public Page<Atendimento> filtrar(Long orgao, AtendimentoFilter filtro, Pageable page);
	
	public String consultar(String campo, Long id, Long orgao);
	
	public Atendimento consultaAtendimento(Long codigoAtendimento);
	
	public Atendimento pesquisarNumeroProtocolo(String numeroProtocolo, Long orgao);
	
	public List<AtendimentoNatureza> getQtdAtendimentosNatureza(Long orgao, Integer ano, Integer totalAtendimentos);
	
	public List<AtendimentoNatureza> getQtdAtendimentosNaturezaDashboard(Long orgao, Integer mes, Integer ano);
	
	public List<AtendimentoNatureza> getQtdPainelPesquisaSatisfacao(Long orgao, Integer ano);
	
	public List<AtendimentoNatureza> getQtdPainelOrigemManifestacao(Long orgao, Integer ano);
	
	public List<AtendimentoNatureza> getQtdAtendimentosSecretaria(Long orgao, Integer status, Integer mes, Integer ano);
	
	public StackedColumn getQtdAtendimentosSecretariaNatureza(Long orgao, Integer status, Integer mes, Integer ano);
	
	public List<AtendimentoNatureza> getQtdAtendimentosNatureza(Long orgao, Integer status, Integer mes, Integer ano, Long area, Long natureza);
	
	public List<AtendimentoNatureza> getQtdAtendimentosAssunto(Long orgao, Integer status, Integer ano);
	
	public List<DadosGrafico> getQtdAtendimentosResolutividade(Long orgao, Integer ano);
	
	public List<DadosGrafico> getQtdAtendimentos(Long orgao, Integer ano);
	
	public List<DetalheAtendimentoArea> getAtendimentoArea(Long orgao, Date dataInicial, Date dataFinal, String area, String status);
	
	public List<DetalheAtendimentoArea> getAtendimentoArea(Long orgao, Date dataInicial, Date dataFinal, String area);
	
	public List<RelatorioPeriodo> getAtendimentoPeriodo(Long orgao, Date dataInicial, Date dataFinal);
	
	public Page<Atendimento> consultaNovasManifestacoes(Long orgao, Pageable page, Integer totalRegistros);
	
	public Page<Atendimento> consultaCanalDenuncias(Long orgao, Pageable page, Integer totalRegistros);
	
	public Page<Atendimento> consultaManifestacoesVencidas(Long orgao, Pageable page, Integer totalRegistros);
	
	public Page<Atendimento> consultaManifestacoesVencer(Long orgao, Integer diasVencer, Pageable page, Integer totalRegistros);
	
	public Integer qtdHistoricoUsuario(Long codigoOrgao, Long tipoDocumento, String documento, String email, Long numero);
	
	public List<HistoricoUsuarioDto> historicoUsuario(Long codigoOrgao, Long tipoDocumento, String documento, String email, Long numero);


}
