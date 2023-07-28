package br.com.multiinovacoes.gcon.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.mail.Mailer;
import br.com.multiinovacoes.gcon.model.AnexoLai;
import br.com.multiinovacoes.gcon.model.AreaLai;
import br.com.multiinovacoes.gcon.model.AssuntoLai;
import br.com.multiinovacoes.gcon.model.AtendimentoLai;
import br.com.multiinovacoes.gcon.model.EncaminhamentoLai;
import br.com.multiinovacoes.gcon.model.EncaminhamentoRespostaLai;
import br.com.multiinovacoes.gcon.model.Feriado;
import br.com.multiinovacoes.gcon.model.Usuario;
import br.com.multiinovacoes.gcon.model.dto.EncaminhamentoLaiDto;
import br.com.multiinovacoes.gcon.model.dto.EncaminhamentoRespostaLaiSetorDto;
import br.com.multiinovacoes.gcon.model.mapper.EncaminhamentoLaiMapper;
import br.com.multiinovacoes.gcon.model.mapper.ModeloDocumentoLaiMapper;
import br.com.multiinovacoes.gcon.model.request.EncaminhamentoLaiRequest;
import br.com.multiinovacoes.gcon.model.request.RespostaParcialLaiRequest;
import br.com.multiinovacoes.gcon.model.response.ListaModeloDocumentoLaiResponse;
import br.com.multiinovacoes.gcon.repository.AnexoLaiRepository;
import br.com.multiinovacoes.gcon.repository.AreaLaiRepository;
import br.com.multiinovacoes.gcon.repository.AssuntoLaiRepository;
import br.com.multiinovacoes.gcon.repository.AtendimentoLaiRepository;
import br.com.multiinovacoes.gcon.repository.ConfiguracaoLaiRepository;
import br.com.multiinovacoes.gcon.repository.EncaminhamentoLaiRepository;
import br.com.multiinovacoes.gcon.repository.EncaminhamentoRespostaLaiRepository;
import br.com.multiinovacoes.gcon.repository.FeriadoRepository;
import br.com.multiinovacoes.gcon.repository.ModeloDocumentoLaiRepository;
import br.com.multiinovacoes.gcon.repository.SetorLaiRepository;
import br.com.multiinovacoes.gcon.repository.SetorRepository;
import br.com.multiinovacoes.gcon.repository.UsuarioRepository;
import br.com.multiinovacoes.gcon.security.GconSecurity;
import br.com.multiinovacoes.gcon.util.GeradorString;

@Service
@Transactional
public class EncaminhamentoLaiService {
	
	
	@Autowired
	EncaminhamentoLaiMapper encaminhamentoLaiMapper;
	
	@Autowired
	EncaminhamentoLaiRepository encaminhamentoLaiRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	private Mailer mailer;
	
	@Autowired
	private GconSecurity gconSecurity;
	
	@Autowired
	private AreaLaiRepository areaLaiRepository;
	
	@Autowired
	private AtendimentoLaiRepository atendimentoLaiRepository;
	
	@Autowired
	private AssuntoLaiRepository assuntoLaiRepository;
	
	@Autowired
	private FeriadoRepository feriadoRepository;
	
	@Autowired
	private SetorLaiRepository setorLaiRepository;

	@Autowired
	private SetorRepository setorRepository;
	
	@Autowired
	private ModeloDocumentoLaiService modeloDocumentoLaiService;
	
	@Autowired
	private ModeloDocumentoLaiRepository modeloDocumentoLaiRepository;
	
	@Autowired
	private RespostaParcialLaiService respostaParcialLaiService;
	
	@Autowired
	private EncaminhamentoRespostaLaiRepository respostaRepository;
	
	@Autowired
	private ModeloDocumentoLaiMapper modeloDocumentoLaiMapper;
	
	@Autowired
	private ConfiguracaoLaiRepository configuracaoLaiRepository;
	
	@Autowired
	private AnexoLaiRepository anexoLaiRepository;

	private List<AtendimentoLai> getListaEncaminhamentosEnviados(Long orgao, Pageable pageable) {
    	List<EncaminhamentoLai> lista = encaminhamentoLaiRepository.consultaPedidosEncaminhadosLai(orgao, pageable, atendimentoLaiRepository.getNovosAtendimentosLai(orgao));
    	List<AtendimentoLai> novaLista = new ArrayList<>();
    	for (EncaminhamentoLai encaminhamentoLai : lista) {
    		AtendimentoLai atendimentoLai = atendimentoLaiRepository.consultaAtendimentolai(encaminhamentoLai.getAtendimento());
    		atendimentoLai.setDescricaoSetorEncaminhado(setorLaiRepository.getById(encaminhamentoLai.getSetorDestino()).getDescricao());
    		atendimentoLai.setDataPrazoRespostaSetor(encaminhamentoLai.getDataPrazoResposta());
    		atendimentoLai.setDataEnviadoSetor(encaminhamentoLai.getDataEncaminhamento());
    		novaLista.add(atendimentoLai);
    	}
    	return novaLista;
	}

	private List<AtendimentoLai> getListaEncaminhamentosRecebidos(Long orgao, Pageable pageable) {
    	List<EncaminhamentoLai> lista = encaminhamentoLaiRepository.consultaEncaminhamentosRecebidos(orgao, pageable, atendimentoLaiRepository.getNovosAtendimentosLai(orgao));
    	List<AtendimentoLai> novaLista = new ArrayList<>();
    	for (EncaminhamentoLai encaminhamentoLai : lista) {
    		AtendimentoLai atendimentoLai = atendimentoLaiRepository.consultaAtendimentolai(encaminhamentoLai.getAtendimento());
    		atendimentoLai.setDescricaoSetorEncaminhado(setorLaiRepository.getById(encaminhamentoLai.getSetorDestino()).getDescricao());
    		atendimentoLai.setDataPrazoRespostaSetor(encaminhamentoLai.getDataPrazoResposta());
    		atendimentoLai.setDataEnviadoSetor(encaminhamentoLai.getDataEncaminhamento());
    		novaLista.add(atendimentoLai);
    	}
    	return novaLista;
	}

	private List<AtendimentoLai> getListaEncaminhamentosEnviadosVencidos(Long orgao, Pageable pageable) {
    	List<EncaminhamentoLai> lista = encaminhamentoLaiRepository.consultaPedidosEncaminhadosLaiVencidos(orgao, pageable, atendimentoLaiRepository.getNovosAtendimentosLai(orgao));
    	List<AtendimentoLai> novaLista = new ArrayList<>();
    	for (EncaminhamentoLai encaminhamentoLai : lista) {
    		AtendimentoLai atendimentoLai = atendimentoLaiRepository.consultaAtendimentolai(encaminhamentoLai.getAtendimento());
    		atendimentoLai.setDescricaoSetorEncaminhado(setorLaiRepository.getById(encaminhamentoLai.getSetorDestino()).getDescricao());
    		atendimentoLai.setDataPrazoRespostaSetor(encaminhamentoLai.getDataPrazoResposta());
    		atendimentoLai.setDataEnviadoSetor(encaminhamentoLai.getDataEncaminhamento());
    		novaLista.add(atendimentoLai);
    	}
    	return novaLista;
	}

    public Page<AtendimentoLai> getListaAtendimentosEncaminhadosLai(Long orgao, Pageable pageable)
    {   
    	return new PageImpl<>(this.getListaEncaminhamentosEnviados(orgao, pageable), pageable, encaminhamentoLaiRepository.getEncaminhamentosEnviados(orgao));
    }

    public Page<AtendimentoLai> getListaAtendimentosEncaminhadosRecebidosLai(Long orgao, Pageable pageable)
    {   
    	return new PageImpl<>(this.getListaEncaminhamentosRecebidos(orgao, pageable), pageable, encaminhamentoLaiRepository.getEncaminhamentosEnviados(orgao));
    }

    public Page<AtendimentoLai> getListaAtendimentosEncaminhadosLaiVencimento(Long orgao, Pageable pageable)
    {
    	List<AtendimentoLai> lista = this.getListaEncaminhamentosEnviados(orgao, pageable);
    	List<AtendimentoLai> novaLista = new ArrayList<>();
    	for (AtendimentoLai atendimentoLai : lista) {
    		LocalDate dataParametro = atendimentoLai.getDataPrazoRespostaSetor().minusDays(configuracaoLaiRepository.findByOrgao(atendimentoLai.getOrgao()).getQtdDiasAlertaEncaminhamento());
			if (dataParametro.isBefore(LocalDate.now()) || dataParametro.compareTo(LocalDate.now()) == 0) {
				atendimentoLai.setQtdDiasVencimentoSetor((int)diasUteis(dataParametro, atendimentoLai.getDataPrazoRespostaSetor(), atendimentoLai.getOrgao()));
				novaLista.add(atendimentoLai);
			}
		}
    	
    	return new PageImpl<>(novaLista, pageable, encaminhamentoLaiRepository.getEncaminhamentosEnviados(orgao));
    }

    public Page<AtendimentoLai> getListaAtendimentosEncaminhadosLaiVencidos(Long orgao, Pageable pageable)
    {
    	List<AtendimentoLai> lista = this.getListaEncaminhamentosEnviadosVencidos(orgao, pageable);
    	List<AtendimentoLai> novaLista = new ArrayList<>();
    	for (AtendimentoLai atendimentoLai : lista) {
			if (atendimentoLai.getDataPrazoRespostaSetor().isBefore(LocalDate.now())) {
				long diferencaEmDias = ChronoUnit.DAYS.between(atendimentoLai.getDataPrazoRespostaSetor(), LocalDate.now());
				atendimentoLai.setQtdDiasVencimentoSetor((int)diferencaEmDias);
				novaLista.add(atendimentoLai);
			}
		}
    	
    	return new PageImpl<>(novaLista, pageable, encaminhamentoLaiRepository.getEncaminhamentosEnviadosVencidos(orgao));
    }

    public Integer getQtdAtendimentosEncaminhadosLaiVencimento(Long orgao)
    {
    	int qtd = 0;
    	List<EncaminhamentoLai> lista = encaminhamentoLaiRepository.getListaEncaminhamentosEnviados(orgao);
    	for (EncaminhamentoLai encaminhamentoLai : lista) {
    		LocalDate dataParametro = encaminhamentoLai.getDataPrazoResposta().minusDays(configuracaoLaiRepository.findByOrgao(orgao).getQtdDiasAlertaEncaminhamento());
			if (dataParametro.isBefore(LocalDate.now())) {
				qtd++;
			}
		}
    	return qtd;
    }


	public LocalDate adicionarDiasUteis(Integer qtdeDiasAcrescentados, Long codigoOrgao, LocalDate data) {
		List<Feriado> feriadoInfoList = null;
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
	
	public long diasUteis(LocalDate dataInicial, LocalDate dataFinal, Long codigoOrgao) {
		List<Feriado> feriadoInfoList = null;
		long diferencaEmDias = ChronoUnit.DAYS.between(dataInicial, dataFinal);
		long diasUteis = 0;
		while(diferencaEmDias > 0){
			dataInicial = dataInicial.plusDays(1);
			feriadoInfoList = feriadoRepository.findByOrgaoAndDataFeriado(codigoOrgao, dataInicial);
			if (dataInicial.getDayOfWeek() != DayOfWeek.SATURDAY && dataInicial.getDayOfWeek() != DayOfWeek.SUNDAY) {
				diasUteis++;
			}
			if (!feriadoInfoList.isEmpty()){
				diasUteis--;
			}
			diferencaEmDias--;
		}
		return diasUteis;
	}

	
	
	public List<EncaminhamentoLaiDto> getEncaminhamentos(Long codigoAtendimento) {
		return encaminhamentoLaiMapper.fromResponseList(encaminhamentoLaiRepository.findByAtendimento(codigoAtendimento));
	}
	
	   public String listaEncaminhamentoAtivosComRespostaSatisfaz(Long codigoAtendimento){
		   List<EncaminhamentoLai> lista = encaminhamentoLaiRepository.consultaAtendimento(codigoAtendimento);
		   if (lista != null) {
			   for (EncaminhamentoLai enc : lista) {
				   EncaminhamentoRespostaLai encResp = respostaRepository.findByEncaminhamentoAndSatisfaz(enc.getId(), 1);
				   if (encResp != null) {
					   return encResp.getDespacho();
				   }
			   }
		   }else {
			   return "";
		   }
		   return null;
	   }
	   
	   public List<EncaminhamentoLaiDto> listaEncaminhamentoComResposta(Long codigoAtendimento){
		   List<EncaminhamentoLai> lista = encaminhamentoLaiRepository.consultaAtendimento(codigoAtendimento);
		   List<EncaminhamentoLaiDto> listaRetorno = null;
		   EncaminhamentoLaiDto encaminhamentoResposta = null;
		   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		   int seq = 0;
		   if (lista != null) {
			   listaRetorno = new ArrayList<>();
			   for (EncaminhamentoLai enc : lista) {
				   enc.setSequencial(++seq);
				   enc.setDataEncaminhado(enc.getDataEncaminhamento().format(formatter));
				   enc.setDescricaoSetorOrigem(setorRepository.getById(enc.getSetorOrigem()).getDescricao());
				   enc.setDescricaoSetorDestino(setorLaiRepository.getById(enc.getSetorDestino()).getDescricao());
				   listaRetorno.add(encaminhamentoLaiMapper.toDto(enc));
				   EncaminhamentoRespostaLai encResp = respostaRepository.findByEncaminhamento(enc.getId());
				   if (encResp != null) {
					   encaminhamentoResposta = new EncaminhamentoLaiDto();
					   encaminhamentoResposta.setSequencial(++seq); 
					   encaminhamentoResposta.setDataEncaminhamento(encResp.getDataResposta());
					   encaminhamentoResposta.setId(encResp.getEncaminhamento());
					   encaminhamentoResposta.setIdResposta (encResp.getId());
					   encaminhamentoResposta.setDespacho(encResp.getDespacho().replace("##37;", "%"));
					   encaminhamentoResposta.setStatus(encResp.getStatus());
					   encaminhamentoResposta.setSatisfaz(encResp.getSatisfaz());
					   encaminhamentoResposta.setSetorOrigem(enc.getSetorDestino());
					   encaminhamentoResposta.setSetorDestino(enc.getSetorOrigem());
					   encaminhamentoResposta.setModeloDocumento(enc.getModeloDocumento());
					   encaminhamentoResposta.setTipo("R");
					   encaminhamentoResposta.setDescricaoSetorOrigem(enc.getDescricaoSetorDestino());
					   encaminhamentoResposta.setDescricaoSetorDestino(enc.getDescricaoSetorOrigem());
					   encaminhamentoResposta.setDataEncaminhado(encResp.getDataResposta().format(formatter));
					   listaRetorno.add(encaminhamentoResposta);
				   }
			   }
		   }
		   return listaRetorno;
	   }
	   
	
	
	
	public void salvarEncaminhamento(EncaminhamentoLaiRequest request) {

		Optional<AtendimentoLai> atendimento = atendimentoLaiRepository.findById(request.getAtendimento());
		Map<String, Object> variaveis = new HashMap<>();
		String[] anexos = new String[request.getSelectedAnexos().size()];
		Usuario usuario = usuarioRepository.getById(gconSecurity.getIdUsuario());
		EncaminhamentoLai encaminhamento = null;
		encaminhamento = encaminhamentoLaiMapper.fromEncaminhamentoLai(request);
		encaminhamento.setSetorDestino(request.getSetorDestino());
		encaminhamento.setSetorOrigem(usuario.getSetor());
		encaminhamento.setDataEncaminhamento(LocalDateTime.now());
		encaminhamento.setUsuario(usuario.getId());
		encaminhamento.setStatus(0);
		encaminhamento.setSatisfaz(0);
		if (atendimento.isPresent()) {
			encaminhamento.setDataPrazoResposta(adicionarDiasUteis(configuracaoLaiRepository.findByOrgao(atendimento.get().getOrgao()).getQtdDiasEncaminhamento(), atendimento.get().getOrgao(), LocalDate.now()));
			Optional<AreaLai> area = areaLaiRepository.findById(atendimento.get().getArea());
			Optional<AssuntoLai> assunto =  assuntoLaiRepository.findById(atendimento.get().getAssunto());
			variaveis.put("atendimento", atendimento.get());
			if (area.isPresent()) {
				variaveis.put("area", area.get().getDescricao());
			}else {
				variaveis.put("area", "");
			}
			if (assunto.isPresent()) {
				variaveis.put("assunto", assunto.get().getDescricao());
			}else {
				variaveis.put("assunto", "");
			}
		}
		encaminhamento.setEmailEnviado(request.getEmailEnviado());
		encaminhamento.setParametro(GeradorString.getRandomString());
		encaminhamentoLaiRepository.save(encaminhamento);
		
		
		if (request.isEnviarRespostaParcial()) {
			RespostaParcialLaiRequest respostaRequest = new RespostaParcialLaiRequest();
            String modelo = modeloDocumentoLaiService.getModeloDocumento(7L, encaminhamento.getAtendimento()); 
            respostaRequest.setAtendimento(encaminhamento.getAtendimento());
			respostaRequest.setDescricao(modelo);
			respostaRequest.setEmailEnviado(atendimento.get().getEmail());
			respostaRequest.setFormaEnvio(1);
			respostaRequest.setModeloDocumento(7L);
			respostaParcialLaiService.salvarRespostaParcial(respostaRequest);
		}
		
		for (int i = 0; i < request.getSelectedAnexos().size(); i++) {
			Optional<AnexoLai> anexo = anexoLaiRepository.findById(request.getSelectedAnexos().get(i));
			if (anexo.isPresent()) {
				anexos[i] = "C:\\jboss-4.2.1.GA_CLOUD\\server\\default\\deploy\\multiwork.war\\arquivos\\7\\lai\\"+anexo.get().getNomeArquivo();
			}
		}
		
		variaveis.put("despacho", encaminhamento.getDespacho());
		variaveis.put("dataPrazoResposta", encaminhamento.getDataPrazoResposta());
		variaveis.put("url", "https://portalouvidoria.com.br:8501/gconweb/resposta-encaminhamento/"+encaminhamento.getParametro());
		String template = "mail/emailencaminhamentolai";
		mailer.enviarEmail(Arrays.asList(encaminhamento.getEmailEnviado()), template, variaveis, "Serviço de Atendimento ao Cidadão do Sistema FIEPE", "Encaminhamento de Pedido", anexos);
		
	}
	
	public List<EncaminhamentoLai> getListaEncaminhamentosRecebidos(Long orgao, Pageable pageable, int totalRegistros){
		return encaminhamentoLaiRepository.consultaEncaminhamentosRecebidos(orgao, pageable, totalRegistros);
	}

	public Integer getQtdEncaminhamentosRecebidos(Long orgao){
		return encaminhamentoLaiRepository.getEncaminhamentosRecebidos(orgao);
	}

	public EncaminhamentoLaiDto getEncaminhamento(Long codigoEncaminhamento) {
		EncaminhamentoLaiDto encaminhamentoDto = encaminhamentoLaiMapper.toDto(encaminhamentoLaiRepository.consultaEncaminhamento(codigoEncaminhamento));
		encaminhamentoDto.setDescricaoSetorOrigem(setorRepository.getById(encaminhamentoDto.getSetorOrigem()).getDescricao());
		encaminhamentoDto.setDescricaoSetorDestino(setorLaiRepository.getById(encaminhamentoDto.getSetorDestino()).getDescricao());
		return encaminhamentoDto;
	}

	
	public void excluir(Long codigoEncaminhamento) {
		EncaminhamentoLai enc = encaminhamentoLaiRepository.getById(codigoEncaminhamento);
		enc.setStatus(1);
		encaminhamentoLaiRepository.save(enc);
	}
	
	public EncaminhamentoRespostaLaiSetorDto getEncaminhamentoRespostaSetor(String parametro) {
		EncaminhamentoLai encaminhamento = encaminhamentoLaiRepository.findByParametro(parametro);
		EncaminhamentoRespostaLaiSetorDto encaminhamentoRespostaLaiSetorDto = null;
		if (encaminhamento != null) {
			encaminhamentoRespostaLaiSetorDto = new EncaminhamentoRespostaLaiSetorDto();
			EncaminhamentoRespostaLai resposta = respostaRepository.findByEncaminhamento(encaminhamento.getId());
			if (resposta == null) {
				encaminhamentoRespostaLaiSetorDto.setNomeSetorDestino(setorRepository.getById(encaminhamento.getSetorOrigem()).getDescricao());
				AtendimentoLai atendimento = atendimentoLaiRepository.getById(encaminhamento.getAtendimento());
				encaminhamentoRespostaLaiSetorDto.setProtocolo(atendimento.getNumeroProtocolo());
				encaminhamentoRespostaLaiSetorDto.setCodigoAtendimento(atendimento.getId());
				encaminhamentoRespostaLaiSetorDto.setListaModelo(new ListaModeloDocumentoLaiResponse(Optional
						.ofNullable(modeloDocumentoLaiMapper
								.fromResponseList(modeloDocumentoLaiRepository.findByOrgaoAndTipoOrderByDescricaoAsc(atendimento.getOrgao(), 5)))
						.orElse(Collections.emptyList())));
			}else {
				encaminhamentoRespostaLaiSetorDto.setRespondido(true);
			}
		}
		return encaminhamentoRespostaLaiSetorDto;
	}



}
