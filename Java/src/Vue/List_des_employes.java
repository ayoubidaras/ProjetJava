/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.ModifInfirmier;
import Modele.AjoutInfirmier;
import Controle.connexionDB;
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
public class List_des_employes extends javax.swing.JInternalFrame {

    static Connection conn = null;
    ResultSet rs = null ;
    ResultSet rs2 = null ;
    PreparedStatement ps = null;
    static String test;
    /**
     * Creates new form List_des_employes
     */
    public List_des_employes() {
        initComponents();
       // conn = connexionDB.start();
        Affichage();
        /*initComponents();
        remove_title_bar();
        
        conn = connexionDB.start();
        
        Affichage();*/
        
    }
    public List_des_employes(Boolean x, Connection connexion) {
        List_des_employes.conn = connexion;
        initComponents();
        remove_title_bar();
        //conn = connexionDB.start();
        Affichage(x);  
        
    }
    
   
    //display table
    private void Affichage(Boolean x){
       try{
           String requete;
           if(x == false)
                requete = "select I.numero ,prenom, nom from EMPLOYE E, INFIRMIER I where E.numero = I.numero"; 
           else 
                requete = "select I.numero ,prenom, nom from EMPLOYE E, INFIRMIER I where E.numero = I.numero and rotation =NUIT";
           
           ps = conn.prepareStatement(requete);
           rs = ps.executeQuery();
           Table_Emp.setModel(DbUtils.resultSetToTableModel(rs));
           
       }catch(SQLException e){
           System.out.println("Exeption" + e);
       }
    }
    
    private void Affichage(){
       try{
           String requete;
           requete = "select I.numero ,prenom, nom, salaire from EMPLOYE E, INFIRMIER I where E.numero = I.numero"; 
         
           
           ps = conn.prepareStatement(requete);
           rs = ps.executeQuery();
           Table_Emp.setModel(DbUtils.resultSetToTableModel(rs));
           
       }catch(SQLException e){
           System.out.println("Exeption" + e);
       }
    }
    
    
    private void remove_title_bar(){
        putClientProperty("List_des_employes.isPalette", Boolean.TRUE);
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
                int row = Table_Emp.getSelectedRow();
                test = (Table_Emp.getModel().getValueAt(row, 0).toString());
                String requete = "select * from EMPLOYE E, INFIRMIER I where E.numero = I.numero and I.numero ='"+test+"'";
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
                    String t6 = rs.getString("code_service");
                    txt_service.setText(t6);
                    String t7 = rs.getString("rotation");
                    txt_rotation.setText(t7);
                    
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
                String requete1 = "select E.numero, E.prenom, E.nom from INFIRMIER I , EMPLOYE E where E.nom LIKE ? and E.numero = I.numero";
                ps = conn.prepareStatement(requete1); 
                ps.setString(1, "%"+txt_search.getText()+"%");
                rs =ps.executeQuery();
                Table_Emp.setModel(DbUtils.resultSetToTableModel(rs));
            
       }catch(SQLException e){
           System.out.println(e);
       }
       
       
      if(radio_num.isSelected())
        try{
             String requete2 = "select E.numero, E.prenom, E.nom from INFIRMIER I , EMPLOYE E where E.numero LIKE ? and E.numero = I.numero";
             ps = conn.prepareStatement(requete2); 
             ps.setString(1,"%"+ txt_search.getText()+"%");
             rs2 = ps.executeQuery();


             Table_Emp.setModel(DbUtils.resultSetToTableModel(rs2));

        }catch(SQLException e){
            System.out.println(e);
        }
      
      if(radio_prenom.isSelected())
          try{
             String requete2 = "select E.numero, E.prenom, E.nom from INFIRMIER I , EMPLOYE E where E.prenom LIKE ? and E.numero = I.numero";
             ps = conn.prepareStatement(requete2); 
             ps.setString(1,"%"+ txt_search.getText()+"%");
             rs2 = ps.executeQuery();


             Table_Emp.setModel(DbUtils.resultSetToTableModel(rs2));

        }catch(SQLException e){
            System.out.println(e);
        }
      
       if(radio_sal.isSelected())
          try{
             String requete2 = "select E.numero, E.prenom, E.nom, salaire from INFIRMIER I , EMPLOYE E where I.salaire LIKE ? and E.numero = I.numero";
             ps = conn.prepareStatement(requete2); 
             ps.setString(1,"%"+ txt_search.getText()+"%");
             rs2 = ps.executeQuery();


             Table_Emp.setModel(DbUtils.resultSetToTableModel(rs2));

        }catch(SQLException e){
            System.out.println(e);
        }
        if(radio_rot.isSelected())
          try{
             String requete2 = "select E.numero, E.prenom, E.nom, rotation from INFIRMIER I , EMPLOYE E where I.rotation LIKE ? and E.numero = I.numero";
             ps = conn.prepareStatement(requete2); 
             ps.setString(1,"%"+ txt_search.getText()+"%");
             rs2 = ps.executeQuery();


             Table_Emp.setModel(DbUtils.resultSetToTableModel(rs2));

        }catch(SQLException e){
            System.out.println(e);
        }
         if(radio_ser.isSelected())
          try{
             String requete2 = "select E.numero, E.prenom, E.nom, code_service from INFIRMIER I , EMPLOYE E where I.code_service LIKE ? and E.numero = I.numero";
             ps = conn.prepareStatement(requete2); 
             ps.setString(1,"%"+ txt_search.getText()+"%");
             rs2 = ps.executeQuery();


             Table_Emp.setModel(DbUtils.resultSetToTableModel(rs2));

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
        panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_Emp = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_numero = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_nom = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_prenom = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_tel = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_adresse = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_service = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_rotation = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btn_suppr = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        radio_num = new javax.swing.JRadioButton();
        radio_nom = new javax.swing.JRadioButton();
        radio_prenom = new javax.swing.JRadioButton();
        radio_sal = new javax.swing.JRadioButton();
        radio_ser = new javax.swing.JRadioButton();
        radio_rot = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(102, 102, 102));
        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setForeground(new java.awt.Color(255, 255, 255));
        setTitle("Liste"); // NOI18N
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1248, 492));
        setVisible(true);

        panel.setBackground(new java.awt.Color(204, 204, 204));

        jScrollPane1.setBackground(new java.awt.Color(102, 102, 102));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1248, 492));

        Table_Emp.setBackground(new java.awt.Color(153, 153, 153));
        Table_Emp.setModel(new javax.swing.table.DefaultTableModel(
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
        Table_Emp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_EmpMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Table_Emp);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Numero :");

        txt_numero.setBackground(new java.awt.Color(240, 240, 240));
        txt_numero.setBorder(null);

        jLabel2.setText("Nom :");

        txt_nom.setBackground(new java.awt.Color(240, 240, 240));
        txt_nom.setBorder(null);

        jLabel3.setText("Prénom :");

        txt_prenom.setBackground(new java.awt.Color(240, 240, 240));
        txt_prenom.setBorder(null);

        jLabel4.setText("Numéros de téléphone :");

        txt_tel.setBackground(new java.awt.Color(240, 240, 240));
        txt_tel.setBorder(null);

        jLabel5.setText("Adresse :");

        txt_adresse.setBackground(new java.awt.Color(240, 240, 240));
        txt_adresse.setBorder(null);

        jLabel6.setText("Code de service :");

        txt_service.setBackground(new java.awt.Color(240, 240, 240));
        txt_service.setBorder(null);

        jLabel7.setText("Rotation :");

        txt_rotation.setBackground(new java.awt.Color(240, 240, 240));
        txt_rotation.setBorder(null);

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setText("Modifier");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(19, 19, 19)
                                .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_prenom, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_adresse))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_service, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(txt_rotation, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 34, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(btn_suppr, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_prenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_adresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_service, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_rotation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_suppr)
                .addContainerGap())
        );

        add.setBackground(new java.awt.Color(255, 153, 51));
        add.setText("Ajout Infirmière");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Recherche :");

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

        btn_search.setText("rechercher");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        radio_num.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_num);
        radio_num.setSelected(true);
        radio_num.setText("Numero");
        radio_num.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        radio_nom.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_nom);
        radio_nom.setText("Nom");

        radio_prenom.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_prenom);
        radio_prenom.setText("Prenom");

        radio_sal.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_sal);
        radio_sal.setText("Salaire");

        radio_ser.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_ser);
        radio_ser.setText("Service");

        radio_rot.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(radio_rot);
        radio_rot.setText("Rotation");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_search)
                        .addGap(26, 26, 26)
                        .addComponent(radio_num)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radio_nom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio_prenom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radio_ser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio_rot)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio_sal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(363, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_search)
                        .addComponent(radio_num)
                        .addComponent(radio_nom)
                        .addComponent(radio_prenom)
                        .addComponent(radio_sal)
                        .addComponent(radio_ser)
                        .addComponent(radio_rot))
                    .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Table_EmpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_EmpMouseClicked
        deplacement();
    }//GEN-LAST:event_Table_EmpMouseClicked

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        AjoutInfirmier inf = new AjoutInfirmier(conn);
        inf.setVisible(true);
    }//GEN-LAST:event_addActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ModifInfirmier inf = new ModifInfirmier(test, conn);
        inf.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_supprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_supprActionPerformed
       try{ 
           
            String requete = "delete from EMPLOYE where numero ='"+gettableresult()+"'";
            String requete2 = "delete from INFIRMIER where numero ='"+gettableresult()+"'";
            System.out.println(test);
            if(JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir le supprimer ?",
                                        "Supprimer", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION)
          
        {
                ps = conn.prepareStatement(requete2);
                ps.execute();
                ps = conn.prepareStatement(requete);
                ps.execute();

                JOptionPane.showMessageDialog(null,"Supprimé avec succès");
                dispose();
          }
       }catch(SQLException e){
           System.out.println(e);
       }
    }//GEN-LAST:event_btn_supprActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
      search(); 
       
    }//GEN-LAST:event_btn_searchActionPerformed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
       search();
    }//GEN-LAST:event_txt_searchKeyReleased

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Table_Emp;
    private javax.swing.JButton add;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_suppr;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    private javax.swing.JRadioButton radio_nom;
    private javax.swing.JRadioButton radio_num;
    private javax.swing.JRadioButton radio_prenom;
    private javax.swing.JRadioButton radio_rot;
    private javax.swing.JRadioButton radio_sal;
    private javax.swing.JRadioButton radio_ser;
    private javax.swing.JTextField txt_adresse;
    private javax.swing.JTextField txt_nom;
    private javax.swing.JTextField txt_numero;
    private javax.swing.JTextField txt_prenom;
    private javax.swing.JTextField txt_rotation;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_service;
    private javax.swing.JTextField txt_tel;
    // End of variables declaration//GEN-END:variables
}
