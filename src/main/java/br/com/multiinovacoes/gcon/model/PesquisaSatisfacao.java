package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OUVIDORIA_PESQUISA_SATISFACAO")
public class PesquisaSatisfacao implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3766690804261829245L;

	@Id
	@Column(name = "IN_ID_PESQUISA_SATISFACAO")
	private Long id;
	
	@Column(name = "IN_ID_ATENDIMENTO")
	private Long idAtendimento;
	
	@Column(name = "IN_ID_PERGUNTA")
	private Long idPergunta;
	
	@Column(name = "IN_ID_RESPOSTA")
	private Long idResposta;
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdAtendimento() {
		return idAtendimento;
	}

	public void setIdAtendimento(Long idAtendimento) {
		this.idAtendimento = idAtendimento;
	}

	public Long getIdPergunta() {
		return idPergunta;
	}

	public void setIdPergunta(Long idPergunta) {
		this.idPergunta = idPergunta;
	}

	public Long getIdResposta() {
		return idResposta;
	}

	public void setIdResposta(Long idResposta) {
		this.idResposta = idResposta;
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
		PesquisaSatisfacao other = (PesquisaSatisfacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	
}
