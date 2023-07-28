package br.com.multiinovacoes.gcon.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

public class LogAtendimentoDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5312810743068344136L;
	
	
	@ApiModelProperty(value = "Data do log")
	private LocalDateTime data;
	
	@ApiModelProperty(value = "Hora do log")
	private LocalDateTime hora;

	@ApiModelProperty(value = "Descrição do usuário")
	private String descricaoUsuario;
	
	@ApiModelProperty(value = "Ação feita")
	private String acao;

	public LocalDateTime getHora() {
		return hora;
	}

	public void setHora(LocalDateTime hora) {
		this.hora = hora;
	}

	public String getDescricaoUsuario() {
		return descricaoUsuario;
	}

	public void setDescricaoUsuario(String descricaoUsuario) {
		this.descricaoUsuario = descricaoUsuario;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}


	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}



}
