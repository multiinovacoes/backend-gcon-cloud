package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.DespachoLaiController.DESPACHO_LAI;
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
import br.com.multiinovacoes.gcon.model.request.DespachoLaiRequest;
import br.com.multiinovacoes.gcon.model.response.DespachoLaiResponse;
import br.com.multiinovacoes.gcon.model.response.ListaDespachoLaiResponse;
import br.com.multiinovacoes.gcon.repository.DespachoLaiRepository;
import br.com.multiinovacoes.gcon.service.DespachoLaiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Despacho Lai", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = DESPACHO_LAI	)
public class DespachoLaiController {
	
	public static final String DESPACHO_LAI = "/despacho-lai";
	public static final String LISTAR = "/listar/atendimento/{codigo}";
	public static final String PARAMETRO = "/{codigo}";
	public static final String INSERT_DESPACHO = "/insert-despacho/atendimento/{codigoAtendimento}";

	
	@Autowired
	DespachoLaiRepository despachoRepository;
	
	@Autowired
	DespachoLaiService despachoService;
	
	@Autowired
	DespachoMapper despachoMapper;
	
	
	@ApiOperation(value = "Obter lista de despachos", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ListaDespachoLaiResponse getDespachos(@PathVariable Long codigo) {
		return new ListaDespachoLaiResponse(despachoService.getDespachos(codigo));
	}

	@ApiOperation(value = "Inserir Despacho Lai", nickname = INSERT_DESPACHO)
	@GetMapping(path = INSERT_DESPACHO, produces = APPLICATION_JSON_VALUE)
	public DespachoLaiResponse getInserirDespacho(@PathVariable Long codigoAtendimento) {
		return new DespachoLaiResponse(despachoService.getInsertDespacho(codigoAtendimento));
	}
	
	@ApiOperation(value = "Obter uma despacho para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public DespachoLaiResponse getEditarDespacho(@PathVariable Long codigo) {
		return new DespachoLaiResponse(despachoService.getDespacho(codigo));
	}
	
	@ApiOperation(value = "Enviar uma despacho", nickname = DESPACHO_LAI) 
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public void salvarDespacho(@Valid @RequestBody DespachoLaiRequest request) {
		despachoService.salvarDespacho(request);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma despacho", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirDespacho(@PathVariable Long codigo) {
		despachoService.excluir(codigo);
	}


}
