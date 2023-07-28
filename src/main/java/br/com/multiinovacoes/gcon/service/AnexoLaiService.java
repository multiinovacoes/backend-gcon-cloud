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

import br.com.multiinovacoes.gcon.model.AnexoLai;
import br.com.multiinovacoes.gcon.model.AtendimentoLai;
import br.com.multiinovacoes.gcon.model.dto.ListaAnexoLaiDto;
import br.com.multiinovacoes.gcon.model.request.ListaAnexoLaiRequest;
import br.com.multiinovacoes.gcon.repository.AnexoLaiRepository;
import br.com.multiinovacoes.gcon.repository.AtendimentoLaiRepository;
import br.com.multiinovacoes.gcon.util.UploadGcon;

@Service
@Transactional
public class AnexoLaiService {
	
	@Autowired
	AnexoLaiRepository anexoRepository;
	
	@Autowired
	AtendimentoLaiRepository atendimentoRepository;

	public List<AnexoLai> getListaAnexos(Long atendimento){
		return Optional.ofNullable(anexoRepository.findByAtendimento(atendimento)).orElse(Collections.emptyList());
	}
	
	@Transactional
	public void salvarArquivo(ListaAnexoLaiRequest lista){
		Optional<AtendimentoLai> atendimento = atendimentoRepository.findById(lista.getAtendimento());
		if (atendimento.isPresent()) {
			try {
				for (ListaAnexoLaiDto listaAnexoDto : lista.getListaAnexoDto()) {
			    	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			    	String nomeArq = timeStamp + "_" + listaAnexoDto.getNomeArquivo();
					UploadGcon.upload(listaAnexoDto.getStringBase64(), nomeArq, 7l);
					AnexoLai anexo = new AnexoLai();
					anexo.setAtendimento(atendimento.get().getId());
					anexo.setDataAnexo(LocalDate.now());
					anexo.setNomeArquivo(nomeArq);
					anexo.setResponsavel(1);
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
