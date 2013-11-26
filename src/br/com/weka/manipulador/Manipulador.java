package br.com.weka.manipulador;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Manipulador {

	private String arquivoPrincipal;
	private String arquivoEspacial;
	private String diretorioDestinoArquivoEspacial;
	private String diretorioClustersEspaciais;
	private String diretorioClustersTemporais;
	private String diretorioDestinoDatawarehouse;
	private String arquivoDatawarehouse;
		
	public Manipulador() throws IOException {
		Properties p = getProp();
		arquivoPrincipal = p.getProperty("prop.arquivo.principal");
		arquivoEspacial = p.getProperty("prop.arquivo.espacial");
		diretorioDestinoArquivoEspacial = p.getProperty("prop.diretorio.destino.espacial");
		diretorioClustersTemporais = p.getProperty("prop.diretorio.destino.temporal.clusters");
		diretorioDestinoDatawarehouse = p.getProperty("prop.diretorio.destino.datawarehouse");
		arquivoDatawarehouse = p.getProperty("prop.arquivo.datawarehouse");
		diretorioClustersEspaciais = p.getProperty("prop.diretorio.destino.espacial.clusters");
	}
	
		
	public String getArquivoPrincipal() {
		return arquivoPrincipal;
	}

	public String getArquivoEspacial() {
		return arquivoEspacial;
	}

	public String getDiretorioDestinoArquivoEspacial() {
		return diretorioDestinoArquivoEspacial;
	}

	public String getDiretorioClustersEspaciais() {
		return diretorioClustersEspaciais;
	}

	public String getDiretorioClustersTemporais() {
		return diretorioClustersTemporais;
	}

	public String getDiretorioDestinoDatawarehouse() {
		return diretorioDestinoDatawarehouse;
	}

	public String getArquivoDatawarehouse() {
		return arquivoDatawarehouse;
	}

	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(
				"./properties/dados.properties");
		props.load(file);
		return props;

	}
	
}