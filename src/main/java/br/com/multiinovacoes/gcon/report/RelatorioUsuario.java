package br.com.multiinovacoes.gcon.report;

public class RelatorioUsuario {
	
	private String id;
	
	private String protocolo;

	private String data;

	private String nomeCriacao;
	
	private String dataAlteracao;

	private String status;

	public RelatorioUsuario(String id, String protocolo, String data, String nomeCriacao, String dataAlteracao,
			String status) {
		super();
		this.id = id;
		this.protocolo = protocolo;
		this.data = data;
		this.nomeCriacao = nomeCriacao;
		this.dataAlteracao = dataAlteracao;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getNomeCriacao() {
		return nomeCriacao;
	}

	public void setNomeCriacao(String nomeCriacao) {
		this.nomeCriacao = nomeCriacao;
	}

	public String getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(String dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
