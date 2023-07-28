package br.com.multiinovacoes.gcon.model.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;


@Api
public class AtendimentoLaiDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2426378029381142933L;
	
	
	@ApiModelProperty(value = "id do atendimento")
	private Long id;
	
	@ApiModelProperty(value = "Orgao do atendimento")
	private Long orgao;
	
	@ApiModelProperty(value = "Número do atendimento")
	private Long numeroAtendimento;

	@ApiModelProperty(value = "Número do protocolo")
	private String numeroProtocolo;
	
	@ApiModelProperty(value = "Ano de atendimento")
	private Integer anoAtendimento;
	
	@ApiModelProperty(value = "Assunto do atendimento")
	private Long assunto;

	@ApiModelProperty(value = "Nome solicitante do atendimento")
	private String nomeSolicitante;
	
	@ApiModelProperty(value = "Tipo de documento do atendimento")
	private Long tipoDocumento;
	
	@ApiModelProperty(value = "Número do documento do atendimento")
	private String numeroDocumento;
	
	@ApiModelProperty(value = "Ud do endereço do atendimento")
	private String uf;
	
	@ApiModelProperty(value = "Municipio do endereço do atendimento")
	private String cidade;
	
	@ApiModelProperty(value = "Email do atendimento")
	private String email;
	
	@ApiModelProperty(value = "Telefone do atendimento")
	private String telefone;
	
	@ApiModelProperty(value = "Data de entrada do atendimento")
	private LocalDate dataEntrada;

	@ApiModelProperty(value = "Data de prazo do atendimento")
	private LocalDate dataPrazo;

	@ApiModelProperty(value = "Origem manifestação do atendimento")
	private Long origemContato;
	
	@ApiModelProperty(value = "Descrição do atendimento")
	private String descricao;
	
	@ApiModelProperty(value = "Natureza do atendimento")
	private Long natureza;
	
	@ApiModelProperty(value = "Sequencial Orgao")
	private Long sequencialOrgao;
	
	@ApiModelProperty(value = "Instituição")
	private Long instituicao;

	@ApiModelProperty(value = "Area")
	private Long area;
	
	@ApiModelProperty(value = "Usuário criação")
	private Long usuarioCriacao;
	
	@ApiModelProperty(value = "Tipo de Usuário")
	private Integer tipoUsuario;
	
	private LocalDateTime dataCriacao;

	private Long usuarioAlteracao;
	
	private LocalDateTime dataAlteracao;

	private Integer statusAtendimento;
	
	private Integer status;
	
	private String resposta;
	
	private String observacao;
	
	private LocalDateTime dataConclusao;
	
	private Integer satisfaz;
	
	private Integer prazoProrrogado;

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

	public Long getNumeroAtendimento() {
		return numeroAtendimento;
	}

	public void setNumeroAtendimento(Long numeroAtendimento) {
		this.numeroAtendimento = numeroAtendimento;
	}

	public String getNumeroProtocolo() {
		return numeroProtocolo;
	}

	public void setNumeroProtocolo(String numeroProtocolo) {
		this.numeroProtocolo = numeroProtocolo;
	}

	public Integer getAnoAtendimento() {
		return anoAtendimento;
	}

	public void setAnoAtendimento(Integer anoAtendimento) {
		this.anoAtendimento = anoAtendimento;
	}

	public Long getAssunto() {
		return assunto;
	}

	public void setAssunto(Long assunto) {
		this.assunto = assunto;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public Long getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(Long tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDataPrazo() {
		return dataPrazo;
	}

	public void setDataPrazo(LocalDate dataPrazo) {
		this.dataPrazo = dataPrazo;
	}

	public Long getOrigemContato() {
		return origemContato;
	}

	public void setOrigemContato(Long origemContato) {
		this.origemContato = origemContato;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getNatureza() {
		return natureza;
	}

	public void setNatureza(Long natureza) {
		this.natureza = natureza;
	}

	public Long getSequencialOrgao() {
		return sequencialOrgao;
	}

	public void setSequencialOrgao(Long sequencialOrgao) {
		this.sequencialOrgao = sequencialOrgao;
	}

	public Long getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Long instituicao) {
		this.instituicao = instituicao;
	}

	public Long getArea() {
		return area;
	}

	public void setArea(Long area) {
		this.area = area;
	}

	public Long getUsuarioCriacao() {
		return usuarioCriacao;
	}

	public void setUsuarioCriacao(Long usuarioCriacao) {
		this.usuarioCriacao = usuarioCriacao;
	}

	public Integer getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Integer tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Integer getStatusAtendimento() {
		return statusAtendimento;
	}

	public void setStatusAtendimento(Integer statusAtendimento) {
		this.statusAtendimento = statusAtendimento;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public LocalDateTime getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(LocalDateTime dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public Integer getSatisfaz() {
		return satisfaz;
	}

	public void setSatisfaz(Integer satisfaz) {
		this.satisfaz = satisfaz;
	}

	public Integer getPrazoProrrogado() {
		return prazoProrrogado;
	}

	public void setPrazoProrrogado(Integer prazoProrrogado) {
		this.prazoProrrogado = prazoProrrogado;
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
