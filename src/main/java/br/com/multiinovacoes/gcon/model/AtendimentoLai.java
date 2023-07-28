package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.multiinovacoes.gcon.enums.StatusAtendimentoEnum;


@Entity
@Table(name = "LAI_ATENDIMENTO")
public class AtendimentoLai implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2426378029381142933L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INATENDIMENTOID")
	private Long id;
	
	@Column(name = "INCODIGOORGAO")
	private Long orgao;

	@Column(name = "INNUMEROATENDIMENTO")
	private Long numeroAtendimento;

	@Column(name = "VANUMPROTOCOLO")
	private String numeroProtocolo;
	
	@Column(name = "SMANOATENDIMENTO")
	private Integer anoAtendimento;
	
	@Column(name = "VANOMESOLICITANTE")
	private String nomeSolicitante;
	
	@Column(name = "INCODIGOTIPODOCUMENTO")
	private String tipoDocumento;
	
	@Column(name = "VACPF")
	private String numeroDocumento;
	
	@Column(name = "CHSIGLAUF")
	private String uf;
	
	@Column(name = "VAMUNICIPIO")
	private String cidade;
	
	@Column(name = "VAEMAIL")
	private String email;
	
	@Column(name = "VAFONE")
	private String telefone;
	
	@Column(name = "DAENTRADA")
	private LocalDate dataEntrada;

	@Column(name = "INCODIGOORIGEMMANIFESTACAO")
	private Long origemContato;
	
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
	
	@Column(name = "INCODIGOASSUNTO")
	private Long assunto;
	
	@Column(name = "INCODIGONATUREZA")
	private Long natureza;
	
	@Column(name = "SMSTATUSATENDIMENTO")
	private Integer statusAtendimento;
	
	@Column(name = "SMSTATUS")
	private Integer status;
	
	@Column(name = "VARESPOSTA")
	private String resposta;
	
	@Column(name = "VA_OBSERVACOES")
	private String observacao;
	
	@Column(name = "DACONCLUSAO")
	private LocalDateTime dataConclusao;
	
	@Column(name = "INSATISFAZ")
	private Integer satisfaz;
	
	@Column(name = "INSEQUENCIALORGAO")
	private Long sequencialOrgao;
	
	@Column(name = "INSTITUICAO")
	private Long instituicao;

	@Column(name = "INCODIGOAREA")
	private Long area;

	@Column(name = "TIPO_USUARIO")
	private Integer tipoUsuario;

	@Column(name = "PRAZO_PRORROGADO")
	private Integer prazoProrrogado;

	@Column(name = "RESULTADO_PEDIDO")
	private Integer resultadoPedido;
	
	@Transient
	private String descricaoArea;
	
	@Transient
	private String descricaoInstituicao;
	
	@Transient
	private String descricaoStatusAtendimento;
	
	@Transient
	private LocalDate dataPrazoRespostaSetor;
	
	@Transient
	private String descricaoSetorEncaminhado;
	
	@Transient
	private int qtdDiasVencimentoSetor;
	
	@Transient
	private LocalDateTime dataEnviadoSetor;
	
	@Transient
	private Integer qtdCobrancaEmail;

	public Integer getResultadoPedido() {
		return resultadoPedido;
	}

	public void setResultadoPedido(Integer resultadoPedido) {
		this.resultadoPedido = resultadoPedido;
	}

	public Integer getQtdCobrancaEmail() {
		return qtdCobrancaEmail;
	}

	public void setQtdCobrancaEmail(Integer qtdCobrancaEmail) {
		this.qtdCobrancaEmail = qtdCobrancaEmail;
	}

	public LocalDateTime getDataEnviadoSetor() {
		return dataEnviadoSetor;
	}

	public void setDataEnviadoSetor(LocalDateTime dataEnviadoSetor) {
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

	public LocalDate getDataPrazoRespostaSetor() {
		return dataPrazoRespostaSetor;
	}

	public void setDataPrazoRespostaSetor(LocalDate dataPrazoRespostaSetor) {
		this.dataPrazoRespostaSetor = dataPrazoRespostaSetor;
	}

	public String getDescricaoStatusAtendimento() {
		return StatusAtendimentoEnum.pegarDescricao(statusAtendimento).getDescricao();
	}

	public void setDescricaoStatusAtendimento(String descricaoStatusAtendimento) {
		this.descricaoStatusAtendimento = descricaoStatusAtendimento;
	}

	public String getDescricaoArea() {
		return descricaoArea;
	}

	public void setDescricaoArea(String descricaoArea) {
		this.descricaoArea = descricaoArea;
	}

	public String getDescricaoInstituicao() {
		return descricaoInstituicao;
	}

	public void setDescricaoInstituicao(String descricaoInstituicao) {
		this.descricaoInstituicao = descricaoInstituicao;
	}

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

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
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

	public Integer getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Integer tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Integer getPrazoProrrogado() {
		return prazoProrrogado;
	}

	public void setPrazoProrrogado(Integer prazoProrrogado) {
		this.prazoProrrogado = prazoProrrogado;
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
		AtendimentoLai other = (AtendimentoLai) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

}
