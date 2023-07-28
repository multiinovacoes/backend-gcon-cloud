package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.OrigemManifestacaoController.ORIGEM_MANIFESTACAO;
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

import br.com.multiinovacoes.gcon.model.dto.OrigemManifestacaoDto;
import br.com.multiinovacoes.gcon.model.mapper.OrigemManifestacaoMapper;
import br.com.multiinovacoes.gcon.model.request.OrigemManifestacaoRequest;
import br.com.multiinovacoes.gcon.model.response.ListaOrigemManifestacaoResponse;
import br.com.multiinovacoes.gcon.model.response.OrigemManifestacaoResponse;
import br.com.multiinovacoes.gcon.repository.OrigemManifestacaoRepository;
import br.com.multiinovacoes.gcon.service.OrigemManifestacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "OrigemManifestacao", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = ORIGEM_MANIFESTACAO	)
public class OrigemManifestacaoController {
	
	public static final String ORIGEM_MANIFESTACAO = "/origens";
	public static final String LISTAR = "/listar";
	public static final String PARAMETRO = "/{codigo}";

	
	@Autowired
	OrigemManifestacaoRepository origemManifestacaoRepository;
	
	@Autowired
	OrigemManifestacaoService origemManifestacaoService;
	
	@Autowired
	OrigemManifestacaoMapper origemManifestacaoMapper;
	
	@ApiOperation(value = "Obter lista de origem de manifestaçõess", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ListaOrigemManifestacaoResponse getListaOrigemManifestacaos(Long orgao) {
		return new ListaOrigemManifestacaoResponse(origemManifestacaoService.getListaOrigemManifestacaos(orgao));
	}
	
	@ApiOperation(value = "Obter uma origem de manifestações para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public OrigemManifestacaoResponse getEditarOrigemManifestacao(@PathVariable Long codigo) {
		return new OrigemManifestacaoResponse(origemManifestacaoService.getOrigemManifestacao(codigo));
	}
	
	@ApiOperation(value = "Enviar uma origem manifestação", nickname = ORIGEM_MANIFESTACAO)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public OrigemManifestacaoResponse salvarOrigemManifestacao(@Valid @RequestBody OrigemManifestacaoRequest request) {
		OrigemManifestacaoDto origemManifestacaoDto = origemManifestacaoMapper.fromOrigemManifestacao(request);
		return new OrigemManifestacaoResponse(origemManifestacaoService.salvarOrigemManifestacao(origemManifestacaoDto));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma origem manifestação", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirOrigemManifestacao(@PathVariable Long codigo) {
		origemManifestacaoRepository.deleteById(codigo);
	}
	
	@ApiOperation(value = "Atualizar uma origem manifestação", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public OrigemManifestacaoResponse atualizarOrigemManifestacao(@PathVariable Long codigo, @Valid @RequestBody OrigemManifestacaoDto request) {
		return new OrigemManifestacaoResponse(origemManifestacaoService.salvarOrigemManifestacao(request));
	}
	

	@ApiOperation(value = "Obter uma origem manifestação pelo filtro", nickname = ORIGEM_MANIFESTACAO)
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ListaOrigemManifestacaoResponse getPesquisaOrigemManifestacao(@RequestParam(required = false, defaultValue = "%") String descricao) {
		return new ListaOrigemManifestacaoResponse(origemManifestacaoService.getPesquisaOrigemManifestacaoDescricao(descricao));
	}


}
