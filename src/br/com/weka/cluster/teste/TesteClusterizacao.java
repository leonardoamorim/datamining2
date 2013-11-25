package br.com.weka.cluster.teste;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.weka.cluster.ClusterizacaoEspacial;
import br.com.weka.cluster.ClusterizacaoTemporal;
import br.com.weka.cluster.ClusterizacaoTemporalPorCluster;
import br.com.weka.mineracao.Datawarehouse;
import br.com.weka.mineracao.Mineracao;
import br.com.weka.model.Estatistica;


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
//	   clusterizacaoTemporal();
//	  	geraDataWareHouse();
      geraRankingIndicadores();
	  
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
		clusterizacaoEspacial.clusterizar("/home/adercio/Área de Trabalho/projetoClusterizacao/datamining2/arquivo/arquivo.arff", "/home/adercio/Área de Trabalho/projetoClusterizacao/datamining2/arquivo/espacial/clusterizacao_espacial.arff");
	}
	
	private static void clusterizacaoPorClusterEmVariosArquivos(){
		try {
		ClusterizacaoTemporalPorCluster ctpc = new ClusterizacaoTemporalPorCluster("/home/adercio/Área de Trabalho/projetoClusterizacao/datamining2/arquivo/espacial/clusterizacao_espacial.arff");
		ctpc.iniciarRanking();
		System.out.println("---------------- Criado Arquivos ------------------");
		}catch(IOException ex){
			ex.printStackTrace();
		}
			
	}
  
	public static void geraDataWareHouse(){
		Datawarehouse datawarehouse = new Datawarehouse();
		datawarehouse.geraDataWareHouse("/home/adercio/Área de Trabalho/projetoClusterizacao/datamining2/arquivo/temporal/clusters/", "/home/adercio/Área de Trabalho/projetoClusterizacao/datamining2/arquivo/temporal/datawarehouse.csv");
	}
	
	public static void geraRankingIndicadores(){
		Mineracao mineracao;
		try {
			mineracao = new Mineracao();
			mineracao.startMineracao();
			mineracao.ordenaPorRanking();
			System.out.println("Ordenação: " + mineracao.getListaRankingIndicadores());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
