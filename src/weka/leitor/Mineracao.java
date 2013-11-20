package weka.leitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Mineracao {
	
	private List<String[]> lista = new ArrayList<String[]>();
	private List<String[]> listaIndicadores = new ArrayList<String[]>();
	private static List<Tupla> listaRankingIndicadores = new ArrayList<Tupla>();
	private static int totalGeralMarcacoes = 0;

    public Mineracao(String caminhoDoArquivo) throws IOException {  
	    
	    File arquivoCSV = new File(caminhoDoArquivo);  
	    FileReader fr = new FileReader(arquivoCSV);  
	    BufferedReader br = new BufferedReader(fr);  
	                     
	    String linha;  
	    while ((linha = br.readLine()) != null) {  
	        String registro[] = linha.split(",");  
	        lista.add(registro);  
	    } 
	    
	    if (lista.size() > 0) {  
    		// percorrer todos os indicadores	
		for(int j=1; j <= 59; j++){
			// lista de todos registros
			for (int i = 1; i < lista.size(); i++) {  
		            	String[] indicador = {null,null};
		            	// qual indicador pertence
		            	indicador[0] = ""+j;
		            	// valor do registro
		            	indicador[1] = lista.get(i)[j];
		                this.listaIndicadores.add(indicador);
	            	}
            }  
        } 
    }
    
    public static void main(String[] args) {
		try {
			Mineracao m = new Mineracao("/home/adercio/Área de Trabalho/projetoClusterizacao/datamining2/arquivo/temporal/datawarehouse.csv");
			for(int i=1; i <=59; i++){
				int qtdeRegistros = m.contarTotalDeRegistrosPorTipoDeIndicador(i);
				int qtdeMarcacoes = m.contarTotalDeMarcacaoPorTipoDeIndicador(i);
				totalGeralMarcacoes += qtdeMarcacoes;

            	// qual indicador pertence
				Tupla tupla = new Tupla(i, qtdeRegistros, qtdeMarcacoes);
            	
				listaRankingIndicadores.add(tupla);
			}
			// Ordenar por ranking
			m.ordenaPorRanking();
			
//			System.out.println("Soma Total Todas as Marcações: "+totalTodasMarcacoes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
    
	public void ordenaPorRanking(){
    	Collections.sort(listaRankingIndicadores, new TuplaComparator());
	    
//	    ArrayList teste2 = listaR;
		System.out.println("Ordenação: " + listaRankingIndicadores);
	}
    
	
	public int contarTotalDeRegistrosPorTipoDeIndicador(int indicador){
		int qtdeRegistros = 0;
		for (int i=0; i < listaIndicadores.size(); i++) {
			if(listaIndicadores.get(i)[0].trim().equalsIgnoreCase(String.valueOf(indicador))){
				listaIndicadores.get(i)[1] = listaIndicadores.get(i)[1].replaceAll(" ", "");
				if(!listaIndicadores.get(i)[1].equalsIgnoreCase("0")){
					qtdeRegistros++;
				}
			}
		}
		return qtdeRegistros;
	}
	
	public int contarTotalDeMarcacaoPorTipoDeIndicador(int indicador){
		int qtdeMarcacao = 0;
		for (int i=0; i < listaIndicadores.size(); i++) {
			if(listaIndicadores.get(i)[0].trim().equalsIgnoreCase(String.valueOf(indicador))){
				listaIndicadores.get(i)[1] = listaIndicadores.get(i)[1].replaceAll(" ", "");
				if(!listaIndicadores.get(i)[1].equalsIgnoreCase("0")){
					qtdeMarcacao = qtdeMarcacao + Integer.parseInt(listaIndicadores.get(i)[1]);
				}
			}
		}
		return qtdeMarcacao;
	}

}
