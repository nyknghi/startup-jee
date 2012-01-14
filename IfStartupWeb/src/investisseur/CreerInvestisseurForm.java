package investisseur;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class CreerInvestisseurForm extends org.apache.struts.action.ActionForm{
    
    private String nom;
    private String email;
    private String password;
    private String type;
    private ArrayList<InvestisseurBean> list = new ArrayList<InvestisseurBean>();

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<InvestisseurBean> getList() {
        return list;
    }

    public void setList(ArrayList<InvestisseurBean> list) {
        this.list = list;
    }
    
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if(nom==null || nom.isEmpty()){
            errors.add("nom", new ActionMessage("Specifiez le nom de l'investisseur"));
        }else if(email==null || email.isEmpty()){
            errors.add("email", new ActionMessage("Specifiez l'adresse mail"));
        }else if(password==null || password.isEmpty()){
            errors.add("mdp", new ActionMessage("Le mot de passe est obligatoire"));
        }
        return errors;
    }
}
