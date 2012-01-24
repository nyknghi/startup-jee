package levees;

public class EtapeLeveeBean extends LeveeBean{

	private String etape;
	private double cible;
	private double total;
	
	public EtapeLeveeBean(String i, String d, String s, String e, double c, double t){
		super(i,d,s);
		etape = e;
		cible = c;
		total = t;
	}

	public String getEtape() {
		return etape;
	}

	public void setEtape(String etape) {
		this.etape = etape;
	}

	public double getCible() {
		return cible;
	}

	public void setCible(double cible) {
		this.cible = cible;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
