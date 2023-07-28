package br.com.multiinovacoes.gcon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findByCpf(String cpf);
	
	public Optional<Usuario> findByIdAndSenhaInformada(Long id, String senhaInformada);
	
	
	@Query("SELECT usuario from Usuario usuario where usuario.orgao = ?1 order by usuario.nome")
	public List<Usuario> getListaUsuario(Long orgao);

}
