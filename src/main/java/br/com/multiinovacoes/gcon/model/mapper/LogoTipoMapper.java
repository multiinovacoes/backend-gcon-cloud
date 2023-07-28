package br.com.multiinovacoes.gcon.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.LogoTipo;
import br.com.multiinovacoes.gcon.model.dto.LogoTipoDto;
import br.com.multiinovacoes.gcon.model.request.LogoTipoRequest;

@Mapper(componentModel = "spring")
public interface LogoTipoMapper {
	
	@Mapping(target = "arquivo", ignore = true)
	@Mapping(target = "status", ignore = true)
	LogoTipoDto toDto(LogoTipo logoTipo);
	
	@Mapping(target = "orgao", ignore = true)
	@Mapping(target = "id", ignore = true)
	LogoTipoDto fromLogoTipo(LogoTipoRequest request);
	
	LogoTipo toLogoTipo(LogoTipoDto dto);


}
