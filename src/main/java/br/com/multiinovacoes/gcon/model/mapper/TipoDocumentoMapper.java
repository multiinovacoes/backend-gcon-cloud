package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.TipoDocumento;
import br.com.multiinovacoes.gcon.model.dto.TipoDocumentoDto;
import br.com.multiinovacoes.gcon.model.request.TipoDocumentoRequest;

@Mapper(componentModel = "spring")
public interface TipoDocumentoMapper {
	
	
	List<TipoDocumentoDto> fromResponseList(List<TipoDocumento> list);
	
	TipoDocumentoDto toDto(TipoDocumento tipoDocumento);
	
	@Mapping(target = "id", ignore = true)
	TipoDocumentoDto fromTipoDocumento(TipoDocumentoRequest request);
	
	TipoDocumento toTipoDocumento(TipoDocumentoDto dto);


}
