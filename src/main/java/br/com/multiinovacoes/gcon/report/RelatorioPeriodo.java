package br.com.multiinovacoes.gcon.report;

public class RelatorioPeriodo {
	
	private Long id;
	
	private String protocolo;

	private String data;

	private String assunto;

	private String natureza;

	private String status;
	
	private String dataConclusao;
	
	private String dataPrazo;
	
	
public RelatorioPeriodo(
	Long id,
	String protocolo,
	String data,
	String assunto,
	String natureza,
	String status,
	String dataConclusao,
	String dataPrazo
	) {
	this.id = id;
	this.protocolo = protocolo;
	this.data = data;
	this.assunto = assunto;
	this.natureza = natureza;
	this.status = status;
	this.dataConclusao = dataConclusao;
	this.dataPrazo =dataPrazo;
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
	public String getDataPrazo() {
		return dataPrazo;
	}

	public void setDataPrazo(String dataPrazo) {
		this.dataPrazo = dataPrazo;
	}
	

}
