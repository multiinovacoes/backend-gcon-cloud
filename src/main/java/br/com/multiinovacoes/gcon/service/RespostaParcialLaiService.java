package br.com.multiinovacoes.gcon.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.mail.Mailer;
import br.com.multiinovacoes.gcon.model.RespostaParcialLai;
import br.com.multiinovacoes.gcon.model.dto.RespostaParcialLaiDto;
import br.com.multiinovacoes.gcon.model.mapper.RespostaParcialLaiMapper;
import br.com.multiinovacoes.gcon.model.request.RespostaParcialLaiRequest;
import br.com.multiinovacoes.gcon.repository.AtendimentoLaiRepository;
import br.com.multiinovacoes.gcon.repository.RespostaParcialLaiRepository;
import br.com.multiinovacoes.gcon.security.GconSecurity;

@Service
@Transactional
public class RespostaParcialLaiService {
	
	
	@Autowired
	RespostaParcialLaiMapper respostaParcialMapper;
	
	@Autowired
	RespostaParcialLaiRepository respostaParcialRepository;
	
	@Autowired
	private Mailer mailer;
	
	@Autowired
	AtendimentoLaiRepository atendimentoRepository;
	
	@Autowired
	private GconSecurity gconSecurity;


	
	public List<RespostaParcialLaiDto> getRespostaParcials(Long codigoAtendimento) {
		return respostaParcialMapper.fromResponseList(respostaParcialRepository.consultaAtendimento(codigoAtendimento));
	}
	
	public void salvarRespostaParcial(RespostaParcialLaiRequest respostaParciaRequest) {

		
		RespostaParcialLai respostaParcial = respostaParcialMapper.toRespostaParcial(respostaParcialMapper.fromRespostaParcial(respostaParciaRequest));
		
		respostaParcial.setEmailEnviado(respostaParcial.getEmailEnviado() == null ?  "" : respostaParcial.getEmailEnviado());
		respostaParcial.setDataResposta(LocalDate.now());
		respostaParcial.setStatus(0);
		respostaParcial.setUsuario(gconSecurity.getIdUsuario());
		respostaParcialRepository.save(respostaParcial);
		
		String[] anexosEmail = new String[0];
		
		
		Map<String, Object> variaveis = new HashMap<>();
		variaveis.put("texto", respostaParcial.getDescricao());
		String template = "mail/emailrespostaparcial";
		mailer.enviarEmail(Arrays.asList(respostaParcial.getEmailEnviado()), template, variaveis, "Serviço de Atendimento ao Cidadão SESI e SENAI/PE", 
				"Resposta Parcial do Pedido", anexosEmail);
	}
	
	public RespostaParcialLaiDto getRespostaParcial(Long codigoResposta) {
		RespostaParcialLai resposta = respostaParcialRepository.consultaResposta(codigoResposta);
		//resposta.setDescricaoFormaEnvio(FormaEnvioRespostaParcialEnum.pegarDescricao(resposta.getFormaEnvio()).getDescricao());
		return respostaParcialMapper.toDto(resposta);
	}

	
	public void excluir(Long codigoRespostaParcial) {
		RespostaParcialLai resp = respostaParcialRepository.getById(codigoRespostaParcial);
		resp.setStatus(1);
		respostaParcialRepository.save(resp);
	}


}
