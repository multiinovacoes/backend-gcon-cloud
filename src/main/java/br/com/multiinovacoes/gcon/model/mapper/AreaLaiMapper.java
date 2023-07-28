package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.AreaLai;
import br.com.multiinovacoes.gcon.model.dto.AreaLaiDto;
import br.com.multiinovacoes.gcon.model.request.AreaLaiRequest;

@Mapper(componentModel = "spring")
public interface AreaLaiMapper {
	
	
	List<AreaLaiDto> fromResponseList(List<AreaLai> list);
	
	AreaLaiDto toDto(AreaLai area);
	 
	@Mapping(target = "id", ignore = true)
	AreaLaiDto fromAreaLai(AreaLaiRequest request);
	
	AreaLai toAreaLai(AreaLaiDto dto);


}
