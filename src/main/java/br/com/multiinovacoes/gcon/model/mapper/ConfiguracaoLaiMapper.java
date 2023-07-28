package br.com.multiinovacoes.gcon.model.mapper;

import org.mapstruct.Mapper;

import br.com.multiinovacoes.gcon.model.ConfiguracaoLai;
import br.com.multiinovacoes.gcon.model.dto.ConfiguracaoLaiDto;
import br.com.multiinovacoes.gcon.model.request.ConfiguracaoLaiRequest;

@Mapper(componentModel = "spring")
public interface ConfiguracaoLaiMapper {
	
	ConfiguracaoLaiDto toDto(ConfiguracaoLai configuracao);
	
	ConfiguracaoLaiDto fromConfiguracaoLai(ConfiguracaoLaiRequest request);
	
	ConfiguracaoLai toConfiguracaoLai(ConfiguracaoLaiDto dto);


}
