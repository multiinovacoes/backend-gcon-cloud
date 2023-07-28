package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.AssuntoController.ASSUNTO;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import br.com.multiinovacoes.gcon.exception.NegocioException;
import br.com.multiinovacoes.gcon.model.dto.AssuntoDto;
import br.com.multiinovacoes.gcon.model.mapper.AssuntoMapper;
import br.com.multiinovacoes.gcon.model.request.AssuntoRequest;
import br.com.multiinovacoes.gcon.model.response.AssuntoResponse;
import br.com.multiinovacoes.gcon.model.response.ListaAssuntoResponse;
import br.com.multiinovacoes.gcon.repository.AssuntoRepository;
import br.com.multiinovacoes.gcon.service.AssuntoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Assunto", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = ASSUNTO	)
public class AssuntoController {
	
	public static final String ASSUNTO = "/assunto";
	public static final String LISTAR = "/listar";
	public static final String PARAMETRO = "/{codigo}";
	public static final String LISTAR_ASSUNTOS = "/listar/assuntos";
	
	@Autowired
	AssuntoRepository assuntoRepository;
	
	@Autowired
	AssuntoService assuntoService;
	
	@Autowired
	AssuntoMapper assuntoMapper;
	
	@ApiOperation(value = "Obter lista de assuntos", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AssuntoDto>> getListaAssuntos(@RequestParam Long orgao) {
		return new ResponseEntity<>(assuntoService.getListaAssuntosOrgao(orgao), HttpStatus.OK);
	}

	@ApiOperation(value = "Obter lista de assuntos por area", nickname = LISTAR)
	@GetMapping(path = LISTAR_ASSUNTOS, produces = APPLICATION_JSON_VALUE)
	public ListaAssuntoResponse getListaAssuntosArea(@RequestParam String orgao, @RequestParam Long area) {
		return new ListaAssuntoResponse(assuntoService.getListaAssuntos(Long.parseLong(orgao), area, StatusEnum.ATIVO.getCodigo()));
	}

	@ApiOperation(value = "Obter uma assunto para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public AssuntoResponse getEditarAssunto(@PathVariable Long codigo, @RequestParam Long orgao) {
		return new AssuntoResponse(assuntoService.getAssunto(codigo, orgao));
	}
	
	@ApiOperation(value = "Enviar uma assunto", nickname = ASSUNTO)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public AssuntoResponse salvarAssunto(@Valid @RequestBody AssuntoRequest request) {
		try {
			AssuntoDto assuntoDto = assuntoMapper.fromAssunto(request);
			return new AssuntoResponse(assuntoService.salvarAssunto(assuntoDto));
		}catch (Exception e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma assunto", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirAssunto(@PathVariable Long codigo) {
		assuntoRepository.deleteById(codigo);
	}
	
	@ApiOperation(value = "Atualizar uma assunto", nickname = PARAMETRO)
	@PutMapping(produces = APPLICATION_JSON_VALUE)
	public AssuntoResponse atualizarAssunto(@Valid @RequestBody AssuntoDto request) {
		return new AssuntoResponse(assuntoService.salvarAssunto(request));
	}
	

	@ApiOperation(value = "Obter uma assunto pelo filtro", nickname = ASSUNTO)
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ListaAssuntoResponse getPesquisaAssunto(@RequestParam(required = false, defaultValue = "%") String descricao, Long orgao) {
		return new ListaAssuntoResponse(assuntoService.getPesquisaAssuntoDescricao(descricao, orgao));
	}


}
