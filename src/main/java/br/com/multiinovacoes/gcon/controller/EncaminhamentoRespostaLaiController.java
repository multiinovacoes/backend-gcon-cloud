package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.EncaminhamentoRespostaLaiController.ENCAMINHAMENTO_RESPOSTA_LAI;
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

import br.com.multiinovacoes.gcon.model.dto.EncaminhamentoRespostaLaiSetorDto;
import br.com.multiinovacoes.gcon.model.mapper.EncaminhamentoRespostaLaiMapper;
import br.com.multiinovacoes.gcon.model.request.EncaminhamentoRespostaLaiRequest;
import br.com.multiinovacoes.gcon.model.response.EncaminhamentoRespostaLaiResponse;
import br.com.multiinovacoes.gcon.repository.EncaminhamentoRespostaLaiRepository;
import br.com.multiinovacoes.gcon.service.EncaminhamentoRespostaLaiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Encaminhamento Resposta", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = ENCAMINHAMENTO_RESPOSTA_LAI	)
public class EncaminhamentoRespostaLaiController {
	
	public static final String ENCAMINHAMENTO_RESPOSTA_LAI = "/encaminhamento-resposta-lai";
	public static final String PARAMETRO = "/{codigo}";
	public static final String PARAMETRO_ENCAMINHAMENTO = "/enc/{codigoResposta}";
	public static final String ENCAMINHAMENTO_RESPOSTA_SETOR = "/encaminhamentosRespostaSetor";
	
	@Autowired
	EncaminhamentoRespostaLaiRepository encaminhamentoRespostaRepository;
	
	@Autowired
	EncaminhamentoRespostaLaiService encaminhamentoRespostaService;
	
	@Autowired
	EncaminhamentoRespostaLaiMapper encaminhamentoRespostaMapper; 
	
	@ApiOperation(value = "Obter uma encaminhamento resposta para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public EncaminhamentoRespostaLaiResponse getEditarResposta(@PathVariable Long codigo) {
		return new EncaminhamentoRespostaLaiResponse(encaminhamentoRespostaService.getEncaminhamentoResposta(codigo));
	}
	
	@ApiOperation(value = "Obter uma encaminhamento resposta para edição", nickname = PARAMETRO_ENCAMINHAMENTO)
	@GetMapping(path = PARAMETRO_ENCAMINHAMENTO, produces = APPLICATION_JSON_VALUE)
	public boolean verifyResposta(@PathVariable Long codigoResposta) {
		return encaminhamentoRespostaService.findByCodigoEncaminhamento(codigoResposta) == null ? false : true;
	}

	@ApiOperation(value = "Enviar uma encaminhamento resposta", nickname = ENCAMINHAMENTO_RESPOSTA_LAI) 
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public void salvarEncaminhamentoResposta(@Valid @RequestBody EncaminhamentoRespostaLaiRequest request) {
		encaminhamentoRespostaService.salvarEncaminhamentoResposta(request);
	}
	
	@ApiOperation(value = "Atualizar uma encaminhamento resposta", nickname = PARAMETRO)
	@PutMapping(produces = APPLICATION_JSON_VALUE)
	public void atualizarEncaminhamentoResposta(@Valid @RequestBody EncaminhamentoRespostaLaiRequest request) {
		encaminhamentoRespostaService.atualizar(request);
	}

	@ApiOperation(value = "Responder um encaminhamento enviado", nickname = ENCAMINHAMENTO_RESPOSTA_SETOR) 
	@PostMapping(path = ENCAMINHAMENTO_RESPOSTA_SETOR, produces = APPLICATION_JSON_VALUE)
	public void salvarEncaminhamentoRespostaSetor(@RequestBody EncaminhamentoRespostaLaiSetorDto encaminhamentoRespostaSetor) throws IOException{
		encaminhamentoRespostaService.salvarEncaminhamentoResposta(encaminhamentoRespostaSetor);
		
	}

}
