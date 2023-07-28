package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.TipoExpressoesController.TIPO_EXPRESSOES;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.multiinovacoes.gcon.model.dto.TipoExpressoesDto;
import br.com.multiinovacoes.gcon.model.mapper.TipoExpressoesMapper;
import br.com.multiinovacoes.gcon.model.request.TipoExpressoesRequest;
import br.com.multiinovacoes.gcon.model.response.ListaTipoExpressoesResponse;
import br.com.multiinovacoes.gcon.model.response.TipoExpressoesResponse;
import br.com.multiinovacoes.gcon.repository.TipoExpressoesRepository;
import br.com.multiinovacoes.gcon.service.TipoExpressoesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Tipo Expressoes", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = TIPO_EXPRESSOES	)
public class TipoExpressoesController {
	
	public static final String TIPO_EXPRESSOES = "/tipoExpressoes";
	public static final String LISTAR = "/listar";
	public static final String PARAMETRO = "/{codigo}";

	
	@Autowired
	TipoExpressoesRepository tipoExpressoesRepository;
	
	@Autowired
	TipoExpressoesService tipoExpressoesService;
	
	@Autowired
	TipoExpressoesMapper tipoExpressoesMapper;
	
	@ApiOperation(value = "Obter lista de tipoExpressoess", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ListaTipoExpressoesResponse getListaTipoExpressoess(Long orgao) {
		return new ListaTipoExpressoesResponse(tipoExpressoesService.getListaTipoExpressoess(orgao));
	}
	
	@ApiOperation(value = "Obter uma tipoExpressoes para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public TipoExpressoesResponse getEditarTipoExpressoes(@PathVariable Long codigo) {
		return new TipoExpressoesResponse(tipoExpressoesService.getTipoExpressoes(codigo));
	}
	
	@ApiOperation(value = "Enviar uma tipoExpressoes", nickname = TIPO_EXPRESSOES)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public TipoExpressoesResponse salvarTipoExpressoes(@Valid @RequestBody TipoExpressoesRequest request) {
		TipoExpressoesDto tipoExpressoesDto = tipoExpressoesMapper.fromTipoExpressoes(request);
		return new TipoExpressoesResponse(tipoExpressoesService.salvarTipoExpressoes(tipoExpressoesDto));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma tipoExpressoes", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirTipoExpressoes(@PathVariable Long codigo) {
		tipoExpressoesRepository.deleteById(codigo);
	}
	
	@ApiOperation(value = "Atualizar uma tipoExpressoes", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public TipoExpressoesResponse atualizarTipoExpressoes(@PathVariable Long codigo, @Valid @RequestBody TipoExpressoesDto request) {
		return new TipoExpressoesResponse(tipoExpressoesService.salvarTipoExpressoes(request));
	}
	

	@ApiOperation(value = "Obter uma tipoExpressoes pelo filtro", nickname = TIPO_EXPRESSOES)
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ListaTipoExpressoesResponse getPesquisaTipoExpressoes(@RequestParam(required = false, defaultValue = "%") String descricao, Long orgao) {
		return new ListaTipoExpressoesResponse(tipoExpressoesService.getPesquisaTipoExpressoesDescricao(orgao, descricao));
	}


}
