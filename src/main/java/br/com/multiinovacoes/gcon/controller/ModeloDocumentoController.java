package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.ModeloDocumentoController.MODELO_DOCUMENTO;
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

import br.com.multiinovacoes.gcon.model.dto.ModeloDocumentoDto;
import br.com.multiinovacoes.gcon.model.mapper.ModeloDocumentoMapper;
import br.com.multiinovacoes.gcon.model.request.EncaminhamentoRequest;
import br.com.multiinovacoes.gcon.model.request.ModeloDocumentoRequest;
import br.com.multiinovacoes.gcon.model.response.ListaModeloDocumentoResponse;
import br.com.multiinovacoes.gcon.model.response.ModeloDocumentoResponse;
import br.com.multiinovacoes.gcon.repository.ModeloDocumentoRepository;
import br.com.multiinovacoes.gcon.service.ModeloDocumentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "ModeloDocumento", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = MODELO_DOCUMENTO	)
public class ModeloDocumentoController {
	
	public static final String MODELO_DOCUMENTO = "/modelosDoc";
	public static final String LISTAR = "/listar";
	public static final String PARAMETRO = "/{codigo}";
	public static final String MODELO_DOCUMENTO_TIPO = "/modeloDocTipos";
	public static final String MODELO_DOCUMENTO_ENC = "/modeloDocEnc";
	public static final String MODELO_DOCUMENTO_OUTROS = "/modeloDocOutros";
	public static final String MODELO_DOCUMENTO_TIPO_SITE = "/modeloDocTiposSite";
	
	@Autowired
	ModeloDocumentoRepository modeloDocumentoRepository;
	
	@Autowired
	ModeloDocumentoService modeloDocumentoService;
	
	@Autowired
	ModeloDocumentoMapper modeloDocumentoMapper;
	
	@ApiOperation(value = "Obter lista de modeloDocumentos", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ListaModeloDocumentoResponse getListaModeloDocumentos(Long orgao) {
		return new ListaModeloDocumentoResponse(modeloDocumentoService.getListaModeloDocumentos(orgao));
	}

	@ApiOperation(value = "Obter uma modeloDocumento para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public ModeloDocumentoResponse getEditarModeloDocumento(@PathVariable Long codigo) {
		return new ModeloDocumentoResponse(modeloDocumentoService.getModeloDocumento(codigo));
	}

	@ApiOperation(value = "Pegar a descrição do modelo selecionado", nickname = MODELO_DOCUMENTO_ENC)
	@GetMapping(path = MODELO_DOCUMENTO_ENC, produces = APPLICATION_JSON_VALUE)
	public ModeloDocumentoResponse getModelo(EncaminhamentoRequest encaminhamento) {
		return  new ModeloDocumentoResponse(modeloDocumentoService.getModeloDocumento(encaminhamento));
	}

	@ApiOperation(value = "Pegar a descrição do modelo selecionado", nickname = MODELO_DOCUMENTO_ENC)
	@GetMapping(path = MODELO_DOCUMENTO_OUTROS, produces = APPLICATION_JSON_VALUE)
	public ModeloDocumentoResponse getModelo(Long codigoModelo, Long codigoAtendimento) {
		return  new ModeloDocumentoResponse(modeloDocumentoService.getModeloDocumento(codigoModelo, codigoAtendimento));
	}

	@ApiOperation(value = "Enviar uma modeloDocumento", nickname = MODELO_DOCUMENTO)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public ModeloDocumentoResponse salvarModeloDocumento(@Valid @RequestBody ModeloDocumentoRequest request) {
		ModeloDocumentoDto modeloDocumentoDto = modeloDocumentoMapper.fromModeloDocumento(request);
		return new ModeloDocumentoResponse(modeloDocumentoService.salvarModeloDocumento(modeloDocumentoDto));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma modeloDocumento", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirModeloDocumento(@PathVariable Long codigo) {
		modeloDocumentoRepository.deleteById(codigo);
	}
	
	@ApiOperation(value = "Atualizar uma modelo documento", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public ModeloDocumentoResponse atualizarModeloDocumento(@PathVariable Long codigo, @Valid @RequestBody ModeloDocumentoDto request) {
		return new ModeloDocumentoResponse(modeloDocumentoService.salvarModeloDocumento(request));
	}
	

	@ApiOperation(value = "Obter uma modelo documento pelo filtro", nickname = MODELO_DOCUMENTO)
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ListaModeloDocumentoResponse getPesquisaModeloDocumento(@RequestParam(required = false, defaultValue = "%") String descricao, Long orgao) {
		return new ListaModeloDocumentoResponse(modeloDocumentoService.getPesquisaModeloDocumentoDescricao(orgao, descricao));
	}

	@ApiOperation(value = "Obter uma modelo documento pelo tipo", nickname = MODELO_DOCUMENTO_TIPO)
	@GetMapping(path = MODELO_DOCUMENTO_TIPO, produces = APPLICATION_JSON_VALUE)
	public ListaModeloDocumentoResponse getPesquisaModeloDocumentoTipo(@RequestParam Integer tipo, Long orgao) {
		return new ListaModeloDocumentoResponse(modeloDocumentoService.getPesquisaModeloDocumentoTipo(orgao,tipo));
	}

	@ApiOperation(value = "Obter uma modelo documento pelo tipo", nickname = MODELO_DOCUMENTO_TIPO)
	@GetMapping(path = MODELO_DOCUMENTO_TIPO_SITE, produces = APPLICATION_JSON_VALUE)
	public ListaModeloDocumentoResponse getPesquisaModeloDocumentoSite(@RequestParam String orgao, @RequestParam String codigo) {
		return new ListaModeloDocumentoResponse(modeloDocumentoService.getPesquisaModeloDocumentoTipo(Long.parseLong(orgao), Integer.parseInt(codigo)));
	}


}
