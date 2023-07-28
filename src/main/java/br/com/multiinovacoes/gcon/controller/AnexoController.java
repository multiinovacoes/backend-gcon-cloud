package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.AnexoController.ANEXO;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import br.com.multiinovacoes.gcon.model.mapper.AnexoMapper;
import br.com.multiinovacoes.gcon.model.request.ListaAnexoRequest;
import br.com.multiinovacoes.gcon.model.response.ListaAnexoResponse;
import br.com.multiinovacoes.gcon.service.AnexoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Anexo", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = ANEXO	)
public class AnexoController {
	
	public static final String ANEXO = "/anexos";
	public static final String LISTAR = "/listar";
	public static final String PARAMETRO = "/{codigo}";
	
	private static final String EXTERNAL_FILE_PATH = "C:\\jboss-4.2.1.GA_UBEC\\server\\default\\deploy\\multiwork.war\\arquivos\\5\\";
	
	
	@Autowired
	AnexoService anexoService;
	
	@Autowired
	AnexoMapper anexoMapper;
	
	@ApiOperation(value = "Obter lista de anexos", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ListaAnexoResponse getListaAnexos(Long codigoAtendimento) {
		return new ListaAnexoResponse(anexoMapper.fromResponseList(anexoService.getListaAnexos(codigoAtendimento)));
	}
	
	@ApiOperation(value = "Salva um arquivo")
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public void salvarAnexo(@Valid @RequestBody ListaAnexoRequest request) {
		anexoService.salvarArquivo(request);
	}
	
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma despacho", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirAnexo(@PathVariable Long codigo) {
		anexoService.excluir(codigo);
	}
	
	
	@RequestMapping("/file/{fileName:.+}")
	public ResponseEntity<byte[]> downloadAnexo(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileName") String fileName) throws IOException {
		File file = new File(EXTERNAL_FILE_PATH +  fileName);
		if (file.exists()) {
			String mimeType = Files.probeContentType(file.toPath());
			System.out.println(mimeType);
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
		   byte[] arquivo = Files.readAllBytes(file.toPath());
	       return ResponseEntity.ok()
	   			.header(HttpHeaders.CONTENT_TYPE, mimeType)
	   			.body(arquivo);
		}
		return null;
	}    
	
	


}
