package startup.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejb.entity.Fondateur;
import ejb.session.InvestisseursRemote;

public class Main {
	public static void main(String[] args) {
		try {
			Properties props = new Properties();
			try {
				props.load(new FileInputStream("jndi.properties"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
		}
		//System.out.println(props.toString());
		
		Context ctx = new InitialContext();
	    
		InvestisseursRemote g_inv = (InvestisseursRemote) ctx.lookup("InvestisseursBean/remote");
		 
		Fondateur f = new Fondateur("Dupont", "dupont@gmail.com", "123456");
		g_inv.ajouterFondateur(f);
    	
		System.out.println(g_inv.testSession());
		
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}

