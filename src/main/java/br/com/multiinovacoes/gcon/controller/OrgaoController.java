package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.OrgaoController.ORGAO;
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

import br.com.multiinovacoes.gcon.enums.StatusEnum;
import br.com.multiinovacoes.gcon.model.dto.OrgaoDto;
import br.com.multiinovacoes.gcon.model.mapper.OrgaoMapper;
import br.com.multiinovacoes.gcon.model.request.OrgaoRequest;
import br.com.multiinovacoes.gcon.model.response.ListaOrgaoResponse;
import br.com.multiinovacoes.gcon.model.response.OrgaoResponse;
import br.com.multiinovacoes.gcon.repository.OrgaoRepository;
import br.com.multiinovacoes.gcon.service.OrgaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Orgão", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = ORGAO	)
public class OrgaoController {
	
	public static final String ORGAO = "/orgaos";
	public static final String LISTAR = "/listar";
	public static final String LISTAR_ATIVOS = "/listar-ativos";
	public static final String PARAMETRO = "/{codigo}";
	
	
	@Autowired
	OrgaoRepository orgaoRepository; 
	
	@Autowired
	OrgaoService orgaoService;
	
	@Autowired
	OrgaoMapper orgaoMapper;
	
	@ApiOperation(value = "Obter lista de orgãos", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ListaOrgaoResponse getListaOrgaos() {
		return new ListaOrgaoResponse(orgaoService.getListaOrgaos());
	}
	
	@ApiOperation(value = "Obter lista de orgãos ativos", nickname = LISTAR_ATIVOS)
	@GetMapping(path = LISTAR_ATIVOS, produces = APPLICATION_JSON_VALUE)
	public ListaOrgaoResponse getListaOrgaosAtivos() {
		return new ListaOrgaoResponse(orgaoService.getListaOrgaosAtivos(StatusEnum.ATIVO.getCodigo()));
	}	
	
	@ApiOperation(value = "Obter um órgão para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public OrgaoResponse getEditarOrgao(@PathVariable Long codigo) {
		return new OrgaoResponse(orgaoService.getOrgao(codigo));
	}
	
	@ApiOperation(value = "Enviar um órgão", nickname = ORGAO)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public OrgaoResponse salvarNatureza(@Valid @RequestBody OrgaoRequest request) {
		OrgaoDto orgaoDto = orgaoMapper.fromOrgao(request);
		return new OrgaoResponse(orgaoService.salvarOrgao(orgaoDto));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir um órgão", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirOrgao(@PathVariable Long codigo) {
		orgaoRepository.deleteById(codigo);
	}
	
	@ApiOperation(value = "Atualizar um órgão", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public OrgaoResponse atualizarOrgao(@PathVariable Long codigo, @Valid @RequestBody OrgaoDto request) {
		return new OrgaoResponse(orgaoService.salvarOrgao(request));
	}
	

	@ApiOperation(value = "Obter um órgão pelo filtro", nickname = ORGAO)
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ListaOrgaoResponse getPesquisaOrgao(@RequestParam(required = false, defaultValue = "%") String descricao) {
		return new ListaOrgaoResponse(orgaoService.getPesquisaOrgaoDescricao(descricao));
	}



}
