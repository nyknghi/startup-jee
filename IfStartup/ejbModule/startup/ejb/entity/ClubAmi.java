package startup.ejb.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="IFStartupBD", name="ClubAmi")
public class ClubAmi {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int idClub;
	@Basic(optional=false)
	private String nomClub;
	
	public ClubAmi(){}
	public ClubAmi(String nom){
		this.nomClub = nom;
	}
	
}
