package br.com.multiinovacoes.gcon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.multiinovacoes.gcon.model.RedefinicaoSenha;


@Repository
public interface RedefinicaoSenhaRepository extends JpaRepository<RedefinicaoSenha, Long> {
	
	
	public Optional<RedefinicaoSenha> findByParametro(String parametro);
	


}
