package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "LAI_ANEXO_ARQUIVO")
public class AnexoLai implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -808936296045014021L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INCODIGOANEXO")
	private Long id;

	@Column(name = "INATENDIMENTOID")
	private Long atendimento;

	@Column(name = "VANOMEARQUIVO")
	private String nomeArquivo;
	
	@Column(name = "DADATAANEXO")
	private LocalDate dataAnexo;

	@Column(name = "RESPONSAVEL")
	private Integer responsavel;
	
	@Transient
	private String nomeResponsavel;

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public Integer getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Integer responsavel) {
		this.responsavel = responsavel;
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

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public LocalDate getDataAnexo() {
		return dataAnexo;
	}

	public void setDataAnexo(LocalDate dataAnexo) {
		this.dataAnexo = dataAnexo;
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
		AnexoLai other = (AnexoLai) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	
	

}
