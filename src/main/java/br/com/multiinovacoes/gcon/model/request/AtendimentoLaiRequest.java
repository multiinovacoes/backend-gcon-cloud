package br.com.multiinovacoes.gcon.model.request;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;


@Api
public class AtendimentoLaiRequest {
	

	@ApiModelProperty(value = "id do atendimento")
	private Long id;
	
	@ApiModelProperty(value = "Numero do atendimento")
	private Long numeroAtendimento;

	@ApiModelProperty(value = "Nome solicitante do atendimento")
	private String nomeSolicitante;
	
	@ApiModelProperty(value = "Assunto do endereço do atendimento")
	private Long assunto;
	
	@ApiModelProperty(value = "Tipo de documento do atendimento")
	private Long tipoDocumento;
	
	@ApiModelProperty(value = "Ano do atendimento")
	private Long anoAtendimento;
	
	@ApiModelProperty(value = "Protocolo do atendimento")
	private String numeroProtocolo;
	
	@ApiModelProperty(value = "Número do documento do atendimento")
	private String numeroDocumento;
	
	@ApiModelProperty(value = "Ud do endereço do atendimento")
	private String uf;
	
	@ApiModelProperty(value = "Cidade do endereço do atendimento")
	private String cidade;
	
	@ApiModelProperty(value = "Email do atendimento")
	private String email;
	
	@ApiModelProperty(value = "Telefone do atendimento")
	private String telefone;
	
	@ApiModelProperty(value = "Data de entrada do atendimento")
	private LocalDate dataEntrada;

	@ApiModelProperty(value = "Descrição oque do atendimento")
	private String descricao;

	@ApiModelProperty(value = "Natureza do atendimento")
	private Long natureza;
	
	@ApiModelProperty(value = "Origem contato do atendimento")
	private Long origemContato;
	
	@ApiModelProperty(value = "Orgao do atendimento")
	private Long orgao;
	
	private Long area;
	
	private Long instituicao;
	
	private Integer tipoUsuario;
	
	private Long usuarioCriacao;
	
	private LocalDateTime dataCriacao;

	private Long usuarioAlteracao;
	
	private LocalDateTime dataAlteracao;

	private LocalDate dataPrazo;
	
	private Integer statusAtendimento;
	
	private Integer status;
	
	private String resposta;
	
	private String observacao;
	
	private Timestamp dataConclusao;
	
	private Integer satisfaz;
	
	private Long sequencialOrgao;
	
	private Integer resultadoPedido;
	
	private List<Long>selectedAnexos;	

	public List<Long> getSelectedAnexos() {
		return selectedAnexos;
	}

	public void setSelectedAnexos(List<Long> selectedAnexos) {
		this.selectedAnexos = selectedAnexos;
	}

	
	public Integer getResultadoPedido() {
		return resultadoPedido;
	}

	public void setResultadoPedido(Integer resultadoPedido) {
		this.resultadoPedido = resultadoPedido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public Long getAssunto() {
		return assunto;
	}

	public void setAssunto(Long assunto) {
		this.assunto = assunto;
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

	public Long getOrigemContato() {
		return origemContato;
	}

	public void setOrigemContato(Long origemContato) {
		this.origemContato = origemContato;
	}

	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
	}

	public Long getArea() {
		return area;
	}

	public void setArea(Long area) {
		this.area = area;
	}

	public Long getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Long instituicao) {
		this.instituicao = instituicao;
	}

	public Integer getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Integer tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Long getAnoAtendimento() {
		return anoAtendimento;
	}

	public void setAnoAtendimento(Long anoAtendimento) {
		this.anoAtendimento = anoAtendimento;
	}

	public String getNumeroProtocolo() {
		return numeroProtocolo;
	}

	public void setNumeroProtocolo(String numeroProtocolo) {
		this.numeroProtocolo = numeroProtocolo;
	}

	public Long getUsuarioCriacao() {
		return usuarioCriacao;
	}

	public void setUsuarioCriacao(Long usuarioCriacao) {
		this.usuarioCriacao = usuarioCriacao;
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

	public LocalDate getDataPrazo() {
		return dataPrazo;
	}

	public void setDataPrazo(LocalDate dataPrazo) {
		this.dataPrazo = dataPrazo;
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

	public Timestamp getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Timestamp dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public Integer getSatisfaz() {
		return satisfaz;
	}

	public void setSatisfaz(Integer satisfaz) {
		this.satisfaz = satisfaz;
	}

	public Long getSequencialOrgao() {
		return sequencialOrgao;
	}

	public void setSequencialOrgao(Long sequencialOrgao) {
		this.sequencialOrgao = sequencialOrgao;
	}

	public Long getNumeroAtendimento() {
		return numeroAtendimento;
	}

	public void setNumeroAtendimento(Long numeroAtendimento) {
		this.numeroAtendimento = numeroAtendimento;
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
