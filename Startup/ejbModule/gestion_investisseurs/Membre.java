package gestion_investisseurs;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="Membre")
@IdClass(MembreId.class)
@NamedQuery(name="findMembreById", query="SELECT m FROM Membre as m WHERE m.idBA = :idBA AND m.idClub = :idClub")

public class Membre implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private long idBA;
	@Id
	private long idClub;
	@ManyToOne
	@PrimaryKeyJoinColumn(name="ba_id", referencedColumnName="idInvestisseur")
	private BusinessAngel businessAngel;
	@ManyToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn(name="clubAmi_id", referencedColumnName="idClub")
	private ClubAmi clubAmi;
	private Date date;
	
	public Membre(){}
	
	public Membre(ClubAmi club, BusinessAngel ba){
		this.clubAmi = club;
		this.businessAngel = ba;
		this.idBA = ba.getIdInvestisseur();
		this.idClub = club.getIdClub();
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

	public long getIdBA() {
		return idBA;
	}

	public void setIdBA(long idBA) {
		this.idBA = idBA;
	}

	public long getIdClub() {
		return idClub;
	}

	public void setIdClub(long idClub) {
		this.idClub = idClub;
	}
	
	
}
