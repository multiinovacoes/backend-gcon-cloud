package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.TipoExpressoes;
import br.com.multiinovacoes.gcon.model.dto.TipoExpressoesDto;
import br.com.multiinovacoes.gcon.model.request.TipoExpressoesRequest;

@Mapper(componentModel = "spring")
public interface TipoExpressoesMapper {
	
	
	List<TipoExpressoesDto> fromResponseList(List<TipoExpressoes> list);
	
	TipoExpressoesDto toDto(TipoExpressoes tipoExpressoes);
	 
	@Mapping(target = "id", ignore = true)
	TipoExpressoesDto fromTipoExpressoes(TipoExpressoesRequest request);
	
	TipoExpressoes toTipoExpressoes(TipoExpressoesDto dto);


}
