package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "OUVIDORIA_ATENDIMENTO_RESPOSTA")
public class TipoResposta  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2426378029381142933L;
	
	@Id
	@Column(name = "INCODIGOATENDIMENTORESPOSTA")
	private Long id;

	@NotNull
	@Column(name = "INCODIGOORGAO")
	private Long orgao;

	@NotNull
	@NotBlank(message = "Descrição é obrigatório")
	@Column(name = "VADESCRICAO")
	private String descricao;

	@NotNull
	@Column(name = "SMATIVO")
	private Integer status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	

}
