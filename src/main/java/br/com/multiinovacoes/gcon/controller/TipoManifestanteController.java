package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.TipoManifestanteController.TIPO_MANIFESTANTE;
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

import br.com.multiinovacoes.gcon.model.dto.TipoManifestanteDto;
import br.com.multiinovacoes.gcon.model.mapper.TipoManifestanteMapper;
import br.com.multiinovacoes.gcon.model.request.TipoManifestanteRequest;
import br.com.multiinovacoes.gcon.model.response.ListaTipoManifestanteResponse;
import br.com.multiinovacoes.gcon.model.response.TipoManifestanteResponse;
import br.com.multiinovacoes.gcon.repository.TipoManifestanteRepository;
import br.com.multiinovacoes.gcon.service.TipoManifestanteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "TipoManifestante", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = TIPO_MANIFESTANTE	)
public class TipoManifestanteController {
	
	public static final String TIPO_MANIFESTANTE = "/tiposManifestante";
	public static final String LISTAR = "/listar";
	public static final String LISTAR_SITE = "/listar-site";
	public static final String PARAMETRO = "/{codigo}";
	public static final String CONSULTA_TIPO_DOCUMENTO = "/consulta-tipodocumento";

	
	@Autowired
	TipoManifestanteRepository tipoManifestanteRepository;
	
	@Autowired
	TipoManifestanteService tipoManifestanteService;
	
	@Autowired
	TipoManifestanteMapper tipoManifestanteMapper;
	
	@ApiOperation(value = "Obter lista de tipo manifestantes", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ListaTipoManifestanteResponse getListaTipoManifestantes(Long orgao) {
		return new ListaTipoManifestanteResponse(tipoManifestanteService.getListaTipoManifestantes(orgao));
	}
	
	@ApiOperation(value = "Obter lista de tipo manifestantes", nickname = LISTAR)
	@GetMapping(path = LISTAR_SITE, produces = APPLICATION_JSON_VALUE)
	public ListaTipoManifestanteResponse getListaTipoManifestantesSite(Long orgao) {
		return new ListaTipoManifestanteResponse(tipoManifestanteService.getListaTipoManifestantesSite(orgao));
	}
	
	@ApiOperation(value = "Obter uma tipo manifestante para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public TipoManifestanteResponse getEditarTipoManifestante(@PathVariable Long codigo) {
		return new TipoManifestanteResponse(tipoManifestanteService.getTipoManifestante(codigo));
	}
	
	@ApiOperation(value = "Enviar uma tipo manifestante", nickname = TIPO_MANIFESTANTE)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public TipoManifestanteResponse salvarTipoManifestante(@Valid @RequestBody TipoManifestanteRequest request) {
		TipoManifestanteDto tipoManifestanteDto = tipoManifestanteMapper.fromTipoManifestante(request);
		return new TipoManifestanteResponse(tipoManifestanteService.salvarTipoManifestante(tipoManifestanteDto));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma tipo manifestante", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirTipoManifestante(@PathVariable Long codigo) {
		tipoManifestanteRepository.deleteById(codigo);
	}
	
	@ApiOperation(value = "Atualizar uma tipo manifestante", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public TipoManifestanteResponse atualizarTipoManifestante(@PathVariable Long codigo, @Valid @RequestBody TipoManifestanteDto request) {
		return new TipoManifestanteResponse(tipoManifestanteService.salvarTipoManifestante(request));
	}
	

	@ApiOperation(value = "Obter uma tipo manifestante pelo filtro", nickname = TIPO_MANIFESTANTE)
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ListaTipoManifestanteResponse getPesquisaTipoManifestante(@RequestParam(required = false, defaultValue = "%") String descricao, Long orgao) {
		return new ListaTipoManifestanteResponse(tipoManifestanteService.getPesquisaTipoManifestanteDescricao(orgao, descricao));
	}
	
	
	@ApiOperation(value = "Obter lista de tipo documentos", nickname = CONSULTA_TIPO_DOCUMENTO)
	@GetMapping(path = CONSULTA_TIPO_DOCUMENTO, produces = APPLICATION_JSON_VALUE)
	public Long consultaTipoDocumento(Long orgao, Long codigoTipoManifestante) {
		return tipoManifestanteRepository.findByOrgaoAndIdAndStatus(orgao, codigoTipoManifestante, 0).getTipoDocumento();
	}


}
