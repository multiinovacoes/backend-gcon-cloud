package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.TipoDocumentoController.TIPO_DOCUMENTO;
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

import br.com.multiinovacoes.gcon.model.dto.TipoDocumentoDto;
import br.com.multiinovacoes.gcon.model.mapper.TipoDocumentoMapper;
import br.com.multiinovacoes.gcon.model.request.TipoDocumentoRequest;
import br.com.multiinovacoes.gcon.model.response.ListaTipoDocumentoResponse;
import br.com.multiinovacoes.gcon.model.response.TipoDocumentoResponse;
import br.com.multiinovacoes.gcon.repository.TipoDocumentoRepository;
import br.com.multiinovacoes.gcon.service.TipoDocumentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "TipoDocumento", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = TIPO_DOCUMENTO	)
public class TipoDocumentoController {
	
	public static final String TIPO_DOCUMENTO = "/tipoDocumentos";
	public static final String LISTAR = "/listar";
	public static final String PARAMETRO = "/{codigo}";

	
	@Autowired
	TipoDocumentoRepository tipoDocumentoRepository;
	
	@Autowired
	TipoDocumentoService tipoDocumentoService;
	
	@Autowired
	TipoDocumentoMapper tipoDocumentoMapper;
	
	@ApiOperation(value = "Obter um tipoDocumento para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public TipoDocumentoResponse getEditarTipoDocumento(@PathVariable Long codigo, @RequestParam Long orgao) {
		return new TipoDocumentoResponse(tipoDocumentoService.getTipoDocumento(orgao, codigo));
	}
	
	@ApiOperation(value = "Enviar um tipo documento", nickname = TIPO_DOCUMENTO)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public TipoDocumentoResponse salvarTipoDocumento(@Valid @RequestBody TipoDocumentoRequest request) {
		TipoDocumentoDto tipoDocumentoDto = tipoDocumentoMapper.fromTipoDocumento(request);
		return new TipoDocumentoResponse(tipoDocumentoService.salvarTipoDocumento(tipoDocumentoDto));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir um tipo documento", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirTipoDocumento(@PathVariable Long codigo) {
		tipoDocumentoRepository.deleteById(codigo);
	}
	
	@ApiOperation(value = "Atualizar um tipo documento", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public TipoDocumentoResponse atualizarTipoDocumento(@PathVariable Long codigo, @Valid @RequestBody TipoDocumentoDto request) {
		return new TipoDocumentoResponse(tipoDocumentoService.salvarTipoDocumento(request));
	}
	

	@ApiOperation(value = "Obter lista de tipo documentos", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ListaTipoDocumentoResponse getListaTipoDocumentos(Long orgao) {
		return new ListaTipoDocumentoResponse(tipoDocumentoMapper.fromResponseList(tipoDocumentoRepository.findByOrgao(orgao)));
	}



}
