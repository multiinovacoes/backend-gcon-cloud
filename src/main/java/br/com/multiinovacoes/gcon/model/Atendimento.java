package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "OUVIDORIA_ATENDIMENTO")
public class Atendimento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2426378029381142933L;

	public static final Integer STATUS_ABERTO = 0;
	public static final Integer STATUS_CONCLUIDO = 1;
	public static final Integer STATUS_CANCELADO = 2;

	public static final Integer STATUS_ATENDIMENTO_NAO_CLASSIFICADO = 0;
	public static final Integer STATUS_ATENDIMENTO_CLASSIFICADO = 1;
	public static final Integer STATUS_ATENDIMENTO_CONCLUIDO = 2;
	public static final Integer STATUS_ATENDIMENTO_ENCAMINHADO = 3;
	public static final Integer STATUS_ATENDIMENTO_NAO_ENCAMINHADO = 4;
	public static final Integer STATUS_ATENDIMENTO_ENCAMINHADO_RESPONDIDO = 5;
	public static final Integer STATUS_ATENDIMENTO_ENCAMINHADO_NAO_RESPONDIDO = 6;
	public static final Integer STATUS_ATENDIMENTO_IMPROCEDENTE = 7;
	public static final Integer STATUS_ATENDIMENTO_CONCLUIDO_SOLUCAO_PENDENTE = 8;

	public static final String FORMA_RESPOSTA_CARTA = "1";
	public static final String FORMA_RESPOSTA_EMAIL = "2";
	public static final String FORMA_RESPOSTA_TELEFONE = "4";
	public static final String FORMA_RESPOSTA_PRESENCIAL = "5";
	
	public static final Long CANAL_DENUNCIA = 5l;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INATENDIMENTOID")
	private Long id;

	@Column(name = "INCODIGOORGAO")
	private Long orgao;

	@Column(name = "VANUMPROTOCOLO")
	private String numeroProtocolo;

	@Column(name = "SMANOATENDIMENTO")
	private Integer anoAtendimento;

	@Column(name = "INNUMEROATENDIMENTO")
	private Long numeroAtendimento;

	@Column(name = "INSIGILO")
	private Integer manterSigilo;

	@Column(name = "INTIPOMANIFESTANTENOVO")
	//@Column(name = "INTIPO_MANIFESTANTE")
	private Long tipoUsuario;

	@Column(name = "VANOMESOLICITANTE")
	private String nomeSolicitante;

	@Column(name = "INCODIGOTIPODOCUMENTONOVO")
	//@Column(name = "INCODIGOTIPODOCUMENTO")
	private Long tipoDocumento;

	@Column(name = "VACPF")
	private String numeroDocumento;

	@Column(name = "DADATA_NASCIMENTO")
	private Timestamp dataNascimento;

	@Column(name = "INCODIGOESTADOCIVIL")
	private Integer estadoCivil;

	@Column(name = "INCODIGOMUNICIPIO")
	private Integer codigoMunicipio;

	@Column(name = "VACEP")
	private String cep;

	@Column(name = "VAENDERECO")
	private String endereco;

	@Column(name = "VACOMPLEMENTO")
	private String complemento;

	@Column(name = "CHSIGLAUF")
	private String uf;

	@Column(name = "VABAIRRO")
	private String bairro;

	@Column(name = "VAMUNICIPIO")
	private String municipio;

	@Column(name = "VAEMAIL")
	private String email;

	@Column(name = "VADDD")
	private String dddFone;

	@Column(name = "VAFONE")
	private String fone;

	@Column(name = "VADDDCEL")
	private String dddCelular;

	@Column(name = "VAFONECEL")
	private String foneCelular;

	@Column(name = "DAENTRADA")
	private LocalDate dataEntrada;

	@Column(name = "INCODIGOORIGEMMANIFESTACAO")
	private Long origemManifestacao;

	@Column(name = "VADESCRICAO")
	private String descricao;

	@Column(name = "INCODIGOUSUARIO")
	private Long usuarioCriacao;

	@Column(name = "DACRIACAO")
	private LocalDateTime dataCriacao;

	@Column(name = "INCODIGOUSUARIOALTERACAO")
	private Long usuarioAlteracao;

	@Column(name = "DAALTERACAO")
	private LocalDateTime dataAlteracao;

	@Column(name = "DADATA_PRAZO")
	private LocalDate dataPrazo;

	@Column(name = "DADATA_PRAZO_ATUAL")
	private LocalDate dataPrazoPrevisto;

	@Column(name = "VADESCRICAOOQUE")
	private String descricaoOque;

	@Column(name = "VADESCRICAOCOMO")
	private String descricaoComo;

	@Column(name = "VADESCRICAOONDE")
	private String descricaoOnde;

	@Column(name = "VADESCRICAOQUEM")
	private String descricaoQuem;

	@Column(name = "INCODIGOAREA")
	private Long area;

	@Column(name = "INCODIGOASSUNTONOVO")
	//@Column(name = "INCODIGOASSUNTO")
	private Long assunto;

	@Column(name = "INCODIGONATUREZA")
	private Long natureza;

	@Column(name = "INCODIGOPRIORIZACAO")
	private Integer priorizacao;

	@Column(name = "SMSTATUSATENDIMENTO")
	private Integer statusAtendimento;

	@Column(name = "SMSTATUS")
	private Integer status;

	@Column(name = "VARESPOSTA")
	private String resposta;

	@Column(name = "VA_OBSERVACOES")
	private String observacao;

	@Column(name = "DACONCLUSAO")
	private Timestamp dataConclusao;

	@Column(name = "INSATISFAZ")
	private Integer satisfaz;

	@Column(name = "VADESCRICAOFATOS")
	private String descricaoFatos;

	@Column(name = "VADATAQUANDO")
	private String dataQuando;

	@Column(name = "INSEQUENCIALORGAO")
	private Long sequencialOrgao;

	@Column(name = "VAPROTOCOLOORIGEM")
	private String protocoloOrigem;

	@Column(name = "VASENHA_MANIFESTANTE")
	private String senhaManifestante;

	@Column(name = "INPENDENTE")
	private Integer pendente;

	@Transient
	private String descricaoUsuario;
	
	@Column(name = "IN_BAIRRO_OCORRENCIA")
	private Long codigoBairroOcorrencia;
	
	@Column(name = "INCODIGOSETOR")
	private Long setor;

	@Column(name = "VADESCRICAOQUANDO")
	private String descricaoQuando;
	
	@Column(name = "VATEMPOATENDIMENTO")
	private String tempoDuracaoAtendimento;

	@Column(name = "INTOTALDIAS")
	private Integer totalDias;
	
	@Column(name = "INCODIGOATENDIMENTORESPOSTA")
	private Integer codigoAtendimentoResposta;

	@Column(name = "VAJUSTIFICATIVAPRORROGACAO")
	private String justificativaProrrogacao;
	
	@Column(name = "VAPROVIDENCIA")
	private String providencia;
	
	@Column(name = "INCODIGOMODELO")
	private Long codigoModelo;
	
	@Column(name = "VADESCRICAOSOLUCAO")
	private String descricaoSolucao;
	
	@Column(name = "VAOUTROLOCAL")
	private String outroLocal;

	@Column(name = "INCODIGOINSTITUICAO")
	private Integer instituicao;
	
	@Column(name = "PARAMETRO_HASH")
	private String parametroHash;
	
	@Column(name = "INCODIGORELACAOSUSPEITO")
	private String codigoRelacaoSuspeito;
	
	@Column(name = "EVIDENCIA")
	private String evidencias;

	@Column(name = "DESCRICAO_OUTROS_TIPO_USUARIO")
	private String descricaoOutrosTipoUsuario;

	@Column(name = "DESCRICAO_OUTROS_RELACAO_SUSPEITO")
	private String descricaoOutrosRelacaoSuspeito;

	@Column(name = "INCODIGOTIPODENUNCIA")
	private String codigoTipoDenuncia;
	
	@Column(name = "DESCRICAO_OUTROS_TIPO_DENUNCIA")
	private String descricaoOutrosTipoDenuncia;

	@Column(name = "CODIGO_UNIDADE_MISSAO")
	private Long codigoUnidadeMissao;

	@Transient
	private String descricaoAssunto;
	
	@Transient
	private LocalDate dataPrazoRespostaSetor;
	
	@Transient
	private String descricaoSetorEncaminhado;
	
	@Transient
	private int qtdDiasVencimentoSetor;
	
	@Transient
	private LocalDate dataEnviadoSetor;

	public LocalDate getDataPrazoRespostaSetor() {
		return dataPrazoRespostaSetor;
	}

	public void setDataPrazoRespostaSetor(LocalDate dataPrazoRespostaSetor) {
		this.dataPrazoRespostaSetor = dataPrazoRespostaSetor;
	}

	public LocalDate getDataEnviadoSetor() {
		return dataEnviadoSetor;
	}

	public void setDataEnviadoSetor(LocalDate dataEnviadoSetor) {
		this.dataEnviadoSetor = dataEnviadoSetor;
	}

	public String getDescricaoSetorEncaminhado() {
		return descricaoSetorEncaminhado;
	}

	public void setDescricaoSetorEncaminhado(String descricaoSetorEncaminhado) {
		this.descricaoSetorEncaminhado = descricaoSetorEncaminhado;
	}

	public int getQtdDiasVencimentoSetor() {
		return qtdDiasVencimentoSetor;
	}

	public void setQtdDiasVencimentoSetor(int qtdDiasVencimentoSetor) {
		this.qtdDiasVencimentoSetor = qtdDiasVencimentoSetor;
	}

	public String getDescricaoAssunto() {
		return descricaoAssunto;
	}

	public void setDescricaoAssunto(String descricaoAssunto) {
		this.descricaoAssunto = descricaoAssunto;
	}

	public String getOutroLocal() {
		return outroLocal;
	}

	public void setOutroLocal(String outroLocal) {
		this.outroLocal = outroLocal;
	}

	public String getDescricaoSolucao() {
		return descricaoSolucao;
	}

	public void setDescricaoSolucao(String descricaoSolucao) {
		this.descricaoSolucao = descricaoSolucao;
	}

	public String getProvidencia() {
		return providencia;
	}

	public void setProvidencia(String providencia) {
		this.providencia = providencia;
	}

	public Long getCodigoModelo() {
		return codigoModelo;
	}

	public void setCodigoModelo(Long codigoModelo) {
		this.codigoModelo = codigoModelo;
	}

	public Integer getCodigoAtendimentoResposta() {
		return codigoAtendimentoResposta;
	}

	public void setCodigoAtendimentoResposta(Integer codigoAtendimentoResposta) {
		this.codigoAtendimentoResposta = codigoAtendimentoResposta;
	}

	public String getJustificativaProrrogacao() {
		return justificativaProrrogacao;
	}

	public void setJustificativaProrrogacao(String justificativaProrrogacao) {
		this.justificativaProrrogacao = justificativaProrrogacao;
	}

	public String getTempoDuracaoAtendimento() {
		return tempoDuracaoAtendimento;
	}

	public Integer getTotalDias() {
		return totalDias;
	}

	public void setTotalDias(Integer totalDias) {
		this.totalDias = totalDias;
	}

	public void setTempoDuracaoAtendimento(String tempoDuracaoAtendimento) {
		this.tempoDuracaoAtendimento = tempoDuracaoAtendimento;
	}

	public String getDescricaoQuando() {
		return descricaoQuando;
	}

	public void setDescricaoQuando(String descricaoQuando) {
		this.descricaoQuando = descricaoQuando;
	}

	public Long getSetor() {
		return setor;
	}

	public void setSetor(Long setor) {
		this.setor = setor;
	}

	public Long getCodigoBairroOcorrencia() {
		return codigoBairroOcorrencia;
	}

	public void setCodigoBairroOcorrencia(Long codigoBairroOcorrencia) {
		this.codigoBairroOcorrencia = codigoBairroOcorrencia;
	}

	public String getDescricaoUsuario() {
		return descricaoUsuario;
	}

	public void setDescricaoUsuario(String descricaoUsuario) {
		this.descricaoUsuario = descricaoUsuario;
	}

	public Integer getPendente() {
		return pendente;
	}

	public void setPendente(Integer pendente) {
		this.pendente = pendente;
	}

	public String getProtocoloOrigem() {
		return protocoloOrigem;
	}

	public Integer getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public void setCodigoMunicipio(Integer codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
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

	public Long getNumeroAtendimento() {
		return numeroAtendimento;
	}

	public void setNumeroAtendimento(Long numeroAtendimento) {
		this.numeroAtendimento = numeroAtendimento;
	}

	public Long getArea() {
		return area;
	}

	public void setArea(Long area) {
		this.area = area;
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

	public void setStatusAtendimento(Integer statusAtendimento) {
		this.statusAtendimento = statusAtendimento;
	}

	public Timestamp getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Timestamp dataConclusao) {
		this.dataConclusao = dataConclusao;
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

	public Long getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Long tipoUsuario) {
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

	public Long getAssunto() {
		return assunto;
	}

	public void setAssunto(Long assunto) {
		this.assunto = assunto;
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

	public String getSenhaManifestante() {
		return senhaManifestante;
	}

	public void setSenhaManifestante(String senhaManifestante) {
		this.senhaManifestante = senhaManifestante;
	}

	public Integer getManterSigilo() {
		return manterSigilo;
	}

	public void setManterSigilo(Integer manterSigilo) {
		this.manterSigilo = manterSigilo;
	}

	public Integer getSatisfaz() {
		return satisfaz;
	}

	public void setSatisfaz(Integer satisfaz) {
		this.satisfaz = satisfaz;
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

	public String getParametroHash() {
		return parametroHash;
	}

	public void setParametroHash(String parametroHash) {
		this.parametroHash = parametroHash;
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

	public String getCodigoTipoDenuncia() {
		return codigoTipoDenuncia;
	}

	public void setCodigoTipoDenuncia(String codigoTipoDenuncia) {
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
		Atendimento other = (Atendimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
