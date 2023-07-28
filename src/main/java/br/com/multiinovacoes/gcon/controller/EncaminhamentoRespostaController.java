package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.EncaminhamentoRespostaController.ENCAMINHAMENTO_RESPOSTA;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.multiinovacoes.gcon.model.dto.EncaminhamentoRespostaSetorDto;
import br.com.multiinovacoes.gcon.model.mapper.EncaminhamentoRespostaMapper;
import br.com.multiinovacoes.gcon.model.request.EncaminhamentoRespostaRequest;
import br.com.multiinovacoes.gcon.model.response.EncaminhamentoRespostaResponse;
import br.com.multiinovacoes.gcon.repository.EncaminhamentoRespostaRepository;
import br.com.multiinovacoes.gcon.service.EncaminhamentoRespostaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Encaminhamento Resposta", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = ENCAMINHAMENTO_RESPOSTA	)
public class EncaminhamentoRespostaController {
	
	public static final String ENCAMINHAMENTO_RESPOSTA = "/encaminhamentosResposta";
	public static final String PARAMETRO = "/{codigo}";
	public static final String PARAMETRO_ENCAMINHAMENTO = "/enc/{codigoResposta}";
	public static final String ENCAMINHAMENTO_RESPOSTA_SETOR = "/encaminhamentosRespostaSetor";
	
	@Autowired
	EncaminhamentoRespostaRepository encaminhamentoRespostaRepository;
	
	@Autowired
	EncaminhamentoRespostaService encaminhamentoRespostaService;
	
	@Autowired
	EncaminhamentoRespostaMapper encaminhamentoRespostaMapper; 
	
	@ApiOperation(value = "Obter uma encaminhamento resposta para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public EncaminhamentoRespostaResponse getEditarResposta(@PathVariable Long codigo) {
		return new EncaminhamentoRespostaResponse(encaminhamentoRespostaService.getEncaminhamentoResposta(codigo));
	}
	
	@ApiOperation(value = "Obter uma encaminhamento resposta para edição", nickname = PARAMETRO_ENCAMINHAMENTO)
	@GetMapping(path = PARAMETRO_ENCAMINHAMENTO, produces = APPLICATION_JSON_VALUE)
	public boolean verifyResposta(@PathVariable Long codigoResposta) {
		return encaminhamentoRespostaService.findByCodigoEncaminhamento(codigoResposta) == null ? false : true;
	}

	@ApiOperation(value = "Enviar uma encaminhamento resposta", nickname = ENCAMINHAMENTO_RESPOSTA) 
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public void salvarEncaminhamentoResposta(@Valid @RequestBody EncaminhamentoRespostaRequest request) {
		encaminhamentoRespostaService.salvarEncaminhamentoResposta(request);
	}
	
	@ApiOperation(value = "Atualizar uma encaminhamento resposta", nickname = PARAMETRO)
	@PutMapping(produces = APPLICATION_JSON_VALUE)
	public void atualizarEncaminhamentoResposta(@Valid @RequestBody EncaminhamentoRespostaRequest request) {
		encaminhamentoRespostaService.atualizar(request);
	}

	@ApiOperation(value = "Responder um encaminhamento enviado", nickname = ENCAMINHAMENTO_RESPOSTA_SETOR) 
	@PostMapping(path = ENCAMINHAMENTO_RESPOSTA_SETOR, produces = APPLICATION_JSON_VALUE)
	public void salvarEncaminhamentoRespostaSetor(@RequestBody EncaminhamentoRespostaSetorDto encaminhamentoRespostaSetor) throws IOException{
		encaminhamentoRespostaService.salvarEncaminhamentoResposta(encaminhamentoRespostaSetor);
	}

}
