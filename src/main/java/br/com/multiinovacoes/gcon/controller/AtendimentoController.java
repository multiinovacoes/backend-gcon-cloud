package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.AtendimentoController.ATENDIMENTO;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.multiinovacoes.gcon.graphics.StackedColumn;
import br.com.multiinovacoes.gcon.model.Atendimento;
import br.com.multiinovacoes.gcon.model.dto.ResumoAtendimentoDto;
import br.com.multiinovacoes.gcon.model.filter.AtendimentoFilter;
import br.com.multiinovacoes.gcon.model.mapper.AtendimentoMapper;
import br.com.multiinovacoes.gcon.model.request.AtendimentoConclusaoRequest;
import br.com.multiinovacoes.gcon.model.request.AtendimentoRequest;
import br.com.multiinovacoes.gcon.model.request.PesquisaSatisfacaoRequest;
import br.com.multiinovacoes.gcon.model.response.AtendimentoResponse;
import br.com.multiinovacoes.gcon.model.response.ListaAtendimentoResponse;
import br.com.multiinovacoes.gcon.model.response.ListaHistoricoUsuarioResponse;
import br.com.multiinovacoes.gcon.model.response.ResumoAtendimentoResponse;
import br.com.multiinovacoes.gcon.report.AtendimentoNatureza;
import br.com.multiinovacoes.gcon.report.DadosGrafico;
import br.com.multiinovacoes.gcon.report.PainelOuvidoria;
import br.com.multiinovacoes.gcon.repository.AtendimentoRepository;
import br.com.multiinovacoes.gcon.service.AtendimentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Atendimento", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = ATENDIMENTO	)
public class AtendimentoController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(AtendimentoController.class);
	
	public static final String ATENDIMENTO = "/atendimentos";
	public static final String ATENDIMENTO_SITE = "/atendimentos-site";
	public static final String CONCLUIR_ATENDIMENTO = "/concluir-atendimento";
	public static final String SITE_UBEC = "/site-ubec";
	public static final String SITE_CLOUD = "/site-cloud";
	public static final String LISTAR = "/listar";
	public static final String LISTAR_NOVOS = "/listarNovos";
	public static final String QTD_NOVOS_ATENDIMENTOS = "/novos-atendimentos";
	public static final String QTD_CANAL_DENUNCIAS = "/canal-denuncias";
	public static final String LISTAR_CANAL_DENUNCIAS = "/lista-canal-denuncias";
	public static final String QTD_ATENDIMENTOS_NAO_ENCAMINHADO = "/atendimentos-nao-enc";
	public static final String QTD_ATENDIMENTOS_VENCIDOS = "/atendimentos-vencidos";
	public static final String QTD_ATENDIMENTOS_VENCER = "/atendimentos-vencer";
	public static final String QTD_ATENDIMENTO_RECEBIDOS = "/atendimentos-recebidos";
	public static final String QTD_ATENDIMENTOS_MES = "/totalAtendimentosMes";
	public static final String QTD_ATENDIMENTOS = "/totalAtendimentos";
	public static final String QTD_ATENDIMENTOS_SETOR_NATUREZA = "/totalAtendimentosSetorNatureza";
	public static final String QTD_ATENDIMENTOS_PAINEL_OUV = "/totalAtendimentosPainelOuv";
	public static final String QTD_ATENDIMENTOS_MES_NAO_CONCLUIDOS = "/totalAtendimentosMesNaoConcluidos";
	public static final String QTD_ATENDIMENTOS_MES_CONCLUIDOS = "/totalAtendimentosMesConcluidos";
	public static final String QTD_ATENDIMENTOS_NATUREZA = "/totalAtendimentosNatureza";
	public static final String QTD_ATENDIMENTOS_NATUREZA_DASHBOARD = "/totalAtendimentosNaturezaDashboard";
	public static final String QTD_ATENDIMENTOS_SECRETARIA = "/totalAtendimentosSecretaria";
	public static final String AVALIACAO_PESQUISA_SATISFACAO = "/avaliacao-pesquisa-satisfacao";
	public static final String QTD_ATENDIMENTOS_ORIGEM_MANIFESTACAO = "/totalAtendimentosOrigemManifestacao";
	public static final String QTD_ATENDIMENTOS_ASSUNTO = "/totalAtendimentosAssunto";
	public static final String QTD_ATENDIMENTOS_RESOLUTIVIDADE = "/totalAtendimentosResolutividade";
	public static final String PARAMETRO = "/{codigo}";
	public static final String CANCELAR_MANIFESTACAO = "/cancelar/{codigo}";
	public static final String CONSULTA = "/consulta";
	public static final String CONSULTA_ATENDIMENTO = "/consultaAtendimento";
	public static final String NOVO_ATENDIMENTO = "/novo-atendimento";
	public static final String LISTAR_HISTORICO = "/historico-usuario";
	public static final String PESQUISAR_PROTOCOLO = "/pesquisar-protocolo";
	public static final String CONECTA_RECIFE = "/conecta-recife";
	public static final String REABRIR_ATENDIMENTO = "/reabrir-atendimento";
	public static final String LISTAR_VENCIDOS = "/listar-vencidos";
	public static final String LISTAR_VENCER = "/listar-vencer";
	public static final String LISTAR_ENCAMINHAMENTOS_VENCIDOS = "/atendimentos-enc-vencidos";
	public static final String LISTAR_ENCAMINHAMENTOS_VENCER = "/atendimentos-enc-vencer";
	public static final String PESQUISA_SATISFACAO = "/pesquisaSatisfacao";
	public static final String PESQUISA_SATISFACAO_EMAIL = "/pesquisa-satisfacao/{parametro}";
	
	
	@Autowired 
	AtendimentoRepository atendimentoRepository;
	
	@Autowired
	AtendimentoService atendimentoService;
	
	@Autowired
	AtendimentoMapper atendimentoMapper;
	
	@ApiOperation(value = "Enviar uma atendimento", nickname = PESQUISA_SATISFACAO)
	@PostMapping(path = PESQUISA_SATISFACAO, produces = APPLICATION_JSON_VALUE)
	public Boolean salvarPesquisaSatisfacao(@Valid @RequestBody PesquisaSatisfacaoRequest request) {
		return atendimentoService.getSalvarPesquisaSatisfacao(request);
	}
	
	@ApiOperation(value = "Lista de novos atendimentos", nickname = NOVO_ATENDIMENTO)
	@GetMapping(path = NOVO_ATENDIMENTO, produces = APPLICATION_JSON_VALUE)
	public AtendimentoResponse novoAtendimento() {
		LOGGER.info("Novo atendimento");
		return new AtendimentoResponse(atendimentoService.novoAtendimento());
	}

	@ApiOperation(value = "Quantidade de novos atendimentos", nickname = LISTAR_NOVOS)
	@GetMapping(path = LISTAR_NOVOS, produces = APPLICATION_JSON_VALUE)
	public Integer getQtdNovosAtendimentos(Long orgao) {
		return atendimentoRepository.getNovosAtendimentos(orgao);
	}

	@ApiOperation(value = "Quantidade de denuncias", nickname = QTD_CANAL_DENUNCIAS)
	@GetMapping(path = QTD_CANAL_DENUNCIAS, produces = APPLICATION_JSON_VALUE)
	public Integer getQtdCanalDenuncias(Long orgao) {
		return atendimentoRepository.getCanalDenuncias(orgao, Atendimento.CANAL_DENUNCIA);
	}

	@ApiOperation(value = "Quantidade de atendimentos vencidos", nickname = LISTAR_VENCIDOS)
	@GetMapping(path = LISTAR_VENCIDOS, produces = APPLICATION_JSON_VALUE)
	public Integer getQtdAtendimentosVencidos(Long orgao) {
		return atendimentoRepository.getAtendimentosVencidos(orgao);
	}

	@ApiOperation(value = "Quantidade de atendimentos a vencer", nickname = LISTAR_VENCER)
	@GetMapping(path = LISTAR_VENCER, produces = APPLICATION_JSON_VALUE)
	public Integer getQtdAtendimentosVencer(Long orgao) {
		return atendimentoService.qtdAtendimentoVencer(orgao);
	}

	@ApiOperation(value = "Lista de novos atendimentos", nickname = QTD_NOVOS_ATENDIMENTOS)
	@GetMapping(path = QTD_NOVOS_ATENDIMENTOS, produces = APPLICATION_JSON_VALUE)
	public ListaAtendimentoResponse getListaNovosAtendimentos(Long orgao, Pageable page) {
		return new ListaAtendimentoResponse(atendimentoService.getListaNovosAtendimentos(orgao, page));
	}

	@ApiOperation(value = "Lista de denúncias", nickname = LISTAR_CANAL_DENUNCIAS)
	@GetMapping(path = LISTAR_CANAL_DENUNCIAS, produces = APPLICATION_JSON_VALUE)
	public ListaAtendimentoResponse getListaCanalDenuncias(Long orgao, Pageable page) {
		return new ListaAtendimentoResponse(atendimentoService.getListaCanalDenuncias(orgao, page));
	}

	@ApiOperation(value = "Lista de novos atendimentos", nickname = QTD_ATENDIMENTOS_NAO_ENCAMINHADO)
	@GetMapping(path = QTD_ATENDIMENTOS_NAO_ENCAMINHADO, produces = APPLICATION_JSON_VALUE)
	public List<Atendimento> getListaAtendimentosNaoEnviados(Long orgao) {
		return atendimentoService.getListaAtendimentosNaoEncaminhados(orgao);
	}


	@ApiOperation(value = "Lista de atendimentos vencidos", nickname = QTD_ATENDIMENTOS_VENCIDOS)
	@GetMapping(path = QTD_ATENDIMENTOS_VENCIDOS, produces = APPLICATION_JSON_VALUE)
	public ListaAtendimentoResponse getListaAtendimentosVencidos(Long orgao, Pageable page) {
		return new ListaAtendimentoResponse(atendimentoService.getListaAtendimentosVencidos(orgao, page));
	}

	@ApiOperation(value = "Lista de atendimentos vencer", nickname = QTD_ATENDIMENTOS_VENCER)
	@GetMapping(path = QTD_ATENDIMENTOS_VENCER, produces = APPLICATION_JSON_VALUE)
	public ListaAtendimentoResponse getListaAtendimentosVencer(Long orgao, Pageable page) {
		return new ListaAtendimentoResponse(atendimentoService.getListaAtendimentosVencer(orgao, page));
	}

	@ApiOperation(value = "Lista de novos atendimentos", nickname = QTD_ATENDIMENTO_RECEBIDOS)
	@GetMapping(path = QTD_ATENDIMENTO_RECEBIDOS, produces = APPLICATION_JSON_VALUE)
	public ListaAtendimentoResponse getListaAtendimentosRecebidos(Long orgao, Pageable page) {
		return new ListaAtendimentoResponse(atendimentoService.getListaAtendimentosRecebidos(orgao, page));
	}

	@ApiOperation(value = "Quantidade de atendimentos do mês atual", nickname = QTD_ATENDIMENTOS_MES)
	@GetMapping(path = QTD_ATENDIMENTOS_MES, produces = APPLICATION_JSON_VALUE)
	public Integer getQtdTotalAtendimentosMes(Long orgao) {
		return atendimentoRepository.getQtdAtendimentosMes(orgao, 2, LocalDate.now().getMonthValue(), LocalDate.now().getYear());
	}

	@ApiOperation(value = "Quantidade de atendimentos anual", nickname = QTD_ATENDIMENTOS)
	@GetMapping(path = QTD_ATENDIMENTOS, produces = APPLICATION_JSON_VALUE)
	public List<DadosGrafico> getQtdTotalAtendimentosAnual(Long orgao, Integer ano) {
		return atendimentoRepository.getQtdAtendimentos(orgao, ano);
	}

	@ApiOperation(value = "Quantidade de atendimentos concluidos anual", nickname = QTD_ATENDIMENTOS_PAINEL_OUV)
	@GetMapping(path = QTD_ATENDIMENTOS_PAINEL_OUV, produces = APPLICATION_JSON_VALUE)
	public PainelOuvidoria getQtdTotalAtendimentosMesConcluidos(Long orgao, Integer ano) {
		return atendimentoService.getPainel(orgao, ano);
	}
	
	@ApiOperation(value = "Quantidade de atendimentos concluidos do mês", nickname = QTD_ATENDIMENTOS_MES_CONCLUIDOS)
	@GetMapping(path = QTD_ATENDIMENTOS_MES_CONCLUIDOS, produces = APPLICATION_JSON_VALUE)
	public Integer getQtdTotalAtendimentosMesConcluidos(Long orgao) {
		return atendimentoRepository.getQtdAtendimentosMesConcluidos(orgao, LocalDate.now().getMonthValue(), LocalDate.now().getYear());
	}

	@ApiOperation(value = "Quantidade de atendimentos do mês por natureza", nickname = QTD_ATENDIMENTOS_NATUREZA_DASHBOARD)
	@GetMapping(path = QTD_ATENDIMENTOS_NATUREZA_DASHBOARD, produces = APPLICATION_JSON_VALUE)
	public List<AtendimentoNatureza> getQtdTotalAtendimentosNaturezaMes(Long orgao) {
		return atendimentoRepository.getQtdAtendimentosNaturezaDashboard(orgao, LocalDate.now().getMonthValue(), LocalDate.now().getYear());
	}

	@ApiOperation(value = "Avaliação da pesquisa de satisfação", nickname = AVALIACAO_PESQUISA_SATISFACAO)
	@GetMapping(path = AVALIACAO_PESQUISA_SATISFACAO, produces = APPLICATION_JSON_VALUE)
	public List<AtendimentoNatureza> getAvaliacaoPesquisaSatisfacao(Long orgao, Integer ano) {
		return atendimentoRepository.getQtdPainelPesquisaSatisfacao(orgao, ano);
	}

	@ApiOperation(value = "Quantidade de atendimentos por origem de manifestação", nickname = QTD_ATENDIMENTOS_ORIGEM_MANIFESTACAO)
	@GetMapping(path = QTD_ATENDIMENTOS_ORIGEM_MANIFESTACAO, produces = APPLICATION_JSON_VALUE)
	public List<AtendimentoNatureza> getQtdAtendimentosOrigemManifestacao(Long orgao) {
		return atendimentoRepository.getQtdPainelOrigemManifestacao(orgao, LocalDate.now().getYear());
	}

	@ApiOperation(value = "Quantidade de atendimentos do mês por natureza", nickname = QTD_ATENDIMENTOS_NATUREZA)
	@GetMapping(path = QTD_ATENDIMENTOS_NATUREZA, produces = APPLICATION_JSON_VALUE)
	public List<AtendimentoNatureza> getQtdTotalAtendimentosNatureza(Long orgao, Integer ano) {
		return atendimentoService.getPainelNatureza(orgao, ano);
	}

	@ApiOperation(value = "Quantidade de atendimentos do mês por secretaria", nickname = QTD_ATENDIMENTOS_SECRETARIA)
	@GetMapping(path = QTD_ATENDIMENTOS_SECRETARIA, produces = APPLICATION_JSON_VALUE)
	public List<AtendimentoNatureza> getQtdTotalAtendimentosSecretaria(Long orgao, Integer mes, Integer ano) {
		if (mes == 0) {
			return atendimentoRepository.getQtdAtendimentosSecretaria(orgao, 2, 0, ano);
		}
		return atendimentoRepository.getQtdAtendimentosSecretaria(orgao, 2, mes+1, ano);
	}

	@ApiOperation(value = "Quantidade de atendimentos do mês por setor e natureza", nickname = QTD_ATENDIMENTOS_SETOR_NATUREZA)
	@GetMapping(path = QTD_ATENDIMENTOS_SETOR_NATUREZA, produces = APPLICATION_JSON_VALUE)
	public StackedColumn getQtdTotalAtendimentosSetorNatureza(Long orgao) {
		return atendimentoRepository.getQtdAtendimentosSecretariaNatureza(orgao, 2, 0, LocalDate.now().getYear());
	}

	@ApiOperation(value = "Quantidade de atendimentos por assunto", nickname = QTD_ATENDIMENTOS_ASSUNTO)
	@GetMapping(path = QTD_ATENDIMENTOS_ASSUNTO, produces = APPLICATION_JSON_VALUE)
	public List<AtendimentoNatureza> getQtdTotalAtendimentosAssunto(Long orgao, Integer ano) {
		return atendimentoRepository.getQtdAtendimentosAssunto(orgao, 2, ano);
	}

	@ApiOperation(value = "Quantidade de atendimentos por resolutividade", nickname = QTD_ATENDIMENTOS_RESOLUTIVIDADE)
	@GetMapping(path = QTD_ATENDIMENTOS_RESOLUTIVIDADE, produces = APPLICATION_JSON_VALUE)
	public List<DadosGrafico> getQtdTotalAtendimentosResolutividade(Long orgao, Integer ano) {
		return atendimentoRepository.getQtdAtendimentosResolutividade(orgao, ano);
	}

	@ApiOperation(value = "Obter uma atendimento para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ATENDIMENTO') and #oauth2.hasScope('write')")
	public AtendimentoResponse getEditarAtendimento(@PathVariable Long codigo) {
		return new AtendimentoResponse(atendimentoService.getAtendimento(codigo));
	}

	@ApiOperation(value = "Enviar uma atendimento", nickname = ATENDIMENTO)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public AtendimentoResponse salvarAtendimento(@Valid @RequestBody AtendimentoRequest request) {
		return new AtendimentoResponse(atendimentoService.salvarAtendimento(request));
	}

	@ApiOperation(value = "Enviar uma atendimento", nickname = SITE_UBEC)
	@PostMapping(path = SITE_UBEC, produces = APPLICATION_JSON_VALUE)
	public AtendimentoResponse salvarAtendimentoSiteUbec(@Valid @RequestBody AtendimentoRequest request) {
		return new AtendimentoResponse(atendimentoService.salvarAtendimentoSite(request, "2"));
	}

	@ApiOperation(value = "Enviar uma atendimento", nickname = SITE_CLOUD)
	@PostMapping(path = SITE_CLOUD, produces = APPLICATION_JSON_VALUE)
	public AtendimentoResponse salvarAtendimentoSiteCloud(@Valid @RequestBody AtendimentoRequest request) {
		return new AtendimentoResponse(atendimentoService.salvarAtendimentoSite(request, "1"));
	}

	@ApiOperation(value = "Concluir um atendimento", nickname = CONCLUIR_ATENDIMENTO)
	@PutMapping(path = CONCLUIR_ATENDIMENTO, produces = APPLICATION_JSON_VALUE)
	public void concluirAtendimento(@Valid @RequestBody AtendimentoConclusaoRequest request, Principal principal) {
		atendimentoService.concluirAtendimento(request, principal);
	}

	@ApiOperation(value = "Reabrir um atendimento", nickname = REABRIR_ATENDIMENTO)
	@PutMapping(path = REABRIR_ATENDIMENTO, produces = APPLICATION_JSON_VALUE)
	public AtendimentoResponse reabrirAtendimento(@Valid @RequestBody AtendimentoRequest atendimento, Principal principal) {
		return new AtendimentoResponse(atendimentoService.reabrirAtendimento(atendimento.getId(), principal));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma atendimento", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirAtendimento(@PathVariable Long codigo) {
		atendimentoRepository.deleteById(codigo);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Cancelar uma manifestação", nickname = CANCELAR_MANIFESTACAO)
	@DeleteMapping(path = CANCELAR_MANIFESTACAO, produces = APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('ROLE_CANCELAR_ATENDIMENTO') and #oauth2.hasScope('write')")
	public void cancelarAtendimento(@PathVariable Long codigo) {
		atendimentoService.cancelarAtendimento(codigo);
	}

	@ApiOperation(value = "Atualizar uma atendimento", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public AtendimentoResponse atualizarAtendimento(@PathVariable Long codigo, 
			@Valid @RequestBody AtendimentoRequest request, Principal principal) {
		return new AtendimentoResponse(atendimentoService.salvarAtendimento(request));
	}
	

	@ApiOperation(value = "Obter uma atendimento pelo filtro", nickname = ATENDIMENTO)
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ListaAtendimentoResponse getPesquisaAtendimento(AtendimentoFilter filtro, Pageable page, Long orgao) {
		return new ListaAtendimentoResponse(atendimentoService.getPesquisaAtendimentoDescricao(orgao, filtro, page));
	}
	
	@ApiOperation(value = "Obter o atendimento", nickname = CONSULTA_ATENDIMENTO)
	@GetMapping(path = CONSULTA_ATENDIMENTO, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ResumoAtendimentoResponse> getConsultaAtendimento(Long orgao, String cpf, String numero, String senha) {
		 List<ResumoAtendimentoDto> resumo = atendimentoService.getResumo(orgao, cpf, numero, senha);
		 if (!resumo.isEmpty()) {
			 ResumoAtendimentoResponse resumoAtendimento = new ResumoAtendimentoResponse(resumo.get(0));
			 return ResponseEntity.ok(resumoAtendimento); 
		 }else {
			 return ResponseEntity.notFound().build();
		 }
	}
	
	@ApiOperation(value = "Responder a pesquisa de satisfação enviada por email", nickname = PESQUISA_SATISFACAO_EMAIL)
	@GetMapping(path = PESQUISA_SATISFACAO_EMAIL, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ResumoAtendimentoResponse> getPesquisaSatisfacaoEmail(@PathVariable("parametro") String parametro) {
		 List<ResumoAtendimentoDto> resumo = atendimentoService.getResumo(parametro);
		 if (!resumo.isEmpty()) {
			 ResumoAtendimentoResponse resumoAtendimento = new ResumoAtendimentoResponse(resumo.get(0));
			 return ResponseEntity.ok(resumoAtendimento); 
		 }else {
			 return ResponseEntity.notFound().build();
		 }
	}

	
	@ApiOperation(value = "Obter lista de historico do usuario", nickname = LISTAR)
	@GetMapping(path = LISTAR_HISTORICO, produces = APPLICATION_JSON_VALUE)
	public ListaHistoricoUsuarioResponse getListaHistoricoUsuario(Long codigoAtendimento) {
		return new ListaHistoricoUsuarioResponse(atendimentoService.getHistoricoUsuario(codigoAtendimento));
	}	
	
	
	@ApiOperation(value = "Obter uma atendimento para edição", nickname = PARAMETRO)
	@GetMapping(path = PESQUISAR_PROTOCOLO, produces = APPLICATION_JSON_VALUE)
	public AtendimentoResponse getPesquisarProtocolo(String numeroProtocolo, Long orgao) {
		return new AtendimentoResponse(atendimentoService.getPesquisaProtocolo(numeroProtocolo, orgao));
	}
	
	
	@ApiOperation(value = "Lista de atendimento encaminhados vencidos", nickname = LISTAR_ENCAMINHAMENTOS_VENCIDOS)
	@GetMapping(path = LISTAR_ENCAMINHAMENTOS_VENCIDOS, produces = APPLICATION_JSON_VALUE)
	public ListaAtendimentoResponse getListaAtendimentosEncaminhadosVencidos(Long orgao, Pageable page) {
		return new ListaAtendimentoResponse(atendimentoService.getListaAtendimentosEncaminhadosVencidos (orgao, page));
	}
	
	@ApiOperation(value = "Lista de atendimento encaminhados vencer", nickname = LISTAR_ENCAMINHAMENTOS_VENCER)
	@GetMapping(path = LISTAR_ENCAMINHAMENTOS_VENCER, produces = APPLICATION_JSON_VALUE)
	public ListaAtendimentoResponse getListaAtendimentosEncaminhadosVencer(Long orgao, Pageable page) {
		return new ListaAtendimentoResponse(atendimentoService.getListaAtendimentosEncaminhadosVencer(orgao, page));
	}

	

}
