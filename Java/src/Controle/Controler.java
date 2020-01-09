package Controle;

import Vue.LoginGUI;
import java.sql.Connection;

public class Controler {
    
    
    Connection conn = null;
	
	//private LoginGUI l_gui = null;
	
	
	public Controler(String[] args)
	{
      
            LoginGUI loginGUI;
            loginGUI = new LoginGUI(conn);
       
    }

	public static void main(String[] args) 
	{
            Controler controler = new Controler(args);
	}

}
