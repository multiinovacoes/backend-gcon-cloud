package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.Natureza;
import br.com.multiinovacoes.gcon.model.dto.NaturezaDto;
import br.com.multiinovacoes.gcon.model.request.NaturezaRequest;

@Mapper(componentModel = "spring")
public interface NaturezaMapper {
	

	List<NaturezaDto> fromResponseList(List<Natureza> list);
	
	NaturezaDto toDto(Natureza natureza);
	
	@Mapping(target = "id", ignore = true)
	NaturezaDto fromNatureza(NaturezaRequest request);
	
	Natureza toNatureza(NaturezaDto dto);
}
