package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.TipoResposta;
import br.com.multiinovacoes.gcon.model.dto.TipoRespostaDto;
import br.com.multiinovacoes.gcon.model.request.TipoRespostaRequest;

@Mapper(componentModel = "spring")
public interface TipoRespostaMapper {
	

	List<TipoRespostaDto> fromResponseList(List<TipoResposta> list);
	
	TipoRespostaDto toDto(TipoResposta tipoResposta);
	
	@Mapping(target = "id", ignore = true)
	TipoRespostaDto fromTipoResposta(TipoRespostaRequest request);
	
	TipoResposta toTipoResposta(TipoRespostaDto dto);
}
