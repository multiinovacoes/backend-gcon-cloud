package br.com.multiinovacoes.gcon.model.request;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CobrancaDespachoRequest  {

	@ApiModelProperty(value = "Id do despacho")	
	private Long setor;
	
	@ApiModelProperty(value = "Lista de atendimentos")
    private List<Long>selectedAtendimentos;

	public Long getSetor() {
		return setor;
	}

	public void setSetor(Long setor) {
		this.setor = setor;
	}

	public List<Long> getSelectedAtendimentos() {
		return selectedAtendimentos;
	}

	public void setSelectedAtendimentos(List<Long> selectedAtendimentos) {
		this.selectedAtendimentos = selectedAtendimentos;
	}	


}
