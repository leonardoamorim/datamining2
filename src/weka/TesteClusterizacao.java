package weka;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import weka.leitor.Estatistica;
import weka.leitor.ReplicadorArquivosPorCluster;


/**
 * Esta classe demonstra o uso de um algoritmo de agrupamento (K-Means). A classe
 * lê um arquivo no formato .ARFF, cria o agrupamento  e classifica o próprio arquivo original.
 */
public class TesteClusterizacao
  {
  public static void main(String[] args) throws Exception
    {
//	  	  clusterizacaoEspacial();
//	   clusterizacaoPorClusterEmVariosArquivos();
	   clusterizacaoTemporal();
	  
    }

	private static void clusterizacaoTemporal() {
		ClusterizacaoTemporal ct = new ClusterizacaoTemporal();
		try {
			ct.clusterizar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	private static void clusterizacaoEspacial() throws Exception {
		ClusterizacaoEspacial clusterizacaoEspacial = new ClusterizacaoEspacial();
		clusterizacaoEspacial.clusterizar();
	}
	
	private static void clusterizacaoPorClusterEmVariosArquivos(){
		try {
			ReplicadorArquivosPorCluster rapc = new ReplicadorArquivosPorCluster("/home/adercio/Área de Trabalho/projetoClusterizacao/datamining2/arquivo/espacial/clusterizacao_espacial.arff");
			List<Estatistica> listaEstatistica = rapc.getListaEstatistica();
			List<Estatistica> listaPorCluster = new ArrayList<Estatistica>();
			for(int i=0; i <= 99; i++){
				for (Estatistica estatistica : listaEstatistica) {
					if(estatistica.getCluster().trim().equals(i+"")){
						listaPorCluster.add(estatistica);
					}
				}
				rapc.criarArquivo(listaPorCluster, i);
				listaPorCluster = new ArrayList<Estatistica>();
			}
			System.out.println("---------------- Criado Arquivos ------------------");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}
  

}
