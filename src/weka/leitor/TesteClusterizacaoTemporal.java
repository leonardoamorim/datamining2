package weka.leitor;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class TesteClusterizacaoTemporal {

	private List<Estatistica> listaEstatistica = new ArrayList<Estatistica>();
	
	public TesteClusterizacaoTemporal(String fileName) throws IOException{
		LerArquivoClusterizacaoTemporal leitor = new LerArquivoClusterizacaoTemporal(fileName);
		this.listaEstatistica = leitor.getListaEstatistica();
	}
	
	public int getQuantidadeTipoIndicadorPorCluster(Integer tipoMarcador, Integer cluster){
		int count = 0;
		for(Estatistica e : this.listaEstatistica){
			if(e.getTipo_marcador() == tipoMarcador && Integer.parseInt(e.getCluster().trim()) == cluster){
				count++;
			}
		}
		
		return count;
	}
	
	
	public static void main(String[] args) {
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
			// percorrer os arquivos
		  for(int i = 0; i < 100; i++){
			  TesteClusterizacaoTemporal tct = new TesteClusterizacaoTemporal("/home/adercio/Área de Trabalho/projetoClusterizacao/datamining2/arquivo/temporal/clusters/clusterizacao_temporal_"+i+".arff");
				for(int cluster =0; cluster < 10; cluster++){
					textoBuffer.append(" "+i+"."+cluster+" ");
					virgula= ", ";
					for (int indicador=1; indicador <= 59; indicador++) {
						quantidade = tct.getQuantidadeTipoIndicadorPorCluster(indicador, cluster);
						if(indicador == 59){
							virgula = "";
						}
						textoBuffer.append(","+quantidade+virgula);
					}
					textoBuffer.append("\n");
				}
			
				try {  
		              FileOutputStream oStream = new FileOutputStream("/home/adercio/Área de Trabalho/projetoClusterizacao/datamining2/arquivo/temporal/datawarehouse.csv"); // ou usando um File com argumento  
		              OutputStreamWriter osw = new OutputStreamWriter(oStream);  
		              Writer writer = new BufferedWriter(osw);  
		              writer.write(textoBuffer.toString());  
		    
		              writer.flush();  
		              writer.close();  
		              System.out.println("Arquivo "+i+" Gerado com sucesso!");
		          } catch (Exception e) {  
		              e.printStackTrace();  
		          } 
		  }
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
