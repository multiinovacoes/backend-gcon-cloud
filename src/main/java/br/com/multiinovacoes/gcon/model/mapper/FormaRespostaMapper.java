package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.FormaResposta;
import br.com.multiinovacoes.gcon.model.dto.FormaRespostaDto;
import br.com.multiinovacoes.gcon.model.request.FormaRespostaRequest;

@Mapper(componentModel = "spring")
public interface FormaRespostaMapper {
	
	
	List<FormaRespostaDto> fromResponseList(List<FormaResposta> list);
	
	FormaRespostaDto toDto(FormaResposta formaResposta);
	 
	@Mapping(target = "id", ignore = true)
	FormaRespostaDto fromFormaResposta(FormaRespostaRequest request);
	
	FormaResposta toFormaResposta(FormaRespostaDto dto);


}
