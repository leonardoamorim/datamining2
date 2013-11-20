package weka.leitor;


class Tupla {
	int indicador;
	int qtd_registros;
	int qtd_marcadores;
	
	public Tupla(int i, int r, int m){
		indicador = i;
		qtd_registros = r;
		qtd_marcadores = m;
	}
	
	public String toString(){
		return "{ " + indicador + ", " + qtd_registros + ", " + qtd_marcadores + " }";
	}	
}
