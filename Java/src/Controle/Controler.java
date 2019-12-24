package Controle;

import Vue.LoginFrame;

public class Controler {
	
	private LoginFrame l_gui = null;
	
	
	public Controler(String[] args)
	{
      
		l_gui = new LoginFrame(this);
       
    }

	public static void main(String[] args) 
	{
		new Controler(args);
	}

}
