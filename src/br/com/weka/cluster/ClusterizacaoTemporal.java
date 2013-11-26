package br.com.weka.cluster;

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

import weka.clusterers.SimpleKMeans;
import weka.core.EuclideanDistance;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import br.com.weka.manipulador.Manipulador;
import br.com.weka.model.Estatistica;

public class ClusterizacaoTemporal {
	
	private List<String[]> lista = new ArrayList<String[]>();
	
	public ClusterizacaoTemporal(String caminhoDoArquivo) throws IOException {  
	    File arquivoCSV = new File(caminhoDoArquivo);  
	    FileReader fr = new FileReader(arquivoCSV);  
	    BufferedReader br = new BufferedReader(fr);  
	                     
	    String linha;  
	    while ((linha = br.readLine()) != null) {  
	        String registro[] = linha.split(",");  
	        lista.add(registro);  
	    } 
    }
	
	public ClusterizacaoTemporal(){
		
	}

	public void clusterizar()  throws Exception{
		for (int i=0; i <= 99; i++) {
				Manipulador manip = new Manipulador();
				FileReader reader = new FileReader(manip.getDiretorioClustersEspaciais()+"clusterizacao_espacial"+i+".arff");
			    Instances instâncias = new Instances(reader);
			    String[] opcoes = new String[2];
			    opcoes[0] = "-R";                                    // "range"
			    opcoes[1] = "1,2,3,4,5,6,8,9,10,11,12"; // deixando apenas os atributos de coordenadas
			    Remove remove = new Remove();                         // new instance of filter
			    remove.setOptions(opcoes);                           // set options
			    remove.setInputFormat(instâncias);                          // inform filter about dataset **AFTER** setting options
			    Instances newData = Filter.useFilter(instâncias, remove);   // apply filter
			    
			    // Ignorando atributos (nao tenho certeza se é esse método)
			    //instâncias.deleteAttributeAt(0);
		
			    // Criamos o agrupamento.
			    SimpleKMeans agrupamento = new SimpleKMeans();
				EuclideanDistance ed = new EuclideanDistance();
				
				//Definindo como será feita a clusterização
			    agrupamento.setNumClusters(10);
			    agrupamento.setDisplayStdDevs(true);
				String[] options = new String[2];
				options[0] = "-R";
				options[1] = "first-last";
				agrupamento.setPreserveInstancesOrder(false);
				agrupamento.setDistanceFunction(ed);
				agrupamento.setMaxIterations(500);
				agrupamento.setSeed(10);
				agrupamento.setDontReplaceMissingValues(false);
				agrupamento.setOptions(options);
			    agrupamento.buildClusterer(newData);
			    
			    StringBuffer textoBuffer = new StringBuffer();
			    
			     // Mostramos a que cluster pertence cada instância.
			    for(int inst=0;inst<newData.numInstances();inst++)
			    {
			    	Instance instancia = newData.instance(inst);
			    	Instance teste = instâncias.instance(inst);
			        int cluster = (int)(agrupamento.clusterInstance(instancia));
			        textoBuffer.append(teste+","+cluster + " \n");	
			    }
			    
			    try {
		            FileOutputStream oStream = new FileOutputStream(manip.getDiretorioClustersTemporais()+"clusterizacao_temporal_"+i+".arff"); // ou usando um File com argumento  
		            OutputStreamWriter osw = new OutputStreamWriter(oStream);  
		            Writer writer = new BufferedWriter(osw);  
		            writer.write(textoBuffer.toString());  
		  
		            writer.flush();  
		            writer.close();  
		            System.out.println("Arquivo clusterizacao_temporal_"+i+".arff - Sucesso");
		        } catch (Exception e) {  
		        	System.out.println("Arquivo clusterizacao_temporal_"+i+".arff - Erro");
		            e.printStackTrace();  
		        } 
		}
		System.out.println("---------------- Criado Arquivos ------------------");
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
