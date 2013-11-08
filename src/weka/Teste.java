package weka;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import weka.leitor.Estatistica;
import weka.leitor.ReplicadorArquivosPorCluster;

public class Teste {

	public static void main(String[] args) {
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
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	
	}

}
