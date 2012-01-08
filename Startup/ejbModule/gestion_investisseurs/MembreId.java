package gestion_investisseurs;

import java.io.Serializable;

public class MembreId implements Serializable{

	private static final long serialVersionUID = 1L;

	private long idBA;
	private long idClub;
	
	public MembreId(){}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idBA ^ (idBA >>> 32));
		result = prime * result + (int) (idClub ^ (idClub >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MembreId other = (MembreId) obj;
		if (idBA != other.idBA)
			return false;
		if (idClub != other.idClub)
			return false;
		return true;
	}
}
