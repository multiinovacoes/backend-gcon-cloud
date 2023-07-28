package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.LogoTipoController.LOGO_TIPO;
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

import br.com.multiinovacoes.gcon.model.dto.LogoTipoDto;
import br.com.multiinovacoes.gcon.model.mapper.LogoTipoMapper;
import br.com.multiinovacoes.gcon.model.request.LogoTipoRequest;
import br.com.multiinovacoes.gcon.model.response.LogoTipoResponse;
import br.com.multiinovacoes.gcon.repository.LogoTipoRepository;
import br.com.multiinovacoes.gcon.service.LogoTipoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "LogoTipo", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = LOGO_TIPO	)
public class LogoTipoController {
	
	public static final String LOGO_TIPO = "/logoTipo";
	public static final String LISTAR = "/listar";
	public static final String PARAMETRO = "/{codigo}";

	
	@Autowired
	LogoTipoRepository logoTipoRepository;
	
	@Autowired
	LogoTipoService logoTipoService;
	
	@Autowired
	LogoTipoMapper logoTipoMapper;
	
	@ApiOperation(value = "Enviar um logotipo email", nickname = LOGO_TIPO)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public LogoTipoResponse salvarLogoTipo(@Valid @RequestBody LogoTipoRequest request) {
		LogoTipoDto logoTipoDto = logoTipoMapper.fromLogoTipo(request);
		return new LogoTipoResponse(logoTipoService.salvarLogoTipo(logoTipoDto));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir um logotipo", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirLogoTipo(@PathVariable Long codigo) {
		logoTipoRepository.deleteById(codigo);
	}
	
	@ApiOperation(value = "Atualizar um logotipo", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public LogoTipoResponse atualizarLogoTipo(@Valid @RequestBody LogoTipoDto request) {
		return new LogoTipoResponse(logoTipoService.salvarLogoTipo(request));
	}
	
	@ApiOperation(value = "Obter o logotipo", nickname = LOGO_TIPO)
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public LogoTipoResponse getEditarLogoTipo(Long orgao) {
		return new LogoTipoResponse(logoTipoMapper.toDto(logoTipoRepository.findByOrgao(orgao)));
	}



}
