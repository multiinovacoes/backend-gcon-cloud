package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.UsuarioDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Usuário Response")
public class ListaUsuarioResponse {
	
	@ApiModelProperty(value = "Lista de Usuários")
	private List<UsuarioDto> usuarioDtoList;
	
	public ListaUsuarioResponse(List<UsuarioDto> usuarioDtoList) {
		this.usuarioDtoList = usuarioDtoList;
	}

	public List<UsuarioDto> getUsuarioDtoList() {
		return usuarioDtoList;
	}

	public void setUsuarioDtoList(List<UsuarioDto> usuarioDtoList) {
		this.usuarioDtoList = usuarioDtoList;
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
