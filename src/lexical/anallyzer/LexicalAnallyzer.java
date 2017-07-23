/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lexical.anallyzer;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roshaann 2.7 gpa
 */
public class LexicalAnallyzer {

     LexicalAnallyzer(){
         
     }
    /**
     * @param args the command line arguments
     */
    public static  void main(String[] args) throws ParseException {
      
        
//         ArrayList<String> a=new ArrayList<String>();
//        ArrayList<String> b=new ArrayList<String>();
//        
//        a.add("ali");
//        a.add("Salman");
//       
//        b.add("ali");
//        b.add("Salman");
//        
//        if(a.equals(b)){
//            System.out.println(" heelo");
//        }
    Stack<String> a=new Stack<String>();
    String aa=null;
    
    a.add(aa);
    if(a.get(0)==null){
        System.out.println(a);
    }
    
      Reader r=new Reader();
   //   SyntaxParser rr=new SyntaxParser();
 //  IntermediateSyntax is=new IntermediateSyntax();
      
    
//    t.showAll();
             // t.length();
      //  System.out.println("ggggg");
       
//        Inc_Dec id=new Inc_Dec();
//        if(id.IND.contains("++")==true){
//            System.out.println("JAHANJAHAN");
//        }
        
    }
   
    
}

class Data_Type{
   ArrayList<String> DT=new ArrayList<String>();
   int a;
   Data_Type(){
       DT.add("Int");
       DT.add("J");
   }
   
}

class Access_Modifiers{
     ArrayList<String> AM=new ArrayList<String>();
   
     Access_Modifiers(){
    
         AM.add("public");
}
    }

class AO{
    ArrayList<String> OP1=new ArrayList<String>();
    ArrayList<Character> OP2=new ArrayList<Character>();
    
    AO(){
        OP1.add("+=");
        OP1.add("-=");
        OP1.add("*=");
        OP1.add("~=");
        OP1.add("%=");
        OP2.add('=');
    }
}

class Add_Sub{
    ArrayList<Character> AS=new ArrayList<Character>();
    
    Add_Sub(){
        AS.add('+');
        AS.add('-');
    }
}

class RO{
  ArrayList<String> RO=new ArrayList<String>();
  ArrayList<Character> RO1=new ArrayList<Character>();
  RO(){
      RO1.add('<');
      RO.add("<=");
      RO.add(">=");
      RO1.add('>');
      RO.add("==");
      RO.add("!=");
  }
}

class Word_Breaker{
     ArrayList<Character> WB=new ArrayList<Character>();
  
   
     Word_Breaker(){
         WB.add('*');
         WB.add('+');
     }
}

class Div_Mod{
    ArrayList<Character> DM=new ArrayList<Character>();
    
    Div_Mod(){
        DM.add('~');
        DM.add('%');
        DM.add('*');
    }
}

class Inc_Dec{
    ArrayList<String> IND=new ArrayList<String>();
    
    Inc_Dec(){
        
        IND.add("++");
        IND.add("--");
    }}
    
class Logical_Operator{
    ArrayList<String> LO=new ArrayList<String>();
    
    Logical_Operator(){
        LO.add("&&");
        LO.add("||");
      //  LO.add("!");
    }
}
class All{
    ArrayList<Character> all=new ArrayList<Character>();
    
    All(){
        all.add('+');
         all.add('*');
          all.add('=');
           all.add('>');
            all.add('<');
             all.add('/');
              all.add('*');
               all.add('%');
                all.add('-');
                 all.add('~');
                 all.add('!');
                   all.add('|');
                    all.add('&');
                    all.add('^');
                    all.add('\r');
                    all.add('\n');
                    all.add(' ');
                    all.add('\'');
                    all.add('\"');
                    all.add('\t');
                    
                    
         
    }
}

class Extra_Rules{
     ArrayList<Character> ER=new ArrayList<Character>();
      ArrayList<Character> ER2=new ArrayList<Character>();
      
      Extra_Rules(){
          ER.add('/');
          ER.add('\\');
          ER.add('(');
          ER.add(')');
          ER.add('.');
          ER.add(':');
          ER.add(']');
          ER.add('[');
          ER.add(',');
      ER.add(';');
      }
}



class Bit_Wise{
    ArrayList<Character> BW=new ArrayList<Character>();
    
    Bit_Wise(){
        BW.add('&');
        BW.add('|');
        BW.add('^');
    }
}
//meny // ko signle krdia he
//comment n`` and n` `a

//class a{
//    b a=new b();
//    void na(){
//        
//    }
//}
//class b{
//    int b;
//    a o=new a();
//    
//    void na(){
//        o;}
//    
//    class a{
//        
//        int c;
//    void na(){}
//    class l{
//        a m=new a();
//    }}
//}
class a{
    a[] a={new c(), new b()};
    int d;
}
    class b extends a{
        
    }
class c extends b{
    c c=new c();
    c()
    {
        d=5;
    }}