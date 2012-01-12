package com.vaannila;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class OrganiserLeveeForm {
	private String nom;
	private String date;
	private String cible;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCible() {
		return cible;
	}
	public void setCible(String cible) {
		this.cible = cible;
	}
	
	
}
