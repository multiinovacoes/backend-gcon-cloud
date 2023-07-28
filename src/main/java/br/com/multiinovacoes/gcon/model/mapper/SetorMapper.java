package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.Setor;
import br.com.multiinovacoes.gcon.model.dto.SetorDto;
import br.com.multiinovacoes.gcon.model.request.SetorRequest;

@Mapper(componentModel = "spring")
public interface SetorMapper {
	
	
	List<SetorDto> fromResponseList(List<Setor> list);
	SetorDto toDto(Setor area);
	
	@Mapping(target = "id", ignore = true)
	SetorDto fromSetor(SetorRequest request);
	
	@Mapping(target = "tratamentoInicial", ignore = true)
	@Mapping(target = "endereco", ignore = true)
	@Mapping(target = "complemento", ignore = true)
	@Mapping(target = "numero", ignore = true)
	@Mapping(target = "bairro", ignore = true)
	@Mapping(target = "cidade", ignore = true)
	@Mapping(target = "uf", ignore = true)
	@Mapping(target = "cep", ignore = true)
	@Mapping(target = "encaminhamentoAutomatico", ignore = true)
	@Mapping(target = "encaminhamentoInterno", ignore = true)
	Setor toSetor(SetorDto dto);


}
