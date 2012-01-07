package gestion_investisseurs;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(schema="IfStartup", name="Membre", uniqueConstraints={@UniqueConstraint(columnNames={"clubAmi","businessAngel"})})
public class Membre {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idMembre;
	private Date date;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="clubAmi_id", referencedColumnName="idClub")
	private ClubAmi clubAmi;
	@ManyToOne
	@JoinColumn(name="ba_id", referencedColumnName="idInvestisseur")
	private BusinessAngel businessAngel;
	
	public Membre(ClubAmi club, BusinessAngel ba){
		this.clubAmi = club;
		this.businessAngel = ba;
	}

	public int getIdMembre() {
		return idMembre;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ClubAmi getClubAmi() {
		return clubAmi;
	}

	public void setClubAmi(ClubAmi clubAmi) {
		this.clubAmi = clubAmi;
	}

	public BusinessAngel getBusinessAngel() {
		return businessAngel;
	}

	public void setBusinessAngel(BusinessAngel businessAngel) {
		this.businessAngel = businessAngel;
	}
	
	
	
}
