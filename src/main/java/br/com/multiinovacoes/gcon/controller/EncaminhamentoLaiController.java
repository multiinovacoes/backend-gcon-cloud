package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.EncaminhamentoLaiController.ENCAMINHAMENTO_LAI;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.multiinovacoes.gcon.model.dto.EncaminhamentoRespostaLaiSetorDto;
import br.com.multiinovacoes.gcon.model.mapper.EncaminhamentoLaiMapper;
import br.com.multiinovacoes.gcon.model.request.EncaminhamentoLaiRequest;
import br.com.multiinovacoes.gcon.model.response.EncaminhamentoLaiResponse;
import br.com.multiinovacoes.gcon.model.response.ListaEncaminhamentoLaiResponse;
import br.com.multiinovacoes.gcon.model.response.ModeloDocumentoLaiResponse;
import br.com.multiinovacoes.gcon.repository.EncaminhamentoLaiRepository;
import br.com.multiinovacoes.gcon.service.EncaminhamentoLaiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Encaminhamento", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = ENCAMINHAMENTO_LAI	)
public class EncaminhamentoLaiController {
	
	public static final String ENCAMINHAMENTO_LAI = "/encaminhamento-lai";
	public static final String ENCAMINHAMENTOS_RECEBIDOS = "/encaminhamentos-recebidos-lai";
	public static final String LISTAR = "/listar/atendimento/{codigo}";
	public static final String LISTAR_ENC_ABERTOS = "/listarAbertos/atendimento/{codigo}";
	public static final String PARAMETRO = "/{codigo}";
	public static final String RESPOSTA_SATISFAZ = "/listar/encaminhamento/resposta/{codigo}";
	public static final String PARAMETRO_ENVIADO = "/resposta-setor/{parametro}";
	public static final String LISTAR_ATENDIMENTOS_ENVIADOS = "/listar-atendimentos-enviados";
	public static final String QTD_ENCAMINHAMENTOS_ENVIADOS_VENCTO = "/listar-enc-enviados-vencimento";
	public static final String QTD_ENCAMINHAMENTOS_ENVIADOS_VENCIDOS = "/listar-enc-enviados-vencidos";
	

	
	@Autowired
	EncaminhamentoLaiRepository encaminhamentoLaiRepository;
	
	@Autowired
	EncaminhamentoLaiService encaminhamentoLaiService;
	
	@Autowired
	EncaminhamentoLaiMapper encaminhamentoMapper;
	
	@ApiOperation(value = "Quantidade de encaminhamentos recebidos", nickname = PARAMETRO)
	@GetMapping(path = ENCAMINHAMENTOS_RECEBIDOS, produces = APPLICATION_JSON_VALUE)
	public Integer getQtdEncaminhamentosRecebidos(Long orgao) {
		return encaminhamentoLaiRepository.getEncaminhamentosRecebidos(orgao);
	}
	
	@ApiOperation(value = "Quantidade de atendimentos enviados", nickname = LISTAR_ATENDIMENTOS_ENVIADOS)
	@GetMapping(path = LISTAR_ATENDIMENTOS_ENVIADOS, produces = APPLICATION_JSON_VALUE)
	public Integer getQtdNovosAtendimentos(Long orgao) {
		return encaminhamentoLaiRepository.getEncaminhamentosEnviados(orgao);
	}

	@ApiOperation(value = "Quantidade de atendimentos enviados vencidos", nickname = QTD_ENCAMINHAMENTOS_ENVIADOS_VENCIDOS)
	@GetMapping(path = QTD_ENCAMINHAMENTOS_ENVIADOS_VENCIDOS, produces = APPLICATION_JSON_VALUE)
	public Integer getQtdEncaminhamentosEnviadosVencidos(Long orgao) {
		return encaminhamentoLaiRepository.getEncaminhamentosEnviadosVencidos(orgao);
	}

	@ApiOperation(value = "Quantidade de atendimentos enviados vencimento", nickname = QTD_ENCAMINHAMENTOS_ENVIADOS_VENCTO)
	@GetMapping(path = QTD_ENCAMINHAMENTOS_ENVIADOS_VENCTO, produces = APPLICATION_JSON_VALUE)
	public Integer getQtdEncaminhamentosEnviadosVencimento(Long orgao) {
		return encaminhamentoLaiService.getQtdAtendimentosEncaminhadosLaiVencimento(orgao);
	}

	@ApiOperation(value = "Obter lista de encaminhamentos", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ListaEncaminhamentoLaiResponse getListaEncaminhamentos(@PathVariable Long codigo) {
		return new ListaEncaminhamentoLaiResponse(encaminhamentoLaiService.listaEncaminhamentoComResposta(codigo));
	}

	@ApiOperation(value = "Obter resposta de conclusão do executor", nickname = LISTAR)
	@GetMapping(path = RESPOSTA_SATISFAZ, produces = APPLICATION_JSON_VALUE)
	public ModeloDocumentoLaiResponse getRespostaSatisfaz(@PathVariable Long codigo) {
		return new ModeloDocumentoLaiResponse(encaminhamentoLaiService.listaEncaminhamentoAtivosComRespostaSatisfaz(codigo)); 
	}

	@ApiOperation(value = "Obter lista de encaminhamentos abertos", nickname = LISTAR_ENC_ABERTOS)
	@GetMapping(path = LISTAR_ENC_ABERTOS, produces = APPLICATION_JSON_VALUE)
	public ListaEncaminhamentoLaiResponse getListaEncaminhamentosAbertos(@PathVariable Long codigo) {
		return new ListaEncaminhamentoLaiResponse(encaminhamentoMapper.fromResponseList(encaminhamentoLaiRepository.findByAtendimentoAndStatus(codigo, 0)));
	}

	@ApiOperation(value = "Obter uma encaminhamento para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public EncaminhamentoLaiResponse getEditarEncaminhamento(@PathVariable Long codigo) {
		return new EncaminhamentoLaiResponse(encaminhamentoLaiService.getEncaminhamento(codigo));
	}
	
	@ApiOperation(value = "Enviar uma encaminhamento", nickname = ENCAMINHAMENTO_LAI) 
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public void salvarEncaminhamento(@Valid @RequestBody EncaminhamentoLaiRequest request) {
		encaminhamentoLaiService.salvarEncaminhamento(request);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma encaminhamento", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirEncaminhamento(@PathVariable Long codigo) {
		encaminhamentoLaiService.excluir(codigo);
	}
	
	@ApiOperation(value = "Obter o encaminhamento", nickname = PARAMETRO_ENVIADO)
	@GetMapping(path = PARAMETRO_ENVIADO, produces = APPLICATION_JSON_VALUE)
	public EncaminhamentoRespostaLaiSetorDto getEncaminhamentoEnviado(@PathVariable("parametro") String parametro) {
		return encaminhamentoLaiService.getEncaminhamentoRespostaSetor(parametro);
	}
	

}
