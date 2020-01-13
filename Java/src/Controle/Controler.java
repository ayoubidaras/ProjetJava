package Controle;

import java.sql.Connection;

public class Controler {
    
    
    Connection conn = null;
	
	//private LoginGUI l_gui = null;
	
	/**
     * Launches the connection frame in order to enable the user to connect to the system
     */
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
