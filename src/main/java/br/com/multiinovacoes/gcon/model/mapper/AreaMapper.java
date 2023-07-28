package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.Area;
import br.com.multiinovacoes.gcon.model.dto.AreaDto;
import br.com.multiinovacoes.gcon.model.request.AreaRequest;

@Mapper(componentModel = "spring")
public interface AreaMapper {
	
	
	List<AreaDto> fromResponseList(List<Area> list);
	
	AreaDto toDto(Area area);
	 
	@Mapping(target = "id", ignore = true)
	AreaDto fromArea(AreaRequest request);
	
	Area toArea(AreaDto dto);


}
