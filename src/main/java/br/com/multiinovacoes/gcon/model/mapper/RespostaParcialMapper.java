package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.RespostaParcial;
import br.com.multiinovacoes.gcon.model.dto.RespostaParcialDto;
import br.com.multiinovacoes.gcon.model.request.RespostaParcialRequest;

@Mapper(componentModel = "spring")
public interface RespostaParcialMapper {
	
	
	List<RespostaParcialDto> fromResponseList(List<RespostaParcial> list);
	
	RespostaParcialDto toDto(RespostaParcial respostaParcial);
	
	@Mapping(target = "descricaoFormaEnvio", ignore = true)
	@Mapping(target = "cancelado", ignore = true)
	@Mapping(target = "anoAtendimento", ignore = true)
	@Mapping(target = "codigoAtendimento", ignore = true)
	@Mapping(target = "descricaoRespostaHTML", ignore = true)
	@Mapping(target = "id", ignore = true)
	RespostaParcialDto fromRespostaParcial(RespostaParcialRequest request);   
	
	@Mapping(target = "usuario", ignore = true)
	@Mapping(target = "descricaoFormaEnvio", ignore = true)
	RespostaParcial toRespostaParcial(RespostaParcialDto dto);


}
