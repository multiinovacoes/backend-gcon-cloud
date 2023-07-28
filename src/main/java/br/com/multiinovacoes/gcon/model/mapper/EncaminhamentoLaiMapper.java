package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.multiinovacoes.gcon.model.EncaminhamentoLai;
import br.com.multiinovacoes.gcon.model.dto.EncaminhamentoLaiDto;
import br.com.multiinovacoes.gcon.model.request.EncaminhamentoLaiRequest;

@Mapper(componentModel = "spring")
public interface EncaminhamentoLaiMapper {
	

	List<EncaminhamentoLaiDto> fromResponseList(List<EncaminhamentoLai> list);
	 
	EncaminhamentoLaiDto toDto(EncaminhamentoLai atendimentoLai);
	
	EncaminhamentoLai fromEncaminhamentoLai(EncaminhamentoLaiRequest request); 
	
	EncaminhamentoLai toEncaminhamentoLai(EncaminhamentoLaiDto dto);   
	



}
