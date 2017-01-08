/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author kimani kogi
 */
public class methods {
     public Connection getConnection()
  {
      //for client server situations ,unomment the following line getpath();
      //then comment line 103=path="localhost";
    //getPath();
    Connection con = null;
    try
    {
    String path;
   String db=":3306/sms";
   String jdbc="jdbc:mysql://";
   String user="root";
   String pass="123ERYcog.";
   //for client server instance comment next line path =localhost
   path="localhost";
   String dbp=(jdbc+path+db);
        
      con = DriverManager.getConnection(dbp,user,pass);
     // con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "123ERYcog.");
    }
    catch (Exception ex)
    {
      System.out.println(ex.getMessage());
    }
    return con;
  }
  
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
        
        //DefaultTableModel model = (DefaultTableModel)this.table.getModel();
        
        //model.setRowCount(0);
        
        //findUsers();
        
      //  JOptionPane.showMessageDialog(null,  message + " Succefully");
      }
      else
      {
      //  JOptionPane.showMessageDialog(null, "Not " + message);
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
      
      
      //gets ids Subjects offered
       public ArrayList<String> Subjects()
  {
    ArrayList<String> usersList = new ArrayList<String>();
    try
    {
      
        Connection con = getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
      String searchQuery = "SELECT * FROM `subjects` ";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
          
        //usersList.add(rs.getString("name"));
         usersList.add(rs.getString("id"));
        //usersList.add(user);
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
       
       
       //gets names of subjects offerd
              public ArrayList<String> Subjectsnames()
  {
    ArrayList<String> usersList = new ArrayList<String>();
    try
    {
      
        Connection con = getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
      String searchQuery = "SELECT * FROM `subjects` ";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
          
        //usersList.add(rs.getString("name"));
         usersList.add(rs.getString("subjectname"));
        //usersList.add(user);
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
              
              
              //gets available class streams for all forms
  public ArrayList<String> Streams()
  {
    ArrayList<String> usersList = new ArrayList<String>();
    try
    {
      
        Connection con = getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
      String searchQuery = "SELECT * FROM `streams` ";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
          
        usersList.add(rs.getString("streamname"));
        
        //usersList.add(user);
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
  
  
   public ArrayList<String> ExamsTypes()
  {
    ArrayList<String> usersList = new ArrayList<String>();
    try
    {
      
        Connection con = getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
      String searchQuery = "SELECT * FROM `examtypes` ";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
          
        usersList.add(rs.getString("examname"));
        
        //usersList.add(user);
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
   
   //gets exams types id
    public ArrayList<String> ExamsTypesId()
  {
    ArrayList<String> usersList = new ArrayList<String>();
    try
    {
      
        Connection con = getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
      String searchQuery = "SELECT * FROM `examtypes` ";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {          

        usersList.add(rs.getString("autoid"));
        
        //usersList.add(user);
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
  
  
  //gets grading settings
    public ArrayList<String> GradingSubjects()
  {
    ArrayList<String> usersList = new ArrayList<String>();
    try
    {
      
        Connection con = getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
      String searchQuery = "SELECT * FROM `subjects` ";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
          
       // usersList.add(rs.getString("id"));
        usersList.add(rs.getString("name"));
        
        //usersList.add(user);
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
     public ArrayList<String> Grading()
  {
    ArrayList<String> usersList = new ArrayList<String>();
    try
    {
      
        Connection con = getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
      String searchQuery = "SELECT * FROM `subjects` ";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
          
       // usersList.add(rs.getString("id"));
        usersList.add(rs.getString("name"));
        
        //usersList.add(user);
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
    public String getStudentName(String id){
        String name=null;
        
         try
    {
      
        Connection con = getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
      String searchQuery = "SELECT * FROM `students` WHERE `id`='"+id+"'";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
          
       // usersList.add(rs.getString("id"));
        name=(rs.getString("fname")+" "+rs.getString("lastname"));
        
        //usersList.add(user);
      }
      st.close();
      rs.close();
      con.close();
    }
    catch (Exception ex)
    {
      System.out.println(ex.getMessage());
    }
        
        
        
        
        
       return name;
    }
    String a,b,c,d,e,am,bm,cm,dm,bp,cp,dp;
public void getGrade(String form){
    
         try {
             Connection con = getConnection();
             // Connection con = getConnection();
             Statement st = con.createStatement();
             String searchQuery = "SELECT * FROM `grades` WHERE `form`='"+form+"'";
             ResultSet rs = st.executeQuery(searchQuery);
             while (rs.next())
             {
                   a=rs.getString("a");
                   b=rs.getString("b");
                   c=rs.getString("c");
                   d=rs.getString("d");
                   e=rs.getString("e");
                   am=rs.getString("am");
                   bm=rs.getString("bm");
                   cm=rs.getString("cm");
                   dm=rs.getString("dm");
                   bp=rs.getString("bp");
                   cp=rs.getString("cp");
                   dp=rs.getString("dp");
             //usersList.add(user);
             }  } catch (SQLException ex) {
             Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
         }
}
public String checkGrade(int form,String ave){
    getGrade(String.valueOf(form));
    
    String g=null;
  float avew=Float.valueOf(ave);
    
int a=1;
if(avew<Integer.valueOf(dm)){
    g="E";
    
}
else if(avew>Integer.valueOf(e)&&avew<=Integer.valueOf(dm)){
    g="D-";
}
else if(avew>Integer.valueOf(dm)&&avew<=Integer.valueOf(d)){
    g="D";
}
else if(avew>Integer.valueOf(d)&&avew<=Integer.valueOf(dp)){
    g="D+";
}
else if(avew>Integer.valueOf(dp)&&avew<=Integer.valueOf(cm)){
    g="C-";
}
else if(avew>Integer.valueOf(cm)&&avew<=Integer.valueOf(c)){
    g="C";
}
else if(avew>Integer.valueOf(c)&&avew<=Integer.valueOf(cp)){
    g="C+";
}
else if(avew>Integer.valueOf(cp)&&avew<=Integer.valueOf(bm)){
    g="B-";
}
else if(avew>Integer.valueOf(bm)&&avew<=Integer.valueOf(b)){
    g="B";
}
else if(avew>Integer.valueOf(b)&&avew<=Integer.valueOf(bp)){
    g="B+";
}
else if(avew>Integer.valueOf(bp)&&avew<=Integer.valueOf(am)){
    g="A-";
}
else if(avew>Integer.valueOf(am)){
    g="A";
}





return g;
}


public void getGradePerSubject(String id){
    
         try {
             Connection con = getConnection();
             // Connection con = getConnection();
             Statement st = con.createStatement();
             String searchQuery = "SELECT * FROM `subjects` WHERE `id`='"+id+"'";
             ResultSet rs = st.executeQuery(searchQuery);
             while (rs.next())
             {
                   a=rs.getString("A");
                   b=rs.getString("B");
                   c=rs.getString("C");
                   d=rs.getString("D");
                   e=rs.getString("E");
                   am=rs.getString("A-");
                   bm=rs.getString("B-");
                   cm=rs.getString("C-");
                   dm=rs.getString("D-");
                   bp=rs.getString("B+");
                   cp=rs.getString("C+");
                   dp=rs.getString("D+");
             //usersList.add(user);
             }  } catch (SQLException ex) {
             Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
         }
}
public String[] checkGrade(String ave ,String id){
    getGradePerSubject(id);
    
    String g=null;
    String r=null;
  float avew=Float.valueOf(ave);
    
//int aa=1;
if(avew<Integer.valueOf(dm)){
    g="E";
    r="Very poor";
    
}
else if(avew>=Integer.valueOf(dm)&&avew<Integer.valueOf(d)){
    g="D-";
    r="Very Poor";
}
else if(avew>=Integer.valueOf(d)&&avew<Integer.valueOf(dp)){
    g="D";
    r="Poor";
}
else if(avew>=Integer.valueOf(dp)&&avew<Integer.valueOf(cm)){
    g="D+";
    r="Very Low";
}
else if(avew>=Integer.valueOf(cm)&&avew<Integer.valueOf(c)){
    g="C-";
    r="Low";
}
else if(avew>=Integer.valueOf(c)&&avew<Integer.valueOf(cp)){
    g="C";
    r="Below Average";
}
else if(avew>=Integer.valueOf(cp)&&avew<Integer.valueOf(bm)){
    g="C+";
    r="Average";
}
else if(avew>=Integer.valueOf(bm)&&avew<Integer.valueOf(b)){
    g="B-";
    r="Above Average";
}
else if(avew>=Integer.valueOf(b)&&avew<Integer.valueOf(bp)){
    g="B";
    
    r="Fair";
}
else if(avew>=Integer.valueOf(bp)&&avew<Integer.valueOf(am)){
    g="B+";
  r=  "Good";
}
else if(avew>=Integer.valueOf(am)&&avew<Integer.valueOf(a)){
    g="A-";
    r="Very Good";
}
else if(avew>=Integer.valueOf(a)){
    g="A";
    r="Excellent";
}





return new String[]{g,r};
}

}
// gets types of exams offerd
