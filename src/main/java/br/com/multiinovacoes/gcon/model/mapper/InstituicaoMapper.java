package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.Instituicao;
import br.com.multiinovacoes.gcon.model.dto.InstituicaoDto;
import br.com.multiinovacoes.gcon.model.request.InstituicaoRequest;

@Mapper(componentModel = "spring")
public interface InstituicaoMapper {
	
	
	List<InstituicaoDto> fromResponseList(List<Instituicao> list);
	
	InstituicaoDto toDto(Instituicao instituicao);
	 
	@Mapping(target = "id", ignore = true)
	InstituicaoDto fromInstituicao(InstituicaoRequest request);
	
	Instituicao toInstituicao(InstituicaoDto dto);


}
