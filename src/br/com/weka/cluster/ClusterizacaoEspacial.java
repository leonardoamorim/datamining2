package br.com.weka.cluster;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import weka.clusterers.SimpleKMeans;
import weka.core.EuclideanDistance;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class ClusterizacaoEspacial {

	public void clusterizar(String arquivoOrigem, String arquivoDestino)  throws Exception{
		FileReader reader = new FileReader(arquivoOrigem);
	    Instances instâncias = new Instances(reader);
	    String[] opcoes = new String[2];
	    opcoes[0] = "-R";                                    // "range"
	    opcoes[1] = "1,2,3,4,5,6,7,8,9,10"; // deixando apenas os atributos de coordenadas
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
	    agrupamento.setNumClusters(100);
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
	    // Mostramos estatísticas sobre o agrupamento.
	    Instances clusterCenters = agrupamento.getClusterCentroids();
	    Instances clusterSTDDEVs = agrupamento.getClusterStandardDevs();
	    int[] clusterSizes = agrupamento.getClusterSizes();
	    

	    StringBuffer textoBuffer = new StringBuffer();
	    
	        // Mostramos a que cluster pertence cada instância.
	    for(int inst=0;inst<newData.numInstances();inst++)
	    {
	    	Instance instancia = newData.instance(inst);
	    	Instance teste = instâncias.instance(inst);
	        int cluster = (int)(agrupamento.clusterInstance(instancia));
	        textoBuffer.append(inst+","+teste+","+cluster + " \n");	
	    }
	    
	    try {  
            FileOutputStream oStream = new FileOutputStream(arquivoDestino); // ou usando um File com argumento  
            OutputStreamWriter osw = new OutputStreamWriter(oStream);  
            Writer writer = new BufferedWriter(osw);  
            writer.write(textoBuffer.toString());  
  
            writer.flush();  
            writer.close();  
            System.out.println("Clusterização Espacial realizada com sucesso!");
        } catch (Exception e) {  
        	System.out.println("Falha na clusterização Espacial!");
            e.printStackTrace();  
        } 
	}
}
