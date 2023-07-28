package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.Anexo;
import br.com.multiinovacoes.gcon.model.dto.AnexoDto;
import br.com.multiinovacoes.gcon.model.request.AnexoRequest;

@Mapper(componentModel = "spring")
public interface AnexoMapper {
	
	
	List<AnexoDto> fromResponseList(List<Anexo> list);
	
	AnexoDto toDto(Anexo anexo);
	 
	AnexoDto fromAnexo(AnexoRequest request);
	
	@Mapping(target = "dataAnexo", ignore = true)
	@Mapping(target = "numeroAtendimento", ignore = true)
	@Mapping(target = "orgao", ignore = true)
	@Mapping(target = "origem", ignore = true)
	@Mapping(target = "visivel", ignore = true)
	Anexo toAnexo(AnexoDto dto);


}
