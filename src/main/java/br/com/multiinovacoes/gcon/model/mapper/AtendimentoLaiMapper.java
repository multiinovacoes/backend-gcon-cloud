package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.multiinovacoes.gcon.model.AtendimentoLai;
import br.com.multiinovacoes.gcon.model.dto.AtendimentoLaiDto;
import br.com.multiinovacoes.gcon.model.request.AtendimentoLaiRequest;

@Mapper(componentModel = "spring")
public interface AtendimentoLaiMapper {
	

	List<AtendimentoLaiDto> fromResponseList(List<AtendimentoLai> list);
	 
	AtendimentoLaiDto toDto(AtendimentoLai atendimentoLai);
	
	AtendimentoLai fromAtendimentoLai(AtendimentoLaiRequest request); 
	
	AtendimentoLai toAtendimentoLai(AtendimentoLaiDto dto);   
	



}
