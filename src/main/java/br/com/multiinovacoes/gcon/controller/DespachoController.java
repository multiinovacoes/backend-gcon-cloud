package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.DespachoController.DESPACHO;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.multiinovacoes.gcon.model.mapper.DespachoMapper;
import br.com.multiinovacoes.gcon.model.request.CobrancaDespachoRequest;
import br.com.multiinovacoes.gcon.model.request.DespachoRequest;
import br.com.multiinovacoes.gcon.model.response.DespachoResponse;
import br.com.multiinovacoes.gcon.model.response.ListaDespachoResponse;
import br.com.multiinovacoes.gcon.repository.DespachoRepository;
import br.com.multiinovacoes.gcon.service.DespachoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Despacho", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = DESPACHO	)
public class DespachoController {
	
	public static final String DESPACHO = "/despachos";
	public static final String LISTAR = "/listar/atendimento/{codigo}";
	public static final String PARAMETRO = "/{codigo}";
	public static final String ENVIAR_DESPACHO_COBRANCA = "/enviar-despacho-cobranca";

	
	@Autowired
	DespachoRepository despachoRepository;
	
	@Autowired
	DespachoService despachoService;
	
	@Autowired
	DespachoMapper despachoMapper;
	
	
	@ApiOperation(value = "Obter lista de despachos", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ListaDespachoResponse getDespachos(@PathVariable Long codigo) {
		return new ListaDespachoResponse(despachoService.getDespachos(codigo));
	}

	
	
	@ApiOperation(value = "Obter uma despacho para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public DespachoResponse getEditarDespacho(@PathVariable Long codigo) {
		return new DespachoResponse(despachoService.getDespacho(codigo));
	}
	
	@ApiOperation(value = "Enviar uma despacho", nickname = DESPACHO) 
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public void salvarDespacho(@Valid @RequestBody DespachoRequest request) {
		despachoService.salvarDespacho(request);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma despacho", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirDespacho(@PathVariable Long codigo) {
		despachoService.excluir(codigo);
	}
	
	@ApiOperation(value = "Enviar despacho de cobrança", nickname = ENVIAR_DESPACHO_COBRANCA) 
	@PostMapping(path= ENVIAR_DESPACHO_COBRANCA, produces = APPLICATION_JSON_VALUE)
	public void enviarCobranca(@Valid @RequestBody CobrancaDespachoRequest request) {
		despachoService.enviarDespachoCobranca(request);
	}



}
