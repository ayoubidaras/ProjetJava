/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controle.connexionDB;
import static Vue.List_des_employes.test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author olivier
 */
public class ModifInfirmier extends javax.swing.JFrame {

    Connection conn = null;
    ResultSet rs = null ;
    PreparedStatement ps = null;
    /** Creates new form AjoutInfirmier
     * @param test */
    public ModifInfirmier(String test) {
        initComponents();
        conn = connexionDB.start();
        this.recuperer(test);
        
    }
    
    private void recuperer(String test){
       /* System.out.println("A");
        List_des_employes inf = new List_des_employes();
        System.out.println("B");
        inf.deplacement();
        System.out.println("C");*/
        try{
            
            String rec = test;
            String requete = "select * from EMPLOYE E, INFIRMIER I where E.numero = I.numero and I.numero ='"+rec+"'";
            
            ps =conn.prepareStatement(requete);
            rs = ps.executeQuery();
            if(rs.next()){
                String t1 = rs.getString("numero");
                txt_num.setText(t1);
                String t2 = rs.getString("prenom");
                txt_prenom.setText(t2);
                String t3 = rs.getString("nom");
                txt_nom.setText(t3);
                String t4 = rs.getString("tel");
                txt_tel.setText(t4);
                String t5 = rs.getString("adresse");
                txt_adresse.setText(t5);
                String t6 = rs.getString("code_service");
                txt_service.setText(t6);
                String t7 = rs.getString("salaire");
                txt_salaire.setText(t7);
                String t8 = rs.getString("rotation");
                
                if(t8 == "Jour")
                    radio_jour.isSelected();
                else
                    radio_nuit.isSelected();
                
                
                
            }
            
        }catch(SQLException e){
            System.out.println("Exeption" + e);
        }
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_adresse = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_service = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        radio_jour = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        radio_nuit = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btn_modif = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_num = new javax.swing.JTextField();
        txt_salaire = new javax.swing.JTextField();
        txt_prenom = new javax.swing.JTextField();
        txt_nom = new javax.swing.JTextField();
        txt_tel = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("Prenom :");

        jLabel3.setText("Numéro :");

        radio_jour.setText("Jour");

        jLabel4.setText("Téléphone :");

        radio_nuit.setText("Nuit");

        jLabel5.setText("Adresse :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Infirmier(e) :");

        jLabel6.setText("Code de service :");

        btn_modif.setText("Modifier");
        btn_modif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modifActionPerformed(evt);
            }
        });

        jLabel7.setText("Rotation :");

        jLabel9.setText("Salaire :");

        txt_num.setEditable(false);
        txt_num.setBackground(new java.awt.Color(240, 240, 240));
        txt_num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_numActionPerformed(evt);
            }
        });

        jLabel1.setText("Nom :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_adresse, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_num, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(25, 25, 25)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_prenom)
                                            .addComponent(txt_nom))))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_salaire, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_service, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radio_jour)
                                    .addComponent(radio_nuit)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(btn_modif, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txt_salaire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_prenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_adresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_service, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(radio_jour))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radio_nuit)
                .addGap(30, 30, 30)
                .addComponent(btn_modif, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_modifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modifActionPerformed
        
            
            String t1 = txt_num.getText();
            String t2 = txt_prenom.getText();
            String t3 = txt_nom.getText();
            String t4 = txt_tel.getText();
            String t5 = txt_adresse.getText();
            
            String requete = "update EMPLOYE set numero = '"+t1+"', prenom ='"+t2+"', nom ='"+t3+"', tel ='"+t4+"', adresse ='"+t5+"' where numero = '"+t1+"'";
            try{
            ps = conn.prepareStatement(requete);
            ps.execute();

        }catch(SQLException e){
            System.out.println("Exeption 1a" + e);
        }finally{
            try{
                ps.close();
            }catch(SQLException e){
                System.out.println("Exeption 1b" + e);
            }
        } 
            try{
            String t6 = txt_service.getText();
            String t7 = txt_salaire.getText();
            
            String t8;
            if(radio_jour.isSelected())
                t8 = "Jour";
            else
                t8 ="Nuit";
            
            String requete2 = "update INFIRMIER set numero = '"+t1+"', code_service= '"+t6+"',salaire= '"+t7+"', rotation= '"+t8+"' where numero = '"+t1+"'";
            
            ps = conn.prepareStatement(requete2);
            ps.execute();

            JOptionPane.showMessageDialog(null,"Modifié avec succès");
            dispose();
        }catch(SQLException e){
            System.out.println("Exeption 2a" + e);
        }finally{
            try{
                ps.close();
                rs.close();
            }catch(SQLException e){
                System.out.println("Exeption 2b" + e);
            }}
    }//GEN-LAST:event_btn_modifActionPerformed

    private void txt_numActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_numActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_numActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ModifInfirmier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModifInfirmier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModifInfirmier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModifInfirmier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ModifInfirmier(test).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_modif;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton radio_jour;
    private javax.swing.JRadioButton radio_nuit;
    private javax.swing.JTextField txt_adresse;
    private javax.swing.JTextField txt_nom;
    private javax.swing.JTextField txt_num;
    private javax.swing.JTextField txt_prenom;
    private javax.swing.JTextField txt_salaire;
    private javax.swing.JTextField txt_service;
    private javax.swing.JTextField txt_tel;
    // End of variables declaration//GEN-END:variables
}
