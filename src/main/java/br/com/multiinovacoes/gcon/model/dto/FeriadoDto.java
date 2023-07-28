package br.com.multiinovacoes.gcon.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

public class FeriadoDto {
	
	@ApiModelProperty(value = "Id do Feriado")
	private Long id;

	@ApiModelProperty(value = "Descrição do feriado")
	private String descricao;
	
	@ApiModelProperty(value = "Status do feriado")
	private Integer status;
	
	@ApiModelProperty(value = "Orgão do feriado")
    private Long orgao;
	
	@ApiModelProperty(value = "Data do feriado")
	private LocalDate dataFeriado;
		
	@ApiModelProperty(value = "Data de criação do feriado")
	private LocalDateTime dataCriacao;
	
	@ApiModelProperty(value = "Usuário da criação do feriado")
	private Long usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
	}

	public LocalDate getDataFeriado() {
		return dataFeriado;
	}

	public void setDataFeriado(LocalDate dataFeriado) {
		this.dataFeriado = dataFeriado;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	


}
