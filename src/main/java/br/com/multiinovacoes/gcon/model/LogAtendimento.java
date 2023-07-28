package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OUVIDORIA_LOG_ATENDIMENTO")
public class LogAtendimento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5312810743068344136L;
	
	
	@Id
	@Column(name = "IN_ID")
	private Long id;
	
	@Column(name = "IN_IDATENDIMENTO")
	private Long atendimento;
	
	@Column(name = "DADATA")
	private LocalDateTime data;

	@Column(name = "INIDUSUARIO")
	private Long idUsuario;
	
	@Column(name = "VAACAO")
	private String acao;
	
	
	public LogAtendimento() {
	}

	public LogAtendimento(Long id, Long atendimento, LocalDateTime data, Long idUsuario, String acao) {
		super();
		this.id = id;
		this.atendimento = atendimento;
		this.data = data;
		this.idUsuario = idUsuario;
		this.acao = acao;
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

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
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
		LogAtendimento other = (LogAtendimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	


}
