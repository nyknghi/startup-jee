package levees;

public class EtapeLeveeBean extends LeveeBean{

	private String etape;
	
	public EtapeLeveeBean(String i, String d, String s, String e){
		super(i,d,s);
		etape = e;
	}

	public String getEtape() {
		return etape;
	}

	public void setEtape(String etape) {
		this.etape = etape;
	}
}
