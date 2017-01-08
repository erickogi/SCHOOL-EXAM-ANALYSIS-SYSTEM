/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author kimani kogi
 */
public class registerSubjects extends javax.swing.JFrame {
private int count=10;
String id = null;
   String  name = null;
   
   HashMap<String,String>subject=new HashMap<String,String>();
    /**
     * Creates new form registerSubjects
     */
    public registerSubjects() {
        initComponents();
        
        setSelected();
        
        initializeSubjects();
        
        list();
        
        txtCount.setText(String.valueOf(count));
        
   

    }
    private void initializeSubjects(){
        subject.put("s1", "Mathematics");
        subject.put("s2", "English");
        subject.put("s3", "Kiswahili");
        subject.put("s4", "Physics");
        subject.put("s5", "Chemistry");
        subject.put("s6", "Biology");
        subject.put("s7", "History");
        subject.put("s8", "Geography");
        subject.put("s12", "Agriculture");
        subject.put("s23", "Business");
        
    }
    public void list(){

        
              //  ArrayList<subjectsIdName>num=new ArrayList<>();
       
        
    chkAgri.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
            if(ie.getStateChange()==1){
                count++;
                txtCount.setText(String.valueOf(count));
            subject.put("s12", "Agriculture");
            
            
           
            }
            else{
                count--;
               txtCount.setText(String.valueOf(count));
               // subject.put("s12", "Agriculture");
                subject.remove("s12");
            }
            }
                   
               });
    chkArabic.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
              if(ie.getStateChange()==1){
                count++;
                txtCount.setText(String.valueOf(count));
               subject.put("s22", "Arabic");
            }
            else{
                count--;
               txtCount.setText(String.valueOf(count));
               subject.remove("s22");
            }  
            }
        
    });
   chkArt.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
            if(ie.getStateChange()==1){
                count++;
                txtCount.setText(String.valueOf(count));
                 subject.put("s14", "Art-Design");
            }
            else{
                count--;
               txtCount.setText(String.valueOf(count));
                subject.remove("s14");
            }
            }
       
   });
   chkBc.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
             if(ie.getStateChange()==1){
                count++;
                txtCount.setText(String.valueOf(count));
                 subject.put("s16", "B/c");
            }
            else{
                count--;
               txtCount.setText(String.valueOf(count));
               subject.remove("s16");
            }
            }
       
   });
    chkBio.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
             if(ie.getStateChange()==1){
                count++;
                txtCount.setText(String.valueOf(count));
                  subject.put("s6", "Biology");
            }
            else{
                count--;
               txtCount.setText(String.valueOf(count));
               subject.remove("s6");
            }
            }
        
    });
     chkBs.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
             if(ie.getStateChange()==1){
                count++;
                txtCount.setText(String.valueOf(count));
                subject.put("s23", "Business");
            }
            else{
                count--;
               txtCount.setText(String.valueOf(count));
                subject.remove("s23");
            }
            }
         
     });
  chkChem.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
             if(ie.getStateChange()==1){
                count++;
                txtCount.setText(String.valueOf(count));
                subject.put("s5", "Chemistry");
            }
            else{
                count--;
               txtCount.setText(String.valueOf(count));
               subject.remove("s5");
            }
            }
      
  });
     chkComp.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
             if(ie.getStateChange()==1){
                count++;
                txtCount.setText(String.valueOf(count));
                subject.put("s15", "Computer");
            }
            else{
                count--;
               txtCount.setText(String.valueOf(count));
               subject.remove("s15");
            }
            }
         
     });
     chkCre.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
             if(ie.getStateChange()==1){
                count++;
                txtCount.setText(String.valueOf(count));
                 subject.put("s9", "Cre");
            }
            else{
                count--;
               txtCount.setText(String.valueOf(count));
               subject.remove("s9");
            }
            }
         
     });
     chkEng.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
             if(ie.getStateChange()==1){
                count++;
                txtCount.setText(String.valueOf(count));
                 subject.put("s2", "English");
            }
            else{
                count--;
               txtCount.setText(String.valueOf(count));
               subject.remove("s2");
            }
            }
         
     });
     chkFrench.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
             if(ie.getStateChange()==1){
                count++;
                txtCount.setText(String.valueOf(count));
                 subject.put("s20", "French");
            }
            else{
                count--;
               txtCount.setText(String.valueOf(count));
                subject.remove("s20");
            }
            }
         
     });
   chkGeo.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
             if(ie.getStateChange()==1){
                count++;
                txtCount.setText(String.valueOf(count));
                 subject.put("s8", "Geography");
            }
            else{
                count--;
               txtCount.setText(String.valueOf(count));
               subject.remove("s8");
            }
            }
       
   });
    chkGerman.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
             if(ie.getStateChange()==1){
                count++;
                txtCount.setText(String.valueOf(count));
                  subject.put("s21", "German");
            }
            else{
                count--;
               txtCount.setText(String.valueOf(count));
                subject.remove("s21");
            }
            }
        
    });
    chkHist.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
             if(ie.getStateChange()==1){
                count++;
                txtCount.setText(String.valueOf(count));
                subject.put("s7", "History");
            }
            else{
                count--;
               txtCount.setText(String.valueOf(count));
                subject.remove("s7");
            }
            }
        
    });
    chkHre.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
              if(ie.getStateChange()==1){
                count++;
                txtCount.setText(String.valueOf(count));
                  subject.put("s11", "Hre");
            }
            else{
                count--;
               txtCount.setText(String.valueOf(count));
                subject.remove("s11");
            }
            }
        
    });
    chkHs.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
             if(ie.getStateChange()==1){
                count++;
                txtCount.setText(String.valueOf(count));
                 subject.put("s13", "Home-science");
            }
            else{
                count--;
               txtCount.setText(String.valueOf(count));
                subject.remove("s13");
            }
            }
        
    });
 chkIre.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
             if(ie.getStateChange()==1){
                count++;
                txtCount.setText(String.valueOf(count));
                 subject.put("s10", "Ire");
            }
            else{
                count--;
               txtCount.setText(String.valueOf(count));
                subject.remove("s10");
            }
            }
     
 });
     chkKiswa.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
             if(ie.getStateChange()==1){
                count++;
                txtCount.setText(String.valueOf(count));
                 subject.put("s3", "Kiswahili");
            }
            else{
                count--;
               txtCount.setText(String.valueOf(count));
                subject.remove("s3");
               
            }
            }
         
     });
     chkMaths.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
             if(ie.getStateChange()==1){
                count++;
                txtCount.setText(String.valueOf(count));
                  subject.put("s1", "Mathematics");
            }
            else{
                count--;
               txtCount.setText(String.valueOf(count));
                 subject.remove("s1");
            }
            }
         
     });
    chkMetalwook.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
             if(ie.getStateChange()==1){
                count++;
                txtCount.setText(String.valueOf(count));
                 subject.put("s18", "Metalwork");
            }
            else{
                count--;
               txtCount.setText(String.valueOf(count));
                 subject.remove("s18");
            }
            }
        
    });
   chkMusic.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
             if(ie.getStateChange()==1){
                count++;
                txtCount.setText(String.valueOf(count));
                 subject.put("s19", "Music");
            }
            else{
                count--;
               txtCount.setText(String.valueOf(count));
                 subject.remove("s19");
            }
            }
       
   });
   chkPhy.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
             if(ie.getStateChange()==1){
                count++;
                txtCount.setText(String.valueOf(count));
                 subject.put("s4", "Physics");
            }
            else{
                count--;
               txtCount.setText(String.valueOf(count));
                subject.remove("s4");
               
            } 
            }
       
   });
     chkWoodwork.addItemListener (new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
             if(ie.getStateChange()==1){
                count++;
                txtCount.setText(String.valueOf(count));
                 subject.put("s17", "Woodwork");
            }
            else{
                count--;
               txtCount.setText(String.valueOf(count));
               subject.remove("s17");
            }
            }
       
   });
    } 

public void setSelected(){
    
       chkAgri.setSelected(true);
  //  chkArabic;
  //  chkArt;
  //  chkBc;
    chkBio.setSelected(true);
     chkBs.setSelected(true);
  chkChem.setSelected(true);
    // chkComp;
    // chkCre;
     chkEng.setSelected(true);
    // chkFrench;
   chkGeo.setSelected(true);
    // chkGerman;
    chkHist.setSelected(true);
   // chkHre;
   //  chkHs;
 //chkIre;
     chkKiswa.setSelected(true);
     chkMaths.setSelected(true);
    // chkMetalwook;
   // chkMusic;
   chkPhy.setSelected(true);
    // chkWoodwork; 
    
    
    
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        chkMaths = new javax.swing.JCheckBox();
        chkEng = new javax.swing.JCheckBox();
        chkKiswa = new javax.swing.JCheckBox();
        chkGeo = new javax.swing.JCheckBox();
        chkHist = new javax.swing.JCheckBox();
        chkHs = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        chkChem = new javax.swing.JCheckBox();
        chkPhy = new javax.swing.JCheckBox();
        chkBio = new javax.swing.JCheckBox();
        chkAgri = new javax.swing.JCheckBox();
        chkBs = new javax.swing.JCheckBox();
        chkComp = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        txtCount = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        chkCre = new javax.swing.JCheckBox();
        chkIre = new javax.swing.JCheckBox();
        chkHre = new javax.swing.JCheckBox();
        chkBc = new javax.swing.JCheckBox();
        chkArt = new javax.swing.JCheckBox();
        chkMusic = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        chkFrench = new javax.swing.JCheckBox();
        chkGerman = new javax.swing.JCheckBox();
        chkArabic = new javax.swing.JCheckBox();
        chkMetalwook = new javax.swing.JCheckBox();
        chkWoodwork = new javax.swing.JCheckBox();
        btnSave = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        chkMaths.setText("MATHEMATICS");

        chkEng.setText("ENGLISH");

        chkKiswa.setText("KISWAHILI");

        chkGeo.setText("GEOGRAPHY");

        chkHist.setText("HISTORY");

        chkHs.setText("HOME SCIENCE");
        chkHs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkHsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkMaths)
                    .addComponent(chkEng)
                    .addComponent(chkKiswa)
                    .addComponent(chkGeo)
                    .addComponent(chkHist)
                    .addComponent(chkHs))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkMaths)
                .addGap(18, 18, 18)
                .addComponent(chkEng)
                .addGap(18, 18, 18)
                .addComponent(chkKiswa)
                .addGap(47, 47, 47)
                .addComponent(chkGeo)
                .addGap(18, 18, 18)
                .addComponent(chkHist)
                .addGap(18, 18, 18)
                .addComponent(chkHs)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        chkChem.setText("CHEMISTRY");

        chkPhy.setText("PHYISICS");

        chkBio.setText("BIOLOGY");

        chkAgri.setText("AGRICULTURE");

        chkBs.setText("BUSINESS STUDUIES");

        chkComp.setText("COMPUTER STUDIES");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("SUBJECT COUNT ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(chkChem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addComponent(chkPhy)
                    .addComponent(chkBio)
                    .addComponent(chkAgri)
                    .addComponent(chkBs)
                    .addComponent(chkComp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCount, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkChem)
                    .addComponent(jLabel1)
                    .addComponent(txtCount))
                .addGap(18, 18, 18)
                .addComponent(chkPhy)
                .addGap(18, 18, 18)
                .addComponent(chkBio)
                .addGap(37, 37, 37)
                .addComponent(chkAgri)
                .addGap(18, 18, 18)
                .addComponent(chkBs)
                .addGap(18, 18, 18)
                .addComponent(chkComp)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        chkCre.setText("CRE");

        chkIre.setText("IRE");

        chkHre.setText("HRE");

        chkBc.setText("BUILDING AND CONSTRUCTION");

        chkArt.setText("ART AND DESIGN");
        chkArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkArtActionPerformed(evt);
            }
        });

        chkMusic.setText("MUSIC");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkCre)
                    .addComponent(chkIre)
                    .addComponent(chkHre)
                    .addComponent(chkBc)
                    .addComponent(chkArt)
                    .addComponent(chkMusic))
                .addContainerGap(196, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkCre)
                .addGap(18, 18, 18)
                .addComponent(chkIre)
                .addGap(18, 18, 18)
                .addComponent(chkHre)
                .addGap(36, 36, 36)
                .addComponent(chkBc)
                .addGap(18, 18, 18)
                .addComponent(chkArt)
                .addGap(14, 14, 14)
                .addComponent(chkMusic)
                .addContainerGap())
        );

        chkFrench.setText("FRENCH");

        chkGerman.setText("GERMAN");

        chkArabic.setText("ARABIC");
        chkArabic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkArabicActionPerformed(evt);
            }
        });

        chkMetalwook.setText("METAL WORK");

        chkWoodwork.setText("WOOD WORK");

        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jButton1.setText("CLEAR");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkFrench)
                            .addComponent(chkGerman)
                            .addComponent(chkArabic)
                            .addComponent(chkMetalwook)
                            .addComponent(chkWoodwork)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSave)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkFrench)
                .addGap(18, 18, 18)
                .addComponent(chkGerman)
                .addGap(18, 18, 18)
                .addComponent(chkArabic)
                .addGap(26, 26, 26)
                .addComponent(chkMetalwook)
                .addGap(18, 18, 18)
                .addComponent(chkWoodwork)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkArabicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkArabicActionPerformed
        
    }//GEN-LAST:event_chkArabicActionPerformed

    private void chkHsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkHsActionPerformed
       
    }//GEN-LAST:event_chkHsActionPerformed

    private void chkArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkArtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkArtActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
      for(HashMap.Entry<String,String>entry: subject.entrySet()){
          String id=entry.getKey();
          String n=entry.getValue();
           String query = "INSERT INTO `subjects`(`id`,`subjectname`,`A`, `B`, `C`, `D`,"
                   + " `E`, `A-`, `B-`, `C-`, `D-`, `B+`, `C+`, `D+`) VALUES"
                   + " ('" + id+ "','" + n + "','82', '68', '52', '28', '0', '78', '62', '48', '22', '72', '58', '32')";
      //   '2', 's2', 'English', '82', '68', '52', '28', '0', '78', '62', '48', '22', '72', '58', '32'


          methods m=new methods();
          m.executeSQlQuery(query, "Inserted");
         // JOptionPane.showMessageDialog(null, id +" "+n);
      }
      JOptionPane.showMessageDialog(null, "Added");
      grading m=new grading();
      m.setVisible(true);
      this.dispose();
    }//GEN-LAST:event_btnSaveActionPerformed

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
            java.util.logging.Logger.getLogger(registerSubjects.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registerSubjects.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registerSubjects.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registerSubjects.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registerSubjects().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox chkAgri;
    private javax.swing.JCheckBox chkArabic;
    private javax.swing.JCheckBox chkArt;
    private javax.swing.JCheckBox chkBc;
    private javax.swing.JCheckBox chkBio;
    private javax.swing.JCheckBox chkBs;
    private javax.swing.JCheckBox chkChem;
    private javax.swing.JCheckBox chkComp;
    private javax.swing.JCheckBox chkCre;
    private javax.swing.JCheckBox chkEng;
    private javax.swing.JCheckBox chkFrench;
    private javax.swing.JCheckBox chkGeo;
    private javax.swing.JCheckBox chkGerman;
    private javax.swing.JCheckBox chkHist;
    private javax.swing.JCheckBox chkHre;
    private javax.swing.JCheckBox chkHs;
    private javax.swing.JCheckBox chkIre;
    private javax.swing.JCheckBox chkKiswa;
    private javax.swing.JCheckBox chkMaths;
    private javax.swing.JCheckBox chkMetalwook;
    private javax.swing.JCheckBox chkMusic;
    private javax.swing.JCheckBox chkPhy;
    private javax.swing.JCheckBox chkWoodwork;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel txtCount;
    // End of variables declaration//GEN-END:variables
}
