package br.com.multiinovacoes.gcon.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.RedefinicaoSenha;
import br.com.multiinovacoes.gcon.model.Usuario;
import br.com.multiinovacoes.gcon.model.dto.AlterarSenhaDto;
import br.com.multiinovacoes.gcon.repository.RedefinicaoSenhaRepository;
import br.com.multiinovacoes.gcon.repository.UsuarioRepository;
import br.com.multiinovacoes.gcon.util.CriptografiaSenha;


@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RedefinicaoSenhaRepository redefinicaoSenhaRepository;
	
	
	public List<Usuario> listaUsuarios(Long orgao){
		return usuarioRepository.getListaUsuario(orgao);
	}

	
	public Optional<Usuario> getCpf(String cpf) {
		return usuarioRepository.findByCpf(cpf);
	}
	
	@Transactional
	public boolean alterarSenha(AlterarSenhaDto alterarSenha) {
		 Optional<Usuario> usuario = usuarioRepository.findByIdAndSenhaInformada(alterarSenha.getIdUsuario(), alterarSenha.getSenhaAntiga());
		 if (usuario.isPresent()) {
			 
			 usuario.get().setSenhaInformada(alterarSenha.getSenhaNova());
			 usuario.get().setSenha(CriptografiaSenha.getSenhaCodificada(alterarSenha.getSenhaNova()));
			 usuarioRepository.save(usuario.get());
			 
			 RedefinicaoSenha redefinicaoSenha = new RedefinicaoSenha();
			 redefinicaoSenha.setCodigoUsuario(usuario.get().getId());
			 redefinicaoSenha.setDataSolicitacao(LocalDateTime.now());
			 redefinicaoSenha.setParametro("");
			 redefinicaoSenha.setSenhaRedefinida(1);
			 redefinicaoSenha.setDataExpira(LocalDateTime.now());
			 redefinicaoSenhaRepository.save(redefinicaoSenha);
             return true;				
		 }
		 return false;
	}


}
