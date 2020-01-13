/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.ModifChambre;
import Modele.AjoutChambre;
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
public class Liste_de_chambres extends javax.swing.JInternalFrame {
    
    static Connection conn = null;
    ResultSet rs = null ;
    PreparedStatement ps = null;
    static String test;
    static Boolean x;
    /**
     * Creates new form Liste_de_chambres
     * @param connexion
     * @param xi
     */
    public Liste_de_chambres(Connection connexion, Boolean xi) {
        Liste_de_chambres.x = xi;
        Liste_de_chambres.conn = connexion;
        initComponents();
       remove_title_bar();
        Affichage(x);
    }

    private void Affichage(Boolean x){
       try{
          
           String requete;
           if(x==false)
           requete = "select * from CHAMBRE";
           else
             requete =  "select lit, C.no_chambre, nom, H.no_malade, C.code_service from CHAMBRE C ,MALADE M, HOSPITALISATION H where numero = H.no_malade and H.no_chambre = C.no_chambre";
           //requete = "select lit, C.no_chambre, nom, prenom, H.no_malade from CHAMBRE C ,MALADE M, HOSPITALISATION H where numero = H.no_malade and H.no_chambre = C.no_chambre";  
           ps = conn.prepareStatement(requete);
           rs = ps.executeQuery();
           Table_chbr.setModel(DbUtils.resultSetToTableModel(rs));
           
       }catch(SQLException e){
           System.out.println("Exeption" + e);
       }
    }
    
     private void remove_title_bar(){
        putClientProperty("Liste_de_chambres.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
    }
     
     public void deplacement()
         {
             try{
               
               if(x ==true){
                int row =Table_chbr.getSelectedRow();
                Liste_de_chambres.test = (Table_chbr.getModel().getValueAt(row, 0).toString());
                String requete = "select * from CHAMBRE C ,MALADE M, HOSPITALISATION H where numero = H.no_malade and H.no_chambre = C.no_chambre and lit ='"+test+"'";
                ps = conn.prepareStatement(requete);
                rs = ps.executeQuery();
                
                    if(rs.next()){
                        String t1 = rs.getString("no_chambre");
                        txt_chbr.setText(t1);

                        String t4 = rs.getString("lit");
                        txt_lit.setText(t4);
                        String t5 = rs.getString("surveillant");
                        txt_sur.setText(t5);
                        String t6 = rs.getString("numero");
                        num_pat.setText(t6);
                         String t8 = rs.getString("code_service");
                        txt_code.setText(t8);


                        String t2 = rs.getString("prenom");
                        txt_prenom.setText(t2);
                        String t3 = rs.getString("nom");
                        txt_nom.setText(t3);
                        String t7 = rs.getString("mutuelle");
                        txt_mut.setText(t7);
                        }
                    }
                else {
                   int row =Table_chbr.getSelectedRow();
                    Liste_de_chambres.test = (Table_chbr.getModel().getValueAt(row, 1).toString());
                    String requete = "select * from CHAMBRE where no_chambre ='"+test+"'";
                    ps = conn.prepareStatement(requete);
                    rs = ps.executeQuery();

                 if(rs.next()){
                     String t1 = rs.getString("no_chambre");
                     txt_chbr.setText(t1);
                     String t5 = rs.getString("surveillant");
                     txt_sur.setText(t5);
                     String t8 = rs.getString("code_service");
                        txt_code.setText(t8);
                     jLabel5.setEnabled(false);
                     jLabel6.setEnabled(false);
                     jLabel2.setEnabled(false);
                     jLabel3.setEnabled(false);
                     jLabel8.setEnabled(false);
                     
                     
                     
                     String t6 = " ";
                     num_pat.setText(t6);


                     String t2 =" ";
                     txt_prenom.setText(t2);
                     String t3 = " ";
                     txt_nom.setText(t3);
                     String t7 = " ";
                     txt_mut.setText(t7);
                     
                     }

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
                String requete1 = "select lit, C.no_chambre, nom, H.no_malade, C.code_service from CHAMBRE C ,MALADE M, HOSPITALISATION H where numero = H.no_malade and H.no_chambre = C.no_chambre and nom LIKE ? order by nom";
                ps = conn.prepareStatement(requete1); 
                ps.setString(1, "%"+txt_search.getText()+"%");
                rs =ps.executeQuery();
                Table_chbr.setModel(DbUtils.resultSetToTableModel(rs));
            
       }catch(SQLException e){
           System.out.println(e);
       }
       
       
//      if(radio_lit.isSelected())
//        try{
//             String requete2 ="select lit, C.no_chambre, nom, H.no_malade, C.code_service from CHAMBRE C ,MALADE M, HOSPITALISATION H where numero = H.no_malade and H.no_chambre = C.no_chambre and lit LIKE ?";
//             ps = conn.prepareStatement(requete2); 
//             ps.setString(1,"%"+ txt_search.getText()+"%");
//             rs = ps.executeQuery();
//
//
//             Table_chbr.setModel(DbUtils.resultSetToTableModel(rs));
//
//        }catch(SQLException e){
//            System.out.println(e);
//        }
      
      if(radio_chbr.isSelected())
          try{
             String requete2 = "select * from CHAMBRE where no_chambre LIKE ? order by no_chambre";
             ps = conn.prepareStatement(requete2); 
             ps.setString(1,"%"+ txt_search.getText()+"%");
             rs = ps.executeQuery();


             Table_chbr.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(SQLException e){
            System.out.println(e);
        }
      
       if(radio_pat.isSelected())
          try{
             String requete2 = "select lit, C.no_chambre, nom, H.no_malade, C.code_service from CHAMBRE C ,MALADE M, HOSPITALISATION H where numero = H.no_malade and H.no_chambre = C.no_chambre and H.no_malade LIKE ? order by H.no_malade";
             ps = conn.prepareStatement(requete2); 
             ps.setString(1,"%"+ txt_search.getText()+"%");
             rs = ps.executeQuery();

             Table_chbr.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(SQLException e){
            System.out.println(e);
        }
    }
      
       public String gettableresult()
    {
        return test;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        radio_pat = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_chbr = new javax.swing.JTable();
        Add = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        panel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_nom = new javax.swing.JTextField();
        txt_prenom = new javax.swing.JTextField();
        txt_lit = new javax.swing.JTextField();
        num_pat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_chbr = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_sur = new javax.swing.JTextField();
        btn_modif = new javax.swing.JButton();
        btn_suppr = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_mut = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_code = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        radio_nom = new javax.swing.JRadioButton();
        radio_chbr = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        txt_count = new javax.swing.JTextField();

        setBackground(new java.awt.Color(204, 204, 204));
        setPreferredSize(new java.awt.Dimension(1300, 467));

        radio_pat.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_pat);
        radio_pat.setText("n° patient");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Table_chbr.setBackground(new java.awt.Color(153, 153, 153));
        Table_chbr.setModel(new javax.swing.table.DefaultTableModel(
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
        Table_chbr.setMaximumSize(new java.awt.Dimension(736, 366));
        Table_chbr.setMinimumSize(new java.awt.Dimension(736, 366));
        Table_chbr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_chbrMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Table_chbr);

        Add.setBackground(new java.awt.Color(255, 153, 51));
        Add.setText("Ajout chambre");
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

        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("nombre de surveillant :");

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

        txt_chbr.setEditable(false);
        txt_chbr.setBorder(null);
        txt_chbr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_chbrActionPerformed(evt);
            }
        });

        jLabel2.setText("Nom patient :");

        jLabel7.setText("Numéros chambre :");

        txt_sur.setEditable(false);
        txt_sur.setBorder(null);

        btn_modif.setBackground(new java.awt.Color(204, 204, 204));
        btn_modif.setText("Modifier");
        btn_modif.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_modif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modifActionPerformed(evt);
            }
        });

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

        jLabel8.setText("Mutuelle patient :");

        txt_mut.setEditable(false);
        txt_mut.setBorder(null);

        jLabel9.setText("Code du service :");

        txt_code.setEditable(false);
        txt_code.setBorder(null);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_modif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_lit))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(num_pat, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_prenom, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_sur, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_mut, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_code))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_chbr, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btn_suppr, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_chbr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_sur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_lit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
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
                .addComponent(btn_modif, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btn_suppr)
                .addContainerGap())
        );

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setText("Trier");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        radio_nom.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_nom);
        radio_nom.setText("Nom");

        radio_chbr.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_chbr);
        radio_chbr.setSelected(true);
        radio_chbr.setText("Chambre");

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Reporting :");

        jButton5.setBackground(new java.awt.Color(255, 102, 153));
        jButton5.setText("Compter");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(txt_count, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_count, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio_chbr)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radio_nom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio_pat)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Add, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radio_nom)
                    .addComponent(radio_chbr)
                    .addComponent(radio_pat)
                    .addComponent(jButton1)
                    .addComponent(Add))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Table_chbrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_chbrMouseClicked
        deplacement();
    }//GEN-LAST:event_Table_chbrMouseClicked

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        AjoutChambre chbr = new AjoutChambre(conn);
        chbr.setVisible(true);
    }//GEN-LAST:event_AddActionPerformed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        search();
    }//GEN-LAST:event_txt_searchActionPerformed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
         search();
    }//GEN-LAST:event_txt_searchKeyReleased

    private void txt_nomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nomActionPerformed

    private void txt_chbrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_chbrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_chbrActionPerformed

    private void btn_modifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modifActionPerformed
        ModifChambre chbr = new ModifChambre(test,conn);
        chbr.setVisible(true);
    }//GEN-LAST:event_btn_modifActionPerformed

    private void btn_supprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_supprActionPerformed
          try{

            String requete = "delete from CHAMBRE where no_chambre ='"+gettableresult()+"'";
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
          Affichage(x);
    }//GEN-LAST:event_btn_supprActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        search();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int row;
        row = Table_chbr.getRowCount();
        String rowString = Integer.toString(row);
        txt_count.setText(rowString);
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JTable Table_chbr;
    private javax.swing.JButton btn_modif;
    private javax.swing.JButton btn_suppr;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField num_pat;
    private javax.swing.JPanel panel;
    private javax.swing.JRadioButton radio_chbr;
    private javax.swing.JRadioButton radio_nom;
    private javax.swing.JRadioButton radio_pat;
    private javax.swing.JTextField txt_chbr;
    private javax.swing.JTextField txt_code;
    private javax.swing.JTextField txt_count;
    private javax.swing.JTextField txt_lit;
    private javax.swing.JTextField txt_mut;
    private javax.swing.JTextField txt_nom;
    private javax.swing.JTextField txt_prenom;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_sur;
    // End of variables declaration//GEN-END:variables
}
