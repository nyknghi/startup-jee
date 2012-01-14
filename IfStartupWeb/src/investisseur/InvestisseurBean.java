/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package investisseur;

public class InvestisseurBean{
    String nom;
    String email;
    String pwd;

    public InvestisseurBean(String n, String e, String p){
    	nom = n;
    	email = e;
    	pwd = p;
    }

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}

