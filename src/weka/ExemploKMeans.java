package weka;

import java.io.FileReader;


import weka.clusterers.SimpleKMeans;
import weka.core.*;

/**
 * Esta classe demonstra o uso de um algoritmo de agrupamento (K-Means). A classe
 * lê um arquivo no formato .ARFF, cria o agrupamento  e classifica o próprio arquivo original.
 */
public class ExemploKMeans
  {
  public static void main(String[] args) throws Exception
    {
    // Usaremos a base de flores íris.
    FileReader reader = new FileReader("/home/leo/workspace2/DataMining2/arquivo.arff");
    Instances instâncias = new Instances(reader);
    // Queremos ignorar a classe das instâncias.
    instâncias.deleteAttributeAt(4);
    // Criamos o agrupamento.
    SimpleKMeans agrupamento = new SimpleKMeans();
    agrupamento.setNumClusters(100);
    agrupamento.setDisplayStdDevs(true);
    agrupamento.buildClusterer(instâncias);
    // Mostramos estatísticas sobre o agrupamento.
    Instances clusterCenters = agrupamento.getClusterCentroids();
    Instances clusterSTDDEVs = agrupamento.getClusterStandardDevs();
    int[] clusterSizes = agrupamento.getClusterSizes();
    for(int cluster=0;cluster<clusterCenters.numInstances();cluster++)
      {
      System.out.println("Cluster #"+(cluster+1)+": "+clusterSizes[cluster]+" dados.");
      System.out.println("   Centróide: "+clusterCenters.instance(cluster));
      System.out.println("   STDDEV: "+clusterSTDDEVs.instance(cluster));
      }
        // Mostramos a que cluster pertence cada instância.
        for(int inst=0;inst<instâncias.numInstances();inst++)
      {
            Instance instância = instâncias.instance(inst);
            int cluster = (int)(agrupamento.clusterInstance(instância));
            System.out.println("Para instância "+inst+" cluster é "+cluster);
            }
    }

}
