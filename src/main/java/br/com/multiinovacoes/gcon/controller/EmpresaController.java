package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.EmpresaController.EMPRESA;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.multiinovacoes.gcon.model.dto.EmpresaDto;
import br.com.multiinovacoes.gcon.model.mapper.EmpresaMapper;
import br.com.multiinovacoes.gcon.model.request.EmpresaRequest;
import br.com.multiinovacoes.gcon.model.response.EmpresaResponse;
import br.com.multiinovacoes.gcon.repository.EmpresaRepository;
import br.com.multiinovacoes.gcon.service.EmpresaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Empresa", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = EMPRESA	)
public class EmpresaController {
	
	public static final String EMPRESA = "/empresas";
	public static final String LISTAR = "/listar";
	public static final String PARAMETRO = "/{codigo}";

	
	@Autowired
	EmpresaRepository empresaRepository;
	
	@Autowired
	EmpresaService empresaService;
	
	@Autowired
	EmpresaMapper empresaMapper;
	
	@ApiOperation(value = "Obter lista de empresas", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmpresaDto>> getListaEmpresas(Long orgao) {
		return new ResponseEntity<>(empresaService.getListaEmpresas(orgao), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Obter uma empresa para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public EmpresaResponse getEditarEmpresa(@PathVariable Long codigo) {
		return new EmpresaResponse(empresaService.getEmpresa(codigo));
	}
	
	@ApiOperation(value = "Enviar uma empresa", nickname = EMPRESA)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public EmpresaResponse salvarEmpresa(@Valid @RequestBody EmpresaRequest request) {
		EmpresaDto empresaDto = empresaMapper.fromEmpresa(request);
		return new EmpresaResponse(empresaService.salvar(empresaDto));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma empresa", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirEmpresa(@PathVariable Long codigo) {
		empresaRepository.deleteById(codigo);
	}
	
	@ApiOperation(value = "Atualizar uma empresa", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public EmpresaResponse atualizarEmpresa(@PathVariable Long codigo, @Valid @RequestBody EmpresaDto request) {
		return new EmpresaResponse(empresaService.salvar(request));
	}
	



}
