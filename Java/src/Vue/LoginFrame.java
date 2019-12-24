package Vue;


import javax.swing.*;

import com.sun.xml.internal.ws.api.server.Container;

import Controle.Controler;
import Controle.connexionDB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.sql.*;

public class LoginFrame extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btn_connexion;
    static Connection conn = null;

    public LoginFrame(Controler controle) {
    	
    	super("LoginFrame");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(100,0);
        setSize(1080,1200);
        setMinimumSize(new Dimension(700,400));
    	
    	//init();
    	
    	java.awt.Container contentPage = getContentPane();
    	
		
		
		JPanel connectionPlace = new JPanel(new GridLayout(2,2));
		contentPage.add(connectionPlace);
    	
		btn_connexion = new JButton("Se connecter");
        btn_connexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null,"Hello World !");
                conn = connexionDB.start();
            }
        });
        
        connectionPlace.add(btn_connexion);
        
        setVisible(true);
    }

    public static void init(){
         
         
        // conn = connexionDB.start();
         
    }
}
