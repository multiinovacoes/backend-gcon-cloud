package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.OrigemManifestacao;
import br.com.multiinovacoes.gcon.model.dto.OrigemManifestacaoDto;
import br.com.multiinovacoes.gcon.model.request.OrigemManifestacaoRequest;

@Mapper(componentModel = "spring")
public interface OrigemManifestacaoMapper {
	
	
	List<OrigemManifestacaoDto> fromResponseList(List<OrigemManifestacao> list);
	
	OrigemManifestacaoDto toDto(OrigemManifestacao origemManifestacao);
	
	@Mapping(target = "id", ignore = true)
	OrigemManifestacaoDto fromOrigemManifestacao(OrigemManifestacaoRequest request);
	
	OrigemManifestacao toOrigemManifestacao(OrigemManifestacaoDto dto);


}
