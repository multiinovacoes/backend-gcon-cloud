package br.com.multiinovacoes.gcon.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.AtendimentoLai;
import br.com.multiinovacoes.gcon.model.Feriado;
import br.com.multiinovacoes.gcon.model.ModeloDocumentoLai;
import br.com.multiinovacoes.gcon.model.dto.ModeloDocumentoLaiDto;
import br.com.multiinovacoes.gcon.model.mapper.ModeloDocumentoLaiMapper;
import br.com.multiinovacoes.gcon.model.request.EncaminhamentoLaiRequest;
import br.com.multiinovacoes.gcon.repository.AtendimentoLaiRepository;
import br.com.multiinovacoes.gcon.repository.EncaminhamentoRespostaLaiRepository;
import br.com.multiinovacoes.gcon.repository.FeriadoRepository;
import br.com.multiinovacoes.gcon.repository.ModeloDocumentoLaiRepository;

@Service
@Transactional
public class ModeloDocumentoLaiService {

	@Autowired
	private ModeloDocumentoLaiMapper modeloDocumentoMapper;

	@Autowired
	private ModeloDocumentoLaiRepository modeloDocumentoRepository;

	@Autowired
	private AtendimentoLaiRepository atendimentoRepository;
	
	@Autowired
	private FeriadoRepository feriadoRepository;

	@Autowired
	private EncaminhamentoRespostaLaiRepository encaminhamentoRespostaLaiRepository;
	
	public LocalDate adicionarDiasUteis(Integer qtdeDiasAcrescentados, Long codigoOrgao) {
		List<Feriado> feriadoInfoList = null;
		LocalDate data = LocalDate.now();
		while(qtdeDiasAcrescentados > 0){
			data = data.plusDays(1);
			feriadoInfoList = feriadoRepository.findByOrgaoAndDataFeriado(codigoOrgao, data);
			if (!feriadoInfoList.isEmpty()){
				++qtdeDiasAcrescentados;
			}
			if (data.getDayOfWeek() != DayOfWeek.SATURDAY && data.getDayOfWeek() != DayOfWeek.SUNDAY) {
				--qtdeDiasAcrescentados;
			}
		}
		return data;
	}

	public List<ModeloDocumentoLaiDto> getListaModeloDocumentos(Long orgao) {
		return Optional
				.ofNullable(modeloDocumentoMapper
						.fromResponseList(modeloDocumentoRepository.findByOrgaoOrderByDescricaoAsc(orgao)))
				.orElse(Collections.emptyList());
	}

	public ModeloDocumentoLaiDto getModeloDocumento(Long codigo) {
		return modeloDocumentoMapper.toDto(modeloDocumentoRepository.getById(codigo));
	}

	public String getModeloDocumento(EncaminhamentoLaiRequest encaminhamentoRequest) {
		ModeloDocumentoLai modelo = modeloDocumentoRepository.getById(encaminhamentoRequest.getModeloDocumento());
		return this.getSubstituiExpressoesModelo(modelo, encaminhamentoRequest);
	}

	public String getModeloDocumento(Long codigoModelo, Long codigoAtendimento) {
		ModeloDocumentoLai modelo = modeloDocumentoRepository.getById(codigoModelo);
		return this.getSubstituiExpressoesModelo(modelo, codigoAtendimento);
	}

	public ModeloDocumentoLaiDto salvarModeloDocumento(ModeloDocumentoLaiDto modeloDocumentoDto) {
		return modeloDocumentoMapper
				.toDto(modeloDocumentoRepository.save(modeloDocumentoMapper.toModeloDocumento(modeloDocumentoDto)));
	}

	public List<ModeloDocumentoLaiDto> getPesquisaModeloDocumentoDescricao(Long orgao, String descricao) {
		return Optional
				.ofNullable(modeloDocumentoMapper.fromResponseList(modeloDocumentoRepository
						.findByOrgaoAndDescricaoContainingIgnoreCaseOrderByDescricaoAsc(orgao, descricao)))
				.orElse(Collections.emptyList());
	}

	public List<ModeloDocumentoLaiDto> getPesquisaModeloDocumentoTipo(Long orgao, Integer tipo) {
		return Optional
				.ofNullable(modeloDocumentoMapper
						.fromResponseList(modeloDocumentoRepository.findByOrgaoAndTipoOrderByDescricaoAsc(orgao, tipo)))
				.orElse(Collections.emptyList());
	}

	private String getSubstituiExpressoesModelo(ModeloDocumentoLai modelo, EncaminhamentoLaiRequest encaminhamentoRequest) {
		AtendimentoLai atendimento = atendimentoRepository.getById(encaminhamentoRequest.getAtendimento());
		String modeloDoc = modelo.getModelo().trim();
		
		if (modeloDoc.contains("[NUMERO_PROTOCOLO]")) {
			modeloDoc = modeloDoc.replace("[NUMERO_PROTOCOLO]", atendimento.getNumeroProtocolo());
		}
		
		if (modeloDoc.contains("[ENTIDADE]")) {
			modeloDoc = modeloDoc.replace("[ENTIDADE]", atendimento.getInstituicao() == 1 ? "SENAI" : "SESI");
		}
		
		if (modeloDoc.contains("[DATA_SETOR]")) {
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			modeloDoc = modeloDoc.replace("[DATA_SETOR]",  formatter.format(adicionarDiasUteis(16, atendimento.getOrgao())));
		}
		return modeloDoc;
	}

	private String getSubstituiExpressoesModelo(ModeloDocumentoLai modelo, Long codigoAtendimento) {
		AtendimentoLai atendimento = atendimentoRepository.getById(codigoAtendimento);
		String modeloDoc = modelo.getModelo().trim();

		
		if (modeloDoc.contains("[NOME_CIDADAO]")) {
			modeloDoc = modeloDoc.replace("[NOME_CIDADAO]", atendimento.getNomeSolicitante());
		}

		if (modeloDoc.contains("[NUMERO_PROTOCOLO]")) {
			modeloDoc = modeloDoc.replace("[NUMERO_PROTOCOLO]", atendimento.getNumeroProtocolo());
		}
		
		if (modeloDoc.contains("[ENTIDADE]")) {
			modeloDoc = modeloDoc.replace("[ENTIDADE]", atendimento.getInstituicao() == 1 ? "SENAI" : "SESI");
		}
		
		if (modeloDoc.contains("[DATA_SETOR]")) {
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			modeloDoc = modeloDoc.replace("[DATA_SETOR]",  formatter.format(adicionarDiasUteis(16, atendimento.getOrgao())));
		}
		
		if (modeloDoc.contains("[TEXTO_CONCLUSAO]")) {
			String textoResposta = encaminhamentoRespostaLaiRepository.consultar(codigoAtendimento);
			modeloDoc = modeloDoc.replace("[TEXTO_CONCLUSAO]", textoResposta);
		}

		return modeloDoc;
	}


}
