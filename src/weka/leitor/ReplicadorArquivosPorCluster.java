package weka.leitor;

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

public class ReplicadorArquivosPorCluster {

	List<String[]> lista = new ArrayList<String[]>();  
	
    public ReplicadorArquivosPorCluster(String caminhoDoArquivo) throws IOException {  
	    
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
                estatistica.setNumero_instancia(lista.get(i)[0]);
                estatistica.setId_usuario(Integer.parseInt(lista.get(i)[1]));
                estatistica.setMarcador(lista.get(i)[2]);
                estatistica.setTipo_marcador(Integer.parseInt(lista.get(i)[3]));
                estatistica.setAno(Integer.parseInt(lista.get(i)[4]));
                estatistica.setMes(Integer.parseInt(lista.get(i)[5]));
                estatistica.setSemana(Integer.parseInt(lista.get(i)[6]));
                estatistica.setDia(Integer.parseInt(lista.get(i)[7]));
                estatistica.setHora(Integer.parseInt(lista.get(i)[8]));
                estatistica.setMinuto(Integer.parseInt(lista.get(i)[9]));
                estatistica.setSegundo(Integer.parseInt(lista.get(i)[10]));
                estatistica.setCoordenadaX(lista.get(i)[11]);
                estatistica.setCoordenadaY(lista.get(i)[12]);
                estatistica.setCluster(lista.get(i)[13]);
                estatisticas.add(estatistica);
            }  
        }  
    	return estatisticas;
    }  
    
    public void criarArquivo(List<Estatistica> lista, int posicao){
    	StringBuffer textoBuffer = new StringBuffer();
    	// add cabecalho do arquivo.
	    textoBuffer.append("@relation arquivo \n \n"+
	    					"@attribute numero instancia \n"+
							"@attribute id_usuario numeric \n"+
							"@attribute marcador {DimensaoAmbiental,DimensaoSocial} \n"+
							"@attribute tipo_marcador numeric \n"+
							"@attribute ano numeric \n"+
							"@attribute mes numeric \n"+
							"@attribute semana numeric \n"+
							"@attribute dia numeric \n"+
							"@attribute hora numeric \n"+
							"@attribute minuto numeric \n"+
							"@attribute segundo numeric \n"+
							"@attribute coordenada_x numeric \n"+
							"@attribute coordenada_y numeric \n"+
							"@attribute cluster numeric \n\n"+
							"@data \n");
    	for (int i=0; i < lista.size(); i++) {
    		Estatistica e = lista.get(i);
    		textoBuffer.append(e.getNumero_instancia()+","+e.getId_usuario()+","+e.getMarcador()+","+e.getTipo_marcador()+","+e.getAno()
    				+","+e.getMes()+","+e.getSemana()+","+e.getDia()+","+e.getHora()+","+e.getMinuto()+","+e.getSegundo()
    				+","+e.getCoordenadaX()+","+e.getCoordenadaY()+","+e.getCluster()+"\n");
		}
    	  try {  
              FileOutputStream oStream = new FileOutputStream("/home/adercio/Área de Trabalho/projetoClusterizacao/datamining2/arquivo/espacial/clusters/clusterizacao_espacial"+posicao+".arff"); // ou usando um File com argumento  
              OutputStreamWriter osw = new OutputStreamWriter(oStream);  
              Writer writer = new BufferedWriter(osw);  
              writer.write(textoBuffer.toString());  
    
              writer.flush();  
              writer.close();  
          } catch (Exception e) {  
              e.printStackTrace();  
          } 
    }
}
