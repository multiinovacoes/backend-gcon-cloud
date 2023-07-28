package br.com.multiinovacoes.gcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.multiinovacoes.gcon.model.AnexoLai;

public interface AnexoLaiRepository extends JpaRepository<AnexoLai, Long> {
	
	public List<AnexoLai> findByAtendimento(Long atendimento);


}
