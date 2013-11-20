package weka.leitor;

import java.util.Comparator;

public class TuplaComparator implements Comparator<Tupla>{


	@Override
	public int compare(Tupla o1, Tupla o2) {
		Tupla t1 = o1;
		Tupla t2 = o2;
		if (t1.qtd_registros == t2.qtd_registros) {
			return t1.qtd_marcadores > t2.qtd_marcadores ? -1 : (t1.qtd_marcadores < t2.qtd_marcadores ? +1 : 0); 
		}
		else
		return t1.qtd_registros > t2.qtd_registros ? -1 : (t1.qtd_registros < t2.qtd_registros ? +1 : 0); 
	}

}
