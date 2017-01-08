/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
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
public class Form1Exams extends javax.swing.JFrame {
DefaultTableModel model = new DefaultTableModel();
static int aa;
  //subject to be enterd  ,converted to lower case
String subjectin="null";
//id of the examtype  selected
static int examid=0;
  //term of year ie first term ,second term etc
  int termid=0;
  // year of school
static int yearid=0;
//subject id
 String subjectid;
  
  int cntrltbl=0;
  static String title;
 
   String filePath;
   String fileurlp = null;
   int found=0;
    /**
     * Creates new form Form1Exams
     */
    public Form1Exams() {
        
        
    this.table = new JTable(this.model);
    this.sid = new JTextField();
    final TableRowSorter<TableModel> rowSorter = new TableRowSorter(this.table.getModel());
    this.table.setRowSorter(rowSorter);
    sid.getDocument().addDocumentListener(new DocumentListener()
    {
      public void insertUpdate(DocumentEvent e)
      {
        String text = Form1Exams.this.sid.getText();
        if (text.trim().length() == 0) {
          rowSorter.setRowFilter(null);
        } else {
          rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, new int[0]));
        }
      }
      
      public void removeUpdate(DocumentEvent e)
      {
        String text = Form1Exams.this.sid.getText();
        if (text.trim().length() == 0) {
          rowSorter.setRowFilter(null);
        } else {
          rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, new int[0]));
        }
      }
      
      public void changedUpdate(DocumentEvent e)
      {
        throw new UnsupportedOperationException("Not supported yet.");
      }
    });
        
        initComponents();
        setSubjectsModel();
        setExamsModel();
        actionPerformed();
       // findUsers();
       //action and statechanged listner for term radio buttons 
  
         //title();
    }
  //  public void setTitle(int yr){
        
   // }
    private void setSubjectsModel() {
        jComboBoxSubjects.removeAllItems();
        jComboBoxSubjects.addItem("Choose Subjects");
        String []subs=findSubjectname();
        for (int a = 0; a < subs.length; a++) {

            jComboBoxSubjects.addItem(subs[a]);
        }
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
  
    public int getExamId( String choice){
        int xamId=0;
    try {
        methods m= new methods();
        Connection con = m.getConnection();
        // Connection con = getConnection();
        Statement st = con.createStatement();
        String searchQuery = "SELECT `autoid` FROM `examtypes` WHERE `examname`='"+choice+"' ";
        ResultSet rs = st.executeQuery(searchQuery);
        while (rs.next())
        {
            
           xamId=(Integer.valueOf(rs.getString("autoid")));
            
            //usersList.add(user);
        }
        st.close();
        rs.close();
        con.close();
    } catch (SQLException ex) {
        Logger.getLogger(Form1Exams.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        return xamId;
        
    }
    //setTitle
    public void title(String title){
        this.setTitle(title);
    }
    //students profile picture 
     public void showimg()
    throws Exception
  {
    this.icon.setIcon(null);
    this.icon.setText(" no image");
    try
    {
        methods m=new methods();
       // Connection con = m.getConnection();
      Connection con = m.getConnection();
      Statement st2 = con.createStatement();
      
      ResultSet res7 = st2.executeQuery("SELECT imgurl FROM students  WHERE id='" + this.sid.getText() + "'");
      if (res7.next())
      {
        this.filePath = res7.getString("imgurl");
        
        st2.close();
        res7.close();
        con.close();
        String op = "image";
        if (this.filePath.equals(op))
        {
          this.icon.setIcon(null);
          this.icon.setIcon(null);
          this.icon.setText(" no image");
        }
        else
        {
          BufferedImage img = null;
          try
          {
           img = ImageIO.read(new java.io.File(this.filePath));
            this.fileurlp = this.filePath.replace("\\", "\\\\");
          }
          catch (IOException e)
          {
           // JOptionPane.showMessageDialog(null, "error loading image \n  make sure image is in images folder ");
            
            this.icon.setIcon(null);
            this.icon.setText(" no image");
          }
          Image dimg = img.getScaledInstance(this.icon.getWidth(), this.icon.getHeight(), 4);
          
          ImageIcon icon = new ImageIcon(dimg);
          this.icon.setText("");
          this.icon.setIcon(icon);
        }
      }
      else
      {
           this.icon.setText(" no image");
        //JOptionPane.showMessageDialog(null, "error loading image \n  make sure image is in images folder ");
      }
    }
    catch (Exception ex)
    {
      System.out.println(ex.getMessage());
    }
  }
  
     //fetch exam resuts
public ArrayList<ExamDbDataHolder> ListUsers(String ValToSearch)
  {
    ArrayList<ExamDbDataHolder> usersList = new ArrayList();
    try
    {
     methods m=new methods();
        Connection con = m.getConnection();
      Statement st = con.createStatement();
     //  Statement st1 = con.createStatement();
      String searchQuery = "SELECT * FROM `exam` WHERE `sid` ='" + ValToSearch + "'AND `yearid`='"+yearid+"'";
      // String s="SELECT 1+2+3+4+5+6+`7`+``+agri+cre+bs From `exam`WHERE CONCAT(`sid`) LIKE '%" + ValToSearch + "%' ";
      //String searchQuery = "SELECT * FROM `exam` WHERE CONCAT(`sid`) LIKE '%" + ValToSearch + "%'";
      ResultSet rs = st.executeQuery(searchQuery);
       int a=0;
     // ResultSet rs1 = st1.executeQuery(s);
      while (rs.next())

         
      {
        ExamDbDataHolder user = new ExamDbDataHolder(rs.getString("sid"),rs.getString("uniq"), rs.getString("s1"), rs.getString("s2"), rs.getString("s3")
                ,rs.getString("s4"),rs.getString("s5"),rs.getString("s6"),rs.getString("s7"),rs.getString("s8"),
                rs.getString("s9"),rs.getString("s10"),rs.getString("s11"),rs.getString("s12"),rs.getString("s13"),rs.getString("s14")
                ,rs.getString("s15"),rs.getString("s16"),rs.getString("s17"),rs.getString("s18"),rs.getString("s19"),rs.getString("s20")
                ,rs.getString("s21"),rs.getString("s22"),rs.getString("s23"),a);
        
        usersList.add(user);
      }
      st.close();
      rs.close();
      con.close();
    }
    catch (Exception ex)
    {
      System.out.println(ex.getMessage());
    }
    return usersList;
  }
String classs="null";
//get Students name and class
    public void select()
    throws Exception
  {
      methods m=new methods();
        Connection con = m.getConnection();
    //Connection con = getConnection();
    Statement st2 = con.createStatement();
    //added where form=? to avoid filing form 1 marks  in form 2 jframe and such 
    //12/10/2016 saturday 
   String yr=String.valueOf(yearid);
    ResultSet res7 = st2.executeQuery("SELECT DISTINCT fname,class FROM students  WHERE id='" + this.sid.getText() + "' AND form='"+yr+"'");
    Statement st = con.createStatement();
  
    ResultSet res = st.executeQuery("SELECT class FROM students  WHERE id='" + this.sid.getText() + "'AND form='"+yr+"'");
  
    if (res7.next())
    {
        classs=res7.getString("class");
      this.sname.setText(res7.getString("fname"));
     
    }
    else{
        found=1;
        JOptionPane.showMessageDialog(null, "not found");
    }
    st2.close();
   res7.close();
  res.close();
  st.close();
    con.close();
  }
    //find subjects id of offered subjects from db
     public  String[] findSubjectid()
  {
      methods m=new methods();
      String [] subjectsid;
     ArrayList<String> usersList =  m.Subjects();
    subjectsid=new String[usersList.size()];
    usersList.toArray(subjectsid);
     return subjectsid;
  }
   //find subject names of offerd subjects from db
   public  String[] findSubjectname()
  {
      methods m=new methods();
      String [] subjectsnames;
     ArrayList<String> usersList =  m.Subjectsnames();
    subjectsnames=new String[usersList.size()];
    usersList.toArray(subjectsnames);
    
   return subjectsnames;
  }
   //display exams results in jtable
  public void findUsers()
  {
  String []  Subjects=findSubjectid();
  String []  Subjectsnames=findSubjectname();
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
    String col[]={"Adm","Uniq"};
    
   // String [] subjectsIdentifiers={}
    String [] both=(String[])ArrayUtils.addAll(col,Subjectsnames);
    
    
    model.setColumnIdentifiers(both);
    Object[] row = new Object[subjectCount+4];
    for (int i = 0; i < users.size(); i++)
    {
       
      row[0] = ((ExamDbDataHolder)users.get(i)).getSid();
      
      row[1] = ((ExamDbDataHolder)users.get(i)).getUnique();
      
       int c=2;
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
      
      
      
//      row[2] = ((ExamDbDataHolder)users.get(i)).getMathematics();
//      row[3] = ((ExamDbDataHolder)users.get(i)).getEng();
//      row[4] = ((ExamDbDataHolder)users.get(i)).getKiswa();
//      row[5] = ((ExamDbDataHolder)users.get(i)).getPhy();
//      row[6] = ((ExamDbDataHolder)users.get(i)).getChem();
//      row[7] = ((ExamDbDataHolder)users.get(i)).getBio();
//      row[8] = ((ExamDbDataHolder)users.get(i)).getHist();
//      row[9] = ((ExamDbDataHolder)users.get(i)).getGeo();
//      row[10] = ((ExamDbDataHolder)users.get(i)).getCre();
//      row[11] = ((ExamDbDataHolder)users.get(i)).getAgri();
//      row[12] = ((ExamDbDataHolder)users.get(i)).getBs();
    
      
      model.addRow(row);
    }
    this.table.setModel(model);
  }
  //execute all queries
   public void executeSQlQuery(String query, String message)
           
  {
   methods m=new methods();
   Connection con = m.getConnection();
    try
    {
      Statement st = con.createStatement();
      if (st.executeUpdate(query) == 1)
      {
        //  Form1Exams n=new Form1Exams();
        
        DefaultTableModel model = (DefaultTableModel)this.table.getModel();
        
        model.setRowCount(0);
        
        findUsers();
        //clear();
        JOptionPane.showMessageDialog(null, "Marks " + message + " Succefully");
      }
      else
      {
        JOptionPane.showMessageDialog(null, "Marks Not " + message);
       // JOptionPane.showMessageDialog(null, "MAKE SURE THE BOOK ID IS NOT ALREADY BEING USED \nCHECK THE BOOK DETAILS ENTERED FOR ERRORS ie ID SHOULD BE NUMBERS ONLY");
      }
      st.close();
      con.close();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }
//refresh table after deletion,insertion,update
  public void table(){
      //  Form1Exams n=new Form1Exams();
          
        DefaultTableModel model = (DefaultTableModel)this.table.getModel();
        
        model.setRowCount(0);
        
        findUsers();
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        sname = new javax.swing.JTextField();
        jComboBoxSubjects = new javax.swing.JComboBox<>();
        subject = new javax.swing.JTextField();
        sid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        icon = new javax.swing.JLabel();
        jButtonEnter = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButtonUpdate = new javax.swing.JButton();
        subjectup = new javax.swing.JTextField();
        lblsubject = new javax.swing.JLabel();
        jComboBoxExam = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        table.setBackground(new java.awt.Color(204, 255, 204));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowHeight(40);
        table.setRowMargin(4);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        sname.setEditable(false);

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

        sid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sidActionPerformed(evt);
            }
        });
        sid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sidKeyReleased(evt);
            }
        });

        jLabel2.setText("ADM NO");

        jLabel3.setText("NAME");

        icon.setText("image");

        jButtonEnter.setText("ENTER");
        jButtonEnter.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jButtonEnterStateChanged(evt);
            }
        });
        jButtonEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnterActionPerformed(evt);
            }
        });

        jButtonClear.setText("CLEAR");
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chose Term", "Term 1", "Term 2", "Term 3" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jButtonUpdate.setText("UPDATE");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        subjectup.setEditable(false);

        lblsubject.setText("Subject");

        jComboBoxExam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxExamItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(icon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxSubjects, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sid, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonClear, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonUpdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(subjectup, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sname)
                            .addComponent(subject)
                            .addComponent(jLabel3)
                            .addComponent(jButtonEnter, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(jComboBoxExam, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, 0, 141, Short.MAX_VALUE)))
                    .addComponent(lblsubject))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(icon, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sname, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(subject, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxSubjects, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonEnter)
                            .addComponent(jButtonClear))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(lblsubject)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(subjectup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonUpdate)
                        .addGap(0, 36, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(jComboBoxExam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxSubjectsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSubjectsActionPerformed
      
    }//GEN-LAST:event_jComboBoxSubjectsActionPerformed

    private void jComboBoxSubjectsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxSubjectsItemStateChanged
    
      cntrltbl();

      if(evt.getItem()!=""&&evt.getStateChange()==ItemEvent.SELECTED&&evt.getItem()!="Choose Subjects"){

      subjectin=evt.getItem().toString();
      subjectNameToId(subjectin);

     }
      else{
         subjectin="null"; 
         subjectid="";
      }

    }//GEN-LAST:event_jComboBoxSubjectsItemStateChanged
 private void jButtonEnterActionPerformed(java.awt.event.ActionEvent evt) {                                         
     found=0;
     try {
             select();
         } catch (Exception ex) {
             Logger.getLogger(Form1Exams.class.getName()).log(Level.SEVERE, null, ex);
         }
     if(found==0){
     cntrltbl();
     insertvalidator();
     }
     else{
         ;
         JOptionPane.showMessageDialog(null, "CANT FIND SUCH STUDENT");
     }
    } 
 
  public void insertvalidator(){
       if (subjectid.equals("")||examid==0) {
            JOptionPane.showMessageDialog(null, "Select Exam And Subject");
            
           
     }
       else{
         
           validatetermid();
  
    
       }
    
}
  public void validatetermid(){
      if(termid==0||subjectin.equals("null")){
          
          JOptionPane.showMessageDialog(null, "You must select The term");
      }
      else{
      insertquery();
      }
      
  }
 
 
 public void insertquery(){

   methods m=new methods();
    String sidi = this.sid.getText();
    String sname = this.sname.getText();
    String subject = this.subject.getText();
    if ((sidi.equals(""))  || (subject.equals(""))) {
      JOptionPane.showMessageDialog(null, "make sure all fields are filed");
    } 
    else {
        try{
     String uniq=unig();
     String stru = uniq;
        
        Connection con = m.getConnection();
        String str = "";
        
        str = "select * from exam where  uniq =?";
        
        PreparedStatement pst = con.prepareStatement(str);
        
        pst.setString(1, stru);
        
        ResultSet rs = pst.executeQuery();
        if (rs.next())
        {
             // JOptionPane.showMessageDialog(null, uniq+"A Student WITH SUCH an ID  (" + this.sid.getText() + ")  ALREADY EXIST \n ");
       
            checksubject();
          //JOptionPane.showMessageDialog(null, uniq+"A Student WITH SUCH an ID  (" + this.sid.getText() + ")  ALREADY EXIST \n ");
       
        }
        else{
            insert();
        }
        pst.close();
        rs.close();
        con.close();
    }
        catch(Exception a){
            
        }

   }
 }
 public void checksubject(){
     
 try{
     methods m=new methods();
       String uniq=unig();
     String stru = uniq;
        String sidi = this.sid.getText();
    String sname = this.sname.getText();
    String subject = this.subject.getText(); 
     String  str = "select "+subjectid+" from exam where  uniq =?";
    // String stru=sidi;  
      Connection con = m.getConnection();
        PreparedStatement pst = con.prepareStatement(str);
      
        pst.setString(1, stru);
      
        
        ResultSet rs = pst.executeQuery();
       
        if (rs.next())
        {
          //  String d=String.valueOf(subjectid);
            String y=rs.getString(subjectid);
             //JOptionPane.showMessageDialog(null,y);
            if(y.equals("0")){
               update();
            }
             else
            {
            JOptionPane.showMessageDialog(null, "Subject marks for this subject already enterd");
         
        }
            
        con.close();
        rs.close();
        pst.close();
        }
        else{
             JOptionPane.showMessageDialog(null,"Error");
         
           // insert();
        }
        }
        catch(Exception a){
            a.printStackTrace();
                JOptionPane.showMessageDialog(null,a+" Error");
                }
 
 }
  public void update(){
   String uniq=unig();
    // methods m=new methods();
      String query1 = "UPDATE `exam` SET `"+subjectid+"`='" + this.subject.getText() + "'"
                       + " WHERE `uniq` = '" + uniq + "'";
       executeSQlQuery(query1, "Updated");
       
 }
 
 public String unig(){
     Calendar cal=new GregorianCalendar();
  String sidi = this.sid.getText();
  String yr=String.valueOf(yearid);
  String termi=String.valueOf(termid);
  String exami=String.valueOf(examid);
 String year=String.valueOf(cal.get(Calendar.YEAR));
 //JOptionPane.showMessageDialog(null, year);
  String uniq=yr+termi+exami+sidi+year;
 // JOptionPane.showMessageDialog(null,uniq);
  return uniq;
 }
 public void insert(){
     String uniq=unig();
     String yr=String.valueOf(yearid);
     // methods m=new methods();
    // String classs="west";
     String query = "INSERT INTO `exam`(`yearid`,`sid`,`class`, `"+subjectid+"`,`examid`,`termid`,`uniq`,`updated_at`) VALUES ('"+yr+"','" 
             + this.sid.getText() + "','" + classs+ "','" + this.subject.getText() + "','" + examid + "','" + termid + "','" + uniq + "',now())";
          
          executeSQlQuery(query, "Inserted");
 }
    private void jButtonEnterStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jButtonEnterStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonEnterStateChanged

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        clear();
    }//GEN-LAST:event_jButtonClearActionPerformed
public void clear(){
    sid.setText("");
    sname.setText("");
    subject.setText("");
    subjectup.setText("");
    subjectup.setEditable(false);
    icon.setIcon(null);
    examid=0;
    uniqn="0000";
   termid=0;
   examid=0;
   subjectid="";
   subjectin="null";
    jComboBoxSubjects.setSelectedIndex(0);
    jComboBox2.setSelectedIndex(0);
    jComboBoxExam.setSelectedIndex(0);
    filePath=null;
    fileurlp = null;
    
}
    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
     cntrltbl();
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
        
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void sidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sidKeyReleased
     this.icon.setText(""); 
     
//         try {
//             select();
//         } catch (Exception ex) {
//             Logger.getLogger(Form1Exams.class.getName()).log(Level.SEVERE, null, ex);
//         }
        cntrltbl();
        findUsers();
    }//GEN-LAST:event_sidKeyReleased

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
    if(cntrltbl==0){
        JOptionPane.showMessageDialog(null, "no selected item");
    }  
    else{
       String a= subjectNameToId(lblsubject.getText());
        
        updatein(a);
    }
    }//GEN-LAST:event_jButtonUpdateActionPerformed
public void updatein(String a){
    String to="0";
    // String uniq=handleTableMouseEvent();
    // methods m=new methods();
    if(subjectup.getText()==""){
        to="0";
        
    }
    else{
         to=subjectup.getText();
    }
      String query1 = "UPDATE `exam` SET `"+a+"`='" + to + "'"
                       + " WHERE `uniq` = '" + uniqn+ "'";
       executeSQlQuery(query1, "Updated");
      // subjectup.setEditable(true);
      subjectup.setText("");
}
    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
  int a=0;
  cntrltbl=1;
     int index= jComboBoxSubjects.getSelectedIndex();
     if(index!=0){
    int i = this.table.getSelectedRow();
    
    TableModel model = this.table.getModel();
    lblsubject.setText(subjectin);
    subjectup.setEditable(true);
    this.subjectup.setText(model.getValueAt(i, index+1).toString());
    uniqn=(model.getValueAt(i, 1).toString());
//  String []subs=findSubjectname();
//  for(int i=0;i<subs.length;i++){
//      if(subjectin.equals(subs[i])){
//         // JOptionPane.showMessageDialog(null, subjects[i]);
//          a=i+2;
//         handleTableMouseEvent(a);  
    //  }
 // }
     }
     
    
  
    }//GEN-LAST:event_tableMouseClicked

    private void jComboBoxExamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxExamItemStateChanged
 if(evt.getItem()!=""&&evt.getStateChange()==ItemEvent.SELECTED&&evt.getItem()!="Choose Exam"){

      examid=getExamId(evt.getItem().toString());
    //  subjectNameToId(exam);

     }
      else{
      examid=0;  
      }      
    }//GEN-LAST:event_jComboBoxExamItemStateChanged

    private void sidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sidActionPerformed
public void cntrltbl(){
     cntrltbl=0;
     lblsubject.setText("Subject");
     subjectup.setText("");
}
    public String handleTableMouseEvent(){
   
       int index= jComboBoxSubjects.getSelectedIndex();
    int i = this.table.getSelectedRow();
    
    TableModel model = this.table.getModel();
    lblsubject.setText(subjectin);
    this.subjectup.setText(model.getValueAt(i, index).toString());
    uniqn=(model.getValueAt(i, 1).toString());
    return uniqn;
}
    String uniqn="0000";
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
            java.util.logging.Logger.getLogger(Form1Exams.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form1Exams.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form1Exams.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form1Exams.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form1Exams().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel icon;
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonEnter;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBoxExam;
    private javax.swing.JComboBox<String> jComboBoxSubjects;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblsubject;
    private javax.swing.JTextField sid;
    private javax.swing.JTextField sname;
    private javax.swing.JTextField subject;
    private javax.swing.JTextField subjectup;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

private void actionPerformed(){
    
    
    
    
      
       // sid.setText(A);
         this.sid.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent b)
              
      {
          icon.setIcon(null);
          found=0;
        try
        {
            select();
            if(found==0){
            showimg();
            }
        }
        catch (Exception ex)
        {
          Logger.getLogger(Form1Exams.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    });
}
  //convert subject name to id
    public String subjectNameToId(String name){
        
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
