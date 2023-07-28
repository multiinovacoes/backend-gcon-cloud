package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.ModeloDocumento;
import br.com.multiinovacoes.gcon.model.dto.ModeloDocumentoDto;
import br.com.multiinovacoes.gcon.model.request.ModeloDocumentoRequest;

@Mapper(componentModel = "spring")
public interface ModeloDocumentoMapper {
	
	
	List<ModeloDocumentoDto> fromResponseList(List<ModeloDocumento> list);
	
	ModeloDocumentoDto toDto(ModeloDocumento area);
	
	@Mapping(target = "id", ignore = true)
	ModeloDocumentoDto fromModeloDocumento(ModeloDocumentoRequest request);
	
	ModeloDocumento toModeloDocumento(ModeloDocumentoDto dto);


}
