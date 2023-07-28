package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.Assunto;
import br.com.multiinovacoes.gcon.model.dto.AssuntoDto;
import br.com.multiinovacoes.gcon.model.request.AssuntoRequest;

@Mapper(componentModel = "spring")
public interface AssuntoMapper {
	   
	
	List<AssuntoDto> fromResponseList(List<Assunto> list);
	
	AssuntoDto toDto(Assunto assunto);
	
	@Mapping(target = "id", ignore = true)
	AssuntoDto fromAssunto(AssuntoRequest request);
	
	Assunto toAssunto(AssuntoDto dto);


}
