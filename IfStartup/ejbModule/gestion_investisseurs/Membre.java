package gestion_investisseurs;


import java.util.Date;

public class Membre {
	private int idMembre;
	private Date date;
	private ClubAmi clubAmi;
	private BusinessAngel businessAngel;
	
	public Membre(ClubAmi club, BusinessAngel ba){
		this.clubAmi = club;
		this.businessAngel = ba;
	}
}
