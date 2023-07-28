package br.com.multiinovacoes.gcon.report.controller;

import static br.com.multiinovacoes.gcon.report.controller.RelatorioController.RELATORIO;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.multiinovacoes.gcon.model.request.RelatorioGeralRequest;
import br.com.multiinovacoes.gcon.report.DadosComparativoPeriodo;
import br.com.multiinovacoes.gcon.report.DadosGrafico;
import br.com.multiinovacoes.gcon.report.DadosRelatorio;
import br.com.multiinovacoes.gcon.report.DadosRelatorioUsuario;
import br.com.multiinovacoes.gcon.report.DetalheAtendimento;
import br.com.multiinovacoes.gcon.report.DetalheAtendimentoArea;
import br.com.multiinovacoes.gcon.report.FusionCharts;
import br.com.multiinovacoes.gcon.report.RelatorioPeriodo;
import br.com.multiinovacoes.gcon.report.RetornoRelatorioGeral;
import br.com.multiinovacoes.gcon.repository.AtendimentoRepository;
import br.com.multiinovacoes.gcon.service.AtendimentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Relatorio", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = RELATORIO )
public class RelatorioController {
	
	public static final String RELATORIO = "/relatorio";
	public static final String ATENDIMENTO_AREA = "/atendimento-area";
	public static final String ATENDIMENTO_PERIODO = "/atendimento-periodo";
	public static final String ATENDIMENTO_GERAL = "/atendimento-geral";
	public static final String ATENDIMENTO_NATUREZA = "/atendimento-natureza";
	public static final String ATENDIMENTO_PRIORIZACAO = "/atendimento-priorizacao";
	public static final String ATENDIMENTO_ORIGEM = "/atendimento-origem";
	public static final String ATENDIMENTO_AREA_ASSUNTO = "/atendimento-area-assunto";
	public static final String ATENDIMENTO_SECRETARIA = "/atendimento-secretaria";
	public static final String ATENDIMENTO_SECRETARIA_NATUREZA = "/atendimento-secretaria-natureza";
	public static final String ATENDIMENTO_TIPO_MANIFESTANTE = "/atendimento-tipo-manifestante";
	public static final String ATENDIMENTO_MEDIA_RESPOSTA = "/atendimento-media-resposta";
	public static final String EFICIENCIA_OUVIDORIA = "/eficiencia-ouvidoria";
	public static final String COMPARATIVO_PERIODO = "/comparativo-periodo";
	public static final String ATENDIMENTO_USUARIO = "/atendimento-usuario";
	public static final String ATENDIMENTO_ASSUNTO = "/atendimento-assunto";
	public static final String PRODUTIVIDADE_CALLCENTER = "/produtividade-callcenter";
	public static final String SEPLAG = "/seplag";
	public static final String ATENDIMENTO_AREA_REL = "/atendimento-area/impressao";
	public static final String ATENDIMENTO_PERIODO_REL = "/atendimento-periodo/impressao";
	public static final String ATENDIMENTO_USUARIO_REL = "/atendimento-usuario/impressao";
	public static final String ATENDIMENTO = "/atendimento";
	

	@Autowired
	AtendimentoRepository atendimentoRepository;
	
	@Autowired
	AtendimentoService atendimentoService;
	

	@ApiOperation(value = "Relatório de atendimentos por periodo", nickname = ATENDIMENTO_PERIODO)
	@GetMapping(path = ATENDIMENTO_PERIODO, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RelatorioPeriodo>> getConsultaPeriodo(Long orgao, Date dataInicial, Date dataFinal) {
		List<RelatorioPeriodo> resumo = atendimentoService.relatorioAtendimentoPeriodo(orgao, dataInicial, dataFinal);
		return ResponseEntity.ok(resumo); 
	}
	
	@ApiOperation(value = "Relatório de atendimentos geral", nickname = ATENDIMENTO_GERAL)
	@PostMapping(path = ATENDIMENTO_GERAL, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<RetornoRelatorioGeral> getConsultaGeral(@Valid @RequestBody RelatorioGeralRequest request) {
		RetornoRelatorioGeral resumo = atendimentoRepository.getListaGeral(request);
		return ResponseEntity.ok(resumo); 
	}

	@ApiOperation(value = "Relatório de atendimentos por natureza", nickname = ATENDIMENTO_NATUREZA)
	@GetMapping(path = ATENDIMENTO_NATUREZA, produces = APPLICATION_JSON_VALUE)
	public List<DadosGrafico>  getRelatorioNatureza(Long orgao, Date dataInicial, Date dataFinal) {
		return atendimentoRepository.getRelatorioNatureza(orgao, dataInicial, dataFinal);
	}

	@ApiOperation(value = "Relatório de atendimentos por priorização", nickname = ATENDIMENTO_PRIORIZACAO)
	@GetMapping(path = ATENDIMENTO_PRIORIZACAO, produces = APPLICATION_JSON_VALUE)
	public List<DadosGrafico>  getRelatorioPriorizacao(Long orgao, Date dataInicial, Date dataFinal) {
		return atendimentoRepository.getRelatorioPriorizacao(orgao, dataInicial, dataFinal);
	}

	@ApiOperation(value = "Relatório de atendimentos por origem", nickname = ATENDIMENTO_ORIGEM)
	@GetMapping(path = ATENDIMENTO_ORIGEM, produces = APPLICATION_JSON_VALUE)
	public List<DadosGrafico>  getRelatorioOrigem(Long orgao, Date dataInicial, Date dataFinal) {
		return atendimentoRepository.getRelatorioOrigem(orgao, dataInicial, dataFinal);
	}

	@ApiOperation(value = "Relatório de atendimentos por área assunto", nickname = ATENDIMENTO_AREA_ASSUNTO)
	@GetMapping(path = ATENDIMENTO_AREA_ASSUNTO, produces = APPLICATION_JSON_VALUE)
	public List<DadosGrafico>  getRelatorioAreaAssunto(Long orgao, Date dataInicial, Date dataFinal) {
		return atendimentoRepository.getRelatorioAreaAssunto(orgao, dataInicial, dataFinal);
	}

	@ApiOperation(value = "Relatório de atendimentos por secretaria", nickname = ATENDIMENTO_SECRETARIA)
	@GetMapping(path = ATENDIMENTO_SECRETARIA, produces = APPLICATION_JSON_VALUE)
	public DadosRelatorio  getRelatorioSecretaria(Long orgao, Date dataInicial, Date dataFinal, Long area) {
		return atendimentoRepository.getRelatorioSecretaria(orgao, dataInicial, dataFinal, area);
	}

	@ApiOperation(value = "Relatório de atendimentos por usuario", nickname = ATENDIMENTO_USUARIO)
	@GetMapping(path = ATENDIMENTO_USUARIO, produces = APPLICATION_JSON_VALUE)
	public DadosRelatorioUsuario  getRelatorioUsuario(Long orgao, Date dataInicial, Date dataFinal, Long usuario) {
		return atendimentoRepository.getRelatorioUsuario(orgao, dataInicial, dataFinal, usuario);
	}

	@ApiOperation(value = "Relatório de atendimentos por tipo manifestante", nickname = ATENDIMENTO_TIPO_MANIFESTANTE)
	@GetMapping(path = ATENDIMENTO_TIPO_MANIFESTANTE, produces = APPLICATION_JSON_VALUE)
	public List<DadosGrafico>  getRelatorioTipoManifestante(Long orgao, Date dataInicial, Date dataFinal) {
		return atendimentoRepository.getRelatorioTipoManifestante(orgao, dataInicial, dataFinal);
	}

	@ApiOperation(value = "Relatório de atendimentos por assunto", nickname = ATENDIMENTO_ASSUNTO)
	@GetMapping(path = ATENDIMENTO_ASSUNTO, produces = APPLICATION_JSON_VALUE)
	public List<DadosGrafico>  getRelatorioAssunto(Long orgao, Date dataInicial, Date dataFinal) {
		return atendimentoRepository.getRelatorioAssunto(orgao, dataInicial, dataFinal);
	}

	@ApiOperation(value = "Relatório de atendimentos por média de resposta", nickname = ATENDIMENTO_MEDIA_RESPOSTA)
	@GetMapping(path = ATENDIMENTO_MEDIA_RESPOSTA, produces = APPLICATION_JSON_VALUE)
	public List<DadosGrafico>  getRelatorioMediaResposta(Long orgao, Date dataInicial, Date dataFinal) {
		return atendimentoRepository.getRelatorioMediaResposta(orgao, dataInicial, dataFinal);
	}

	@ApiOperation(value = "Relatório de produtividade call center", nickname = PRODUTIVIDADE_CALLCENTER)
	@GetMapping(path = PRODUTIVIDADE_CALLCENTER, produces = APPLICATION_JSON_VALUE)
	public DadosRelatorio  getRelatorioProdutividadeCallCenter(Long orgao, Date dataInicial, Date dataFinal, String area) {
		return atendimentoRepository.getRelatorioProdutividadeCallCenter(orgao, dataInicial, dataFinal, area);
	}

	@ApiOperation(value = "Relatório de eficiência da ouvidoria", nickname = EFICIENCIA_OUVIDORIA)
	@GetMapping(path = EFICIENCIA_OUVIDORIA, produces = APPLICATION_JSON_VALUE)
	public List<DadosGrafico>  getRelatorioEficienciaOuvidoria(Long orgao, Date dataInicial, Date dataFinal) {
		return atendimentoRepository.getRelatorioEficienciaOuvidoria(orgao, dataInicial, dataFinal);
	}

	@ApiOperation(value = "Relatório de comparativo de períodos", nickname = COMPARATIVO_PERIODO)
	@GetMapping(path = COMPARATIVO_PERIODO, produces = APPLICATION_JSON_VALUE)
	public DadosComparativoPeriodo  getRelatorioComparativoPeriodo(Long orgao, Date dataInicial, Date dataFinal, Date dataInicialAnterior, Date dataFinalAnterior) {
		return atendimentoRepository.getRelatorioComparativo(orgao, dataInicial, dataFinal, dataInicialAnterior, dataFinalAnterior);
	}

	@ApiOperation(value = "Relatório de atendimentos por natureza", nickname = ATENDIMENTO_NATUREZA)
	@GetMapping(path = "graficoNatureza", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<DadosGrafico> getStringGrafico(Long orgao, Date dataInicial, Date dataFinal) {
		List<DadosGrafico> lista = atendimentoRepository.getRelatorioNatureza(orgao, dataInicial, dataFinal);
		FusionCharts fusionCharts = new FusionCharts();
		String grafico = fusionCharts.createXMLDataList(lista, "");
		DadosGrafico dados = new DadosGrafico();
		dados.setLabel(grafico);
		return ResponseEntity.ok(dados); 
	}

	@ApiOperation(value = "Relatório de atendimentos por area", nickname = ATENDIMENTO_AREA)
	@GetMapping(path = ATENDIMENTO_AREA, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DetalheAtendimentoArea>> getConsulta(Long orgao, Date dataInicial, Date dataFinal, String area, String status) {
		List<DetalheAtendimentoArea> resumo = atendimentoService.relatorioAtendimentoAreaPDF(orgao, dataInicial, dataFinal, area, status);
		return ResponseEntity.ok(resumo); 
	}
	
	@ApiOperation(value = "Impressão do relatório de atendimentos por area", nickname = ATENDIMENTO_AREA_REL)
	@GetMapping(path = ATENDIMENTO_AREA_REL, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<byte[]> relatorioPorPessoa(
			Long orgao, Date dataInicial, Date dataFinal, String area, String status) {
		List<DetalheAtendimentoArea> resumo = atendimentoService.relatorioAtendimentoAreaPDF(orgao, dataInicial, dataFinal, area, status);
		byte[] relatorio = atendimentoService.impressaoRelatorio(resumo, dataInicial, dataFinal, "/rel/atendimento-area.jasper", orgao);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}	

	@ApiOperation(value = "Impressão do relatório de atendimentos por período", nickname = ATENDIMENTO_PERIODO_REL)
	@GetMapping(path = ATENDIMENTO_PERIODO_REL, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<byte[]> relatorioPeridoPDF(
			Long orgao, Date dataInicial, Date dataFinal, String area, String status) {
		List<RelatorioPeriodo> resumo = atendimentoService.relatorioAtendimentoPeriodo(orgao, dataInicial, dataFinal);
		byte[] relatorio = atendimentoService.impressaoRelatorio(resumo, dataInicial, dataFinal, "/rel/atendimento-periodo.jasper", orgao);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}	

	@ApiOperation(value = "Impressão do relatório de atendimentos por período", nickname = ATENDIMENTO_USUARIO_REL)
	@GetMapping(path = ATENDIMENTO_USUARIO_REL, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<byte[]> relatorioUsuarioPDF(
			Long orgao, Date dataInicial, Date dataFinal, Long usuario) {
		DadosRelatorioUsuario resumo = atendimentoRepository.getRelatorioUsuario(orgao, dataInicial, dataFinal, usuario);
		byte[] relatorio = atendimentoService.impressaoRelatorio(resumo.getLista(), dataInicial, dataFinal, "/rel/atendimento-usuario.jasper", orgao);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}	

	@ApiOperation(value = "Impressão do relatório de atendimentos por area", nickname = ATENDIMENTO)
	@GetMapping(path = ATENDIMENTO, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<byte[]> relatorioAtendimento(Long idAtendimento){
		List<DetalheAtendimento> resumo = atendimentoService.relatorioAtendimentoAreaPDF(idAtendimento);
		byte[] relatorio = atendimentoService.impressaoRelatorio(resumo);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}
	
	@ApiOperation(value = "Relatório de atendimentos SEPLAG", nickname = SEPLAG)
	@GetMapping(path = SEPLAG, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DetalheAtendimentoArea>> getConsultaSeplag(Long orgao, Date dataInicial, Date dataFinal, String area) {
		List<DetalheAtendimentoArea> resumo = atendimentoService.relatorioAtendimentoAreaPDF(orgao, dataInicial, dataFinal, area);
		return ResponseEntity.ok(resumo); 
	}
	

}