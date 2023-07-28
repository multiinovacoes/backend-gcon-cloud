package br.com.multiinovacoes.gcon.model.request;

import io.swagger.annotations.ApiModelProperty;


public class AnexoRequest  {
	
	@ApiModelProperty(value = "Id do atendimento")
	private Long codigoAtendimento;

	@ApiModelProperty(value = "Id do anexo")	
	private Long id;
	
	@ApiModelProperty(value = "Atendimento do anexo")	
	private Long atendimento;
	
	@ApiModelProperty(value = "Nome do arquivo anexo")
	private String nomeArquivo;

	public Long getCodigoAtendimento() {
		return codigoAtendimento;
	}

	public void setCodigoAtendimento(Long codigoAtendimento) {
		this.codigoAtendimento = codigoAtendimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Long atendimento) {
		this.atendimento = atendimento;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		AnexoRequest other = (AnexoRequest) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
