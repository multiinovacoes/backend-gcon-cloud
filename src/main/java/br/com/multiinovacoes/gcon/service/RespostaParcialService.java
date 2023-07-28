package br.com.multiinovacoes.gcon.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.enums.FormaEnvioRespostaParcialEnum;
import br.com.multiinovacoes.gcon.enums.OrgaoEnum;
import br.com.multiinovacoes.gcon.mail.Mailer;
import br.com.multiinovacoes.gcon.model.Anexo;
import br.com.multiinovacoes.gcon.model.Atendimento;
import br.com.multiinovacoes.gcon.model.LogAtendimento;
import br.com.multiinovacoes.gcon.model.LogoTipo;
import br.com.multiinovacoes.gcon.model.RespostaParcial;
import br.com.multiinovacoes.gcon.model.Usuario;
import br.com.multiinovacoes.gcon.model.dto.RespostaParcialDto;
import br.com.multiinovacoes.gcon.model.mapper.RespostaParcialMapper;
import br.com.multiinovacoes.gcon.model.request.RespostaParcialRequest;
import br.com.multiinovacoes.gcon.repository.AnexoRepository;
import br.com.multiinovacoes.gcon.repository.AtendimentoRepository;
import br.com.multiinovacoes.gcon.repository.LogAtendimentoRepository;
import br.com.multiinovacoes.gcon.repository.LogoTipoRepository;
import br.com.multiinovacoes.gcon.repository.RespostaParcialRepository;
import br.com.multiinovacoes.gcon.repository.UsuarioRepository;
import br.com.multiinovacoes.gcon.security.GconSecurity;

@Service
@Transactional
public class RespostaParcialService {
	
	
	@Autowired
	RespostaParcialMapper respostaParcialMapper;
	
	@Autowired
	RespostaParcialRepository respostaParcialRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	private Mailer mailer;
	
	@Autowired
	AtendimentoRepository atendimentoRepository;
	
	@Autowired
	private GconSecurity gconSecurity;

	@Autowired
	private LogAtendimentoRepository logAtendimentoRepository;
	
	@Autowired
	private AnexoRepository anexoRepository;
	
	@Autowired
	private LogoTipoRepository logotipoRepository;

	
	public List<RespostaParcialDto> getRespostaParcials(Long codigoAtendimento) {
		return respostaParcialMapper.fromResponseList(respostaParcialRepository.consultaAtendimento(codigoAtendimento));
	}
	
	public void salvarRespostaParcial(RespostaParcialRequest respostaParciaRequest) {

		Usuario usuario = usuarioRepository.findById(gconSecurity.getIdUsuario()).orElse(null);
		
		RespostaParcial respostaParcial = respostaParcialMapper.toRespostaParcial(respostaParcialMapper.fromRespostaParcial(respostaParciaRequest));
		
		Atendimento atendimento = atendimentoRepository.getById(respostaParciaRequest.getAtendimento());
		
		LogoTipo logo = logotipoRepository.findByOrgao(atendimento.getOrgao());
		
		respostaParcial.setEmailEnviado(respostaParcial.getEmailEnviado() == null ?  "" : respostaParcial.getEmailEnviado());
		respostaParcial.setDataResposta(LocalDate.now());
		respostaParcial.setStatus(1);
		respostaParcial.setUsuario(usuario);
		respostaParcial.setCancelado(0);
		respostaParcial.setAnoAtendimento(atendimento.getAnoAtendimento());
		respostaParcial.setCodigoAtendimento(atendimento.getNumeroAtendimento());
		respostaParcial.setId(respostaParcialRepository.getMaxId()+1);
		respostaParcialRepository.save(respostaParcial);
		
		LogAtendimento logAtendimento = 
				  new LogAtendimento(
						  logAtendimentoRepository.getMaxId()+1,
						  respostaParcial.getAtendimento(),
						  LocalDateTime.now(),
						  usuario.getId(),
						  "Enviou resposta parcial"
						  );
		logAtendimentoRepository.save(logAtendimento);
		
		
		String[] anexos = null;
		
		if (respostaParciaRequest.getSelectedAnexos() != null) {
			anexos = new String[respostaParciaRequest.getSelectedAnexos().size()];
		    
			for (int i = 0; i < respostaParciaRequest.getSelectedAnexos().size(); i++) {
				Optional<Anexo> anexo = anexoRepository.findById(respostaParciaRequest.getSelectedAnexos().get(i));
				if (anexo.isPresent()) {
					anexos[i] = "C:\\gcon_arquivos\\arquivos\\"+anexo.get().getNomeArquivo();
				}
			}
		}

		
		if (respostaParciaRequest.getFormaEnvio() == 2 && !respostaParcial.getEmailEnviado().equals("")) {
			Map<String, Object> variaveis = new HashMap<>();
			variaveis.put("texto", respostaParcial.getDescricao());
			variaveis.put("logo", "https://portalouvidoria.com.br:8501/assets/image/"+logo.getNome());
			String template = "mail/emailrespostaparcial";
			mailer.enviarEmail(Arrays.asList(respostaParcial.getEmailEnviado()), template, variaveis, OrgaoEnum.pegarDescricao(atendimento.getOrgao()).getDescricao(), "Resposta Parcial", anexos);
		}

		
		
	}
	
	public RespostaParcialDto getRespostaParcial(Long codigoResposta) {
		RespostaParcial resposta = respostaParcialRepository.consultaResposta(codigoResposta);
		resposta.setDescricaoFormaEnvio(FormaEnvioRespostaParcialEnum.pegarDescricao(resposta.getFormaEnvio()).getDescricao());
		return respostaParcialMapper.toDto(resposta);
	}

	
	public void excluir(Long codigoRespostaParcial) {
		RespostaParcial resp = respostaParcialRepository.getById(codigoRespostaParcial);
		resp.setCancelado(1);
		respostaParcialRepository.save(resp);
	}


}
