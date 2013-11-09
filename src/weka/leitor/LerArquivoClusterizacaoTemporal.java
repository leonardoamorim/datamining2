package weka.leitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LerArquivoClusterizacaoTemporal {  
    
	List<String[]> lista = new ArrayList<String[]>();  
	
	 public LerArquivoClusterizacaoTemporal(String caminhoDoArquivo) throws IOException {  
		    
		    File arquivoCSV = new File(caminhoDoArquivo);  
		    FileReader fr = new FileReader(arquivoCSV);  
		    BufferedReader br = new BufferedReader(fr);  
		                     
		    String linha;  
		    while ((linha = br.readLine()) != null) {  
		        String registro[] = linha.split(",");  
		        lista.add(registro);  
		    } 
	    }  
	    
	    
	    public List<Estatistica> getListaEstatistica(){
	    	List<Estatistica> estatisticas = new ArrayList<Estatistica>();
	    	if (lista.size() > 0) {  
	            for (int i = 1; i < lista.size(); i++) {  
	                Estatistica estatistica = new Estatistica();
//	                estatistica.setNumero_instancia(lista.get(i)[0]);
	                estatistica.setId_usuario(Integer.parseInt(lista.get(i)[0]));
	                estatistica.setMarcador(lista.get(i)[1]);
	                estatistica.setTipo_marcador(Integer.parseInt(lista.get(i)[2]));
	                estatistica.setAno(Integer.parseInt(lista.get(i)[3]));
	                estatistica.setMes(Integer.parseInt(lista.get(i)[4]));
	                estatistica.setSemana(Integer.parseInt(lista.get(i)[5]));
	                estatistica.setDia(Integer.parseInt(lista.get(i)[6]));
	                estatistica.setHora(Integer.parseInt(lista.get(i)[7]));
	                estatistica.setMinuto(Integer.parseInt(lista.get(i)[8]));
	                estatistica.setSegundo(Integer.parseInt(lista.get(i)[9]));
	                estatistica.setCoordenadaX(lista.get(i)[10]);
	                estatistica.setCoordenadaY(lista.get(i)[11]);
	                estatistica.setCluster(lista.get(i)[12]);
	                estatisticas.add(estatistica);
	            }  
	        }  
	    	return estatisticas;
	    }   
    
}  
