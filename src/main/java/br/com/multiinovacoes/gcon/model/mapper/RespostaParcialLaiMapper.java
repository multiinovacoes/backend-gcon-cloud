package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.RespostaParcialLai;
import br.com.multiinovacoes.gcon.model.dto.RespostaParcialLaiDto;
import br.com.multiinovacoes.gcon.model.request.RespostaParcialLaiRequest;

@Mapper(componentModel = "spring")
public interface RespostaParcialLaiMapper {
	
	
	List<RespostaParcialLaiDto> fromResponseList(List<RespostaParcialLai> list);
	
	RespostaParcialLaiDto toDto(RespostaParcialLai respostaParcial);
	
	@Mapping(target = "descricaoFormaEnvio", ignore = true)
	@Mapping(target = "descricaoRespostaHTML", ignore = true)
	RespostaParcialLaiDto fromRespostaParcial(RespostaParcialLaiRequest request);   
	
	@Mapping(target = "usuario", ignore = true)
	RespostaParcialLai toRespostaParcial(RespostaParcialLaiDto dto);


}
