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
	  	ClusterizacaoEspacial clusterizacaoEspacial = new ClusterizacaoEspacial();
	  	clusterizacaoEspacial.clusterizar();
    }
  

}
