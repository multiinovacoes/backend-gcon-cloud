package br.com.multiinovacoes.gcon.model.dto;

import io.swagger.annotations.ApiModelProperty;


public class HistoricoUsuarioDto  {

	
	@ApiModelProperty(value = "Id do Atendimento")	
	private Long atendimento;
	
	@ApiModelProperty(value = "Numero do protocolo da manifestação")
	private String numeroProtocolo;
	
	@ApiModelProperty(value = "Data de entrada da manifestação")
	private String dataEntrada;
	
	@ApiModelProperty(value = "Assunto da manifestação")
	private String assunto;
	
	@ApiModelProperty(value = "Status da manifestação")
	private String status;
	
	public HistoricoUsuarioDto() {
	}

	public HistoricoUsuarioDto(Long atendimento, String numeroProtocolo, String dataEntrada, 
			String assunto, String status) {
		super();
		this.atendimento = atendimento;
		this.numeroProtocolo = numeroProtocolo;
		this.dataEntrada = dataEntrada;
		this.assunto = assunto;
		this.status = status;
	}

	public Long getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Long atendimento) {
		this.atendimento = atendimento;
	}

	public String getNumeroProtocolo() {
		return numeroProtocolo;
	}

	public void setNumeroProtocolo(String numeroProtocolo) {
		this.numeroProtocolo = numeroProtocolo;
	}

	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atendimento == null) ? 0 : atendimento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HistoricoUsuarioDto other = (HistoricoUsuarioDto) obj;
		if (atendimento == null) {
			if (other.atendimento != null)
				return false;
		} else if (!atendimento.equals(other.atendimento))
			return false;
		return true;
	}
	
	

}
