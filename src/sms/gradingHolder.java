/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms;

/**
 *
 * @author kimani kogi
 */
public class gradingHolder {
     private String id;
     private String name;
     
     private String a;
     private String b;
     private String c;
     private String d;
     private String e;
     
     private String aminus;
     private String bminus;
     private String cminus;
     private String dminus;
     
     private String bplus;
     private String cplus;
     private String dplus;
         
     
      public gradingHolder(String Id, String Name,
              String A,String B,String C,String D,String E,
              
              String AMINUS,String BMINUS,String CMINUS,String DMINUS,
              
              String BPLUS,String CPLUS,String DPLUS)
  {
     this.id = Id;
     this.name = Name;
     
     this.a=A;
     this.b=B;
     this.c=C;
     this.d=D;
     this.e=E;
     
     
     this.aminus=AMINUS;
     this.bminus=BMINUS;
     this.cminus=CMINUS;
     this.dminus=DMINUS;
     
     this.bplus=BPLUS;
     this.cplus=CPLUS;
     this.dplus=DPLUS;
   
  }
      
     public String getId()
  {
    return this.id;
  }
  
  public String getName()
  {
    return this.name;
  } 
   public String getA()
  {
    return this.a;
  }
    public String getB()
  {
    return this.b;
  }
    public String getC()
  {
    return this.c;
  }
    public String getD()
  {
    return this.d;
  }
    public String getE()
  {
    return this.e;
  }
    public String getA_Minus()
  {
    return this.aminus;
  }
    public String getB_Minus()
  {
    return this.bminus;
  }
    public String getC_Minus()
  {
    return this.cminus;
  }
     public String getD_Minus()
  {
    return this.dminus;
  }
  
     
 
    public String getB_Plus()
  {
    return this.bplus;
  }
    public String getC_Plus()
  {
    return this.cplus;
  }
     public String getD_Plus()
  {
    return this.dplus;
  }
      
      
      
      
}
