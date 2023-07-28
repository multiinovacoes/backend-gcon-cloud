package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.AssuntoLaiController.ASSUNTO_LAI;
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

import br.com.multiinovacoes.gcon.model.dto.AssuntoLaiDto;
import br.com.multiinovacoes.gcon.model.mapper.AssuntoLaiMapper;
import br.com.multiinovacoes.gcon.model.request.AssuntoLaiRequest;
import br.com.multiinovacoes.gcon.model.response.AssuntoLaiResponse;
import br.com.multiinovacoes.gcon.model.response.ListaAssuntoLaiResponse;
import br.com.multiinovacoes.gcon.repository.AssuntoLaiRepository;
import br.com.multiinovacoes.gcon.security.GconSecurity;
import br.com.multiinovacoes.gcon.service.AssuntoLaiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Assunto Lai", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = ASSUNTO_LAI	)
public class AssuntoLaiController {
	
	public static final String ASSUNTO_LAI = "/assunto-lai";
	public static final String LISTAR = "/listar";
	public static final String PARAMETRO = "/{codigo}";
	public static final String LISTAR_ASSUNTOS = "/listar/assuntos";
	
	@Autowired
	AssuntoLaiRepository assuntoLaiRepository;
	
	@Autowired
	AssuntoLaiService assuntoLaiService;
	
	@Autowired
	AssuntoLaiMapper assuntoLaiMapper;
	
	@Autowired
	GconSecurity gconSecurity;

	
	@ApiOperation(value = "Obter lista de assuntos", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AssuntoLaiDto>> getListaAssuntos(@RequestParam Long orgao) {
		return new ResponseEntity<>(assuntoLaiService.getListaAssuntosOrgao(orgao), HttpStatus.OK);
	}

	@ApiOperation(value = "Obter lista de assuntos por area", nickname = LISTAR)
	@GetMapping(path = LISTAR_ASSUNTOS, produces = APPLICATION_JSON_VALUE)
	public ListaAssuntoLaiResponse getListaAssuntosArea(@RequestParam Long orgao, @RequestParam Long area) {
		return new ListaAssuntoLaiResponse(assuntoLaiService.getListaAssuntos(orgao,area));
	}

	@ApiOperation(value = "Obter uma assunto para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public AssuntoLaiResponse getEditarAssunto(@PathVariable Long codigo) {
		return new AssuntoLaiResponse(assuntoLaiService.getAssunto(codigo));
	}
	
	@ApiOperation(value = "Enviar uma assunto", nickname = ASSUNTO_LAI)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public AssuntoLaiResponse salvarAssunto(@Valid @RequestBody AssuntoLaiRequest request) {
		try {
			AssuntoLaiDto assuntoDto = assuntoLaiMapper.fromAssuntoLai(request);
			return new AssuntoLaiResponse(assuntoLaiService.salvarAssunto(assuntoDto));
		}catch (Exception e) {
		}
		return null;
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma assunto", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirAssunto(@PathVariable Long codigo) {
		assuntoLaiRepository.deleteById(codigo);
	}
	
	@ApiOperation(value = "Atualizar uma assunto", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public AssuntoLaiResponse atualizarAssunto(@PathVariable Long codigo, @Valid @RequestBody AssuntoLaiDto request) {
		return new AssuntoLaiResponse(assuntoLaiService.salvarAssunto(request));
	}
	

	@ApiOperation(value = "Obter uma assunto pelo filtro", nickname = ASSUNTO_LAI)
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ListaAssuntoLaiResponse getPesquisaAssunto(@RequestParam(required = false, defaultValue = "%") String descricao) {
		return new ListaAssuntoLaiResponse(assuntoLaiService.getPesquisaAssuntoDescricao(descricao, gconSecurity.getOrgao()));
	}


}
