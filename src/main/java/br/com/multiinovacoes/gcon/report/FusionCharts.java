package br.com.multiinovacoes.gcon.report;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class FusionCharts {
	
	public String encodeDataURL(String strDataURL, String addNoCacheStr,
	    HttpServletResponse response) {
		String encodedURL = strDataURL;
		//Add the no-cache string if required
		if (addNoCacheStr.equals("true")) {
		    /*We add ?FCCurrTime=xxyyzz
		    If the dataURL already contains a ?, we add &FCCurrTime=xxyyzz
		    We send the date separated with '_', instead of the usual ':' as FusionCharts cannot handle : in URLs
		    */
		    java.util.Calendar nowCal = java.util.Calendar.getInstance();
		    java.util.Date now = nowCal.getTime();
		    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
			    "MM/dd/yyyy HH_mm_ss a");
		    String strNow = sdf.format(now);
		    if (strDataURL.indexOf("?") > 0) {
			encodedURL = strDataURL + "&FCCurrTime=" + strNow;
		    } else {
			strDataURL = strDataURL + "?FCCurrTime=" + strNow;
		    }
		    encodedURL = response.encodeURL(strDataURL);
	
		}
		return encodedURL;
    }

    
    public String createChart(String chartSWF, String strURL, String strXML,
	    String chartId, int chartWidth, int chartHeight, boolean debugMode,
	    boolean registerWithJS) {
		StringBuffer strBuf = new StringBuffer();
		/*
		First we create a new DIV for each chart. We specify the name of DIV as "chartId"Div.
		DIV names are case-sensitive.
		*/
		strBuf.append("<!--START Script Block for Chart -->\n");
		strBuf.append("\t\t<div id='" + chartId + "Div' align='center'>\n");
		strBuf.append("\t\t\t\tChart.\n");
	
		/*The above text "Chart" is shown to users before the chart has started loading
		 (if there is a lag in relaying SWF from server). This text is also shown to users
		 who do not have Flash Player installed. You can configure it as per your needs.*/
	
		strBuf.append("\t\t</div>\n");
	
		/*Now, we render the chart using FusionCharts Class. Each chart's instance (JavaScript) Id
		 is named as chart_"chartId".*/
	
		strBuf.append("\t\t<script type='text/javascript'>\n");
		//Instantiate the Chart
		Boolean registerWithJSBool = new Boolean(registerWithJS);
		Boolean debugModeBool = new Boolean(debugMode);
		int regWithJSInt = boolToNum(registerWithJSBool);
		int debugModeInt = boolToNum(debugModeBool);
	
		strBuf.append("\t\t\t\tvar chart_" + chartId + " = new FusionCharts('"
			+ chartSWF + "', '" + chartId + "', '" + chartWidth + "', '"
			+ chartHeight + "', '" + debugModeInt + "', '" + regWithJSInt
			+ "');\n");
		//Check whether we've to provide data using dataXML method or dataURL method
		if (strXML.equals("")) {
		    strBuf.append("\t\t\t\t//Set the dataURL of the chart\n");
		    strBuf.append("\t\t\t\tchart_" + chartId + ".setDataURL(\"" + strURL
			    + "\");\n");
		} else {
		    strBuf.append("\t\t\t\t//Provide entire XML data using dataXML method\n");
		    strBuf.append("\t\t\t\tchart_" + chartId + ".setDataXML(\"" + strXML
			    + "\");\n");
		}
		strBuf.append("\t\t\t\t//Finally, render the chart.\n");
		strBuf.append("\t\t\t\tchart_" + chartId + ".render(\"" + chartId + "Div\");\n");
		strBuf.append("\t\t</script>\n");
		strBuf.append("\t\t<!--END Script Block for Chart-->\n");
		return strBuf.substring(0);
    }
    
    public String createXMLData(ResultSet result, String titulo) throws SQLException{
    	String str = "<chart caption='"+titulo+"' palette='2' animation='1' formatnumberscale='0' decimals='0' pieslicedepth='30' startingangle='125' showborder='0' >";
    	while(result.next()){
    		str += "<set label='"+result.getString(1)+"' value='"+result.getString(2)+"' />";
    	}
    	str += "</chart>";
    	return str;
    	
    }
    
    
    public String createXMLDataList(List<DadosGrafico> result, String titulo){
    	String str = "<chart caption='"+titulo+"' palette='2' animation='1' formatnumberscale='0' decimals='0' pieslicedepth='30' startingangle='125' showborder='0' >";
    	for (DadosGrafico dados : result) {
    		str += "<set label='"+dados.getLabel()+"' value='"+dados.getValue()+"' />";
    	}
    	str += "</chart>";
    	return str;
    	
    }
    
    public String createXMLDataV1(ResultSet result, String titulo) throws SQLException{
    	String str = "<chart caption='"+titulo+"' palette='2' animation='1' formatnumberscale='0' decimals='0' pieslicedepth='30' startingangle='125' showborder='0' >";
    	while(result.next()){
    		str += "<set label='"+result.getString(1)+"' value='"+result.getString(3)+"' />";
    	}
    	str += "</chart>";
    	return str;
    	
    }
    
    public String createXMLDataV2(ResultSet result, String titulo) throws SQLException{
    	String str = "<chart caption='"+titulo+"' palette='2' animation='1' formatnumberscale='0' decimals='0' pieslicedepth='30' startingangle='125' showborder='0' >";
    	//exportEnabled='1' exportAction='save' exportAtClient='0' exportHandler='http://localhost/multiwork/relatorio/' html5ExportHandler='http://localhost/multiwork/relatorio/'
    	while(result.next()){
    		str += "<set name='"+result.getString(1)+"' value='"+result.getString(2)+"' />";
    	}
    	str += "</chart>";
    	return str;
    	
    }

    public String createListaDados(ResultSet result, String titulo) throws SQLException{
    	String str = "";
    	while(result.next()){
    		str += "['"+result.getString(1)+"',   "+result.getString(2)+"],";
    		
    	}
    	
    	return str;
    	
    }

    public String createChartHTML(String chartSWF, String strURL,
	    String strXML, String chartId, int chartWidth, int chartHeight,
	    boolean debugMode) { 
		/*Generate the FlashVars string based on whether dataURL has been provided
	     or dataXML.*/
		String strFlashVars = "";
		Boolean debugModeBool = new Boolean(debugMode);
	
		if (strXML.equals("")) {
		    //DataURL Mode
		    strFlashVars = "chartWidth=" + chartWidth + "&chartHeight="
			    + chartHeight + "&debugMode=" + boolToNum(debugModeBool)
			    + "&dataURL=" + strURL + "";
		} else {
		    //DataXML Mode
		    strFlashVars = "chartWidth=" + chartWidth + "&chartHeight="
			    + chartHeight + "&debugMode=" + boolToNum(debugModeBool)
			    + "&dataXML=" + strXML + "";
		}
		StringBuffer strBuf = new StringBuffer();
	
		// START Code Block for Chart  
		strBuf.append("\t\t<!--START Code Block for Chart-->\n");
		strBuf
			.append("\t\t\t\t<object classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000' codebase='http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0' width='"
				+ chartWidth
				+ "' height='"
				+ chartHeight
				+ "' id='"
				+ chartId + "'>\n");
		strBuf.append("\t\t\t\t	<param name='allowScriptAccess' value='always' />\n");
		strBuf.append("\t\t\t\t	<param name='movie' value='" + chartSWF + "'/>\n");
		strBuf.append("\t\t\t\t<param name='FlashVars' value=\"" + strFlashVars
			+ "\" />\n");
		strBuf.append("\t\t\t\t	<param name='quality' value='high' />\n");
		strBuf
			.append("\t\t\t\t<embed src='"
				+ chartSWF
				+ "' FlashVars=\""
				+ strFlashVars
				+ "\" quality='high' width='"
				+ chartWidth
				+ "' height='"
				+ chartHeight
				+ "' name='"
				+ chartId
				+ "' allowScriptAccess='always' type='application/x-shockwave-flash' pluginspage='http://www.macromedia.com/go/getflashplayer' />\n");
		strBuf.append("\t\t</object>\n");
		// END Code Block for Chart
		strBuf.append("\t\t<!--END Code Block for Chart-->\n");
		return strBuf.substring(0);
    }

    
    public int boolToNum(Boolean bool) {
		int num = 0;
		if (bool.booleanValue()) {
		    num = 1;
		}
		return num;
    }



}
