package br.com.multiinovacoes.gcon.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.enums.StatusAtendimentoEnum;
import br.com.multiinovacoes.gcon.enums.StatusRecursoEnum;
import br.com.multiinovacoes.gcon.enums.TipoRecursoEnum;
import br.com.multiinovacoes.gcon.mail.Mailer;
import br.com.multiinovacoes.gcon.model.AnexoLai;
import br.com.multiinovacoes.gcon.model.AtendimentoLai;
import br.com.multiinovacoes.gcon.model.ConfiguracaoLai;
import br.com.multiinovacoes.gcon.model.Feriado;
import br.com.multiinovacoes.gcon.model.ModeloDocumentoLai;
import br.com.multiinovacoes.gcon.model.RecursoLai;
import br.com.multiinovacoes.gcon.model.dto.ListaAnexoDto;
import br.com.multiinovacoes.gcon.model.dto.RecursoLaiDto;
import br.com.multiinovacoes.gcon.model.dto.RespostaRecursoDto;
import br.com.multiinovacoes.gcon.model.mapper.ModeloDocumentoLaiMapper;
import br.com.multiinovacoes.gcon.model.mapper.RecursoLaiMapper;
import br.com.multiinovacoes.gcon.model.request.RecursoLaiRequest;
import br.com.multiinovacoes.gcon.model.response.ListaModeloDocumentoLaiResponse;
import br.com.multiinovacoes.gcon.repository.AnexoLaiRepository;
import br.com.multiinovacoes.gcon.repository.AtendimentoLaiRepository;
import br.com.multiinovacoes.gcon.repository.ConfiguracaoLaiRepository;
import br.com.multiinovacoes.gcon.repository.FeriadoRepository;
import br.com.multiinovacoes.gcon.repository.ModeloDocumentoLaiRepository;
import br.com.multiinovacoes.gcon.repository.RecursoLaiRepository;
import br.com.multiinovacoes.gcon.repository.SetorLaiRepository;
import br.com.multiinovacoes.gcon.security.GconSecurity;



@Service
@Transactional
public class RecursoLaiService {
	
	
	@Autowired
	private RecursoLaiMapper recursoLaiMapper;
	
	@Autowired
	private RecursoLaiRepository recursoLaiRepository;
	
	@Autowired
	private ConfiguracaoLaiRepository configuracaoLaiRepository;
	
	@Autowired
	private Mailer mailer;
	
	@Autowired
	private FeriadoRepository feriadoRepository;
	
	@Autowired
	private AtendimentoLaiRepository atendimentoLaiRepository;
	
	@Autowired
	GconSecurity gconSecurity;
	
	@Autowired
	SetorLaiRepository setorLaiRepository;
	
	@Autowired
	private ModeloDocumentoLaiMapper modeloDocumentoLaiMapper;
	
	@Autowired
	private ModeloDocumentoLaiRepository modeloDocumentoLaiRepository;
	
	@Autowired
	private AnexoLaiRepository anexoLaiRepository;


	
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

	public Page<AtendimentoLai> getListaRecursosAbertosProxVencimento(Long orgao, Pageable pageable) {
    	List<RecursoLai> lista = recursoLaiRepository.getListaRecursoAberto(orgao, StatusRecursoEnum.RECURSO_ANDAMENTO.getCodigo());
    	List<AtendimentoLai> novaLista = new ArrayList<>();
    	for (RecursoLai recursoLai : lista) {
    		AtendimentoLai atendimentoLai = atendimentoLaiRepository.consultaAtendimentolai(recursoLai.getAtendimento());
    		LocalDate dataParametro = recursoLai.getDataPrazo().minusDays(configuracaoLaiRepository.findByOrgao(atendimentoLai.getOrgao()).getQtdDiasAlertaRecurso());
			if (dataParametro.isBefore(LocalDate.now()) || dataParametro.compareTo(LocalDate.now()) == 0) {
				atendimentoLai.setQtdDiasVencimentoSetor((int)diasUteis(dataParametro, recursoLai.getDataPrazo(), atendimentoLai.getOrgao()));
				atendimentoLai.setDataPrazoRespostaSetor(recursoLai.getDataPrazo());
				novaLista.add(atendimentoLai);
			}
    	}
    	return new PageImpl<>(novaLista, pageable, atendimentoLaiRepository.getAtendimentosLaiRecursoAberto(orgao, StatusRecursoEnum.RECURSO_ANDAMENTO.getCodigo(),
    			TipoRecursoEnum.RECURSO_1_INSTANCIA.getCodigo()));
	}
	
	   public Integer getQtdRecursosLaiVencimento(Long orgao)
	    {
	    	int qtd = 0;
	    	List<RecursoLai> lista = recursoLaiRepository.getListaRecursoAberto(orgao, StatusRecursoEnum.RECURSO_ANDAMENTO.getCodigo());
	    	for (RecursoLai recursoLai : lista) {
	    		LocalDate dataParametro = recursoLai.getDataPrazo().minusDays(configuracaoLaiRepository.findByOrgao(orgao).getQtdDiasAlertaRecurso());
				if (dataParametro.isBefore(LocalDate.now()) || dataParametro.compareTo(LocalDate.now()) == 0) {
					qtd++;
				}
			}
	    	return qtd;
	    }
	
	
	public RecursoLaiDto getRecursoLai(Long codigoRecurso) {
		RecursoLaiDto recursoLaiDto = recursoLaiMapper.toDto(recursoLaiRepository.findById(codigoRecurso).get());
		if (StatusRecursoEnum.RECURSO_ANDAMENTO.getCodigo().equals(recursoLaiDto.getStatus())){
			recursoLaiDto.setDataResposta(LocalDateTime.now());
		}
		return recursoLaiDto;
	}

	 
	public RecursoLaiDto getAutorizaRecurso(Long codigoAtendimento) {
		ConfiguracaoLai conf = configuracaoLaiRepository.findByOrgao(7l);
		RecursoLaiDto recursoLaiDto = new RecursoLaiDto();
		Optional<AtendimentoLai> atendimento = atendimentoLaiRepository.findById(codigoAtendimento);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		if (atendimento.isPresent()) {
			
			List<RecursoLai> recursoLai = recursoLaiRepository.findByAtendimento(codigoAtendimento);
			
			if (!recursoLai.isEmpty()) {

				if (recursoLai.size() < 2) {
					
					for (RecursoLai recursoLai2 : recursoLai) {
						
						if (recursoLai2.getRespostaRecurso() == null) {
							recursoLaiDto.setRecursoAutorizado(false);
							recursoLaiDto.setMensagem("Abertura de recurso não permitida. O recurso de 1ª instância ainda não foi respondido!");
						}else {
							LocalDate dataLimiteRecurso = this.adicionarDiasUteis(conf.getQtdDiasPermitirAbrirRecurso(), conf.getOrgao(), recursoLai2.getDataResposta().toLocalDate());
							if (dataLimiteRecurso.isAfter(LocalDate.now()) || dataLimiteRecurso.compareTo(LocalDate.now()) == 0) {
								recursoLaiDto.setDataPrazo(this.adicionarDiasUteis(conf.getQtdDiasVencerRecurso(), conf.getOrgao(), LocalDate.now()));
								recursoLaiDto.setDataRecurso(LocalDateTime.now());
								recursoLaiDto.setDataResposta(null);
								recursoLaiDto.setJustificativa(null);
								recursoLaiDto.setRecursoAutorizado(true);
								recursoLaiDto.setAtendimento(codigoAtendimento);
								recursoLaiDto.setRespostaRecurso(null);
								recursoLaiDto.setStatus(StatusRecursoEnum.RECURSO_ANDAMENTO.getCodigo());
								recursoLaiDto.setTipo(TipoRecursoEnum.RECURSO_2_INSTANCIA.getCodigo());
							}else {
								recursoLaiDto.setRecursoAutorizado(false);
								recursoLaiDto.setMensagem("Abertura de recurso não permitida. O prazo para abertura de recurso foi expirado em: " + dataLimiteRecurso.format(formatter));
							}
						}
					}
				}else {
					recursoLaiDto.setRecursoAutorizado(false);
					recursoLaiDto.setMensagem("Abertura de recurso não permitida. Todas as instâncias recursais para este pedido foram utilizadas!");
				}
			}else {
				LocalDate dataLimiteRecurso = LocalDate.now();
				if (atendimento.get().getStatusAtendimento().equals(StatusAtendimentoEnum.STATUS_RESOLVIDO.getCodigo())) {
					dataLimiteRecurso = this.adicionarDiasUteis(conf.getQtdDiasPermitirAbrirRecurso(), conf.getOrgao(), atendimento.get().getDataConclusao().toLocalDate());
					recursoLaiDto.setRecursoAutorizado(true);
				}else{
					if (atendimento.get().getDataPrazo().isBefore(LocalDate.now())) {
						dataLimiteRecurso = this.adicionarDiasUteis(conf.getQtdDiasPermitirAbrirRecurso(), conf.getOrgao(), atendimento.get().getDataPrazo());
						recursoLaiDto.setRecursoAutorizado(true);
					}else {
						recursoLaiDto.setRecursoAutorizado(false);
						recursoLaiDto.setMensagem("Abertura de recurso não permitda. O pedido de informação ainda está dentro do prazo previsto de resposta!");
					}
				} 
				if (recursoLaiDto.getRecursoAutorizado()) {
					if (dataLimiteRecurso.isAfter(LocalDate.now()) || dataLimiteRecurso.compareTo(LocalDate.now()) == 0) {
						recursoLaiDto.setDataPrazo(this.adicionarDiasUteis(conf.getQtdDiasVencerRecurso(), conf.getOrgao(), LocalDate.now()));
						recursoLaiDto.setDataRecurso(LocalDateTime.now());
						recursoLaiDto.setDataResposta(null);
						recursoLaiDto.setJustificativa(null);
						recursoLaiDto.setAtendimento(codigoAtendimento);
						recursoLaiDto.setRespostaRecurso(null);
						recursoLaiDto.setStatus(StatusRecursoEnum.RECURSO_ANDAMENTO.getCodigo());
						recursoLaiDto.setTipo(TipoRecursoEnum.RECURSO_1_INSTANCIA.getCodigo());
					}else {
						recursoLaiDto.setRecursoAutorizado(false);
						recursoLaiDto.setMensagem("Abertura de recurso não permitida. O prazo para abertura de recurso foi expirado em: " + dataLimiteRecurso.format(formatter));
					}
				}
			}
		}
		return recursoLaiDto;
	}

	
	
	public RecursoLaiDto salvarRecursoLai(RecursoLaiRequest recursoLaiRequest) throws IOException{
			ConfiguracaoLai conf = configuracaoLaiRepository.findByOrgao(7l);
			RecursoLai recursoLai = recursoLaiMapper.fromRecursoLai(recursoLaiRequest);
		    recursoLai.setDataPrazo(adicionarDiasUteis(conf.getQtdDiasVencerRecurso(), conf.getOrgao(), LocalDate.now()));
		    recursoLai.setDataRecurso(LocalDateTime.now());
		    recursoLai.setDataResposta(null);
		    recursoLai.setIdUsuarioResposta(null);
		    recursoLai.setRespostaRecurso(null);
		    recursoLai.setStatus(StatusRecursoEnum.RECURSO_ANDAMENTO.getCodigo());
		    
		    recursoLaiRepository.save(recursoLai);
		    
		    Optional<AtendimentoLai> atendimento = atendimentoLaiRepository.findById(recursoLai.getAtendimento());
		    
			String template = null;
			Map<String, Object> variaveis = new HashMap<>();
			variaveis.put("atendimento", atendimento.get());
			template = "mail/emailcadastrorecurso";
			mailer.enviarEmail(Arrays.asList(atendimento.get().getEmail()), template, variaveis, "Serviço de Atendimento ao Cidadão do Sistema FIEPE", "Abertura de Recurso",  null);
		return recursoLaiMapper.toDto(recursoLai);
	}
	
	public RecursoLaiDto responderRecursoLai(RecursoLaiRequest recursoLaiRequest) throws IOException{
		RecursoLai recursoLai = recursoLaiMapper.fromRecursoLai(recursoLaiRequest);
	    recursoLai.setIdUsuarioResposta(gconSecurity.getIdUsuario());
	    
	    recursoLai.setStatus(StatusRecursoEnum.RECURSO_RESOLVIDO.getCodigo());
	    recursoLaiRepository.save(recursoLai);
	    
	    Optional<AtendimentoLai> atendimento = atendimentoLaiRepository.findById(recursoLai.getAtendimento());
	    
	    String[] anexosEmail = null;
	    
	    if (recursoLaiRequest.getListaAnexoDto() != null) {
	    	anexosEmail = new String[recursoLaiRequest.getListaAnexoDto().size()];
	    	for (int i = 0; i < anexosEmail.length; i++) {
	    		ListaAnexoDto anexos = recursoLaiRequest.getListaAnexoDto().get(i); 			
		    	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		    	String nomeArq = timeStamp + "_" + anexos.getNomeArquivo();
	    		buildFileUBEC(anexos.getStringBase64(), nomeArq, atendimento.get().getOrgao());
		    	AnexoLai anexo = new AnexoLai();
		    	anexo.setAtendimento(atendimento.get().getId());
		    	anexo.setDataAnexo(LocalDate.now());
		    	anexo.setNomeArquivo(nomeArq);
		    	anexo.setResponsavel(3);
		    	anexoLaiRepository.save(anexo);
		    	anexosEmail[i] = "C:\\jboss-4.2.1.GA_CLOUD\\server\\default\\deploy\\multiwork.war\\arquivos\\"+atendimento.get().getOrgao().toString()+"\\"+anexo.getNomeArquivo();
		    }
	    }

	    
		String template = null;
		Map<String, Object> variaveis = new HashMap<>();
		variaveis.put("recursoLai", recursoLai);
		template = "mail/emailrespostarecurso";
		mailer.enviarEmail(Arrays.asList(atendimento.get().getEmail()), template, variaveis, "Serviço de Atendimento ao Cidadão do Sistema FIEPE", "Resposta do Recurso",  null);
		return recursoLaiMapper.toDto(recursoLai);
	}
	
	
	public RespostaRecursoDto getRespostaRecursoInstancia(String parametro) {
		RecursoLai recurso = recursoLaiRepository.findByParametro(parametro);
		AtendimentoLai atendimentoLai = atendimentoLaiRepository.consultaAtendimentolai(recurso.getAtendimento());
		RespostaRecursoDto respostaRecursoDto = null;
		if (recurso != null) {
			respostaRecursoDto = new RespostaRecursoDto();
			if (recurso.getIdUsuarioResposta() == null) {
				if (atendimentoLai.getInstituicao().equals(1l)) {
					respostaRecursoDto.setNomeSetorDestino(setorLaiRepository.getById(4l).getDescricao());
					List<ModeloDocumentoLai> lista = new ArrayList<>();
					lista.add(modeloDocumentoLaiRepository.findById(6l).get());
					respostaRecursoDto.setListaModelo(new ListaModeloDocumentoLaiResponse(Optional
							.ofNullable(modeloDocumentoLaiMapper
									.fromResponseList(lista))
							.orElse(Collections.emptyList())));
				}else {
					respostaRecursoDto.setNomeSetorDestino(setorLaiRepository.getById(3l).getDescricao());
					List<ModeloDocumentoLai> lista = new ArrayList<>();
					lista.add(modeloDocumentoLaiRepository.findById(5l).get());
					respostaRecursoDto.setListaModelo(new ListaModeloDocumentoLaiResponse(Optional
							.ofNullable(modeloDocumentoLaiMapper
									.fromResponseList(lista))
							.orElse(Collections.emptyList())));
				}
				respostaRecursoDto.setProtocolo(atendimentoLai.getNumeroProtocolo());
				respostaRecursoDto.setCodigoAtendimento(atendimentoLai.getId());
			}else {
				respostaRecursoDto.setRespondido(true);
			}
		}
		return respostaRecursoDto;
	}
	
	
	public void salvarEncaminhamentoRespostaRecurso(RespostaRecursoDto respostaRecursoDto) throws IOException{
		RecursoLai recursoLai = recursoLaiRepository.findByParametro(respostaRecursoDto.getParametro());
		AtendimentoLai atendimentoLai = atendimentoLaiRepository.consultaAtendimentolai(recursoLai.getAtendimento());
		if (recursoLai != null) {
			AtendimentoLai atendimento = atendimentoLaiRepository.getById(recursoLai.getAtendimento());
			recursoLai.setDataResposta(LocalDateTime.now());
			recursoLai.setRespostaRecurso(respostaRecursoDto.getResposta());
			recursoLai.setResultado(respostaRecursoDto.getResultado());
			recursoLai.setIdUsuarioResposta(1l);
			recursoLaiRepository.save(recursoLai);
		    	
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    	
    		String[] anexosEmail = null;
		    
		    if (respostaRecursoDto.getListaAnexoDto() != null) {
		    	anexosEmail = new String[respostaRecursoDto.getListaAnexoDto().size()];
		    	for (int i = 0; i < anexosEmail.length; i++) {
		    		ListaAnexoDto anexos = respostaRecursoDto.getListaAnexoDto().get(i); 					
			    	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			    	String nomeArq = timeStamp + "_" + anexos.getNomeArquivo();
			        buildFileUBEC(anexos.getStringBase64(), nomeArq, atendimento.getOrgao());
			    	AnexoLai anexo = new AnexoLai();
			    	anexo.setAtendimento(atendimento.getId());
			    	anexo.setDataAnexo(LocalDate.now());
			    	anexo.setNomeArquivo(nomeArq);
			    	anexo.setResponsavel(4);
			    	anexoLaiRepository.save(anexo);
			    	anexosEmail[i] = "C:\\jboss-4.2.1.GA_CLOUD\\server\\default\\deploy\\multiwork.war\\arquivos\\"+atendimento.getOrgao().toString()+"\\lai\\" + anexo.getNomeArquivo();
			    }
		    }
		    	
		    
	        String conteudo = "Prezado(a) Senhor(a), sua resposta do recurso referente ao atendimento nº " +atendimento.getAnoAtendimento()+""+atendimento.getNumeroAtendimento() +" enviado a seus cuidados em " + recursoLai.getDataRecurso().format(formatter)+" foi recebida com sucesso pelo sistema em " +  recursoLai.getDataResposta().format(formatter) +  ".";

	    	Map<String, Object> variaveis = new HashMap<>();
	    	variaveis.put("conteudo", conteudo);
			variaveis.put("despacho", recursoLai.getRespostaRecurso());
			String template = "mail/emailrespostarecursoencaminhado";
			mailer.enviarEmail(Arrays.asList(atendimentoLai.getEmail()), template, variaveis, "Confirmação de Resposta do recurso do atendimento N° " + atendimento.getNumeroProtocolo(), "Resposta do Recurso", anexosEmail);
	        

	    	variaveis = new HashMap<>();
			variaveis.put("recursoLai", recursoLai);
			template = "mail/emailrespostarecurso";
			mailer.enviarEmail(Arrays.asList(atendimentoLai.getEmail()), template, variaveis, "Confirmação de Resposta do recurso do atendimento N° " + atendimento.getNumeroProtocolo(), "Resposta do Recurso", anexosEmail);
		}
	}
	
	
	private void buildFileUBEC(String base64, String urlAnexo, Long codigoOrgao)throws IOException {
		try {
			byte[] anexo2 = Base64.decodeBase64(base64.split(",")[1]);
			OutputStream out = new FileOutputStream(
					"C:\\jboss-4.2.1.GA_CLOUD\\server\\default\\deploy\\multiwork.war\\arquivos\\"+codigoOrgao.toString()+"\\lai\\"+urlAnexo);
			out.write(anexo2);
			out.close();
		}catch (Exception e) {
			e.getMessage();
		}
			
	}	
	
	

}
