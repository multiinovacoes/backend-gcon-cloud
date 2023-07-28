package br.com.multiinovacoes.gcon.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.Configuracao;
import br.com.multiinovacoes.gcon.model.dto.ConfiguracaoDto;
import br.com.multiinovacoes.gcon.model.request.ConfiguracaoRequest;

@Mapper(componentModel = "spring")
public interface ConfiguracaoMapper {
	
	ConfiguracaoDto toDto(Configuracao configuracao);
	
	ConfiguracaoDto fromConfiguracao(ConfiguracaoRequest request);
	
	@Mapping(target = "setor", ignore = true)
	Configuracao toConfiguracao(ConfiguracaoDto dto);


}
