package Vue;


import javax.swing.*;

import Controle.connexionDB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginFrame {
    private JPanel LoginPanel;
    private JButton btn_connexion;
    static Connection conn = null;

    public LoginFrame() {
        btn_connexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null,"Hello World !");
            }
        });
    }

    public static void main(String[] args){
         JFrame frame = new JFrame("LoginFrame");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.pack();
         frame.setVisible(true);
         conn = connexionDB.start();
         
    }
}
