package ba;

import java.util.ArrayList;

public class ParticiperClubForm extends org.apache.struts.action.ActionForm {
	private String club;
	private ArrayList clubList;
	private Boolean checkboxValue;
	
	public String getClub() {
		return club;
	}
	public void setClub(String club) {
		this.club = club;
	}
	public ArrayList getClubList() {
		return clubList;
	}
	public void setClubList(ArrayList clubList) {
		this.clubList = clubList;
	}
	public Boolean getCheckboxValue() {
		return checkboxValue;
	}
	public void setCheckboxValue(Boolean checkboxValue) {
		this.checkboxValue = checkboxValue;
	}	
}
