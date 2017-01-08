/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms;

import java.sql.ResultSet;
import javax.swing.table.TableModel;

/**
 *
 * @author kimani kogi
 */
public class ExamDbDataHolder {
    
  private String sid;
   private String uniq;
  private String maths;
  private String eng;
  private String kiswa;
  private String phy;
  private String chem;
  private String bio;
  private String hist;
  private String geo;
  private String cre;
 
  private String ire;
  private String hre;
  private String agri;
  private String home;
  private String art;
   private String comp;
  private String biulding;
  private String wood;
  private String metal;
  private String music;
  private String french;
  private String german;
  private String arabic;
  private String business;
  int total;
  static TableModel resultSetToTableModel(ResultSet result)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public ExamDbDataHolder(String Id,String Uniq, String Maths, String Eng, String Kiswa, String Phy,
          String Chem, String Bio, String Hist, String Geo,
          String Cre, String Hre,String Ire,String Agri,String Home,String ART,String Comp,String Building,
          String Wood,String Metal,String Music,String French,String German,String Arabic,String Business, int Total)
  {
    this.sid = Id;
     this.uniq = Uniq;
    this.maths = Maths;
    this.eng = Eng;
    this.kiswa = Kiswa;
     this.phy = Phy;
       this.chem = Chem;
     this.bio = Bio;
     this.hist = Hist;
     this.geo = Geo;
    this.cre = Cre;
      this.ire = Ire;
      this.hre = Hre;
      this.agri = Agri;
      this.home = Home;
      this.art = ART;
      this.comp = Comp;
      this.biulding = Building;
      this.wood = Wood;
      this.metal = Metal;
      this.music = Music;
      this.french = French;
      this.german = German;
    this.arabic = Arabic;
      this.business = Business;
       this.total = Total;
      
  }
  
  public String getSid()
  {
    return this.sid;
  }
    public String getUnique()
  {
    return this.uniq;
  }
  public String getMathematics()
  {
    return this.maths;
  }
  
  public String getEnglish()
  {
    return this.eng;
  }
  
  public String getKiswahili()
  {
    return this.kiswa;
  }
   public String getPhysics()
  {
    return this.phy;
  }   
     public String getChemistry()
  {
    return this.chem;
  }
        public String getBiology()
  {
    return this.bio;
  }
   public String getHistory()
  {
    return this.hist;
  }
    public String getGeography()
  {
    return this.geo;
  }
    public String getCre()
  {
    return this.cre;
  }
  
      public String getIre()
  {
    return this.ire;
  }
       public String getHre()
  {
    return this.hre;
  }
        public String getAgriculture()
  {
    return this.agri;
  }
         public String getHomescience()
  {
    return this.home;
  }
          public String getArtdesign()
  {
    return this.art;
  }
           public String getComputer()
  {
    return this.comp;
  }
            public String getBuilding()
  {
    return this.biulding;
  }
             public String getWoodwork()
  {
    return this.wood;
  }
              public String getMetalwork()
  {
    return this.metal;
  }
               public String getMusic()
  {
    return this.music;
  }
                public String getFrench()
  {
    return this.french;
  }
    public String getGerman()
  {
    return this.german;
  }
     public String getArabic()
  {
    return this.arabic;
  }
    public String getBusiness()
  {
    return this.business;
  }
      public int getTotal()
  {
    return this.total;
  }
   
}

