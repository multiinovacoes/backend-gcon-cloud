package br.com.multiinovacoes.gcon.model.dto;

import java.time.LocalDate;

import br.com.multiinovacoes.gcon.enums.FormaEnvioRespostaParcialEnum;
import br.com.multiinovacoes.gcon.util.RegexHtml;
import io.swagger.annotations.ApiModelProperty;


public class RespostaParcialDto {



	@ApiModelProperty(value = "Id da resposta parcial")	
	private Long id;
	
	@ApiModelProperty(value = "Id da origem manifestação")	
	private Long atendimento;
	
	@ApiModelProperty(value = "Modelo documento da resposta parcial")	
	private Long modeloDocumento;

	@ApiModelProperty(value = "Descrição da resposta parcial")	
	private String descricao;

	@ApiModelProperty(value = "Data Resposta da resposta parcial")	
	private LocalDate dataResposta;

	@ApiModelProperty(value = "Email da resposta parcial")	
	private String emailEnviado;
	
	@ApiModelProperty(value = "Forma envio da resposta parcial")	
	private Integer formaEnvio;

	@ApiModelProperty(value = "Status da resposta parcial")	
	private Integer status;
	
	@ApiModelProperty(value = "Descrição sem HTML da resposta parcial")	
	private String descricaoRespostaHTML;

	@ApiModelProperty(value = "Cancelamento da resposta parcial")	
	private Integer cancelado;

	@ApiModelProperty(value = "Ano da resposta parcial")	
	private String anoAtendimento;

	@ApiModelProperty(value = "Código atendimento da resposta parcial")	
	private Long codigoAtendimento;

	@ApiModelProperty(value = "Órgão da resposta parcial")	
	private Long orgao;
	
	@ApiModelProperty(value = "Usuário da resposta parcial")
	private UsuarioDto usuario;
	
	@ApiModelProperty(value = "Descrição forma de envio da resposta parcial")
	private String descricaoFormaEnvio;
	
	@ApiModelProperty(value = "Data que foi encaminhado")
	private String dataFormatada;

	public String getDataFormatada() {
		return dataFormatada;
	}

	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}

	public String getDescricaoFormaEnvio() {
		if (formaEnvio != null) {
			descricaoFormaEnvio = FormaEnvioRespostaParcialEnum.pegarDescricao(formaEnvio).getDescricao();
		}
		return descricaoFormaEnvio;
	}

	public void setDescricaoFormaEnvio(String descricaoFormaEnvio) {
		this.descricaoFormaEnvio = descricaoFormaEnvio;
	}

	public UsuarioDto getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}

	public Integer getCancelado() {
		return cancelado;
	}

	public void setCancelado(Integer cancelado) {
		this.cancelado = cancelado;
	}

	public String getAnoAtendimento() {
		return anoAtendimento;
	}

	public void setAnoAtendimento(String anoAtendimento) {
		this.anoAtendimento = anoAtendimento;
	}

	public Long getCodigoAtendimento() {
		return codigoAtendimento;
	}

	public void setCodigoAtendimento(Long codigoAtendimento) {
		this.codigoAtendimento = codigoAtendimento;
	}

	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
	}

	public String getDescricaoRespostaHTML() {
		return  RegexHtml.getMather(descricao);
	}

	public void setDescricaoRespostaHTML(String descricaoRespostaHTML) {
		this.descricaoRespostaHTML = descricaoRespostaHTML;
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


	public Long getModeloDocumento() {
		return modeloDocumento;
	}


	public void setModeloDocumento(Long modeloDocumento) {
		this.modeloDocumento = modeloDocumento;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEmailEnviado() {
		return emailEnviado;
	}

	public void setEmailEnviado(String emailEnviado) {
		this.emailEnviado = emailEnviado;
	}

	public Integer getStatus() {
		return status;
	}
	
	

	public LocalDate getDataResposta() {
		return dataResposta;
	}


	public void setDataResposta(LocalDate dataResposta) {
		this.dataResposta = dataResposta;
	}


	public Integer getFormaEnvio() {
		return formaEnvio;
	}


	public void setFormaEnvio(Integer formaEnvio) {
		this.formaEnvio = formaEnvio;
	}



	public void setStatus(Integer status) {
		this.status = status;
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
		RespostaParcialDto other = (RespostaParcialDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
