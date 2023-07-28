package br.com.multiinovacoes.gcon.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.multiinovacoes.gcon.model.Atendimento;
import br.com.multiinovacoes.gcon.model.dto.AtendimentoDto;
import br.com.multiinovacoes.gcon.model.request.AtendimentoRequest;
import br.com.multiinovacoes.gcon.model.request.AtendimentoSiteRequest;
import br.com.multiinovacoes.gcon.model.response.AtendimentoConectaResponse;

@Mapper(componentModel = "spring")
public interface AtendimentoMapper {

	 
	@Mapping(target = "sigilo", ignore = true)
	@Mapping(target = "dataNasci", ignore = true)
	@Mapping(target = "descricaoUsuarioAlteracao", ignore = true)
	@Mapping(target = "qtdAnexos", ignore = true)
	@Mapping(target = "qtdHistoricoUsuario", ignore = true)
	@Mapping(target = "camposDesabilitados", ignore = true)
	@Mapping(target = "habilitaBotaoConcluir", ignore = true)
	@Mapping(target = "manifestacaoClassificada", ignore = true)
	@Mapping(target = "manifestacaoConcluida", ignore = true)
	@Mapping(target = "identificado", ignore = true)
	@Mapping(target = "listaAnexoDto", ignore = true)
	@Mapping(target = "modeloResposta", ignore = true)
	@Mapping(target = "complementoDescricao", ignore = true)
	@Mapping(target = "descricaoQuando", ignore = true)
	@Mapping(target = "manifestante", ignore = true)
	@Mapping(target = "modoResposta", ignore = true)
	@Mapping(target = "numero", ignore = true)
	@Mapping(target = "situacao", ignore = true)
	AtendimentoDto toDto(Atendimento area);   
	 
	
	AtendimentoDto fromAtendimentoDto(AtendimentoRequest request);
	
	@Mapping(target = "dataPrazoRespostaSetor", ignore = true)
	@Mapping(target = "dataEnviadoSetor", ignore = true)
	@Mapping(target = "descricaoSetorEncaminhado", ignore = true)
	@Mapping(target = "qtdDiasVencimentoSetor", ignore = true)
	@Mapping(target = "codigoModelo", ignore = true)
	@Mapping(target = "descricaoSolucao", ignore = true)
	@Mapping(target = "descricaoUsuario", ignore = true)
	@Mapping(target = "outroLocal", ignore = true)
	@Mapping(target = "providencia", ignore = true)
	@Mapping(target = "totalDias", ignore = true)
	Atendimento fromAtendimento(AtendimentoRequest request);
	
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "numeroAtendimento", ignore = true)
	@Mapping(target = "numeroProtocolo", ignore = true)
	@Mapping(target = "origemManifestacao", ignore = true)
	@Mapping(target = "sequencialOrgao", ignore = true)
	@Mapping(target = "anoAtendimento", ignore = true)
	@Mapping(target = "estadoCivil", ignore = true)
	@Mapping(target = "dddFone", ignore = true)
	@Mapping(target = "fone", ignore = true)
	@Mapping(target = "descricao", ignore = true)
	@Mapping(target = "senhaManifestante", ignore = true)
	AtendimentoDto fromAtendimento(AtendimentoSiteRequest request); 
	
	
	@Mapping(target = "dataEnviadoSetor", ignore = true)
	@Mapping(target = "dataPrazoRespostaSetor", ignore = true)
	@Mapping(target = "descricaoSetorEncaminhado", ignore = true)
	@Mapping(target = "qtdDiasVencimentoSetor", ignore = true)
	@Mapping(target = "codigoModelo", ignore = true)
	@Mapping(target = "outroLocal", ignore = true)
	@Mapping(target = "totalDias", ignore = true)
	@Mapping(target = "descricaoSolucao", ignore = true)
	@Mapping(target = "providencia", ignore = true)
	@Mapping(target = "descricaoUsuario", ignore = true)
	Atendimento toAtendimento(AtendimentoDto dto);   

	AtendimentoConectaResponse fromResponse(AtendimentoDto atendimentodto);

}
