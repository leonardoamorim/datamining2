package weka;

import java.io.FileReader;
import weka.clusterers.SimpleKMeans;
import weka.core.*;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.Filter;

/**
 * Esta classe demonstra o uso de um algoritmo de agrupamento (K-Means). A classe
 * lê um arquivo no formato .ARFF, cria o agrupamento  e classifica o próprio arquivo original.
 */
public class ExemploKMeans
  {
  public static void main(String[] args) throws Exception
    {
    FileReader reader = new FileReader("/home/leo/workspace2/DataMining2/arquivo.arff");
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
    
    // Mostrando quantas instâncias tem um cluster e sua centroide (não vamos usar)
    /*
    for(int cluster=0;cluster<clusterCenters.numInstances();cluster++)
    {
      System.out.println("Cluster #"+(cluster+1)+": "+clusterSizes[cluster]+" dados.");
      System.out.println("   Centróide: "+clusterCenters.instance(cluster));
      //System.out.println("   STDDEV: "+clusterSTDDEVs.instance(cluster));
    }
    */
        // Mostramos a que cluster pertence cada instância.
    for(int inst=0;inst<newData.numInstances();inst++)
    {
    	Instance instância = newData.instance(inst);
    	Instance teste = instâncias.instance(inst);
        int cluster = (int)(agrupamento.clusterInstance(instância));
        System.out.println(inst+","+teste+","+cluster);
    
    }
    //Caso queira ver que realmente o filtro funciona
    //System.out.println(newData);
    }
  

}
