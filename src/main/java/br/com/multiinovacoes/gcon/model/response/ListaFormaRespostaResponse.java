package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.FormaRespostaDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Forma Resposta Response")
public class ListaFormaRespostaResponse {
	
	@ApiModelProperty(value = "Lista de Formas de Respostas")
	private List<FormaRespostaDto> formaRespostaDtoList;
	
	public ListaFormaRespostaResponse(List<FormaRespostaDto> formaRespostaDtoList) {
		this.formaRespostaDtoList = formaRespostaDtoList;
	}

	public List<FormaRespostaDto> getFormaRespostaDtoList() {
		return formaRespostaDtoList;
	}

	public void setFormaRespostaDtoList(List<FormaRespostaDto> formaRespostaDtoList) {
		this.formaRespostaDtoList = formaRespostaDtoList;
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
