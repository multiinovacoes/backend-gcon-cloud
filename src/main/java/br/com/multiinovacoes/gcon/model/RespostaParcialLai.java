package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.multiinovacoes.gcon.util.RegexHtml;


@Entity
@Table(name = "LAI_RESPOSTA_PARCIAL")
public class RespostaParcialLai implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2426378029381142933L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INCODIGORESPOSTA")
	private Long id;

	@Column(name = "INCODIGOMODELO")
	private Long modeloDocumento;

	@Column(name = "DESCRICAO")
	private String descricao;

	@Column(name = "DADATA")
	private LocalDate dataResposta;

	@Column(name = "VAEMAILENVIADO")
	private String emailEnviado;

	@Column(name = "INCODIGOUSUARIO")
	private Long usuario;
	
	@Column(name = "INFORMAENVIO")
	private Integer formaEnvio;

	@Column(name = "INSTATUS")
	private Integer status;

	@Column(name = "INCODIGOATENDIMENTO")
	private Long atendimento;

	@Transient
	private String descricaoRespostaHTML;
	
	@Transient
	private String descricaoFormaEnvio;
	

	public String getDescricaoFormaEnvio() {
		return descricaoFormaEnvio;
	}

	public void setDescricaoFormaEnvio(String descricaoFormaEnvio) {
		this.descricaoFormaEnvio = descricaoFormaEnvio;
	}

	public String getDescricaoRespostaHTML() {
		return RegexHtml.getMather(descricao);
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
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
		RespostaParcialLai other = (RespostaParcialLai) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
