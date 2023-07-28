package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.NaturezaController.NATUREZA;
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

import br.com.multiinovacoes.gcon.exception.NegocioException;
import br.com.multiinovacoes.gcon.model.dto.NaturezaDto;
import br.com.multiinovacoes.gcon.model.mapper.NaturezaMapper;
import br.com.multiinovacoes.gcon.model.request.NaturezaRequest;
import br.com.multiinovacoes.gcon.model.response.ListaNaturezaResponse;
import br.com.multiinovacoes.gcon.model.response.NaturezaResponse;
import br.com.multiinovacoes.gcon.repository.NaturezaRepository;
import br.com.multiinovacoes.gcon.service.NaturezaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Natureza", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = NATUREZA	)
public class NaturezaController {
	
	
	public static final String NATUREZA = "/naturezas";
	public static final String LISTAR = "/listar";
	public static final String PARAMETRO = "/{codigo}";

	@Autowired
	NaturezaRepository naturezaRepository;
	
	@Autowired
	NaturezaService naturezaService;
	
	@Autowired
	NaturezaMapper naturezaMapper;
	
	@ApiOperation(value = "Obter lista de naturezas", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<NaturezaDto>> getListaNaturezas(Long orgao) {
		return new ResponseEntity<>(naturezaService.getListaNaturezas(orgao), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Obter uma natureza para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public NaturezaResponse getEditarNatureza(@PathVariable Long codigo) {
		return new NaturezaResponse(naturezaService.getNatureza(codigo));
	}
	
	@ApiOperation(value = "Enviar uma natureza", nickname = NATUREZA)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public NaturezaResponse salvarNatureza(@Valid @RequestBody NaturezaRequest request) {
		try {
			NaturezaDto naturezaDto = naturezaMapper.fromNatureza(request);
		return new NaturezaResponse(naturezaService.salvarNatureza(naturezaDto));
		}catch (Exception e) {
		     throw new NegocioException(e.getMessage(), e);
		}
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma natureza", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirNatureza(@PathVariable Long codigo) {
		naturezaRepository.deleteById(codigo);
	}
	
	@ApiOperation(value = "Atualizar uma natureza", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public NaturezaResponse atualizarNatureza(@PathVariable Long codigo, @Valid @RequestBody NaturezaDto request) {
		return new NaturezaResponse(naturezaService.salvarNatureza(request));
	}
	

	@ApiOperation(value = "Obter uma natureza pelo filtro", nickname = NATUREZA)
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ListaNaturezaResponse getPesquisaNatureza(@RequestParam(required = false, defaultValue = "%") String descricao) {
		return new ListaNaturezaResponse(naturezaService.getPesquisaNaturezaDescricao(descricao));
	}



}
