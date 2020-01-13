/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.ModifService;
import Modele.AjoutService;
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
public class Liste_des_services extends javax.swing.JInternalFrame {

    /**
     * Creates new form Liste_des_services
     */
    static Connection conn = null;
    ResultSet rs = null ;
    PreparedStatement ps = null;
    static String test;
    
    /**
     * Creates new form Liste_des_services
     * @param connexion
     */
     public Liste_des_services(Connection connexion) {
        Liste_des_services.conn = connexion;
        initComponents();
        remove_title_bar();
        //conn = connexionDB.start();
        Affichage();
            
        }
    /**
     * Displays rows from SQL query in a table model 
     */
         private void Affichage(){
       try{
           String requete;
                requete = "select code,E.numero, E.nom, S.nom , batiment  from SERVICE S,EMPLOYE E, DOCTEUR D where D.numero = S.directeur and D.numero = E.numero";
              
           
           ps = conn.prepareStatement(requete);
           rs = ps.executeQuery();
           Table_service.setModel(DbUtils.resultSetToTableModel(rs));
           
          
           
       }catch(SQLException e){
           System.out.println("Exeption" + e);
       }
    }
         
          public String gettableresult()
    {
        return test;
    }
    
	/**
     * Presents on the right side of the panel the values of each column in the database for the selected row on the table in the left side of the panel
     */
          public void deplacement()
         {
             try{
                int row =Table_service.getSelectedRow();
                Liste_des_services.test = (Table_service.getModel().getValueAt(row, 0).toString());
                String requete = "select * from SERVICE S,EMPLOYE E, DOCTEUR D where S.code = '"+test+"' and D.numero = E.numero and directeur = D.numero";
                ps = conn.prepareStatement(requete);
                rs = ps.executeQuery();
                
                if(rs.next()){
                     
                    String t1 = rs.getString("code");
                    txt_code.setText(t1);
                    String t2 = rs.getString("nom");
                    txt_nom.setText(t2);
                    String t3 = rs.getString("batiment");
                    txt_bat.setText(t3);
                    String t4 = rs.getString("E.nom");
                    txt_dir.setText(t4);
                    
                    
                }
             }catch(SQLException e)
             {
                 System.out.println(e);
             }finally{
                 try{
                 rs.close();
                 ps.close();
                 }catch(SQLException e)
             {
                 System.out.println(e);
             }
             }
         }
    
	/**
     * Searches for data throughout the database based on one of the given parameters
     */
          public void search()
    {
        if(radio_code.isSelected())
            try{
                String requete1 = "select code,E.numero, S.nom , batiment  from SERVICE S,EMPLOYE E, DOCTEUR D where D.numero = S.directeur and D.numero = E.numero and code LIKE ?";
                ps = conn.prepareStatement(requete1); 
                ps.setString(1, "%"+txt_search.getText()+"%");
                rs =ps.executeQuery();
                Table_service.setModel(DbUtils.resultSetToTableModel(rs));
            
       }catch(SQLException e){
           System.out.println(e);
       }
       
       
      if(radio_nom.isSelected())
        try{
             String requete2 = "select code,E.numero, S.nom , batiment  from SERVICE S,EMPLOYE E, DOCTEUR D where D.numero = S.directeur and D.numero = E.numero and S.nom LIKE ?";
             ps = conn.prepareStatement(requete2); 
             ps.setString(1,"%"+ txt_search.getText()+"%");
             rs = ps.executeQuery();


             Table_service.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(SQLException e){
            System.out.println(e);
        }
      
      if(radio_bat.isSelected())
          try{
             String requete2 = "select code,E.numero, S.nom , batiment  from SERVICE S,EMPLOYE E, DOCTEUR D where D.numero = S.directeur and D.numero = E.numero and batiment LIKE ?";
             ps = conn.prepareStatement(requete2); 
             ps.setString(1,"%"+ txt_search.getText()+"%");
             rs = ps.executeQuery();


             Table_service.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(SQLException e){
            System.out.println(e);
        }
      
       if(radio_dir.isSelected())
          try{
             String requete2 = "select code,E.numero, S.nom , batiment  from SERVICE S,EMPLOYE E, DOCTEUR D where D.numero = S.directeur and D.numero = E.numero and directeur LIKE ?";;
             ps = conn.prepareStatement(requete2); 
             ps.setString(1,"%"+ txt_search.getText()+"%");
             rs = ps.executeQuery();

             Table_service.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(SQLException e){
            System.out.println(e);
        }
       
       if(radio_nom_dir.isSelected())
          try{
             String requete2 = "select code,E.numero, E.nom, S.nom , batiment  from SERVICE S,EMPLOYE E, DOCTEUR D where D.numero = S.directeur and D.numero = E.numero and E.nom LIKE ?";;
             ps = conn.prepareStatement(requete2); 
             ps.setString(1,"%"+ txt_search.getText()+"%");
             rs = ps.executeQuery();

             Table_service.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(SQLException e){
            System.out.println(e);
        }
    }
        
      
         
     
    /**
     * Removes the title bar 
     */
    private void remove_title_bar(){
        putClientProperty("Liste_des_services.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
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
        Add = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_nom = new javax.swing.JTextField();
        txt_bat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_code = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_dir = new javax.swing.JTextField();
        btn_modif = new javax.swing.JButton();
        btn_suppr = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_service = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        radio_code = new javax.swing.JRadioButton();
        radio_nom = new javax.swing.JRadioButton();
        radio_bat = new javax.swing.JRadioButton();
        radio_dir = new javax.swing.JRadioButton();
        radio_nom_dir = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(204, 204, 204));
        setBorder(null);
        setPreferredSize(new java.awt.Dimension(1248, 492));

        Add.setBackground(new java.awt.Color(255, 153, 51));
        Add.setText("Ajout Service");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("Batiment :");

        txt_nom.setEditable(false);
        txt_nom.setBorder(null);
        txt_nom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nomActionPerformed(evt);
            }
        });

        txt_bat.setEditable(false);
        txt_bat.setBorder(null);

        jLabel5.setText("Directeur :");

        txt_code.setEditable(false);
        txt_code.setBorder(null);

        jLabel2.setText("Nom :");

        jLabel7.setText("Code :");

        txt_dir.setEditable(false);
        txt_dir.setBorder(null);

        btn_modif.setBackground(new java.awt.Color(204, 204, 204));
        btn_modif.setText("Modifier");
        btn_modif.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_modif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modifActionPerformed(evt);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_code, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_bat, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(txt_dir, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_modif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(btn_suppr, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_bat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_dir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_modif, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_suppr)
                .addGap(42, 42, 42))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Table_service.setBackground(new java.awt.Color(153, 153, 153));
        Table_service.setModel(new javax.swing.table.DefaultTableModel(
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
        Table_service.setMaximumSize(new java.awt.Dimension(736, 366));
        Table_service.setMinimumSize(new java.awt.Dimension(736, 366));
        Table_service.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_serviceMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Table_service);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Recherche :");

        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });

        btn_search.setBackground(new java.awt.Color(204, 204, 204));
        btn_search.setText("recherche");
        btn_search.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        radio_code.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_code);
        radio_code.setText("Code ");

        radio_nom.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_nom);
        radio_nom.setText("Nom");

        radio_bat.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_bat);
        radio_bat.setText("Batiment");

        radio_dir.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_dir);
        radio_dir.setSelected(true);
        radio_dir.setText("N°Directeur");

        radio_nom_dir.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_nom_dir);
        radio_nom_dir.setText("Directeur");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radio_code)
                .addGap(18, 18, 18)
                .addComponent(radio_nom)
                .addGap(18, 18, 18)
                .addComponent(radio_bat)
                .addGap(18, 18, 18)
                .addComponent(radio_dir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radio_nom_dir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Add, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 326, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 3, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search)
                    .addComponent(radio_code)
                    .addComponent(radio_nom)
                    .addComponent(radio_bat)
                    .addComponent(radio_dir)
                    .addComponent(radio_nom_dir)
                    .addComponent(Add))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	/**
     * Executes the data insertion method 
     */
    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        AjoutService ser = new AjoutService(conn);
        ser.setVisible(true);
    }//GEN-LAST:event_AddActionPerformed

    private void txt_nomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nomActionPerformed

	/**
     * Executes the data modification method for the selected row
     */
    private void btn_modifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modifActionPerformed
        ModifService ser = new ModifService(test, conn);
        ser.setVisible(true);
    }//GEN-LAST:event_btn_modifActionPerformed

	/**
     * Executes the data deletion method for the selected row
     */
    private void btn_supprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_supprActionPerformed
		try{

            String requete = "delete from DOCTEUR where numero ='"+gettableresult()+"'";
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
    }//GEN-LAST:event_btn_supprActionPerformed

	/**
     * Executes "deplacement()" method when a row is selected
     */
    private void Table_serviceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_serviceMouseClicked
        deplacement();
    }//GEN-LAST:event_Table_serviceMouseClicked

	/**
     * Executes the search algorithm
     */
    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        search();
    }//GEN-LAST:event_txt_searchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JTable Table_service;
    private javax.swing.JButton btn_modif;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_suppr;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton radio_bat;
    private javax.swing.JRadioButton radio_code;
    private javax.swing.JRadioButton radio_dir;
    private javax.swing.JRadioButton radio_nom;
    private javax.swing.JRadioButton radio_nom_dir;
    private javax.swing.JTextField txt_bat;
    private javax.swing.JTextField txt_code;
    private javax.swing.JTextField txt_dir;
    private javax.swing.JTextField txt_nom;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
