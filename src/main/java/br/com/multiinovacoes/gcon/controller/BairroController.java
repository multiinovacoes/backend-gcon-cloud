package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.BairroController.BAIRRO;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import br.com.multiinovacoes.gcon.model.dto.BairroDto;
import br.com.multiinovacoes.gcon.model.mapper.BairroMapper;
import br.com.multiinovacoes.gcon.model.request.BairroRequest;
import br.com.multiinovacoes.gcon.model.response.BairroResponse;
import br.com.multiinovacoes.gcon.repository.BairroRepository;
import br.com.multiinovacoes.gcon.service.BairroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Bairro", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = BAIRRO	)
public class BairroController {
	
	public static final String BAIRRO = "/bairros";
	public static final String LISTAR = "/listar";
	public static final String PARAMETRO = "/{codigo}";

	
	@Autowired
	BairroRepository bairroRepository;
	
	@Autowired
	BairroService bairroService;
	
	@Autowired
	BairroMapper bairroMapper;
	
	@ApiOperation(value = "Obter lista de bairros", nickname = LISTAR)
	@GetMapping(path = LISTAR, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BairroDto>> getListaBairros(Long orgao) {
		return new ResponseEntity<>(bairroService.getListaBairros(orgao), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Obter uma bairro para edição", nickname = PARAMETRO)
	@GetMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public BairroResponse getEditarBairro(@PathVariable Long codigo) {
		return new BairroResponse(bairroService.getBairro(codigo));
	}
	
	@ApiOperation(value = "Enviar uma bairro", nickname = BAIRRO)
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public BairroResponse salvarBairro(@Valid @RequestBody BairroRequest request) {
		BairroDto bairroDto = bairroMapper.fromBairro(request);
		return new BairroResponse(bairroService.salvar(bairroDto));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Excluir uma bairro", nickname = PARAMETRO)
	@DeleteMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public void excluirBairro(@PathVariable Long codigo) {
		bairroRepository.deleteById(codigo);
	}
	
	@ApiOperation(value = "Atualizar uma bairro", nickname = PARAMETRO)
	@PutMapping(path = PARAMETRO, produces = APPLICATION_JSON_VALUE)
	public BairroResponse atualizarBairro(@PathVariable Long codigo, @Valid @RequestBody BairroDto request) {
		return new BairroResponse(bairroService.salvar(request));
	}
	



}
