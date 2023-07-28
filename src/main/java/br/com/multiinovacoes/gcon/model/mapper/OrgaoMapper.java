package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.Orgao;
import br.com.multiinovacoes.gcon.model.dto.OrgaoDto;
import br.com.multiinovacoes.gcon.model.request.OrgaoRequest;

@Mapper(componentModel = "spring")
public interface OrgaoMapper {
	
	List<OrgaoDto> fromResponseList(List<Orgao> list);
	
	OrgaoDto toDto(Orgao natureza);
	
	@Mapping(target = "id", ignore = true)
	OrgaoDto fromOrgao(OrgaoRequest request);
	
	Orgao toOrgao(OrgaoDto dto);


}
