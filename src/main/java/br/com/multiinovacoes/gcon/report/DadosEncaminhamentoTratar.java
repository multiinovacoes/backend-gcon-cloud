package br.com.multiinovacoes.gcon.report;

public class DadosEncaminhamentoTratar {
	
	private Long id;
	
	private String protocolo;

	private String data;

	private String assunto;
	
	private Long codigoEncaminhamento;
	
	private String dataPrazo;
	
	private String prazoVencido;
	
	private String prioridade;
	
	private String diasVencido;
	
	
public DadosEncaminhamentoTratar(
	Long id,
	String protocolo,
	String data,
	String assunto,
	Long codigoEncaminhamento,
	String dataPrazo,
    String prazoVencido,
    String prioridade,
    String diasVencido
	) {
	this.id = id;
	this.protocolo = protocolo;
	this.data = data;
	this.assunto = assunto;
	this.codigoEncaminhamento = codigoEncaminhamento;
	this.dataPrazo = dataPrazo;
    this.prazoVencido = prazoVencido;
    this.prioridade = prioridade;
    this.diasVencido = diasVencido;

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

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Long getCodigoEncaminhamento() {
		return codigoEncaminhamento;
	}

	public void setCodigoEncaminhamento(Long codigoEncaminhamento) {
		this.codigoEncaminhamento = codigoEncaminhamento;
	}

	public String getDataPrazo() {
		return dataPrazo;
	}

	public void setDataPrazo(String dataPrazo) {
		this.dataPrazo = dataPrazo;
	}

	public String getPrazoVencido() {
		return prazoVencido;
	}

	public void setPrazoVencido(String prazoVencido) {
		this.prazoVencido = prazoVencido;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getDiasVencido() {
		return diasVencido;
	}

	public void setDiasVencido(String diasVencido) {
		this.diasVencido = diasVencido;
	}


	

	
}
