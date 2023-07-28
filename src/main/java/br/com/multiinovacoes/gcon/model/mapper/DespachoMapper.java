package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.Despacho;
import br.com.multiinovacoes.gcon.model.dto.DespachoDto;
import br.com.multiinovacoes.gcon.model.request.DespachoRequest;

@Mapper(componentModel = "spring")
public interface DespachoMapper {
	
	
	List<DespachoDto> fromResponseList(List<Despacho> list);
	
	DespachoDto toDto(Despacho despacho);
	  
	@Mapping(target = "descricaoSetorOrigem", ignore = true)
	@Mapping(target = "descricaoSetorDestino", ignore = true)  
	@Mapping(target = "dataFormatada", ignore = true)
	@Mapping(target = "codigoEncaminhamento", ignore = true)
	@Mapping(target = "id", ignore = true)
	DespachoDto fromDespacho(DespachoRequest request); 
	
	@Mapping(target = "anoAtendimento", ignore = true)
	@Mapping(target = "codigoAtendimento", ignore = true)
	@Mapping(target = "orgaoDestino", ignore = true)
	@Mapping(target = "orgaoOrigem", ignore = true)
	Despacho toDespacho(DespachoDto dto);

	

}
