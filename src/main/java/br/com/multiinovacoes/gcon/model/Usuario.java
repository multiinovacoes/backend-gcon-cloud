package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "GLOB_USUARIO")
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2700574673164534419L;
	
	
	@Id
	@Column(name = "CODIGO_USUARIO")
	private Long id;
	
	@NotBlank(message = "Nome é obrigatório")
	@Column(name = "NOME")
	private String nome;

	@Column(name = "CPF")
	private String cpf;

	@Column(name = "SENHA1")
	private String senha;
	
	@Column(name = "CODIGO_ORGAO")
	private Long orgao;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "CODIGO_SETOR")
	private Long setor;
	
	@Column(name = "SENHA_INFORMADA")
	private String senhaInformada;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario")
	@JsonManagedReference
	private List<PermissaoUsuario> permissoes;

	public List<PermissaoUsuario> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<PermissaoUsuario> permissoes) {
		this.permissoes = permissoes;
	}

	public String getSenhaInformada() {
		return senhaInformada;
	}

	public void setSenhaInformada(String senhaInformada) {
		this.senhaInformada = senhaInformada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getSetor() {
		return setor;
	}

	public void setSetor(Long setor) {
		this.setor = setor;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	

}
