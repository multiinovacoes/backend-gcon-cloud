package br.com.multiinovacoes.gcon.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.Atendimento;
import br.com.multiinovacoes.gcon.model.Encaminhamento;
import br.com.multiinovacoes.gcon.model.ModeloDocumento;
import br.com.multiinovacoes.gcon.model.TipoExpressoes;
import br.com.multiinovacoes.gcon.model.dto.ModeloDocumentoDto;
import br.com.multiinovacoes.gcon.model.mapper.ModeloDocumentoMapper;
import br.com.multiinovacoes.gcon.model.request.EncaminhamentoRequest;
import br.com.multiinovacoes.gcon.repository.AreaRepository;
import br.com.multiinovacoes.gcon.repository.AssuntoRepository;
import br.com.multiinovacoes.gcon.repository.AtendimentoRepository;
import br.com.multiinovacoes.gcon.repository.DespachoRepository;
import br.com.multiinovacoes.gcon.repository.EncaminhamentoRepository;
import br.com.multiinovacoes.gcon.repository.EncaminhamentoRespostaRepository;
import br.com.multiinovacoes.gcon.repository.ModeloDocumentoRepository;
import br.com.multiinovacoes.gcon.repository.OrgaoRepository;
import br.com.multiinovacoes.gcon.repository.RespostaParcialRepository;
import br.com.multiinovacoes.gcon.repository.SetorRepository;
import br.com.multiinovacoes.gcon.repository.TipoExpressoesRepository;

@Service
@Transactional
public class ModeloDocumentoService {

	@Autowired
	private ModeloDocumentoMapper modeloDocumentoMapper;

	@Autowired
	private ModeloDocumentoRepository modeloDocumentoRepository;

	@Autowired
	private TipoExpressoesRepository tipoExpressoesRepository;

	@Autowired
	private AtendimentoRepository atendimentoRepository;

	@Autowired
	private SetorRepository setorRepositoty;
	
	@Autowired
	private OrgaoRepository orgaoRepository;
	
	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private EncaminhamentoRepository encaminhamentoRepository;
	
	@Autowired
	private RespostaParcialRepository respostaParcialRepository;
	
	@Autowired
	private DespachoRepository despachoRepository;
	
	@Autowired
	private EncaminhamentoRespostaRepository encaminhamentoRespostaRepository;
	
	@Autowired
	private AssuntoRepository assuntoRepository;

	public List<ModeloDocumentoDto> getListaModeloDocumentos(Long orgao) {
		return Optional
				.ofNullable(modeloDocumentoMapper
						.fromResponseList(modeloDocumentoRepository.findByOrgaoOrderByDescricaoAsc(orgao)))
				.orElse(Collections.emptyList());
	}

	public ModeloDocumentoDto getModeloDocumento(Long codigo) {
		return modeloDocumentoMapper.toDto(modeloDocumentoRepository.getById(codigo));
	}

	public String getModeloDocumento(EncaminhamentoRequest encaminhamentoRequest) {
		ModeloDocumento modelo = modeloDocumentoRepository.getById(encaminhamentoRequest.getModeloDocumento());
		return this.getSubstituiExpressoesModelo(modelo, encaminhamentoRequest);
	}

	public String getModeloDocumento(Long codigoModelo, Long codigoAtendimento) {
		ModeloDocumento modelo = modeloDocumentoRepository.getById(codigoModelo);
		return this.getSubstituiExpressoesModelo(modelo, codigoAtendimento);
	}

	public ModeloDocumentoDto salvarModeloDocumento(ModeloDocumentoDto modeloDocumentoDto) {
		if (modeloDocumentoDto.getId() == null) {
			modeloDocumentoDto.setId(modeloDocumentoRepository.getMaxId()+1);
		}
		return modeloDocumentoMapper
				.toDto(modeloDocumentoRepository.save(modeloDocumentoMapper.toModeloDocumento(modeloDocumentoDto)));
	}

	public List<ModeloDocumentoDto> getPesquisaModeloDocumentoDescricao(Long orgao, String descricao) {
		return Optional
				.ofNullable(modeloDocumentoMapper.fromResponseList(modeloDocumentoRepository
						.findByOrgaoAndDescricaoContainingIgnoreCaseOrderByDescricaoAsc(orgao, descricao)))
				.orElse(Collections.emptyList());
	}

	public List<ModeloDocumentoDto> getPesquisaModeloDocumentoTipo(Long orgao, Integer tipo) {
		return Optional
				.ofNullable(modeloDocumentoMapper
						.fromResponseList(modeloDocumentoRepository.findByOrgaoAndTipoOrderByDescricaoAsc(orgao, tipo)))
				.orElse(Collections.emptyList());
	}

	private String getSubstituiExpressoesModelo(ModeloDocumento modelo, EncaminhamentoRequest encaminhamentoRequest) {
		int InicioChaves = 0;
		int FinalChaves = 0;
		String valorCampo = "";
		Atendimento atendimento = atendimentoRepository.getById(encaminhamentoRequest.getAtendimento());
		String modeloDoc = modelo.getModelo().trim();
		Optional<TipoExpressoes> tipoExpressoes = Optional.empty();
		int i = 0;
		while ((i >= 0) && (i < modeloDoc.length() - 1)) {
			if ((modeloDoc.substring(i, i + 1 + 1)).equals("{{")) {
				InicioChaves = i;
			}
			if ((modeloDoc.substring(i, i + 1 + 1)).equals("}}")) {
				FinalChaves = i + 1;
			}

			if ((InicioChaves > 0) && (FinalChaves > 0)) {
				String expressao = modeloDoc.substring(InicioChaves, FinalChaves + 1);
				tipoExpressoes = tipoExpressoesRepository.findByExpressao(expressao);
				if (tipoExpressoes.isPresent()) {
					if (tipoExpressoes.get().getObjetoAtendimento().equals("Atendimento")) {
						valorCampo = atendimentoRepository.consultar(tipoExpressoes.get().getCampo(),
								atendimento.getId(), atendimento.getOrgao());
						modeloDoc = modeloDoc.substring(0, InicioChaves) + " " + valorCampo + " "
								+ modeloDoc.substring(FinalChaves + 1, modeloDoc.length());
					}
					if (tipoExpressoes.get().getObjetoAtendimento().equals("Setor")) {
						valorCampo = setorRepositoty.filtrar(tipoExpressoes.get().getCampo(),
								encaminhamentoRequest.getSetorDestino(), atendimento.getOrgao());
						modeloDoc = modeloDoc.substring(0, InicioChaves) + " " + valorCampo + " "
								+ modeloDoc.substring(FinalChaves + 1, modeloDoc.length());
					}
					if (tipoExpressoes.get().getObjetoAtendimento().equals("Orgao")) {
						valorCampo = orgaoRepository.filtrar(tipoExpressoes.get().getCampo(),
								atendimento.getOrgao());
						modeloDoc = modeloDoc.substring(0, InicioChaves) + " " + valorCampo + " "
								+ modeloDoc.substring(FinalChaves + 1, modeloDoc.length());
					}
					if (tipoExpressoes.get().getObjetoAtendimento().equals("Assunto")) {
						valorCampo = assuntoRepository.getById(atendimento.getAssunto()).getDescricao();
						modeloDoc = modeloDoc.substring(0, InicioChaves) + " " + valorCampo + " "
								+ modeloDoc.substring(FinalChaves + 1, modeloDoc.length());
					}
					if (tipoExpressoes.get().getObjetoAtendimento().equals("Area")) {
						valorCampo = areaRepository.getById(atendimento.getArea()).getDescricao();
						modeloDoc = modeloDoc.substring(0, InicioChaves) + " " + valorCampo + " "
								+ modeloDoc.substring(FinalChaves + 1, modeloDoc.length());
					}
					
				}
				i = 0;
				InicioChaves = 0;
				FinalChaves = 0;
			}
			i++;
		}
		return modeloDoc;
	}
	
	
	private String getSubstituiExpressoesModelo(ModeloDocumento modelo, Long codigoAtendimento) {
		int InicioChaves = 0;
		int FinalChaves = 0;
		String valorCampo = "";
		Atendimento atendimento = atendimentoRepository.getById(codigoAtendimento);
		String modeloDoc = modelo.getModelo().trim();
		Optional<TipoExpressoes> tipoExpressoes = Optional.empty();
		int i = 0;
		long codigoSetorEncaminhado = 0;
		List<Encaminhamento> encaminhamentos = encaminhamentoRepository.consultaAtendimento(codigoAtendimento);
		if (encaminhamentos != null && encaminhamentos.get(encaminhamentos.size()-1).getCancelado() == 0) {
			codigoSetorEncaminhado = encaminhamentos.get(encaminhamentos.size()-1).getSetorDestino();
		}
		while ((i >= 0) && (i < modeloDoc.length() - 1)) {
			if ((modeloDoc.substring(i, i + 1 + 1)).equals("{{")) {
				InicioChaves = i;
			}
			if ((modeloDoc.substring(i, i + 1 + 1)).equals("}}")) {
				FinalChaves = i + 1;
			}

			if ((InicioChaves > 0) && (FinalChaves > 0)) {
				String expressao = modeloDoc.substring(InicioChaves, FinalChaves + 1);
				tipoExpressoes = tipoExpressoesRepository.findByExpressao(expressao);
				if (tipoExpressoes.isPresent()) {
					if (tipoExpressoes.get().getObjetoAtendimento().equals("Atendimento")) {
						valorCampo = atendimentoRepository.consultar(tipoExpressoes.get().getCampo(),
								atendimento.getId(), atendimento.getOrgao());
						modeloDoc = modeloDoc.substring(0, InicioChaves) + " " + valorCampo + " "
								+ modeloDoc.substring(FinalChaves + 1, modeloDoc.length());
					}
					if (tipoExpressoes.get().getCampo().equals("DATAENCAMINHAMENTO")) {
						valorCampo = encaminhamentoRepository.consultar("DAENCAMINHAMENTO",
								atendimento.getId());
						modeloDoc = modeloDoc.substring(0, InicioChaves) + " " + valorCampo + " "
								+ modeloDoc.substring(FinalChaves + 1, modeloDoc.length());
					}
					if (tipoExpressoes.get().getCampo().equals("DESCRICAO_RETORNO_ENCAMINHAMENTO")) {
						valorCampo = encaminhamentoRespostaRepository.consultar(atendimento.getId());
						modeloDoc = modeloDoc.substring(0, InicioChaves) + " " + valorCampo + " "
								+ modeloDoc.substring(FinalChaves + 1, modeloDoc.length());
					}
					if (tipoExpressoes.get().getObjetoAtendimento().equals("Setor")) {
						
						if (tipoExpressoes.get().getCampo().equals("SETOR_ENCAMINHADO")) {
							valorCampo = setorRepositoty.filtrar("DESCRICAO",
									codigoSetorEncaminhado, atendimento.getOrgao());
						}else {
							valorCampo = setorRepositoty.filtrar(tipoExpressoes.get().getCampo(),
									codigoSetorEncaminhado, atendimento.getOrgao());
						}
						modeloDoc = modeloDoc.substring(0, InicioChaves) + " " + valorCampo + " "
								+ modeloDoc.substring(FinalChaves + 1, modeloDoc.length());
					}
					if (tipoExpressoes.get().getObjetoAtendimento().equals("Orgao")) {
						valorCampo = orgaoRepository.filtrar(tipoExpressoes.get().getCampo(),
								atendimento.getOrgao());
						modeloDoc = modeloDoc.substring(0, InicioChaves) + " " + valorCampo + " "
								+ modeloDoc.substring(FinalChaves + 1, modeloDoc.length());
					}
					if (tipoExpressoes.get().getObjetoAtendimento().equals("Assunto")) {
						valorCampo = assuntoRepository.getById(atendimento.getAssunto()).getDescricao();
						modeloDoc = modeloDoc.substring(0, InicioChaves) + " " + valorCampo + " "
								+ modeloDoc.substring(FinalChaves + 1, modeloDoc.length());
					}
					if (tipoExpressoes.get().getObjetoAtendimento().equals("Area")) {
						valorCampo = areaRepository.getById(atendimento.getArea()).getDescricao();
						modeloDoc = modeloDoc.substring(0, InicioChaves) + " " + valorCampo + " "
								+ modeloDoc.substring(FinalChaves + 1, modeloDoc.length());
					}
					if (tipoExpressoes.get().getObjetoAtendimento().equals("Resposta")) {
						valorCampo = respostaParcialRepository.consultar(tipoExpressoes.get().getCampo(),
								atendimento.getId());
						modeloDoc = modeloDoc.substring(0, InicioChaves) + " " + valorCampo + " "
								+ modeloDoc.substring(FinalChaves + 1, modeloDoc.length());
					}
					if (tipoExpressoes.get().getObjetoAtendimento().equals("Despacho")) {
						valorCampo = despachoRepository.consultar(tipoExpressoes.get().getCampo(),
								atendimento.getId());
						modeloDoc = modeloDoc.substring(0, InicioChaves) + " " + valorCampo + " "
								+ modeloDoc.substring(FinalChaves + 1, modeloDoc.length());
					}
					
					
				}
				i = 0;
				InicioChaves = 0;
				FinalChaves = 0;
			}
			i++;
		}
		return modeloDoc;
	}
	

}
