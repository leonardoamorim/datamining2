package br.com.weka.mineracao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import br.com.weka.model.Estatistica;

public class Datawarehouse {
	
	private List<String[]> lista = new ArrayList<String[]>();
	private List<Estatistica> listaEstatistica = new ArrayList<Estatistica>();
	
	public Datawarehouse(String caminhoDoArquivo) throws IOException {  
	    File arquivoCSV = new File(caminhoDoArquivo);  
	    FileReader fr = new FileReader(arquivoCSV);  
	    BufferedReader br = new BufferedReader(fr);  
	                     
	    String linha;  
	    while ((linha = br.readLine()) != null) {  
	        String registro[] = linha.split(",");  
	        lista.add(registro);  
	    } 
	    
    }
	
	public Datawarehouse(){
		
	}

	public void geraDataWareHouse(String pastaOrigem, String arquivoDestino){
		try {
			StringBuffer textoBuffer = new StringBuffer();
			int quantidade = 0;
			textoBuffer.append("ClusterTemp, Indicador 1, Indicador 2, Indicador 3, Indicador 4, Indicador 5, Indicador 6, Indicador 7, Indicador 8"
					+ ", Indicador 9, Indicador 10, Indicador 11, Indicador 12, Indicador 13, Indicador 14, Indicador 15, Indicador 16, Indicador 17"
					+ ", Indicador 18, Indicador 19, Indicador 20, Indicador 21, Indicador 22, Indicador 23, Indicador 24, Indicador 25, Indicador 26"
					+ ", Indicador 27, Indicador 28, Indicador 29, Indicador 30, Indicador 31, Indicador 32, Indicador 33, Indicador 34, Indicador 35"
					+ ", Indicador 36, Indicador 37, Indicador 38, Indicador 39, Indicador 40, Indicador 41, Indicador 42, Indicador 43, Indicador 44"
					+ ", Indicador 45, Indicador 46, Indicador 47, Indicador 48, Indicador 49, Indicador 50, Indicador 51, Indicador 52, Indicador 53"
					+ ", Indicador 54, Indicador 55, Indicador 56, Indicador 57, Indicador 58, Indicador 59");
			textoBuffer.append("\n");
			String virgula= "";
			Datawarehouse datawarehouse = null;
			// percorrer os arquivos
		  for(int i = 0; i < 100; i++){
			  datawarehouse = new Datawarehouse(pastaOrigem+"clusterizacao_temporal_"+i+".arff");
			  
			  for(int cluster =0; cluster < 10; cluster++){
					textoBuffer.append(" "+i+"."+cluster+", ");
					virgula= ", ";
					for (int indicador=1; indicador <= 59; indicador++) {
						quantidade = datawarehouse.getQuantidadeTipoIndicadorPorCluster(indicador, cluster);
						if(indicador == 59){
							virgula = "";
						}
						textoBuffer.append(quantidade+virgula);
					}
					textoBuffer.append("\n");
				}
			  System.out.println("Estrutura do Arquivo "+i+" criado!");
		  }
		  
		  try {  
              FileOutputStream oStream = new FileOutputStream(arquivoDestino); // arquivo datawarehouse que será criado. 
              OutputStreamWriter osw = new OutputStreamWriter(oStream);  
              Writer writer = new BufferedWriter(osw);  
              writer.write(textoBuffer.toString());  
    
              writer.flush();  
              writer.close();  
              System.out.println("Datawarehouse gerado com sucesso!");
          } catch (Exception e) {  
              e.printStackTrace();  
          } 
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public int getQuantidadeTipoIndicadorPorCluster(Integer tipoMarcador, Integer cluster){
		int count = 0;
		List<Estatistica> listaEstac = this.getListaEstatistica();
		for(Estatistica e : listaEstac){
			if(e.getTipoMarcador() == tipoMarcador && Integer.parseInt(e.getCluster().trim()) == cluster){
				count++;
			}
		}
		
		return count;
	}
	
	  
    public List<Estatistica> getListaEstatistica(){
    	List<Estatistica> estatisticas = new ArrayList<Estatistica>();
    	if (lista.size() > 0) {  
            for (int i = 1; i < lista.size(); i++) {  
                Estatistica estatistica = new Estatistica();
                estatistica.setIdUsuario(Integer.parseInt(lista.get(i)[0]));
                estatistica.setMarcador(lista.get(i)[1]);
                estatistica.setTipoMarcador(Integer.parseInt(lista.get(i)[2]));
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
