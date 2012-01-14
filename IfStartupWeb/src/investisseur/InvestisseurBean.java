/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package investisseur;

/**
 *
 * @author UTILISATEUR
 */
public class InvestisseurBean {
    private String nom;
    private String email;
    private String password;
    
    public InvestisseurBean(String n, String e, String p){
        nom = n;
        email = e;
        password = p;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getNom() {
        return nom;
    }

    public String getPassword() {
        return password;
    }
}
