package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.PerguntaFrequenteController.PERGUNTA_FREQUENTE;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.multiinovacoes.gcon.model.dto.PerguntaFrequenteDto;
import br.com.multiinovacoes.gcon.model.mapper.PerguntaFrequenteMapper;
import br.com.multiinovacoes.gcon.model.request.PerguntaFrequenteRequest;
import br.com.multiinovacoes.gcon.model.response.ListaPerguntaFrequenteResponse;
import br.com.multiinovacoes.gcon.model.response.PerguntaFrequenteResponse;
import br.com.multiinovacoes.gcon.repository.PerguntaFrequenteRepository;
import br.com.multiinovacoes.gcon.service.PerguntaFrequenteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Pergunta Frequente", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = PERGUNTA_FREQUENTE	)
public class PerguntaFrequenteController {
	
	public static final String PERGUNTA_FREQUENTE = "/pergunta-frequente";
	public static final String LISTAR = "/listar";
	public static final String PARAMETRO = "/{codigo}";

	
	@Autowired
	PerguntaFrequenteRepository perguntaFrequenteRepository;
	
	@Autowired
	PerguntaFrequenteService perguntaFrequenteService;
	
	@Autowired
	PerguntaFrequenteMapper perguntaFrequenteMapper;
	
	@ApiOperation(value = "Obter lista de perguntas frequentes", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ListaPerguntaFrequenteResponse getListaPerguntaFrequente(Long orgao) {
		return new ListaPerguntaFrequenteResponse(perguntaFrequenteMapper.fromResponseList(perguntaFrequenteRepository.findByOrgaoOrderByPerguntaAsc(orgao)));
	}
	
	@ApiOperation(value = "Obter uma pergunta frequente para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public PerguntaFrequenteResponse getEditarPerguntaFrequente(@PathVariable Long codigo) {
		return new PerguntaFrequenteResponse(perguntaFrequenteService.getPerguntaFrequente(codigo));
	}
	
	@ApiOperation(value = "Enviar uma pergunta frequente", nickname = PERGUNTA_FREQUENTE)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public PerguntaFrequenteResponse salvarPerguntaFrequente(@Valid @RequestBody PerguntaFrequenteRequest request) {
		PerguntaFrequenteDto perguntaFrequenteDto = perguntaFrequenteMapper.fromPerguntaFrequente(request);
		return new PerguntaFrequenteResponse(perguntaFrequenteService.salvar(perguntaFrequenteDto));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma pergunta frequente", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirPerguntaFrequente(@PathVariable Long codigo) {
		perguntaFrequenteRepository.deleteById(codigo);
	}
	
	@ApiOperation(value = "Atualizar uma pergunta frequente", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public PerguntaFrequenteResponse atualizarPerguntaFrequente(@PathVariable Long codigo, @Valid @RequestBody PerguntaFrequenteDto request) {
		return new PerguntaFrequenteResponse(perguntaFrequenteService.salvar(request));
	}
	



}
