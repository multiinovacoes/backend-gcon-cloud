package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.TipoManifestante;
import br.com.multiinovacoes.gcon.model.dto.TipoManifestanteDto;
import br.com.multiinovacoes.gcon.model.request.TipoManifestanteRequest;

@Mapper(componentModel = "spring")
public interface TipoManifestanteMapper {
	
	
	List<TipoManifestanteDto> fromResponseList(List<TipoManifestante> list);
	
	TipoManifestanteDto toDto(TipoManifestante tipoManifestante);
	
	@Mapping(target = "id", ignore = true)
	TipoManifestanteDto fromTipoManifestante(TipoManifestanteRequest request);
	
	TipoManifestante toTipoManifestante(TipoManifestanteDto dto);


}
