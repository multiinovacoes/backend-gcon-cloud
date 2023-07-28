package br.com.multiinovacoes.gcon.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.DescricaoOuvidoria;
import br.com.multiinovacoes.gcon.model.dto.DescricaoOuvidoriaDto;
import br.com.multiinovacoes.gcon.model.request.DescricaoOuvidoriaRequest;

@Mapper(componentModel = "spring")
public interface DescricaoOuvidoriaMapper {
	
	DescricaoOuvidoriaDto toDto(DescricaoOuvidoria descricaoOuvidoria);
	
	@Mapping(target = "id", ignore = true)
	DescricaoOuvidoriaDto fromDescricaoOuvidoria(DescricaoOuvidoriaRequest request);
	
	DescricaoOuvidoria toDescricaoOuvidoria(DescricaoOuvidoriaDto dto);


}
