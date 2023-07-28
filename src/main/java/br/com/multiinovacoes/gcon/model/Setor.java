package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "GLOB_SETOR")
public class Setor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2426378029381142933L;
	
	@Id
	@Column(name = "CODIGO_SETOR")
	private Long id;

	@NotBlank(message = "Descrição é obrigatório")
	@Column(name = "DESCRICAO")
	private String descricao;

	@Column(name = "SMATIVO")
	private Integer status;

	@Column(name = "CODIGO_ORGAO")
	private Long orgao;

	@Column(name = "SIGLA")
	private String sigla;

	@Column(name = "VAFONE")
	private String fone;

	@Column(name = "VANOMEDIRIGENTE")
	private String nomeDirigente;

	@Column(name = "VAEMAILDIRIGENTE")
	private String emailDirigente;

	@Column(name = "VATRATAMENTO_FORNAL")
	private String tratamentoFormal;

	@Column(name = "VATRATAMENTO_INICIAL")
	private String tratamentoInicial;

	@NotBlank(message = "Email do setor é obrigatório")
	@Column(name = "EMAIL")
	private String emailSetor;

	@Column(name = "VAGENERO_DIRIGENTE")
	private String generoDirigente;

	@NotBlank(message = "Gênero do setor é obrigatório")
	@Column(name = "VAGENERO_ORGAO")
	private String generoSetor;

	@Column(name = "VACARGO")
	private String cargoDirigente;
	
	@Column(name = "VAENDERECO")
	private String endereco;

	@Column(name = "VACOMPLEMENTO")
	private String complemento;

	@Column(name = "VANUMERO")
	private String numero;

	@Column(name = "VABAIRRO")
	private String bairro;

	@Column(name = "VACIDADE")
	private String cidade;

	@Column(name = "VAUF")
	private String uf;

	@Column(name = "VACEP")
	private String cep;

	@Column(name = "INENCAMINHAMENTOAUTOMATICO")
	private Integer encaminhamentoAutomatico;

	@Column(name = "SMENCAMINHAMENTOINTERNO")
	private Integer encaminhamentoInterno;

	@Column(name = "DATA_CRIACAO")
	private LocalDateTime dataCriacao;
	
	@Column(name = "NOME_SUPLENTE")
	private String nomeSuplente;
	
	@Column(name = "EMAIL_SUPLENTE")
	private String emailSuplente;
	
	@Column(name = "RESPONSAVEL_SETOR")
	private Integer responsavelSetor;

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
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

	public String getTratamentoFormal() {
		return tratamentoFormal;
	}

	public void setTratamentoFormal(String tratamentoFormal) {
		this.tratamentoFormal = tratamentoFormal;
	}

	public String getEmailSetor() {
		return emailSetor;
	}

	public void setEmailSetor(String emailSetor) {
		this.emailSetor = emailSetor;
	}

	public String getGeneroDirigente() {
		return generoDirigente;
	}

	public void setGeneroDirigente(String generoDirigente) {
		this.generoDirigente = generoDirigente;
	}

	public String getGeneroSetor() {
		return generoSetor;
	}

	public void setGeneroSetor(String generoSetor) {
		this.generoSetor = generoSetor;
	}

	public String getCargoDirigente() {
		return cargoDirigente;
	}

	public void setCargoDirigente(String cargoDirigente) {
		this.cargoDirigente = cargoDirigente;
	}

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
	
	


	public String getTratamentoInicial() {
		return tratamentoInicial;
	}

	public void setTratamentoInicial(String tratamentoInicial) {
		this.tratamentoInicial = tratamentoInicial;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getEncaminhamentoAutomatico() {
		return encaminhamentoAutomatico;
	}

	public void setEncaminhamentoAutomatico(Integer encaminhamentoAutomatico) {
		this.encaminhamentoAutomatico = encaminhamentoAutomatico;
	}

	public Integer getEncaminhamentoInterno() {
		return encaminhamentoInterno;
	}

	public void setEncaminhamentoInterno(Integer encaminhamentoInterno) {
		this.encaminhamentoInterno = encaminhamentoInterno;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
	}

	public String getNomeSuplente() {
		return nomeSuplente;
	}

	public void setNomeSuplente(String nomeSuplente) {
		this.nomeSuplente = nomeSuplente;
	}

	public String getEmailSuplente() {
		return emailSuplente;
	}

	public void setEmailSuplente(String emailSuplente) {
		this.emailSuplente = emailSuplente;
	}

	public Integer getResponsavelSetor() {
		return responsavelSetor;
	}

	public void setResponsavelSetor(Integer responsavelSetor) {
		this.responsavelSetor = responsavelSetor;
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
		Setor other = (Setor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
