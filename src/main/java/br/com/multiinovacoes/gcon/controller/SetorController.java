package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.SetorController.SETOR;
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

import br.com.multiinovacoes.gcon.model.dto.SetorDto;
import br.com.multiinovacoes.gcon.model.mapper.SetorMapper;
import br.com.multiinovacoes.gcon.model.request.SetorRequest;
import br.com.multiinovacoes.gcon.model.response.ListaSetorResponse;
import br.com.multiinovacoes.gcon.model.response.SetorResponse;
import br.com.multiinovacoes.gcon.repository.SetorRepository;
import br.com.multiinovacoes.gcon.service.SetorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Setor", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = SETOR	)
public class SetorController {
	
	public static final String SETOR = "/setores";
	public static final String LISTAR = "/listar";
	public static final String PARAMETRO = "/{codigo}";
	public static final String LISTAR_ESPECIFICO = "/listarEspecifico/{codigoAtendimento}";
	public static final String LISTAR_ESPECIFICO_ATENDIMENTO = "/listarEspecifico";
	
	@Autowired
	SetorRepository setorRepository;
	
	@Autowired
	SetorService setorService;
	
	@Autowired
	SetorMapper setorMapper;

	
	@ApiOperation(value = "Obter lista de setores", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ListaSetorResponse getListaSetores(Long orgao) {
		return new ListaSetorResponse(setorService.getListaSetores(orgao));
	}

	@ApiOperation(value = "Obter lista de setores especificos sem o setor encaminhado aberto", nickname = LISTAR_ESPECIFICO_ATENDIMENTO)
	@GetMapping(path = LISTAR_ESPECIFICO_ATENDIMENTO, produces = APPLICATION_JSON_VALUE)
	public ListaSetorResponse getListaSetoresSemSetorEncaminhadoAberto(Long orgao, Long atendimento) {
		return new ListaSetorResponse(setorService.getListaSetores(orgao, atendimento));
	}

	@ApiOperation(value = "Obter lista de setores especificos do encaminhamento", nickname = LISTAR_ESPECIFICO)
	@GetMapping(path = LISTAR_ESPECIFICO, produces = APPLICATION_JSON_VALUE)
	public ListaSetorResponse getListaSetoresEspecifico(@PathVariable Long codigoAtendimento) {
		return new ListaSetorResponse(setorService.getSetoresDestino(codigoAtendimento));
	}


	@ApiOperation(value = "Obter uma setor para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public SetorResponse getEditarSetor(@PathVariable Long codigo) {
		return new SetorResponse(setorService.getSetor(codigo));
	}
	
	@ApiOperation(value = "Enviar uma setor", nickname = SETOR)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public SetorResponse salvarSetor(@Valid @RequestBody SetorRequest request) {
		SetorDto setorDto = setorMapper.fromSetor(request);
		return new SetorResponse(setorService.salvarSetor(setorDto));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma setor", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirSetor(@PathVariable Long codigo) {
		setorRepository.deleteById(codigo);
	}
	
	@ApiOperation(value = "Atualizar uma setor", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public SetorResponse atualizarSetor(@PathVariable Long codigo, @Valid @RequestBody SetorDto request) {
		return new SetorResponse(setorService.salvarSetor(request));
	}
	

	@ApiOperation(value = "Obter uma setor pelo filtro", nickname = SETOR)
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ListaSetorResponse getPesquisaSetor(@RequestParam(required = false, defaultValue = "%") String descricao, Long orgao) {
		return new ListaSetorResponse(setorService.getPesquisaSetorDescricao(orgao, descricao));
	}


}
