package br.com.multiinovacoes.gcon.controller;

import static br.com.multiinovacoes.gcon.controller.LoginController.LOGIN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.multiinovacoes.gcon.model.dto.AlterarSenhaDto;
import br.com.multiinovacoes.gcon.model.mapper.UsuarioMapper;
import br.com.multiinovacoes.gcon.model.response.ListaUsuarioResponse;
import br.com.multiinovacoes.gcon.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@Api(value = "Login", produces = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(path = LOGIN	)
public class LoginController {
	
	public static final String LOGIN = "/login";
	public static final String ESQUECEU_SENHA = "/esqueceu-senha";
	public static final String ALTERAR_SENHA = "/alterar-senha";
	public static final String LISTA_USUARIO = "/listar-usuario";
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	
	@ApiOperation(value = "Alterar Senha", nickname = ALTERAR_SENHA)
	@PostMapping(path = ALTERAR_SENHA, produces = APPLICATION_JSON_VALUE)
	public Boolean alterarSenha(@RequestBody AlterarSenhaDto alterarSenha) {
		 boolean retorno = usuarioService.alterarSenha(alterarSenha);
		 return retorno; 
	}

	@ApiOperation(value = "Lista de Usuários", nickname = LISTA_USUARIO)
	@GetMapping(path = LISTA_USUARIO, produces = APPLICATION_JSON_VALUE)
	public ListaUsuarioResponse listaUsuarios(Long orgao) {
		 return  new ListaUsuarioResponse(usuarioMapper.fromResponseList(usuarioService.listaUsuarios(orgao)));
	}


}
