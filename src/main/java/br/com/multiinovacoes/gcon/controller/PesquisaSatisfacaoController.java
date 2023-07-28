package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.PesquisaSatisfacaoController.PESQUISA_SATISFACAO;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.multiinovacoes.gcon.model.dto.PerguntaSatisfacaoDto;
import br.com.multiinovacoes.gcon.model.request.PesquisaSatisfacaoRequest;
import br.com.multiinovacoes.gcon.service.PesquisaSatisfacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Pesquisa de Satisfação", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = PESQUISA_SATISFACAO	)
public class PesquisaSatisfacaoController {
	
	public static final String PESQUISA_SATISFACAO = "/pesquisaSatisfacao";
	public static final String LISTA_PESQUISA_SATISFACAO = "/lista-pesquisa-satisfacao";
	
	@Autowired
	private PesquisaSatisfacaoService pesquisaSatisfacaoService;
	
	
	@ApiOperation(value = "Enviar uma atendimento", nickname = PESQUISA_SATISFACAO)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public Boolean salvarPesquisaSatisfacao(@Valid @RequestBody PesquisaSatisfacaoRequest request) {
	    try {
	    	return pesquisaSatisfacaoService.getSalvarPesquisaSatisfacao(request);
	    }catch (Exception e) {
	         throw new ResponseStatusException(
	                 HttpStatus.NOT_FOUND, "Atendimento não encontrado", e);

		}
	}
	
	@ApiOperation(value = "Lista de perguntas e resposta de satisfação", nickname = LISTA_PESQUISA_SATISFACAO)
	@GetMapping(path = LISTA_PESQUISA_SATISFACAO, produces = APPLICATION_JSON_VALUE)
	public List<PerguntaSatisfacaoDto> getPerguntasPesquisaSatisfacao(Long orgao) {
		return pesquisaSatisfacaoService.listaPesquisaSatisfacao(orgao);
	}



}
