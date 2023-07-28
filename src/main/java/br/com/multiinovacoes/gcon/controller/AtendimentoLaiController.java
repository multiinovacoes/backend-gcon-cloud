package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.AtendimentoLaiController.ATENDIMENTO_LAI;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

import br.com.multiinovacoes.gcon.enums.StatusRecursoEnum;
import br.com.multiinovacoes.gcon.enums.TipoRecursoEnum;
import br.com.multiinovacoes.gcon.model.filter.AtendimentoFilter;
import br.com.multiinovacoes.gcon.model.mapper.AtendimentoLaiMapper;
import br.com.multiinovacoes.gcon.model.request.AtendimentoLaiRequest;
import br.com.multiinovacoes.gcon.model.response.AtendimentoLaiResponse;
import br.com.multiinovacoes.gcon.model.response.ListaAtendimentoLaiResponse;
import br.com.multiinovacoes.gcon.repository.AtendimentoLaiRepository;
import br.com.multiinovacoes.gcon.security.GconSecurity;
import br.com.multiinovacoes.gcon.service.AtendimentoLaiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Atendimento", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = ATENDIMENTO_LAI	)
public class AtendimentoLaiController {
	
	public static final String ATENDIMENTO_LAI = "/atendimentos-lai";
	public static final String CONCLUIR_ATENDIMENTO = "/concluir-atendimento/{codigo}";
	public static final String LISTAR_NOVOS_LAI = "/listar-novos-atendimentos";
	public static final String QTD_NOVOS_PEDIDOS = "/novos-pedidos";
	public static final String PARAMETRO = "/{codigo}";
	public static final String PRORROGAR_PRAZO = "/prorrogar-prazo/{codigo}";
	public static final String LISTAR_ENCAMINHAMENTOS_ENVIADOS_LAI = "/listar-atendimentos-encaminhados";
	public static final String LISTAR_ENCAMINHAMENTOS_RECEBIDOS_LAI = "/listar-atendimentos-encaminhados-rec";
	public static final String LISTAR_ENCAMINHAMENTOS_VENCIMENTO_LAI = "/listar-atendimentos-encaminhados-vencimento";
	public static final String LISTAR_ENCAMINHAMENTOS_VENCIDOS_LAI = "/listar-atendimentos-encaminhados-vencidos";
	public static final String LISTAR_PEDIDOS_VENCIDOS_LAI = "/listar-atendimentos-vencidos";
	public static final String QTD_PEDIDOS_VENCIDOS = "/atendimentos-vencidos";
	public static final String QTD_PEDIDOS_RECURSO_ABERTO = "/pedidos-recurso-aberto";
	public static final String QTD_LISTA_RECURSO_ABERTO = "/lista-recurso-aberto";
	public static final String QTD_PEDIDOS_RECURSO_VENCIDO = "/pedidos-recurso-vencido";
	public static final String QTD_LISTA_RECURSO_VENCIDO = "/lista-recurso-vencido";
	public static final String LISTAR_RECURSO_VENCIMENTO_LAI = "/listar-recurso-vencimento";
	public static final String QTD_LISTA_RECURSO_VENCIMENTO = "/lista-recurso-vencimento";
	public static final String CANCELAR = "/cancelar/{codigo}";
	
	@Autowired 
	AtendimentoLaiRepository atendimentoLaiRepository;
	
	@Autowired
	AtendimentoLaiMapper atendimentoLaiMapper;
	
	@Autowired
	AtendimentoLaiService atendimentoLaiService;
	
	@Autowired
	GconSecurity gconSecurity;

	@ApiOperation(value = "Cadastrar um pedido")
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public AtendimentoLaiResponse  salvarAtendimento(@Valid @RequestBody AtendimentoLaiRequest request) throws Exception{
		return new AtendimentoLaiResponse(atendimentoLaiService.salvarAtendimentoLai(request));
	}

	@ApiOperation(value = "Cadastrar um pedido")
	@PutMapping(path = CONCLUIR_ATENDIMENTO, produces = APPLICATION_JSON_VALUE)
	public AtendimentoLaiResponse  concluirAtendimento(@Valid @PathVariable Long codigo, @RequestBody AtendimentoLaiRequest request) throws Exception{
		return new AtendimentoLaiResponse(atendimentoLaiService.concluirAtendimentoLai(request));
	}
	
	@ApiOperation(value = "Atualizar uma atendimento", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public AtendimentoLaiResponse atualizarAtendimento(@PathVariable Long codigo, 
			@Valid @RequestBody AtendimentoLaiRequest request) {
		return new AtendimentoLaiResponse(atendimentoLaiService.salvarAtendimentoLai(request));
	}

	@ApiOperation(value = "Quantidade de novos atendimentos", nickname = QTD_PEDIDOS_RECURSO_ABERTO)
	@GetMapping(path = QTD_PEDIDOS_RECURSO_ABERTO, produces = APPLICATION_JSON_VALUE)
	public Integer getQtdRecursosAbertos(Long orgao) {
		return atendimentoLaiRepository.getAtendimentosLaiRecursoAberto(orgao, StatusRecursoEnum.RECURSO_ANDAMENTO.getCodigo(), TipoRecursoEnum.RECURSO_1_INSTANCIA.getCodigo());
	}

	@ApiOperation(value = "Quantidade de novos atendimentos", nickname = QTD_PEDIDOS_RECURSO_VENCIDO)
	@GetMapping(path = QTD_PEDIDOS_RECURSO_VENCIDO, produces = APPLICATION_JSON_VALUE)
	public Integer getQtdRecursosVencidos(Long orgao) {
		return atendimentoLaiRepository.getAtendimentosRecursosVencidosLai(orgao, StatusRecursoEnum.RECURSO_ANDAMENTO.getCodigo(), TipoRecursoEnum.RECURSO_1_INSTANCIA.getCodigo());
	}

	@ApiOperation(value = "Quantidade de novos atendimentos", nickname = LISTAR_NOVOS_LAI)
	@GetMapping(path = LISTAR_NOVOS_LAI, produces = APPLICATION_JSON_VALUE)
	public Integer getQtdNovosAtendimentos(Long orgao) {
		return atendimentoLaiRepository.getNovosAtendimentosLai(orgao);
	}

	@ApiOperation(value = "Quantidade de atendimentos vencidos", nickname = LISTAR_PEDIDOS_VENCIDOS_LAI)
	@GetMapping(path = LISTAR_PEDIDOS_VENCIDOS_LAI, produces = APPLICATION_JSON_VALUE)
	public Integer getQtdAtendimentosVencidos(Long orgao) {
		return atendimentoLaiRepository.getAtendimentosVencidosLai(orgao);
	}

	@ApiOperation(value = "Lista de pedidos encaminhados", nickname = LISTAR_ENCAMINHAMENTOS_ENVIADOS_LAI)
	@GetMapping(path = LISTAR_ENCAMINHAMENTOS_ENVIADOS_LAI, produces = APPLICATION_JSON_VALUE)
	public ListaAtendimentoLaiResponse getListaAtendimentosLaiEncaminhadosVencimento(Long orgao, Pageable page) {
		return new ListaAtendimentoLaiResponse(atendimentoLaiService.getListaAtendimentosEncaminhadosLai(gconSecurity.getOrgao(), page));
	}
	
	@ApiOperation(value = "Lista de pedidos recebidos", nickname = LISTAR_ENCAMINHAMENTOS_RECEBIDOS_LAI)
	@GetMapping(path = LISTAR_ENCAMINHAMENTOS_RECEBIDOS_LAI, produces = APPLICATION_JSON_VALUE)
	public ListaAtendimentoLaiResponse getListaAtendimentosLaiEncaminhadosRecebidos(Long orgao, Pageable page) {
		return new ListaAtendimentoLaiResponse(atendimentoLaiService.getListaAtendimentosEncaminhadosRecebidosLai(gconSecurity.getOrgao(), page));
	}


	@ApiOperation(value = "Lista de pedidos encaminhados", nickname = LISTAR_ENCAMINHAMENTOS_VENCIMENTO_LAI)
	@GetMapping(path = LISTAR_ENCAMINHAMENTOS_VENCIMENTO_LAI, produces = APPLICATION_JSON_VALUE)
	public ListaAtendimentoLaiResponse getListaAtendimentosLaiEncaminhados(Long orgao, Pageable page) {
		return new ListaAtendimentoLaiResponse(atendimentoLaiService.getListaAtendimentosEncaminhadosLaiVencimento(gconSecurity.getOrgao(), page));
	}

	@ApiOperation(value = "Lista de pedidos encaminhados", nickname = LISTAR_RECURSO_VENCIMENTO_LAI)
	@GetMapping(path = LISTAR_RECURSO_VENCIMENTO_LAI, produces = APPLICATION_JSON_VALUE)
	public ListaAtendimentoLaiResponse getListaRecursosVencimento(Long orgao, Pageable page) {
		return new ListaAtendimentoLaiResponse(atendimentoLaiService.getListaRecursosLaiVencimento(gconSecurity.getOrgao(), page));
	}

	@ApiOperation(value = "Lista de pedidos encaminhados", nickname = LISTAR_ENCAMINHAMENTOS_VENCIDOS_LAI)
	@GetMapping(path = LISTAR_ENCAMINHAMENTOS_VENCIDOS_LAI, produces = APPLICATION_JSON_VALUE)
	public ListaAtendimentoLaiResponse getListaAtendimentosLaiEncaminhadosVencidos(Long orgao, Pageable page) {
		return new ListaAtendimentoLaiResponse(atendimentoLaiService.getListaAtendimentosEncaminhadosLaiVencidos(gconSecurity.getOrgao(), page));
	}

	@ApiOperation(value = "Lista de novos pedidos", nickname = QTD_NOVOS_PEDIDOS)
	@GetMapping(path = QTD_NOVOS_PEDIDOS, produces = APPLICATION_JSON_VALUE)
	public ListaAtendimentoLaiResponse getListaNovosAtendimentosLai(Long orgao, Pageable page) {
		return new ListaAtendimentoLaiResponse(atendimentoLaiService.getListaNovosAtendimentosLai(gconSecurity.getOrgao(), page));
	}

	@ApiOperation(value = "Lista de pedidos com recurso aberto", nickname = QTD_LISTA_RECURSO_ABERTO)
	@GetMapping(path = QTD_LISTA_RECURSO_ABERTO, produces = APPLICATION_JSON_VALUE)
	public ListaAtendimentoLaiResponse getListaPedidosRecursoAberto(Long orgao, Pageable page) {
		return new ListaAtendimentoLaiResponse(atendimentoLaiService.getListaAtendimentosLaiRecursoAberto(gconSecurity.getOrgao(), page));
	}

	@ApiOperation(value = "Lista de pedidos com recurso aberto", nickname = QTD_LISTA_RECURSO_VENCIDO)
	@GetMapping(path = QTD_LISTA_RECURSO_VENCIDO, produces = APPLICATION_JSON_VALUE)
	public ListaAtendimentoLaiResponse getListaPedidosRecursoVencido(Long orgao, Pageable page) {
		return new ListaAtendimentoLaiResponse(atendimentoLaiService.getListaAtendimentosLaiRecursoVencido(gconSecurity.getOrgao(), page));
	}

	@ApiOperation(value = "Lista de novos pedidos", nickname = QTD_PEDIDOS_VENCIDOS)
	@GetMapping(path = QTD_PEDIDOS_VENCIDOS, produces = APPLICATION_JSON_VALUE)
	public ListaAtendimentoLaiResponse getListaAtendimentosVencidosLai(Long orgao, Pageable page) {
		return new ListaAtendimentoLaiResponse(atendimentoLaiService.getListaAtendimentosVencidosLai(gconSecurity.getOrgao(), page));
	}
	
	@ApiOperation(value = "Obter uma atendimento para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public AtendimentoLaiResponse getEditarAtendimento(@PathVariable Long codigo) {
		return new AtendimentoLaiResponse(atendimentoLaiService.getAtendimento(codigo));
	}

	@ApiOperation(value = "Atualizar uma atendimento", nickname = PRORROGAR_PRAZO)
	@GetMapping(path = PRORROGAR_PRAZO, produces = APPLICATION_JSON_VALUE)
	public AtendimentoLaiResponse prorrogarPrazo(@PathVariable Long codigo) {
		return new AtendimentoLaiResponse(atendimentoLaiService.prorrogarPrazo(codigo));
	}
	
	@ApiOperation(value = "Obter uma atendimento pelo filtro", nickname = ATENDIMENTO_LAI)
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ListaAtendimentoLaiResponse getPesquisaAtendimento(AtendimentoFilter filtro, Pageable page) {
		return new ListaAtendimentoLaiResponse(atendimentoLaiService.getPesquisaAtendimentoDescricao(gconSecurity.getOrgao(), filtro, page));
	}
	

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Cancelar uma pedido de informação", nickname = CANCELAR)
	@DeleteMapping(path = CANCELAR, produces = APPLICATION_JSON_VALUE)
	public void cancelarPedido(@PathVariable Long codigo) {
		atendimentoLaiService.cancelarPedidoInformacao(codigo);
	}




}
