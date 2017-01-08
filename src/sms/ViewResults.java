/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms;


import static com.itextpdf.kernel.pdf.PdfName.Image;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.apache.commons.lang3.ArrayUtils;


/**
 *
 * @author kimani kogi
 */
public class ViewResults extends javax.swing.JFrame {
    
DefaultTableModel model = new DefaultTableModel();

static int yearid;
int examid;
int termid;
String stream="";
    /**
     * Creates new form ViewResults
     */
    public ViewResults() {
      
         this.table = new JTable(model);
        this. sid= new JTextField();
         final TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
          sid.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = sid.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
           @Override
            public void removeUpdate(DocumentEvent e) {
                String text = sid.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
           });
        initComponents();
        setExamsModel();
        setSubjectsModel();
        setStreamsModel();
   // findUsers();
   // sorter(2);
    }
        public void title(String title){
        this.setTitle(title);
    }
   private void setSubjectsModel() {
        Form1Exams n=new Form1Exams();
        jComboBoxSubjects.removeAllItems();
        jComboBoxSubjects.addItem("Total");
        String []subs=n.findSubjectname();
        for (int a = 0; a < subs.length; a++) {

            jComboBoxSubjects.addItem(subs[a]);
        }
    }
    private void setStreamsModel() {
        // Form1Exams n=new Form1Exams();
        jComboBoxStreams.removeAllItems();
        jComboBoxStreams.addItem("Streams");
        String []subs=findStreams();
        for (int a = 0; a < subs.length; a++) {

            jComboBoxStreams.addItem(subs[a]);
        }
    }
    
     public  String[] findStreams()
  {
      methods m=new methods();
      String [] streams;
     ArrayList<String> usersList =  m.Streams();
    streams=new String[usersList.size()];
    usersList.toArray(streams);
     return streams;
  }
    //setexams types
    private void setExamsModel(){
        methods m= new methods ();
        jComboBoxExam.removeAllItems();
        jComboBoxExam.addItem("Choose Exam");
       
        ArrayList<String> list=m.ExamsTypes();
        String [] Exams=new String[list.size()];
        list.toArray(Exams);
          for (int a = 0; a < Exams.length; a++) {

            jComboBoxExam.addItem(Exams[a]);
        }
        
    }  
    
public ArrayList<ExamDbDataHolder> ListUsers(String ValToSearch)
  {
      
    //  yearid, termid, examid, sid, class, uniq, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15, s16, s17, s18, s19, s20, s21, s22, s23, updated_at
    ArrayList<ExamDbDataHolder> usersList = new ArrayList();
    try
    {
     methods m=new methods();
        Connection con = m.getConnection();
      Statement st = con.createStatement();
      Statement st1 = con.createStatement();
      String searchQuery = "SELECT * FROM `exam` WHERE `yearid` ='" + yearid + "'AND `termid`='"+termid+"'AND `examid`='"+examid+"'"
              + "AND CONCAT(`class`) LIKE '%" + stream + "%'AND YEAR(updated_at)='"+yearchooser.getYear()+"'ORDER BY `total` DESC ";
      String s="SELECT s1+s2+s3+s4+s5+s6+s7+s8+s9+s10+s11+s12+s13+s14+s15+s16"
              + "+s17+s18+s19+s20+s21+s22+s23 From `exam` WHERE `yearid` ='" + yearid + "'AND `termid`='"+termid+"'AND `examid`='"+yearid+"'"
              + "AND CONCAT(`class`) LIKE '%" + stream + "%'AND YEAR(updated_at)='"+yearchooser.getYear()+"'";
      //String searchQuery = "SELECT * FROM `exam` WHERE CONCAT(`sid`) LIKE '%" + ValToSearch + "%'";
      ResultSet rs = st.executeQuery(searchQuery);
      /// int a=0;
    //  ResultSet rs1 = st1.executeQuery(s);
      while (rs.next())

         
      {
        ExamDbDataHolder user = new ExamDbDataHolder(rs.getString("sid"),rs.getString("uniq"), rs.getString("s1"), rs.getString("s2"), rs.getString("s3")
                ,rs.getString("s4"),rs.getString("s5"),rs.getString("s6"),rs.getString("s7"),rs.getString("s8"),
                rs.getString("s9"),rs.getString("s10"),rs.getString("s11"),rs.getString("s12"),rs.getString("s13"),rs.getString("s14")
                ,rs.getString("s15"),rs.getString("s16"),rs.getString("s17"),rs.getString("s18"),rs.getString("s19"),rs.getString("s20")
                ,rs.getString("s21"),rs.getString("s22"),rs.getString("s23"),rs.getInt("total"));
        
        usersList.add(user);
      }
      st.close();
      rs.close();
       st1.close();
    //  rs1.close();
      con.close();
    }
    catch (Exception ex)
    {
      System.out.println(ex.getMessage());
    }
    return usersList;
  }
//     public  String[] findSubjectid()
//  {
//      methods m=new methods();
//      String [] subjectsid;
//     ArrayList<String> usersList =  m.Subjects();
//    subjectsid=new String[usersList.size()];
//    usersList.toArray(subjectsid);
//     return subjectsid;
//  }
//   //find subject names of offerd subjects from db
//   public  String[] findSubjectname()
//  {
//      methods m=new methods();
//      String [] subjectsnames;
//     ArrayList<String> usersList =  m.Subjectsnames();
//    subjectsnames=new String[usersList.size()];
//    usersList.toArray(subjectsnames);
//    
//   return subjectsnames;
//  }
   //display exams results in jtable
  public void findUsers()
  {
      methods nn=new methods();
       Form1Exams n=new Form1Exams();
  String []  Subjects=n.findSubjectid();
  String []  Subjectsnames=n.findSubjectname();
  int subjectCount=Subjects.length;
  //JOptionPane.showMessageDialog(null, subjectCount);
      
    ArrayList<ExamDbDataHolder> users = ListUsers(this.sid.getText());
    DefaultTableModel model = new DefaultTableModel();
    
   
//    String [] subjectsIdentifiers={"Maths","Eng","Kisw","Phy","Chem","Bio","Hist","Geo","Cre","Hre","Agri","Home Scie","Arts","Comp",
//                                      "B/c","Wood","Metal","Music","French","German","Arabic","Business"};
//    for(int y=0;y<Subjects.length;y++){
//        
//    }
//    String []toCol;
    String col[]={"Adm","Uniq","Total","Ave","AGG"};
    
   // String [] subjectsIdentifiers={}
    String [] both=(String[])ArrayUtils.addAll(col,Subjectsnames);
    
    
    model.setColumnIdentifiers(both);
    Object[] row = new Object[subjectCount+6];
    for (int i = 0; i < users.size(); i++)
    {
       
      row[0] = ((ExamDbDataHolder)users.get(i)).getSid();
      
      row[1] = ((ExamDbDataHolder)users.get(i)).getUnique();
       row[2] = ((ExamDbDataHolder)users.get(i)).getTotal();
        float g=Float.valueOf(((ExamDbDataHolder)users.get(i)).getTotal());
         //  tbl.addCell(String.format("%.1f", g));
       
        row[3] = String.format("%.1f", g/getYear());
         String gr=nn.checkGrade(yearid,String.format("%.1f", g/getYear()));
         row[4] = gr;
       int c=5;
      for(int s=0;s<Subjects.length;s++){
          
          if(Subjects[s].equals("s1")){
             row[c] = ((ExamDbDataHolder)users.get(i)).getMathematics();
             c++;
          }
          else if(Subjects[s].equals("s2")){
               row[c] = ((ExamDbDataHolder)users.get(i)).getEnglish();
               c++;
          } 
          else if(Subjects[s].equals("s3")){
               row[c] = ((ExamDbDataHolder)users.get(i)).getKiswahili();
               c++;
          }
          else if(Subjects[s].equals("s4")){
               row[c] = ((ExamDbDataHolder)users.get(i)).getPhysics();
               c++;
          }
          else if(Subjects[s].equals("s5")){
               row[c] = ((ExamDbDataHolder)users.get(i)).getChemistry();
               c++;
          }
          else if(Subjects[s].equals("s6")){
               row[c] = ((ExamDbDataHolder)users.get(i)).getBiology();
               c++;
          }
          else if(Subjects[s].equals("s7")){
               row[c] = ((ExamDbDataHolder)users.get(i)).getHistory();
               c++;
          }
          else if(Subjects[s].equals("s8")){
               row[c] = ((ExamDbDataHolder)users.get(i)).getGeography();
               c++;
          }
          else if(Subjects[s].equals("s9")){
               row[c] = ((ExamDbDataHolder)users.get(i)).getCre();
               c++;
          }
          else if(Subjects[s].equals("s10")){
               row[c] = ((ExamDbDataHolder)users.get(i)).getIre();
               c++;
          }
          else if(Subjects[s].equals("s11")){
               row[c] = ((ExamDbDataHolder)users.get(i)).getHre();
               c++;
          }
          else if(Subjects[s].equals("s12")){
               row[c] = ((ExamDbDataHolder)users.get(i)).getAgriculture();
               c++;
          }
          else if(Subjects[s].equals("s13")){
               row[c] = ((ExamDbDataHolder)users.get(i)).getHomescience();
               c++;
          }
          else if(Subjects[s].equals("s14")){
               row[c] = ((ExamDbDataHolder)users.get(i)).getArtdesign();
               c++;
          }
          else if(Subjects[s].equals("s15")){
               row[c] = ((ExamDbDataHolder)users.get(i)).getComputer();
               c++;
               
          }
          else if(Subjects[s].equals("s16")){
               row[c] = ((ExamDbDataHolder)users.get(i)).getBuilding();
               c++;
          }
          else if(Subjects[s].equals("s17")){
               row[c] = ((ExamDbDataHolder)users.get(i)).getWoodwork();
               c++;
          }
          else if(Subjects[s].equals("s18")){
               row[c] = ((ExamDbDataHolder)users.get(i)).getMetalwork();
               c++;
          }
          else if(Subjects[s].equals("s19")){
               row[c] = ((ExamDbDataHolder)users.get(i)).getMusic();
               c++;
          }
          else if(Subjects[s].equals("s20")){
               row[c] = ((ExamDbDataHolder)users.get(i)).getFrench();
               c++;
          }
          else if(Subjects[s].equals("s21")){
               row[c] = ((ExamDbDataHolder)users.get(i)).getGerman();
               c++;
          }
          else if(Subjects[s].equals("s22")){
               row[c] = ((ExamDbDataHolder)users.get(i)).getArabic();
               c++;
          }
          else if(Subjects[s].equals("s23")){
               row[c] = ((ExamDbDataHolder)users.get(i)).getBusiness();
               c++;
          }
     }
      model.addRow(row);
     
    }
    this.table.setModel(model);
     counter(model.getRowCount());
  }
   public void table(){
      //  Form1Exams n=new Form1Exams();
          
        DefaultTableModel model = (DefaultTableModel)this.table.getModel();
        
        model.setRowCount(0);
        
        findUsers();
  }
   public int getYear(){
       int subjects=0;
       switch (yearid){
           case 1:
               subjects=11;
               break;
           case 2:
               subjects=11;
               break;
           case 3:
               subjects=8;
               break;
           case 4:
               subjects=8;
               break;
               
               
       }
       return subjects;
   }
   public void counter(int count){
      
            DefaultTableModel model = new DefaultTableModel();
           String []c={"count"};
             model.setColumnIdentifiers(c);
          Object[] row = new Object[1];
    
            for (int a=1;a<=count;a++){
           
                 row[0] =a ;
           
            model.addRow(row);
             }
            
    
    this.tblCounter.setModel(model);
  
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        sid = new javax.swing.JTextField();
        jComboBoxSubjects = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxTerms = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        yearchooser = new com.toedter.calendar.JYearChooser();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jComboBoxExam = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCounter = new javax.swing.JTable();
        jComboBoxStreams = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        table.setAutoCreateRowSorter(true);
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table.setGridColor(new java.awt.Color(0, 102, 102));
        table.setRowHeight(40);
        table.setSelectionForeground(new java.awt.Color(255, 0, 102));
        jScrollPane1.setViewportView(table);

        sid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sidKeyReleased(evt);
            }
        });

        jComboBoxSubjects.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxSubjectsItemStateChanged(evt);
            }
        });
        jComboBoxSubjects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSubjectsActionPerformed(evt);
            }
        });

        jComboBoxTerms.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chose Term", "Term 1", "Term 2", "Term 3" }));
        jComboBoxTerms.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxTermsItemStateChanged(evt);
            }
        });
        jComboBoxTerms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTermsActionPerformed(evt);
            }
        });

        jButton1.setText("View");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        yearchooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                yearchooserPropertyChange(evt);
            }
        });

        jLabel2.setText("Change year");

        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBoxExam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxExamItemStateChanged(evt);
            }
        });

        tblCounter.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblCounter.setEnabled(false);
        tblCounter.setRowHeight(40);
        tblCounter.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblCounter);

        jComboBoxStreams.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxStreamsItemStateChanged(evt);
            }
        });

        jLabel3.setText("Optional");

        jLabel4.setText("Optional");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                        .addComponent(yearchooser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBoxStreams, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxSubjects, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxTerms, javax.swing.GroupLayout.Alignment.LEADING, 0, 106, Short.MAX_VALUE)
                            .addComponent(jComboBoxExam, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sid)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxExam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxTerms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxSubjects, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxStreams, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(46, 46, 46)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(yearchooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(sid, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE))
                .addContainerGap())
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sidKeyReleased
    table();
    }//GEN-LAST:event_sidKeyReleased

    private void jComboBoxTermsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTermsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTermsActionPerformed
String year;
    private void yearchooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_yearchooserPropertyChange
         year= String.valueOf(yearchooser.getYear()) ;
      //  findUsers();
    }//GEN-LAST:event_yearchooserPropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    check();
        
        try{
         
        if(orderBy!=null&&!orderBy.isEmpty()){
              sorter(2);
             
        }
        sorter(Integer.valueOf(orderBy));
     }
     catch(Exception a){
         
     }
       
        
    }//GEN-LAST:event_jButton1ActionPerformed
public void check(){
    if(jComboBoxExam.getSelectedIndex()==0||
            jComboBoxTerms.getSelectedIndex()==0||
           stream==null&&!stream.isEmpty()
        ){
        JOptionPane.showMessageDialog(null,"Some info Missing");
    }
    else{
        findUsers();
       // sorter(2);
    }
}
    private void jComboBoxExamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxExamItemStateChanged
    
        Form1Exams n=new Form1Exams();
        if(evt.getItem()!=""&&evt.getStateChange()==ItemEvent.SELECTED&&evt.getItem()!="Choose Exam"){

      examid=n.getExamId(evt.getItem().toString());
    //  subjectNameToId(exam);

     }
      else{
      examid=0;  
      }       
    }//GEN-LAST:event_jComboBoxExamItemStateChanged

    private void jComboBoxTermsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxTermsItemStateChanged
 String [] terms={"Term 1","Term 2","Term 3"};
        if(evt.getItem()==terms[0]&&evt.getStateChange()==ItemEvent.SELECTED){
      termid=1;
     // JOptionPane.showMessageDialog(null, term);
     }
        else if(evt.getItem()==terms[1]&&evt.getStateChange()==ItemEvent.SELECTED){
        termid=2;
       // JOptionPane.showMessageDialog(null, term);
    }
        else if(evt.getItem()==terms[2]&&evt.getStateChange()==ItemEvent.SELECTED){
            termid=3;
           // JOptionPane.showMessageDialog(null, term);
            
        }         
    }//GEN-LAST:event_jComboBoxTermsItemStateChanged

    private void jComboBoxSubjectsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSubjectsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSubjectsActionPerformed
String orderBy=null;


private int getIndex(String e){
     Form1Exams n=new Form1Exams();
    int index=-1;
    String []  Subjectsnames=n.findSubjectname();
    for(int i=0;i<Subjectsnames.length;i++){
        if(Subjectsnames[i].equals(e)){
            index=i;
        }
    }
    return index;
}
    private void jComboBoxSubjectsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxSubjectsItemStateChanged
     
 if(evt.getItem()!=""&&evt.getStateChange()==ItemEvent.SELECTED&&evt.getItem()!="Total"){

     
     
     String x=evt.getItem().toString();
     
     int y=getIndex(x);
      orderBy=String.valueOf(y+5);
    //  subjectNameToId(exam);

     }
      else{
      orderBy="2" ;
      }  
      
    }//GEN-LAST:event_jComboBoxSubjectsItemStateChanged

    private void jComboBoxStreamsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxStreamsItemStateChanged
         if(evt.getItem()!=""&&evt.getStateChange()==ItemEvent.SELECTED&&evt.getItem()!="Streams"){

     
     
     stream=evt.getItem().toString();
     
     //int y=getIndex(x);
    //  orderBy=String.valueOf(y+3);
    //  subjectNameToId(exam);

     }
      else{
     stream="";
      } 
        
        
        
    }//GEN-LAST:event_jComboBoxStreamsItemStateChanged
private void itextPrint(){
   //  String searchQuery = "SELECT * FROM `exam` WHERE `yearid` ='" + yearid + "'AND `termid`='"+termid+"'AND `examid`='"+yearid+"'"
    //          + "AND CONCAT(`class`) LIKE '%" + stream + "%'AND YEAR(updated_at)='"+yearchooser.getYear()+"'";
    
    methods nn=new methods();
    JFileChooser chooser = new JFileChooser();
    chooser.setCurrentDirectory(new java.io.File(","));
    chooser.setDialogTitle("Save at");
    chooser.setApproveButtonText("save");
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    if(chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
    try{
        Form1Exams n = new Form1Exams();
        Document pdfp=new Document();
        PdfWriter.getInstance(pdfp, new FileOutputStream(new File(chooser.getSelectedFile(),"report.pdf")));
        pdfp.open();
        Paragraph p=new Paragraph();
        p.setAlignment(Element.ALIGN_CENTER);
        p.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD,18,Font.BOLD));
         Paragraph po=new Paragraph();
        po.setAlignment(Element.ALIGN_CENTER);
        po.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD,16,Font.BOLD));
        
        Paragraph pd=new Paragraph();
          pd.setAlignment(Element.ALIGN_CENTER);
         pd.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD,14,Font.BOLD));
        p.add("ITHANGA SECONDARY SCHOOL");
        po.add("PO.BOX 238  ITHANGA THIKA");
         pd.add(new Date().toString());
        pdfp.add(p);
        pdfp.add(po);
        pdfp.add(pd);
         
        
      
        pdfp.add(new Paragraph("\n.................................................................."
                + ".................................................................................\n"));
         String []names=  n.findSubjectname();
        PdfPTable tbl=new PdfPTable(names.length+6);
        tbl.setWidths(new float[]{1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1});
       // tbl.setWidthPercentage(100);
        tbl.setTotalWidth(575);
        tbl.setLockedWidth(true);
         PdfPTable tbl1=new PdfPTable(names.length+6);
        tbl1.setWidths(new float[]{1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1});
       // tbl.setWidthPercentage(100);
        tbl1.setTotalWidth(575);
        tbl1.setLockedWidth(true);
     PdfPCell cell=new PdfPCell (new Paragraph("RESULTS"));
     cell.setColspan((names.length+4)*2);
     cell.setBackgroundColor(Color.CYAN);
        tbl1.addCell(cell);
        tbl1.addCell("Pos");
          tbl1.addCell("id");
          tbl1.addCell("Name");
          int a;
          
          for(a=0;a<names.length;a++){
              String s=names[a];
               tbl1.addCell(s.substring(0,Math.min(s.length(), 3)));
          }

        tbl1.addCell("Tot");
       tbl1.addCell("Ave");
          tbl1.addCell("Agg");
           pdfp.add(tbl1);
           
        
       
         try{
         
  String []  Subjects=n.findSubjectid();
  String []  Subjectsnames=n.findSubjectname();
  int subjectCount=Subjects.length;
          ArrayList<ExamDbDataHolder> users = ListUsers(this.sid.getText());
          
           for (int i = 0; i < users.size(); i++)
    {
         tbl.addCell(String.valueOf(i+1));
        String id=((ExamDbDataHolder)users.get(i)).getSid();
     String nam=   nn.getStudentName(id);
        String name="Eric";
        tbl.addCell(id);
        tbl.addCell(nam);
          int c=2;
          for(int s=0;s<Subjects.length;s++){
          
          if(Subjects[s].equals("s1")){
              tbl.addCell(((ExamDbDataHolder)users.get(i)).getMathematics());
            //  JOptionPane.showMessageDialog(null,((ExamDbDataHolder)users.get(i)).getMathematics());
           // String maths = ((ExamDbDataHolder)users.get(i)).getMathematics();
             c++;  
          }
          else if(Subjects[s].equals("s2")){
                tbl.addCell(((ExamDbDataHolder)users.get(i)).getEnglish());
               //row[c] = ((ExamDbDataHolder)users.get(i)).getEnglish();
               c++;
          } 
          else if(Subjects[s].equals("s3")){
               tbl.addCell(((ExamDbDataHolder)users.get(i)).getKiswahili());
              // row[c] = ((ExamDbDataHolder)users.get(i)).getKiswahili();
               c++;
          }
          else if(Subjects[s].equals("s4")){
               tbl.addCell(((ExamDbDataHolder)users.get(i)).getPhysics());
            //   row[c] = ((ExamDbDataHolder)users.get(i)).getPhysics();
               c++;
          }
          else if(Subjects[s].equals("s5")){
              tbl.addCell(((ExamDbDataHolder)users.get(i)).getChemistry());
              // row[c] = ((ExamDbDataHolder)users.get(i)).getChemistry();
               c++;
          }
          else if(Subjects[s].equals("s6")){
               tbl.addCell(((ExamDbDataHolder)users.get(i)).getBiology());
            //   row[c] = ((ExamDbDataHolder)users.get(i)).getBiology();
               c++;
          }
          else if(Subjects[s].equals("s7")){
               tbl.addCell(((ExamDbDataHolder)users.get(i)).getHistory());
             //  row[c] = ((ExamDbDataHolder)users.get(i)).getHistory();
               c++;
          }
          else if(Subjects[s].equals("s8")){
               tbl.addCell(((ExamDbDataHolder)users.get(i)).getGeography());
              // row[c] = ((ExamDbDataHolder)users.get(i)).getGeography();
               c++;
          }
          else if(Subjects[s].equals("s9")){
               tbl.addCell(((ExamDbDataHolder)users.get(i)).getCre());
             //  row[c] = ((ExamDbDataHolder)users.get(i)).getCre();
               c++;
          }
          else if(Subjects[s].equals("s10")){
               tbl.addCell(((ExamDbDataHolder)users.get(i)).getIre());
            //   row[c] = ((ExamDbDataHolder)users.get(i)).getIre();
               c++;
          }
          else if(Subjects[s].equals("s11")){
               tbl.addCell(((ExamDbDataHolder)users.get(i)).getHre());
            //   row[c] = ((ExamDbDataHolder)users.get(i)).getHre();
               c++;
          }
          else if(Subjects[s].equals("s12")){
               tbl.addCell(((ExamDbDataHolder)users.get(i)).getAgriculture());
            //   row[c] = ((ExamDbDataHolder)users.get(i)).getAgriculture();
               c++;
          }
          else if(Subjects[s].equals("s13")){
               tbl.addCell(((ExamDbDataHolder)users.get(i)).getHomescience());
             //  row[c] = ((ExamDbDataHolder)users.get(i)).getHomescience();
               c++;
          }
          else if(Subjects[s].equals("s14")){
               tbl.addCell(((ExamDbDataHolder)users.get(i)).getArtdesign());
            //   row[c] = ((ExamDbDataHolder)users.get(i)).getArtdesign();
               c++;
          }
          else if(Subjects[s].equals("s15")){
               tbl.addCell(((ExamDbDataHolder)users.get(i)).getComputer());
              // row[c] = ((ExamDbDataHolder)users.get(i)).getComputer();
               c++;
               
          }
          else if(Subjects[s].equals("s16")){
               tbl.addCell(((ExamDbDataHolder)users.get(i)).getBuilding());
              // row[c] = ((ExamDbDataHolder)users.get(i)).getBuilding();
               c++;
          }
          else if(Subjects[s].equals("s17")){
               tbl.addCell(((ExamDbDataHolder)users.get(i)).getWoodwork());
             //  row[c] = ((ExamDbDataHolder)users.get(i)).getWoodwork();
               c++;
          }
          else if(Subjects[s].equals("s18")){
               tbl.addCell(((ExamDbDataHolder)users.get(i)).getMetalwork());
             //  row[c] = ((ExamDbDataHolder)users.get(i)).getMetalwork();
               c++;
          }
          else if(Subjects[s].equals("s19")){
               tbl.addCell(((ExamDbDataHolder)users.get(i)).getMusic());
            //   row[c] = ((ExamDbDataHolder)users.get(i)).getMusic();
               c++;
          }
          else if(Subjects[s].equals("s20")){
               tbl.addCell(((ExamDbDataHolder)users.get(i)).getFrench());
              // row[c] = ((ExamDbDataHolder)users.get(i)).getFrench();
               c++;
          }
          else if(Subjects[s].equals("s21")){
               tbl.addCell(((ExamDbDataHolder)users.get(i)).getGerman());
             //  row[c] = ((ExamDbDataHolder)users.get(i)).getGerman();
               c++;
          }
          else if(Subjects[s].equals("s22")){
               tbl.addCell(((ExamDbDataHolder)users.get(i)).getArabic());
              // row[c] = ((ExamDbDataHolder)users.get(i)).getArabic();
               c++;
          }
          else if(Subjects[s].equals("s23")){
               tbl.addCell(((ExamDbDataHolder)users.get(i)).getBusiness());
             //  row[c] = ((ExamDbDataHolder)users.get(i)).getBusiness();
               c++;
          }
             
          }
          
          int tt=((ExamDbDataHolder)users.get(i)).getTotal(); 
           tbl.addCell(String.valueOf(tt));
           float g=Float.valueOf(tt)/11;
           tbl.addCell(String.format("%.1f", g));
           String gr=nn.checkGrade(yearid,String.format("%.1f", g));
           tbl.addCell(gr);
          
    }
           pdfp.add(tbl);
     }
     catch(Exception j){
         j.printStackTrace();
     }
        
        
        
        
    
        pdfp.close();
        
        
        
        
        
    // Image img=new Image.getInstance("j.png");
        
    } catch (DocumentException ex) {
        Logger.getLogger(ViewResults.class.getName()).log(Level.SEVERE, null, ex);
    } catch (FileNotFoundException ex) {
        Logger.getLogger(ViewResults.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
}
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      itextPrint();
        
//        MessageFormat h= new MessageFormat("RESULTS");
//      
//        
//        try{
//           table.print(JTable.PrintMode.FIT_WIDTH,h,null);
//               
//           }
//           
//        catch (PrinterException ex) {
//        Logger.getLogger(ViewResults.class.getName()).log(Level.SEVERE, null, ex);
//    }
       
    }//GEN-LAST:event_jButton2ActionPerformed
private void sorter(int column){
     TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        
        List<RowSorter.SortKey>sortKey=new ArrayList<>();
        
        int colToSort=column;
        sortKey.add(new RowSorter.SortKey(colToSort, SortOrder.DESCENDING));
        
        sorter.setSortKeys(sortKey);
        sorter.sort();
    
    
}
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
            java.util.logging.Logger.getLogger(ViewResults.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewResults.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewResults.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewResults.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewResults().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBoxExam;
    private javax.swing.JComboBox<String> jComboBoxStreams;
    private javax.swing.JComboBox<String> jComboBoxSubjects;
    private javax.swing.JComboBox<String> jComboBoxTerms;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField sid;
    private javax.swing.JTable table;
    private javax.swing.JTable tblCounter;
    private com.toedter.calendar.JYearChooser yearchooser;
    // End of variables declaration//GEN-END:variables
 public String subjectNameToId(String name){
        String subjectid=null;
         switch (name) {
        case "Mathematics":
           subjectid="s1";
           break;
        case "English":
          subjectid="s2";
          break;
         case "Kiswahili":
           subjectid="s3";
           break;
           
            case "Physics":
           subjectid="s4";
           break;
            case "Chemistry":
           subjectid="s5";
           break;
            case "Biology":
           subjectid="s6";
           break;
            case "History":
           subjectid="s7";
           break;
            case "Geography":
           subjectid="s8";
           break;
            case "Cre":
           subjectid="s9";
           break;
            case "Ire":
           subjectid="s10";
           break;
            case "Hre":
           subjectid="s11";
           break;
            case "Agriculture":
           subjectid="s12";
           break;
            case "Home-science":
          subjectid="s13";
           break;
            case "Art-design":
           subjectid="s14";
           break;
            case "Computer":
           subjectid="s15";
           break;
            case "B/c":
           subjectid="s16";
           break;
            case "Woodwork":
           subjectid="s17";
           break;
            case "Metalwork":
          subjectid="s18";
           break;
            case "Music":
           subjectid="s19";
           break;
            case "French":
           subjectid="s20";
           break;
            case "German":
           subjectid="s21";
           break;
            case "Arabic":
           subjectid="s22";
           break;
            case "Business":
           subjectid="s23";
           break;
           
          
       }
        
      return subjectid;  
    }
}
