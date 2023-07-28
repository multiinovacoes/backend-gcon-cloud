package br.com.multiinovacoes.gcon.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.PerguntaSatisfacao;
import br.com.multiinovacoes.gcon.model.dto.PerguntaSatisfacaoDto;

@Mapper(componentModel = "spring")
public interface PerguntaSatisfacaoMapper {
	
	
	List<PerguntaSatisfacaoDto> fromResponseList(List<PerguntaSatisfacao> list);
	
	PerguntaSatisfacaoDto toDto(PerguntaSatisfacao perguntaSatisfacao);
	
	@Mapping(target = "orgao", ignore = true)
	@Mapping(target = "status", ignore = true)
	PerguntaSatisfacao toPerguntaSatisfacao(PerguntaSatisfacaoDto dto);


}
