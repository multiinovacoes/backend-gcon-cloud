package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.OrgaoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Orgão Response")
public class OrgaoResponse {

    @ApiModelProperty(value = "Orgão Dto")
	OrgaoDto orgaoDto;
    
    public OrgaoResponse(OrgaoDto orgaoDto) {
		this.orgaoDto = orgaoDto;
	}

	public OrgaoDto getOrgaoDto() {
		return orgaoDto;
	}

	public void setOrgaoDto(OrgaoDto orgaoDto) {
		this.orgaoDto = orgaoDto;
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
