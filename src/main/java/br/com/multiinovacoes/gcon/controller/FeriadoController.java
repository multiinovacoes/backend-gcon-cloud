package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.FeriadoController.FERIADO;
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

import br.com.multiinovacoes.gcon.model.dto.FeriadoDto;
import br.com.multiinovacoes.gcon.model.mapper.FeriadoMapper;
import br.com.multiinovacoes.gcon.model.request.FeriadoRequest;
import br.com.multiinovacoes.gcon.model.response.FeriadoResponse;
import br.com.multiinovacoes.gcon.model.response.ListaFeriadoResponse;
import br.com.multiinovacoes.gcon.repository.FeriadoRepository;
import br.com.multiinovacoes.gcon.service.FeriadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Feriado", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = FERIADO	)
public class FeriadoController {
	
	public static final String FERIADO = "/feriados";
	public static final String LISTAR = "/listar";
	public static final String PARAMETRO = "/{codigo}";

	
	@Autowired
	FeriadoRepository feriadoRepository;
	
	@Autowired
	FeriadoService feriadoService;
	
	@Autowired
	FeriadoMapper feriadoMapper;
	
	@ApiOperation(value = "Obter lista de feriados", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ListaFeriadoResponse getListaFeriados(Long orgao) {
		return new ListaFeriadoResponse(feriadoService.getListaFeriados(orgao));
	}
	
	@ApiOperation(value = "Obter uma feriado para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public FeriadoResponse getEditarFeriado(@PathVariable Long codigo) {
		return new FeriadoResponse(feriadoService.getFeriado(codigo));
	}
	
	@ApiOperation(value = "Enviar uma feriado", nickname = FERIADO)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public FeriadoResponse salvarFeriado(@Valid @RequestBody FeriadoRequest request) {
		FeriadoDto feriadoDto = feriadoMapper.fromFeriado(request);
		return new FeriadoResponse(feriadoService.salvarFeriado(feriadoDto));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma feriado", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirFeriado(@PathVariable Long codigo) {
		feriadoRepository.deleteById(codigo);
	}
	
	@ApiOperation(value = "Atualizar uma feriado", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public FeriadoResponse atualizarFeriado(@PathVariable Long codigo, @Valid @RequestBody FeriadoDto request) {
		return new FeriadoResponse(feriadoService.salvarFeriado(request));
	}
	

	@ApiOperation(value = "Obter uma feriado pelo filtro", nickname = FERIADO)
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ListaFeriadoResponse getPesquisaFeriado(@RequestParam(required = false, defaultValue = "%") String descricao, Long orgao) {
		return new ListaFeriadoResponse(feriadoService.getPesquisaFeriadoDescricao(orgao, descricao));
	}


}
