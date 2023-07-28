package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.DescricaoOuvidoriaController.DESCRICAO_OUVIDORIA;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.multiinovacoes.gcon.model.dto.DescricaoOuvidoriaDto;
import br.com.multiinovacoes.gcon.model.mapper.DescricaoOuvidoriaMapper;
import br.com.multiinovacoes.gcon.model.request.DescricaoOuvidoriaRequest;
import br.com.multiinovacoes.gcon.model.response.DescricaoOuvidoriaResponse;
import br.com.multiinovacoes.gcon.repository.DescricaoOuvidoriaRepository;
import br.com.multiinovacoes.gcon.service.DescricaoOuvidoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "DescricaoOuvidoria", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = DESCRICAO_OUVIDORIA	)
public class DescricaoOuvidoriaController {
	
	public static final String DESCRICAO_OUVIDORIA = "/descricaoOuvidoria";
	public static final String PARAMETRO = "/{codigo}";

	
	@Autowired
	DescricaoOuvidoriaRepository descricaoOuvidoriaRepository;
	
	@Autowired
	DescricaoOuvidoriaService descricaoOuvidoriaService;
	
	@Autowired
	DescricaoOuvidoriaMapper descricaoOuvidoriaMapper;
	
	@ApiOperation(value = "Obter uma descricao ouvidoria", nickname = DESCRICAO_OUVIDORIA)
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public DescricaoOuvidoriaResponse getEditar(Long orgao) {
		return new DescricaoOuvidoriaResponse(descricaoOuvidoriaService.getDescricaoOuvidoriaOrgao(orgao));
	}
	
	@ApiOperation(value = "Enviar uma descricao ouvidoria", nickname = DESCRICAO_OUVIDORIA)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public DescricaoOuvidoriaResponse salvarDescricaoOuvidoria(@Valid @RequestBody DescricaoOuvidoriaRequest request) {
		DescricaoOuvidoriaDto descricaoOuvidoriaDto = descricaoOuvidoriaMapper.fromDescricaoOuvidoria(request);
		return new DescricaoOuvidoriaResponse(descricaoOuvidoriaService.salvarDescricaoOuvidoria(descricaoOuvidoriaDto));
	}
	
	@ApiOperation(value = "Atualizar uma descricao ouvidoria", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public DescricaoOuvidoriaResponse atualizarDescricaoOuvidoria(@Valid @RequestBody DescricaoOuvidoriaDto request) {
		return new DescricaoOuvidoriaResponse(descricaoOuvidoriaService.salvarDescricaoOuvidoria(request));
	}
	



}
