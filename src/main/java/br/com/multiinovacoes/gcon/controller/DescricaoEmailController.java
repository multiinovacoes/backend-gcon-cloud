package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.DescricaoEmailController.DESCRICAO_EMAIL;
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

import br.com.multiinovacoes.gcon.model.dto.DescricaoEmailDto;
import br.com.multiinovacoes.gcon.model.mapper.DescricaoEmailMapper;
import br.com.multiinovacoes.gcon.model.request.DescricaoEmailRequest;
import br.com.multiinovacoes.gcon.model.response.DescricaoEmailResponse;
import br.com.multiinovacoes.gcon.repository.DescricaoEmailRepository;
import br.com.multiinovacoes.gcon.service.DescricaoEmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "DescricaoEmail", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = DESCRICAO_EMAIL	)
public class DescricaoEmailController {
	
	public static final String DESCRICAO_EMAIL = "/descricaoEmail";
	public static final String PARAMETRO = "/{codigo}";

	
	@Autowired
	DescricaoEmailRepository descricaoEmailRepository;
	
	@Autowired
	DescricaoEmailService descricaoEmailService;
	
	@Autowired
	DescricaoEmailMapper descricaoEmailMapper;
	
	@ApiOperation(value = "Obter uma descricao email para edição", nickname = DESCRICAO_EMAIL)
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public DescricaoEmailResponse getEditar(Long orgao) {
		return new DescricaoEmailResponse(descricaoEmailService.getDescricaoEmailOrgao(orgao));
	}
	
	@ApiOperation(value = "Enviar uma descricao email", nickname = DESCRICAO_EMAIL)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public DescricaoEmailResponse salvarDescricaoEmail(@Valid @RequestBody DescricaoEmailRequest request) {
		DescricaoEmailDto descricaoEmailDto = descricaoEmailMapper.fromDescricaoEmail(request);
		return new DescricaoEmailResponse(descricaoEmailService.salvarDescricaoEmail(descricaoEmailDto));
	}
	
	@ApiOperation(value = "Atualizar uma descricao email", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public DescricaoEmailResponse atualizarDescricaoEmail(@Valid @RequestBody DescricaoEmailDto request) {
		return new DescricaoEmailResponse(descricaoEmailService.salvarDescricaoEmail(request));
	}
	



}
