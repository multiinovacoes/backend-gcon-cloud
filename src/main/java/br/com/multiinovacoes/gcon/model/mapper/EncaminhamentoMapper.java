package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.Encaminhamento;
import br.com.multiinovacoes.gcon.model.dto.EncaminhamentoDto;
import br.com.multiinovacoes.gcon.model.request.EncaminhamentoRequest;

@Mapper(componentModel = "spring")
public interface EncaminhamentoMapper {
	
	
	List<EncaminhamentoDto> fromResponseList(List<Encaminhamento> list);
	
	@Mapping(target = "diasUteisResposta", ignore = true)
	@Mapping(target = "enviarRespostaParcial", ignore = true)
	@Mapping(target = "idResposta", ignore = true)
	@Mapping(target = "satisfaz", ignore = true)
	@Mapping(target = "tipo", ignore = true)
	@Mapping(target = "tokenSetor", ignore = true)
	EncaminhamentoDto toDto(Encaminhamento encaminhamento);
	
	@Mapping(target = "cancelado", ignore = true)
	@Mapping(target = "dataEncaminhado", ignore = true)
	@Mapping(target = "dataEncaminhamento", ignore = true)
	@Mapping(target = "dataPrazo", ignore = true)
	@Mapping(target = "descricaoSetorDestino", ignore = true)
	@Mapping(target = "descricaoSetorOrigem", ignore = true)
	@Mapping(target = "diasUteisResposta", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "idResposta", ignore = true)
	@Mapping(target = "orgaoDestino", ignore = true)
	@Mapping(target = "orgaoOrigem", ignore = true)
	@Mapping(target = "parametro", ignore = true)
	@Mapping(target = "recebeu", ignore = true)
	@Mapping(target = "satisfaz", ignore = true)
	@Mapping(target = "sequencial", ignore = true)
	@Mapping(target = "status", ignore = true)
	@Mapping(target = "tipo", ignore = true)
	@Mapping(target = "tokenSetor", ignore = true)
	EncaminhamentoDto fromEncaminhamento(EncaminhamentoRequest request);  
	
	Encaminhamento toEncaminhamento(EncaminhamentoDto dto);


}
