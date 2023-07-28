package br.com.multiinovacoes.gcon.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.multiinovacoes.gcon.mail.Mailer;
import br.com.multiinovacoes.gcon.model.AtendimentoLai;
import br.com.multiinovacoes.gcon.model.DespachoLai;
import br.com.multiinovacoes.gcon.model.EncaminhamentoLai;
import br.com.multiinovacoes.gcon.model.SetorLai;
import br.com.multiinovacoes.gcon.model.dto.DespachoLaiDto;
import br.com.multiinovacoes.gcon.model.mapper.DespachoLaiMapper;
import br.com.multiinovacoes.gcon.model.request.DespachoLaiRequest;
import br.com.multiinovacoes.gcon.repository.AtendimentoLaiRepository;
import br.com.multiinovacoes.gcon.repository.DespachoLaiRepository;
import br.com.multiinovacoes.gcon.repository.EncaminhamentoLaiRepository;
import br.com.multiinovacoes.gcon.repository.SetorLaiRepository;
import br.com.multiinovacoes.gcon.repository.SetorRepository;
import br.com.multiinovacoes.gcon.repository.UsuarioRepository;
import br.com.multiinovacoes.gcon.security.GconSecurity;

@Service
@Transactional
public class DespachoLaiService {
	
	
	@Autowired
	DespachoLaiMapper despachoMapper;
	
	@Autowired
	DespachoLaiRepository despachoRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	AtendimentoLaiRepository atendimentoRepository;
	
	@Autowired
	EncaminhamentoLaiRepository encaminhamentoRepository;
	
	@Autowired
	private Mailer mailer;
	
	@Autowired
	private GconSecurity gconSecurity;
	
	@Autowired
	private SetorLaiRepository setorLaiRepository;
	
	@Autowired
	private SetorRepository setorRepository;
	
	public List<DespachoLaiDto> getDespachos(Long codigoAtendimento) {
		AtendimentoLai atendimento = atendimentoRepository.getById(codigoAtendimento);
		List<DespachoLaiDto> despachoDtoList = despachoMapper.fromResponseList(despachoRepository.consultaAtendimentoLai(atendimento));
		List<DespachoLaiDto> listaRetorno = new ArrayList<>();
		if (despachoDtoList != null) {
			for (DespachoLaiDto despachoDto : despachoDtoList) {
				despachoDto.setDescricaoSetorOrigem(setorRepository.getById(despachoDto.getSetorOrigem()).getDescricao());
				despachoDto.setDescricaoSetorDestino(setorLaiRepository.getById(despachoDto.getSetorDestino()).getDescricao());
				listaRetorno.add(despachoDto);
			}
		}
		return listaRetorno;
	}
	
	
	public void salvarDespacho(DespachoLaiRequest request) {
		AtendimentoLai atendimento = atendimentoRepository.getById(request.getAtendimento());
		DespachoLai despacho =  despachoMapper.toDespachoLai(despachoMapper.fromDespachoLai(request));
		despachoRepository.save(despacho);
		
		Map<String, Object> variaveis = new HashMap<>();
		variaveis.put("atendimento", atendimento);
		variaveis.put("despacho", request.getDescricao());
		
		String template = "mail/emailencaminhamento";
		
		mailer.enviarEmail(Arrays.asList(request.getEmailEnviado()), template, variaveis, "", "Encaminhamento de Despacho Nº " + atendimento.getNumeroProtocolo(), null);
		
		
	}
	
	public DespachoLaiDto getDespacho(Long codigoDespacho) {
		DespachoLaiDto despachoDto = despachoMapper.toDto(despachoRepository.consultaDespacho(codigoDespacho));
		despachoDto.setDescricaoSetorDestino(setorRepository.getById(despachoDto.getSetorDestino()).getDescricao());
		return despachoDto;
	}

	public DespachoLaiDto getInsertDespacho(Long codigoAtendimento) {
		Optional<EncaminhamentoLai> encaminhamentoAberto = encaminhamentoRepository.getVerificarEncaminhamentoAberto(codigoAtendimento);
		if (encaminhamentoAberto.isPresent()) {
			SetorLai setorLai = setorLaiRepository.getById(encaminhamentoAberto.get().getSetorDestino());
			DespachoLaiDto despachoLai = new DespachoLaiDto();
			despachoLai.setAtendimento(codigoAtendimento);
			despachoLai.setCodigoEncaminhamento(encaminhamentoAberto.get().getId());
			despachoLai.setDataDespacho(LocalDateTime.now());
			despachoLai.setEmailEnviado(setorLai.getEmailSetor());
			despachoLai.setSetorOrigem(encaminhamentoAberto.get().getSetorOrigem());
			despachoLai.setSetorDestino(encaminhamentoAberto.get().getSetorDestino());
			despachoLai.setDescricaoSetorDestino(setorLai.getDescricao());
			despachoLai.setUsuario(gconSecurity.getIdUsuario());
			despachoLai.setStatus(0);
			return despachoLai;
		}
		return new DespachoLaiDto();
	}
	
	public void excluir(Long codigoDespacho) {
		despachoRepository.deleteById(codigoDespacho);
	}


}
