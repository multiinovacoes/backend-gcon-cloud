package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.AnexoLai;
import br.com.multiinovacoes.gcon.model.dto.AnexoLaiDto;
import br.com.multiinovacoes.gcon.model.request.AnexoLaiRequest;

@Mapper(componentModel = "spring")
public interface AnexoLaiMapper {
	
	
	List<AnexoLaiDto> fromResponseList(List<AnexoLai> list);
	
	AnexoLaiDto toDto(AnexoLai anexo);
	 
	AnexoLaiDto fromAnexo(AnexoLaiRequest request);
	
	@Mapping(target = "dataAnexo", ignore = true)
	AnexoLai toAnexo(AnexoLaiDto dto);


}
