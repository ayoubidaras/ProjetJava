package Controle;

import Vue.LoginGUI;

public class Controler {
	
	private LoginGUI l_gui = null;
	
	
	public Controler(String[] args)
	{
      
		l_gui = new LoginGUI();
       
    }

	public static void main(String[] args) 
	{
		new Controler(args);
	}

}
