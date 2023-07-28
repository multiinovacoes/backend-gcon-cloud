package br.com.multiinovacoes.gcon.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.enums.StatusAtendimentoEnum;
import br.com.multiinovacoes.gcon.enums.StatusRecursoEnum;
import br.com.multiinovacoes.gcon.enums.TipoModeloDocumentoEnum;
import br.com.multiinovacoes.gcon.enums.TipoRecursoEnum;
import br.com.multiinovacoes.gcon.mail.Mailer;
import br.com.multiinovacoes.gcon.model.AnexoLai;
import br.com.multiinovacoes.gcon.model.AtendimentoLai;
import br.com.multiinovacoes.gcon.model.Feriado;
import br.com.multiinovacoes.gcon.model.dto.AtendimentoLaiDto;
import br.com.multiinovacoes.gcon.model.filter.AtendimentoFilter;
import br.com.multiinovacoes.gcon.model.mapper.AtendimentoLaiMapper;
import br.com.multiinovacoes.gcon.model.request.AtendimentoLaiRequest;
import br.com.multiinovacoes.gcon.repository.AnexoLaiRepository;
import br.com.multiinovacoes.gcon.repository.AtendimentoLaiRepository;
import br.com.multiinovacoes.gcon.repository.ConfiguracaoLaiRepository;
import br.com.multiinovacoes.gcon.repository.FeriadoRepository;


@Service
public class AtendimentoLaiService {

	@Autowired
	private AtendimentoLaiRepository atendimentoLaiRepository;
	
	@Autowired
	private AtendimentoLaiMapper atendimentoLaiMapper;

	@Autowired
	private FeriadoRepository feriadoRepository;
	
	@Autowired
	private Mailer mailer;
	
	@Autowired
	private EncaminhamentoLaiService encaminhamentoLaiService;
	
	@Autowired
	private ConfiguracaoLaiRepository configuracaoLaiRepository;
	
	@Autowired
	private AnexoLaiRepository anexoLaiRepository;
	
	@Autowired
	private ModeloDocumentoLaiService modeloDocumentoLaiService;
	
	@Autowired
	private RecursoLaiService recursoLaiService;

	
	
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
    public Page<AtendimentoLai> getListaNovosAtendimentosLai(Long orgao, Pageable page)
    {
        return atendimentoLaiRepository.consultaNovosPedidosLai(orgao, page, atendimentoLaiRepository.getNovosAtendimentosLai(orgao));
    }

    public Page<AtendimentoLai> getListaAtendimentosLaiRecursoAberto(Long orgao, Pageable page)
    {
        return atendimentoLaiRepository.consultaAtendimentosLaiRecursoAberto(orgao, page, atendimentoLaiRepository.getAtendimentosLaiRecursoAberto(orgao, StatusRecursoEnum.RECURSO_ANDAMENTO.getCodigo(), 
        		TipoRecursoEnum.RECURSO_1_INSTANCIA.getCodigo()));
    }

    public Page<AtendimentoLai> getListaAtendimentosLaiRecursoVencido(Long orgao, Pageable page)
    {
        return atendimentoLaiRepository.consultaAtendimentosLaiRecursoVencido(orgao, page, atendimentoLaiRepository.getAtendimentosRecursosVencidosLai(orgao, StatusRecursoEnum.RECURSO_ANDAMENTO.getCodigo(),
        		TipoRecursoEnum.RECURSO_1_INSTANCIA.getCodigo()));
    }

    public Page<AtendimentoLai> getListaAtendimentosVencidosLai(Long orgao, Pageable page)
    {
        return atendimentoLaiRepository.consultaPedidosVencidosLai(orgao, page, atendimentoLaiRepository.getAtendimentosVencidosLai(orgao));
    }

    public Page<AtendimentoLai> getListaAtendimentosEncaminhadosLai(Long orgao, Pageable pageable)
    {
        return encaminhamentoLaiService.getListaAtendimentosEncaminhadosLai(orgao, pageable);
    }

    public Page<AtendimentoLai> getListaAtendimentosEncaminhadosRecebidosLai(Long orgao, Pageable pageable)
    {
        return encaminhamentoLaiService.getListaAtendimentosEncaminhadosRecebidosLai(orgao, pageable);
    }

    public Page<AtendimentoLai> getListaAtendimentosEncaminhadosLaiVencimento(Long orgao, Pageable pageable)
    {
        return encaminhamentoLaiService.getListaAtendimentosEncaminhadosLaiVencimento(orgao, pageable);
    }

    public Page<AtendimentoLai> getListaAtendimentosEncaminhadosLaiVencidos(Long orgao, Pageable pageable)
    {
        return encaminhamentoLaiService.getListaAtendimentosEncaminhadosLaiVencidos(orgao, pageable);
    }

    public Page<AtendimentoLai> getListaRecursosLaiVencimento(Long orgao, Pageable pageable)
    {
        return recursoLaiService.getListaRecursosAbertosProxVencimento(orgao, pageable);
    }

	@Transactional
	public AtendimentoLaiDto getAtendimento(Long codigoAtendimento) {
		AtendimentoLaiDto atendimentoDto = atendimentoLaiMapper.toDto(atendimentoLaiRepository.consultaAtendimentolai(codigoAtendimento));
        return atendimentoDto;
	}
	
	@Transactional
	public AtendimentoLaiDto salvarAtendimentoLai(AtendimentoLaiRequest request) {
		AtendimentoLai atendimento = atendimentoLaiMapper.fromAtendimentoLai(request);
		atendimentoLaiRepository.save(atendimento);
		AtendimentoLaiDto atendimentoDto = atendimentoLaiMapper.toDto(atendimento);
		return atendimentoDto;
	}

	@Transactional
	public AtendimentoLaiDto concluirAtendimentoLai(AtendimentoLaiRequest request) {
		AtendimentoLai atendimento = atendimentoLaiMapper.fromAtendimentoLai(request);
		String[] anexos = null;
		if (request.getSelectedAnexos() != null) {
			anexos = new String[request.getSelectedAnexos().size()];
			for (int i = 0; i < request.getSelectedAnexos().size(); i++) {
				Optional<AnexoLai> anexo = anexoLaiRepository.findById(request.getSelectedAnexos().get(i));
				if (anexo.isPresent()) {
					anexos[i] = "C:\\jboss-4.2.1.GA_CLOUD\\server\\default\\deploy\\multiwork.war\\arquivos\\7\\lai\\"+anexo.get().getNomeArquivo();
				}
			}
		}			
			
		atendimento.setStatusAtendimento(StatusAtendimentoEnum.STATUS_RESOLVIDO.getCodigo());
		atendimento.setDataConclusao(LocalDateTime.now());
		atendimentoLaiRepository.save(atendimento);
		AtendimentoLaiDto atendimentoDto = atendimentoLaiMapper.toDto(atendimento);
		
		

		String template = null;
	    Map<String, Object> variaveis = new HashMap<>();
		variaveis.put("atendimento", atendimento);
		template = "mail/emailconclusaoatendimento";
		mailer.enviarEmail(Arrays.asList(atendimento.getEmail()), template, variaveis, "Serviço de Atendimento ao Cidadão do Sistema FIEPE", "Resposta do Pedido", anexos);
		
		return atendimentoDto;
	}

	@Transactional
	public AtendimentoLaiDto prorrogarPrazo(Long codigoAtendimento) {
		AtendimentoLai atendimento = atendimentoLaiRepository.getById(codigoAtendimento);
		atendimento.setDataPrazo(adicionarDiasUteis(configuracaoLaiRepository.findByOrgao(atendimento.getOrgao()).getQtdMaxProrrogaPrazoAtendimento(), atendimento.getOrgao(), atendimento.getDataPrazo()));
		atendimento.setPrazoProrrogado(1);
		atendimentoLaiRepository.save(atendimento);
		AtendimentoLaiDto atendimentoDto = atendimentoLaiMapper.toDto(atendimento);
		
		String modeloResposta = modeloDocumentoLaiService.getModeloDocumento(TipoModeloDocumentoEnum.MODELO_DOCUMENTO_PRORROGACAO_PRAZO.getCodigo(), atendimento.getId());
		
		String template = null;
	    Map<String, Object> variaveis = new HashMap<>();
		variaveis.put("modeloProrrogacaoPrazo", modeloResposta);
		template = "mail/emailprorrogacaoprazo";
		mailer.enviarEmail(Arrays.asList(atendimento.getEmail()), template, variaveis, "Serviço de Atendimento ao Cidadão do Sistema FIEPE", "Resposta do Pedido", null);
		
		
		return atendimentoDto;
	}
	
	@Transactional
	public Page<AtendimentoLai> getPesquisaAtendimentoDescricao(Long orgao, AtendimentoFilter filtro, Pageable page){
		return atendimentoLaiRepository.filtrar(orgao, filtro, page);
	}
	
	@Transactional
	public void cancelarPedidoInformacao(Long idAtendimento) {
		AtendimentoLai atendimentoLai = atendimentoLaiRepository.getById(idAtendimento);
		atendimentoLai.setStatus(2);
		atendimentoLai.setStatusAtendimento(2);
		atendimentoLaiRepository.save(atendimentoLai);
	}

	



}
