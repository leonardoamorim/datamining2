package br.com.weka.comparator;

import java.util.Comparator;

import br.com.weka.model.Tupla;

public class TuplaComparator implements Comparator<Tupla>{


	@Override
	public int compare(Tupla o1, Tupla o2) {
		Tupla t1 = o1;
		Tupla t2 = o2;
		if (t1.getQtdRegistros() == t2.getQtdRegistros()) {
			return t1.getQtdMarcadores() > t2.getQtdMarcadores() ? -1 : (t1.getQtdMarcadores() < t2.getQtdMarcadores() ? +1 : 0); 
		}
		else
		return t1.getQtdRegistros() > t2.getQtdRegistros() ? -1 : (t1.getQtdRegistros() < t2.getQtdRegistros() ? +1 : 0); 
	}

}
