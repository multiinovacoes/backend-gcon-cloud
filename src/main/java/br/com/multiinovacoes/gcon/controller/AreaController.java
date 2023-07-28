package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.AreaController.AREA;
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

import br.com.multiinovacoes.gcon.model.dto.AreaDto;
import br.com.multiinovacoes.gcon.model.mapper.AreaMapper;
import br.com.multiinovacoes.gcon.model.request.AreaRequest;
import br.com.multiinovacoes.gcon.model.response.AreaResponse;
import br.com.multiinovacoes.gcon.model.response.ListaAreaResponse;
import br.com.multiinovacoes.gcon.repository.AreaRepository;
import br.com.multiinovacoes.gcon.service.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Area", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = AREA	)
public class AreaController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(AreaController.class);
	
	public static final String AREA = "/areas";
	public static final String LISTAR = "/listar";
	public static final String PARAMETRO = "/{codigo}";

	
	@Autowired
	AreaRepository areaRepository;
	
	@Autowired
	AreaService areaService;
	
	@Autowired
	AreaMapper areaMapper;
	
	
	@ApiOperation(value = "Obter lista de areas", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ListaAreaResponse getListaAreas(Long orgao) {
		return new ListaAreaResponse(areaService.getListaAreas(orgao));
	}
	
	@ApiOperation(value = "Obter uma area para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public AreaResponse getEditarArea(@PathVariable Long codigo) {
		return new AreaResponse(areaService.getArea(codigo));
	}
	
	@ApiOperation(value = "Enviar uma area", nickname = AREA)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public AreaResponse salvarArea(@Valid @RequestBody AreaRequest request) {
		AreaDto areaDto = areaMapper.fromArea(request);
		return new AreaResponse(areaService.salvarArea(areaDto));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma area", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirArea(@PathVariable Long codigo) {
		areaRepository.deleteById(codigo);
	}
	
	@ApiOperation(value = "Atualizar uma area", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public AreaResponse atualizarArea(@PathVariable Long codigo, @Valid @RequestBody AreaDto request) {
		return new AreaResponse(areaService.salvarArea(request));
	}
	

	@ApiOperation(value = "Obter uma area pelo filtro", nickname = AREA)
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ListaAreaResponse getPesquisaArea(@RequestParam(required = false, defaultValue = "%") String descricao, Long orgao) {
		return new ListaAreaResponse(areaService.getPesquisaAreaDescricao(orgao, descricao));
	}


}
