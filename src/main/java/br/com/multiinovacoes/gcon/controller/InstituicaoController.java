package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.InstituicaoController.INSTITUICAO;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import br.com.multiinovacoes.gcon.model.dto.InstituicaoDto;
import br.com.multiinovacoes.gcon.model.mapper.InstituicaoMapper;
import br.com.multiinovacoes.gcon.model.request.InstituicaoRequest;
import br.com.multiinovacoes.gcon.model.response.InstituicaoResponse;
import br.com.multiinovacoes.gcon.model.response.ListaInstituicaoResponse;
import br.com.multiinovacoes.gcon.repository.InstituicaoRepository;
import br.com.multiinovacoes.gcon.service.InstituicaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Instituição", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = INSTITUICAO	)
public class InstituicaoController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(InstituicaoController.class);
	
	public static final String INSTITUICAO = "/instituicao";
	public static final String LISTAR = "/listar";
	public static final String PARAMETRO = "/{codigo}";

	
	@Autowired
	InstituicaoRepository instituicaoRepository;
	
	@Autowired
	InstituicaoService instituicaoService;
	
	@Autowired
	InstituicaoMapper instituicaoMapper;
	
	@ApiOperation(value = "Obter lista de instituicão", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ListaInstituicaoResponse getListaInstituicoes(Long orgao) {
		return new ListaInstituicaoResponse(instituicaoService.getListaInstituicoes(orgao));
	}
	
	@ApiOperation(value = "Obter uma instituicão para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public InstituicaoResponse getEditarInstituicao(@PathVariable Long codigo) {
		return new InstituicaoResponse(instituicaoService.getInstituicao(codigo));
	}
	
	@ApiOperation(value = "Enviar uma instituicão", nickname = INSTITUICAO)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public InstituicaoResponse salvarInstituicao(@Valid @RequestBody InstituicaoRequest request) {
		InstituicaoDto instituicaoDto = instituicaoMapper.fromInstituicao(request);
		return new InstituicaoResponse(instituicaoService.salvarInstituicao(instituicaoDto));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma instituicão", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirInstituicao(@PathVariable Long codigo) {
		instituicaoRepository.deleteById(codigo);
	}
	
	@ApiOperation(value = "Atualizar uma instituicão", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public InstituicaoResponse atualizarInstituicao(@PathVariable Long codigo, @Valid @RequestBody InstituicaoDto request) {
		return new InstituicaoResponse(instituicaoService.salvarInstituicao(request));
	}
	

	@ApiOperation(value = "Obter uma instituicão pelo filtro", nickname = INSTITUICAO)
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ListaInstituicaoResponse getPesquisaInstituicao(@RequestParam(required = false, defaultValue = "%") String descricao, Long orgao) {
		return new ListaInstituicaoResponse(instituicaoService.getPesquisaInstituicaoDescricao(orgao, descricao));
	}


}
