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
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roshaann 2.7 gpa
 */
public class Reader {
   static int lineno=1;
   int temporaryline=1;
    //Declaring all classes
  static  Logical_Operator LO=new Logical_Operator();
    static Bit_Wise BW=new Bit_Wise();
   static AO AO=new AO();
  static  Div_Mod DM=new Div_Mod();
   static Inc_Dec IND=new Inc_Dec();
   static Add_Sub AS=new Add_Sub();
   static RO RO=new RO();
   static Extra_Rules ER=new Extra_Rules();
   static All all=new All();
//    String Data_Tpe[]={"","",""};
//   String Access_Modifiers;
//    char Operato;
//    char Add_Sub;
//    char Relational_Operato;
//    char Word_Breaker;
  static  int lexicalError;
    //token ka object to send all tokens into object
    //static isliye  kia he q k kch tokens REs.java me hain to class name sy access krlunga
  static Tokens t=new Tokens();
    
    
    
     static StringBuilder temp=new StringBuilder();
    
    
    //This constructor is responsible for reading file character by character and performing operations
       Reader(){
      
      
           BufferedReader br=null;
      
           try{
                 
                br = new BufferedReader(new FileReader("code.txt"));
    
            StringBuilder sb = new StringBuilder();
  
            int line  ;  

            //reading file and checking ,, if it reaches the end
            while (!((line= br.read())!=-1)&&temp.length()!=0||line!=-1) {
               
                sb.append((char)line);  
                
//                //word breaker
//                if(Breaker.WB.contains((char) line)==true){
//               
//                    break;  
//                }
                //checkking comment
                if((char)line=='n'){
                    
                   StringBuilder temp=new StringBuilder();
                   temp.append((char)line);
                   br.mark(24);
                   
                   if((char)br.read()=='`'){
                       
                       //phley ham token banany bhj dengy q k ab pta he ke ye koi na koi cmmnt he to identifier ka token k llye bhjdo 
                       //ye temp idetntifier wala he jo meny class variable banaya tha
                       if(this.temp.length()!=0){
                           System.out.println("tata");
                            REs.checkDT(temp.toString());
                          //  REs.check_KW_ID(temp.toString());
                          // System.out.println("sending \""+ this.temp +"\" word to Re");
                        this.temp.setLength(0);
                       }
                       //single line comment
                       if((char)br.read()=='`'){
                           
                           br.reset();
                           while(((line=br.read())!=10)||line!=-1){ //checking for \n for end of line
                           //breaking it for both conditions
                           if(((line=br.read())==10)||line==-1||(char)line=='\r'){
                              // System.out.println("ttt");
                               break;
                           }
                       } }
                       //multi line comment
                        else{
                           int templine=line;
                           br.reset();
                            //checking for end of the file
                                   while((line=br.read())!=-1){ 
                                      
                                       if((char)line=='`'){
                                           //now checkking for end of multi line comment
                                           if(((char)(line=br.read()))=='a'){
                                               line=br.read();
                                               break;
                                               
                                           }
                                          
                                       }
                                       //checking if line change occur between comment to keep the record of lines
                                        if(line==10){ 
                                               lineno++;
                                           }
                                   }
                                   if(line==-1){
                                       break;
                                   }
                                   }  
                       }
                   
                   // br.reset();
                   else{
                       br.reset();
                   }
                }
                //word                                                                                                                                         // agar all me na ho or exttra me na ho or -1 ke equal na ho && temp empty na ho phr chalegaright wala hissa                                       
                if((((char)line>='a'&&(char)line<='z')|| ((char)line>='A'&&(char)line<='Z')||(char)line=='$'||(char)line=='_')||((((char)line>='0'&&(char)line<='9')||(all.all.contains((char)line)||ER.ER.contains((char)line)||line==-1)==false)&&(this.temp.length()>0))){
                    //System.out.println((char)line);
                   temp.append((char)line);
                   /// System.out.println(line);
                    continue;
                }
                
                //wordbreaker
                else{
                    
                    //if temp was already full thn first empty temp and thn chk the wordbreaker
                    if(temp.length()!=0){
                       
                         REs.checkDT(temp.toString());
                        //REs.check_KW_ID(temp.toString());
                           //  System.out.println("sending \""+ temp +"\" word to Re");
                        temp.setLength(0);
                        
                        //checking for last extra iteration otherwise it will print one extra word breaker
                        if(line==-1){
                            break;
                        }
                        
                        
                    }
                    //for char
                    if((char)line=='\'')
                    {
                       // try{
                       line=br.read();
                        //for escape character
                        if((char)line=='\\'){
                        
                            line=br.read();
                            br.mark(26); //markin the point of character after \ 
                              //read 2nd character now checking it against escape characters
                            
                                if(((char)line=='t'||(char)line=='b'||(char)line=='n'||(char)line=='r'||(char)line=='f'||(char)line=='\''||(char)line=='\\'||(char)line=='\"')){
                                  
                                        if((br.read())==39){ //39 means '
                                              //printing the character after /
                                           //   System.out.println("");
                                            // System.out.println("send the escape character "+(char)line+" for the token" );
                                            t.add("nchara", Character.toString((char)line), lineno);
                                            System.out.println("(Character_Constant"+" , \\"+(char)line+" , "+lineno+")");
                                             br.reset();  //\ khud append krna token banaty v me bagher usky bhj rha hun
                                            // br.read();
                                             line=br.read(); //ab ' ke agay ka read krega next
                                        }
                                         else{
                                            //ye escape characctr ki jaga koi or hoga to wo prnt krega
                                            br.reset(); //ye ab after \ ispy marker legaya he
                                                System.out.println("Error invalid character constant \\"+(char)line +(char)br.read() +" @ line no "+lineno);
                                             
                                                //for error reporting
                                                lexicalError++;
                                         }
                                
                                    }
                                //ye us wqt chalega agar '\ ke baad line end hogyi
                                else if((char)line=='\r'){
                                    
                                    br.reset();
                                   //  System.out.println("Error invalid  character constant "+line  +" @ liney no "+lineno);
                                     System.out.println("Error invalid character constatn at the end of line "+lineno);
                                     
                                      //for error reporting
                                                lexicalError++;
                                   // lineno++;
                                }
                                else{
                                  br.reset(); //ressuming  after (backslash)\ position
                                 // line=br.read(); //reading next character after \     line ke andr backslash k baad wala he
                                    System.out.println("Error invalid char constant \\"+(char)line+" @ lineno "+lineno);
                                    br.read(); //ab  '\h'  ' ke baad ka read krega next m
                                    
                                     //for error reporting
                                                lexicalError++;
                              }
                            
                   
                                } 
                        //normal characters k liye
                        else{
                                
                          //  line=br.read();//line me ' ke baad ka character
                            br.mark(24);
                            StringBuilder chartemp=new StringBuilder(); //for storing the complete error
                               
                            //token state
                                if((char)br.read()=='\''){  //chacking for extra character
                                   // System.out.println("send "+(char) line + "for char token");
                                   t.add("nchara", Character.toString((char)line), lineno);
                                   System.out.println("( Character_Constant "+" , "+(char)line+" , "+lineno+" )");
                                    br.reset();
                                    br.read();
                                }
                                else if((char)line=='\r'){ //checking for the end of the line char meane 'new line
                                    br.reset();
                                    System.out.println("Error invalid character at end of line "+lineno+" @ lineno "+lineno);
                                     //for error reporting
                                                lexicalError++;
                                }
                                else{//error state
                                    
                                    chartemp.append((char)line);
                                    br.reset();
                                    chartemp.append((char)br.read());
                                       System.out.println("Error invalid character constant "+ chartemp+" @ line no "+Reader.lineno);
                                        //for error reporting
                                                lexicalError++;
                                   // System.out.println("Invalid character "+(char)line/*+(char)br.read()*/+" @ linenoo "+lineno);
                                }
                            
                           // System.out.println("it "+(char)line+"a// wordbreaker"); 
                    }
                    }
                    //for strings
                    else if((char)line=='\"'){
                     //   System.out.println("hhhhhhhhhhhhh");
                        StringBuilder temp=new StringBuilder();
                        int previous=line;
                        //
                        while((((line=br.read())!=34)&&line!=-1&&(char)line!='\n'&&(char)line!='\r')||(line==34&&previous==92)){
                        temp.append((char)line);
                        previous=line;
                           // System.out.println("gggggggggggg");
                        }
                        //agar ye hua "" to error
                        if(line==34&&previous==34&&temp.length()==0){
                            System.out.println("Error invalid String constant \" @line no " + lineno);
                            
                             //for error reporting
                                                lexicalError++;
                        }
      
                        else if(line==34){
                            t.add("nstringa ", temp.toString() , lineno);
                            System.out.println("(String_Constant "+" , "+temp+" , "+lineno+" )");
                           // System.out.println("send "+temp+"for String check or for token");
                        }
                        
                        else if(line==-1){
                           // System.out.println("invalid String constant"+temp+" reach end of the program");
                               System.out.println("Error invalid String constant "+ temp+" reached end of the program");
                                //for error reporting
                                                lexicalError++;
                        }
                        else if((char)line=='\r'){
                            System.out.println("Error invalid String constant "+temp+" @ line no "+lineno);
                            
                             //for error reporting
                                                lexicalError++;
                        }
                        else if((char)line=='\n'){
                          //System.out.println("bbbbbb");  
                            int l=temp.length();
                            temp.setLength((l-1));
                            //System.out.println("Invalid String constant "+temp+" @ line no "+lineno);
                            System.out.println("Error invalid Stringj constant "+ temp+" @ line no "+Reader.lineno);
                             //for error reporting
                                                lexicalError++;
                              // lineno++;
                        }
                        
                    }
                    //+ or - check kkrega also ++ -- and += and -=
                    else if((char)line=='+'||(char)line=='-'){
                        int Flag_for_dot=0; //this will checck teo occurence of . 
                        int Flag_for_allowing_abc=0;  //this will allow this type of thing 7.0abc 
                        int previous=-1; //this will eliminate 7.a
                        
                        StringBuilder temp=new StringBuilder();
                        temp.append((char)line); //appending + or -
                        br.mark(24);
                        temp.append((char)br.read());  //appending next char to chk ++ -- += -=
                        
                        //checking int and float - and +
                        
//                        if((temp.charAt(1)>='0'&&temp.charAt(1)<='9')||temp.charAt(1)=='.'){
//                            br.reset();
//                            br.mark(500);
//                            
//                            temp.setLength(1);
//                            line=br.read();
//                          
//                           // System.out.println(temp);  ye single print kr rha h
//                            //first dot or 0 say 9 tk ki chezen allow krega       //abc tb allow krega jb us sy pehly . na ho ya us sy pehly koi integer ho
//                            while(((char)line>='0'&&(char)line<='9')||(char)line=='.'||(((char)line>='a'&&(char)line<='z')&&((char)previous!='.'&&previous!=0))){
//                             
//                                if((char)line=='.'&&Flag_for_dot>0){
//                                    break;
//                                }
//                                else if((char)line=='.'){
//                                    Flag_for_dot++;
//                                    temp.append((char)line);
//                                      previous=line;
//                                    line=br.read();
//                                  
//                                }
//                                else if((char)line>='0'&&(char)line<='9'){
//                                    temp.append((char)line);
//                                       previous=line;
//                                    line=br.read();
//                                
//                                }
//                                else{
//                                    temp.append((char)line);
//                                    previous=line;
//                                    line=br.read();
//                                  
//                                }
//                                 //  System.out.println(temp);
//                                
//                            }
//                            
//                            //agar last character . agaya in case 0.a to . b temop me ajaega to iska yahin token banega
//                            if(temp.charAt(temp.length()-1)=='.'){
//                                temp.setLength(temp.length()-1);
//                               // System.out.println("Token ( "+(char)line +" , "+(char)line+" , "+lineno+" )");
//                             //   System.out.println(temp);
//                            }
//                           
//                            br.reset();
//                            for(int i=0;i<(temp.length()-1);i++){
//                                line=br.read();
//                            }
//                           // REs.checkDT(temp.toString());
//                            REs.checkIntFloat(temp.toString());
//                           // System.out.println(temp+"jjjjjjjjjjjj");
//                            
//                        }
                        //checking ++ or --
                         if(IND.IND.contains(temp.toString())==true){
                             t.add(IND.getClass().getSimpleName(), temp.toString(), lineno);
                            System.out.println("( "+IND.getClass().getSimpleName()+" , "+temp+" , "+lineno+" )");
                            br.reset();
                            br.read();
                            float a=12.23f+-.3f;
                        }
                        
                        //checking += and -=
                        else if(AO.OP1.contains(temp.toString())==true){
                           
                              t.add(AO.getClass().getSimpleName(), temp.toString(), lineno);
                             System.out.println("( "+AO.getClass().getSimpleName()+" , "+temp+" , "+lineno+" )");
                            br.reset();//resetting it back to 1st character
                            br.read(); //incrementing 
                        }
                        else{
                              t.add(AS.getClass().getSimpleName(), Character.toString((char)line), lineno);
                            System.out.println("( "+AS.getClass().getSimpleName()+" , "+(char)line+" , "+lineno+" )");
                            br.reset();//resetting it back to 1st character
                            
                        }
                        
                    }
                    // agar mery commment // ye hoga to me isi me chk krunga
                     //* or / or % check kkrega also += /= and += and %=
                    else if(DM.DM.contains((char)line)==true){ //comparing * / %
                        StringBuilder temp=new StringBuilder();
                        temp.append((char)line); //appending * or / %
                        br.mark(24);
                        temp.append((char)br.read());  //appending next char to chk *= /= %= //
                        

                        
                        //checking *= and /=  %=
                         if(AO.OP1.contains(temp.toString())==true){
                           
                               t.add(AO.getClass().getSimpleName(), temp.toString(), lineno);
                             System.out.println("( "+AO.getClass().getSimpleName()+" , "+temp+" , "+lineno+" )");
                            br.reset();//resetting it back to 1st character
                            br.read(); //incrementing 
                        }
                         else if(temp.toString().equals("//")){
                             
                               t.add("//",temp.toString(), lineno);
                              System.out.println("( "+" // "+" , "+temp+" , "+lineno+")");
                            br.reset();//resetting it back to 1st character
                            br.read(); //incrementing 
                         }
                        else{
                             t.add(DM.getClass().getSimpleName(),Character.toString( (char)line), lineno);
                            System.out.println("( "+DM.getClass().getSimpleName()+" , "+(char)line+" , "+lineno+" )");
                            br.reset();//resetting it back to 1st character
                            
                        }
                        
                    }
                    
                    //checking = and ==
                     else if(AO.OP2.contains((char)line)==true){ //comparing * / %
                        StringBuilder temp=new StringBuilder();
                        temp.append((char)line); //appending * or / %
                        br.mark(24);
                        temp.append((char)br.read());  //appending next char to chk *= /= %= //
                        

                        
                        //checking == and /=  %=
                         if(RO.RO.contains(temp.toString())==true){
                           
                             t.add(RO.getClass().getSimpleName(), temp.toString(), lineno);
                             System.out.println("( "+RO.getClass().getSimpleName()+" , "+temp+" , "+lineno+" )");
                            br.reset();//resetting it back to 1st character
                            br.read(); //incrementing 
                        }
                        
                        else{
                             t.add(/*AO.getClass().getSimpleName()*/ Character.toString((char)line) , Character.toString((char)line), lineno);
                            System.out.println("( "+AO.getClass().getSimpleName()+" , "+(char)line+" , "+lineno+" )");
                            br.reset();//resetting it back to 1st character
                            
                        }
                        
                    }
                     
                     
                      //checking > < >= <= and == !=
                     else if((RO.RO1.contains((char)line)==true)||(char)line=='!'){ //comparing * / %
                        StringBuilder temp=new StringBuilder();
                        temp.append((char)line); //appending > < ! or / %
                        br.mark(24);
                        temp.append((char)br.read());  //appending next char to chk >= <= != //
                    //  System.out.println((char)line);
                        //checking >= and <=  !=
                        if(RO.RO.contains(temp.toString())==true){
                           
                            t.add(RO.getClass().getSimpleName(), temp.toString(), lineno);
                             System.out.println("( "+/*RO.getClass().getSimpleName()*/temp.toString()+" , "+temp+","+lineno+" )");
                            br.reset();//resetting it back to 1st character
                            br.read(); //incrementing 
                        }
                        
                       
                        //System.out.println((char)line);
                        else if((char)line!='!'){
                            t.add(RO.getClass().getSimpleName(), Character.toString((char)line), lineno);
                            System.out.println("( "+RO.getClass().getSimpleName()+" , "+(char)line+" , "+lineno+" )");
                            br.reset();//resetting it back to 1st character
                            
                        }
                        else{
                            ////LAST CHANGE 
                            if((char)line=='!'){
                                System.out.println("Error invalid character "+(char)line+" found at lineno "+lineno);
                                
                                 //for error reporting
                                                lexicalError++;
                            } //LAST CHANGE END
                         //ye kia he ! ke liye edit"  srf ! ke liye he ye agy pichy kch nh
                           // System.out.println("Error invalid lliteral "+ (char) line+" @ line no "+Reader.lineno);
                           t.add(LO.getClass().getSimpleName(), Character.toString((char)line), lineno);
                            System.out.println("(2"+/*LO.getClass().getSimpleName()*/(char)line+" , "+(char)line+" , "+lineno+" )");
                            br.reset(); //reset meny syntax ka expression chk krty v kia he
                        }
                        
                    }
                     
                      //checking && & || | ^   //ab koi bitwise logical ki class nh bcx ye sb alag class me aengy due to precedence
                     else if(BW.BW.contains((char)line)==true){ //comparing * / %
                        StringBuilder temp=new StringBuilder();
                        temp.append((char)line); //appending & | ^
                        br.mark(24);
                        temp.append((char)br.read());  //appending next char to chk *= /= %= //
                        

                        
                        //checking && and ||
                         if(LO.LO.contains(temp.toString())==true){
                           
                             t.add(/*LO.getClass().getSimpleName()*/temp.toString(), temp.toString(), lineno);
                             System.out.println("( "+/*LO.getClass().getSimpleName()*/temp+" ,h "+temp+" , "+lineno+" )");
                            br.reset();//resetting it back to 1st character
                            br.read(); //incrementing 
                        }
                        
                        else{
                             t.add(/*BW.getClass().getSimpleName()*/Character.toString((char)line), Character.toString((char)line), lineno);
                            System.out.println("( "+/*BW.getClass().getSimpleName()*/(char) line+" , "+(char)line+" , "+lineno+" )");
                            br.reset();//resetting it back to 1st character
                            
                        }
                        
                    }
                     
                     //for int and float constants
                     
                     else if(((char)line>='0'&&(char)line<='9')||(char)line=='.'){
                           
                           //br.reset();
                             int Flag_for_dot=0; //this will checck teo occurence of . 
                             int Flag_for_allowing_abc=0;  //this will allow this type of thing 7.0abc 
                             int previous=-1; //this will eliminate 7.a
                            br.mark(500);
                            StringBuilder temp=new StringBuilder();
                            
                            //temp.setLength(1);
                          //  line=br.read();
                          
                           // System.out.println(temp);  ye single print kr rha h
                            //first dot or 0 say 9 tk ki chezen allow krega       //abc tb allow krega jb us sy pehly . na ho ya us sy pehly koi integer ho
                            while(((char)line>='0'&&(char)line<='9')||(char)line=='.'||(((char)line>='a'&&(char)line<='z')&&((char)previous!='.'&&previous!=0))){
                             
                                if((char)line=='.'&&Flag_for_dot>0){
                                    break;
                                }
                                else if((char)line=='.'){
                                    Flag_for_dot++;
                                    temp.append((char)line);
                                      previous=line;
                                    line=br.read();
                                  
                                }
                                else if((char)line>='0'&&(char)line<='9'){
                                    temp.append((char)line);
                                       previous=line;
                                    line=br.read();
                                
                                }
                                else{
                                    temp.append((char)line);
                                    previous=line;
                                    line=br.read();
                                  
                                }
                                 //  System.out.println(temp);
                                
                            }
                           // System.out.println(temp);
                            //agar last character . agaya in case 0.a to . b temop me ajaega to iska yahin token banega
                            if(temp.charAt(temp.length()-1)=='.'){
                             
                                if(temp.length()==1){
                                    t.add(Character.toString(temp.charAt(temp.length()-1)), Character.toString(temp.charAt(temp.length()-1)), lineno);
                                System.out.println("( "+temp.charAt(temp.length()-1) +" , "+temp.charAt(temp.length()-1)+" , "+lineno+" )");
                                temp.setLength(temp.length()-1);}
                                
                                else{
                                    int s=temp.length()-1;
                                    temp.setLength(s);
                                     REs.checkIntFloat(temp.toString());
                                     //System.out.println("( "+temp.charAt(temp.length()-1) +" , "+temp.charAt(temp.length()-1)+" , "+lineno+" )");
                                     
                                }
                                
                             //   System.out.println(temp);
                            }
                            else{
                              //  System.out.println(temp+"jjjjjjjjjjjj");
                              REs.checkIntFloat(temp.toString());
                            }
                            br.reset();
                            for(int i=0;i<(temp.length()-1);i++){
                                line=br.read();
                            }
                           
                            
                        }
                     
                     
                     
                     
                     // / and \ curlie k liye
                     else if(ER.ER.contains((char)line)){
                         t.add(Character.toString((char)line), Character.toString((char)line), lineno);
                         System.out.println("( "+(char)line+ " , "+(char)line+" , "+lineno+")");
                     }
                    
//                     else if(/*(char)line!='\n'||*/(line!=32&&(char)line!='\n')){
//                        //ignoring space and calling all other things as invalid literal
//                         System.out.println("Error invalid literal "+(char)line+" @lihne no "+lineno);
//                     }
                    else if((char)line=='\n'){
                        
                        lineno++;
                      //  System.out.println(lineno);
                }
                    
                    else if(line!=32&&line!=13&&line!=9){
                      //q k comment jb last line p arha tha or us sy pehly identifier read horha tha to ek iteration extra hrhi th see main loop of while
                        if(line==-1){
                            break;
                        }
                        else
                          System.out.println("Error invalid literal "+ (char) line+" @ line no "+Reader.lineno);
                         //for error reporting
                                                lexicalError++;
                    }
                }
            }        
            br.close();
           
//            System.out.println(sb);
//               System.out.println(temp);

//saray tokens print horhy
//t.showAll();
//SyntaxParser r=new SyntaxParser();

            String everything = sb.toString();

           }      
     
        catch (FileNotFoundException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        catch (IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
           finally{
               if(br!=null){
                   try {
                       br.close();
                   } catch (IOException ex) {
                       Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
           }
        
           //NOW CALLING Syntax if there are no errors in lexical
           //if(lexicalError==0){
               
               SyntaxParser sp=new SyntaxParser();
     //      }
    }}
    

class Tokens{
  String cp;
   String vp;
 int lineno;
 ArrayList <String> value=new ArrayList();
 ArrayList<String> classParts=new ArrayList<String>();
  ArrayList<Tokens> token=new ArrayList<Tokens>();
  ArrayList<Integer> line=new ArrayList<Integer>();
    
    Tokens(){
        
    }
    Tokens (String clss,String value, int line){
        this.cp=clss;
        this.vp=value;
        this.lineno=line;
    }
    
    public  void add(String clss,String value, int line){
        
        //using builtin add function of arraylist
        token.add(new Tokens(clss,value,line));
          //adding all class parts in string arraylist
           classParts.add(clss);
           this.value.add(value);
           this.line.add(line);
    }
   public void length(){
        System.out.println(token.size());
    }
   
   public void showAll(){
       
       for(int i=0;i<token.size();i++){
           System.out.println(token.get(i).cp+" , "+token.get(i).vp+" , "+token.get(i).lineno);
           System.out.println("");
         
       }
   }
}

       
      
