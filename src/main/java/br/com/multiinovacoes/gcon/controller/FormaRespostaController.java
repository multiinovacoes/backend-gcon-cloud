package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.FormaRespostaController.FORMA_RESPOSTA;
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

import br.com.multiinovacoes.gcon.model.dto.FormaRespostaDto;
import br.com.multiinovacoes.gcon.model.mapper.FormaRespostaMapper;
import br.com.multiinovacoes.gcon.model.request.FormaRespostaRequest;
import br.com.multiinovacoes.gcon.model.response.FormaRespostaResponse;
import br.com.multiinovacoes.gcon.model.response.ListaFormaRespostaResponse;
import br.com.multiinovacoes.gcon.repository.FormaRespostaRepository;
import br.com.multiinovacoes.gcon.service.FormaRespostaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Forma Resposta", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = FORMA_RESPOSTA	)
public class FormaRespostaController {
	
	public static final String FORMA_RESPOSTA = "/formaRespostas";
	public static final String LISTAR = "/listar";
	public static final String PARAMETRO = "/{codigo}";

	
	@Autowired
	FormaRespostaRepository formaRespostaRepository;
	
	@Autowired
	FormaRespostaService formaRespostaService;
	
	@Autowired
	FormaRespostaMapper formaRespostaMapper;
	
	@ApiOperation(value = "Obter lista de forma Respostas", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ListaFormaRespostaResponse getListaFormaRespostas() {
		return new ListaFormaRespostaResponse(formaRespostaService.getListaFormaRespostas());
	}
	
	@ApiOperation(value = "Obter uma forma Resposta para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public FormaRespostaResponse getEditarFormaResposta(@PathVariable Long codigo) {
		return new FormaRespostaResponse(formaRespostaService.getFormaResposta(codigo));
	}
	
	@ApiOperation(value = "Enviar uma forma Resposta", nickname = FORMA_RESPOSTA)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public FormaRespostaResponse salvarFormaResposta(@Valid @RequestBody FormaRespostaRequest request) {
		FormaRespostaDto formaRespostaDto = formaRespostaMapper.fromFormaResposta(request);
		return new FormaRespostaResponse(formaRespostaService.salvarFormaResposta(formaRespostaDto));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma forma Resposta", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirFormaResposta(@PathVariable Long codigo) {
		formaRespostaRepository.deleteById(codigo);
	}
	
	@ApiOperation(value = "Atualizar uma forma Resposta", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public FormaRespostaResponse atualizarFormaResposta(@PathVariable Long codigo, @Valid @RequestBody FormaRespostaDto request) {
		return new FormaRespostaResponse(formaRespostaService.salvarFormaResposta(request));
	}
	



}
