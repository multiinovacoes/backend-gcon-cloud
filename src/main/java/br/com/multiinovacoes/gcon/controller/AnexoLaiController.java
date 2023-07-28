package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.AnexoLaiController.ANEXO_LAI;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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

import br.com.multiinovacoes.gcon.model.mapper.AnexoLaiMapper;
import br.com.multiinovacoes.gcon.model.request.ListaAnexoLaiRequest;
import br.com.multiinovacoes.gcon.model.response.ListaAnexoLaiResponse;
import br.com.multiinovacoes.gcon.service.AnexoLaiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Anexo", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = ANEXO_LAI	)
public class AnexoLaiController {
	
	public static final String ANEXO_LAI = "/anexos-lai";
	public static final String LISTAR = "/listar";
	public static final String PARAMETRO = "/{codigo}";
	public static final String DOWNLOAD = "/download";
	
    private static final String EXTERNAL_FILE_PATH = "C:\\jboss-4.2.1.GA_CLOUD\\server\\default\\deploy\\multiwork.war\\arquivo\\7\\lai\\";
	
	@Autowired
	AnexoLaiService anexoService;
	
	@Autowired
	AnexoLaiMapper anexoMapper;
	
	@ApiOperation(value = "Obter lista de anexos", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ListaAnexoLaiResponse getListaAnexos(Long atendimento) {
		return new ListaAnexoLaiResponse(anexoMapper.fromResponseList(anexoService.getListaAnexos(atendimento)));
	}
	
	@ApiOperation(value = "Salva um arquivo")
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public void salvarAnexo(@Valid @RequestBody ListaAnexoLaiRequest request) {
		anexoService.salvarArquivo(request);
	}
	
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma despacho", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirAnexo(@PathVariable Long codigo) {
		anexoService.excluir(codigo);
	}
	
	@GetMapping(path = DOWNLOAD, produces = APPLICATION_JSON_VALUE)
    public HttpEntity<byte[]> download() throws IOException {
        byte[] arquivo = Files.readAllBytes( Paths.get("/home/wolmir/minha-imagem.jpg") );
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment;filename=\"minha-imagem.jpg\"");
        HttpEntity<byte[]> entity = new HttpEntity<byte[]>( arquivo, httpHeaders);
        return entity;
    }

	@RequestMapping("/file/{fileName:.+}")
	public ResponseEntity<byte[]> downloadAnexo(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileName") String fileName) throws IOException {
		File file = new File(EXTERNAL_FILE_PATH + fileName);
		if (file.exists()) {
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
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
