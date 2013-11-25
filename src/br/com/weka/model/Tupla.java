package br.com.weka.model;

public class Tupla {
	private int indicador;
	private int qtdRegistros;
	private int qtdMarcadores;
	
	public Tupla(int i, int r, int m){
		indicador = i;
		qtdRegistros = r;
		qtdMarcadores = m;
	}
	
	public int getIndicador() {
		return indicador;
	}

	public void setIndicador(int indicador) {
		this.indicador = indicador;
	}

	public int getQtdRegistros() {
		return qtdRegistros;
	}

	public void setQtdRegistros(int qtdRegistros) {
		this.qtdRegistros = qtdRegistros;
	}

	public int getQtdMarcadores() {
		return qtdMarcadores;
	}

	public void setQtdMarcadores(int qtdMarcadores) {
		this.qtdMarcadores = qtdMarcadores;
	}

	public String toString(){
		return "{ " + indicador + ", " + qtdRegistros + ", " + qtdMarcadores + " }";
	}
	
}
