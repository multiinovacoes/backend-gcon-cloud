package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.multiinovacoes.gcon.model.EncaminhamentoResposta;
import br.com.multiinovacoes.gcon.model.dto.EncaminhamentoRespostaDto;
import br.com.multiinovacoes.gcon.model.request.EncaminhamentoRespostaRequest;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EncaminhamentoRespostaMapper {
	
	
	List<EncaminhamentoRespostaDto> fromResponseList(List<EncaminhamentoRespostaDto> list);
	
	@Mapping(target = "token", ignore = true)
	@Mapping(target = "usuario", ignore = true)
	EncaminhamentoRespostaDto toDto(EncaminhamentoResposta encaminhamento); 
	
	EncaminhamentoRespostaDto fromEncaminhamentoResposta(EncaminhamentoRespostaRequest request);   
	
	EncaminhamentoResposta toEncaminhamento(EncaminhamentoRespostaDto dto);


}
