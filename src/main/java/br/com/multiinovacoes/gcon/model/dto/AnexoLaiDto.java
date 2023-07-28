package br.com.multiinovacoes.gcon.model.dto;

import br.com.multiinovacoes.gcon.enums.ResponsavelAnexoEnum;
import io.swagger.annotations.ApiModelProperty;


public class AnexoLaiDto  {

	@ApiModelProperty(value = "Id do anexo")	
	private Long id;
	
	@ApiModelProperty(value = "Id do Atendimento")	
	private Long atendimento;
	
	@ApiModelProperty(value = "Nome do arquivo anexo")
	private String nomeArquivo;
	
	private Integer responsavel;
	
	private String nomeResponsavel;

	public String getNomeResponsavel() {
		if (responsavel != null) {
			nomeResponsavel = ResponsavelAnexoEnum.pegarDescricao(responsavel).getDescricao();
		}
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public Integer getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Integer responsavel) {
		this.responsavel = responsavel;
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
		AnexoLaiDto other = (AnexoLaiDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
