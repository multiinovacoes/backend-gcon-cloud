package br.com.multiinovacoes.gcon.graphics;

import java.util.List;

public class SeriesName {
	
	private String seriesname;
	
	private List<Data> data;
	
	public SeriesName() {
	}

	public SeriesName(String seriesname, List<Data> data) {
		super();
		this.seriesname = seriesname;
		this.data = data;
	}

	public String getSeriesname() {
		return seriesname;
	}

	public void setSeriesname(String seriesname) {
		this.seriesname = seriesname;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}
	
	

}
