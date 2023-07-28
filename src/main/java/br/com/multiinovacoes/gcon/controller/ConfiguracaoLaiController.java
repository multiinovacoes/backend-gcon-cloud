package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.ConfiguracaoLaiController.CONFIGURACAO_LAI;
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

import br.com.multiinovacoes.gcon.model.dto.ConfiguracaoLaiDto;
import br.com.multiinovacoes.gcon.model.mapper.ConfiguracaoLaiMapper;
import br.com.multiinovacoes.gcon.model.request.ConfiguracaoLaiRequest;
import br.com.multiinovacoes.gcon.model.response.ConfiguracaoLaiResponse;
import br.com.multiinovacoes.gcon.repository.ConfiguracaoLaiRepository;
import br.com.multiinovacoes.gcon.security.GconSecurity;
import br.com.multiinovacoes.gcon.service.ConfiguracaoLaiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Configuracao Lai", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = CONFIGURACAO_LAI	)
public class ConfiguracaoLaiController {
	
	public static final String CONFIGURACAO_LAI = "/configuracoes-lai";
	public static final String LISTAR = "/listar";
	public static final String PARAMETRO = "/{codigo}";

	
	@Autowired
	ConfiguracaoLaiRepository configuracaoRepository;
	
	@Autowired
	ConfiguracaoLaiService configuracaoService;
	
	@Autowired
	ConfiguracaoLaiMapper configuracaoMapper;
	
	@Autowired
	GconSecurity gconSecurity;


	@ApiOperation(value = "Obter uma configuração para edição", nickname = CONFIGURACAO_LAI)
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ConfiguracaoLaiResponse getEditar() {
		return new ConfiguracaoLaiResponse(configuracaoService.getConfiguracaoOrgao(gconSecurity.getOrgao()));
	}

	
	@ApiOperation(value = "Obter uma configuração para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public ConfiguracaoLaiResponse getEditarConfiguracao(@PathVariable Long codigo) {
		return new ConfiguracaoLaiResponse(configuracaoService.getConfiguracao(codigo));
	}
	
	@ApiOperation(value = "Enviar uma configuração", nickname = CONFIGURACAO_LAI)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public ConfiguracaoLaiResponse salvarConfiguracao(@Valid @RequestBody ConfiguracaoLaiRequest request) {
		ConfiguracaoLaiDto configuracaoDto = configuracaoMapper.fromConfiguracaoLai(request);
		return new ConfiguracaoLaiResponse(configuracaoService.salvarConfiguracao(configuracaoDto));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma configuração", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirConfiguracao(@PathVariable Long codigo) {
		configuracaoRepository.deleteById(codigo);
	}
	
	@ApiOperation(value = "Atualizar uma configuração", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public ConfiguracaoLaiResponse atualizarConfiguracao(@Valid @RequestBody ConfiguracaoLaiDto request) {
		return new ConfiguracaoLaiResponse(configuracaoService.salvarConfiguracao(request));
	}
	



}
