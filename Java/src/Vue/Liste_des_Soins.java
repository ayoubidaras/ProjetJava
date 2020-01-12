/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;


import Modele.AjoutSoin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.proteanit.sql.DbUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 *
 * @author olivier
 */
public class Liste_des_Soins extends javax.swing.JInternalFrame {
static Connection conn = null;
    ResultSet rs = null ;
    PreparedStatement ps = null;
    static String test;
    /**
     * Creates new form Liste_des_Soins
     * @param connexion
     */
    public Liste_des_Soins(Connection connexion) {
        Liste_des_Soins.conn = connexion;
        initComponents();
        remove_title_bar();
        //conn = connexionDB.start();
        Affichage();
    }
    
     private void remove_title_bar(){
        putClientProperty("Liste_des_Soins.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
    }
     
      private void Affichage(){
       try{
           String requete;
          
                requete = "select  no_malade,no_docteur from SOIGNE";
            
           
           ps = conn.prepareStatement(requete);
           rs = ps.executeQuery();
           Table_soin.setModel(DbUtils.resultSetToTableModel(rs));
           
       }catch(SQLException e){
           System.out.println("Exeption" + e);
       }
    }
      
       public void deplacement()
         {
             try{
                int row = Table_soin.getSelectedRow();
                Liste_des_Soins.test = (Table_soin.getModel().getValueAt(row,Table_soin.getColumn("no_malade").getModelIndex()).toString());
                String requete = "select * from SOIGNE, MALADE where no_malade = '"+test+"' and no_malade = numero";
                ps = conn.prepareStatement(requete);
                rs = ps.executeQuery();
                
                if(rs.next()){
                    String t1 = rs.getString("numero");
                    txt_numero.setText(t1);
                    String t2 = rs.getString("prenom");
                    txt_prenom.setText(t2);
                    String t3 = rs.getString("nom");
                    txt_nom.setText(t3);
                    
                }
                
                row = Table_soin.getSelectedRow();
                Liste_des_Soins.test = (Table_soin.getModel().getValueAt(row,Table_soin.getColumn("no_docteur").getModelIndex()).toString());
                
                requete ="select * from SOIGNE , DOCTEUR D, EMPLOYE E where no_docteur = '"+test+"' and no_docteur = D.numero and E.numero = D.numero";
                ps = conn.prepareStatement(requete);
                rs = ps.executeQuery();
                
                if(rs.next()){
                        
                    String t1 = rs.getString("specialite");
                    txt_spe.setText(t1);
                    String t2 = rs.getString("nom");
                    txt_doc.setText(t2); 
                }
                
             }catch(SQLException e)
             {
                 System.out.println(e);
             }
         }
       
        public void search()
    {
        if(radio_pat_nom.isSelected())
            try{
                String requete1 = "select no_malade, M.nom, no_docteur, E.nom from SOIGNE S, MALADE M, DOCTEUR D, EMPLOYE E where D.numero =E.numero and D.numero = no_docteur and M.numero = no_malade and M.nom LIKE ? order by M.nom";
                ps = conn.prepareStatement(requete1); 
                ps.setString(1, "%"+txt_search.getText()+"%");
                rs =ps.executeQuery();
                Table_soin.setModel(DbUtils.resultSetToTableModel(rs));
            
       }catch(SQLException e){
           System.out.println(e);
       }
       
       
      if(radio_pat.isSelected())
        try{
             String requete2 = "select no_malade, M.nom, no_docteur, E.nom from SOIGNE S, MALADE M, DOCTEUR D , EMPLOYE E where E.numero = D.numero and D.numero = no_docteur and M.numero = no_malade and no_malade LIKE ? order by no_malade";
             ps = conn.prepareStatement(requete2); 
             ps.setString(1,"%"+ txt_search.getText()+"%");
             rs = ps.executeQuery();


             Table_soin.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(SQLException e){
            System.out.println(e);
        }
      
      if(radio_doc.isSelected())
          try{
             String requete2 =  "select no_malade, M.nom, no_docteur, E.nom from SOIGNE S, MALADE M, DOCTEUR D , EMPLOYE E where E.numero = D.numero and D.numero = no_docteur and M.numero = no_malade and no_docteur LIKE ? order by no_docteur";
             ps = conn.prepareStatement(requete2); 
             ps.setString(1,"%"+ txt_search.getText()+"%");
             rs = ps.executeQuery();


             Table_soin.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(SQLException e){
            System.out.println(e);
        }
      
       if(radio_doc_nom.isSelected())
          try{
             String requete2 =  "select no_malade, M.nom, no_docteur, E.nom from SOIGNE S, MALADE M, DOCTEUR D , EMPLOYE E where E.numero = D.numero and D.numero = no_docteur and M.numero = no_malade and E.nom LIKE ? order by E.nom";
             ps = conn.prepareStatement(requete2); 
             ps.setString(1,"%"+ txt_search.getText()+"%");
             rs = ps.executeQuery();

             Table_soin.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(SQLException e){
            System.out.println(e);
        }
         if(radio_spe.isSelected())
          try{
             String requete2 =  "select no_malade, M.nom, no_docteur, E.nom , D.specialite from SOIGNE S, MALADE M, DOCTEUR D  , EMPLOYE E where E.numero = D.numero and D.numero = no_docteur and M.numero = no_malade and D.specialite LIKE ? order by D.specialite";
             ps = conn.prepareStatement(requete2); 
             ps.setString(1,"%"+ txt_search.getText()+"%");
             rs = ps.executeQuery();

             Table_soin.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
        public String gettableresult()
    {
        return test;
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
        radio_pat = new javax.swing.JRadioButton();
        radio_pat_nom = new javax.swing.JRadioButton();
        radio_doc = new javax.swing.JRadioButton();
        radio_doc_nom = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_soin = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_nom = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_prenom = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_numero = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_doc = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_spe = new javax.swing.JTextField();
        btn_suppr = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        radio_spe = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 204));
        setPreferredSize(new java.awt.Dimension(1248, 492));

        radio_pat.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_pat);
        radio_pat.setSelected(true);
        radio_pat.setText("n° Patient");

        radio_pat_nom.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_pat_nom);
        radio_pat_nom.setText("Nom patient");

        radio_doc.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_doc);
        radio_doc.setText("n° Doc");

        radio_doc_nom.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_doc_nom);
        radio_doc_nom.setText("Nom Doc");

        jButton1.setBackground(new java.awt.Color(255, 153, 51));
        jButton1.setText("Soigner");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Table_soin.setBackground(new java.awt.Color(153, 153, 153));
        Table_soin.setModel(new javax.swing.table.DefaultTableModel(
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
        Table_soin.setSelectionForeground(new java.awt.Color(153, 153, 153));
        Table_soin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_soinMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Table_soin);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Nom :");

        txt_nom.setEditable(false);
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

        jLabel7.setText("Docteur attitré :");

        txt_doc.setEditable(false);
        txt_doc.setBorder(null);

        jLabel8.setText("Spécialité :");

        txt_spe.setEditable(false);
        txt_spe.setBorder(null);

        btn_suppr.setBackground(new java.awt.Color(255, 153, 153));
        btn_suppr.setText("Supprimer");
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
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_prenom, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(11, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txt_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_spe, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_suppr, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_doc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_spe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(btn_suppr)
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Recherche :");

        txt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchActionPerformed(evt);
            }
        });
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

        radio_spe.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_spe);
        radio_spe.setText("Spécialité");

        jButton2.setBackground(new java.awt.Color(255, 102, 153));
        jButton2.setText("Patients / Doc");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radio_pat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio_pat_nom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio_doc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio_doc_nom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio_spe)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(124, 124, 124))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(205, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search)
                    .addComponent(radio_pat)
                    .addComponent(radio_pat_nom)
                    .addComponent(radio_doc)
                    .addComponent(radio_doc_nom)
                    .addComponent(jButton1)
                    .addComponent(radio_spe))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AjoutSoin soi = new AjoutSoin(conn);
        soi.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Table_soinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_soinMouseClicked
        deplacement();
    }//GEN-LAST:event_Table_soinMouseClicked

    private void txt_nomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nomActionPerformed

    private void btn_supprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_supprActionPerformed
         try{
             
             int row = Table_soin.getSelectedRow();
             String var = (Table_soin.getModel().getValueAt(row,Table_soin.getColumn("no_docteur").getModelIndex()).toString());
             String var2= (Table_soin.getModel().getValueAt(row,Table_soin.getColumn("no_malade").getModelIndex()).toString());

            String requete2 = "delete from SOIGNE where no_docteur ='"+var+"' and no_malade = '"+var2+"'";
            if(JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir le supprimer ?",
                "Supprimer", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION)

        {
            ps = conn.prepareStatement(requete2);
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

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         String[] docteurs = null;
        int[] nombrePatients = null;
        int nbLignes = 0;
        try{
            String requete = "select E.prenom, E.nom, count(S.no_malade) from EMPLOYE E, SOIGNE S where E.numero = S.no_docteur group by no_docteur";
            ps = conn.prepareStatement(requete);
            rs = ps.executeQuery();
            rs.last();
            nbLignes = rs.getRow();
            docteurs = new String[nbLignes];
            nombrePatients = new int[nbLignes];
            rs.first();
            for (int i=0; i<nbLignes; i++){
                docteurs[i] = rs.getString(1)+" "+rs.getString(2);
                nombrePatients[i] = rs.getInt(3);
                rs.next();
            }


             }catch(SQLException e)
             {
                 System.out.println(e);
             }




        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i<nbLignes;i++){

            dataset.addValue(nombrePatients[i], docteurs[i]+"("+nombrePatients[i]+")", docteurs[i]+"("+nombrePatients[i]+")");
        }

        JFreeChart chart1 = ChartFactory.createBarChart("Nombres de patients pour chaque docteur", "Docteurs", "Nombre de patients", dataset, PlotOrientation.VERTICAL, true,true,false);

        //P.setForegroundAlpha(TOP_ALIGNMENT);
        ChartFrame frame1 = new ChartFrame("Nombres de patients pour chaque docteur", chart1);
        frame1.setVisible(true);
        frame1.setSize(1200,700);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Table_soin;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_suppr;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton radio_doc;
    private javax.swing.JRadioButton radio_doc_nom;
    private javax.swing.JRadioButton radio_pat;
    private javax.swing.JRadioButton radio_pat_nom;
    private javax.swing.JRadioButton radio_spe;
    private javax.swing.JTextField txt_doc;
    private javax.swing.JTextField txt_nom;
    private javax.swing.JTextField txt_numero;
    private javax.swing.JTextField txt_prenom;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_spe;
    // End of variables declaration//GEN-END:variables
}
