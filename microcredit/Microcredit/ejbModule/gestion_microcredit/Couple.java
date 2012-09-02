package gestion_microcredit;

import java.io.Serializable;
import javax.persistence.*;

@SuppressWarnings("serial")
public class Couple<A, B> implements Serializable{
	private A objetA;
	private B objetB;
	
	public Couple (A objetA, B objetB){
		this.objetA = objetA;
		this.objetB = objetB;
	}

	public A getObjetA() {
		return objetA;
	}

	public void setObjetA(A objetA) {
		this.objetA = objetA;
	}

	public B getObjetB() {
		return objetB;
	}

	public void setObjetB(B objetB) {
		this.objetB = objetB;
	}
}

