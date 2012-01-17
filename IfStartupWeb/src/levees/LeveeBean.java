/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package levees;

/**
 *
 * @author UTILISATEUR
 */
public class LeveeBean {
    private String idLevee;
    private String date;
    private String startup;

    
    public LeveeBean(String i, String d, String s){
        idLevee = i;
        date = d;
        startup = s;
    }

    public String getIdLevee() {
        return idLevee;
    }

    public void setIdLevee(String idLevee) {
        this.idLevee = idLevee;
    }
    
    public String getDate() {
        return date;
    }

    public String getStartup() {
        return startup;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStartup(String startup) {
        this.startup = startup;
    }
}
