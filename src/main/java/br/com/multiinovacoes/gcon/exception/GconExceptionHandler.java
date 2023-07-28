//package br.com.multiinovacoes.gcon.exception;
//
//import java.time.LocalDateTime;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//@ControllerAdvice
//public class GconExceptionHandler extends ResponseEntityExceptionHandler {
//	
//	@ExceptionHandler(EntidadeNaoEncontradaException.class)
//	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(
//			EntidadeNaoEncontradaException ex, WebRequest request) {
//		
//		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), 
//				HttpStatus.NOT_FOUND, request);
//	}
//	
//	
//	@ExceptionHandler(NegocioException.class)
//	public ResponseEntity<?> tratarNegocioException(NegocioException ex, WebRequest request) {
//		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), 
//				HttpStatus.BAD_REQUEST, request);
//	}
//	
//	@Override
//	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
//			HttpStatus status, WebRequest request) {
//		MensagemResposta mensagem = new MensagemResposta();
//		if (body == null) {
//			mensagem.setData(LocalDateTime.now());
//			mensagem.setMensagem(status.getReasonPhrase());
//			body = mensagem;
//		} else if (body instanceof String) {
//			mensagem.setMensagem((String)body);
//			body = mensagem;
//		}
//		return super.handleExceptionInternal(ex, body, headers, status, request);
//	}
//
//
//}
