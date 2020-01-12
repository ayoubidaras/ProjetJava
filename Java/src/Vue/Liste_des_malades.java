/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.ModifMalade;
import Modele.AjoutMalade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author olivier
 */
public class Liste_des_malades extends javax.swing.JInternalFrame {

    static Connection conn = null;
    ResultSet rs = null ;
    PreparedStatement ps = null;
    static String test;
    
   
     public Liste_des_malades(Connection connexion) {
         Liste_des_malades.conn = connexion;
        initComponents();
        remove_title_bar();
        //conn = connexionDB.start();
        Affichage();
            
        }
     
         private void Affichage(){
       try{
           String requete;
          
                requete = "select numero, prenom, nom from MALADE";
            
           
           ps = conn.prepareStatement(requete);
           rs = ps.executeQuery();
           Table_mal.setModel(DbUtils.resultSetToTableModel(rs));
           
       }catch(SQLException e){
           System.out.println("Exeption" + e);
       }
    }
     
    private void remove_title_bar(){
        putClientProperty("Liste_des_malades.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
    }
    
     public String gettableresult()
    {
        return test;
    }
    
     public void deplacement()
         {
             try{
                int row = Table_mal.getSelectedRow();
                Liste_des_malades.test = (Table_mal.getModel().getValueAt(row, 0).toString());
                String requete = "select * from MALADE where numero = '"+test+"'";
                ps = conn.prepareStatement(requete);
                rs = ps.executeQuery();
                
                if(rs.next()){
                    String t1 = rs.getString("numero");
                    txt_numero.setText(t1);
                    String t2 = rs.getString("prenom");
                    txt_prenom.setText(t2);
                    String t3 = rs.getString("nom");
                    txt_nom.setText(t3);
                    String t4 = rs.getString("tel");
                    txt_tel.setText(t4);
                    String t5 = rs.getString("adresse");
                    txt_adresse.setText(t5);
                    String t6 = rs.getString("mutuelle");
                    txt_mutuelle.setText(t6);
                    
                }
                
                requete = "select * from MALADE M, HOSPITALISATION H, SOIGNE S, EMPLOYE E, DOCTEUR D where M.numero ='"+test+"' and M.numero = H.no_malade and H.no_malade = S.no_malade and S.no_docteur = D.numero";
                ps = conn.prepareStatement(requete);
                rs = ps.executeQuery();
                
                if(rs.next()){
                        
                    String t1 = rs.getString("no_chambre");
                    txt_chbr.setText(t1);
                    String t2 = rs.getString("nom");
                    txt_doc.setText(t2); 
                }
                else{
                    txt_chbr.setText("aucune");
                    txt_doc.setText("aucun"); 
                        }
                
             }catch(SQLException e)
             {
                 System.out.println(e);
             }
         }
     
     public void search()
    {
        if(radio_nom.isSelected())
            try{
                String requete1 = "select numero, prenom, nom from MALADE where nom LIKE ? order by nom";
                ps = conn.prepareStatement(requete1); 
                ps.setString(1, "%"+txt_search.getText()+"%");
                rs =ps.executeQuery();
                Table_mal.setModel(DbUtils.resultSetToTableModel(rs));
            
       }catch(SQLException e){
           System.out.println(e);
       }
       
       
      if(radio_num.isSelected())
        try{
             String requete2 = "select numero, prenom, nom from MALADE where numero LIKE ? order by numero";
             ps = conn.prepareStatement(requete2); 
             ps.setString(1,"%"+ txt_search.getText()+"%");
             rs = ps.executeQuery();


             Table_mal.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(SQLException e){
            System.out.println(e);
        }
      
      if(radio_prenom.isSelected())
          try{
             String requete2 = "select numero, prenom, nom from MALADE where prenom LIKE ? order by prenom";
             ps = conn.prepareStatement(requete2); 
             ps.setString(1,"%"+ txt_search.getText()+"%");
             rs = ps.executeQuery();


             Table_mal.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(SQLException e){
            System.out.println(e);
        }
      
       if(radio_mut.isSelected())
          try{
             String requete2 = "select numero, prenom, nom, mutuelle from MALADE where mutuelle LIKE ? order by mutuelle";
             ps = conn.prepareStatement(requete2); 
             ps.setString(1,"%"+ txt_search.getText()+"%");
             rs = ps.executeQuery();

             Table_mal.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(SQLException e){
            System.out.println(e);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_nom = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_prenom = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_numero = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_tel = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_adresse = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_mutuelle = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_doc = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_chbr = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        btn_suppr = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_mal = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        radio_num = new javax.swing.JRadioButton();
        radio_nom = new javax.swing.JRadioButton();
        radio_prenom = new javax.swing.JRadioButton();
        radio_mut = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 204));
        setBorder(null);
        setPreferredSize(new java.awt.Dimension(1248, 492));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Nom :");

        txt_nom.setBackground(new java.awt.Color(240, 240, 240));
        txt_nom.setBorder(null);
        txt_nom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nomActionPerformed(evt);
            }
        });

        jLabel2.setText("Prénom :");

        txt_prenom.setEditable(false);
        txt_prenom.setBorder(null);

        jLabel3.setText("Numero :");

        txt_numero.setEditable(false);
        txt_numero.setBorder(null);

        jLabel4.setText("Numéro de télephone :");

        txt_tel.setEditable(false);
        txt_tel.setBorder(null);

        jLabel5.setText("Adresse :");

        txt_adresse.setEditable(false);
        txt_adresse.setBorder(null);

        jLabel6.setText("Mutuelle :");

        txt_mutuelle.setEditable(false);
        txt_mutuelle.setBorder(null);

        jLabel7.setText("Docteur attitré :");

        txt_doc.setEditable(false);
        txt_doc.setBorder(null);

        jLabel8.setText("Chambre n° :");

        txt_chbr.setEditable(false);
        txt_chbr.setBorder(null);

        jButton3.setBackground(new java.awt.Color(204, 204, 204));
        jButton3.setText("Modifier");
        jButton3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btn_suppr.setBackground(new java.awt.Color(255, 153, 153));
        btn_suppr.setText("Suppression");
        btn_suppr.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_suppr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_supprActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("INFORMATIONS :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_prenom)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(11, 11, 11)
                                .addComponent(txt_adresse, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txt_chbr, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_mutuelle, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(23, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txt_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_suppr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)))
                        .addGap(0, 189, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txt_prenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_adresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_mutuelle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_doc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_chbr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(15, 15, 15)
                .addComponent(btn_suppr)
                .addContainerGap())
        );

        Table_mal.setBackground(new java.awt.Color(153, 153, 153));
        Table_mal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Table_mal.setSelectionForeground(new java.awt.Color(153, 153, 153));
        Table_mal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_malMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Table_mal);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Recherche :");

        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });

        btn_search.setBackground(new java.awt.Color(204, 204, 204));
        btn_search.setText("recherche");
        btn_search.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        radio_num.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_num);
        radio_num.setSelected(true);
        radio_num.setText("Numéro");

        radio_nom.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_nom);
        radio_nom.setText("Nom");

        radio_prenom.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_prenom);
        radio_prenom.setText("Prénom");

        radio_mut.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_mut);
        radio_mut.setText("Mutuelle");

        jButton1.setBackground(new java.awt.Color(255, 153, 51));
        jButton1.setText("Ajouter un patient");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(radio_num)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio_nom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio_prenom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio_mut)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(344, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search)
                    .addComponent(radio_num)
                    .addComponent(radio_nom)
                    .addComponent(radio_prenom)
                    .addComponent(radio_mut)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nomActionPerformed

    private void Table_malMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_malMouseClicked
        deplacement();
    }//GEN-LAST:event_Table_malMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         AjoutMalade mal = new AjoutMalade(conn);
        mal.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ModifMalade mal = new ModifMalade(test,conn);
        mal.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btn_supprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_supprActionPerformed
        try{ 
           
            String requete = "delete from MALADE where numero ='"+gettableresult()+"'";
            System.out.println(test);
            if(JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir le supprimer ?",
                                        "Supprimer", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION)
          
        {
                ps = conn.prepareStatement(requete);
                ps.execute();

                JOptionPane.showMessageDialog(null,"Supprimé avec succès");
                dispose();
          }
       }catch(SQLException e){
           System.out.println(e);
       }
        Affichage();
    }//GEN-LAST:event_btn_supprActionPerformed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        search();
    }//GEN-LAST:event_txt_searchKeyReleased

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
       search();
    }//GEN-LAST:event_btn_searchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Table_mal;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_suppr;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton radio_mut;
    private javax.swing.JRadioButton radio_nom;
    private javax.swing.JRadioButton radio_num;
    private javax.swing.JRadioButton radio_prenom;
    private javax.swing.JTextField txt_adresse;
    private javax.swing.JTextField txt_chbr;
    private javax.swing.JTextField txt_doc;
    private javax.swing.JTextField txt_mutuelle;
    private javax.swing.JTextField txt_nom;
    private javax.swing.JTextField txt_numero;
    private javax.swing.JTextField txt_prenom;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_tel;
    // End of variables declaration//GEN-END:variables
}
