package br.com.multiinovacoes.gcon.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexHtml {

	private RegexHtml() {
	}

	public static String getMather(String descricao){
		  Pattern p = Pattern.compile("<.*?>");
		  Matcher m = p.matcher(descricao);
	        String desc = m.replaceAll("");
	        int valor = (desc.length()/7);
	        return m.replaceAll("").substring(0,valor);
	  }

	  public static String getMatherEncaminhamentoResposta(String descricao){
		  Pattern p = Pattern.compile("<.*?>");
		  Matcher m = p.matcher(descricao);
	        //String desc = m.replaceAll("");
	        //int valor = (desc.length()/7);
	        return m.replaceAll("");//.substring(0,valor);
	  }

}
