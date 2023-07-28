package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.Bairro;
import br.com.multiinovacoes.gcon.model.dto.BairroDto;
import br.com.multiinovacoes.gcon.model.request.BairroRequest;

@Mapper(componentModel = "spring")
public interface BairroMapper {
	
	
	List<BairroDto> fromResponseList(List<Bairro> list);
	
	@Mapping(target = "idOrgao", ignore = true)
	BairroDto toDto(Bairro bairro);
	 
	@Mapping(target = "id", ignore = true)
	BairroDto fromBairro(BairroRequest request);
	
	@Mapping(target = "orgao", ignore = true)
	Bairro toBairro(BairroDto dto);


}
