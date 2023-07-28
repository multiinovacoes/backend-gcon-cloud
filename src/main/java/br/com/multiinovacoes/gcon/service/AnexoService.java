package br.com.multiinovacoes.gcon.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.Anexo;
import br.com.multiinovacoes.gcon.model.Atendimento;
import br.com.multiinovacoes.gcon.model.dto.ListaAnexoDto;
import br.com.multiinovacoes.gcon.model.request.ListaAnexoRequest;
import br.com.multiinovacoes.gcon.repository.AnexoRepository;
import br.com.multiinovacoes.gcon.repository.AtendimentoRepository;
import br.com.multiinovacoes.gcon.util.UploadGcon;

@Service
@Transactional
public class AnexoService {
	
	@Autowired
	AnexoRepository anexoRepository;
	
	@Autowired
	AtendimentoRepository atendimentoRepository;

	public List<Anexo> getListaAnexos(Long atendimento){
		return Optional.ofNullable(anexoRepository.findByAtendimento(atendimento)).orElse(Collections.emptyList());
	}
	
	@Transactional
	public void salvarArquivo(ListaAnexoRequest lista){
		Optional<Atendimento> atendimento = atendimentoRepository.findById(lista.getCodigoAtendimento());
		if (atendimento.isPresent()) {
			try {
				for (ListaAnexoDto listaAnexoDto : lista.getListaAnexoDto()) {
			    	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			    	String nomeArq = timeStamp + "_" + listaAnexoDto.getNomeArquivo();
					UploadGcon.upload(listaAnexoDto.getStringBase64(), nomeArq, atendimento.get().getOrgao());
					Anexo anexo = new Anexo();
					anexo.setAtendimento(atendimento.get().getId());
					anexo.setDataAnexo(LocalDate.now());
					anexo.setNomeArquivo(nomeArq);
					anexo.setNumeroAtendimento(atendimento.get().getNumeroAtendimento());
					anexo.setOrgao(atendimento.get().getOrgao());
					anexo.setOrigem(0);
					anexo.setVisivel(1);
					anexo.setId(anexoRepository.getMaxSequencialId()+1);
					anexoRepository.save(anexo);
				}
			}catch (IOException e) {
				e.getCause();
			}
		}
	}
	
	public void excluir(Long codigoAnexo) {
		anexoRepository.deleteById(codigoAnexo);
	}


}
