package br.com.multiinovacoes.gcon.mail;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Component
public class Mailer {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine thymeleaf;
	
	public String EMAIL_REMETENTE = "ouvidoriagcon@multiinovacoes.com.br"; 
	
	@Async
	public void enviarEmail(List<String> destinatarios, String template, Map<String, Object> variaveis, String siglaOrgao, String subject, String[] anexos) {
		try {
			Context context = new Context(new Locale("pt", "BR"));
			variaveis.entrySet().forEach(e -> context.setVariable(e.getKey(), e.getValue()));
			String mensagem = thymeleaf.process(template, context);
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setFrom(EMAIL_REMETENTE, siglaOrgao);
			helper.setTo(destinatarios.toArray(new String[destinatarios.size()]));
			helper.setSubject(subject);
			helper.setText(mensagem, true);
			if (anexos != null) {
	            for (int i = 0; i < anexos.length; i++) {
	                File attach = new File(anexos[i]);
	                helper.addAttachment("Attachment: " + attach.getName(), attach);
	            }
			}
			mailSender.send(mimeMessage);
		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new RuntimeException("Problemas com o envio de e-mail!", e); 
		}
	}
}