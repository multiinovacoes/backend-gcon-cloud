package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.AreaLaiController.AREA_LAI;
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

import br.com.multiinovacoes.gcon.model.dto.AreaLaiDto;
import br.com.multiinovacoes.gcon.model.mapper.AreaLaiMapper;
import br.com.multiinovacoes.gcon.model.request.AreaLaiRequest;
import br.com.multiinovacoes.gcon.model.response.AreaLaiResponse;
import br.com.multiinovacoes.gcon.model.response.ListaAreaLaiResponse;
import br.com.multiinovacoes.gcon.repository.AreaLaiRepository;
import br.com.multiinovacoes.gcon.security.GconSecurity;
import br.com.multiinovacoes.gcon.service.AreaLaiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Area", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = AREA_LAI	)
public class AreaLaiController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(AreaLaiController.class);
	
	public static final String AREA_LAI = "/area-lai";
	public static final String LISTAR = "/listar";
	public static final String LISTAR_AREAS = "/listar/instituicao";
	public static final String PARAMETRO = "/{codigo}";

	
	@Autowired
	AreaLaiRepository areaLaiRepository;
	
	@Autowired
	AreaLaiService areaLaiService;
	
	@Autowired
	AreaLaiMapper areaLaiMapper;
	
	@Autowired
	GconSecurity gconSecurity;
	
	@ApiOperation(value = "Obter lista de areas", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ListaAreaLaiResponse getListaAreas() {
		return new ListaAreaLaiResponse(areaLaiService.getListaAreas(gconSecurity.getOrgao()));
	}

	@ApiOperation(value = "Obter lista de areas pela instituição", nickname = LISTAR_AREAS)
	@GetMapping(path = LISTAR_AREAS, produces = APPLICATION_JSON_VALUE)
	public ListaAreaLaiResponse getListaAreas(Long instituicao) {
		return new ListaAreaLaiResponse(areaLaiService.getListaAreas(gconSecurity.getOrgao(), instituicao));
	}

	@ApiOperation(value = "Obter uma area para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public AreaLaiResponse getEditarArea(@PathVariable Long codigo) {
		return new AreaLaiResponse(areaLaiService.getArea(codigo));
	}
	
	@ApiOperation(value = "Enviar uma area", nickname = AREA_LAI)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public AreaLaiResponse salvarArea(@Valid @RequestBody AreaLaiRequest request) {
		AreaLaiDto areaDto = areaLaiMapper.fromAreaLai(request);
		return new AreaLaiResponse(areaLaiService.salvarArea(areaDto));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma area", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirArea(@PathVariable Long codigo) {
		areaLaiRepository.deleteById(codigo);
	}
	
	@ApiOperation(value = "Atualizar uma area", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public AreaLaiResponse atualizarArea(@PathVariable Long codigo, @Valid @RequestBody AreaLaiDto request) {
		return new AreaLaiResponse(areaLaiService.salvarArea(request));
	}
	

	@ApiOperation(value = "Obter uma area pelo filtro", nickname = AREA_LAI)
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ListaAreaLaiResponse getPesquisaArea(@RequestParam(required = false, defaultValue = "%") String descricao) {
		return new ListaAreaLaiResponse(areaLaiService.getPesquisaAreaDescricao(gconSecurity.getOrgao(), descricao));
	}


}
