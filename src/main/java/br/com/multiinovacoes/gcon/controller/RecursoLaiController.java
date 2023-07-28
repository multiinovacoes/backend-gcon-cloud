package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.RecursoLaiController.RECURSO_LAI;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.multiinovacoes.gcon.model.dto.RespostaRecursoDto;
import br.com.multiinovacoes.gcon.model.mapper.RecursoLaiMapper;
import br.com.multiinovacoes.gcon.model.request.RecursoLaiRequest;
import br.com.multiinovacoes.gcon.model.response.ListaRecursoLaiResponse;
import br.com.multiinovacoes.gcon.model.response.RecursoLaiResponse;
import br.com.multiinovacoes.gcon.repository.RecursoLaiRepository;
import br.com.multiinovacoes.gcon.security.GconSecurity;
import br.com.multiinovacoes.gcon.service.RecursoLaiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Recurso Lai", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = RECURSO_LAI	)
public class RecursoLaiController {
	
	public static final String RECURSO_LAI = "/recurso-lai";
	public static final String CONSULTA_PEDIDO = "/consultaPedido/{codigoAtendimento}";
	public static final String PARAMETRO = "/{codigo}";
	public static final String AUTORIZA_RECURSO = "/autoriza-recurso/{codigoAtendimento}";
	public static final String RESPONDER_RECURSO = "/responder-recurso";
	public static final String QTD_RECURSO_VENCIMENTO = "/listar-recurso-vencimento";
	public static final String PARAMETRO_ENVIADO = "/resposta-recurso/{parametro}";
	public static final String ENCAMINHAMENTO_RESPOSTA_RECURSO = "/encaminhamento-resposta-recurso";


	
	@Autowired 
	RecursoLaiRepository recursoLaiRepository;
	
	@Autowired
	RecursoLaiService recursoLaiService;
	
	@Autowired
	RecursoLaiMapper recursoLaiMapper;
	
	@Autowired
	GconSecurity gconSecurity;


	@ApiOperation(value = "Cadastrar uma manifestação")
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public RecursoLaiResponse  salvarRecurso(@Valid @RequestBody RecursoLaiRequest request) throws Exception{
		return new RecursoLaiResponse(recursoLaiService.salvarRecursoLai(request));
	}
	
	@ApiOperation(value = "Responder um recurso lai", nickname = RESPONDER_RECURSO)
	@PostMapping(path = RESPONDER_RECURSO, produces = APPLICATION_JSON_VALUE)
	public RecursoLaiResponse  responderRecursoLai(@Valid @RequestBody RecursoLaiRequest request) throws Exception{
		return new RecursoLaiResponse(recursoLaiService.responderRecursoLai(request));
	}
	
	@ApiOperation(value = "Lista de recursos", nickname = CONSULTA_PEDIDO)
	@GetMapping(path = CONSULTA_PEDIDO, produces = APPLICATION_JSON_VALUE)
	public ListaRecursoLaiResponse getListaRecursos(@PathVariable Long codigoAtendimento) {
		return new ListaRecursoLaiResponse(recursoLaiMapper.fromResponseList(recursoLaiRepository.findByAtendimento(codigoAtendimento)));
	}

	@ApiOperation(value = "Obter um recurso para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public RecursoLaiResponse getEditar(@PathVariable Long codigo) {
		return new RecursoLaiResponse(recursoLaiService.getRecursoLai(codigo));
	}
	
	@ApiOperation(value = "Obter um recurso para edição", nickname = AUTORIZA_RECURSO)
	@GetMapping(path = AUTORIZA_RECURSO, produces = APPLICATION_JSON_VALUE)
	public RecursoLaiResponse getAutorizaRecurso(@PathVariable String codigoAtendimento) {
		return new RecursoLaiResponse(recursoLaiService.getAutorizaRecurso(Long.parseLong(codigoAtendimento)));
	}
	
	@ApiOperation(value = "Quantidade de recursos proximos so vencimento", nickname = QTD_RECURSO_VENCIMENTO)
	@GetMapping(path = QTD_RECURSO_VENCIMENTO, produces = APPLICATION_JSON_VALUE)
	public Integer getQtdEncaminhamentosEnviadosVencimento(Long orgao) {
		return recursoLaiService.getQtdRecursosLaiVencimento(orgao);
	}

	@ApiOperation(value = "Obter o recurso", nickname = PARAMETRO_ENVIADO)
	@GetMapping(path = PARAMETRO_ENVIADO, produces = APPLICATION_JSON_VALUE)
	public RespostaRecursoDto getResponderRecursoInstancia(@PathVariable("parametro") String parametro) {
		return recursoLaiService.getRespostaRecursoInstancia(parametro);
	}
	
	@ApiOperation(value = "Responder um encaminhamento de recurso", nickname = ENCAMINHAMENTO_RESPOSTA_RECURSO) 
	@PostMapping(path = ENCAMINHAMENTO_RESPOSTA_RECURSO, produces = APPLICATION_JSON_VALUE)
	public void salvarEncaminhamentoRespostaRecurso(@RequestBody RespostaRecursoDto respostaRecursoDto) throws IOException{
		recursoLaiService.salvarEncaminhamentoRespostaRecurso(respostaRecursoDto);
	}



}
