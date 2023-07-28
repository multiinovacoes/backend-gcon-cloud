package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GLOB_REDEFINICAO_SENHA")
public class RedefinicaoSenha implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9088274237629008997L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODIGO")
	private Long id;

	@Column(name = "CODIGO_USUARIO")
	private Long codigoUsuario;

	@Column(name = "PARAMETRO")
	private String parametro;

	@Column(name = "DATA_SOLICITACAO")
	private LocalDateTime dataSolicitacao;

	@Column(name = "DATA_EXPIRA")
	private LocalDateTime dataExpira;

	@Column(name = "SENHA_REDEFINIDA")
	private Integer senhaRedefinida;

	@Column(name = "DATA_REDEFINIDA")
	private LocalDateTime dataRedefinida;
	
	public RedefinicaoSenha() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Long codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public LocalDateTime getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public LocalDateTime getDataExpira() {
		return dataExpira;
	}

	public void setDataExpira(LocalDateTime dataExpira) {
		this.dataExpira = dataExpira;
	}

	public Integer getSenhaRedefinida() {
		return senhaRedefinida;
	}

	public void setSenhaRedefinida(Integer senhaRedefinida) {
		this.senhaRedefinida = senhaRedefinida;
	}

	public LocalDateTime getDataRedefinida() {
		return dataRedefinida;
	}

	public void setDataRedefinida(LocalDateTime dataRedefinida) {
		this.dataRedefinida = dataRedefinida;
	}
	
	


}
