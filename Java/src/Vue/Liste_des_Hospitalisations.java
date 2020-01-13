/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.AjoutHospitalisation;
import Modele.ModifHospitalisation;
import static Vue.List_des_employes.conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JRootPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.proteanit.sql.DbUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author olivier
 */
public class Liste_des_Hospitalisations extends javax.swing.JInternalFrame {

    /**
     * Creates new form Liste_des_Hospitalisations
     */
    static Connection conn = null;
    ResultSet rs = null ;
    PreparedStatement ps = null;
    static String test;
    /**
     * Creates new form Liste_des_Hospitalisation
     * @param connexion
     */
    public Liste_des_Hospitalisations(Connection connexion ){
        Liste_des_Hospitalisations.conn = connexion;
        initComponents();
        remove_title_bar();
        Affichage();
    }
    
    /**
     * Displays rows from SQL query in a table model 
     */
    private void Affichage(){
       try{
          
           String requete;
           requete = "select H.no_malade, H.no_chambre,lit, code_service from HOSPITALISATION H, MALADE M where numero = H.no_malade";
             
           ps = conn.prepareStatement(requete);
           rs = ps.executeQuery();
           Table_hos.setModel(DbUtils.resultSetToTableModel(rs));
           
       }catch(SQLException e){
           System.out.println("Exeption" + e);
       }
    }
    
    /**
     * Removes the title bar 
     */
    private void remove_title_bar(){
      putClientProperty("Liste_des_Hospitalisations.isPalette", Boolean.TRUE);
      getRootPane().setWindowDecorationStyle(JRootPane.NONE);
      ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
      this.setBorder(null);
    }
     /**
     * Presents on the right side of the panel the values of each column in the database for the selected row on the table in the left side of the panel
     */
           public void deplacement()
         {
             try{
                int row = Table_hos.getSelectedRow();
                Liste_des_Hospitalisations.test = (Table_hos.getModel().getValueAt(row, 0).toString());
                String requete = "select * from MALADE where numero = '"+test+"'";
                ps = conn.prepareStatement(requete);
                rs = ps.executeQuery();
                
                if(rs.next()){
                    String t1 = rs.getString("numero");
                    num_pat.setText(t1);
                    String t2 = rs.getString("prenom");
                    txt_prenom.setText(t2);
                    String t3 = rs.getString("nom");
                    txt_nom.setText(t3);
                    String t4 = rs.getString("mutuelle");
                    txt_mut.setText(t4);
                    
                }
                
                requete = "select * from HOSPITALISATION where no_malade ='"+test+"' ";
                ps = conn.prepareStatement(requete);
                rs = ps.executeQuery();
                
                if(rs.next()){
                        
                    String t1 = rs.getString("code_service");
                    txt_code.setText(t1);
                    String t2 = rs.getString("no_chambre");
                    txt_chbr.setText(t2); 
                    String t3 = rs.getString("lit");
                    txt_lit.setText(t3); 
                }
                
             }catch(SQLException e)
             {
                 System.out.println(e);
             }
         }
           
     /**
     * Searches for data throughout the database based on one of the given parameters
     */
           public void search()
    {
        if(radio_pat.isSelected())
            try{
                String requete1 =  "select no_malade, no_chambre, lit from HOSPITALISATION where no_malade LIKE ?";
                ps = conn.prepareStatement(requete1); 
                ps.setString(1, "%"+txt_search.getText()+"%");
                rs =ps.executeQuery();
                Table_hos.setModel(DbUtils.resultSetToTableModel(rs));
            
       }catch(SQLException e){
           System.out.println(e);
       }
       
       
      if(radio_lit.isSelected())
        try{
             String requete2 ="select no_malade, no_chambre, lit from HOSPITALISATION where lit LIKE ?";
             ps = conn.prepareStatement(requete2); 
             ps.setString(1,"%"+ txt_search.getText()+"%");
             rs = ps.executeQuery();


             Table_hos.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(SQLException e){
            System.out.println(e);
        }
      
      if(radio_chbr.isSelected())
          try{
             String requete2 ="select no_malade, no_chambre, lit from HOSPITALISATION where no_chambre LIKE ?";
             ps = conn.prepareStatement(requete2); 
             ps.setString(1,"%"+ txt_search.getText()+"%");
             rs = ps.executeQuery();


             Table_hos.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(SQLException e){
            System.out.println(e);
        }
      
      if(radio_nom.isSelected())
            try{
                String requete1 =  "select no_malade, nom, no_chambre, lit from HOSPITALISATION, MALADE where nom LIKE ? and numero = no_malade";
                ps = conn.prepareStatement(requete1); 
                ps.setString(1, "%"+txt_search.getText()+"%");
                rs =ps.executeQuery();
                Table_hos.setModel(DbUtils.resultSetToTableModel(rs));
            
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
        jButton1 = new javax.swing.JButton();
        radio_lit = new javax.swing.JRadioButton();
        panel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_nom = new javax.swing.JTextField();
        txt_prenom = new javax.swing.JTextField();
        txt_lit = new javax.swing.JTextField();
        num_pat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_chbr = new javax.swing.JTextField();
        btn_modif = new javax.swing.JButton();
        btn_suppr = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_mut = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_code = new javax.swing.JTextField();
        radio_pat = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_hos = new javax.swing.JTable();
        Add = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        radio_nom = new javax.swing.JRadioButton();
        radio_chbr = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 204));
        setPreferredSize(new java.awt.Dimension(1248, 492));

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setText("rechercher");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        radio_lit.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_lit);
        radio_lit.setSelected(true);
        radio_lit.setText("n° lit");

        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("Chambre :");

        jLabel3.setText("Prénom patient :");

        txt_nom.setEditable(false);
        txt_nom.setBorder(null);
        txt_nom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nomActionPerformed(evt);
            }
        });

        txt_prenom.setEditable(false);
        txt_prenom.setBorder(null);

        txt_lit.setEditable(false);
        txt_lit.setBorder(null);

        num_pat.setEditable(false);
        num_pat.setBorder(null);

        jLabel5.setText("Numéro du lit :");

        jLabel6.setText("Numéro du patient :");

        jLabel2.setText("Nom patient :");

        txt_chbr.setEditable(false);
        txt_chbr.setBorder(null);

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

        jLabel8.setText("Mutuelle patient :");

        txt_mut.setEditable(false);
        txt_mut.setBorder(null);

        jLabel9.setText("Code du service :");

        txt_code.setEditable(false);
        txt_code.setBorder(null);
        txt_code.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_codeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_lit))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_chbr, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_code)))
                        .addGap(138, 138, 138))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(num_pat, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_prenom, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_mut, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel10))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(btn_suppr, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addComponent(btn_modif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(55, 55, 55))))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(43, 43, 43)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(num_pat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_prenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_mut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_chbr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_lit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btn_modif, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btn_suppr)
                .addContainerGap())
        );

        radio_pat.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_pat);
        radio_pat.setText("n° patient");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Table_hos.setBackground(new java.awt.Color(153, 153, 153));
        Table_hos.setModel(new javax.swing.table.DefaultTableModel(
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
        Table_hos.setMaximumSize(new java.awt.Dimension(736, 366));
        Table_hos.setMinimumSize(new java.awt.Dimension(736, 366));
        Table_hos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_hosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Table_hos);

        Add.setBackground(new java.awt.Color(255, 153, 51));
        Add.setText("Ajout Hospitalisation");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Rechercher :");

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

        radio_nom.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_nom);
        radio_nom.setText("Nom Patient");

        radio_chbr.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_chbr);
        radio_chbr.setText("Chambre");

        jButton2.setBackground(new java.awt.Color(255, 153, 51));
        jButton2.setText("Chambres / Services");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radio_pat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio_nom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radio_chbr)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radio_lit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Add, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton2))
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(248, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radio_lit)
                    .addComponent(radio_nom)
                    .addComponent(radio_chbr)
                    .addComponent(radio_pat)
                    .addComponent(jButton1)
                    .addComponent(Add)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Executes the search algorithm
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      search();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void txt_nomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nomActionPerformed
    
    /**
     * Executes the data modification method for the selected row
     */
    private void btn_modifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modifActionPerformed
        ModifHospitalisation hos = new ModifHospitalisation(test,conn);
        hos.setVisible(true);
    }//GEN-LAST:event_btn_modifActionPerformed

    /**
     * Executes the data deletion method for the selected row
     */
    private void btn_supprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_supprActionPerformed
        /*  try{

            String requete2 = "delete from DOCTEUR where numero ='"+gettableresult()+"'";
            String requete = "delete from EMPLOYE where numero ='"+gettableresult()+"'";
            System.out.println(test);
            if(JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir le supprimer ?",
                "Supprimer", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION)

        {
            ps = conn.prepareStatement(requete);
            ps.execute();
            ps = conn.prepareStatement(requete2);
            ps.execute();

            JOptionPane.showMessageDialog(null,"Supprimé avec succès");
            dispose();
        }
        }catch(SQLException e){
            System.out.println(e);
        }*/
    }//GEN-LAST:event_btn_supprActionPerformed
    
    /**
     * Executes "deplacement()" method when a row is selected
     */
    private void Table_hosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_hosMouseClicked
       deplacement();
    }//GEN-LAST:event_Table_hosMouseClicked
    
    /**
     * Executes the data insertion method 
     */
    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        AjoutHospitalisation hos = new AjoutHospitalisation(conn);
        hos.setVisible(true);
    }//GEN-LAST:event_AddActionPerformed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
       search();
    }//GEN-LAST:event_txt_searchActionPerformed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
       search();
    }//GEN-LAST:event_txt_searchKeyReleased

    private void txt_codeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_codeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_codeActionPerformed
    
    /**
     * Shows statistics on how many rooms are available for each medical unit if this medical unit has any
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String[] codeService = null;
        int[] nombreChambre = null;
        int nbLignes = 0;
        try{
            String requete = "select code_service, count(no_chambre) from HOSPITALISATION group by code_service";
            ps = conn.prepareStatement(requete);
            rs = ps.executeQuery();  
            rs.last();
            nbLignes = rs.getRow();
            codeService = new String[nbLignes];
            nombreChambre = new int[nbLignes];
            rs.first();
            for (int i=0; i<nbLignes; i++){
                codeService[i] = rs.getString(1);
                nombreChambre[i] = rs.getInt(2);
                rs.next();
            }
            
            
             }catch(SQLException e)
             {
                 System.out.println(e);
             }



        DefaultPieDataset pieDataset = new DefaultPieDataset();
        for (int i = 0; i<nbLignes;i++){
            pieDataset.setValue(codeService[i]+"("+nombreChambre[i]+")", nombreChambre[i]);
        }
        JFreeChart chart = ChartFactory.createPieChart("Nombres de chambres par service", pieDataset, true, true, true);
        PiePlot P = (PiePlot)chart.getPlot();
        //P.setForegroundAlpha(TOP_ALIGNMENT);
        ChartFrame frame = new ChartFrame("Nombres de chambres par service", chart);
        frame.setVisible(true);
        frame.setSize(450,500);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JTable Table_hos;
    private javax.swing.JButton btn_modif;
    private javax.swing.JButton btn_suppr;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField num_pat;
    private javax.swing.JPanel panel;
    private javax.swing.JRadioButton radio_chbr;
    private javax.swing.JRadioButton radio_lit;
    private javax.swing.JRadioButton radio_nom;
    private javax.swing.JRadioButton radio_pat;
    private javax.swing.JTextField txt_chbr;
    private javax.swing.JTextField txt_code;
    private javax.swing.JTextField txt_lit;
    private javax.swing.JTextField txt_mut;
    private javax.swing.JTextField txt_nom;
    private javax.swing.JTextField txt_prenom;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
