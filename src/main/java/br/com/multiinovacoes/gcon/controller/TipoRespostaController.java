package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.TipoRespostaController.TIPO_RESPOSTA;
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

import br.com.multiinovacoes.gcon.model.dto.TipoRespostaDto;
import br.com.multiinovacoes.gcon.model.mapper.TipoRespostaMapper;
import br.com.multiinovacoes.gcon.model.request.TipoRespostaRequest;
import br.com.multiinovacoes.gcon.model.response.ListaTipoRespostaResponse;
import br.com.multiinovacoes.gcon.model.response.TipoRespostaResponse;
import br.com.multiinovacoes.gcon.repository.TipoRespostaRepository;
import br.com.multiinovacoes.gcon.service.TipoRespostaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Tipo Resposta", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = TIPO_RESPOSTA	)
public class TipoRespostaController {
	
	
	public static final String TIPO_RESPOSTA = "/tiposRespostas";
	public static final String LISTAR = "/listar";
	public static final String PARAMETRO = "/{codigo}";

	@Autowired
	TipoRespostaRepository tipoRespostaRepository;
	
	@Autowired
	TipoRespostaService tipoRespostaService;
	
	@Autowired
	TipoRespostaMapper tipoRespostaMapper;
	
	@ApiOperation(value = "Obter lista de tipoRespostas", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ListaTipoRespostaResponse getListaTipoRespostas(Long orgao) {
		return new ListaTipoRespostaResponse(tipoRespostaService.getListaTipoRespostas(orgao));
	}
	
	@ApiOperation(value = "Obter uma tipoResposta para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public TipoRespostaResponse getEditarTipoResposta(@PathVariable Long codigo) {
		return new TipoRespostaResponse(tipoRespostaService.getTipoResposta(codigo));
	}
	
	@ApiOperation(value = "Enviar uma tipoResposta", nickname = TIPO_RESPOSTA)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public TipoRespostaResponse salvarTipoResposta(@Valid @RequestBody TipoRespostaRequest request) {
		TipoRespostaDto tipoRespostaDto = tipoRespostaMapper.fromTipoResposta(request);
		return new TipoRespostaResponse(tipoRespostaService.salvarTipoResposta(tipoRespostaDto));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma tipoResposta", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirTipoResposta(@PathVariable Long codigo) {
		tipoRespostaRepository.deleteById(codigo);
	}
	
	@ApiOperation(value = "Atualizar uma tipoResposta", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public TipoRespostaResponse atualizarTipoResposta(@PathVariable Long codigo, @Valid @RequestBody TipoRespostaDto request) {
		return new TipoRespostaResponse(tipoRespostaService.salvarTipoResposta(request));
	}
	

	@ApiOperation(value = "Obter uma tipoResposta pelo filtro", nickname = TIPO_RESPOSTA)
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ListaTipoRespostaResponse getPesquisaTipoResposta(@RequestParam(required = false, defaultValue = "%") String descricao) {
		return new ListaTipoRespostaResponse(tipoRespostaService.getPesquisaTipoRespostaDescricao(descricao));
	}



}
