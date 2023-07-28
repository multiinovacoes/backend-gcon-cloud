package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.ModeloDocumentoLaiController.MODELO_DOCUMENTO_LAI;
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

import br.com.multiinovacoes.gcon.model.dto.ModeloDocumentoLaiDto;
import br.com.multiinovacoes.gcon.model.mapper.ModeloDocumentoLaiMapper;
import br.com.multiinovacoes.gcon.model.request.EncaminhamentoLaiRequest;
import br.com.multiinovacoes.gcon.model.request.ModeloDocumentoLaiRequest;
import br.com.multiinovacoes.gcon.model.response.ListaModeloDocumentoLaiResponse;
import br.com.multiinovacoes.gcon.model.response.ModeloDocumentoLaiResponse;
import br.com.multiinovacoes.gcon.repository.ModeloDocumentoLaiRepository;
import br.com.multiinovacoes.gcon.security.GconSecurity;
import br.com.multiinovacoes.gcon.service.ModeloDocumentoLaiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "ModeloDocumento", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = MODELO_DOCUMENTO_LAI	)
public class ModeloDocumentoLaiController {
	
	public static final String MODELO_DOCUMENTO_LAI = "/modelos-doc-lai";
	public static final String LISTAR = "/listar";
	public static final String PARAMETRO = "/{codigo}";
	public static final String MODELO_DOCUMENTO_TIPO = "/modeloDocTipos";
	public static final String MODELO_DOCUMENTO_ENC = "/modeloDocEnc";
	public static final String MODELO_DOCUMENTO_OUTROS = "/modeloDocOutros";
	public static final String MODELO_DOCUMENTO_TIPO_SITE = "/modeloDocTiposSite";
	
	@Autowired
	ModeloDocumentoLaiRepository modeloDocumentoRepository;
	
	@Autowired
	ModeloDocumentoLaiService modeloDocumentoService;
	
	@Autowired
	ModeloDocumentoLaiMapper modeloDocumentoMapper;
	
	@Autowired
	GconSecurity gconSecurity;

	
	@ApiOperation(value = "Obter lista de modeloDocumentos", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ListaModeloDocumentoLaiResponse getListaModeloDocumentos() {
		return new ListaModeloDocumentoLaiResponse(modeloDocumentoService.getListaModeloDocumentos(gconSecurity.getOrgao()));
	}

	@ApiOperation(value = "Obter uma modeloDocumento para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public ModeloDocumentoLaiResponse getEditarModeloDocumento(@PathVariable Long codigo) {
		return new ModeloDocumentoLaiResponse(modeloDocumentoService.getModeloDocumento(codigo));
	}

	@ApiOperation(value = "Pegar a descrição do modelo selecionado", nickname = MODELO_DOCUMENTO_ENC)
	@GetMapping(path = MODELO_DOCUMENTO_ENC, produces = APPLICATION_JSON_VALUE)
	public ModeloDocumentoLaiResponse getModelo(EncaminhamentoLaiRequest encaminhamento) {
		return  new ModeloDocumentoLaiResponse(modeloDocumentoService.getModeloDocumento(encaminhamento));
	}

	@ApiOperation(value = "Pegar a descrição do modelo selecionado", nickname = MODELO_DOCUMENTO_ENC)
	@GetMapping(path = MODELO_DOCUMENTO_OUTROS, produces = APPLICATION_JSON_VALUE)
	public ModeloDocumentoLaiResponse getModelo(Long codigoModelo, Long codigoAtendimento) {
		return  new ModeloDocumentoLaiResponse(modeloDocumentoService.getModeloDocumento(codigoModelo, codigoAtendimento));
	}

	@ApiOperation(value = "Enviar uma modeloDocumento", nickname = MODELO_DOCUMENTO_LAI)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public ModeloDocumentoLaiResponse salvarModeloDocumento(@Valid @RequestBody ModeloDocumentoLaiRequest request) {
		ModeloDocumentoLaiDto modeloDocumentoDto = modeloDocumentoMapper.fromModeloDocumento(request);
		return new ModeloDocumentoLaiResponse(modeloDocumentoService.salvarModeloDocumento(modeloDocumentoDto));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma modeloDocumento", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirModeloDocumento(@PathVariable Long codigo) {
		modeloDocumentoRepository.deleteById(codigo);
	}
	
	@ApiOperation(value = "Atualizar uma modelo documento", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public ModeloDocumentoLaiResponse atualizarModeloDocumento(@PathVariable Long codigo, @Valid @RequestBody ModeloDocumentoLaiDto request) {
		return new ModeloDocumentoLaiResponse(modeloDocumentoService.salvarModeloDocumento(request));
	}
	

	@ApiOperation(value = "Obter uma modelo documento pelo filtro", nickname = MODELO_DOCUMENTO_LAI)
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ListaModeloDocumentoLaiResponse getPesquisaModeloDocumento(@RequestParam(required = false, defaultValue = "%") String descricao) {
		return new ListaModeloDocumentoLaiResponse(modeloDocumentoService.getPesquisaModeloDocumentoDescricao(gconSecurity.getOrgao(), descricao));
	}

	@ApiOperation(value = "Obter uma modelo documento pelo tipo", nickname = MODELO_DOCUMENTO_TIPO)
	@GetMapping(path = MODELO_DOCUMENTO_TIPO, produces = APPLICATION_JSON_VALUE)
	public ListaModeloDocumentoLaiResponse getPesquisaModeloDocumentoTipo(@RequestParam Integer tipo) {
		return new ListaModeloDocumentoLaiResponse(modeloDocumentoService.getPesquisaModeloDocumentoTipo(gconSecurity.getOrgao(),tipo));
	}

	@ApiOperation(value = "Obter uma modelo documento pelo tipo", nickname = MODELO_DOCUMENTO_TIPO)
	@GetMapping(path = MODELO_DOCUMENTO_TIPO_SITE, produces = APPLICATION_JSON_VALUE)
	public ListaModeloDocumentoLaiResponse getPesquisaModeloDocumentoSite(@RequestParam String orgao, @RequestParam String codigo) {
		return new ListaModeloDocumentoLaiResponse(modeloDocumentoService.getPesquisaModeloDocumentoTipo(Long.parseLong(orgao), Integer.parseInt(codigo)));
	}


}
