package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.ConfiguracaoController.CONFIGURACAO;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.multiinovacoes.gcon.model.dto.ConfiguracaoDto;
import br.com.multiinovacoes.gcon.model.mapper.ConfiguracaoMapper;
import br.com.multiinovacoes.gcon.model.request.ConfiguracaoRequest;
import br.com.multiinovacoes.gcon.model.response.ConfiguracaoResponse;
import br.com.multiinovacoes.gcon.repository.ConfiguracaoRepository;
import br.com.multiinovacoes.gcon.service.ConfiguracaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Configuracao", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = CONFIGURACAO	)
public class ConfiguracaoController {
	
	public static final String CONFIGURACAO = "/configuracoes";
	public static final String LISTAR = "/listar";
	public static final String PARAMETRO = "/{codigo}";

	
	@Autowired
	ConfiguracaoRepository configuracaoRepository;
	
	@Autowired
	ConfiguracaoService configuracaoService;
	
	@Autowired
	ConfiguracaoMapper configuracaoMapper;
	

	@ApiOperation(value = "Obter uma configuração para edição", nickname = CONFIGURACAO)
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ConfiguracaoResponse getEditar(Long orgao) {
		return new ConfiguracaoResponse(configuracaoService.getConfiguracaoOrgao(orgao));
	}

	
	@ApiOperation(value = "Obter uma configuração para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public ConfiguracaoResponse getEditarConfiguracao(@PathVariable Long codigo) {
		return new ConfiguracaoResponse(configuracaoService.getConfiguracao(codigo));
	}
	
	@ApiOperation(value = "Enviar uma configuração", nickname = CONFIGURACAO)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public ConfiguracaoResponse salvarConfiguracao(@Valid @RequestBody ConfiguracaoRequest request) {
		ConfiguracaoDto configuracaoDto = configuracaoMapper.fromConfiguracao(request);
		return new ConfiguracaoResponse(configuracaoService.salvarConfiguracao(configuracaoDto));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma configuração", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirConfiguracao(@PathVariable Long codigo) {
		configuracaoRepository.deleteById(codigo);
	}
	
	@ApiOperation(value = "Atualizar uma configuração", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public ConfiguracaoResponse atualizarConfiguracao(@Valid @RequestBody ConfiguracaoDto request) {
		return new ConfiguracaoResponse(configuracaoService.salvarConfiguracao(request));
	}
	



}
