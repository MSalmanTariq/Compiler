/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexical.anallyzer;

import java.util.ArrayList;

/**
 *
 * @author Roshaann 2.7 gpa
 */
public class REs {static boolean inputIsInteger(String input) {
      String regex = "^[-+]?\\d+$"; // INTEGER RE
      return input.matches(regex);
    }
static boolean inputIsFloat(String input) {
     String regex = "[-+]?[0-9]*\\.?[0-9]*[f]?"; // FLOAT RE
      return input.matches(regex);
    }
static boolean inputIsID(String input) {
      String regex = "[n][\\w]*[a]";  // IDENTIFIER RE
      
      return input.matches(regex);
      
    }

static void checkInt(String s){
    
      boolean flag = false;
      flag = inputIsInteger(s);
      if(flag == true){
          Reader.t.add("ninta", s, Reader.lineno);
            System.out.println("(Int_constant , "+s+" , "+Reader.lineno+" )");} // replace with INT TOKEN
      else
            System.out.println("Error invalid integer constant "+s+" @ line no "+Reader.lineno); // replace with INT INVALID TOKEN
            Reader.lexicalError++;
}

 static void checkIntFloat(String s){
    
    if(s.contains(".")){
        checkFloat(s);
    }
    else{
        checkInt(s);
    }
}
static void checkFloat(String s){
     
      boolean flag = false;
      flag = inputIsFloat(s);
      if(flag == true){
          Reader.t.add("nfloata", s, Reader.lineno);
              System.out.println("(Float_constant ,"+s+" , "+Reader.lineno +")"); // replace with FLOAT TOKEN
      }else {
            System.out.println("Error invalid Float constant "+ s+" @ line no "+Reader.lineno );
                       Reader.lexicalError++;     }// replace with FLOAT IINVALID TOKEN
}
static void checkID(String s){
     
      boolean flag2 = false;
      flag2 = inputIsID(s);
      if(flag2 == true){
          
      Reader.t.add("ID", s, Reader.lineno);
            System.out.println("( ID, " +s+ " , "+Reader.lineno +" )"); // replace with ID TOKEN
      } else
      {System.out.println("Error invalid ID "+ Reader.temp+" @line no"+Reader.lineno); 
      Reader.lexicalError++;}// replace with INVALID ID TOKEN
}
static void checkDT(String s){
    ArrayList<String> DT=new ArrayList<String>();
    DT.add("ninta");
    DT.add("nfloata");
    DT.add("nchara");
    DT.add("nstringa");
    DT.add("ndoublea");
    if(DT.contains(s)){
        Reader.t.add("DT", s, Reader.lineno);
         System.out.println("( DT , " +s+ " , "+Reader.lineno +" )");
    }
    else{
      checkAM(s);  
    }
}
static void checkAM(String s){
     ArrayList<String> AM=new ArrayList<String>();
    AM.add("personal");
    AM.add("general");
    AM.add("secure");
    AM.add("standard");
    if(AM.contains(s)){
        Reader.t.add("AM", s, Reader.lineno);
       System.out.println("( AM , " +s+ " , "+Reader.lineno +" )"); 
    }
    else{
      check_KW_ID(s);  
    }
}
static void check_KW_ID(String s){
   
//    String[] keywords = {"present", "parent", "stable","spread","abstract","perform","new","class",
//                            "veto","absolute","stop","resume","check","spare","loop","loopfor",
//                            "loopwhile","loopdo"};
ArrayList<String> KW=new ArrayList<String>();
        //novita                              present                                                                 abstract                                                             veto                                   stop             resume
 KW.add("extends");KW.add("empty");     KW.add("present");KW.add( "parent");/*KW.add("stable")*/;KW.add("spread");KW.add("implements");KW.add("perform");KW.add("new");KW.add("class");KW.add( "interface");KW.add("absolute");KW.add("pakage");KW.add("import");KW.add("check");KW.add("spare");KW.add("loop");KW.add("loopfor");KW.add("loopwhile");KW.add("loopdo");KW.add("novitachocolate");KW.add("novita");KW.add("loopdo");KW.add("while");KW.add("resume");KW.add("stop");
//    int kw_flag1=0;
//    int a;
//     int length=keywords.length;
//    for(a=0;a<length;a++){
//        if(s==keywords[a]){
//            kw_flag1=1;
//            a=length;
//        }
//        
//    }
    if(KW.contains(s)==true){
        Reader.t.add(s, s, Reader.lineno);
        System.out.println("( "+s+" , " +s+ " , "+Reader.lineno +" )");
    }
    else{
      checkID(s); //replace with KEYWORD TOKEN
    }
}
    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//         
//      checkInt("123"); // yaha pr har ham temp pass kare ge jo check kare ga k valid int he ya ni
//      checkFloat("+.36"); // yaha pr ham float pass karen ge checking k lye
//      
//       check_KW_ID("standard"); // yaha pr keyword(kw) pass kare ge agr keyword hoa toh keyword ka token
//                            //bnjae ga warna id k function k pass ja k temp me jo string hogi
//                            //usko id k lye check kare ga k ager kw ni he to id hogi mgr vo bhi valid honi
//                            //chahye.
//        
//    }
}
