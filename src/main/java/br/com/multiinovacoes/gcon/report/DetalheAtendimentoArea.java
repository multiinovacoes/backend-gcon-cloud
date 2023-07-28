package br.com.multiinovacoes.gcon.report;

public class DetalheAtendimentoArea {
	
	private Long id;
	
	private String protocolo;
	
	private String numero;
	
	private String ano;
	
	private String area;
	
	private String status;
	
	private String assunto;
	
	private String natureza;
	
	private String data;
	
	private String dataConclusao;
	
	private String origem;
	
	private String prioridade;
	
	private String tipoManifestante;
	
	private String bairro;
	
	private String descricaoFatos;
	
	private String observacoes;
	
	
public DetalheAtendimentoArea(
	Long id,
	String protocolo,
	String numero,
	String ano,
	String area,
	String status,
	String assunto,
	String natureza,
	String data,
	String dataConclusao,
	String descricaoFatos,
	String observacoes
	) {
	this.id = id;
	this.protocolo = protocolo;
	this.numero = numero;
	this.ano = ano;
	this.area = area;
	this.status = status;
	this.assunto = assunto;
	this.natureza = natureza;
	this.data = data;
	this.dataConclusao = dataConclusao;
	this.descricaoFatos =descricaoFatos;
	this.observacoes = observacoes;
}

	public DetalheAtendimentoArea(
			String protocolo,
			String data,
			String dataConclusao,
			String assunto,
			String natureza,
			String area,
			String origem,
			String prioridade,
			String tipoManifestante,
			String bairro
			) {
			this.protocolo = protocolo;
			this.data = data;
			this.dataConclusao = dataConclusao;
			this.assunto = assunto;
			this.natureza = natureza;
			this.area = area;
			this.origem = origem;
			this.prioridade = prioridade;
			this.tipoManifestante = tipoManifestante;
			this.bairro = bairro;
		}
	

	public String getDescricaoFatos() {
		return descricaoFatos;
	}

	public void setDescricaoFatos(String descricaoFatos) {
		this.descricaoFatos = descricaoFatos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getNatureza() {
		return natureza;
	}

	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(String dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getTipoManifestante() {
		return tipoManifestante;
	}

	public void setTipoManifestante(String tipoManifestante) {
		this.tipoManifestante = tipoManifestante;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	


}
