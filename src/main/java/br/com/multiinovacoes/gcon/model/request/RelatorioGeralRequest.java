package br.com.multiinovacoes.gcon.model.request;

import java.util.Date;
import java.util.List;

import br.com.multiinovacoes.gcon.report.Campo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class RelatorioGeralRequest  {

	@ApiModelProperty(value = "Id do despacho")	
	private Long orgao;
	
	private Date dataInicial; 
	
	private Date dataFinal;
	
    private List<Campo> camposTipoExpressao;

	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public List<Campo> getCamposTipoExpressao() {
		return camposTipoExpressao;
	}

	public void setCamposTipoExpressao(List<Campo> camposTipoExpressao) {
		this.camposTipoExpressao = camposTipoExpressao;
	}



}
