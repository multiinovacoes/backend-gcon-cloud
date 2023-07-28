package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "LAI_SETOR")
public class SetorLai implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2426378029381142933L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODIGO_SETOR")
	private Long id;

	@NotBlank(message = "Descrição é obrigatório")
	@Column(name = "DESCRICAO")
	private String descricao;

	@Column(name = "STATUS")
	private Integer status;

	@Column(name = "CODIGO_ORGAO")
	private Long orgao;

	@Column(name = "SIGLA")
	private String sigla;

	@Column(name = "VANOMEDIRIGENTE")
	private String nomeDirigente;

	@Column(name = "VAEMAILDIRIGENTE")
	private String emailDirigente;

	@NotBlank(message = "Email do setor é obrigatório")
	@Column(name = "EMAIL")
	private String emailSetor;

	@Column(name = "DATA_CRIACAO")
	private LocalDateTime dataCriacao;
	
	@Column(name = "TIPO_ACESSO_PERFIL")
	private Integer tipoAcessoPerfil;

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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNomeDirigente() {
		return nomeDirigente;
	}

	public void setNomeDirigente(String nomeDirigente) {
		this.nomeDirigente = nomeDirigente;
	}

	public String getEmailDirigente() {
		return emailDirigente;
	}

	public void setEmailDirigente(String emailDirigente) {
		this.emailDirigente = emailDirigente;
	}

	public String getEmailSetor() {
		return emailSetor;
	}

	public void setEmailSetor(String emailSetor) {
		this.emailSetor = emailSetor;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Integer getTipoAcessoPerfil() {
		return tipoAcessoPerfil;
	}

	public void setTipoAcessoPerfil(Integer tipoAcessoPerfil) {
		this.tipoAcessoPerfil = tipoAcessoPerfil;
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
		SetorLai other = (SetorLai) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
