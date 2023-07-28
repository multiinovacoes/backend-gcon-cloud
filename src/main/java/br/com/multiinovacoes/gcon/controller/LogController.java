package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.LogController.LOGS;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.multiinovacoes.gcon.model.response.ListaLogAtendimentoResponse;
import br.com.multiinovacoes.gcon.service.LogAtendimentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Log", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = LOGS	)
public class LogController {
	
	public static final String LOGS = "/logs";
	public static final String HISTORICO_ATENDIMENTO = "/historico-atendimento";

	
	@Autowired
	LogAtendimentoService logAtendimentoService;
	
	@ApiOperation(value = "Obter o logotipo", nickname = HISTORICO_ATENDIMENTO)
	@GetMapping(path = HISTORICO_ATENDIMENTO, produces = APPLICATION_JSON_VALUE)
	public ListaLogAtendimentoResponse getEditarLogoTipo(Long codigoAtendimento) {
		return new ListaLogAtendimentoResponse(logAtendimentoService.listaLogsAtendimento(codigoAtendimento));
	}


}
