package Vue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame {
    private JPanel LoginPanel;
    private JButton btn_connexion;

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
         frame.setContentPane(new LoginFrame().panelMain);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.pack();
         frame.setVisible(true);
    }
}
