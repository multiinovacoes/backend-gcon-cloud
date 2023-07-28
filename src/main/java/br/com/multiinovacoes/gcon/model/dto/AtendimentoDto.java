package br.com.multiinovacoes.gcon.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.multiinovacoes.gcon.util.DateTools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;


@Api
public class AtendimentoDto implements Serializable{
	
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
	
	@ApiModelProperty(value = "Manter sigilo do atendimento")
	private Integer manterSigilo;
	
	@ApiModelProperty(value = "Tipo de usuário do atendimento")
	private Integer tipoUsuario;

	@ApiModelProperty(value = "Nome solicitante do atendimento")
	private String nomeSolicitante;
	
	@ApiModelProperty(value = "Tipo de documento do atendimento")
	private Long tipoDocumento;
	
	@ApiModelProperty(value = "Número do documento do atendimento")
	private String numeroDocumento;
	
	@ApiModelProperty(value = "Data de nascimento do atendimento")
	private Timestamp dataNascimento;
	
	@ApiModelProperty(value = "Estado civil do atendimento")
	private Integer estadoCivil;
	
	@ApiModelProperty(value = "Cep do endereço atendimento")
	private String cep;
	
	@ApiModelProperty(value = "Endereço do atendimento")
	private String endereco;
	
	@ApiModelProperty(value = "Número do endereço  do atendimento")
	private String numero;
	
	@ApiModelProperty(value = "Complemento do endereço do atendimento")
	private String complemento;
	
	@ApiModelProperty(value = "Ud do endereço do atendimento")
	private String uf;
	
	@ApiModelProperty(value = "Bairro do endereço do atendimento")
	private String bairro;
	
	@ApiModelProperty(value = "Municipio do endereço do atendimento")
	private String municipio;
	
	@ApiModelProperty(value = "Email do atendimento")
	private String email;
	
	@ApiModelProperty(value = "DDD fone do atendimento")
	private String dddFone;
	
	@ApiModelProperty(value = "Fone do atendimento")
	private String fone;
	
	@ApiModelProperty(value = "DDD celular do atendimento")
	private String dddCelular;
	
	@ApiModelProperty(value = "Fone celular do atendimento")
	private String foneCelular;
	
	@ApiModelProperty(value = "Data de entrada do atendimento")
	private LocalDate dataEntrada;
	
	@ApiModelProperty(value = "Origem manifestação do atendimento")
	private Long origemManifestacao;
	
	@ApiModelProperty(value = "Descrição do atendimento")
	private String descricao;
	
	@ApiModelProperty(value = "Usuário criação do atendimento")
	private Long usuarioCriacao;
	
	@ApiModelProperty(value = "Data Criação do atendimento")
	private LocalDateTime dataCriacao;
	
	@ApiModelProperty(value = "Tempo duração do atendimento")
	private String tempoDuracaoAtendimento;

	@ApiModelProperty(value = "Data prazo do atendimento")
	private LocalDate dataPrazo;
	
	@ApiModelProperty(value = "Data prazo previsto do atendimento")
	private LocalDate dataPrazoPrevisto;
	
	@ApiModelProperty(value = "Descrição oque do atendimento")
	private String descricaoOque;
	
	@ApiModelProperty(value = "Descrição como do atendimento")
	private String descricaoComo;
	
	@ApiModelProperty(value = "Descrição onde do atendimento")
	private String descricaoOnde;
	
	@ApiModelProperty(value = "Descrição quem do atendimento")
	private String descricaoQuem;
	
	@ApiModelProperty(value = "Descrição quando do atendimento")
	private String descricaoQuando;
	
	@ApiModelProperty(value = "Área do atendimento")
	private Long area;
 
	@ApiModelProperty(value = "Assunto do atendimento")
	private Long assunto;
	
	@ApiModelProperty(value = "Natureza do atendimento")
	private Long natureza;
	
	@ApiModelProperty(value = "Priorização do atendimento")
	private Integer priorizacao;
	
	@ApiModelProperty(value = "Justificativa do atendimento")
	private String justificativaProrrogacao;
	
	@ApiModelProperty(value = "Status atendimento do atendimento")
	private Integer statusAtendimento;
	
	@ApiModelProperty(value = "Status do atendimento")
	private Integer status;
	
	@ApiModelProperty(value = "Resposta do atendimento")
	private String resposta;
	
	@ApiModelProperty(value = "Observação do atendimento")
	private String observacao;
	
	@ApiModelProperty(value = "Data de conclusão do atendimento")
	private Timestamp dataConclusao;
	
	@ApiModelProperty(value = "Satisfaz o atendimento")
	private Integer satisfaz;
	
	@ApiModelProperty(value = "Descrição fatos do atendimento")
	private String descricaoFatos;
	
	@ApiModelProperty(value = "Data quando atendimento")
	private String dataQuando;
	
	@ApiModelProperty(value = "Sequencial atendimento")
	private Long sequencialOrgao;
	
	@ApiModelProperty(value = "Protocolo de origem do atendimento")
	private String protocoloOrigem;
	
	@ApiModelProperty(value = "Complemento descrição do atendimento")
	private String complementoDescricao;
	
	@ApiModelProperty(value = "Modo resposta do atendimento")
	private Integer modoResposta;
	
	@ApiModelProperty(value = "Senha de acesso do atendimento")
	private String senhaManifestante;

	@ApiModelProperty(value = "Situação do atendimento")
	private String situacao;
	
	@ApiModelProperty(value = "Manifestante do atendimento")
	private Long manifestante;
	
	@ApiModelProperty(value = "Forma resposta do atendimento")
	private Long codigoAtendimentoResposta;

	@ApiModelProperty(value = "Bairro ocorrencia do atendimento")
	private Long codigoBairroOcorrencia;
	
	@ApiModelProperty(value = "Modelo esposta do atendimento")
	private Long modeloResposta;
	
	@ApiModelProperty(value = "Municipio do atendimento")
	private Integer codigoMunicipio;
	
	@ApiModelProperty(value = "Usuario alteração do atendimento")
	private Long usuarioAlteracao;
	
	@ApiModelProperty(value = "Data alteração do atendimento")
	private LocalDateTime dataAlteracao; 
	
	@ApiModelProperty(value = "Pendencia do atendimento")
	private Integer pendente;
	
	@ApiModelProperty(value = "Identifica se a manifestação e anonima ou identificada")
	private String identificado;
	
	@ApiModelProperty(value = "Código do setor")
	private Long setor;
	
	@ApiModelProperty(value = "Sigilo do atendimento")
	private Boolean sigilo;
	
	@ApiModelProperty(value = "Descrição do assunto")
	private String descricaoAssunto;
	
	private String descricaoOutrosTipoDenuncia;
	
	private String codigoRelacaoSuspeito;
	
	private Integer codigoTipoDenuncia;
	
	private String evidencias;
	
	private Integer instituicao;
	
	private boolean camposDesabilitados;
	
	private boolean manifestacaoClassificada;
	
	private boolean manifestacaoConcluida;
	
	private boolean habilitaBotaoConcluir;
	
	private String descricaoUsuario;
	
	private String descricaoUsuarioAlteracao;
	
	private Integer qtdAnexos;
	
	private Integer qtdHistoricoUsuario;
	
	private String dataNasci;
	
	private String descricaoOutrosTipoUsuario;

	private String descricaoOutrosRelacaoSuspeito;
	
	private Long codigoUnidadeMissao;
	
	public String getDataNasci() {
		if (dataNascimento != null) {
			dataNasci = DateTools.dateToString(dataNascimento);
		}
		return dataNasci;
	}

	public void setDataNasci(String dataNasci) {
		this.dataNasci = dataNasci;
	}

	public String getDescricaoUsuarioAlteracao() {
		return descricaoUsuarioAlteracao;
	}

	public void setDescricaoUsuarioAlteracao(String descricaoUsuarioAlteracao) {
		this.descricaoUsuarioAlteracao = descricaoUsuarioAlteracao;
	}

	public Integer getQtdHistoricoUsuario() {
		return qtdHistoricoUsuario;
	}

	public void setQtdHistoricoUsuario(Integer qtdHistoricoUsuario) {
		this.qtdHistoricoUsuario = qtdHistoricoUsuario;
	}

	public Integer getQtdAnexos() {
		return qtdAnexos;
	}

	public void setQtdAnexos(Integer qtdAnexos) {
		this.qtdAnexos = qtdAnexos;
	}

	public String getDescricaoUsuario() {
		return descricaoUsuario;
	}

	public void setDescricaoUsuario(String descricaoUsuario) {
		this.descricaoUsuario = descricaoUsuario;
	}

	public String getDescricaoAssunto() {
		return descricaoAssunto;
	}

	public void setDescricaoAssunto(String descricaoAssunto) {
		this.descricaoAssunto = descricaoAssunto;
	}

	public boolean isHabilitaBotaoConcluir() {
		return habilitaBotaoConcluir;
	}

	public void setHabilitaBotaoConcluir(boolean habilitaBotaoConcluir) {
		this.habilitaBotaoConcluir = habilitaBotaoConcluir;
	}

	public boolean isManifestacaoConcluida() {
		return manifestacaoConcluida;
	}

	public void setManifestacaoConcluida(boolean manifestacaoConcluida) {
		this.manifestacaoConcluida = manifestacaoConcluida;
	}

	public boolean isManifestacaoClassificada() {
		return manifestacaoClassificada;
	}

	public void setManifestacaoClassificada(boolean manifestacaoClassificada) {
		this.manifestacaoClassificada = manifestacaoClassificada;
	}

	public boolean isCamposDesabilitados() {
		return camposDesabilitados;
	}

	public void setCamposDesabilitados(boolean camposDesabilitados) {
		this.camposDesabilitados = camposDesabilitados;
	}

	public Boolean getSigilo() {
		return sigilo;
	}

	public void setSigilo(Boolean sigilo) {
		this.sigilo = sigilo;
	}

	public Long getSetor() {
		return setor;
	}

	public void setSetor(Long setor) {
		this.setor = setor;
	}

	private List<ListaAnexoDto> listaAnexoDto;

	public List<ListaAnexoDto> getListaAnexoDto() {
		return listaAnexoDto;
	}

	public void setListaAnexoDto(List<ListaAnexoDto> listaAnexoDto) {
		this.listaAnexoDto = listaAnexoDto;
	}

	public String getIdentificado() {
		return identificado;
	}

	public void setIdentificado(String identificado) {
		this.identificado = identificado;
	}

	public Integer getPendente() {
		return pendente;
	}

	public void setPendente(Integer pendente) {
		this.pendente = pendente;
	}

	public Integer getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public void setCodigoMunicipio(Integer codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	public Long getModeloResposta() {
		return modeloResposta;
	}

	public void setModeloResposta(Long modeloResposta) {
		this.modeloResposta = modeloResposta;
	}


	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Long getCodigoBairroOcorrencia() {
		return codigoBairroOcorrencia;
	}

	public void setCodigoBairroOcorrencia(Long codigoBairroOcorrencia) {
		this.codigoBairroOcorrencia = codigoBairroOcorrencia;
	}


	public Long getCodigoAtendimentoResposta() {
		return codigoAtendimentoResposta;
	}

	public void setCodigoAtendimentoResposta(Long codigoAtendimentoResposta) {
		this.codigoAtendimentoResposta = codigoAtendimentoResposta;
	}
	
	public String getProtocoloOrigem() {
		return protocoloOrigem;
	}

	public void setProtocoloOrigem(String protocoloOrigem) {
		this.protocoloOrigem = protocoloOrigem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getNumeroAtendimento() {
		return numeroAtendimento;
	}

	public void setNumeroAtendimento(Long numeroAtendimento) {
		this.numeroAtendimento = numeroAtendimento;
	}

	public Integer getStatusAtendimento() {
		return statusAtendimento;
	}

	public Long getSequencialOrgao() {
		return sequencialOrgao;
	}

	public void setSequencialOrgao(Long sequencialOrgao) {
		this.sequencialOrgao = sequencialOrgao;
	}

	public String getDescricaoFatos() {
		return descricaoFatos;
	}

	public void setDescricaoFatos(String descricaoFatos) {
		this.descricaoFatos = descricaoFatos;
	}

	public Integer getSatisfaz() {
		return satisfaz;
	}

	public void setSatisfaz(Integer satisfaz) {
		this.satisfaz = satisfaz;
	}

	public void setStatusAtendimento(Integer statusAtendimento) {
		this.statusAtendimento = statusAtendimento;
	}

	public Timestamp getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Timestamp dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public String getJustificativaProrrogacao() {
		return justificativaProrrogacao;
	}

	public void setJustificativaProrrogacao(String justificativaProrrogacao) {
		this.justificativaProrrogacao = justificativaProrrogacao;
	}

	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
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

	public Integer getManterSigilo() {
		return manterSigilo;
	}

	public void setManterSigilo(Integer manterSigilo) {
		this.manterSigilo = manterSigilo;
	}

	public Integer getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Integer tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
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
	

	public String getDataQuando() {
		return dataQuando;
	}

	public void setDataQuando(String dataQuando) {
		this.dataQuando = dataQuando;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Timestamp getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Timestamp dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Integer getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(Integer estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDddFone() {
		return dddFone;
	}

	public void setDddFone(String dddFone) {
		this.dddFone = dddFone;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getDddCelular() {
		return dddCelular;
	}

	public void setDddCelular(String dddCelular) {
		this.dddCelular = dddCelular;
	}

	public String getFoneCelular() {
		return foneCelular;
	}

	public void setFoneCelular(String foneCelular) {
		this.foneCelular = foneCelular;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Long getOrigemManifestacao() {
		return origemManifestacao;
	}

	public void setOrigemManifestacao(Long origemManifestacao) {
		this.origemManifestacao = origemManifestacao;
	}


	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getTempoDuracaoAtendimento() {
		return tempoDuracaoAtendimento;
	}

	public void setTempoDuracaoAtendimento(String tempoDuracaoAtendimento) {
		this.tempoDuracaoAtendimento = tempoDuracaoAtendimento;
	}

	public LocalDate getDataPrazo() {
		return dataPrazo;
	}

	public void setDataPrazo(LocalDate dataPrazo) {
		this.dataPrazo = dataPrazo;
	}

	public LocalDate getDataPrazoPrevisto() {
		return dataPrazoPrevisto;
	}

	public void setDataPrazoPrevisto(LocalDate dataPrazoPrevisto) {
		this.dataPrazoPrevisto = dataPrazoPrevisto;
	}

	public String getDescricaoOque() {
		return descricaoOque;
	}

	public void setDescricaoOque(String descricaoOque) {
		this.descricaoOque = descricaoOque;
	}

	public String getDescricaoComo() {
		return descricaoComo;
	}

	public void setDescricaoComo(String descricaoComo) {
		this.descricaoComo = descricaoComo;
	}

	public String getDescricaoOnde() {
		return descricaoOnde;
	}

	public void setDescricaoOnde(String descricaoOnde) {
		this.descricaoOnde = descricaoOnde;
	}

	public String getDescricaoQuem() {
		return descricaoQuem;
	}

	public void setDescricaoQuem(String descricaoQuem) {
		this.descricaoQuem = descricaoQuem;
	}

	public String getDescricaoQuando() {
		return descricaoQuando;
	}

	public void setDescricaoQuando(String descricaoQuando) {
		this.descricaoQuando = descricaoQuando;
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

	public Long getAssunto() {
		return assunto;
	}

	public void setAssunto(Long assunto) {
		this.assunto = assunto;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Long getNatureza() {
		return natureza;
	}

	public void setNatureza(Long natureza) {
		this.natureza = natureza;
	}

	public Integer getPriorizacao() {
		return priorizacao;
	}

	public void setPriorizacao(Integer priorizacao) {
		this.priorizacao = priorizacao;
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
	
	

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	
	
	
	
	

	public String getComplementoDescricao() {
		return complementoDescricao;
	}

	public void setComplementoDescricao(String complementoDescricao) {
		this.complementoDescricao = complementoDescricao;
	}

	public Integer getModoResposta() {
		return modoResposta;
	}

	public void setModoResposta(Integer modoResposta) {
		this.modoResposta = modoResposta;
	}

	public String getSenhaManifestante() {
		return senhaManifestante;
	}

	public void setSenhaManifestante(String senhaManifestante) {
		this.senhaManifestante = senhaManifestante;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Long getManifestante() {
		return manifestante;
	}

	public void setManifestante(Long manifestante) {
		this.manifestante = manifestante;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Integer instituicao) {
		this.instituicao = instituicao;
	}

	public String getCodigoRelacaoSuspeito() {
		return codigoRelacaoSuspeito;
	}

	public void setCodigoRelacaoSuspeito(String codigoRelacaoSuspeito) {
		this.codigoRelacaoSuspeito = codigoRelacaoSuspeito;
	}

	public String getEvidencias() {
		return evidencias;
	}

	public void setEvidencias(String evidencias) {
		this.evidencias = evidencias;
	}
	

	public String getDescricaoOutrosTipoUsuario() {
		return descricaoOutrosTipoUsuario;
	}

	public void setDescricaoOutrosTipoUsuario(String descricaoOutrosTipoUsuario) {
		this.descricaoOutrosTipoUsuario = descricaoOutrosTipoUsuario;
	}

	public String getDescricaoOutrosRelacaoSuspeito() {
		return descricaoOutrosRelacaoSuspeito;
	}

	public void setDescricaoOutrosRelacaoSuspeito(String descricaoOutrosRelacaoSuspeito) {
		this.descricaoOutrosRelacaoSuspeito = descricaoOutrosRelacaoSuspeito;
	}

	public Integer getCodigoTipoDenuncia() {
		return codigoTipoDenuncia;
	}

	public void setCodigoTipoDenuncia(Integer codigoTipoDenuncia) {
		this.codigoTipoDenuncia = codigoTipoDenuncia;
	}

	public String getDescricaoOutrosTipoDenuncia() {
		return descricaoOutrosTipoDenuncia;
	}

	public void setDescricaoOutrosTipoDenuncia(String descricaoOutrosTipoDenuncia) {
		this.descricaoOutrosTipoDenuncia = descricaoOutrosTipoDenuncia;
	}

	public Long getCodigoUnidadeMissao() {
		return codigoUnidadeMissao;
	}

	public void setCodigoUnidadeMissao(Long codigoUnidadeMissao) {
		this.codigoUnidadeMissao = codigoUnidadeMissao;
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
