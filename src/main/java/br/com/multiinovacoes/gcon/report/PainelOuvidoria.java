package br.com.multiinovacoes.gcon.report;

public class PainelOuvidoria {
	
	private Integer totalAtendimentos;
	
	private Integer totalAtendimentosResolvidos;
	
	private Integer totalAtendimentosTramitacao;
	
	private Double percentualResolvido;
	
	private Double percentualTramitacao;
	
	public PainelOuvidoria() {
	}

	public PainelOuvidoria(Integer totalAtendimentos, Integer totalAtendimentosResolvidos, Integer totalAtendimentosTramitacao, Double percentualResolvido,
			Double percentualTramitacao) {
		super();
		this.totalAtendimentos = totalAtendimentos;
		this.totalAtendimentosResolvidos = totalAtendimentosResolvidos;
		this.totalAtendimentosTramitacao = totalAtendimentosTramitacao;
		this.percentualResolvido = percentualResolvido;
		this.percentualTramitacao = percentualTramitacao;
	}

	public Integer getTotalAtendimentos() {
		return totalAtendimentos;
	}

	public void setTotalAtendimentos(Integer totalAtendimentos) {
		this.totalAtendimentos = totalAtendimentos;
	}

	public Integer getTotalAtendimentosResolvidos() {
		return totalAtendimentosResolvidos;
	}

	public void setTotalAtendimentosResolvidos(Integer totalAtendimentosResolvidos) {
		this.totalAtendimentosResolvidos = totalAtendimentosResolvidos;
	}

	public Integer getTotalAtendimentosTramitacao() {
		return totalAtendimentosTramitacao;
	}

	public void setTotalAtendimentosTramitacao(Integer totalAtendimentosTramitacao) {
		this.totalAtendimentosTramitacao = totalAtendimentosTramitacao;
	}

	public Double getPercentualResolvido() {
		return percentualResolvido;
	}

	public void setPercentualResolvido(Double percentualResolvido) {
		this.percentualResolvido = percentualResolvido;
	}

	public Double getPercentualTramitacao() {
		return percentualTramitacao;
	}

	public void setPercentualTramitacao(Double percentualTramitacao) {
		this.percentualTramitacao = percentualTramitacao;
	}
	
	

}
