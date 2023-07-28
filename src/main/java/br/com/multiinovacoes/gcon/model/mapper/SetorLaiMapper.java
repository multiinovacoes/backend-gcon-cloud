package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.SetorLai;
import br.com.multiinovacoes.gcon.model.dto.SetorLaiDto;
import br.com.multiinovacoes.gcon.model.request.SetorLaiRequest;

@Mapper(componentModel = "spring")
public interface SetorLaiMapper {
	
	
	List<SetorLaiDto> fromResponseList(List<SetorLai> list);
	SetorLaiDto toDto(SetorLai area);
	
	@Mapping(target = "id", ignore = true)
	SetorLaiDto fromSetor(SetorLaiRequest request);
	
	SetorLai toSetor(SetorLaiDto dto);


}
