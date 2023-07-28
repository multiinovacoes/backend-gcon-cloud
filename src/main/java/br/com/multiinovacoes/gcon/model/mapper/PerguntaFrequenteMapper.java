package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.multiinovacoes.gcon.model.PerguntaFrequente;
import br.com.multiinovacoes.gcon.model.dto.PerguntaFrequenteDto;
import br.com.multiinovacoes.gcon.model.request.PerguntaFrequenteRequest;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PerguntaFrequenteMapper {
	
	
	List<PerguntaFrequenteDto> fromResponseList(List<PerguntaFrequente> list);
	
	PerguntaFrequenteDto toDto(PerguntaFrequente perguntaFrequente);
	 
	@Mapping(target = "id", ignore = true)
	PerguntaFrequenteDto fromPerguntaFrequente(PerguntaFrequenteRequest request);
	
	PerguntaFrequente toPerguntaFrequente(PerguntaFrequenteDto dto);


}
