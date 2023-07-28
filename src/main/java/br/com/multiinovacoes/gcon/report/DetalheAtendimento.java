package br.com.multiinovacoes.gcon.report;

import java.util.List;

import br.com.multiinovacoes.gcon.model.Atendimento;
import br.com.multiinovacoes.gcon.model.dto.DespachoDto;
import br.com.multiinovacoes.gcon.model.dto.EncaminhamentoDto;
import br.com.multiinovacoes.gcon.model.dto.RespostaParcialDto;

public class DetalheAtendimento {
	
	private Atendimento atendimento;
	
	private String descricaoArea;
	private String descricaoNatureza;
	private String descricaoOrigem;
	private String descricaoAssunto;
	private String descricaoPriorizacao;
	private String descricaoBairroOcorrencia;
	
	private List<EncaminhamentoDto> listaEncaminhamentos;
	
	private List<DespachoDto> listaDespacho;
	
	private List<RespostaParcialDto> listaRespostaParcial;

	public DetalheAtendimento(Atendimento atendimento, String descricaoArea, String descricaoNatureza,
			String descricaoOrigem, String descricaoAssunto, String descricaoPriorizacao,
			String descricaoBairroOcorrencia, List<EncaminhamentoDto> listaEncaminhamentos,
			List<DespachoDto> listaDespacho, List<RespostaParcialDto> listaRespostaParcial) {
		super();
		this.atendimento = atendimento;
		this.descricaoArea = descricaoArea;
		this.descricaoNatureza = descricaoNatureza;
		this.descricaoOrigem = descricaoOrigem;
		this.descricaoAssunto = descricaoAssunto;
		this.descricaoPriorizacao = descricaoPriorizacao;
		this.descricaoBairroOcorrencia = descricaoBairroOcorrencia;
		this.listaEncaminhamentos = listaEncaminhamentos;
		this.listaDespacho = listaDespacho;
		this.listaRespostaParcial = listaRespostaParcial;
	}

	public String getDescricaoArea() {
		return descricaoArea;
	}

	public void setDescricaoArea(String descricaoArea) {
		this.descricaoArea = descricaoArea;
	}

	public String getDescricaoNatureza() {
		return descricaoNatureza;
	}

	public void setDescricaoNatureza(String descricaoNatureza) {
		this.descricaoNatureza = descricaoNatureza;
	}

	public String getDescricaoOrigem() {
		return descricaoOrigem;
	}

	public void setDescricaoOrigem(String descricaoOrigem) {
		this.descricaoOrigem = descricaoOrigem;
	}

	public String getDescricaoAssunto() {
		return descricaoAssunto;
	}

	public void setDescricaoAssunto(String descricaoAssunto) {
		this.descricaoAssunto = descricaoAssunto;
	}

	public String getDescricaoPriorizacao() {
		return descricaoPriorizacao;
	}

	public void setDescricaoPriorizacao(String descricaoPriorizacao) {
		this.descricaoPriorizacao = descricaoPriorizacao;
	}

	public String getDescricaoBairroOcorrencia() {
		return descricaoBairroOcorrencia;
	}

	public void setDescricaoBairroOcorrencia(String descricaoBairroOcorrencia) {
		this.descricaoBairroOcorrencia = descricaoBairroOcorrencia;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public List<EncaminhamentoDto> getListaEncaminhamentos() {
		return listaEncaminhamentos;
	}

	public void setListaEncaminhamentos(List<EncaminhamentoDto> listaEncaminhamentos) {
		this.listaEncaminhamentos = listaEncaminhamentos;
	}

	public List<DespachoDto> getListaDespacho() {
		return listaDespacho;
	}

	public void setListaDespacho(List<DespachoDto> listaDespacho) {
		this.listaDespacho = listaDespacho;
	}

	public List<RespostaParcialDto> getListaRespostaParcial() {
		return listaRespostaParcial;
	}

	public void setListaRespostaParcial(List<RespostaParcialDto> listaRespostaParcial) {
		this.listaRespostaParcial = listaRespostaParcial;
	}
	

}
