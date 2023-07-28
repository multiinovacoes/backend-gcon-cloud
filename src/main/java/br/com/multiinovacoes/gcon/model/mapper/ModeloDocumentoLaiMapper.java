package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.ModeloDocumentoLai;
import br.com.multiinovacoes.gcon.model.dto.ModeloDocumentoLaiDto;
import br.com.multiinovacoes.gcon.model.request.ModeloDocumentoLaiRequest;

@Mapper(componentModel = "spring")
public interface ModeloDocumentoLaiMapper {
	
	
	List<ModeloDocumentoLaiDto> fromResponseList(List<ModeloDocumentoLai> list);
	
	ModeloDocumentoLaiDto toDto(ModeloDocumentoLai area);
	
	@Mapping(target = "id", ignore = true)
	ModeloDocumentoLaiDto fromModeloDocumento(ModeloDocumentoLaiRequest request);
	
	ModeloDocumentoLai toModeloDocumento(ModeloDocumentoLaiDto dto);


}
