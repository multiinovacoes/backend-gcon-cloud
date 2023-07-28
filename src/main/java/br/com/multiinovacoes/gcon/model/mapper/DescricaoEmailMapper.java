package br.com.multiinovacoes.gcon.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.DescricaoEmail;
import br.com.multiinovacoes.gcon.model.dto.DescricaoEmailDto;
import br.com.multiinovacoes.gcon.model.request.DescricaoEmailRequest;

@Mapper(componentModel = "spring")
public interface DescricaoEmailMapper {
	
	DescricaoEmailDto toDto(DescricaoEmail descricaoEmail);
	
	@Mapping(target = "id", ignore = true)
	DescricaoEmailDto fromDescricaoEmail(DescricaoEmailRequest request);
	
	DescricaoEmail toDescricaoEmail(DescricaoEmailDto dto);


}
