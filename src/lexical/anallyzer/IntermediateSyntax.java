/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexical.anallyzer;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Roshaann 2.7 gpa
 */
public class IntermediateSyntax {

    //////////////////////////////////SEMANTIC K VARIABLES START HERER /////////////////////////
    //TAbles ke Variables
//     ArrayList <Variables> varTable=new ArrayList<Variables>();
//     ArrayList <Class> ClassTable=new ArrayList<Class>();
    Variables var = new Variables();
    Class cls = new Class();
    Function fun = new Function();

    int interfaceBodyScope;// used to save the scope of interface body
    Stack scopes=new Stack();//used in class
    Stack classParent=new Stack();// used in class to save the name of parent class to fill the parent name column in inner class table
 
    int iterationCalculator=0; //used in exp
    Stack<String> nameExp=new Stack<String>(); //used in exp
    Stack<Integer> arrayCounterExp=new Stack<Integer>();
    Stack<String> t=new Stack<String>(); ///used in expression
    String op;//used in expression
    String opA;//used in assignment
    int dotUsed;//used in asssignment
    int arrayTemp;//used in assignment
    int arrayCounterAssign=0;
    String nameA;//used in assignment
    String obAName; //used in assignment
    String typeRestorer; //in assignment
    Stack<String> tA=new Stack<String>();
    String object;
    String AM;
    String Static;
    String Final;
    int arrayCounter;
    String type;
    String name;
    String Extends;
    String returnType;
    ArrayList<Variables> parameters = new ArrayList<Variables>();
    ArrayList<String> Implements = new ArrayList<String>();
    static int scope = 0;
    
    String clsName; //used to check class name==construction name
    String namePara; //used only in para declaration for function parameter
    String typePara;
    /////////////////////////////////SEMANTIC K VARIABLES END HERE/////////////////////////

    int Roshaan = 0;
    Stack failedCfgs = new Stack();
    int errorIndex;
    int temp;
    int newtemp; //salman ka code
    int looptemp;  //salman ka variable
    //  int flag; // salman ki declaration me use hua he
    int previous;
    Stack squareBracket = new Stack();
    Stack comma = new Stack();
    Stack bracket = new Stack();
    Stack semiColon = new Stack();
    Stack slash = new Stack();
    Stack commaAssign = new Stack();
    Stack commaDecl = new Stack();

    ArrayList<String> inComming = Reader.t.classParts;
    ArrayList<String> inCommingVp = Reader.t.value;
    ArrayList<Integer> inCommingLine = Reader.t.line;
    int i = 0;
    int[] a = new int[7];

    //this will be called by main
    IntermediateSyntax() {
        arrayCounterExp.add(0); //wasting one index
        //    Reader.t.showAll();

        //  checkspare();
        //  loopfor();
        //    a();
        // z();
        // assign();
        //  checkspare();
        //  loopdowhile();
        //  loopwhile();
        //  Class();
        //   mainClass();
        //  Interface();a[]
      Pakage();
        //  para_decl();
        //Variables v=new Variables();
   
        if (/*errorIndex <= inComming.size() && errorIndex > 0*/failedCfgs.size() > 0) {
            if (errorIndex == inComming.size()) {
                errorIndex = errorIndex - 1;
//                System.out.println("error== i is working");
            }
//             System.out.println(inCommingVp.get(errorIndex));
//             System.out.println("");
            System.out.println("Semantic Error: Error Found Line no  " + inCommingLine.get(errorIndex) + " CFG failed " + failedCfgs.get(0));
           
        }
    }
    // brackets masla kr rhy hain ()() krky I walay Iprime waly nh
    // remove ) ; walay checks sb me sy siwaye usky jismy hh1 hesrf wohi run hoga q k strt me

    //logic wont work if :; inside :; occure like :na:;;
    //only for checking purpose
      
       
       
    String a() { ///wasting first index
        if (inComming.get(i).equals("nfloata")
                || inComming.get(i).equals("nstringa ")
                || inComming.get(i).equals("nchara")
                || inComming.get(i).equals("ninta")
                || inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals("ID")
                || inComming.get(i).equals("(")) {
//            System.out.println("a " + inComming.get(i));
               
                //assigning value so that no null pointer exception wil occur
                iterationCalculator++;
                arrayCounterExp.add(iterationCalculator, 0);
             
            if (b()) {
                if (i >= inComming.size()) {
//                    System.out.println("a success");
                    
                    if(t.size()>0){
                    System.out.println("returning "+t.peek());
                   
                    String l=t.peek();
                    t.pop();
                   
                     iterationCalculator--;//used to identify the iteration to keep values valid
                    return l;} else{  return null;}
                } 
                else if (aPrime()) {

                    if(t.size()>0){ 
                    System.out.println("returning "+t.peek());
                    String l=t.peek();
                    
                    t.pop();
                    
                    iterationCalculator--;//used to identify the iteration to keep values valid
                    return l;} else{  return null;}
                } 
                else {
                    failedCfgs.add("Expression");
                    errorIndex = i;
//                    System.out.println("a failed");
                }

            } else {
                failedCfgs.add("Expression");
                errorIndex = i;
//                System.out.println("a failed");
            }

        } else {
            failedCfgs.add("Expression");
            errorIndex = i;
            i++;
            return null;
        }
        errorIndex = i;
        return null;
    }

    Boolean aPrime() {

        if (inComming.get(i).equals("||")
                || inComming.get(i).equals("]")
                || inComming.get(i).equals(")")
                || inComming.get(i).equals(";")
                || inComming.get(i).equals(",")
                || inComming.get(i).equals("\\")) {

//            System.out.println("aprime" + inComming.get(i));
            if (inComming.get(i).equals("||")) {

                i++;

                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (b()) {

                    //if file ended out of bound
                    if (i >= inComming.size()) {
//                        System.out.println("hello");
                        return true;

                    } else if (aPrime()) {
//                        System.out.println("aPrime success");

                        return true;
                    } else {
                        errorIndex = i;
                    }

                } else {
                    errorIndex = i;
                }
            } else {

//                System.out.println("aPrime success");
                return true;
            }

        } else {
            errorIndex = i;
            i++;
            return false;

        }
        errorIndex = i;
        return false;
    }

    Boolean b() {

        if (inComming.get(i).equals("nfloata")
                || inComming.get(i).equals("nstringa ")
                || inComming.get(i).equals("nchara")
                || inComming.get(i).equals("ninta")
                || inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals("ID")
                || inComming.get(i).equals("(")) {

//            System.out.println("b" + inComming.get(i));
            if (cc()) {

                if (i >= inComming.size()) {

                    return true;
                }
                if (bPrime()) {
//                    System.out.println("b success");
                    return true;
                } else {
                    errorIndex = i;
                }
            } else {
                errorIndex = i;
            }

        } else {
            errorIndex = i;
            i++;
            return false;
        }
        errorIndex = i;
        return false;
    }

    Boolean bPrime() {

        if (inComming.get(i).equals("&&")
                || inComming.get(i).equals("||")
                || inComming.get(i).equals(")")
                || inComming.get(i).equals(";")
                || inComming.get(i).equals(",")
                || inComming.get(i).equals("\\")
                || inComming.get(i).equals("]")) {
//            System.out.println("bprime" + inComming.get(i));

            if (inComming.get(i).equals("&&")) {

                i++;
                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (cc()) {

                    //if file ended out of bound
                    if (i >= inComming.size()) {
//                        System.out.println("hello");
                        return true;
                    } else if (bPrime()) {

//                        System.out.println("bPrime success");
                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } //remaining checks will return true if found somthing from follow
            //but if ) or ; thn it will only return follow when flag is true
            //            else if((inComming.get(i).equals(")"))||
            //                    (inComming.get(i).equals(";"))){
            //                            System.out.println("7hh");
            //                  if((inComming.get(i).equals(")")&&flagForBracket==true)||
            //                     (inComming.get(i).equals(";")&&flagForSemiColon==true   )  ){
            //                    
            //                      System.out.println("aPrime success");
            //                      return true;
            //                  }
            //                
            //            }
            else {

//                System.out.println("bPrime success");
                return true;
            }

        } else {
            errorIndex = i;
            i++;
            return false;
        }
        errorIndex = i;
        return false;
    }

    Boolean cc() {

        if (inComming.get(i).equals("nfloata")
                || inComming.get(i).equals("nstringa ")
                || inComming.get(i).equals("nchara")
                || inComming.get(i).equals("ninta")
                || inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals("ID")
                || inComming.get(i).equals("(")) {

//            System.out.println("cc" + inComming.get(i));
            if (dd()) {
                if (i >= inComming.size()) {

                    return true;
                }

                if (ccPrime()) {
//                    System.out.println("cc success");
                    return true;
                } else {
                    errorIndex = i;
                }
            } else {
                errorIndex = i;
            }

        } else {
            errorIndex = i;
            i++;
            return false;
        }
        errorIndex = i;
        return false;
    }

    Boolean ccPrime() {

        if (inComming.get(i).equals("|")
                || inComming.get(i).equals("&&")
                || inComming.get(i).equals("||")
                || inComming.get(i).equals(")")
                || inComming.get(i).equals(";")
                || inComming.get(i).equals(",")
                || inComming.get(i).equals("\\")
                || inComming.get(i).equals("]")) {

//            System.out.println("ccprime" + inComming.get(i));
            if (inComming.get(i).equals("|")) {

                i++;
                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (dd()) {

                    //if file ended out of bound
                    if (i >= inComming.size()) {
//                        System.out.println("hello");
                        return true;
                    } else if (ccPrime()) {
//                        System.out.println("ccPrime success");

                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } //remaining checks will return true if found somthing from follow
            //but if ) or ; thn it will only return follow when flag is true
            //            else if((inComming.get(i).equals(")"))||
            //                    (inComming.get(i).equals(";"))){
            //                System.out.println("6hh");
            //                  if((inComming.get(i).equals(")")&&flagForBracket==true)||
            //                     (inComming.get(i).equals(";")&&flagForSemiColon==true   )  ){
            //                    
            //                      System.out.println("aPrime success");
            //                      return true;
            //                  }
            //                
            //            }
            else {

//                System.out.println("ccPrime success");
                return true;
            }

        } else {
            errorIndex = i;
            //  i++;
            return false;
        }
        errorIndex = i;
        return false;
    }

    Boolean dd() {

        if (inComming.get(i).equals("nfloata")
                || inComming.get(i).equals("nstringa ")
                || inComming.get(i).equals("nchara")
                || inComming.get(i).equals("ninta")
                || inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals("ID")
                || inComming.get(i).equals("(")) {

//            System.out.println("dd" + inComming.get(i));
            if (ee()) {

                if (i >= inComming.size()) {
//                    System.out.println("hello");
                    return true;
                } else if (ddPrime()) {
//                    System.out.println("dd success");
                    return true;
                } else {
                    errorIndex = i;
                }
            } else {
                errorIndex = i;
            }

        } else {
            errorIndex = i;
            //   i++;
            return false;
        }

        return false;
    }

    Boolean ddPrime() {

        if (inComming.get(i).equals("^")
                || inComming.get(i).equals("|")
                || inComming.get(i).equals("&&")
                || inComming.get(i).equals("||")
                || inComming.get(i).equals(")")
                || inComming.get(i).equals(";")
                || inComming.get(i).equals(",")
                || inComming.get(i).equals("\\")
                || inComming.get(i).equals("]")) {
//            System.out.println("ddprime" + inComming.get(i));

            if (inComming.get(i).equals("^")) {

                i++;
                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (ee()) {

                    //if file ended out of bound
                    if (i >= inComming.size()) {
//                        System.out.println("hello");
                        return true;
                    } else if (ddPrime()) {

//                        System.out.println("ddPrime success");
                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } //remaining checks will return true if found somthing from follow
            //but if ) or ; thn it will only return follow when flag is true
            //            else if((inComming.get(i).equals(")"))||
            //                    (inComming.get(i).equals(";"))){
            //                System.out.println("5hh");
            //                  if((inComming.get(i).equals(")")&&flagForBracket==true)||
            //                     (inComming.get(i).equals(";")&&flagForSemiColon==true   )  ){
            //                    
            //                      System.out.println("aPrime success");
            //                      return true;
            //                  }
            //                
            //            }
            else {

//                System.out.println("ddPrime success");
                return true;
            }

        } else {
            errorIndex = i;
            i++;
            return false;
        }
        return false;
    }

    Boolean ee() {

        if (inComming.get(i).equals("nfloata")
                || inComming.get(i).equals("nstringa ")
                || inComming.get(i).equals("nchara")
                || inComming.get(i).equals("ninta")
                || inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals("ID")
                || inComming.get(i).equals("(")) {

//            System.out.println("ee" + inComming.get(i));
            if (c()) {
                if (i >= inComming.size()) {
//                    System.out.println("hello");
                    return true;
                } else if (eePrime()) {
//                    System.out.println(" ee success");
                    return true;
                } else {
                    errorIndex = i;
                }
            } else {
                errorIndex = i;
            }

        } else {
            errorIndex = i;
            i++;
            return false;
        }

        return false;
    }

    Boolean eePrime() {

        if (inComming.get(i).equals("&")
                || inComming.get(i).equals("^")
                || inComming.get(i).equals("|")
                || inComming.get(i).equals("&&")
                || inComming.get(i).equals("||")
                || inComming.get(i).equals(")")
                || inComming.get(i).equals(";")
                || inComming.get(i).equals(",")
                || inComming.get(i).equals("\\")
                || inComming.get(i).equals("]")) {
//            System.out.println("eeprime" + inComming.get(i));

            if (inComming.get(i).equals("&")) {

                i++;
                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (c()) {

                    //if file ended out of bound
                    if (i >= inComming.size()) {
//                        System.out.println("hello");
                        return true;
                    } else if (eePrime()) {

//                        System.out.println("eePrime success");
                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } //remaining checks will return true if found somthing from follow
            //but if ) or ; thn it will only return follow when flag is true
            //            else if((inComming.get(i).equals(")"))||
            //                    (inComming.get(i).equals(";"))){
            //                System.out.println("5hh");
            //                  if((inComming.get(i).equals(")")&&flagForBracket==true)||
            //                     (inComming.get(i).equals(";")&&flagForSemiColon==true   )  ){
            //                    
            //                      System.out.println("aPrime success");
            //                      return true;
            //                  }
            //                
            //            }
            else {

//                System.out.println(" eePrime success");
                return true;
            }

        } else {
            errorIndex = i;
            i++;
            return false;
        }
        return false;
    }

    Boolean c() {

        if (inComming.get(i).equals("nfloata")
                || inComming.get(i).equals("nstringa ")
                || inComming.get(i).equals("nchara")
                || inComming.get(i).equals("ninta")
                || inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals("ID")
                || inComming.get(i).equals("(")) {

//            System.out.println("c" + inComming.get(i));
            if (e()) {

                //if file ended out of bound
                if (i >= inComming.size()) {
//                    System.out.println("hello");
                    return true;
                } else if (cPrime()) {
//                    System.out.println("Success c");
                    return true;
                } else {
                    errorIndex = i;
                }
            } else {
                errorIndex = i;
            }

        } else {
            errorIndex = i;
            //   i++;
            return false;
        }

        return false;
    }

    Boolean cPrime() {

        if (inComming.get(i).equals("RO")
                || inComming.get(i).equals("&")
                || inComming.get(i).equals("^")
                || inComming.get(i).equals("|")
                || inComming.get(i).equals("&&")
                || inComming.get(i).equals("||")
                || inComming.get(i).equals(")")
                || inComming.get(i).equals(";")
                || inComming.get(i).equals(",")
                || inComming.get(i).equals("\\")
                || inComming.get(i).equals("]")) {
//            System.out.println("cprime" + inComming.get(i));

            if (inComming.get(i).equals("RO")) {

                i++;

                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (e()) {
                    // if last token , out of bound sy bachny ke liye
                    if (i >= inComming.size()) {
//                        System.out.println("hello");
                        return true;
                    } else if (cPrime()) {

//                        System.out.println(" cPrime success");
                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } //remaining checks will return true if found somthing from follow
            //but if ) or ; thn it will only return follow when flag is true
            //            else if((inComming.get(i).equals(")"))||
            //                    (inComming.get(i).equals(";"))){
            //                            System.out.println("4hh");
            //                  if((inComming.get(i).equals(")")&&flagForBracket==true)||
            //                     (inComming.get(i).equals(";")&&flagForSemiColon==true   )  ){
            //                    
            //                      System.out.println("aPrime success");
            //                      return true;
            //                  }
            //                
            //            }
            else {
//                System.out.println(" cPrime success");

                return true;
            }

        } else {
            errorIndex = i;
            //     i++;
            return false;
        }
        return false;
    }

    Boolean e() {

        if (inComming.get(i).equals("nfloata")
                || inComming.get(i).equals("nstringa ")
                || inComming.get(i).equals("nchara")
                || inComming.get(i).equals("ninta")
                || inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals("ID")
                || inComming.get(i).equals("(")) {

//            System.out.println("e" + inComming.get(i));
            if (f()) {

                if (i >= inComming.size()) {
//                    System.out.println("hello");
                    return true;
                } else if (ePrime()) {
//                    System.out.println("e success");
                    return true;
                } else {
                    errorIndex = i;
                }
            } else {
                errorIndex = i;
            }

        } else {
            errorIndex = i;
            //      i++;
            return false;
        }

        return false;
    }

    Boolean ePrime() {

        if (inComming.get(i).equals("Add_Sub")
                || inComming.get(i).equals("RO")
                || inComming.get(i).equals("&")
                || inComming.get(i).equals("^")
                || inComming.get(i).equals("|")
                || inComming.get(i).equals("&&")
                || inComming.get(i).equals("||")
                || inComming.get(i).equals(")")
                || inComming.get(i).equals(";")
                || inComming.get(i).equals(",")
                || inComming.get(i).equals("\\")
                || inComming.get(i).equals("]")) {
//            System.out.println("eprime" + inComming.get(i));

            if (inComming.get(i).equals("Add_Sub")) {

                i++;
                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (f()) {

                    // if last token , out of bound sy bachny ke liye
                    if (i >= inComming.size()) {
//                        System.out.println("hello");
                        return true;
                    } else if (ePrime()) {

//                        System.out.println(" ePrime success");
                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    //         errorIndex = i;
                }
            }//remaining checks will return true if found somthing from follow
            //but if ) or ; thn it will only return follow when flag is true
            //            else if((inComming.get(i).equals(")"))||
            //                    (inComming.get(i).equals(";"))){
            //                            System.out.println("3hh");
            //                  if((inComming.get(i).equals(")")&&flagForBracket==true)||
            //                     (inComming.get(i).equals(";")&&flagForSemiColon==true   )  ){
            //                    
            //                      System.out.println("aPrime success");
            //                      return true;
            //                  }
            //                
            //            }
            else {

//                System.out.println("ePrime success");
                return true;
            }

        } else {
            errorIndex = i;
            //     i++;
            return false;
        }
        return false;
    }

    Boolean f() {

        if (inComming.get(i).equals("nfloata")
                || inComming.get(i).equals("nstringa ")
                || inComming.get(i).equals("nchara")
                || inComming.get(i).equals("ninta")
                || inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals("ID")
                || inComming.get(i).equals("(")) {

//            System.out.println("f" + inComming.get(i));
            if (g()) {

                if (i >= inComming.size()) {
//                    System.out.println("hello");
                    return true;
                } else if (fPrime()) {
//                    System.out.println(" f success");
                    return true;
                } else {
                    errorIndex = i;
                }
            } else {
                errorIndex = i;
            }

        } else {
            errorIndex = i;
            //     i++;
            return false;
        }

        return false;
    }

    Boolean fPrime() {

        if (inComming.get(i).equals("Div_Mod")
                || inComming.get(i).equals("Add_Sub")
                || inComming.get(i).equals("RO")
                || inComming.get(i).equals("&")
                || inComming.get(i).equals("^")
                || inComming.get(i).equals("|")
                || inComming.get(i).equals("&&")
                || inComming.get(i).equals("||")
                || inComming.get(i).equals(")")
                || inComming.get(i).equals(";")
                || inComming.get(i).equals(",")
                || inComming.get(i).equals("\\")
                || inComming.get(i).equals("]")) {

//            System.out.println("fprime" + inComming.get(i));
            if (inComming.get(i).equals("Div_Mod")) {

                i++;
                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (g()) {
                    // if last token , out of bound sy bachny ke liye
                    if (i >= inComming.size()) {
//                        System.out.println("hello");
                        return true;
                    } else if (fPrime()) {

//                        System.out.println(" fPrime success");
                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            }//remaining checks will return true if found somthing from follow
            //but if ) or ; thn it will only return follow when flag is true
            //            else if((inComming.get(i).equals(")"))||
            //                    (inComming.get(i).equals(";"))){
            //                            System.out.println("hh2");
            //                  if((inComming.get(i).equals(")")&&flagForBracket==true)||
            //                     (inComming.get(i).equals(";")&&flagForSemiColon==true   )  ){
            //                    
            //                      System.out.println("aPrime success");
            //                      return true;
            //                  }
            //                
            //            }
            else {
//                System.out.println(" fPrime success");

                return true;
            }

        } else {
            errorIndex = i;
            //   i++;
            return false;
        }
        return false;
    }

    Boolean g() {

        if (inComming.get(i).equals("nfloata")
                || inComming.get(i).equals("nstringa ")
                || inComming.get(i).equals("nchara")
                || inComming.get(i).equals("ninta")
                || inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals("ID")
                || inComming.get(i).equals("(")) {

//            System.out.println("g" + inComming.get(i));
            if (i()) {
               
                // if last token , out of bound sy bachny ke liye
                if (i >= inComming.size()) {
//                    System.out.println("hello");
                    return true;
                } else if (gPrime()) {
//                    System.out.println("success g");
                    return true;
                } else {
                    errorIndex = i;
                }
            } else {
                errorIndex = i;
            }

        } else {
            errorIndex = i;
            //       i++;
            return false;
        }

        return false;
    }

    Boolean gPrime() {

        if (inComming.get(i).equals("!")
                || inComming.get(i).equals("Div_Mod")
                || inComming.get(i).equals("Add_Sub")
                || inComming.get(i).equals("RO")
                || inComming.get(i).equals("&")
                || inComming.get(i).equals("^")
                || inComming.get(i).equals("|")
                || inComming.get(i).equals("&&")
                || inComming.get(i).equals("||")
                || inComming.get(i).equals(")")
                || inComming.get(i).equals(";")
                || inComming.get(i).equals(",")
                || inComming.get(i).equals("\\")
                || inComming.get(i).equals("]")) {

//            System.out.println("gprime " + inComming.get(i));
            if (inComming.get(i).equals("|")) {

                i++;
                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (i()) {
                   
                    if (gPrime()) {
//                        System.out.println("success gPrime");

                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            }//remaining checks will return true if found somthing from follow
            //but if ) or ; thn it will only return follow when flag is true
            else if ((inComming.get(i).equals(")"))
                    || (inComming.get(i).equals(";"))
                    || (inComming.get(i).equals(","))
                    || inComming.get(i).equals("]")
                    || inComming.get(i).equals("\\")/*only used where expression has a follow  \*/) {

//                System.out.println("1hh");
                if ((inComming.get(i).equals(")") &&/*flagForBracket==true*/ bracket.size() > 0)
                        || (inComming.get(i).equals(",") && comma.size() > 0)
                        || (inComming.get(i).equals(",") && commaDecl.size() > 0)
                        || (inComming.get(i).equals(";") &&/*flagForSemiColon==true*/ semiColon.size() > 0)
                        || inComming.get(i).equals("\\") && slash.size() > 0
                        || inComming.get(i).equals("]") && squareBracket.size() > 0
                        || /*for places where expression has a follow */ (inComming.get(i).equals(",") && commaAssign.size() > 0) /* this will unlock the comma only in assignment*/) {

                    if (inComming.get(i).equals(")")) {
                        //   flagForBracket=false;

                        bracket.pop();

//                        System.out.println(bracket.size());
                    } else if (inComming.get(i).equals(";")) {

                        //new      flagForSemiColon=false;
                        semiColon.pop();
                    } else if ((inComming.get(i).equals(",") && comma.size() > 0)) {
                        comma.pop();
                    } else if ((inComming.get(i).equals(",") && commaAssign.size() > 0)) {
                        commaAssign.pop();
                    } else if (inComming.get(i).equals("\\")) {
                        slash.pop();
                    } else if ((inComming.get(i).equals("]") && squareBracket.size() > 0)) {
                        squareBracket.pop();
                    } else if (inComming.get(i).equals(",") && commaDecl.size() > 0) {
                        commaDecl.pop();
                    }

//                    System.out.println("aPrime success");
                    return true;
                } else {
                    errorIndex = i;
                }

            } else {

//                System.out.println("success gPrime");
                return true;
            }

        } else {
            errorIndex = i;
            //     i++;
            return false;
        }
        return false;
    }

    //rule for I in expression
    Boolean i() {

        Boolean flag = false;

        if (inComming.get(i).equals("nfloata")
                || inComming.get(i).equals("nstringa ")
                || inComming.get(i).equals("nchara")
                || inComming.get(i).equals("ninta")
                || inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals("ID")
                || inComming.get(i).equals("(")) {

//            System.out.println("i" + inComming.get(i));
            if (inComming.get(i).equals("Inc_Dec")) {
                i++;

                if (i >= inComming.size()) {
                    return false;
                }

                if (inComming.get(i).equals("ID")) {
                    i++;

                    if (i >= inComming.size()) {
                        return true;
                    }

                    if (iD1()) {

                        if (array()) {

//                            System.out.println("success i");
                            flag = true;
                            return flag;
                        } else {
                            errorIndex = i;
                        }
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else if (inComming.get(i).equals("ID")) {
                
                nameExp.add(inCommingVp.get(i));
                
                i++;
                //for last element
                if (i >= inComming.size()) {

                    return true;
                }
                
               
                //first checking agr agla character array he to phr array me lookup hoga size ke sath
                if(!inComming.get(i).equals(":")){
                //gettinf type of variable
                //t1 will contain both data type and object name if present
                t.add(var.lookupe(nameExp.peek(), scopes, arrayCounterExp.get(iterationCalculator)));
                    System.out.println(nameExp.peek());
                    System.out.println("temp contains s "+t);
                if((var.lookupe(nameExp.peek(), scopes,arrayCounterExp.get(iterationCalculator)))==null){
                    
                    
                    System.out.println("Semantic Error: Variable "+nameExp.peek()+" not found @ "+inCommingLine.get(i));
                    return false;
                }
                
                //resetting value
                arrayCounterExp.set(iterationCalculator, 0);
                nameExp.pop();
                    
                } 
                
               
                if (iD1()) {
                    
                    //for last element
                    if (i >= inComming.size()) {

                        return true;
                    }
//                    System.out.println("inside(id1)  in i    id<id1> <array> <iprime>" + inComming.get(i));

                    if (array()) {
                        
                        //for last element
                        if (i >= inComming.size()) {
                            return true;

                        }
//                        System.out.println("inside(array)  in i    id<id1> <array> <iprime>" + inComming.get(i));
                        if (iPrime()) {

//                             System.out.println("inside(iprime)  in i    id<id1> <array> <iprime>"+inComming.get(i));
//                            System.out.println("success i");
                            flag = true;
                            return flag;
                        } else {
                            errorIndex = i;
                        }
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }

            } else if (inComming.get(i).equals("(")) {

                bracket.push("(");
                //  flagForBracket=true;
                i++;

                if (a()!=null) {

                    if (i >= inComming.size()) {
                        return false;
                    }

                    if (inComming.get(i).equals(")")) {
                        i++;

                        //bracket.push("(");
//                        System.out.println("success i");
                        flag = true;
                        return flag;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }

            } else if (inComming.get(i).equals("nfloata")
                    || inComming.get(i).equals("nstringa ")
                    || inComming.get(i).equals("nchara")
                    || inComming.get(i).equals("ninta")) {

                i++;
//                System.out.println("success i");
                flag = true;
                return flag;
            } else {
                errorIndex = i;
            }
        } else {
            errorIndex = i;
            flag = false;
            return flag;
        }

        return false;

    }

    Boolean iPrime() {
        if (inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals(".")
                || inComming.get(i).equals("(")
                || inComming.get(i).equals("!")
                || inComming.get(i).equals("Div_Mod")
                || inComming.get(i).equals("Add_Sub")
                || inComming.get(i).equals("RO")
                || inComming.get(i).equals("&")
                || inComming.get(i).equals("^")
                || inComming.get(i).equals("|")
                || inComming.get(i).equals("&&")
                || inComming.get(i).equals("||")
                || inComming.get(i).equals(")")
                || inComming.get(i).equals(";")
                || inComming.get(i).equals(",")
                || inComming.get(i).equals("\\")
                || inComming.get(i).equals("]")) {

//            System.out.println("iprime" + inComming.get(i));
            if (inComming.get(i).equals("Inc_Dec")) {
                i++;
//                System.out.println("success iPrime");

                return true;
            } else if (inComming.get(i).equals(".")
                    || inComming.get(i).equals("(")) {

                if (function_call()) {

//                    System.out.println("success iPrime");
                    return true;
                } else {
                    errorIndex = i;
                }

            } //            else if(inComming.get(i).equals("=")){
            //                
            //                i++;
            //                System.out.println(inComming.get(i));
            //                if(assignPrime()){
            //                    
            //                     System.out.println("success iPrime");
            //                    return true;
            //                }
            //            }
            else {
//                System.out.println("success iPrime");

                return true;
            }
        } else {
            errorIndex = i;
            //  i++;
            return false;
        }
        return false;
    }

    Boolean iD1() {

        if (inComming.get(i).equals(".")
                || inComming.get(i).equals("!")
                || inComming.get(i).equals("Div_Mod")
                || inComming.get(i).equals("Add_Sub")
                || inComming.get(i).equals("RO")
                || inComming.get(i).equals("&")
                || inComming.get(i).equals("^")
                || inComming.get(i).equals("|")
                || inComming.get(i).equals("&&")
                || inComming.get(i).equals("||")
                || inComming.get(i).equals(")")
                || inComming.get(i).equals(";")
                || inComming.get(i).equals(":")
                || inComming.get(i).equals("(")
                || (inComming.get(i).equals("]"))
                || //  inComming.get(i).equals("=")||
                inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals(",")
                || inComming.get(i).equals("\\")) {
//            System.out.println("id1 " + inComming.get(i));

            //temp out of boubd na krdy
            int temp = i + 1;
            if (temp >= inComming.size()) {
                temp = i;
            }
            if (inComming.get(i).equals(".") && !inComming.get(temp).equals(":")) {

                   i++;

                //for null pointer exception end nh hskta ispy to false
                if (i >= inComming.size()) {
//                    System.out.println("hello");
                    return false;
                }

                if (inComming.get(i).equals("ID")) {
                    
                    nameExp.add(inCommingVp.get(i));
                     i++;

                    //for last element
                    if (i >= inComming.size()) {
//                        System.out.println("Hello");
                        return true;
                    }
                    
                    
                    
                    
                        
               //first checking agr agla character array he to phr array me lookup hoga size ke sath
               
               if(!inComming.get(i).equals(":")){
                String tempi;
                
                tempi=var.compatibility(".", t.peek(), nameExp.peek(), scopes,arrayCounterExp.get(iterationCalculator));
                
              
                 t.pop();
                
                t.add(tempi);
                   System.out.println("Temp stack contains " +t);
                //agr null return kia to mtlb not found errie
                if(t.peek()==null){
                   
                   System.out.println("Semantic Error: Variable "+ nameExp.peek()+ " not found @ line no "+inCommingLine.get(i));
                   return false;
               }
                 arrayCounterExp.set(iterationCalculator, 0);
                nameExp.pop();
                } 
                   
                    if (iD1()) {

//                        System.out.println("success iD1");
                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else {
//                System.out.println("success iD1");

                return true;
            }

        } else {
            errorIndex = i;
            i++;
//            System.out.println(inComming.get(i));
            return false;
        }

        return false;
    }

    Boolean array() {

        if (inComming.get(i).equals(".")
                || inComming.get(i).equals("!")
                || inComming.get(i).equals("Div_Mod")
                || inComming.get(i).equals("Add_Sub")
                || inComming.get(i).equals("RO")
                || inComming.get(i).equals("&")
                || inComming.get(i).equals("^")
                || inComming.get(i).equals("|")
                || inComming.get(i).equals("&&")
                || inComming.get(i).equals("||")
                || inComming.get(i).equals(")")
                || inComming.get(i).equals(";")
                || inComming.get(i).equals(":")
                || inComming.get(i).equals("(")
                || (inComming.get(i).equals("]"))
                || // inComming.get(i).equals("=")||
                inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals(",")
                || inComming.get(i).equals("\\")) {

            ///System.out.println("array"+inComming.get(i));
            if (inComming.get(i).equals(":")) {
                //new     flagForSemiColon=true;
                semiColon.push(":");
                i++;
                 
                //incrementing current array value
                arrayCounterExp.set(iterationCalculator, arrayCounterExp.get(iterationCalculator)+1);
             
                //for index out of bound
                if (i >= inComming.size()) {

                    return false;

                } 
                //new         flag2ForSemiColon=flagForSemiColon;
               String tt=a(); 
                if (tt!=null&&tt.equals("ninta")) { 
                    
                    
                    //for index out of bound
                    if (i >= inComming.size()) {

                        return false;
                    }

                    if (inComming.get(i).equals(";")) {

                        i++;
                        
                         //for index out of bound
                        if (i >= inComming.size()) {

                            return true;
                        }
                        
                        
                        //means one variable ends
                        if(!inComming.get(i).equals(":")){
                         
                            //agr t ki length zero he to mtlb ye pehla variable he . krky koi nh he abh
                            //to simple lookup hoga
                            if(t.size()==0||iterationCalculator==t.size()+1){
                                
                                String tempi;
                                tempi=var.lookupe(nameExp.peek(), scopes, arrayCounterExp.get(iterationCalculator));
                                t.add(tempi);
                                System.out.println("Temp stack contains "+t);
                                
                                if(tempi==null){
                                    
                                    System.out.println("Semantic Error: variable "+nameExp.peek()+" not found @ line no "+inCommingLine.get(i));
                                    return false;
                                }
                              
                                ///resetting array counter
                                 arrayCounterExp.set(iterationCalculator, 0);
                                nameExp.pop();
                                
                            }
                            //agr ye pehla variable nh he to  phr . wali compatibility chk hgi
                            else{
                                String tempi;
                                    
                                tempi=var.compatibility(".", t.peek(), nameExp.peek(),scopes, arrayCounterExp.get(iterationCalculator));
                                t.pop();
                                t.add(tempi);
                                
                                System.out.println("Temp stack contains "+t);
                                
                                if(t.peek()==null){
                                    System.out.println("Semantic Error: Variable "+nameExp.peek()+" not found @ line no "+inCommingLine.get(i));
                                    return false;
                                }
                                 arrayCounterExp.set(iterationCalculator, 0);
                                nameExp.pop();
                            }
                        }

                       

                        if (iD1()) {

                           
                            
                            
                            //new                  flagForSemiColon=false;
                            //for index out of bound
                            if (i >= inComming.size()) {

                                return true;
                            }

                            if (array()) {
//                                System.out.println("success  array");
                                return true;
                            } else {
                                errorIndex = i;
                            }
                        } else {
                            errorIndex = i;
                        }
                    } else {
                        errorIndex = i;
                    }
                } else { System.out.println("Semantic Error: only ninta type is allowed inside array delimiters @ line no "+inCommingLine.get(i));
                    errorIndex = i;
                }
            } else {
//                System.out.println("success  array");

                return true;

            }
        } else {
            errorIndex = i;
            //    i++;
            return false;
        }

        return false;
    }

    boolean function_call() {

        if (inComming.get(i).equals(".")
                || inComming.get(i).equals("(")
                || inComming.get(i).equals("!")
                || inComming.get(i).equals("Div_Mod")
                || inComming.get(i).equals("Add_Sub")
                || inComming.get(i).equals("RO")
                || inComming.get(i).equals("&")
                || inComming.get(i).equals("^")
                || inComming.get(i).equals("|")
                || inComming.get(i).equals("&&")
                || inComming.get(i).equals("||")
                || inComming.get(i).equals(")")
                || inComming.get(i).equals(";")
                || inComming.get(i).equals(",")
                || inComming.get(i).equals("]")
                || inComming.get(i).equals("\\")) {

//            System.out.println("function_call" + inComming.get(i));
            int temp = i - 1;
            if (inComming.get(i).equals(".") && inComming.get(temp).equals(";")
                    || inComming.get(i).equals(".") && inComming.get(temp).equals(")")) {

                i++;
                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }
                if (inComming.get(i).equals("ID")) {
                    i++;

                    if (i >= inComming.size()) {
                        return false;
                    }

                    if (inComming.get(i).equals("(")) {
                        i++;
                        bracket.push("(");
                        //agr () hoga srf to push nh krengy
                        if (inComming.get(i).equals(")")) {
                            bracket.pop();
                        }
//                         flagForBracket=true;
//                          flag2ForBracket=true;
                        if (i >= inComming.size()) {

                            return false;
                        }

                        if (/*inComming.get(i).equals(")")*/parameters()) {
                            //      i++;
                            //   flagForBracket=flag2ForBracket;
                            if (i >= inComming.size()) {
                                return true;
                            }

                            if (function_call()) {

//                                System.out.println("success function_call");
                                return true;
                            } else {
                                errorIndex = i;
                            }
                        } else {
                            errorIndex = i;
                        }
//                         }
                    } else {
                        errorIndex = i;
                    }

                } else {
                    errorIndex = i;
                }

            } else if (inComming.get(i).equals("(") && inComming.get(i - 1).equals("ID")) {
                i++;
                bracket.push("(");
                //if () mtlb empty hain to push nh krengy
                if (inComming.get(i).equals(")")) {
                    bracket.pop();
                }
                // flagForBracket=true; //flagForBracket;
                // flag2ForBracket=true;
                //for null poiter
                if (i >= inComming.size()) {
                    return false;
                }

                if (/*inComming.get(i).equals(")")*/parameters()) {
                    //            i++;
                    //    flagForBracket=flag2ForBracket;
//                    System.out.println("inside(  )) in ( <parameters> <function_Call");
                    //for null poiter
                    if (i >= inComming.size()) {
                        return true;
                    }
                    if (function_call()) {
//                        System.out.println("inside(  function call) in ( <parameters> <function_Call");
//                        System.out.println("success function_call");
                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }

            } else {
//                System.out.println("success function_call");

                return true;
            }
        } else {
            errorIndex = i;
            //   i++;
            return false;
        }

        return false;
    }

    boolean parameters() {

        if (inComming.get(i).equals("nfloata")
                || inComming.get(i).equals("nstringa ")
                || inComming.get(i).equals("nchara")
                || inComming.get(i).equals("ninta")
                || inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals("ID")
                || inComming.get(i).equals("(")
                || inComming.get(i).equals(")")) {

//            System.out.println(inComming.get(i));
            if (inComming.get(i).equals("nfloata")
                    || inComming.get(i).equals("nstringa ")
                    || inComming.get(i).equals("nchara")
                    || inComming.get(i).equals("ninta")
                    || inComming.get(i).equals("Inc_Dec")
                    || inComming.get(i).equals("ID")
                    || inComming.get(i).equals("(")) {

                comma.push(",");
                if (a()!=null) {

                    if (comma.size() > 0) {
                        comma.pop();
                    }
                    //for null pointer
                    if (i >= inComming.size()) {
                        return true;
                    }
                    if (opara()) {

//                        System.out.println("success parametr");
                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else if (inComming.get(i).equals(")") && !inComming.get(i - 1).equals(",")) {
                i++;
//                System.out.println("success parametr");
                return true;
            } else {
                errorIndex = i;
            }
        } else {
            errorIndex = i;
            //   i++;
            return false;
        }

        return false;
    }

    boolean opara() {

        if (inComming.get(i).equals(",")
                || inComming.get(i).equals(")")) {

            if (inComming.get(i).equals(",")) {

                i++;
                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (parameters()) {

//                    System.out.println("success opara");
                    return true;
                } else {
                    errorIndex = i;
                }
            } else {
                i++;
//                System.out.println("success opara");
                return true;
            }

        } else {
            errorIndex = i;
            //   i++;
            //   System.out.println("success opara");
            return false;
        }
        return false;
    }

    ///                           ASSIGNMETN  STARTED
    Boolean assign() {
        
        if (inComming.get(i).equals("ID")) {

            nameA=inCommingVp.get(i);
//            System.out.println("assign" + inComming.get(i));
            i++;
         
            //for out of bound
            if (i >= inComming.size()) {

//                System.out.println("Assignment failed");
                return false;
            }

            
             //first checking agr agla character array he to phr array me lookup hoga size ke sath
                if(!inComming.get(i).equals(":")){
                //gettinf type of variable
                    
                //t1 will contain both data type and object name if present
                tA.add(var.lookupe(nameA, scopes, arrayCounterAssign));
                    
                    System.out.println("temp contains s "+tA);
                if((var.lookupe(nameA, scopes,arrayCounterAssign))==null){
                    
                    
                    System.out.println("Semantic Error: Variable "+nameA+" not found @ line no "+inCommingLine.get(i));
                    return false;
                }
                
               
                    
                } 
            
            
            
            if (iD1Assign()) {

                //for out of bound
                if (i >= inComming.size()) {
//                    System.out.println("Assignment failed");
                    return false;
                }

                if (arrayAssign()) {

                    //for out of bound
                    if (i >= inComming.size()) {

//                        System.out.println("Assignment failed");
                        return false;
                    }
                    
                    //YAHAN FINAL CHECK KRNY WALA KAAM
                  //means lookup will also made in interface
                    if(dotUsed==0){
                       if(var.lookupAFinal(nameA, scopes, arrayTemp)==null){
                        
                           System.out.println("Semantic Error: values cant be assigned to absolute variables @ line no "+inCommingLine.get(i) );
                           return false;
                       }
                   }
                    else{
                        System.out.println(nameA+" "+typeRestorer+" "+scopes);
                        if(var.lookupA2Final(nameA,typeRestorer,scopes)==false){
                            
                            System.out.println("Semantic Error: values cant be assigned to absoulute variables @ line no "+inCommingLine.get(i));
                       return false;
                        }
                    }
                    
                    

                    if (inComming.get(i).equals("AO") || inComming.get(i).equals("=")) {

                        opA=inCommingVp.get(i);
                        System.out.println("Done "+tA);
                        i++;

                        //for out of bound
                        if (i >= inComming.size()) {

//                            System.out.println("Assignment failed");
                            return false;
                        } 
                        squareBracket.push("]");
                        if (assignPrime()) {

                            if (i >= inComming.size()) {
                                if (squareBracket.size() > 0) {
                                    squareBracket.pop();
//                                    System.out.println("Failed Assignment");
                                }
//                                System.out.println("Assignment failed");
                                return false;
                            }
                            if (inComming.get(i).equals("]")) {
                                       
                                
                                        //resetting for next time when it will be called
                                        arrayCounterAssign=0;
                                        tA.setSize(0);
                                        dotUsed=0;
                                        arrayTemp=0;
                                        opA=null;
                                        nameA=null;
                                        typeRestorer=null;
//                                System.out.println("Success Assignment");
                                return true;
                            } else {
                                errorIndex = i;
                            }
                        } else {
                            if (squareBracket.size() > 0) {
                                squareBracket.pop();
                            }
                            errorIndex = i;
//                            System.out.println("Failed Assignment");
                        }
                    } else {
                        failedCfgs.add("Assignment");
                        errorIndex = i;
//                        System.out.println("Failed Assignment");
                    }
                } else {
                    failedCfgs.add("Assignment");
                    errorIndex = i;
//                    System.out.println("Failed Assignment");
                }
            } else {
                failedCfgs.add("Assignment");
                errorIndex = i;
//                System.out.println("Failed Assignment");
            }
        } else {
            failedCfgs.add("Assignment");
            errorIndex = i;
//            System.out.println("Failed Assignment");
        }
        return false;
    }

     Boolean iD1Assign() {
        if (inComming.get(i).equals(".")
                || inComming.get(i).equals(":")
                || inComming.get(i).equals("AO") || inComming.get(i).equals("=")) {

//            System.out.println("iD1Assign " + inComming.get(i));
            if (inComming.get(i).equals(".")) {
                dotUsed++;
                i++;

                //for null pointer exception end nh hskta ispy to false
                if (i >= inComming.size()) {
//                    System.out.println("hello");
                    return false;
                }

                if (inComming.get(i).equals("ID")) {
                      nameA=inCommingVp.get(i);
                    i++;
                    
                    //for last element
                    if (i >= inComming.size()) {
//                        System.out.println("Hello");
                        return true;
                    }
                    
                    
                     //first checking agr agla character array he to phr array me lookup hoga size ke sath
               
               if(!inComming.get(i).equals(":")){
                String tempi;
                 //  System.out.println(tA+"kkkk");
                tempi=var.compatibility(".", tA.peek(), nameA, scopes,arrayCounterAssign);
                
              
                typeRestorer= tA.pop();
                
                tA.add(tempi);
                   System.out.println("Temp stack contains " +tA);
                //agr null return kia to mtlb not found errie
                if(tA.peek()==null){
                   
                   System.out.println("Semantic Error: Variable "+ nameA+ " not found @ line no "+inCommingLine.get(i));
                   return false;
               }
                 arrayCounterAssign=0;
                } 

                    
                    if (iD1Assign()) {

//                        System.out.println("success iD1assgnment");
                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else {

                return true;
            }

        } else {
            errorIndex = i;
        }

        return false;
    }

    Boolean arrayAssign() {

        if (inComming.get(i).equals(":")
                || inComming.get(i).equals("AO") || inComming.get(i).equals("=")) {

//            System.out.println("arrayAssign " + inComming.get(i));
            if (inComming.get(i).equals(":")) {
                System.out.println("");
                semiColon.push(":");
                i++;
                
                arrayCounterAssign++;
                
                //for index out of bound
                if (i >= inComming.size()) {

                    return false;

                }
                   String t=a();
                   
                if ( t!=null&&t.equals("ninta")) {
                    System.out.println(" hi");
                    //for index out of bound
                    if (i >= inComming.size()) {

                        return false;
                    }

                    if (inComming.get(i).equals(";")) {

                        i++;

                        //for index out of bound
                        if (i >= inComming.size()) {

                            return true;
                        }
                        
                        
                         //means one variable ends
                        if(!inComming.get(i).equals(":")){
                         
                            //agr t ki length zero he to mtlb ye pehla variable he . krky koi nh he abh
                            //to simple lookup hoga
                            if(tA.size()==0){
                                
                                String tempi;
                                tempi=var.lookupe(nameA, scopes, arrayCounterAssign);
                                tA.add(tempi);
                                System.out.println("Temp stack contains "+tA);
                                
                                if(tempi==null){
                                    
                                    System.out.println("Semantic Error: variable "+nameA+" not found @ line no "+inCommingLine.get(i));
                                    return false;
                                }
                              arrayTemp=arrayCounterAssign;
                               arrayCounterAssign=0;
                                
                            }
                            //agr ye pehla variable nh he to  phr . wali compatibility chk hgi
                            else{
                                String tempi;
                                    
                                tempi=var.compatibility(".", tA.peek(), nameA,scopes, arrayCounterAssign);
                                typeRestorer=tA.pop();
                                tA.add(tempi);
                                
                                System.out.println("Temp stack contains "+tA);
                                
                                if(tA.peek()==null){
                                    System.out.println("Semantic Error: Varaible "+nameA+"not found @ line no "+inCommingLine.get(i));
                                    return false;
                                }
                                arrayTemp=arrayCounterAssign;
                                arrayCounterAssign=0;
                            }
                        }


                        if (iD1Assign()) {

                            //for index out of bound
                            if (i >= inComming.size()) {

                                return true;
                            }

                            if (arrayAssign()) {
//                                System.out.println("success  arrayassign");
                                return true;
                            } else {
                                errorIndex = i;
                            }
                        } else {
                            errorIndex = i;
                        }
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else {
//                System.out.println("Success arrayAssign");
                return true;
            }

        } else {

        }

        return false;
    }
    
    Boolean assignPrime() {

        if (inComming.get(i).equals("nfloata")
                || inComming.get(i).equals("nstringa ")
                || inComming.get(i).equals("nchara")
                || inComming.get(i).equals("ninta")
                || inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals("ID")
                || inComming.get(i).equals("(")
                || inComming.get(i).equals("Boolean")
                || inComming.get(i).equals("new")) {

//            System.out.println("assignPrime" + inComming.get(i));
//            if (inComming.get(i).equals("/")) {
//
//                slash.push("//");
//                i++;
//                //for out of bound
//                if (i >= inComming.size()) {
//
//                    return false;
//                }
//                commaAssign.push(",");
//                if ((oexp())) {
//
//                    //for out of bound
//                    if (i >= inComming.size()) {
//
//                        return false;
//                    }
//                    if (aexp()) {
//
//                        if (i >= inComming.size()) {
//                            return false;
//                        }
//
//                        if (inComming.get(i).equals("\\")) {
//
//                            System.out.println("Success AssignPrime");
//                            return true;
//
//                        }
//                    }
//                }
//            } 
            if (oexp()) {
//                System.out.println("Success AssignPrime");
                return true;
            } else {
                errorIndex = i;
            }

        } else {
            errorIndex = i;
            return false;
        }

        return false;

    }

    Boolean oexp() {
        if (inComming.get(i).equals("nfloata")
                || inComming.get(i).equals("nstringa ")
                || inComming.get(i).equals("nchara")
                || inComming.get(i).equals("ninta")
                || inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals("ID")
                || inComming.get(i).equals("Boolean")
                || inComming.get(i).equals("new")) {
                   String tt;
//            System.out.println("oexp " + inComming.get(i));
            if (inComming.get(i).equals("new")) {

                i++;
                //for out of bound
                if (i >= inComming.size()) {

                    return false;
                }
                if (inComming.get(i).equals("ID")) {
                        
                    obAName=inCommingVp.get(i);
                    i++;
                    //for out of bound
                    if (i >= inComming.size()) {

                        return false;
                    }
                    
                    //checking compatibllities
                    if(opA.equals("=")){
                        String assignType=var.compatibility(opA, tA.peek(), obAName, scopes, 0);
                        //first checking if both have same name
                        if(assignType!=null){
                            
                            //inserting object name
                            if(var.varFinderAssign(tA.peek(), scopes, nameA)!=-1){
                                
                                var.varTable.get(var.varFinderAssign(tA.peek(), scopes, nameA)).object=obAName;
                            }
                            System.out.println("Assignment typem is "+assignType);
                            assignType=null;
                        }
                        else{
                            
                            assignType=var.objectCompatibility(obAName, scopes, tA.peek());
                            if(assignType==null){
                                System.out.println("Semantic Error: type mismatch @ line no "+inCommingLine.get(i));
                                return false;
                            }
                            else{
                                
                                 //inserting object name
                                  if(var.varFinderAssign(tA.peek(), scopes, nameA)!=-1){
                                      
                                    var.varTable.get(var.varFinderAssign(tA.peek(), scopes, nameA)).object=obAName;
                                  }
                            System.out.println("Assignment typem is "+assignType);
                            
                                System.out.println("Assignment type is "+assignType);
                                assignType=null;
                            }
                        }
                        
                        
                    }
                    else{
                        System.out.println("Semantic Error: Only nfloata and ninta types are allowed with "+opA+" operator @ line no "+inCommingLine.get(i));
                   return false;
                    }
                    
                    if (inComming.get(i).equals("(")) {
                        bracket.push("(");
                        i++;
                        if (inComming.get(i).equals(")")) {
                            bracket.pop();
                        }
                        //for out of bound
                        if (i >= inComming.size()) {

                            return false;
                        }
                        if (parameters()) {

//                            System.out.println("Success oexp");
                            return true;
                        } else {
                            errorIndex = i;
                        }
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } 
            
            else if ((tt=a())!=null) {
                
                //dono taraf ki compatibility chkng
//                System.out.println("Success oexp");
                if(opA.equals("=")){
                    String assignType=var.compatibility(opA, tA.peek(), tt, scopes, 0);
                    
                    if(assignType!=null){
                        
                        System.out.println("Assignment type is "+assignType);
                        assignType=null;
                    }else{
                        System.out.println("Semantic Error: type mismatch @ line no "+inCommingLine.get(i));
                        return false;
                    }
                }
                //for += etx
                else{
                    
                    if((tA.peek().equals("ninta")&&tt.equals("ninta"))||
                        (tA.peek().equals("nfloata")&&tt.equals("nfloata"))    ){
                        
                        //in this case it is correct
                    }
                    else{
                        System.out.println("Semantic Error: Operator "+opA+" is allowed only with ninta and nfloata data types @ line no "+inCommingLine.get(i));
                   return false;
                    }
                    
                }
                return true;
            } else {
                errorIndex = i;
            }
        }
        return false;
    }

   

    ////////////                        CLASS START HE YAHAN
    boolean Class() {
        if (inComming.get(i).equals("AM")
                || inComming.get(i).equals("stable")
                || inComming.get(i).equals("absolute")
                || inComming.get(i).equals("class")) {

            scope++;
             
           
         
//            System.out.println("Class " + inComming.get(i));
            if (am()) {

                if (stable()) {

                    if (Final()) {

                        if (inComming.get(i).equals("class")) {

                            type = "class";
                            i++;
                            //for null pointer
                            if (i >= inComming.size()) {
//                                System.out.println("Class failed");
                                return false;
                            }

                            if (inComming.get(i).equals("ID")) {

                                name = inCommingVp.get(i);
                                i++;
                                //for null pointer
                                if (i >= inComming.size()) {
//                                    System.out.println("class failed");
                                    return false;
                                }
                                //bcz main class can also initialze same variables because of same functions
                                Implements = new ArrayList<String>();

                                Extends = null;

                                if (parent()) {

                                    //lookinup procedure for class and inner class first if is for parent and else is for inner
                                    //if scopestack is empty means it is not a inner class and parent will be null and scope will be default zero 
                                    if (scopes.size()==0/*cls.lookUp(name, scope)*/) {
                                        
                                        scopes.push(scope);
                                        System.out.println("Scopes stack contains "+scopes);
                                        
                                      // because all parent classes have scope of zero lookng up with scope 0
                                        if(cls.lookUp(name, /*scope*/0)/*scopes.size()==0*/){
                                            
                                        
                                        cls.insert(/* parent*/null,type, name, /*scope*/0, Static, Final, Extends, Implements, AM,scope);
                                        
                                         System.out.println("insertion successfull..........................................." + inCommingVp.get(i));
                                        
                                       classParent.push(name);//will contain parent nnames for inner classes
                                       
                                       
                                        } 
                                        else{
                                            System.out.println("Semantic Error: Redeclaration error " + inCommingVp.get(i)+" @ line no "+inCommingLine.get(i));
                                                 return false;
                                            
                                        }
                                       
                                    } 
                                    //agar scope stack empty nh mtlb inner class he to parent dalna parega or scope b wo use hoga class k functions me use hua he
                                    else {
                                                                          //class body scope
                                         if(cls.lookUp(name,scopes ,false/*(int) scopes.get(scopes.size()-1))*//*scopes.size()==0*/)){ //setting flag false bcx dnt chk for 0 scope
                                            
                                             scopes.push(scope);
                                             System.out.println("Scopes stack contains "+scopes);
                                        
                                             //for same name of parent and inner class bcz it cant be caught by using lookup with same scope
                                             if(classParent.contains(name)){
                                                 System.out.println("Semantic Error: Redeclaration error class named "+name+" already found @ line no "+inCommingLine.get(i));
                                                 return false;
                                             }
                                             // lass ki body ka scope dega scopes.get(scopes.size()-2
                                            cls.insert(classParent.get(classParent.size()-1).toString(), type, name, (int) scopes.get(scopes.size()-2), Static,Final, Extends, Implements, AM,scope);
                                            
                                            classParent.push(name);
                                         
                                          // clsScope=scope;//saving body scope of class
                                             
                                         }
                                         else{
                                        System.out.println("Semantic Error: Redeclaration error class named "+ name +" already found @ line no "+inCommingLine.get(i));
                                        return false;
                                         }
                                    }
                                    //used in constructor
                                    clsName=name; 
                                    type = name = Static = Extends = Final = AM = null;
                                   Implements = null;

                                    if (inComming.get(i).equals("/")) {
                                        i++;

                                        if (bodyClass()) {

                                            //Code for checking if class ne override kiay hain saray interface methods ya nh
                                            
                                                if(!fun.implementsOverrideMethods((int)scopes.get(scopes.size()-1))){
                                                    System.out.println("Semantic Error: Class should override all interface methods @ line no "+inCommingLine.get(i));
                                                    return false;
                                                }
                                            
                                            
                                            //resetting Implements to null for next iteratio
                                               
                                            if (inComming.get(i).equals("\\")) {

                                                classParent.pop();
                                                scopes.pop();
//                                                //resetting clas scope incase if it was a inner class
//                                                if(scopes.size()!=0){
//                                                    clsScope= (int) scopes.get(scopes.size()-1);
//                                                }
                                                  System.out.println("Scope stack containns" + scopes);
//                                                System.out.println("Success Class");
                                                return true;
                                            } else {
                                                failedCfgs.add("Class");
                                                errorIndex = i;
//                                                System.out.println("Class FAiled");
                                            }
                                        } else {
                                            failedCfgs.add("Class");
                                            errorIndex = i;
//                                            System.out.println("Class FAiled");
                                        }
                                    } else {
                                        failedCfgs.add("Class");
                                        errorIndex = i;
//                                        System.out.println("Class FAiled");
                                    }
                                } else {
                                    failedCfgs.add("Class");
                                    errorIndex = i;
//                                    System.out.println("Class FAiled");
                                }
                            } else {
                                failedCfgs.add("Class");
                                errorIndex = i;
//                                System.out.println("Class FAiled");
                            }
                        } else {
                            failedCfgs.add("Class");
                            errorIndex = i;
//                            System.out.println("Class FAiled");
                        }
                    } else {
                        failedCfgs.add("Class");
                        errorIndex = i;
//                        System.out.println("Class FAiled");
                    }
                } else {
                    failedCfgs.add("Class");
                    errorIndex = i;
//                    System.out.println("Class FAiled");
                }
            } else {
                failedCfgs.add("Class");
                errorIndex = i;
//                System.out.println("Class FAiled");
            }
        } else {
            failedCfgs.add("Class");
            errorIndex = i;
//            System.out.println("class Failed");
        }

        return false;
    }

    private boolean am() {

        if (inComming.get(i).equals("absolute")
                || inComming.get(i).equals("AM")
                || inComming.get(i).equals("stable")
                || inComming.get(i).equals("class")) {

            if (inComming.get(i).equals("AM")) {

                AM = inCommingVp.get(i);
                i++;
                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }

//                System.out.println("AM success");
                return true;
            } else {
//                System.out.println("AM success");
                return true;
            }
        } else {
            errorIndex = i;
        }
        return false;
    }

    boolean stable() {

        if (inComming.get(i).equals("absolute")
                || inComming.get(i).equals("stable")
                || inComming.get(i).equals("class")) {

            if (inComming.get(i).equals("stable")) {

                Static = "stable";
                i++;
                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }

//                System.out.println("stable success");
                return true;
            } else {
//                System.out.println("stable success");
                return true;
            }
        } else {
            errorIndex = i;
        }
        return false;
    }

    boolean Final() {

        if (inComming.get(i).equals("absolute")
                || inComming.get(i).equals("class")) {

            if (inComming.get(i).equals("absolute")) {

                Final = "absolute";
                System.out.println(Final);
                i++;
                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }

//                System.out.println("absolute success");
                return true;
            } else {
//                System.out.println("absolute success");
                return true;
            }
        } else {
            errorIndex = i;
        }
        return false;
    }

    boolean parent() {

        if (inComming.get(i).equals("extends")
                || inComming.get(i).equals("implements")
                || inComming.get(i).equals("/")) {
//            System.out.println("parent " + inComming.get(i));

            if (inComming.get(i).equals("extends")
                    || inComming.get(i).equals("implements")) {

                if (Extends()) {

                    if (Implements()) {

//                        System.out.println("parent success");
                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else {
//                System.out.println("parent success");
                return true;

            }
        } else {
            errorIndex = i;
        }
        return false;
    }

    private boolean Extends() {

        if (inComming.get(i).equals("extends")
                || inComming.get(i).equals("implements")
                || inComming.get(i).equals("/")) {

//            System.out.println("extends " + inComming.get(i));
            if (inComming.get(i).equals("extends")) {

                i++;

                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (inComming.get(i).equals("ID")) {

                    Extends = inCommingVp.get(i);
                    
                    //checking for inheritance class name
                    if(!cls.lookUp(Extends, scope)|| //same scope ki us naam ki class
                       classParent.contains(Extends)|| //koi parent class us naam ki
                   //    !cls.lookUp(Extends, 0)|| //zero sccope me us naam ki class
                        !cls.lookUp(Extends,scopes,true) //setting flag to falsse becx dnt wanna chk for 0 scope  
                            ) 
                       {
                        
                        if(!cls.finalFinder(Extends)){
                        
                            System.out.println("Semantic Error: Final class cant be inherited @ line no "+inCommingLine.get(i));
                            return false;
                        }
                      
                        
                    }
                    else{
                        System.out.println("Semantic Error: Parent class not found @ line no "+inCommingLine.get(i));
                        return false;
                    }
                    
                    i++;
                    //for null pointer
                    if (i >= inComming.size()) {
                        return true;
                    }

                    if (iD1Class()) {

//                        System.out.println("extends success");
                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }

            } else {
//                System.out.println("extends succes");
                return true;
            }
        } else {
            errorIndex = i;
        }
        return false;
    }

    private boolean Implements() {

        if (inComming.get(i).equals("extends")
                || inComming.get(i).equals("implements")
                || inComming.get(i).equals("/")) {

//            System.out.println("implements " + inComming.get(i));
            if (inComming.get(i).equals("implements")) {

                i++;
                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (inComming.get(i).equals("ID")) {
                    System.out.println(inCommingVp.get(i));
                    Implements.add(inCommingVp.get(i));
                    System.out.println(Implements);
                    
                   //chkng implements name in table
                   if(cls.implementsLoopkup(Implements.get(Implements.size()-1))){
                       System.out.println("Implements success");
                   }
                   else{
                       System.out.println("Semantic Error: Interface "+inCommingVp.get(i)+" not found @ line no "+inCommingLine.get(i));
                       return false;
                   }
                    
                    
                    i++;
                    //for null pointer
                    if (i >= inComming.size()) {
                        return true;
                    }

                    if (iD1Class()) {

                        if (listClass()) {

//                            System.out.println("implemetns success");
                            return true;
                        } else {
                            errorIndex = i;
                        }

                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }

            } else {
//                System.out.println("implements succes");
                return true;
            }
        } else {
            errorIndex = i;
        }
        return false;
    }

    private boolean iD1Class() {

        if (inComming.get(i).equals(".")
                || inComming.get(i).equals("implements")
                || inComming.get(i).equals("/")
                || inComming.get(i).equals(",")) {

//            System.out.println("iD1Class " + inComming.get(i));
            if (inComming.get(i).equals(".")) {

                i++;

                //for null pointer exception end nh hskta ispy to false
                if (i >= inComming.size()) {
//                    System.out.println("hello");
                    return false;
                }

                if (inComming.get(i).equals("ID")) {

                    i++;

                    //for last element
                    if (i >= inComming.size()) {
//                        System.out.println("Hello");
                        return true;
                    }
                    if (iD1Class()) {

//                        System.out.println("success iD1class");
                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else {
//                System.out.println("success iD1class");
                return true;
            }

        } else {
            errorIndex = i;
        }

        return false;
    }

    private boolean listClass() {

        if (inComming.get(i).equals(",")
                || inComming.get(i).equals("/")) {

//            System.out.println("listClass " + inComming.get(i));
            if (inComming.get(i).equals(",")) {

                i++;

                //for null pointer exception end nh hskta ispy to false
                if (i >= inComming.size()) {
//                    System.out.println("hello");
                    return false;
                }

                if (inComming.get(i).equals("ID")) {

                    
                    //checcking for repeated interface like implements na,na is not allowed
                    if(!Implements.contains(inCommingVp.get(i))){
                        Implements.add(inCommingVp.get(i));
                        
                    }
                    else{
                        System.out.println("Semantic Error: Repeated Interface not allowed @ line no "+inCommingLine.get(i));
                        return false;
                    }
                     //chkng implements name in table
                   if(cls.implementsLoopkup(Implements.get(Implements.size()-1))){
                     
                   }
                   else{
                       System.out.println("Semantic Error: Interface "+inCommingVp.get(i)+" not found @ line no "+inCommingLine.get(i));
                       return false;
                       
                   }
                    i++;

                    //for last element
                    if (i >= inComming.size()) {
//                        System.out.println("Hello");
                        return true;
                    }
                    if (iD1Class()) {
//                        i++;
//                        //for last element
//                        if (i >= inComming.size()) {
//                            System.out.println("Hello");
//                            return true;
//                        }

                        if (listClass()) {
//                            System.out.println("success lisclass");
                            return true;

                        } else {
                            errorIndex = i;
                        }
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else {
//                System.out.println("success listclass");
                return true;
            }

        } else {
            errorIndex = i;
        }

        return false;

    }

    boolean bodyClass() {

        if (inComming.get(i).equals("AM")
                || inComming.get(i).equals("stable")
                || inComming.get(i).equals("absolute")
                || inComming.get(i).equals("ID")
                || inComming.get(i).equals("DT")
                || inComming.get(i).equals("class")
                || inComming.get(i).equals("\\")
                || inComming.get(i).equals("empty")) {

//            System.out.println("bodyClass " + inComming.get(i));
            if (inComming.get(i).equals("AM")
                    || inComming.get(i).equals("stable")
                    || inComming.get(i).equals("absolute")
                    || inComming.get(i).equals("ID")
                    || inComming.get(i).equals("DT")
                    || inComming.get(i).equals("class")
                    || inComming.get(i).equals("empty")) {

                temp = i;
                if (s_stClass()) {
                    if (i >= inComming.size()) {
                        return true;
                    }

//                    
//                     //for last element
//                     if(i>=inComming.size()){
//                         System.out.println("Hello");
//                          return true;
//                      } 
                    if (m_stClass()) {

//                        System.out.println("success bodyclass");
                        return true;

                    } else {
                        //    errorIndex = i;
                    }
                } else {
                    //errorIndex = i;
                }
            } else {
//                System.out.println("success bodyclass");
                return true;
            }

        } else {
            //errorIndex = i;
        }

        return false;

    }

    private boolean s_stClass() {

        if (inComming.get(temp).equals("AM")
                || inComming.get(temp).equals("stable")
                || inComming.get(temp).equals("absolute")
                || inComming.get(temp).equals("ID")
                || inComming.get(temp).equals("DT")
                || inComming.get(temp).equals("class")
                || inComming.get(temp).equals("empty")) {

//            System.out.println("s_stClass " + inComming.get(temp));
            if (amS_ST()) {

                if (s_stLeftFactor()) {

//                    System.out.println("s_stclass success");
                    return true;
                } else {
                    //  errorIndex = i;
                }
            } else {
                // errorIndex = i;
            }

        } else {
            // errorIndex = i;
        }
        return false;
    }

    private boolean m_stClass() {

        if (inComming.get(i).equals("AM")
                || inComming.get(i).equals("stable")
                || inComming.get(i).equals("absolute")
                || inComming.get(i).equals("ID")
                || inComming.get(i).equals("DT")
                || inComming.get(i).equals("class")
                || inComming.get(i).equals("empty")
                || inComming.get(i).equals("\\")) {

//            System.out.println("m_stClass " + inComming.get(i));
            if (inComming.get(i).equals("AM")
                    || inComming.get(i).equals("stable")
                    || inComming.get(i).equals("absolute")
                    || inComming.get(i).equals("ID")
                    || inComming.get(i).equals("DT")
                    || inComming.get(i).equals("class")
                    || inComming.get(i).equals("empty")) {

                temp = i;
                if (s_stClass()) {
                    if (i >= inComming.size()) {
                        return true;
                    }
                    if (m_stClass()) {

//                        System.out.println("m_stclass success");
                        return true;
                    } else {
                        //     errorIndex = i;
                    }

                } else {
                    //  errorIndex = i;
                }

            } else {
//                System.out.println("m_stClass success");
                return true;
            }
        } else {
            //errorIndex = i;
        }
        return false;

    }

    private boolean amS_ST() {
        if (inComming.get(temp).equals("absolute")
                || inComming.get(temp).equals("AM")
                || inComming.get(temp).equals("stable")
                || inComming.get(temp).equals("ID")
                || inComming.get(temp).equals("empty")
                || inComming.get(temp).equals("DT")
                || inComming.get(temp).equals("class")) {

//            System.out.println("ams_stClass " + inComming.get(temp));
            if (inComming.get(temp).equals("AM")) {

                temp++;

                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }

//                System.out.println("AMs_St success");
                return true;
            } else {
//                System.out.println("AMs_St success");
                return true;
            }
        } else {
            // errorIndex = i;
        }
        return false;
    }

    boolean stableS_St() {

        if (inComming.get(temp).equals("absolute")
                || inComming.get(temp).equals("stable")
                || inComming.get(temp).equals("empty")
                || inComming.get(temp).equals("ID")
                || inComming.get(temp).equals("DT")
                || inComming.get(temp).equals("class")) {

//            System.out.println("astables_stClass " + inComming.get(temp));
            if (inComming.get(temp).equals("stable")) {

                temp++;
                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }

//                System.out.println("stables_ST success");
                return true;
            } else {
//                System.out.println("stables_ST success");
                return true;
            }
        } else {
            //  errorIndex = i;
        }
        return false;
    }

    boolean FinalS_ST() {

        if (inComming.get(temp).equals("absolute")
                || inComming.get(temp).equals("ID")
                || inComming.get(temp).equals("DT")
                || inComming.get(temp).equals("empty")
                || inComming.get(temp).equals("class")) {

//            System.out.println("absolutes_stClass " + inComming.get(temp));
            if (inComming.get(temp).equals("absolute")) {

                temp++;
                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }

//                System.out.println("absoluteS_ST success");
                return true;
            } else {
//                System.out.println("absoluteS_ST success");
                return true;
            }
        } else {
            //  errorIndex = i;
        }
        return false;
    }

    boolean s_stLeftFactor() {

        if (inComming.get(temp).equals("absolute")
                || inComming.get(temp).equals("ID")
                || inComming.get(temp).equals("DT")
                || inComming.get(temp).equals("empty")
                || inComming.get(temp).equals("stable")
                || inComming.get(temp).equals("class")) {

//            System.out.println("s_stleftfctr  " + inComming.get(temp));
            if (inComming.get(temp).equals("ID")) {

                temp++;
                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }
                if (s_stLeftFactorPrimePrime()) {

//                    System.out.println("SST left factor success");
                    return true;
                }

            } else if (inComming.get(temp).equals("absolute")
                    || inComming.get(temp).equals("DT")
                    || inComming.get(temp).equals("empty")
                    || inComming.get(temp).equals("stable")
                    || inComming.get(temp).equals("class")) {

                if (stableS_St()) {

                    if (FinalS_ST()) {

                        if (s_stLeftFactorPrime()) {

//                            System.out.println("s_stLEftFActor success");
                            return true;
                        } else {
                            //       errorIndex = i;
                        }
                    } else {
                        //   errorIndex = i;
                    }
                } else {
                    //  errorIndex = i;
                }
            } else {
                //   errorIndex = i;
            }
        } else {
            //  errorIndex = i;
        }
        return false;
    }

    boolean s_stLeftFactorPrime() {
        if (inComming.get(temp).equals("ID")
                || inComming.get(temp).equals("DT")
                || inComming.get(temp).equals("empty")
                || inComming.get(temp).equals("class")) {

//            System.out.println("sstLeftfctrPrime " + inComming.get(temp));
            if (inComming.get(temp).equals("ID")) {
                temp++;

                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }
                if (s_stLeftFactorPrimePrime()) {

//                    System.out.println("s_stLeftFactorPrime success");
                    return true;
                } else {
                    //       errorIndex = i;
                }
            } else if (inComming.get(temp).equals("DT")) {
                temp++;

                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }
                if (decisionForFunDecl()) {

//                    System.out.println("s_stLeftFactorPrime success");
                    return true;
                } else {
                    //       errorIndex = i;
                }
            } else {
                //     errorIndex = i;
            }
            if (inComming.get(temp).equals("empty")) {
                temp++;

                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }
                if (functionDeclarationClass()) {
                    i++;
                    if (i >= inComming.size()) {
                        return false;
                    }
//                    System.out.println("sending for function declaration");
//                    System.out.println("s_stLeftFactorPrime success");
                    return true;
                } else {
                    //        errorIndex = i;
                }
            } else {
                //   errorIndex = i;
            }
            if (inComming.get(temp).equals("class")) {
                temp++;

                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }
                if (Class()) {

                    i++;
                    //null pointer
                    if (i >= inComming.size()) {
                        return false;
                    }
//                    System.out.println("s_stLeftFactorPrime success" + inComming.get(i));
                    return true;
                } else {
                    //      errorIndex = i;
                }
            } else {
                //     errorIndex = i;
            }
        } else {
            //    errorIndex = i;
        }
        return false;
    }

    private boolean s_stLeftFactorPrimePrime() {

        if (inComming.get(temp).equals("ID")
                || inComming.get(temp).equals("(")
                || inComming.get(temp).equals(".")
                || inComming.get(temp).equals(":")
                || inComming.get(temp).equals("AO")
                || inComming.get(temp).equals("=")
                || inComming.get(temp).equals("Inc_Dec")) {

            if(decisionForFunDecl()){
                
                return true;
            }
            //LASTCHANGE starting here  upar walay if ko remove krky nichy wala comment uncomment krdo purani cheez restore hojaegi

//            if (inComming.get(temp).equals("ID")) {
//                temp++;
//
//                //for null pointer
//                if (i >= inComming.size()) {
//                    return false;
//                }
//                //calling declaration
//                if (xDecl()/*checkBracket()*/) {    // LASTCHANGE now methods with return type id are allowed
//                    i++;
//                    if (i >= inComming.size()) {
//                        return false;
//                    }
////                    System.out.println("sending for declaration");
////                    System.out.println("s_stLeftFactorPrimePrime success");
//                    return true;
//                } else {
//                    //   errorIndex = i;
//                }
//            }
            else if (inComming.get(temp).equals("(")) {
                temp++;

                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }
                if (constructorDeclarationClass()) {
                    i++;
                    if (i >= inComming.size()) {
                        return false;
                    }
//                    System.out.println("sending for constructer declaration");
//                    System.out.println("s_stLeftFactorPrimePrime success");
                    return true;
                } else {
                    //    errorIndex = i;
                }
            } else if (inComming.get(temp).equals(".")
                    || inComming.get(temp).equals(":")
                    || inComming.get(temp).equals("AO")
                    || inComming.get(temp).equals("=")
                    || inComming.get(temp).equals("Inc_Dec")) //errorIndex = i;
            {
                //assignment
                if (xDecl()) {
                    i++;
                    if (i >= inComming.size()) {
                        return false;
                    }
//                    System.out.println("Assignment success");
                    return true;

                }
            }
        } else {
            //  errorIndex = i;
        }

        return false;
    }

    boolean decisionForFunDecl() {

        if (inComming.get(temp).equals("ID")) {

            temp++;
           
            if (checkBracket()) {
                
//                System.out.println("decision success");
                return true;
            } else {
                //      errorIndex = i;
            }
        } else {
            //  errorIndex = i;
        }
        return false;
    }

    boolean checkBracket() {
        if (inComming.get(temp).equals("(")) {
            temp++;
            //null pointer
            if (i >= inComming.size()) {
                return false;
            }
            if (functionDeclarationClass()) {
                i++;
                if (i >= inComming.size()) {
                    return false;
                }
//                System.out.println("function declaration" + inComming.get(i));
                System.out.println("checkBracket success" + inComming.get(i + 1));
                return true;
            } else {
                //      errorIndex = i;
            }
        } else if (inComming.get(temp).equals("=") || inComming.get(temp).equals("]") || inComming.get(temp).equals(":")) {
            temp++;
            //null pointer
            if (i >= inComming.size()) {
                return false;
            }
            if (xDecl()) {
                i++;
                if (i >= inComming.size()) {
                    return false;
                }
//                System.out.println("sending for declaration");
//                System.out.println("checkBracket success");
                return true;
            } else {
                //           errorIndex = i;
            }
        } else {
            //      errorIndex = i;
        }
        return false;
    }

    //salman ka ccode for constructor declaration
    boolean constructorDeclarationClass() {
        if (/*AM1_norm()*/inComming.get(i).equals("AM") || inComming.get(i).equals("ID")) {

           scope++;
           scopes.push(scope);
             System.out.println("Scope stack containns" + scopes);
            if (inComming.get(i).equals("AM")) {
                AM=inCommingVp.get(i);
                i++;
                //null poiner 
                if (i >= inComming.size()) {
                    return false;
                }

            }

            if (inComming.get(i).equals("ID")) {
                name=inCommingVp.get(i);
                i++;
                //null poiner 
                if (i >= inComming.size()) {
                    return false;
                }
                if (inComming.get(i).equals("(")) {
                    i++;
                    //null poiner 
                    if (i >= inComming.size()) {
                        return false;
                    }
                    returnType=Static=Final=null;
                    parameters=new ArrayList<Variables>();
                    if (para_decl()) {
                        System.out.println(classParent.size());
                        if(!classParent.get(classParent.size()-1).equals(name)/*!clsName.equals(name)*/){
                            System.out.println("Semantic Error: Invalid function "+name+" no return type found @ line no "+inCommingLine.get(i));
                            return false;
                        }
                          if (fun.consLookUp(name,(int)scopes.get(scopes.size()-2) /*clsScope*/, parameters)) {

                                        fun.insert(returnType, name,(int)scopes.get(scopes.size()-2) /*clsScope*/, Static, Final, parameters, AM);
                                        System.out.println("insertion constriuctor successfull..........................................." + inCommingVp.get(i));
                                    } else {
                                        System.out.println("Semantic Error: Redeclaration Constructor error " + inCommingVp.get(i)+" @ line no "+inCommingLine.get(i));
                                        return false;
                                    }
                                    name = returnType = AM = Static = Final = null;
                                    parameters = null;
                                    arrayCounter = 0;
                                    
                        if (inComming.get(i).equals(")")) {
                            i++;
                            if (inComming.get(i).equals("/")) {

                                i++;
                                //null poiner 
                                if (i >= inComming.size()) {
                                    return false;
                                }

                                if (body()) {

                                    if (inComming.get(i).equals("\\")) {
//                                        System.out.println("Constructor declaration Class success ");
                                        scopes.pop();
                                        System.out.println("Scope stack containns" + scopes);
                                        return true;

                                    } else {
                                        errorIndex = i;
                                    }
                                } else {
                                    errorIndex = i;
                                }

                            } else {
                                errorIndex = i;
                            }
                        } else {
                            errorIndex = i;
                        }
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else {
                errorIndex = i;
            }
        } else {
            errorIndex = i;
        }
        return false;
    }

    boolean functionDeclarationClass() {
        if (AM1_norm()) {
            scope++;
            scopes.push(scope);
              System.out.println("Scope stack containns" + scopes);

            if (STABLE_norm()) {
                if (FINAL_norm()) {
                    if (inComming.get(i).equals("ID") || inComming.get(i).equals("DT") || inComming.get(i).equals("empty")) {

                        returnType = inCommingVp.get(i);
                        //checking if return type class exists or not
                        //will return true if class not found
                        if(cls.lookUp(returnType, scopes, Boolean.TRUE)&&
                                inComming.get(i).equals("ID")){
                            
                            System.out.println("Semantic Error: class name "+ type+" used in return type not found @ line no "+inCommingLine.get(i));
                            return false;
                        }
                        System.out.println("hi"+returnType);
                        i++;

                        //null poiner 
                        if (i >= inComming.size()) {
                            return false;
                        }
                        if (inComming.get(i).equals("ID")) {

                            name = inCommingVp.get(i);
                            i++;
                            //null poiner 
                            if (i >= inComming.size()) {
                                return false;
                            }

                            if (inComming.get(i).equals("(")) {
                                i++;
                                //null poiner 
                                if (i >= inComming.size()) {
                                    return false;
                                }
                                parameters = new ArrayList<Variables>();
                                if (para_decl()) {
                                    System.out.println("before");
                                    if (fun.lookUp(name, (int) scopes.get(scopes.size()-2), parameters)==null) {
                                        System.out.println("afer");
                                     //   System.out.println(scopes.get(scopes.size()-2)+"h"+clsScope);
                                        fun.insert(returnType, name, (int) scopes.get(scopes.size()-2), Static, Final, parameters, AM);
                                        System.out.println("insertion function successfull..........................................." + inCommingVp.get(i));
                                    } else {
                                        System.out.println("Semantic Error: Redeclaration Function error" + inCommingVp.get(i)+" @ line no "+inCommingLine.get(i));
                                        return false;
                                    }
                                    name = returnType = AM = Static = Final = null;
                                    parameters = null;
                                    arrayCounter = 0;
                                    
                                    if (inComming.get(i).equals(")")) {
                                        i++;
                                        if (inComming.get(i).equals("/")) {
                                          
                                            i++;
                                            //null poiner 
                                            if (i >= inComming.size()) {
                                                return false;
                                            }

                                            if (body()) {

                                                if (inComming.get(i).equals("\\")) {
                                                          scopes.pop();
                                                            System.out.println("Scope stack containns" + scopes);
//                                                    System.out.println("Function declaration Class success");
                                                    return true;

                                                } else {
                                                    errorIndex = i;
                                                }
                                            } else {
                                                errorIndex = i;
                                            }
                                        } else {
                                            errorIndex = i;
                                        }
                                    } else {
                                        errorIndex = i;
                                    }
                                } else {
                                    errorIndex = i;
                                }
                            } else {
                                errorIndex = i;
                            }
                        } else {
                            errorIndex = i;
                        }
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else {
                errorIndex = i;
            }
        } else {
            errorIndex = i;
        }
        return false;
    }

//paradeclare same !!!
//body same
    boolean AM1_norm() {
        if (inComming.get(i).equals("AM") || inComming.get(i).equals("stable") || inComming.get(i).equals("absolute")
                || inComming.get(i).equals("DT") || inComming.get(i).equals("ID") || inComming.get(i).equals("empty")) {

            if (inComming.get(i).equals("AM")) {
                AM = inCommingVp.get(i);
                i++;

                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }

//                System.out.println("AM_norm succcess");
                return true;
            } else if (inComming.get(i).equals("stable") || inComming.get(i).equals("absolute")
                    || inComming.get(i).equals("DT") || inComming.get(i).equals("ID") || inComming.get(i).equals("empty")) {
//                System.out.println("AM_norm succcess");
                return true;
            } else {
                errorIndex = i;
            }
        } else {
            errorIndex = i;
        }
        return false;
    }

    boolean STABLE_norm() {
        if (inComming.get(i).equals("stable") || inComming.get(i).equals("absolute")
                || inComming.get(i).equals("DT") || inComming.get(i).equals("ID") || inComming.get(i).equals("empty")) {

            if (inComming.get(i).equals("stable")) {
                Static = inCommingVp.get(i);
                i++;
                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }
//                System.out.println("stable_norm succcess");
                return true;
            } else if (inComming.get(i).equals("absolute")
                    || inComming.get(i).equals("DT") || inComming.get(i).equals("ID") || inComming.get(i).equals("empty")) {
//                System.out.println("stable_norm succcess");
                return true;
            } else {
                errorIndex = i;
            }
        } else {
            errorIndex = i;
        }
        return false;
    }

    boolean FINAL_norm() {
        if (inComming.get(i).equals("absolute")
                || inComming.get(i).equals("DT") || inComming.get(i).equals("ID") || inComming.get(i).equals("empty")) {


            if (inComming.get(i).equals("absolute")) {
                Final = inCommingVp.get(i);
                i++;

                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }

//                System.out.println("absolute_norm succcess");
                return true;

            } else if (inComming.get(i).equals("DT") || inComming.get(i).equals("ID") || inComming.get(i).equals("empty")) {
//                System.out.println("absolute_norm succcess");
                return true;

            } else {
                errorIndex = i;
            }
        } else {
            errorIndex = i;
        }
        return false;
    }

    //////////////// MAIN CLASSS
    boolean mainClass() {
        if (inComming.get(i).equals("AM")
                || inComming.get(i).equals("absolute")
                || inComming.get(i).equals("absolute")
                || inComming.get(i).equals("novitachocolate")) {

            scope++;

            if (amMainClass()) {

                if (FinalMainClass()) {

                    if (inComming.get(i).equals("novitachocolate")) {
                        type="novitachocolate";
                        i++;
                        //for null pointer
                        if (i >= inComming.size()) {
//                            System.out.println("MainClass failed");
                            return false;
                        }

                        if (inComming.get(i).equals("ID")) {
                            
                            name=inCommingVp.get(i);
                            i++;
                            //for null pointer
                            if (i >= inComming.size()) {
//                                System.out.println("Mainclass failed");
                                return false;
                            }
                            
                            Implements=new ArrayList<String>();
                            Extends=null;
                            
                            if (parent()) {

                                
                                if(cls.lookUp(name, 0)){
                                    
                                    scopes.push(scope);
                                    System.out.println("Scope Stack contains "+scopes);
                                    
                                   cls.insert(null, type, name, /*scope*/0, /*Static*/null, Final, Extends, Implements, AM,scope);
                                    
                                    System.out.println("Insertion MainClass succcessfull");
                                    classParent.push(name);
                                }
                                else{
                                    System.out.println("Semantic Error: Redeclaration Error MainClass @ line no "+inCommingLine.get(i));
                                }
                                
                                //used in constructor
                                clsName=name;
                                type=name=Static=Extends=Final=AM=null;
                                Implements=null;
                                
                                if (inComming.get(i).equals("/")) {
                                    i++;
                                    //for null pointer
                                    if (i >= inComming.size()) {
//                                        System.out.println("Mainclass faield");
                                        return false;
                                    }

                                    if (bodyMainClass()) {

//                                            i++;
//                                            //for null pointer
//                                            if (i >= inComming.size()) {
//                                                System.out.println("class failed");
//                                                return false;
//                                            }
                                        if (inComming.get(i).equals("\\")) {
                                            
                                            classParent.pop();
                                            scopes.pop();
                                            System.out.println("Scope Stack contains "+scopes);
//                                            System.out.println("Success MainClass");
                                            return true;
                                        }
                                    } else {
                                        failedCfgs.add("Main Class");
                                        errorIndex = i;
//                                        System.out.println("MainClass FAiled");
                                    }
                                } else {
                                    failedCfgs.add("Main Class");
                                    errorIndex = i;
//                                    System.out.println("MainClass FAiled");
                                }
                            } else {
                                failedCfgs.add("Main Class");
                                errorIndex = i;
//                                System.out.println("MainClass FAiled");
                            }
                        } else {
                            failedCfgs.add("Main Class");
                            errorIndex = i;
//                            System.out.println("MainClass FAiled");
                        }
                    } else {
                        failedCfgs.add("Main Class");
                        errorIndex = i;
//                        System.out.println("MainClass FAiled");
                    }
                } else {
                    failedCfgs.add("Main Class");
                    errorIndex = i;
//                    System.out.println("MainClass FAiled");
                }

            } else {
                failedCfgs.add("Main Class");
                errorIndex = i;
//                System.out.println("MainClass FAiled");
            }
        } else {
            failedCfgs.add("Main Class");
            errorIndex = i;

        }

        return false;
    }

    private boolean amMainClass() {

        if (inComming.get(i).equals("absolute")
                || inComming.get(i).equals("AM")
                || inComming.get(i).equals("novitachocolate")) {

            if (inComming.get(i).equals("AM")) {
                AM=inCommingVp.get(i);
                i++;
                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }

//                System.out.println("AMMainClass success");
                return true;
            } else {
//                System.out.println("AMMainClass success");
                return true;
            }
        } else {
            errorIndex = i;
        }
        return false;
    }

    boolean FinalMainClass() {

        if (inComming.get(i).equals("absolute")
                || inComming.get(i).equals("novitachocolate")) {

            if (inComming.get(i).equals("absolute")) {
                Final="absolute";
                i++;
                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }

//                System.out.println("FinalMAinClass success");
                return true;
            } else {
//                System.out.println("FinalMAinClass success");
                return true;
            }
        } else {
            errorIndex = i;
        }
        return false;
    }

    boolean bodyMainClass() {

        if (inComming.get(i).equals("AM")
                || inComming.get(i).equals("stable")
                || inComming.get(i).equals("absolute")
                || inComming.get(i).equals("ID")
                || inComming.get(i).equals("DT")
                || inComming.get(i).equals("class")
                || inComming.get(i).equals("\\")
                || inComming.get(i).equals("empty")) {


            if (inComming.get(i).equals("AM")
                    || inComming.get(i).equals("stable")
                    || inComming.get(i).equals("absolute")
                    || inComming.get(i).equals("ID")
                    || inComming.get(i).equals("DT")
                    || inComming.get(i).equals("class")
                    || inComming.get(i).equals("empty")) {

                temp = i;
                if (s_stMainClass()) {

                    if (m_stMainClass()) {

//                        System.out.println("success bodyMainclass");
                        return true;

                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else {
//                System.out.println("success bodyMainclass");
                return true;
            }

        } else {
            errorIndex = i;
        }

        return false;

    }

    private boolean s_stMainClass() {

        if (inComming.get(temp).equals("AM")
                || inComming.get(temp).equals("stable")
                || inComming.get(temp).equals("absolute")
                || inComming.get(temp).equals("ID")
                || inComming.get(temp).equals("DT")
                || inComming.get(temp).equals("class")
                || inComming.get(temp).equals("empty")) {


            if (amS_ST()) {

                if (s_stLeftFactorMainClass()) {

//                    System.out.println("s_stclass success");
                    return true;
                } else {
                    ///        errorIndex = i;
                }
            } else {
                //      errorIndex = i;
            }

        } else {
            //     errorIndex = i;
        }
        return false;
    }

    private boolean m_stMainClass() {

        if (inComming.get(i).equals("AM")
                || inComming.get(i).equals("stable")
                || inComming.get(i).equals("absolute")
                || inComming.get(i).equals("ID")
                || inComming.get(i).equals("DT")
                || inComming.get(i).equals("class")
                || inComming.get(i).equals("empty")
                || inComming.get(i).equals("\\")) {


            if (inComming.get(i).equals("AM")
                    || inComming.get(i).equals("stable")
                    || inComming.get(i).equals("absolute")
                    || inComming.get(i).equals("ID")
                    || inComming.get(i).equals("DT")
                    || inComming.get(i).equals("class")
                    || inComming.get(i).equals("empty")) {

                temp = i;
                if (s_stMainClass()) {

                    if (m_stMainClass()) {

//                        System.out.println("m_stMainclass success");
                        return true;
                    } else {
                        errorIndex = i;
                    }

                } else {
                    errorIndex = i;
                }

            } else {
//                System.out.println("m_stMainClass success");
                return true;
            }
        } else {
            errorIndex = i;
        }
        return false;

    }

    boolean s_stLeftFactorMainClass() {

        if (inComming.get(temp).equals("absolute")
                || inComming.get(temp).equals("ID")
                || inComming.get(temp).equals("DT")
                || inComming.get(temp).equals("empty")
                || inComming.get(temp).equals("stable")
                || inComming.get(temp).equals("class")) {


            if (inComming.get(temp).equals("absolute")
                    || inComming.get(temp).equals("DT")
                    || inComming.get(temp).equals("empty")
                    || inComming.get(temp).equals("stable")
                    || inComming.get(temp).equals("class")) {

                if (stableS_St()) {

                    if (FinalS_ST()) {

                        if (s_stLeftFactorPrimeMainClass()) {

//                            System.out.println("s_stLEftFActorMainClass success");
                            return true;
                        } else {
                            //               errorIndex = i;
                        }
                    } else {
                        //             errorIndex = i;
                    }
                } else {
                    //            errorIndex = i;
                }
            } else if (inComming.get(temp).equals("ID")) {

                temp++;
                //null pointer

                if (i >= inComming.size()) {
                    return false;
                }
                if (s_stLeftFactorPrimePrime()) {

//                    System.out.println("SST left factorMainClass success");
                    return true;
                } else {
                    //         errorIndex = i;
                }

            } else {
                //        errorIndex = i;
            }
        } else {
            //     errorIndex = i;
        }
        return false;
    }

    boolean s_stLeftFactorPrimeMainClass() {
        if (inComming.get(temp).equals("ID")
                || inComming.get(temp).equals("DT")
                || inComming.get(temp).equals("empty")
                || inComming.get(temp).equals("class")) {


            if (inComming.get(temp).equals("ID")) {
                temp++;

                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }
                if (s_stLeftFactorPrimePrimeMainClass()) {

//                    System.out.println("s_stLeftFactorPrimeMainClass success");
                    return true;
                } else {
                    //          errorIndex = i;
                }
            } else if (inComming.get(temp).equals("DT")) {
                temp++;

                //for null pointer
                if (temp >= inComming.size()) {
                    return false;
                }
                if (decisionForFunDeclMainClass()) {

//                    System.out.println("s_stLeftFactorPrimeMainClass success");
                    return true;
                } else {
                    //        errorIndex = i;
                }
            } else {
                //     errorIndex = i;
            }
            if (inComming.get(temp).equals("empty")) {
                temp++;

                //for null pointer
                if (temp >= inComming.size()) {
                    return false;
                }
                if (mainOrNormalFunction()) {

//                    System.out.println("s_stLeftFactorPrimeMainClass success");
                    return true;
                } else {
                    //         errorIndex = i;
                }
            } else {
                //     errorIndex = i;
            }
            if (inComming.get(temp).equals("class")) {
                temp++;

                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }
                if (Class()) {

                    i++;
                    //null pointer
                    if (i >= inComming.size()) {
                        return false;
                    }

//                    System.out.println("s_stLeftFactorPrimeMainClass success" + inComming.get(i));
                    return true;
                } else {
                    //         errorIndex = i;
                }
            } else {
                //       errorIndex = i;
            }
        } else {
            //    errorIndex = i;
        }
        return false;
    }

    private boolean s_stLeftFactorPrimePrimeMainClass() {

        if (inComming.get(temp).equals("ID")
                || inComming.get(temp).equals("(")
                || inComming.get(i).equals(".")
                || inComming.get(i).equals(":")
                || inComming.get(i).equals("=")
                || inComming.get(i).equals("AO")
                || inComming.get(i).equals("Inc_Dec")) {


            if (inComming.get(temp).equals("ID")) {
                temp++;

                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }
                ///call declaration
                if (xDecl()) {
                    i++;
                    //null pointer
                    if (i >= inComming.size()) {
                        return false;
                    }
//                    System.out.println("sending for declaration");
//                    System.out.println("s_stLeftFactorPrimePrimeMainClass success");
                    return true;
                } else {
                    //          errorIndex = i;
                }
            } else if (inComming.get(temp).equals("(")) {
                temp++;

                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }
                if (constructorDeclarationClass()) {
                    i++;
                    //null pointer 
                    if (i >= inComming.size()) {
                        return false;
                    }
//                    System.out.println("sending for constructer declaration");
//                    System.out.println("s_stLeftFactorPrimePrimeMainClass success");
                    return true;
                } else {
                    //         errorIndex = i;
                }
            } else //calling assignment and declaration
            {
                if (xDecl()) {
                    i++;
                    if (i >= inComming.size()) {
                        return false;
                    }
                    return true;
                } //        errorIndex = i;
            }
        } else {
            //    errorIndex = i;
        }
        return false;
    }

    boolean decisionForFunDeclMainClass() {
        if (inComming.get(temp).equals("ID")) {
            temp++;
            //null pointer
            if (temp >= inComming.size()) {
                return false;
            }
            if (checkBracket()) {
//                System.out.println("decisionMAinClass success");
                return true;
            } else {
                //    errorIndex = i;
            }
        } else if (inComming.get(i).equals("novita")) {
            temp++;
            //null pointer
            if (temp >= inComming.size()) {
                return false;
            }
            if (mainfunctionDeclaration()) {
                i++;
                //nullpointer
                if (temp >= inComming.size()) {

                    return false;
                }
//                System.out.println("send to mainfunction call");
                return true;
            } else {
                //      errorIndex = i;
            }

        } else {
            //   errorIndex = i;
        }
        return false;
    }

    private boolean mainOrNormalFunction() {
        if (inComming.get(temp).equals("ID")) {
            temp++;

            //nullpointer
            if (temp >= inComming.size()) {
                return false;
            }
            if (functionDeclarationClass()) {
                i++;
                //null pointer 
                if (i >= inComming.size()) {
                    return false;
                }
//                System.out.println("Send for function call1");
//                System.out.println("mainOrNormalFunction success");
                return true;
            } else {
                //        errorIndex = i;
            }

        } else if (inComming.get(temp).equals("novita")) {
            temp++;

            //nullpointer
            if (temp >= inComming.size()) {

                return false;
            }
            if (mainfunctionDeclaration()) {
                i++;
                //nullpointer
                if (temp >= inComming.size()) {

                    return false;
                }
//                System.out.println("Send for main function call");
//                System.out.println("mainOrNormalFunction success");
                return true;
            } else {
                //      errorIndex = i;
            }

        } else {
            //  errorIndex = i;
        }
        return false;
    }

    boolean mainfunctionDeclaration() {
        if (AM1_norm()) {
            
            scope++;
            scopes.push(scope);
              System.out.println("Scope stack containns" + scopes);
              

            if (STABLE_norm()) {
                if (FINAL_norm()) {
                    if (inComming.get(i).equals("empty")) {
                        returnType="empty";
                        i++;
                        //null poiner 
                        if (i >= inComming.size()) {
                            return false;
                        }
                        if (inComming.get(i).equals("novita")) {
                            name="novita";
                            i++;
                            //null poiner 
                            if (i >= inComming.size()) {
                                return false;
                            }

                            if (inComming.get(i).equals("(")) {
                                i++;
                                //null poiner 
                                if (i >= inComming.size()) {
                                    return false;
                                }
                                parameters=new ArrayList<Variables>();
                                if (para_decl()) {
                                    
                                    if(fun.lookUp(name, (int) scopes.get(scopes.size()-2), parameters)==null){
                                        
                                        fun.insert(returnType, name, (int) scopes.get(scopes.size()-2), Static, Final, parameters, AM);
                                        System.out.println("insertion main function successfull");
                                    }
                                    else{
                                        System.out.println("Semantic Error: Redeclaration error main function @ line no "+inCommingLine.get(i));
                                        return false;
                                    }
                                     name = returnType = AM = Static = Final = null;
                                    parameters = null;
                                    arrayCounter = 0;
                                    
                                    if (inComming.get(i).equals(")")) {
                                        i++;
                                        if (inComming.get(i).equals("/")) {

                                            i++;
                                            //null poiner 
                                            if (i >= inComming.size()) {
                                                return false;
                                            }

                                            if (body()) {

                                                if (inComming.get(i).equals("\\")) {
                                                    scopes.pop();
                                                    System.out.println("Scope Stack contains "+scopes);
//                                                    System.out.println("Main Function declaration  success");
                                                    return true;

                                                } else {
                                                    errorIndex = i;
                                                }
                                            } else {
                                                errorIndex = i;
                                            }
                                        } else {
                                            errorIndex = i;
                                        }
                                    } else {
                                        errorIndex = i;
                                    }
                                } else {
                                    errorIndex = i;
                                }
                            } else {
                                errorIndex = i;
                            }
                        } else {
                            errorIndex = i;
                        }
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else {
                errorIndex = i;
            }
        } else {
            errorIndex = i;
        }
        return false;
    }

    ////////////////// INTERFACE STARTED /////////////////////////////
    boolean Interface() {
// callig extend list as implement list 
//inner interface hata diye hain
        scope++;
        scopes.push(scope);
        System.out.println("Scope contains "+scopes);
  
        if (inComming.get(i).equals("interface")) {
            type=inCommingVp.get(i);

            i++;
            //null pointer
            if (i >= inComming.size()) {
                return false;
            }

            if (inComming.get(i).equals("ID")) {
                name=inCommingVp.get(i);
                Implements=new ArrayList();
                Extends=null;
                i++;
                //null pointer
                if (i >= inComming.size()) {
//                    System.out.println("Failed Interface");
                    return false;
                }
                if (extendsInterface()) {

                      if(cls.lookUp(name,0 /*scope*/)){
                          cls.insert(null, type, name, 0, /*Static*/null, /*Final*/null, /*Extends*/null, Implements, /*AM*/null,scope);
                      }
                      else{
                          System.out.println("Semantic Error: Redeclaration error "+name+" already found @ line no "+inCommingLine.get(i));
                          return false;
                      }
                       
                       
                      type=name=null;
                      Implements=null;
                       if (inComming.get(i).equals("/")) {

                        i++;
                        //null pointer
                        if (i >= inComming.size()) {
//                            System.out.println("Failed Interface");
                            return false;
                        }
                        if (bodyInterface()) {

                            if (inComming.get(i).equals("\\")) {
                                   scopes.pop();
                                   System.out.println("Scope contains "+scopes);
                                           
//                                System.out.println("Success Interface");
                                return true;
                            } else {
                                failedCfgs.add("Interface");
                                errorIndex = i;
                            }
                        } else {
                            failedCfgs.add("Interface");
                            errorIndex = i;
//                            System.out.println("Failed Interface");
                        }
                    } else {
                        failedCfgs.add("Interface");
                        errorIndex = i;
//                        System.out.println("Failed Interface");
                    }
                } else {
                    failedCfgs.add("Interface");
                    errorIndex = i;
//                    System.out.println("Failed Interface");
                }
            } else {
                failedCfgs.add("Interface");
                errorIndex = i;
//                System.out.println("Failed Interface");
            }
        } else {
            failedCfgs.add("Interface");
            errorIndex = i;

        }
        return false;
    }

    private boolean extendsInterface() {

        if (inComming.get(i).equals("extends")
                || inComming.get(i).equals("/")) {


            if (inComming.get(i).equals("extends")) {
                     
                i++;

                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (inComming.get(i).equals("ID")) {
                      Implements.add(inCommingVp.get(i));
                      
                       //chkng implements name in table
                   if(cls.implementsLoopkup(Implements.get(Implements.size()-1))){
                       System.out.println("Implements success");
                   }
                   else{
                       System.out.println("Semantic Error Interface "+inCommingVp.get(i)+" not found @ line no "+inCommingLine.get(i));
                       return false;
                   }
                   
                    i++;
                    //for null pointer
                    if (i >= inComming.size()) {
                        return true;
                    }

                    if (iD1Interface()) {

                        if (listInterface()) {

//                            System.out.println("extendsInterface success");
                            return true;
                        } else {
                            errorIndex = i;
                        }
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }

            } else {
//                System.out.println("extendsInterface succes");
                return true;
            }
        } else {
            errorIndex = i;
        }
        return false;
    }

    boolean listInterface() {
        if (inComming.get(i).equals(",")
                || inComming.get(i).equals("/")) {


            if (inComming.get(i).equals(",")) {

                i++;

                //for null pointer exception end nh hskta ispy to false
                if (i >= inComming.size()) {
//                    System.out.println("hello");
                    return false;
                }

                if (inComming.get(i).equals("ID")) {
                         
                     //checcking for repeated interface like implements na,na is not allowed
                    if(!Implements.contains(inCommingVp.get(i))){
                        Implements.add(inCommingVp.get(i));
                        
                    }
                    else{
                        System.out.println("Semantic Error Repeated Interface "+ inCommingVp.get(i)+" not allowed @ line no "+inCommingLine.get(i));
                        return false;
                    }
                     //chkng implements name in table
                   if(cls.implementsLoopkup(Implements.get(Implements.size()-1))){
                     
                   }
                   else{
                       System.out.println("Semantic Error: Interface "+inCommingVp.get(i)+" not found @ line no "+inCommingLine.get(i));
                       return false;
                       
                   }
                   
                    i++;

                    //for last element
                    if (i >= inComming.size()) {
//                        System.out.println("Hello");
                        return true;
                    }
                    if (iD1Interface()) {

                        if (listInterface()) {
//                            System.out.println("success listInterfcace");
                            return true;

                        } else {
                            errorIndex = i;
                        }
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else {
//                System.out.println("success listInterfcce");
                return true;
            }

        } else {
            errorIndex = i;
        }

        return false;
    }

    boolean bodyInterface() {
        if (inComming.get(i).equals("ID")
                || inComming.get(i).equals("DT")
                || inComming.get(i).equals("interface")
                || inComming.get(i).equals("\\")
                || inComming.get(i).equals("empty")) {


            if (inComming.get(i).equals("ID")
                    || inComming.get(i).equals("DT")
                    || inComming.get(i).equals("interface")
                    || inComming.get(i).equals("empty")) {

                temp = i;
                if (s_stInterface()) {

                    if (m_stInterface()) {

//                        System.out.println("success bodyInterface");
                        return true;

                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else {
//                System.out.println("success bodyInterface");
                return true;
            }

        } else {
            errorIndex = i;
        }

        return false;
    }

    boolean iD1Interface() {
        if (inComming.get(i).equals(".") || inComming.get(i).equals(",")
                || inComming.get(i).equals("/")) {


            if (inComming.get(i).equals(".")) {

                i++;

                //for null pointer exception end nh hskta ispy to false
                if (i >= inComming.size()) {
//                    System.out.println("hello");
                    return false;
                }

                if (inComming.get(i).equals("ID")) {

                    i++;

                    //for last element
                    if (i >= inComming.size()) {
//                        System.out.println("Hello");
                        return true;
                    }
                    if (iD1Interface()) {

//                        System.out.println("success iD1Interface");
                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else {

                return true;
            }

        } else {
            errorIndex = i;
        }

        return false;
    }

    boolean s_stInterface() {
        if (inComming.get(temp).equals("ID")
                || inComming.get(temp).equals("DT")
                || inComming.get(temp).equals("interface")
                || inComming.get(temp).equals("empty")) {


            if (inComming.get(i).equals("interface")) {

                if (Interface()) {
                    i++;
                    if (i >= inComming.size()) {
                        return false;
                    }
//                    System.out.println("s_stInterfae success");
                    return true;
                } else {
                    errorIndex = i;
                }
            } else if (inComming.get(i).equals("DT")
                    || inComming.get(i).equals("ID")) {

                temp++;

                if (s_stForInterface()) {

//                    System.out.println("s_stInterface success");
                    return true;
                } else {
                    errorIndex = i;
                }
            } else {
                errorIndex = i;
            }
            if (inComming.get(i).equals("empty")) {

                if (functionDeclInterface()) {
//                    System.out.println(inComming.get(i));
//                    System.out.println("Sending for interface function declration");
//                    System.out.println("s_stInterfae success");
                    return true;
                } else {
                    errorIndex = i;
                }
            } else {
                errorIndex = i;
            }

        } else {
            temp = i;
        }
        return false;
    }

    boolean m_stInterface() {
        if (inComming.get(i).equals("ID")
                || inComming.get(i).equals("DT")
                || inComming.get(i).equals("interface")
                || inComming.get(i).equals("empty")
                || inComming.get(i).equals("\\")) {


            if (inComming.get(i).equals("ID")
                    || inComming.get(i).equals("DT")
                    || inComming.get(i).equals("interface")
                    || inComming.get(i).equals("empty")) {

                temp = i;
                if (s_stInterface()) {

                    if (m_stInterface()) {

//                        System.out.println("m_stInterface success");
                        return true;
                    } else {
                        errorIndex = i;
                    }

                } else {
                    errorIndex = i;
                }

            } else {
//                System.out.println("m_stInterface success");
                return true;
            }
        } else {
            errorIndex = i;
        }
        return false;
    }

    boolean s_stForInterface() {
        if (inComming.get(temp).equals("ID")) {
            temp++;

            if (decision()) {
//                System.out.println("Success decision");
                return true;
            } else {
                errorIndex = i;
            }

        } else {
            errorIndex = i;
        }
        return false;
    }

    boolean functionDeclInterface() {

        
        if (inComming.get(i).equals("DT")
                || inComming.get(i).equals("ID")
                || inComming.get(i).equals("empty")) {

            returnType=inCommingVp.get(i);
            scope++;
            scopes.push(scope);
            
            //checking if  return type class found or not
            //will return true if class not found
            if(cls.lookUp(returnType, scopes, Boolean.TRUE)&&
                    inComming.get(i).equals("ID")){
                
                System.out.println("Semantic Error: Class "+returnType+" used in return type not found @ line no "+inCommingLine.get(i));
                return false;
            }
            
            System.out.println("Scope contains "+scopes);

            i++;
            //null pointer 
            if (i >= inComming.size()) {
                return false;
            }
            if (inComming.get(i).equals("ID")) {
                name=inCommingVp.get(i);
                i++;
                //null pointer 
                if (i >= inComming.size()) {
                    return false;
                }
                if (inComming.get(i).equals("(")) {
                    i++;
                    //null pointer 
                    if (i >= inComming.size()) {
                        return false;
                    }
                    parameters=new ArrayList<Variables>();
                    
                    if (para_decl()) {

                        
                        if(fun.lookUp(name, (int)scopes.get(0), parameters)==null){
                            
                            fun.insert(returnType, name, (int) scopes.get(0), /*Static*/null, /*Final*/null, parameters, /*AM*/ null);
                            System.out.println("Function Insertion succesfull");
                            
                        }
                        else{
                            System.out.println("Semantic Error: Redeclaration  Function  method "+name+" already found @ line no "+inCommingLine.get(i));
                        return false;
                        }
                        if (inComming.get(i).equals(")")) {
                            i++;
                            //null pointer 
                            if (i >= inComming.size()) {
                                return false;
                            }
                            name=returnType=null;
                            parameters=null;
                            arrayCounter=0;

                            if (inComming.get(i).equals("]")) {
                                i++;
                                  scopes.pop();
                                System.out.println("Scope contains "+scopes);
                                //null pointer 
                                if (i >= inComming.size()) {
                                    return false;
                                }
//                                System.out.println("Interface Function decclrataion success" + inComming.get(i));
                                return true;
                            } else {
                                errorIndex = i;
                            }
                        } else {
                            errorIndex = i;
                        }

                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else {
                errorIndex = i;
            }

        } else {
            errorIndex = i;
        }
        return false;
    }

    boolean variableDeclarationInterface() {
        if (inComming.get(i).equals("AM") || inComming.get(i).equals("stable") || inComming.get(i).equals("absolute") || inComming.get(i).equals("DT")
                || inComming.get(i).equals("ID")) {

            looptemp = i;
            if (AM1_Decl()) {
                if (STABLE_Decl()) {  //decl
                    if (FINAL_Decl()) {
//                        System.out.println("hello");
                        if (DT_loop()) {
//                            System.out.println("hello");
                            if (assign1VariableInterface()) {

                                if (list_decl()) {
                                    if (inComming.get(i).equals("]")) {
                                        //  i++;
                                        return true;

                                    }
                                }
                            }
                        }

                    }
                }
            }
        }

        return false;
    }

    boolean decision() {
        if (inComming.get(temp).equals("(")
                || inComming.get(temp).equals("=")
                || inComming.get(temp).equals(":")) {
            if (inComming.get(temp).equals("(")) {

                if (functionDeclInterface()) {
//                    i++;
//                    if(i>=inComming.size()){
//                        return true;
//                    }
//                    System.out.println("sending for interface function declaartion");
//                    System.out.println("Success decision");
                    return true;
                } else {
                    errorIndex = i;
                }
            } else if (inComming.get(temp).equals("=") || inComming.get(temp).equals(":")) {

                if (variableDeclarationInterface()) {
                    i++;
                    if (i >= inComming.size()) {
                        return true;
                    }
//                    System.out.println("sending for interface  declaration");
//                    System.out.println("Success decision");
                    return true;
                } else {
                    errorIndex = i;
                }
            } else {
                errorIndex = i;
            }
        } else {
            errorIndex = i;
        }
        return false;
    }
    //// This code is copied from declaration with one follow chnge ] nh he array ke follow me bcx na a:; is not alowed in interface

    boolean arr_VariableInterface() {
        if (inComming.get(i).equals(":") || inComming.get(i).equals("=") || inComming.get(i).equals(",")) {

            if (inComming.get(i).equals(":")) {
              
                i++;
                arrayCounter++;
                semiColon.push(":");
                if (a()!=null) {
                    if (inComming.get(i).equals(";")) {
                        i++;
                        Roshaan++;
                        if (arr_VariableInterface()) {
                            return true;
                        }
                    }
                }
            } else if (inComming.get(i).equals("=") || inComming.get(i).equals(",")) {
                return true;
            }

        } else if (inComming.get(i).equals(")")) {

            return true;
        }
        return false;
    }

    boolean assign1VariableInterface() {
        Roshaan = 0;
        if (inComming.get(i).equals("ID")) {

            if (inComming.get(i).equals("ID")) {
                name=inCommingVp.get(i);
                type=inCommingVp.get(i-1);
                
                if(cls.lookUp(type, scopes, Boolean.TRUE)&&
                        inComming.get(i-1).equals("ID")){
                    System.out.println("Semantic Error: Variable declaration class "+type+" not found @ line no "+inCommingLine.get(i));
                    return false;
                }
                i++;
                //  if(ID1_decl()){
                if (arr_VariableInterface()) {
                    
                    if(var.lookUp(name, (int) scopes.get(scopes.size()-1))==null){
                        
                        var.insert(type, name, (int) scopes.get(scopes.size()-1),"absolute", /*Static*/null,/*AM*/null, arrayCounter,null);
                        System.out.println("INterface variable insertion successfull");
                    }
                    else{
                        System.out.println("Semantic Error: Redeclaration Interface variable error "+name+" already found @ line no "+inCommingLine.get(i));
                        return false;
                    }
                    arrayCounter=0;
                    
                    if (assign11()) {
//                        System.out.println("assign 1 success");
                        return true;

                        //  }
                    }
                }
            }
        }
        return false;
    }

    //Salman ka code for parameter declaration this code is also used in Constructor and Function declration in classes
    boolean para_decl() {
        if (inComming.get(i).equals("DT") || inComming.get(i).equals("ID")) {

            typePara = inCommingVp.get(i);
            //will return true if classss not found
            if(cls.lookUp(typePara, scopes, Boolean.TRUE)&&
                    inComming.get(i).equals("ID")){
                System.out.println("Semantic Error: Variable Declaration data type "+typePara+" not found @ line no "+inCommingLine.get(i));
                
                return false;
            }

            if (DT3()) {
                if (inComming.get(i).equals("ID")) {

                    namePara = inCommingVp.get(i);
                    i++;
                    if (i >= inComming.size()) {
//                        System.out.println("para_deccInterface failed");
                        return false;
                    }

                    if (arr()) {

                        if (var.lookUp(namePara, scope)==null) {

                            var.insert(typePara, namePara, scope, null, null, null, arrayCounter,null);
                            parameters.add(var.varTable.get(var.varTable.size() - 1));
                            System.out.println("insertion successfull..........................................." + inCommingVp.get(i));
                        } else {
                            System.out.println("Semantic Error: Rdeclaration error" + inCommingVp.get(i)+" already found @ line no "+inCommingLine.get(i));
                            return false;
                        }
                        typePara = namePara = null;
                        arrayCounter = 0;

                        if (oparaInterfaceFuncDecl()) {
//                            System.out.println("para_deccInterface succcess");
                            return true;
                        } else {
                            errorIndex = i;
//                            System.out.println("para_decclInterface failed");
                        }
                    } else {
                        errorIndex = i;
//                        System.out.println("para_decclInterface failed");
                    }
                } else {
                    errorIndex = i;
//                    System.out.println("para_decclInterface failed");
                }
            } else {
                errorIndex = i;
//                System.out.println("para_decclInterface failed");
            }
        } else if (inComming.get(i).equals(")")) {

            return true;
        }
        return false;
    }

    boolean DT3() {
        if (inComming.get(i).equals("DT") || inComming.get(i).equals("ID")) {

            if (inComming.get(i).equals("DT") || inComming.get(i).equals("ID")) {
                i++;
                if (i >= inComming.size()) {
                    return false;
                }
//                System.out.println("DT3 success");
                return true;
            } else {
                errorIndex = i;
            }

        } else {
            errorIndex = i;
        }
        return false;
    }

    boolean arr() {
        if (inComming.get(i).equals(":") || inComming.get(i).equals(",")) {

            if (inComming.get(i).equals(":")) {
                arrayCounter++;
                i++;
                if (i >= inComming.size()) {
                    return false;
                }
                if (inComming.get(i).equals(";")) {
                    i++;

                    if (arr()) {
//                        System.out.println("arr success");
                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else if (inComming.get(i).equals(",")) {
//                System.out.println("arrInterfaceDecl " + inComming.get(i));
//                System.out.println("arr success");
                return true;
            } else {
                errorIndex = i;
            }

        } else if (inComming.get(i).equals(")")) {

            return true;
        } else {
            errorIndex = i;
        }
        return false;
    }

    boolean oparaInterfaceFuncDecl() {
        if (inComming.get(i).equals(",")) {

            if (inComming.get(i).equals(",")) {
                i++;
                if (i >= inComming.size()) {
                    return false;
                }
                typePara = inCommingVp.get(i);
                ///will return true if class not found
                if(cls.lookUp(typePara, scopes, Boolean.TRUE)
                        &&inComming.get(i).equals("ID")){
                    System.out.println("Semantic Error Variable declaration data type "+typePara+" not found @ line no "+inCommingLine.get(i));
                    return false;
                }
                if (DT3()) {

                    if (inComming.get(i).equals("ID")) {
                        namePara = inCommingVp.get(i);

                        i++;
//                        System.out.println(inComming.get(i));
                        if (i >= inComming.size()) {
                            return false;
                        }
                        if (arr()) {

                            if (var.lookUp(namePara, scope)==null) {

                                var.insert(typePara, namePara, scope, null, null, null, arrayCounter,null);
                                System.out.println(var.varTable.size());
                                parameters.add(var.varTable.get(var.varTable.size() - 1));
                                System.out.println("insertion successfull..........................................." + inCommingVp.get(i));
                            } else {
                                System.out.println("Semantic Error: Redeclaration error " + inCommingVp.get(i)+" already found @ line no "+inCommingLine.get(i));
                                return false;
                            }
                            typePara = namePara = null;
                            arrayCounter = 0;

                            if (oparaInterfaceFuncDecl()) {
//                                System.out.println("oparaIntDecl success");
                                return true;

                            } else {
                                errorIndex = i;
                            }
                        } else {
                            errorIndex = i;
                        }
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else {
                errorIndex = i;
            }
        } else if (inComming.get(i).equals(")")) {

            return true;
        }
        return false;
    }
    //Salman ka code for para decl is ending here
    ///yahan interface function declaration ends horh he

    /////////// PAKAGE /////////////////
    boolean Pakage() {

        if (inComming.get(temp).equals("pakage")) {

            i++;
            //null pointer
            if (i >= inComming.size()) {
//                System.out.println("Pakage failed");
                return false;
            }

            if (inComming.get(i).equals("ID")) {

                i++;
                //null pointer
                if (i >= inComming.size()) {
//                    System.out.println("Pakage failed");
                    return false;
                }
                if (iD1Pakage()) {

                    //null pointer
                    if (i >= inComming.size()) {

//                        System.out.println("Pakage success");
                        return true;

                    }
                    if (inComming.get(i).equals("]")) {

                        i++;
                        //null pointer
                        if (i >= inComming.size()) {
//                            System.out.println("Pakage success");

                            return true;

                        }
                        if (Import()) {
                            System.out.println(inComming.get(i));
                            //null pointer

                            if (i >= inComming.size()) {
//                                System.out.println("Pakage success");

                                return true;

                            }
                            if (bodyPakage()) {

//                                System.out.println("Success Pakage");
                                return true;
                            } else {
                                failedCfgs.add("Pakage");
                                errorIndex = i;
//                                System.out.println("Pakage Failed");
                            }
                        } else {
                            failedCfgs.add("Pakage");
                            errorIndex = i;
//                            System.out.println("Pakage Failed");
                        }
                    } else {
                        failedCfgs.add("Pakage");
                        errorIndex = i;
//                        System.out.println("Pakage Failed");
                    }
                } else {
                    failedCfgs.add("Pakage");
                    errorIndex = i;
//                    System.out.println("Pakage Failed");
                }

            } else {
                failedCfgs.add("Pakage");
                errorIndex = i;
//                System.out.println("Pakage Failed");
            }
        } else {
            failedCfgs.add("Pakage");
            errorIndex = i;

        }
        return false;
    }

    boolean iD1Pakage() {

        if (inComming.get(i).equals(".")
                || inComming.get(i).equals("]")) {


            if (inComming.get(i).equals(".")) {

                i++;

                //for null pointer exception end nh hskta ispy to false
                if (i >= inComming.size()) {
//                    System.out.println("hello");
                    return false;
                }

                if (inComming.get(i).equals("ID")) {

                    i++;

                    //for last element
                    if (i >= inComming.size()) {
//                        System.out.println("Helloo");
                        return false;
                    }
                    if (iD1Pakage()) {

//                        System.out.println("success iD1Pakage");
                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else {
//                System.out.println("success iD1Pakage");
                return true;
            }

        } else {
            errorIndex = i;
        }

        return false;
    }

    boolean Import() {

        if (inComming.get(i).equals("import")
                || inComming.get(i).equals("absolute")
                || inComming.get(i).equals("novitachocolate")
                || inComming.get(i).equals("AM")) {


            if (inComming.get(i).equals("import")) {

                i++;
                //null pointer
                if (i >= inComming.size()) {
//                    System.out.println("Import failed");
                    return false;
                }

                if (stablePakage()) {

                    if (inComming.get(i).equals("novita")) {

                        i++;
                        //null pointer
                        if (i >= inComming.size()) {
//                            System.out.println("Import failed");
                            return false;
                        }

                        if (iD1Pakage()) {

                            //null pointer
                            if (i >= inComming.size()) {
//                                System.out.println("Pakage success");

                                return false;

                            }

                            if (inComming.get(i).equals("]")) {
                                i++;
                                //null pointer
                                if (i >= inComming.size()) {
                                    System.out.println("Pakage success");

                                    return true;

                                }
                                if (Import()) {

//                                    System.out.println("Success Import");
                                    return true;
                                } else {
                                    errorIndex = i;
                                }

                            } else {
                                errorIndex = i;
                            }

                        } else {
                            errorIndex = i;
                        }

                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else {
//                System.out.println("Import success");
                return true;
            }
        }

        return false;
    }

    boolean bodyPakage() {
        if (inComming.get(i).equals("absolute")
                || inComming.get(i).equals("novitachocolate")
                || inComming.get(i).equals("AM")) {

            if (mainClass()) {

                i++;
                if (i >= inComming.size()) {
//                    System.out.println("sucess bodyPakage");
                    return true;
                }
                if (CI()) {
//                    System.out.println("bodyPakage success");
                    return true;
                } else {
                    errorIndex = i;
                }
            } else {
                errorIndex = i;
            }
        } else {
            errorIndex = i;
        }
        return false;
    }

    boolean stablePakage() {

        if (inComming.get(i).equals("stable")
                || inComming.get(i).equals("novita")) {

            if (inComming.get(i).equals("stable")) {
                i++;
                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }
//                System.out.println("Stable succcess");
                return true;
            } else {
//                System.out.println("stable success");
                return true;
            }
        } else {
            errorIndex = i;
        }
        return false;
    }

    boolean CI() {
        if (inComming.get(i).equals("AM")
                || inComming.get(i).equals("stable")
                || inComming.get(i).equals("absolute")
                || inComming.get(i).equals("class")
                || inComming.get(i).equals("interface")
                || inComming.get(i).equals("$")) {


            if (inComming.get(i).equals("$")) {
//                System.out.println("CI success");
                return true;
            } else if (s_stPakage()) {
                if (i >= inComming.size()) {
                    return true;
                }
//                i++;
//                if (i >= inComming.size()) {
//                    System.out.println("sucess CI");
//                    return true;
//                }
                if (m_stPakage()) {

//                    System.out.println("Success CI");
                    return true;
                } else {
                    errorIndex = i;
                }
            } else {
                errorIndex = i;
            }
        } else {
            errorIndex = i;
        }
        return false;
    }

    boolean s_stPakage() {
        if (inComming.get(i).equals("AM")
                || inComming.get(i).equals("stable")
                || inComming.get(i).equals("absolute")
                || inComming.get(i).equals("class")
                || inComming.get(i).equals("interface")) {


            if (inComming.get(i).equals("AM")
                    || inComming.get(i).equals("stable")
                    || inComming.get(i).equals("absolute")
                    || inComming.get(i).equals("class")) {

                if (Class()) {

                    i++;
                    if (i >= inComming.size()) {
//                        System.out.println("sucess bodyPakage");
                        return true;
                    }
//                    System.out.println("s_stPakage success" + inComming.get(i));
                    return true;
                } else {
                    errorIndex = i;
                }
            } else if (Interface()) {
                i++;
                if (i >= inComming.size()) {
//                    System.out.println("sucess bodyPakage");
                    return true;
                }
//                System.out.println("s_stPakage success");
                return true;
            } else {
                errorIndex = i;
            }

        } else {
            errorIndex = i;
        }
        return false;
    }

    boolean m_stPakage() {
        if (inComming.get(i).equals("AM")
                || inComming.get(i).equals("stable")
                || inComming.get(i).equals("absolute")
                || inComming.get(i).equals("class")
                || inComming.get(i).equals("interface")
                || inComming.get(i).equals("$")) {

            if (inComming.get(i).equals("$")) {
//                System.out.println("m_stPakage success");
                return true;
            } else if (s_stPakage()) {

                // i++;
                if (i >= inComming.size()) {
//                    System.out.println("sucess m_stPakage");
                    return true;
                }

                if (m_stPakage()) {

//                    System.out.println("Success m_stPakage");
                    return true;
                } else {
                    errorIndex = i;
                }
            } else {
                errorIndex = i;
            }
        } else {
            errorIndex = i;
        }
        return false;
    }

    // Salman ka code for Declaration  code 
    boolean xDecl() { 
        if (inComming.get(i).equals("AM") || inComming.get(i).equals("stable") || inComming.get(i).equals("absolute") || inComming.get(i).equals("DT")
                || inComming.get(i).equals("ID")) {

            looptemp = i;
            if (AM1_Decl()) {
//                System.out.println("hello1");
                if (STABLE_Decl()) {
//                    System.out.println("Hello2"); //decl
                    if (FINAL_Decl()) {
//                        System.out.println("hello");
                        if (DT_loop()) {
                            System.out.println("helloo");
                            if (assign1()) {

                                if (/*list_decl()*/1 == 1) {
                                    if (inComming.get(i).equals("]")) {
                                        //    i++;
                                        return true;

                                    }
                                }
                            }
                        } else 
                         if (assign()) {
                                // i++;
                                return true;
                            }
                    }
                }
            }
        } else if (inComming.get(i).equals("]")) {
            i++;
            return true;
        }

        return false;
    }

    boolean INC_DEC_CALL() {
        looptemp = i;
        if (inComming.get(i).equals("Inc_Dec")) {
            i++;
            if (inComming.get(i).equals("ID")) {
                i++;
                if (ID1_indec_call()) {
                    if (ARR_indec_call()) {

                        if (inComming.get(i).equals("]")) {
                            i++;
                            return true;
                        } else {
                            i = looptemp;
                        }
                    } else {
                        i = looptemp;
                    }
                } else {
                    i = looptemp;
                }
            } else {
                i = looptemp;
            }
        } else if (inComming.get(i).equals("ID")) {
            i++;
            if (ID1_indec_call()) {
                if (ARR_indec_call()) {
                    if (inComming.get(i).equals("(")) {
                        bracket.push("(");

                        i++;
                        if (inComming.get(i).equals(")")) {
                            bracket.pop();
                        }
                        if (parameters()) {
//                            System.out.println(""+inComming.get(i));
                            if (inComming.get(i).equals("]")) {

                                i++;
                                return true;

                            } else {
                                i = looptemp;
                            }
                        } else {
                            i = looptemp;
                        }
                    } else if (inComming.get(i).equals("Inc_Dec")) {
                        i++;
                        if (inComming.get(i).equals("]")) {
                            i++;
                            return true;
                        } else {
                            i = looptemp;
                        }
                    }
                } else {
                    i = looptemp;
                }
            } else {
                i = looptemp;
            }

        }
        return false;
    }

    boolean AM1_Decl() {
        if (inComming.get(i).equals("AM") || inComming.get(i).equals("stable") || inComming.get(i).equals("absolute")
                || inComming.get(i).equals("DT") || inComming.get(i).equals("ID")) {
            if (inComming.get(i).equals("AM")) {
                AM = inCommingVp.get(i);
                i++;

                return true;
            } else if (inComming.get(i).equals("stable") || inComming.get(i).equals("absolute")
                    || inComming.get(i).equals("DT") || inComming.get(i).equals("ID")) {
                return true;
            }
        }
        return false;
    }

    boolean STABLE_Decl() {
        if (inComming.get(i).equals("stable") || inComming.get(i).equals("absolute")
                || inComming.get(i).equals("DT") || inComming.get(i).equals("ID")) {
            if (inComming.get(i).equals("stable")) {
                Static = "stable";
                i++;

                return true;
            } else if (inComming.get(i).equals("absolute")
                    || inComming.get(i).equals("DT") || inComming.get(i).equals("ID")) {
                return true;
            }
        }
        return false;
    }

    boolean FINAL_Decl() {
        if (inComming.get(i).equals("absolute")
                || inComming.get(i).equals("DT") || inComming.get(i).equals("ID")) {
            if (inComming.get(i).equals("absolute")) {
                i++;
                Final = "absolute";
                // flag = 1;
                return true;
            } else if (inComming.get(i).equals("DT") || inComming.get(i).equals("ID")) {
                return true;
            }
        }
        return false;
    }

    boolean DT_loop() {
        if (inComming.get(i).equals("DT") || inComming.get(i).equals("ID") || inComming.get(i).equals("Inc_Dec")) {

            if (inComming.get(i).equals("DT")) {

                i++;
                return true;
            } else if (inComming.get(i).equals("ID")) {
                i++; 
                if (inComming.get(i).equals("ID")) {
//                    System.out.println(inComming.get(i));

                    return true;
                } else {
//                    System.out.println("babababa");
                    i = looptemp;

                    return false;
                }

            } else if (inComming.get(i).equals("Inc_Dec")) {
                return false;
            }

        }
        return false;
    }

    boolean assign1() {
        Roshaan = 0;
        if (inComming.get(i).equals("ID")) {

            if (inComming.get(i).equals("ID")) {

                name = inCommingVp.get(i);
                type = inCommingVp.get(i-1);
                //function will return true if no matching class found
               
                if(cls.lookUp(type, scopes, true)&&
                        inComming.get(i-1).equals("ID")){
                    System.out.println("Semantic Error: Variable declaration data type "+type+" not found @ line no "+inCommingLine.get(i)); 
                    return false;
                }
//                if(var.lookUp(inCommingVp.get(i).toString(),2)){
//                
//                    var.insert(inCommingVp.get(i-1) , inCommingVp.get(i), 2, Final,Static, AM, arrayCounter);
//                    System.out.println("insertion successfull..........................................."+inCommingVp.get(i));
//                }
//                else{
//                    System.out.println("REdeclaration error"+inCommingVp.get(i));
//                  return false;
//                } 
//                AM=Static=Final=null;  arrayCounter=0;
                //
                i++;
                //  if(ID1_decl()){
                if (arr_decl()) {

                    if (var.lookUp(name, (int) scopes.get(scopes.size()-1))==null) {

                        var.insert(type, name, (int) scopes.get(scopes.size()-1), Final, AM, Static, arrayCounter,null);

                        System.out.println("insertion successfull..........................................." + inCommingVp.get(i));
                    } else {
                        System.out.println("Semantic Error: Redeclaration error" + inCommingVp.get(i)+" already found @ line no "+inCommingLine.get(i));
                        return false;
                    }

                    AM = Static = Final = null;
                    arrayCounter = 0;

                    if (assign11()) {
//                        System.out.println("assign 1 success");
                        return true;

                        //  }
                    }
                }
            }
        }
        return false;
    }

    boolean assign11() {
        if (inComming.get(i).equals("=") || inComming.get(i).equals(",") || inComming.get(i).equals("]")) {

            if (inComming.get(i).equals("=")) {
                i++;
                if (assign111()) {
//                    System.out.println("assign 11 success");
                    return true;
                }
            } else if (inComming.get(i).equals(",") || inComming.get(i).equals("]")) {
                return true;
            }
        }
        return false;
    }

    boolean assign111() {

        if (inComming.get(i).equals("/") || inComming.get(i).equals("new") || inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals("ID") || inComming.get(i).equals("(") || inComming.get(i).equals("ninta") || inComming.get(i).equals("nfloata")
                || inComming.get(i).equals("nchara") || inComming.get(i).equals("nstringa ") || inComming.get(i).equals("boolean_constant")) {

            if (inComming.get(i).equals("Inc_Dec") || inComming.get(i).equals("new")
                    || inComming.get(i).equals("ID") || inComming.get(i).equals("(") || inComming.get(i).equals("ninta") || inComming.get(i).equals("nfloata")
                    || inComming.get(i).equals("nchara") || inComming.get(i).equals("nstringa ") || inComming.get(i).equals("boolean_constant")) {
                //oexp srf tb call krega jb  = sy pehly delimeter na ho
                //Roshaan 
//                  System.out.println(Roshaan);
                if (Roshaan == 0 && oexp_decl()) {

                    return true;
                }
            } else if (inComming.get(i).equals("/")) {
                i++;
                slash.push("/");
                commaDecl.push((","));

                if (oexp_decl()) {
                    if (aexp_decl()) {
                        if (inComming.get(i).equals("\\")) {
                            i++;

                            return true;
                        }
                    }
                }
            }
        }
        return false;

    }

    boolean aexp_decl() {
        if (inComming.get(i).equals(",") || inComming.get(i).equals("\\")) {
//            System.out.println("aexp_decl " + inComming.get(i));
            if (inComming.get(i).equals(",")) {
                commaDecl.push((","));
                i++;
                if (oexp_decl()) {

                    if (aexp_decl()) {
                        return true;

                    }
                }
            } else if (inComming.get(i).equals("\\")) {
                return true;
            }
        }
        return false;
    }

    boolean oexp_decl() {
        Roshaan = 0; String tt;
        if (inComming.get(i).equals("new") || inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals("ID") || inComming.get(i).equals("(") || inComming.get(i).equals("ninta") || inComming.get(i).equals("nfloata")
                || inComming.get(i).equals("nchara") || inComming.get(i).equals("nstringa ") || inComming.get(i).equals("boolean_constant")) {

//            System.out.println("oexp_decl " + inComming.get(i));
            if (inComming.get(i).equals("Inc_Dec")
                    || inComming.get(i).equals("ID") || inComming.get(i).equals("(") || inComming.get(i).equals("ninta") || inComming.get(i).equals("nfloata")
                    || inComming.get(i).equals("nchara") || inComming.get(i).equals("nstringa ") || inComming.get(i).equals("boolean_constant")) {
                squareBracket.push("]");
                
                tt=a();
                if (tt!=null) {

                       String assignType=var.compatibility("=", type, tt, scopes, 0);
                    
                    if(assignType!=null){
                        
                        System.out.println("Declaration type is "+assignType);
                        assignType=null;
                    }else{
                        System.out.println("Semantic Error: type mismatch @ line no "+inCommingLine.get(i));
                        return false;
                    }
                    
                    return true;
                }
            } else if (inComming.get(i).equals("new")) {
                i++;
                if (inComming.get(i).equals("ID")) {
                    //adding the object in varible
                    
                    //yahan p akay ab apni logic restore krlo
                    //agr constructor call valid hogi 
                    //or varaible jo declarre horha he uski class 
                    //ko extends kr rhi hogi ya wohi hogi right wali new k baad
                    //ki id thn upar var table wali insertion krunga
                    //naya compatibility ka function banega jo ye saray kaam krega
                    
                    //first checking if both sides have same class name
                      String assignType=var.compatibility("=", type, inCommingVp.get(i), scopes, 0);
                  if(assignType!=null){
                      
                        var.varTable.get(var.varTable.size()-1).object=inCommingVp.get(i);
                        System.out.println("Declaration successfull "+assignType);
                  }    
                    //checking if class exists and also compatibilty of extends with left class
                   
                  else{
                       assignType=var.objectCompatibility(inCommingVp.get(i), scopes, name);
                  
                    if(assignType==null){
                        System.out.println("Semantic Error: Type missmatch declaration @ line no "+inCommingLine.get(i));
                        return false;
                    }
                    //push the value ofobjecct
                    else{
                        var.varTable.get(var.varTable.size()-1).object=inCommingVp.get(i);
                        System.out.println("Declaration successfull "+assignType);
                        
                    }
                  }  i++;
                    if (inComming.get(i).equals("(")) {
                        i++;
                        squareBracket.push("]");
                        bracket.push("(");
                        if (inComming.get(i).equals(")")) {
                            squareBracket.pop();
                            bracket.pop();
                        }
                        if (para_Decl()) {
                            if (inComming.get(i).equals(")")) {
                                i++;
                                if (i >= inComming.size()) {
                                    return false;
                                }
//                                System.out.println("oexp_decl success");
                                return true;
                            }

                        }
                    }
                }
            }
        }

        return false;
    }

    boolean list_decl() {
        if (inComming.get(i).equals(",") || inComming.get(i).equals("]")) {
//            System.out.println("list_decl " + inComming.get(i));
            if (inComming.get(i).equals(",")) {
                i++;
                if (assign1()) {
                    if (list_decl()) {
                        return true;
                    }
                }
            } else if (inComming.get(i).equals("]")) {
                return true;
            }
        }
        return false;
    }

    boolean para_Decl() {
        if (inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals("ID") || inComming.get(i).equals("(") || inComming.get(i).equals("ninta") || inComming.get(i).equals("nfloata")
                || inComming.get(i).equals("nchara") || inComming.get(i).equals("nstringa ") || inComming.get(i).equals("boolean_constant")
                || inComming.get(i).equals(")")) {

//            System.out.println("para_decl " + inComming.get(i));
            if (inComming.get(i).equals("Inc_Dec")
                    || inComming.get(i).equals("ID") || inComming.get(i).equals("(") || inComming.get(i).equals("ninta") || inComming.get(i).equals("nfloata")
                    || inComming.get(i).equals("nchara") || inComming.get(i).equals("nstringa ") || inComming.get(i).equals("boolean_constant")) {
                if (a()!=null) {
                    if (opera_decl()) {
                        return true;
                    }
                }
            } else if (inComming.get(i).equals(")")) {

                return true;
            }
        }

        return false;
    }

    boolean arr_decl() {
        if (inComming.get(i).equals(":") || inComming.get(i).equals("=") || inComming.get(i).equals(",") || inComming.get(i).equals("]")) {
//            System.out.println("arr_decl " + inComming.get(i));
            if (inComming.get(i).equals(":")) {
                i++;
                arrayCounter++;
                semiColon.push(":");
                if (a()!=null) {
                    if (inComming.get(i).equals(";")) {
                        i++;
                        // int Roshaan=0;
                        //Roshaan
                        Roshaan++;
                        if (arr_decl()) {
                            return true;
                        }
                    }
                }
            } else if (inComming.get(i).equals("=") || inComming.get(i).equals(",") || inComming.get(i).equals("]")) {
                return true;
            }

        } else if (inComming.get(i).equals(")")) {

            return true;
        }
        return false;
    }

    boolean opera_decl() {
        if (inComming.get(i).equals(",") || inComming.get(i).equals(")")) {
//            System.out.println("opera_decl " + inComming.get(i));
            if (inComming.get(i).equals(",")) {
                i++;
                return true;
            } else if (inComming.get(i).equals(")")) {

                return true;
            }
        }
        return false;
    }

    //Salman ka code for declaration ending here
//Salman ka code for  forLoop
    boolean xLoop() {
        if (inComming.get(i).equals("DT")
                || inComming.get(i).equals("ID")) {
//            System.out.println("Declaaration x " + inComming.get(i));
            looptemp = i;
            if (AM1_Decl()) {
                if (STABLE_Decl()) {  //decl
                    if (FINAL_Decl()) {
//                        System.out.println("hello");
                        if (DT_loop()) {
//                            System.out.println("hello");
                            if (assign1()) {

                                if (list_decl()) {
                                    if (inComming.get(i).equals("]")) {
                                        i++;
                                        if (i >= inComming.size()) {
                                            return false;
                                        }
                                        return true;

                                    }
                                }
                            }
                        } else if (assign()) {
                            i++;
                            //null pointer
                            if (i >= inComming.size()) {
                                return false;
                            }
//                            System.out.println("success DT loop");
                            return true;
                        }

                    }
                }
            }
        } else if (inComming.get(i).equals("]")) {
            i++;
            return true;
        }

        return false;
    }

    boolean loopfor() {

        if (inComming.get(i).equals("loopfor")) {
//            System.out.println("loopFor " + inComming.get(i));
            scope++;
            scopes.push(scope);
            i++;
            if (inComming.get(i).equals("(")) {
                i++;
                if (xLoop()) {
//                    System.out.println("HEllo");
                    if (y()) {
//                        System.out.println("HEllo");
                        if (inComming.get(i).equals("]")) {
                            i++;
                            if (z()) {
                                i++;
                                //null pointer
                                if (i >= inComming.size()) {
                                    return false;
                                }
                                if (inComming.get(i).equals(")")) {
                                    i++;
                                    if (inComming.get(i).equals("/")) {
                                        i++;
                                        if (body()) {
                                            if (inComming.get(i).equals("\\")) {
                                                i++;
                                                scopes.pop();
                                                
//                                                System.out.println("loopFor success");
                                                return true;
                                            } else {
                                                failedCfgs.add("loopfor ");
//                                                System.out.println("loopfor failed");
                                                errorIndex = i;
                                            }
                                        } else {
                                            failedCfgs.add("loopfor ");
//                                            System.out.println("loopfor failed");
                                            errorIndex = i;
                                        }
                                    } else {
                                        failedCfgs.add("loopfor ");
//                                        System.out.println("loopfor failed");
                                        errorIndex = i;
                                    }
                                } else {
                                    failedCfgs.add("loopfor ");
//                                    System.out.println("loopfor failed");
                                    errorIndex = i;
                                }
                            } else {
                                failedCfgs.add("loopfor ");
//                                System.out.println("loopfor failed");
                                errorIndex = i;
                            }
                        } else {
                            failedCfgs.add("loopfor ");
//                            System.out.println("loopfor failed");
                            errorIndex = i;
                        }
                    } else {
                        failedCfgs.add("loopfor ");
//                        System.out.println("loopfor failed");
                        errorIndex = i;
                    }
                } else {
                    failedCfgs.add("loopfor ");
//                    System.out.println("loopfor failed");
                    errorIndex = i;
                }
            } else {
                failedCfgs.add("loopfor ");
//                System.out.println("loopfor failed");
                errorIndex = i;
            }
        } else {
            failedCfgs.add("loopfor ");
//            System.out.println("loopfor failed");
            errorIndex = i;
        }
        return false;
    }

    boolean y() {
        if (inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals("ID") || inComming.get(i).equals("(") || inComming.get(i).equals("ninta") || inComming.get(i).equals("nfloata")
                || inComming.get(i).equals("nchara") || inComming.get(i).equals("nstringa ") || inComming.get(i).equals("boolean_constant")) {
//            System.out.println("loopFory " + inComming.get(i));
            squareBracket.push("]");
            if (a()!=null) {

                return true;
            }
        } else if (inComming.get(i).equals("]")) {
//            System.out.println("loopFory " + inComming.get(i));
            return true;
        }
        return false;
    }

    boolean z() {
        if (inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals("ID")) {
//            System.out.println("loopForz " + inComming.get(i));
            if (inComming.get(i).equals("Inc_Dec")) {
                i++;
                if (inComming.get(i).equals("ID")) {
                    i++;
                    if (ID1_z()) {
//                        System.out.println(inComming.get(i));
                        if (ARR_indec_call()) {
                            if (inComming.get(i).equals("]")) {

                                return true;
                            }
                        }
                    }
                }
            } else if (inComming.get(i).equals("ID")) {
//                System.out.println("loopForz " + inComming.get(i));
                newtemp = i;
                i++;
                if (ID1_z()) {
                    if (ARR_indec_call()) {
                        if (inComming.get(i).equals("Inc_Dec")) {

                            i++;
                            if (inComming.get(i).equals("]")) {
//                            System.out.println("loop z success");
                                return true;
                            }

                        } else if (inComming.get(i).equals("AO") || inComming.get(i).equals("=")) {
                            i = newtemp;
                            if (assign()) {
//                            i++;
//                            //null pointer
//                            if (i >= inComming.size()) {
//                                return false;
//                            }
//System.out.println("Success Z");
                                return true;

                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    boolean ID1_z() {
        if (inComming.get(i).equals(".") || inComming.get(i).equals("AO") || inComming.get(i).equals("=") || inComming.get(i).equals(")") || inComming.get(i).equals("Inc_Dec") || inComming.get(i).equals("]") || inComming.get(i).equals(":")) {
            if (inComming.get(i).equals(".")) {
//                System.out.println("ID1Z" + inComming.get(i));
                i++;
                if (inComming.get(i).equals("ID")) {
                    i++;
                    if (ID1_z()) {
                        return true;
                    }
                }
            } else if (inComming.get(i).equals("AO") || inComming.get(i).equals("=")) {
//                System.out.println("ID1Z" + inComming.get(i));
                return true;
            } else if (inComming.get(i).equals("]") || inComming.get(i).equals("Inc_Dec") || inComming.get(i).equals(":")) {
//                System.out.println("ID1Z" + inComming.get(i));
                return true;
            }

        }
        return false;
    }

    //Salman ka for loop ending here
//Salman ka checkspare 
    boolean checkspare() {

        if (inComming.get(i).equals("check")) {
//            System.out.println("checkspare " + inComming.get(i));
            i++;

            scope++;
            scopes.push(scope);
            System.out.println("Scope stack contains "+scopes);
            
            if (inComming.get(i).equals("(")) {
                i++;
                bracket.push("(");

                if (a()!=null) {
                    if (inComming.get(i).equals(")")) {
                        i++;

                        if (inComming.get(i).equals("/")) {
                            i++;

                            if (body()) {
                                if (inComming.get(i).equals("\\")) {
                                    i++;
                                    
                                    scopes.pop();
                                    System.out.println("Scope stack Contains "+scopes);
                                    if (ospare()) {

//                                        System.out.println("checkspare success");
                                        return true;

                                    } else {
                                        failedCfgs.add("checkspare ");
//                                        System.out.println("checkspare failed");
                                        errorIndex = i;

                                    }
                                } else {
                                    failedCfgs.add("checkspare ");
//                                    System.out.println("checkspare failed");
                                    errorIndex = i;

                                }
                            } else {
                                failedCfgs.add("checkspare ");
//                                System.out.println("checkspare failed");
                                errorIndex = i;

                            }
                        } else {
                            failedCfgs.add("checkspare ");
//                            System.out.println("checkspare failed");
                            errorIndex = i;

                        }
                    } else {
                        failedCfgs.add("checkspare ");
//                        System.out.println("checkspare failed");
                        errorIndex = i;

                    }
                } else {
                    failedCfgs.add("checkspare ");
//                    System.out.println("checkspare failed");
                    errorIndex = i;

                }
            } else {
                failedCfgs.add("checkspare ");
//                System.out.println("checkspare failed");
                errorIndex = i;

            }
        } else {
            failedCfgs.add("checkspare ");
//            System.out.println("checkspare failed");

            errorIndex = i;

        }

        return false;
    }

    boolean ospare() {

        if (inComming.get(i).equals("spare")) {
//            System.out.println("spare " + inComming.get(i));
            i++;
            
             scope++;
            scopes.push(scope);
            System.out.println("Scope stack contains "+scopes);
            
            if (inComming.get(i).equals("/")) {
                i++;

                if (body()) {
                    if (inComming.get(i).equals("\\")) {

                        i++;
//                        System.out.println("spare success");
                           scopes.pop();
                           System.out.println("Scope stack contains "+scopes);
                        return true;
                    }
                }
            }
        } else if (inComming.get(i).equals("\\") || inComming.get(i).equals("loopwhile") || inComming.get(i).equals("do")
                || inComming.get(i).equals("loopfor") || inComming.get(i).equals("") || inComming.get(i).equals("ID") || inComming.get(i).equals("general")
                || inComming.get(i).equals("personal") || inComming.get(i).equals("secure") || inComming.get(i).equals("standard") || inComming.get(i).equals("stable")
                || inComming.get(i).equals("absolute") || inComming.get(i).equals("ninta") || inComming.get(i).equals("nfloata")
                || inComming.get(i).equals("Character_constant") || inComming.get(i).equals("String_constant") || inComming.get(i).equals("boolean_constant")
                || inComming.get(i).equals("stop]") || inComming.get(i).equals("resume]") || inComming.get(i).equals("Inc_Dec") || inComming.get(i).equals("(") || inComming.get(i).equals("DT")
                || inComming.get(i).equals("check")) {//change

//            System.out.println("spare " + inComming.get(i));
//            System.out.println("spare success");
            return true;
        } else {
            errorIndex = i;
        }

        return false;
    }

    boolean loopwhile() {

        if (inComming.get(i).equals("loopwhile")) {
            i++;
             scope++;
            scopes.push(scope);
            System.out.println("Scope stack contains "+scopes);
            
//            System.out.println("loopwhile " + inComming.get(i));

            if (inComming.get(i).equals("(")) {
                i++;
                bracket.push("(");
                if (a()!=null) {
                    if (inComming.get(i).equals(")")) {
                        i++;
                        if (inComming.get(i).equals("/")) {
                            i++;
                            if (body()) {
                                if (inComming.get(i).equals("\\")) {
                                    i++;
                                    scopes.pop();
                                    System.out.println("Stack scope contains "+scopes);
//                                    System.out.println("loopwhile success");
                                    return true;
                                } else {
                                    failedCfgs.add("loopwhile ");
//                                    System.out.println("loopwhile failed");
                                    errorIndex = i;
                                }
                            } else {
                                failedCfgs.add("loopwhile ");
//                                System.out.println("loopwhile failed");
                                errorIndex = i;
                            }
                        } else {
                            failedCfgs.add("loopwhile ");
//                            System.out.println("loopwhile failed");
                            errorIndex = i;
                        }
                    } else {
                        failedCfgs.add("loopwhile ");
//                        System.out.println("loopwhile failed");
                        errorIndex = i;
                    }
                } else {
                    failedCfgs.add("loopwhile ");
//                    System.out.println("loopwhile failed");
                    errorIndex = i;
                }
            } else {
                failedCfgs.add("loopwhile ");
                System.out.println("loopwhile failed");
                errorIndex = i;
            }
        } else {
            failedCfgs.add("loopwhile ");
            System.out.println("loopwhile failed");
            // errorIndex = i;
        }
        return false;
    }

// salman code for do while
    boolean loopdowhile() {

        if (inComming.get(i).equals("loopdo")) {
//            System.out.println("loopdowhile " + inComming.get(i));
            scope++;
            scopes.push(scope);
            System.out.println("Scope stack contains "+scopes);
            
            i++;
            if (inComming.get(i).equals("/")) {
                i++;
                if (m_st()) {
                    if (inComming.get(i).equals("\\")) {
                        i++;  
                        scopes.pop();
                        System.out.println("Scope stack contains "+scopes);
                        if (inComming.get(i).equals("while")) {
                            i++;
                            if (inComming.get(i).equals("(")) {
                                bracket.push("(");
                                i++;
                                if (a()!=null) {
                                    if (inComming.get(i).equals(")")) {
                                        i++;
                                        if (inComming.get(i).equals("]")) {
                                            i++;
//                                            System.out.println("loopdowhile success");
                                            return true;
                                        } else {
                                            failedCfgs.add("loopdowhile ");
//                                            System.out.println("loopdowhile failed");
                                            errorIndex = i;
                                        }
                                    } else {
                                        failedCfgs.add("loopdowhile ");
//                                        System.out.println("loopdowhile failed");
                                        errorIndex = i;
                                    }
                                } else {
                                    failedCfgs.add("loopdowhile ");
//                                    System.out.println("loopdowhile failed");
                                    errorIndex = i;
                                }
                            } else {
                                failedCfgs.add("loopdowhile ");
//                                System.out.println("loopdowhile failed");
                                errorIndex = i;
                            }
                        } else {
                            failedCfgs.add("loopdowhile ");
//                            System.out.println("loopdowhile failed");
                            errorIndex = i;
                        }
                    } else {
                        failedCfgs.add("loopdowhile ");
//                        System.out.println("loopdowhile failed");
                        errorIndex = i;
                    }
                } else {
                    failedCfgs.add("loopdowhile ");
//                    System.out.println("loopdowhile failed");
                    errorIndex = i;
                }
            } else {
                failedCfgs.add("loopdowhile ");
//                System.out.println("loopdowhile failed");
                errorIndex = i;
            }
        } else {
            failedCfgs.add("loopdowhile ");
            System.out.println("loopdowhile failed");
            // errorIndex = i;
        }
        return false;
    }
    //salman code for dowhile ended

    //salman code for body used in if while do while for functionbody constructorbody
    boolean body() {
        if (inComming.get(i).equals("loopwhile") || inComming.get(i).equals("loopdo")
                || inComming.get(i).equals("loopfor") || inComming.get(i).equals("") || inComming.get(i).equals("ID") || inComming.get(i).equals("general")
                || inComming.get(i).equals("personal") || inComming.get(i).equals("secure") || inComming.get(i).equals("standard") || inComming.get(i).equals("stable")
                || inComming.get(i).equals("absolute") || inComming.get(i).equals("int_constant") || inComming.get(i).equals("nfloata")
                || inComming.get(i).equals("char_constant") || inComming.get(i).equals("nstringa ") || inComming.get(i).equals("boolean_constant")
                || inComming.get(i).equals("stop]") || inComming.get(i).equals("resume]") || inComming.get(i).equals("Inc_Dec") || inComming.get(i).equals("(") || inComming.get(i).equals("DT")
                || inComming.get(i).equals("check") || inComming.get(i).equals("\\")) {
            System.out.println("body " + inComming.get(i));

            if (inComming.get(i).equals("\\")) {

//                System.out.println("body " + inComming.get(i));
                // i++;
//                System.out.println("body success");
                return true;
            } else if (s_st()) {
                if (m_st()) {
//                    System.out.println("body success");
                    return true;

                } else {
                    errorIndex = i;
                }
            } else {
                errorIndex = i;
            }
        } else {
//            System.out.println("body " + inComming.get(i));

            errorIndex = i;

            return false;
        }
        return false;
    }

    boolean s_st() {
        if (inComming.get(i).equals("loopwhile") || inComming.get(i).equals("loopdo")
                || inComming.get(i).equals("loopfor") || inComming.get(i).equals("ID") || inComming.get(i).equals("general")
                || inComming.get(i).equals("personal") || inComming.get(i).equals("secure") || inComming.get(i).equals("standard") || inComming.get(i).equals("stable")
                || inComming.get(i).equals("absolute") || inComming.get(i).equals("int_constant") || inComming.get(i).equals("nfloata")
                || inComming.get(i).equals("char_constant") || inComming.get(i).equals("nstringa ") || inComming.get(i).equals("boolean_constant")
                || inComming.get(i).equals("stop") || inComming.get(i).equals("resume") || inComming.get(i).equals("Inc_Dec") || inComming.get(i).equals("(") || inComming.get(i).equals("DT")
                || inComming.get(i).equals("check")) {
//            System.out.println("s_st " + inComming.get(i));
            if (inComming.get(i).equals("stop")) {
                i++;
                if (inComming.get(i).equals("]")) {
                    i++;

                    return true;
                }
            } else if (inComming.get(i).equals("resume")) {
                i++;
                if (inComming.get(i).equals("]")) {
                    i++;
                    return true;
                }
            } //else if(decl()){return true;}
            else if (inComming.get(i).equals("check")) {
                if (checkspare()) {
//                    System.out.println("s_st success");
                    return true;
                }
            } else if (inComming.get(i).equals("loopfor")) {
                if (loopfor()) {

//                    System.out.println("s_st success");
                    return true;
                }
            } else if (inComming.get(i).equals("loopwhile")) {
                if (loopwhile()) {
//                    System.out.println("s_st success");
                    return true;
                }
            } else if (inComming.get(i).equals("loopdo")) {
                if (loopdowhile()) {
//                    System.out.println("s_st success");
                    return true;
                }
            } else if (xBody()) {
//                System.out.println("s_st success");
                return true;
            } // declaration + assign function
            else {
                errorIndex = i;
            }
        }

        return false;
    }

    boolean ID1_indec_call() {
        if (inComming.get(i).equals(".")) {
            i++;
            if (inComming.get(i).equals("ID")) {
                i++;
                if (ID1_indec_call()) {
                    return true;
                }
            }
        } else if (inComming.get(i).equals(":") || inComming.get(i).equals("(") || inComming.get(i).equals("]") || inComming.get(i).equals("Inc_Dec")) {
            return true;
        }
        return false;
    }

    boolean ARR_indec_call() {
        if (inComming.get(i).equals(":")) {
            i++;
            semiColon.push(":");
            if (a()!=null) {
                if (inComming.get(i).equals(";")) {
                    i++;
                    if (ID1_indec_call()) {
                        if (ARR_indec_call()) {
                            return true;
                        }
                    }
                }
            }
        } else if (inComming.get(i).equals("(") || inComming.get(i).equals("]") || inComming.get(i).equals("Inc_Dec") || inComming.get(i).equals(".") || inComming.get(i).equals("AO")) {
            return true;
        }
        return false;
    }

    boolean m_st() {
        if (inComming.get(i).equals("loopwhile") || inComming.get(i).equals("loopdo")
                || inComming.get(i).equals("loopfor") || inComming.get(i).equals("") || inComming.get(i).equals("ID") || inComming.get(i).equals("general")
                || inComming.get(i).equals("personal") || inComming.get(i).equals("secure") || inComming.get(i).equals("standard") || inComming.get(i).equals("stable")
                || inComming.get(i).equals("absolute") || inComming.get(i).equals("int_constant") || inComming.get(i).equals("nfloata")
                || inComming.get(i).equals("char_constant") || inComming.get(i).equals("nstringa ") || inComming.get(i).equals("boolean_constant")
                || inComming.get(i).equals("stop") || inComming.get(i).equals("resume") || inComming.get(i).equals("Inc_Dec") || inComming.get(i).equals("(") || inComming.get(i).equals("DT")
                || inComming.get(i).equals("check") || inComming.get(i).equals("\\")) {
//            System.out.println("m_st " + inComming.get(i));

            if (inComming.get(i).equals("\\")) {

//                System.out.println("body " + inComming.get(i));
                // i++;
//                System.out.println("body success");
                return true;
            } else if (s_st()) {
                if (m_st()) {
//                    System.out.println("body success");
                    return true;

                } else {
                    errorIndex = i;
                }
            } else {
                errorIndex = i;
            }
        } else {
//            System.out.println("m_st " + inComming.get(i));

            errorIndex = i;

            return false;
        }
        return false;
    }
//for declaration and assignment in 

    boolean xBody() {
        if (inComming.get(i).equals("AM") || inComming.get(i).equals("stable") || inComming.get(i).equals("absolute") || inComming.get(i).equals("DT")
                || inComming.get(i).equals("ID") || inComming.get(i).equals("Inc_Dec")) {
//            System.out.println("Declaaration x " + inComming.get(i));
            looptemp = i;
            if (AM1_Body()) {
                if (STABLE_Body()) {  //decl
                    if (FINAL_Body()) {
//                        System.out.println("hello");
                        if (DT_loop()) {
//                            System.out.println("hello");
                            if (assign1()) {

                                if (list_decl()) {
                                    if (inComming.get(i).equals("]")) {
                                        i++;
                                        return true;

                                    }
                                }
                            } else {
//                                System.out.println("xBody Failed");
                            }
                        } else if (INC_DEC_CALL()) {
                            return true;
                        } else if (assign()) {

//                            System.out.println("call assign");
                            i++;
                            return true;
                        }

                    } else {
//                        System.out.println("xBody Failed");
                    }
                }
            }
        }

        return false;
    }

    boolean AM1_Body() {
        if (inComming.get(i).equals("AM") || inComming.get(i).equals("stable") || inComming.get(i).equals("absolute")
                || inComming.get(i).equals("DT") || inComming.get(i).equals("ID") || inComming.get(i).equals("Inc_Dec")) {
            if (inComming.get(i).equals("AM")) {
                i++;

                return true;
            } else if (inComming.get(i).equals("stable") || inComming.get(i).equals("absolute")
                    || inComming.get(i).equals("DT") || inComming.get(i).equals("ID") || inComming.get(i).equals("Inc_Dec")) {
                return true;
            }
        }
        return false;
    }

    boolean STABLE_Body() {
        if (inComming.get(i).equals("stable") || inComming.get(i).equals("absolute")
                || inComming.get(i).equals("DT") || inComming.get(i).equals("ID") || inComming.get(i).equals("Inc_Dec")) {
            if (inComming.get(i).equals("stable")) {
                i++;

                return true;
            } else if (inComming.get(i).equals("absolute")
                    || inComming.get(i).equals("DT") || inComming.get(i).equals("ID") || inComming.get(i).equals("Inc_Dec")) {
                return true;
            }
        }
        return false;
    }

    boolean FINAL_Body() {
        if (inComming.get(i).equals("absolute")
                || inComming.get(i).equals("DT") || inComming.get(i).equals("ID") || inComming.get(i).equals("Inc_Dec")) {
            if (inComming.get(i).equals("absolute")) {
                i++;
                // flag = 1;
                return true;
            } else if (inComming.get(i).equals("DT") || inComming.get(i).equals("ID") || inComming.get(i).equals("Inc_Dec")) {
                return true;
            }
        }
        return false;
    }
}

//////////////////////////////////// TABLES ///////////////////
class Variables {

    String type;
    String name;
    int scope;
    String Final;
    String accessModifier;
    String Static;
    int size;
    String object;
    ArrayList<Variables> varTable = new ArrayList<Variables>();
    Class cls=new Class();
    Variables() {

    }

    Variables(String type, String name, int Scope, String Final, String accessModifiers, String Static, int size,String object) {

        this.type = type;
        this.name = name;
        this.scope = Scope;
        this.Final = Final;
        this.Static = Static;
        this.accessModifier = accessModifiers;
        this.size = size;
        this.object=object;

    }

    void insert(String type, String name, int Scope, String Final, String AccessModifier, String Static, int size,String object) {

        varTable.add(new Variables(type, name, Scope, Final, AccessModifier, Static, size,object));

        System.out.println("Inserted Row is Type = " + type + " name= " + name + " Scope = " + Scope + " Final =" + Final
                + " Static =" + Static + " AM = " + AccessModifier + " Size = " + size+" Object = "+object);

    }

    ///used at the time of insertion
    String lookUp(String name, int scope) {
        String flag = null;
        int i;
        for (i = 0; i < varTable.size(); i++) {


            if (varTable.get(i).name.equals(name) && varTable.get(i).scope == scope) {
             
                flag = varTable.get(i).type;
                return flag;
            }

        }
        System.out.println(flag + " " + i);
        return flag;
    }
    
    
    //compatibility of all types of variables                                                        is usedd to compaer the dimensions oof saved and accessedd var
    String compatibility(String operator,String leftOperandType,String rightOperandType,Stack scope,int arrayCounter ){
        
        int rov=0,lov=0;
        ArrayList<String> types=new ArrayList<String>();
        types.add("nchara");types.add("ninta"); types.add("nfloata");
        
        //for variables like ab.ab
        if(operator.equals(".")){
          
            String dataObjectType[]=leftOperandType.split(" ");
            int scopeClass=0;
        
            //logic is for ab.bc ab=LO bc=RO 
            //1) Lft operand ki type mjhy paramter sy milegi by q ke pehly lookup krengy usky liye right operand ki type unknown he mjhy parameter me right operand ka naam milega
            //or mjhy right op ki type ki zrurt b nh bcx class m jakay bs variable dkhna he ab
            //2)Go to that class and then lookup for the variable if it is present also check that it is not private
            //3) if above conditions are met then return the type of the variables that is class name
            
            //for data type and object type in right operand Type var
            for(int j=dataObjectType.length-1;j>=0;j--){
                
                System.out.println(dataObjectType[j]);
                
            //finding the class which is at current index of dataOBjectType
//            for(int i=Class.classTable.size()-1;i>=0;i--){
//                
//                System.out.println(i+" "+Class.classTable.size()+" "+dataObjectType[j]);
//                //if class found of same name
//                   if(Class.classTable.get(i).name.equals(dataObjectType[j])&&
//                        ( Class.classTable.get(i).type.equals("class")||Class.classTable.get(i).type.equals("novitachocolate"))){
//                       
//                       scopeClass=Class.classTable.get(i).bodyScope;
                       
                       //now going into scope finded class and look for variable name
                      scopeClass=cls.lookUpForClassInVarCompat(dataObjectType[j], scope, Boolean.TRUE, false);
                       
                       for(int k=0;k<varTable.size();k++){
//                           System.out.println(varTable.get(k).name+" "+rightOperandType);
//                           System.out.println(varTable.get(k).scope+"=="+scopeClass);
                           //now looking for the variable name=rightoperandType
                           if(varTable.get(k).name.equals(rightOperandType)&&
                                   varTable.get(k).scope==scopeClass&&
                                   varTable.get(k).size==arrayCounter)        {
                                
                             
                                 //checking for private if variable is private thn it cant be accessed
                               if(varTable.get(k).accessModifier!=null){
                                 
                                   if(varTable.get(k).accessModifier.equals("personal")){
                                       return null;
                                   }
                               }
                               
                               //returning the type of variable with object if present
                               if(varTable.get(k).object!=null){
                                   return varTable.get(k).type+" "+varTable.get(k).object;
                               }
                            
                               //agr object null hoto srf datatype return krdo
                               return varTable.get(k).type;
                           }
                           
                    //   }
                 //  }
            }
            
        
            }
              
              
          
        }
       
        //for normal operators * / etc
        else{ 
             //logic is compare all types of left side with first type of ight side
                String leftType[]=leftOperandType.split(" ");
                String rightType[]=rightOperandType.split(" ");//iski srf 1st index chaiye mjhy
               
         
                for(int j=0;j<leftType.length;j++){
               
                    
            System.out.println("nazzrees");
             //agr dono same hain to koi b retur kkrdo
       
            if(rightType[0].equals(leftType[j])){
             
            return rightType[0];
       
            }
       
             else{
               
//                     //agar arraylist contain hi nh krti dono ya kisi ek ko to mtlb compatible nh hain 
//                  if(!types.contains(rightOperandType)||!types.contains(leftOperandType)){
//                    System.out.println(types.contains(rightOperandType));
//                    //  return null;
//                     }
            
                     // agr dono array list me hain to bara wala return hojaega
                  if(types.contains(rightType[0])&&types.contains(leftType[j])){
                      
            
                        for(int i=0;i<types.size();i++){
                
                
                             if(types.get(i).equals(rightType[0])){
                
                                  rov=i;
                             }
                              if(types.get(i).equals(leftType[j])){
                                  lov=i;
                             }   
            
                         }
                     
                        return types.get(Math.max(lov, rov));
                   
            }
                }
        }
            
        } return null;
    }      
    
    //usedd at the time of usage currently in expression
    //it will search for the given variable name and start from thescope which is at the top right now
   //if variable found thn it will return the datatype of that variable otherwise null
    String lookupe(String name , Stack scope,int arrayCounter){
       
        String dataType=null;
        
        //finding class scope to check if class is extending or implementing any other class
        //if it is extending or implementing any other class thn we will also lookup
        //in those classes and interfaces
        //logic is to compare the scope in stack from below to every class body
        //scope present in classTable if the match occur thn the class is identified and we  can check
        //the extends and implements of that class
        System.out.println("haan");
       
        //below called function will do all hard work for you
        scope=parentFinder(name,scope,arrayCounter);
        
        
        //code for finding variable in given scopes present in stack
     for(int j=scope.size()-1;j>=0;j--){
        
            
        for ( int i= 0; i < varTable.size(); i++) {
          
          

            if (varTable.get(i).name.equals(name) &&
                    varTable.get(i).scope ==(int)scope.get(j)&&
                    varTable.get(i).size==arrayCounter) {
                
              
                dataType =varTable.get(i).type ;
                
                //object type b return karenngy agr null na ho to
                if(varTable.get(i).object!=null){
                    
                    return dataType+" "+varTable.get(i).object;
                }
                return dataType;
            }

        }
        
        }  

      return dataType; //null
   }
    
    //used in Assignment to chk final variable
     String lookupAFinal(String name , Stack scope,int arrayCounter){
       
        String dataType=null;
        
        //finding class scope to check if class is extending or implementing any other class
        //if it is extending or implementing any other class thn we will also lookup
        //in those classes and interfaces
        //logic is to compare the scope in stack from below to every class body
        //scope present in classTable if the match occur thn the class is identified and we  can check
        //the extends and implements of that class
        System.out.println("haan");
       
        //below called function will do all hard work for you
        scope=parentFinder(name,scope,arrayCounter);
        
        
        //code for finding variable in given scopes present in stack
     for(int j=scope.size()-1;j>=0;j--){
        
            
        for ( int i= 0; i < varTable.size(); i++) {
           
           

            if (varTable.get(i).name.equals(name) &&
                    varTable.get(i).scope ==(int)scope.get(j)&&
                    varTable.get(i).size==arrayCounter&& 
                    varTable.get(i).Final==null)
                            {
                
                               
                                
                                
               
                dataType =varTable.get(i).type ;
                
                //object type b return karenngy agr null na ho to
                if(varTable.get(i).object!=null){
                    
                    return dataType+" "+varTable.get(i).object;
                }
                return dataType;
            }

        }
        
        }  

      return dataType; //null
   }
    
    //parent finder by taking scope stack used in lookupe above
  Stack<Integer> parentFinder(String varName,Stack scopes,int arrayCounter){
        int scope=0;
        
        // getting the scope of the class by comparing scopes with bodyscope
        for(int j=scopes.size()-1;j>=0;j--){
     //comparing scopes from last with all class scopes from start if match found means its correct class scope
            for(int i=0;i<Class.classTable.size();i++){
            
                //matching body scope
            if(Class.classTable.get(i).bodyScope==(int)scopes.get(j)//&&
                 // (  Class.classTable.get(i).type.equals("class")||Class.classTable.get(i).type.equals("novitachocolate"))
                    ){
                
                scope=(int) scopes.get(j);
                break;
            }
        }
        }
            ArrayList<String> ImplementEx=new ArrayList<String>();
           
            ///now checking the extends and implements of the class jiska scope find kia
            if(scope>0){
            for(int i=0;i<Class.classTable.size();i++) {
         
                
                if(Class.classTable.get(i).bodyScope==scope){
             
                    //name of extends and implements are found
                   if(Class.classTable.get(i).Interface!=null){
                       ImplementEx=Class.classTable.get(i).Interface;
                   }
                   
                   if(Class.classTable.get(i).Extends!=null){
                       ImplementEx.add(Class.classTable.get(i).Extends);
                   }
             
         }  
     }         
            }
            
            Stack<Integer> fakeScope=new Stack<Integer>();
            //now finding the scope of Extends and implmenets
              if(ImplementEx.size()>0){
                  
                  for(int k=0;k<ImplementEx.size();k++){
                      
                      
                  for(int i=Class.classTable.size()-1;i>=0;i--){
                      //searching from the bottom the very first match will break the inner loop
                      if(Class.classTable.get(i).name.equals(ImplementEx.get(k))){
                          
                          fakeScope.add(Class.classTable.get(i).bodyScope);
                          break;
                      }
                  }
              }
              }     
              
              //now finally making a new scope stack
              if(fakeScope.size()>0){
                  
                  //putting the found scopes at the begniinig og original scope
                  //now first all inner scopes will be checked after checking all
                  //thn inherited scopes will be checked
                  // Performing this operation by using extra scope stack 
                  
                  Stack<Integer> scope1=new Stack<Integer>();
                  
                  //first ading fakescopes valuues
                  for(int i=0;i<fakeScope.size();i++){
                      
                      scope1.add(fakeScope.get(i));
                  }
                  //now scopes values
                  for(int i=0;i<scopes.size();i++){
                      scope1.add((int)scopes.get(i));
                  }
                  //this new scope1 contains all extends implements and inner scopes
                  //if accessed from last index thn first all inner scopes will be tretead
                  //thn extend and thn implements(in reverse sequence)
                  
                  //now returning scope1
                return scope1;  
              }
        //orignial list wil be returned if no additional scopes found
              return scopes;
    }
  
  //for assignment and declarationn a b=new b();
  //object name means right class name
  //this method will be used only in the case if variable type and object type is not samme
  String objectCompatibility(String objectName,Stack scopes,String leftType){
      
      int classExists=cls.lookUpForClassInVarCompat(objectName, scopes, Boolean.TRUE,false);//it will return -1 if no class found
      //otherwise classExists contains the body scope of class
     
      //means class name is valid
      if(classExists!=-1){
          
          //now check the compatiblity means  left side type with right side object Name
         //we have sccope so we go through all classes until we found the class having same scope body
     
         for(int i=0;i<Class.classTable.size();i++){
             
         
             //class found having body scope == classExists
                 if(Class.classTable.get(i).bodyScope==classExists){
             
                //now class index is found check if it is extending the left assignment type
         
                    if(Class.classTable.get(i).Extends!=null){
                        
                        //now matching
                        if(Class.classTable.get(i).Extends.equals(leftType)){
                        return leftType;}
                    }
                
                 }
          
      
             }
      }
         
         
      return null;
  }
  //will return the index of required variable
  int varFinderAssign(String leftType,Stack scopes,String leftVName){
      
      int scope;
      
      for(int k=scopes.size()-1;k>=0;k--){
          
          
      for(int i=Class.classTable.size()-1;i>=0;i--){
          
          if(Class.classTable.get(i).name.equals(leftType)&&
                  Class.classTable.get(i).bodyScope==(int)scopes.get(k)&&
                  (Class.classTable.get(i).type.equals("class")||
                  Class.classTable.get(i).type.equals("novitachocolate"))){
                 
              scope=Class.classTable.get(i).bodyScope;
              
              for(int j=0;j<varTable.size();j++){
                  
                  if(varTable.get(j).name.equals(leftVName)&&
                          varTable.get(j).scope==scope){
                      
                      return j;
                  }
              }
          }
      }
      }
      
      //for 0 scope
      for(int i=Class.classTable.size()-1;i>=0;i--){
          
          if(Class.classTable.get(i).name.equals(leftType)&&
                  Class.classTable.get(i).scope==0&&
                  (Class.classTable.get(i).type.equals("class")||
                  Class.classTable.get(i).type.equals("novitachocolate"))){
                 
              scope=Class.classTable.get(i).bodyScope;
              
              for(int j=0;j<varTable.size();j++){
                  
                  if(varTable.get(j).name.equals(leftVName)&&
                          varTable.get(j).scope==scope){
                      
                      return j;
                  }
              }
          }
      }
      
      return -1;
  }
  //used in assginment to chk if vaariable is final
  //return true if it is not final
  Boolean lookupA2Final(String varName, String className,Stack scope){
      String type[]=className.split(" ");
      //class bodyscope found
     
      
      for(int j=type.length-1;j>=0;j--){
      
    int classScope=cls.lookUpForClassInVarCompat(type[j], scope, Boolean.TRUE, Boolean.FALSE);
       
    //chkng variable
      for(int i=0;i<varTable.size();i++){
          
            if(varTable.get(i).scope==classScope&&
                varTable.get(i).name.equals(varName)&&
                    varTable.get(i).Final==null){
                
            return true;
            }
          
  }
      }
      return  false;
  }
}



//     CLASSTABLE 
class Class {
// har class ka scope 0 rkhna srf inner class k ilawa
    String parent;
    String type;
    String name;
    int scope;
    String Static;
    String Final;
    String Extends; 
    ArrayList<String> Interface;
    String accessModifier;
    int bodyScope;
 static  ArrayList<Class> classTable = new ArrayList<Class>();
     
    Class() {

    }

    Class(String parent,String type, String name, int scope, String Static, String Final, String Extends, ArrayList Interface, String AccessModifier,int bodyScope) {
    //parent add krlo inner class ke liye  agr stack null nh hoga to class ka parent aega stack me sy scope dkh k
    //inner class ke ;iye lkup ka alg fnc hga jo scope dkh k name return krega
    //extends jis class me hoga us me stack me parent class ka scope push krdengy takay lookup hosky variables 
    //implements ke liye ek naya function banega jo compare krega child k functions ko with parents overridign detect krny ke liye
        this.parent=parent;
        this.type = type;
        this.name = name;
        this.scope = scope;
        this.Static = Static;
        this.Final = Final;
        this.Extends = Extends;
        this.Interface = Interface;
        this.accessModifier = AccessModifier;
        this.bodyScope=bodyScope;
    }

    void insert(String parent,String type, String name, int scope, String Static, String Final, String Extends, ArrayList<String> Implements, String accessModifier, int bodyScope) {

        classTable.add(new Class(parent,type, name, scope, Static, Final, Extends, Implements, accessModifier,bodyScope));
        System.out.println("Inserted Row is parent ="+parent+" Type = " + type + " name= " + name + " Scope = " + scope + " Final =" + Final
                + " Static =" + Static + " AM = " + accessModifier + " Extends = " + Extends + " Implements = " + Implements+"BodyScope = "+bodyScope);
    }

    Boolean lookUp(String name, int scope) {

        Boolean flag = true;

        int i;
        for (i = 0; i < classTable.size(); i++) {
           
            

            if (classTable.get(i).name.equals(name) && classTable.get(i).scope == scope) {
             
                flag = false;
                return flag;
            }

        }
        return flag;
    }
    //will return true if no matching name class found in scopes
    //if zeroScopeflag is set to true it will also check for 0 scope
    //assuming that clas body scope is not pushed in to the stack yet
     Boolean lookUp(String name, Stack scope,Boolean zeroScopeFlag) {

        Boolean flag = true;

        for(int j=0;j<scope.size();j++){
        
            
        for ( int i= 0; i < classTable.size(); i++) {
            

            if (classTable.get(i).name.equals(name) 
                    && classTable.get(i).scope ==(int)scope.get(j) 
                    &&(classTable.get(i).type.equals("class")||classTable.get(i).type.equals("novitachocolate"))) {
        
                flag = false;
                return flag;
            }

        }
        
        }  
        //if flag is set thn also chk for 0 sccope
        if(zeroScopeFlag==true){
            
                for ( int i= 0; i < classTable.size(); i++) {
           

            if (classTable.get(i).name.equals(name) && 
                    classTable.get(i).scope ==0 &&
                    (classTable.get(i).type.equals("class")||classTable.get(i).type.equals("novitachocolate"))) {
           
                flag = false;
                return flag;
            }

        }
        }
        return flag;
    }
     
     
     
     
     //will return true if no matching name class found in scopes
    //if zeroScopeflag is set to true it will also check for 0 scope
    //assuming that clas body scope is not pushed in to the stack yet
     int lookUpForClassInVarCompat(String name, Stack scope,Boolean zeroScopeFlag,Boolean onlyZeroCheck) {

        int flag = -1;

      
        for(int j=0;j<scope.size();j++){
        
            
        for ( int i= 0; i < classTable.size(); i++) {
           

            if (classTable.get(i).name.equals(name) 
                    && classTable.get(i).scope ==(int)scope.get(j) 
                    &&(classTable.get(i).type.equals("class")||classTable.get(i).type.equals("novitachocolate"))) {
             
                flag = classTable.get(i).bodyScope;
                return flag;
            }

        }
        
        }
        
        //if flag is set thn also chk for 0 sccope
        if(zeroScopeFlag==true){
            
                for ( int i= 0; i < classTable.size(); i++) {
           
        

            if (classTable.get(i).name.equals(name) && 
                    classTable.get(i).scope ==0 &&
                    (classTable.get(i).type.equals("class")||classTable.get(i).type.equals("novitachocolate"))) {
          
                
                flag = classTable.get(i).bodyScope;
                return flag;
            }

        }
        }
        return flag;
    }
    
    
    //checks if the input class is final or not
    Boolean finalFinder(String className){
        
        for(int i=classTable.size()-1;i>=0;i--){
            System.out.println(classTable.size()+""+i);
            System.out.println("FinalFinder class name ="+className+" "+classTable.get(i).name+" "+classTable.get(i).Final);
            if(classTable.get(i).name.equals(className)&&
               classTable.get(i).Final==null   ){
                System.out.println("Found");
                return true;
            }  
        }
        
        return false;
    }
    
    //for implements of classes and interface extends
    ///will return true if input interface exists
    Boolean implementsLoopkup(String interfaceName){
        
        for(int i=0;i<classTable.size();i++){
        
            if(classTable.get(i).name.equals(interfaceName)&&
               classTable.get(i).type.equals("interface")     ){
                
                return true;
            }
    }
        
        return false;
    }
    
    //Parent finder w

}

class Function {

    String returnType;
    String name;
    int scope;
    String Static;
    String Final;
    ArrayList<Variables> parameters;
    String accessModifier;
    ArrayList<Function> functionTable = new ArrayList<Function>();

    Function() {

    }

    Function(String returnType, String name, int scope, String Static, String Final, ArrayList parameters, String AccessModifier) {

        this.returnType = returnType;
        this.name = name;
        this.scope = scope;
        this.Static = Static;
        this.Final = Final;
        this.parameters = parameters;
        this.accessModifier = accessModifier;
    }

    void insert(String returnType, String name, int scope, String Static, String Final, ArrayList<Variables> parameters, String accessModifier) {

        functionTable.add(new Function(returnType, name, scope, Static, Final, parameters, accessModifier));
        
        if(parameters.size()==0){
            System.out.println("Inserted Row is return type = " + returnType + " name= " + name + " Scope = " + scope + " Final =" + Final
                + " Static =" + Static + " AM = " + accessModifier + " parameters = " + parameters);
        }
        else{
        for(int i=0;i<parameters.size();i++){
           
        System.out.println("Inserted Row is return type = " + returnType + " name= " + name + " Scope = " + scope + " Final =" + Final
                + " Static =" + Static + " AM = " + accessModifier + " parameters = " + parameters.get(i).name  + " "+
                parameters.get(i).type+" "+ parameters.get(i).size);
    }
        }
    }

    
    String lookUp(String name, int scope, ArrayList<Variables> parameters) {

        String flag = null;
        int flagForPara=0;
        int i;
        for (i = 0; i < functionTable.size(); i++) {

            System.out.println("mmmmmmmmmmmmmmmm"+functionTable.get(i).name + functionTable.get(i).scope);
            System.out.println(name+scope);
            //checking if any function has same scope , name and no of parameters already in table later will chk the type of parameters in old logic below
            if (functionTable.get(i).name.equals(name)
                    && functionTable.get(i).scope == scope
                    && functionTable.get(i).parameters.size()==parameters.size()) {
                 
                ///old logic for simple lookup without final keyword feature and doesnt take overriding in account STARTS HERE
                if(parameters.size()==0){
                    flag=functionTable.get(i).returnType;
                   return flag;
                }
                      //chkng parameters type for overloading
                else{
                    for(int j=0;j<parameters.size();j++){
                    if(functionTable.get(i).parameters.get(j).type.equals(parameters.get(j).type)){
                       
                        flagForPara++;
                        
                    }
                }  if(flagForPara==parameters.size()){
                   flag=functionTable.get(i).returnType;
                    return flag;
                }
                }
            }
                //OLD LOGIC ENDS HERE
               
            }
            //logic for overriding 
            // if two classes of same name are present and class inherit the same class name thn the class will be the first immediate parent in both of theem will be considered as inherited class
             if(1==1){
                
                String parentClass=null;
              int classBodyScope=scope;
                Boolean flagParentClassPresent=true;
              
                while(flagParentClassPresent){
                    
                    flagParentClassPresent=false;
                    
                for(i=0;i<Class.classTable.size();i++){
                    System.out.println("heelo");
                    //if class has a parent then it should be checke for method override
                  //checking for parent
                    if(Class.classTable.get(i).bodyScope==classBodyScope&&
                       Class.classTable.get(i).Extends!=null     ){
                        
                        //means extends field is not null
                        flagParentClassPresent=true;
                        
                        parentClass=Class.classTable.get(i).Extends;
                        
                        //finding parent class scope
                        for(int j=Class.classTable.size()-1;j>=0;j--){
                             System.out.println("Hello");
                            if(Class.classTable.get(j).name.equals(parentClass)&&
                               (Class.classTable.get(j).type.equals("class")||Class.classTable.get(j).type.equals("novitachocolate"))     ){
                                
                                classBodyScope=Class.classTable.get(j).bodyScope;
                                
                                //finally checking methods of the parent cope for same name and final
                                for(int k=0;k<functionTable.size();k++){
                                    
                                    if(functionTable.get(k).scope==classBodyScope&&
                                       functionTable.get(k).name.equals(name)&&
                                       functionTable.get(k).Final!=null    ){
                                        
                                      System.out.println("ERROR Absolute methods cant be overriden");
                                        flag=functionTable.get(k).returnType;
                                        return flag;
                                    }
                                    if(k==functionTable.size()-1){
                                        
                                        continue;
                                    }
                                }
                            }
                        }
                   
                    }
                    
                }
           
                }
            }

        
        
        return flag;
    }
    
    Boolean consLookUp(String name, int scope, ArrayList<Variables> parameters) {

        Boolean flag = true;
        int flagForPara=0;
        int i;
        try{
        for (i = 0; i < functionTable.size(); i++) {

//            System.out.println(i+""+functionTable.size());
//            System.out.println("mmmmmmmmmmmmmmmm"+functionTable.get(i).name + functionTable.get(i).scope
//                    +functionTable.get(i).returnType+functionTable.get(i).parameters.size());
//            System.out.println(name+scope+parameters.size());
//            System.out.println(i);
            if (functionTable.get(i).name.equals(name)
                    && functionTable.get(i).returnType==null
                    && functionTable.get(i).scope == scope
                    && functionTable.get(i).parameters.size()==parameters.size()) {
               
                System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
                if(parameters.size()==0){
                    flag=false;
                   return flag;
                }
                      //chkng parameters type for overloading
                else{
                    for(int j=0;j<parameters.size();j++){
                    if(functionTable.get(i).parameters.get(j).type.equals(parameters.get(j).type)){
                       
                        flagForPara++;
                        
                    }
                }  if(flagForPara==parameters.size()){
                    flag= false;
                    return flag;
                }
                }
               
            }

        }} catch(NullPointerException e){
            System.out.println(e);
        }
        return flag;
    }
    
    //for checking if class has override all interface methods
    Boolean implementsOverrideMethods(int classBodyScope ){
        
        int checker=0;
        ArrayList<String> Implements=null;
        Boolean returnFlag=true;
        Boolean interfaceIsEmpty=true;
        int interfaceBodyScope; //interface body scop
        
       //finding Implements list using body scope of class
       for(int a=0;a<Class.classTable.size();a++){
       
           if(Class.classTable.get(a).bodyScope==classBodyScope){
               Implements=Class.classTable.get(a).Interface;
               break;
           }
           
       }
       
       //agar implements kch kia hi nh he to direct true return hojaega
       if(Implements==null){
           return true;
       }
        
       //MAIN LOGIC BEGINNING FOR MATCHING METHODS
        //wiil go to the size of no of implements
        for(int i=0;i<Implements.size();i++ ){
            
       
                //finding to body scope of ith interface
                interfaceBodyScope=interfaceBodyScopeFinder(Implements.get(i));
               
 
                for(int k=0;k<functionTable.size();k++){
                                           
                    //grabing a function whose scope is same as interface body scope
                    if(functionTable.get(k).scope==interfaceBodyScope){
                      
                        
                        for(int l=0;l<functionTable.size();l++){
                            
                            //grabbing function with the scope same as class body scope
                            if(functionTable.get(l).scope==classBodyScope){
                                
                                //now comparing the grabbed function with interface function
                               
                                System.out.println("dhannedwaad"+functionTable.get(l).name+" "+functionTable.get(k).name);
                              
                                if(functionTable.get(l).Final==null&&
                                        functionTable.get(l).Static==null&&
                                        functionTable.get(l).accessModifier==null&&
                                        functionTable.get(l).name.equals(functionTable.get(k).name)&&
                                       functionTable.get(l).parameters.size()==functionTable.get(k).parameters.size()&&
                                        functionTable.get(l).returnType.equals(functionTable.get(k).returnType)){
                                    
                                    Boolean flag=true;
                                    
                                    //checking if seqeunece and type of paramters is same
                                    for(int p=0;p<functionTable.get(l).parameters.size();p++){
                                        
                                        if(!functionTable.get(l).parameters.get(p).type.equals(functionTable.get(k).parameters.get(p).type)){
                                            flag=false;
                                        }
                                    }
                                    
                                    if(flag){
                                        checker++;
                                    returnFlag=true;
                                    }
                                }
                            }
                        }
                        
                        if(checker==1){
                             checker=0;
                        }
                        else{
                             return false;
                         }
                    }
                    
                
                
            }
         
        }
        
        return returnFlag;
    }
    
    //provided interface body scope will return number of methods in that interface
    int interfaceMethodsCount(int scope){
        int counter=0;
        
        for(int i=0;i<functionTable.size();i++){
            
            if(functionTable.get(i).scope==scope)
            {
                counter++;
            }
        }
        System.out.println(counter);
        return counter;
    }
    
    //given interface name it will return the bodyScope of that interface
    //will return -5 if interface not found
    int interfaceBodyScopeFinder(String interfaceName){
        int bodyScopeInterface=-5;
                
        for(int i=0;i<Class.classTable.size();i++){
            
            if(Class.classTable.get(i).name.equals(interfaceName)&&
                    Class.classTable.get(i).type.equals("interface")){
                
                bodyScopeInterface=Class.classTable.get(i).bodyScope;
            }
        }
        
        return bodyScopeInterface;
    }

}
