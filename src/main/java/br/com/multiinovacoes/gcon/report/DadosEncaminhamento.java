package br.com.multiinovacoes.gcon.report;

public class DadosEncaminhamento {
	
	private Long id;
	
	private String protocolo;

	private String data;

	private String assunto;
	
	private Long codigoEncaminhamento;
	
	private String dataPrazo;
	
	private String qtdCobrancas;
	
	private String dataUltimoDespacho;
	
	private String prazoVencido;
	
	
public DadosEncaminhamento(
	Long id,
	String protocolo,
	String data,
	String assunto,
	Long codigoEncaminhamento,
	String dataPrazo,
	String qtdCobrancas,
    String dataUltimoDespacho,
    String prazoVencido
	) {
	this.id = id;
	this.protocolo = protocolo;
	this.data = data;
	this.assunto = assunto;
	this.codigoEncaminhamento = codigoEncaminhamento;
	this.dataPrazo = dataPrazo;
	this.qtdCobrancas = qtdCobrancas;
    this.dataUltimoDespacho = dataUltimoDespacho;
    this.prazoVencido = prazoVencido;

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

	public String getQtdCobrancas() {
		return qtdCobrancas;
	}

	public void setQtdCobrancas(String qtdCobrancas) {
		this.qtdCobrancas = qtdCobrancas;
	}

	public String getDataUltimoDespacho() {
		return dataUltimoDespacho;
	}

	public void setDataUltimoDespacho(String dataUltimoDespacho) {
		this.dataUltimoDespacho = dataUltimoDespacho;
	}

	public String getPrazoVencido() {
		return prazoVencido;
	}

	public void setPrazoVencido(String prazoVencido) {
		this.prazoVencido = prazoVencido;
	}


	

	
}
