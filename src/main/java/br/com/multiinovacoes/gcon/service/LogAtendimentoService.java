package br.com.multiinovacoes.gcon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.model.LogAtendimento;
import br.com.multiinovacoes.gcon.model.dto.LogAtendimentoDto;
import br.com.multiinovacoes.gcon.repository.LogAtendimentoRepository;
import br.com.multiinovacoes.gcon.repository.UsuarioRepository;

@Service
@Transactional
public class LogAtendimentoService {
	
	
	
	@Autowired
	private LogAtendimentoRepository logAtendimentoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<LogAtendimentoDto> listaLogsAtendimento(Long codigoAtendimento){
		List<LogAtendimento> listaLogs = logAtendimentoRepository.findByAtendimento(codigoAtendimento);
		List<LogAtendimentoDto> listaLogsDto = new ArrayList<>();
		for (LogAtendimento logAtendimento : listaLogs) {
			LogAtendimentoDto log = new LogAtendimentoDto();
			log.setAcao(logAtendimento.getAcao());
			log.setData(logAtendimento.getData());
			log.setHora(logAtendimento.getData());
			log.setDescricaoUsuario(usuarioRepository.getById(logAtendimento.getIdUsuario()).getNome());
			listaLogsDto.add(log);
		}
		return listaLogsDto;
	}


}
