/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexical.anallyzer;

import java.util.Stack;
import java.util.ArrayList;

/**
 *
 * @author Roshaann 2.7 gpa
 */
public class SyntaxParser {
int Roshaan=0;
Boolean flagForFunctionVariable=false;//will be used inside constructor and function to get rid of AM int function and constructor body LASTCHANGE , another change in interface
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
    // Boolean flagForBracket=false;
    // Boolean flagForSemiColon=false;
    //   Boolean flag2ForBracket=false;
    //   Boolean flag2ForSemiColon=false;
    //  Boolean flag2ForComma=false;
    //getting refernce of token static variable in class Reader having all tokens in it and thn classParts having all class prts
    ArrayList<String> inComming = Reader.t.classParts;
    ArrayList<String> inCommingVp = Reader.t.value;
    ArrayList<Integer> inCommingLine = Reader.t.line;
    int i = 0;
    int[] a = new int[7];

    //this will be called by main
    SyntaxParser() {
        Reader.t.showAll();
        System.out.println(inComming.get(i));
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
        if (/*errorIndex <= inComming.size() && errorIndex > 0*/failedCfgs.size() > 0) {
            if (errorIndex == inComming.size()) {
                errorIndex = errorIndex - 1;
                //System.out.println("error== i is working");
            }
            // System.out.println(inCommingVp.get(errorIndex));
            // System.out.println("");
            System.out.println("Syntax Error: Error Found Line no  " + inCommingLine.get(errorIndex) + " CFG failed " + failedCfgs.get(0));
            //  System.out.println(inCommingVp.get(i));
        }
        else{
            ///IF no error thn call intermediate Syntax means Semantic
            IntermediateSyntax is=new IntermediateSyntax();
        }
    }
    // brackets masla kr rhy hain ()() krky I walay Iprime waly nh
    // remove ) ; walay checks sb me sy siwaye usky jismy hh1 hesrf wohi run hoga q k strt me

    boolean a() {
        if (inComming.get(i).equals("nfloata")
                || inComming.get(i).equals("nstringa ")
                || inComming.get(i).equals("nchara")
                || inComming.get(i).equals("ninta")
                || inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals("ID")
                || inComming.get(i).equals("(")) {
            System.out.println("a " + inComming.get(i));

            if (b()) {
                if (i >= inComming.size()) {
                    System.out.println("a success");
                    return true;
                } else if (aPrime()) {

                    System.out.println("a success");
                    return true;
                } else {
                    failedCfgs.add("Expression");
                    errorIndex = i;
                    System.out.println("a failed");
                }

            } else {
                failedCfgs.add("Expression");
                errorIndex = i;
                System.out.println("a failed");
            }

        } else {
            failedCfgs.add("Expression");
            errorIndex = i;
            i++;
            return false;
        }
        errorIndex = i;
        return false;
    }

    Boolean aPrime() {

        if (inComming.get(i).equals("||")
                || inComming.get(i).equals("]")
                || inComming.get(i).equals(")")
                || inComming.get(i).equals(";")
                || inComming.get(i).equals(",")
                || inComming.get(i).equals("\\")) {

            System.out.println("aprime" + inComming.get(i));

            if (inComming.get(i).equals("||")) {

                i++;

                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (b()) {

                    //if file ended out of bound
                    if (i >= inComming.size()) {
                        System.out.println("hello");
                        return true;

                    } else if (aPrime()) {
                        System.out.println("aPrime success");

                        return true;
                    } else {
                        errorIndex = i;
                    }

                } else {
                    errorIndex = i;
                }
            } else {

                System.out.println("aPrime success");
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

            System.out.println("b" + inComming.get(i));

            if (cc()) {

                if (i >= inComming.size()) {

                    return true;
                }
                if (bPrime()) {
                    System.out.println("b success");
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
            System.out.println("bprime" + inComming.get(i));

            if (inComming.get(i).equals("&&")) {

                i++;
                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (cc()) {

                    //if file ended out of bound
                    if (i >= inComming.size()) {
                        System.out.println("hello");
                        return true;
                    } else if (bPrime()) {

                        System.out.println("bPrime success");
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
            //                System.out.println("7hh");
            //                  if((inComming.get(i).equals(")")&&flagForBracket==true)||
            //                     (inComming.get(i).equals(";")&&flagForSemiColon==true   )  ){
            //                    
            //                      System.out.println("aPrime success");
            //                      return true;
            //                  }
            //                
            //            }
            else {

                System.out.println("bPrime success");

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

            System.out.println("cc" + inComming.get(i));

            if (dd()) {
                if (i >= inComming.size()) {

                    return true;
                }

                if (ccPrime()) {
                    System.out.println("cc success");
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

            System.out.println("ccprime" + inComming.get(i));
            if (inComming.get(i).equals("|")) {

                i++;
                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (dd()) {

                    //if file ended out of bound
                    if (i >= inComming.size()) {
                        System.out.println("hello");
                        return true;
                    } else if (ccPrime()) {
                        System.out.println("ccPrime success");

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

                System.out.println("ccPrime success");
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

            System.out.println("dd" + inComming.get(i));
            if (ee()) {

                if (i >= inComming.size()) {
                    System.out.println("hello");
                    return true;
                } else if (ddPrime()) {
                    System.out.println("dd success");
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
            System.out.println("ddprime" + inComming.get(i));

            if (inComming.get(i).equals("^")) {

                i++;
                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (ee()) {

                    //if file ended out of bound
                    if (i >= inComming.size()) {
                        System.out.println("hello");
                        return true;
                    } else if (ddPrime()) {

                        System.out.println("ddPrime success");
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

                System.out.println("ddPrime success");
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

            System.out.println("ee" + inComming.get(i));
            if (c()) {
                if (i >= inComming.size()) {
                    System.out.println("hello");
                    return true;
                } else if (eePrime()) {
                    System.out.println(" ee success");
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
            System.out.println("eeprime" + inComming.get(i));

            if (inComming.get(i).equals("&")) {

                i++;
                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (c()) {

                    //if file ended out of bound
                    if (i >= inComming.size()) {
                        System.out.println("hello");
                        return true;
                    } else if (eePrime()) {

                        System.out.println("eePrime success");
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

                System.out.println(" eePrime success");
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

            System.out.println("c" + inComming.get(i));

            if (e()) {

                //if file ended out of bound
                if (i >= inComming.size()) {
                    System.out.println("hello");
                    return true;
                } else if (cPrime()) {
                    System.out.println("Success c");
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
            System.out.println("cprime" + inComming.get(i));

            if (inComming.get(i).equals("RO")) {

                i++;

                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (e()) {
                    // if last token , out of bound sy bachny ke liye
                    if (i >= inComming.size()) {
                        System.out.println("hello");
                        return true;
                    } else if (cPrime()) {

                        System.out.println(" cPrime success");
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
            //                System.out.println("4hh");
            //                  if((inComming.get(i).equals(")")&&flagForBracket==true)||
            //                     (inComming.get(i).equals(";")&&flagForSemiColon==true   )  ){
            //                    
            //                      System.out.println("aPrime success");
            //                      return true;
            //                  }
            //                
            //            }
            else {
                System.out.println(" cPrime success");

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

            System.out.println("e" + inComming.get(i));

            if (f()) {

                if (i >= inComming.size()) {
                    System.out.println("hello");
                    return true;
                } else if (ePrime()) {
                    System.out.println("e success");
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
            System.out.println("eprime" + inComming.get(i));

            if (inComming.get(i).equals("Add_Sub")) {

                i++;
                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (f()) {

                    // if last token , out of bound sy bachny ke liye
                    if (i >= inComming.size()) {
                        System.out.println("hello");
                        return true;
                    } else if (ePrime()) {

                        System.out.println(" ePrime success");
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
            //                System.out.println("3hh");
            //                  if((inComming.get(i).equals(")")&&flagForBracket==true)||
            //                     (inComming.get(i).equals(";")&&flagForSemiColon==true   )  ){
            //                    
            //                      System.out.println("aPrime success");
            //                      return true;
            //                  }
            //                
            //            }
            else {

                System.out.println("ePrime success");
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

            System.out.println("f" + inComming.get(i));

            if (g()) {

                if (i >= inComming.size()) {
                    System.out.println("hello");
                    return true;
                } else if (fPrime()) {
                    System.out.println(" f success");
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

            System.out.println("fprime" + inComming.get(i));
            if (inComming.get(i).equals("Div_Mod")) {

                i++;
                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (g()) {
                    // if last token , out of bound sy bachny ke liye
                    if (i >= inComming.size()) {
                        System.out.println("hello");
                        return true;
                    } else if (fPrime()) {

                        System.out.println(" fPrime success");
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
            //                System.out.println("hh2");
            //                  if((inComming.get(i).equals(")")&&flagForBracket==true)||
            //                     (inComming.get(i).equals(";")&&flagForSemiColon==true   )  ){
            //                    
            //                      System.out.println("aPrime success");
            //                      return true;
            //                  }
            //                
            //            }
            else {
                System.out.println(" fPrime success");

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

            System.out.println("g" + inComming.get(i));

            if (i()) {

                // if last token , out of bound sy bachny ke liye
                if (i >= inComming.size()) {
                    System.out.println("hello");
                    return true;
                } else if (gPrime()) {
                    System.out.println("success g");
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

            System.out.println("gprime " + inComming.get(i));

            if (inComming.get(i).equals("|")) {

                i++;
                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (i()) {

                    if (gPrime()) {
                        System.out.println("success gPrime");

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

                System.out.println("1hh");

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

                        System.out.println(bracket.size());
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

                    System.out.println("aPrime success");
                    return true;
                } else {
                    errorIndex = i;
                }

            } else {

                System.out.println("success gPrime");
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

            System.out.println("i" + inComming.get(i));

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

                            System.out.println("success i");

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

                i++;
                //for last element
                if (i >= inComming.size()) {

                    return true;
                }

                if (iD1()) {

                    //for last element
                    if (i >= inComming.size()) {

                        return true;
                    }
                    System.out.println("inside(id1)  in i    id<id1> <array> <iprime>" + inComming.get(i));

                    if (array()) {

                        //for last element
                        if (i >= inComming.size()) {
                            return true;

                        }
                        System.out.println("inside(array)  in i    id<id1> <array> <iprime>" + inComming.get(i));
                        if (iPrime()) {

                            // System.out.println("inside(iprime)  in i    id<id1> <array> <iprime>"+inComming.get(i));
                            System.out.println("success i");

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

                if (a()) {

                    if (i >= inComming.size()) {
                        return false;
                    }

                    if (inComming.get(i).equals(")")) {
                        i++;

                        //bracket.push("(");
                        System.out.println("success i");
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
                System.out.println("success i");
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

            System.out.println("iprime" + inComming.get(i));

            if (inComming.get(i).equals("Inc_Dec")) {
                i++;
                System.out.println("success iPrime");

                return true;
            } else if (inComming.get(i).equals(".")
                    || inComming.get(i).equals("(")) {

                if (function_call()) {

                    System.out.println("success iPrime");
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
                System.out.println("success iPrime");

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
            System.out.println("id1 " + inComming.get(i));

            //temp out of boubd na krdy
            int temp = i + 1;
            if (temp >= inComming.size()) {
                temp = i;
            }
            if (inComming.get(i).equals(".") && !inComming.get(temp).equals(":")) {

                i++;

                //for null pointer exception end nh hskta ispy to false
                if (i >= inComming.size()) {
                    System.out.println("hello");
                    return false;
                }

                if (inComming.get(i).equals("ID")) {

                    i++;

                    //for last element
                    if (i >= inComming.size()) {
                        System.out.println("Hello");
                        return true;
                    }
                    if (iD1()) {

                        System.out.println("success iD1");
                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else {
                System.out.println("success iD1");

                return true;
            }

        } else {
            errorIndex = i;
            i++;
            System.out.println(inComming.get(i));
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

                //for index out of bound
                if (i >= inComming.size()) {

                    return false;

                }
                //new         flag2ForSemiColon=flagForSemiColon;
                if (a()) {
                    //new              flagForSemiColon=flag2ForSemiColon;
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

                        if (iD1()) {

                            //new                  flagForSemiColon=false;
                            //for index out of bound
                            if (i >= inComming.size()) {

                                return true;
                            }

                            if (array()) {
                                System.out.println("success  array");
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
                System.out.println("success  array");

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

            System.out.println("function_call" + inComming.get(i));

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

                                System.out.println("success function_call");
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
                    System.out.println("inside(  )) in ( <parameters> <function_Call");
                    //for null poiter
                    if (i >= inComming.size()) {
                        return true;
                    }
                    if (function_call()) {
                        System.out.println("inside(  function call) in ( <parameters> <function_Call");
                        System.out.println("success function_call");
                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }

            } else {
                System.out.println("success function_call");

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

            System.out.println(inComming.get(i));
            if (inComming.get(i).equals("nfloata")
                    || inComming.get(i).equals("nstringa ")
                    || inComming.get(i).equals("nchara")
                    || inComming.get(i).equals("ninta")
                    || inComming.get(i).equals("Inc_Dec")
                    || inComming.get(i).equals("ID")
                    || inComming.get(i).equals("(")) {

                comma.push(",");
                if (a()) {

                    if (comma.size() > 0) {
                        comma.pop();
                    }
                    //for null pointer
                    if (i >= inComming.size()) {
                        return true;
                    }
                    if (opara()) {

                        System.out.println("success parametr");
                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else if (inComming.get(i).equals(")") && !inComming.get(i - 1).equals(",")) {
                i++;
                System.out.println("success parametr");
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

                    System.out.println("success opara");
                    return true;
                } else {
                    errorIndex = i;
                }
            } else {
                i++;
                System.out.println("success opara");
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

            System.out.println("assign" + inComming.get(i));

            i++;

            //for out of bound
            if (i >= inComming.size()) {

                System.out.println("Assignment failed");

                return false;
            }

            if (iD1Assign()) {

                //for out of bound
                if (i >= inComming.size()) {
                    System.out.println("Assignment failed");
                    return false;
                }

                if (arrayAssign()) {

                    //for out of bound
                    if (i >= inComming.size()) {

                        System.out.println("Assignment failed");
                        return false;
                    }

                    if (inComming.get(i).equals("AO") || inComming.get(i).equals("=")) {

                        i++;

                        //for out of bound
                        if (i >= inComming.size()) {

                            System.out.println("Assignment failed");
                            return false;
                        }
                        squareBracket.push("]");
                        if (assignPrime()) {

                            if (i >= inComming.size()) {
                                if (squareBracket.size() > 0) {
                                    squareBracket.pop();
                                    System.out.println("Failed Assignment");
                                }
                                System.out.println("Assignment failed");
                                return false;
                            }
                            if (inComming.get(i).equals("]")) {

                                System.out.println("Success Assignment");
                                return true;
                            } else {
                                errorIndex = i;
                            }
                        } else {
                            if (squareBracket.size() > 0) {
                                squareBracket.pop();
                            }
                            errorIndex = i;
                            System.out.println("Failed Assignment");
                        }
                    } else {
                        failedCfgs.add("Assignment");
                        errorIndex = i;
                        System.out.println("Failed Assignment");
                    }
                } else {
                    failedCfgs.add("Assignment");
                    errorIndex = i;
                    System.out.println("Failed Assignment");
                }
            } else {
                failedCfgs.add("Assignment");
                errorIndex = i;
                System.out.println("Failed Assignment");
            }
        } else {
            failedCfgs.add("Assignment");
            errorIndex = i;
            System.out.println("Failed Assignment");
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

            System.out.println("assignPrime" + inComming.get(i));

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
                System.out.println("Success AssignPrime");
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

            System.out.println("oexp " + inComming.get(i));
            if (inComming.get(i).equals("new")) {

                i++;
                //for out of bound
                if (i >= inComming.size()) {

                    return false;
                }
                if (inComming.get(i).equals("ID")) {

                    i++;
                    //for out of bound
                    if (i >= inComming.size()) {

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

                            System.out.println("Success oexp");
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
            } else if (a()) {

                System.out.println("Success oexp");
                return true;
            } else {
                errorIndex = i;
            }
        }
        return false;
    }

    Boolean iD1Assign() {
        if (inComming.get(i).equals(".")
                || inComming.get(i).equals(":")
                || inComming.get(i).equals("AO") || inComming.get(i).equals("=")) {

            System.out.println("iD1Assign " + inComming.get(i));

            if (inComming.get(i).equals(".")) {

                i++;

                //for null pointer exception end nh hskta ispy to false
                if (i >= inComming.size()) {
                    System.out.println("hello");
                    return false;
                }

                if (inComming.get(i).equals("ID")) {

                    i++;

                    //for last element
                    if (i >= inComming.size()) {
                        System.out.println("Hello");
                        return true;
                    }
                    if (iD1Assign()) {

                        System.out.println("success iD1assgnment");
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

            System.out.println("arrayAssign " + inComming.get(i));

            if (inComming.get(i).equals(":")) {

                semiColon.push(":");
                i++;

                //for index out of bound
                if (i >= inComming.size()) {

                    return false;

                }

                if (a()) {

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

                        if (iD1Assign()) {

                            //for index out of bound
                            if (i >= inComming.size()) {

                                return true;
                            }

                            if (arrayAssign()) {
                                System.out.println("success  arrayassign");
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
                System.out.println("Success arrayAssign");
                return true;
            }

        } else {

        }

        return false;
    }

    ////////////                        CLASS START HE YAHAN
    boolean Class() {
        if (inComming.get(i).equals("AM")
                || inComming.get(i).equals("stable")
                || inComming.get(i).equals("absolute")
                || inComming.get(i).equals("class")) {

            System.out.println("Class " + inComming.get(i));
            if (am()) {

                if (stable()) {

                    if (Final()) {

                        if (inComming.get(i).equals("class")) {

                            i++;
                            //for null pointer
                            if (i >= inComming.size()) {
                                System.out.println("Class failed");
                                return false;
                            }

                            if (inComming.get(i).equals("ID")) {

                                i++;
                                //for null pointer
                                if (i >= inComming.size()) {
                                    System.out.println("class failed");
                                    return false;
                                }

                                if (parent()) {

                                    if (inComming.get(i).equals("/")) {
                                        i++;

                                        if (bodyClass()) {

                                            if (inComming.get(i).equals("\\")) {

                                                System.out.println("Success Class");
                                                return true;
                                            } else {
                                                failedCfgs.add("Class");
                                                errorIndex = i;
                                                System.out.println("Class FAiled");
                                            }
                                        } else {
                                            failedCfgs.add("Class");
                                            errorIndex = i;
                                            System.out.println("Class FAiled");
                                        }
                                    } else {
                                        failedCfgs.add("Class");
                                        errorIndex = i;
                                        System.out.println("Class FAiled");
                                    }
                                } else {
                                    failedCfgs.add("Class");
                                    errorIndex = i;
                                    System.out.println("Class FAiled");
                                }
                            } else {
                                failedCfgs.add("Class");
                                errorIndex = i;
                                System.out.println("Class FAiled");
                            }
                        } else {
                            failedCfgs.add("Class");
                            errorIndex = i;
                            System.out.println("Class FAiled");
                        }
                    } else {
                        failedCfgs.add("Class");
                        errorIndex = i;
                        System.out.println("Class FAiled");
                    }
                } else {
                    failedCfgs.add("Class");
                    errorIndex = i;
                    System.out.println("Class FAiled");
                }
            } else {
                failedCfgs.add("Class");
                errorIndex = i;
                System.out.println("Class FAiled");
            }
        } else {
            failedCfgs.add("Class");
            errorIndex = i;
            System.out.println("class Failed");
        }

        return false;
    }

    private boolean am() {

        if (inComming.get(i).equals("absolute")
                || inComming.get(i).equals("AM")
                || inComming.get(i).equals("stable")
                || inComming.get(i).equals("class")) {

            if (inComming.get(i).equals("AM")) {

                i++;
                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                System.out.println("AM success");

                return true;
            } else {
                System.out.println("AM success");
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

                i++;
                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                System.out.println("stable success");

                return true;
            } else {
                System.out.println("stable success");
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

                i++;
                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                System.out.println("absolute success");

                return true;
            } else {
                System.out.println("absolute success");
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
            System.out.println("parent " + inComming.get(i));

            if (inComming.get(i).equals("extends")
                    || inComming.get(i).equals("implements")) {

                if (Extends()) {

                    if (Implements()) {

                        System.out.println("parent success");
                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else {
                System.out.println("parent success");
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

            System.out.println("extends " + inComming.get(i));

            if (inComming.get(i).equals("extends")) {

                i++;

                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (inComming.get(i).equals("ID")) {

                    i++;
                    //for null pointer
                    if (i >= inComming.size()) {
                        return true;
                    }

                    if (iD1Class()) {

                        System.out.println("extends success");
                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }

            } else {
                System.out.println("extends succes");
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

            System.out.println("implements " + inComming.get(i));

            if (inComming.get(i).equals("implements")) {

                i++;
                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (inComming.get(i).equals("ID")) {

                    i++;
                    //for null pointer
                    if (i >= inComming.size()) {
                        return true;
                    }

                    if (iD1Class()) {

                        if (listClass()) {

                            System.out.println("implemetns success");
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
                System.out.println("implements succes");
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

            System.out.println("iD1Class " + inComming.get(i));

            if (inComming.get(i).equals(".")) {

                if(1==1){
                    return false;      // LASTCHANGE to get rid of . from implements and extends this is called in both imp and ext so chnging it from here will keep from every where else
                }
                i++;

                //for null pointer exception end nh hskta ispy to false
                if (i >= inComming.size()) {
                    System.out.println("hello");
                    return false;
                }

                if (inComming.get(i).equals("ID")) {

                    i++;

                    //for last element
                    if (i >= inComming.size()) {
                        System.out.println("Hello");
                        return true;
                    }
                    if (iD1Class()) {

                        System.out.println("success iD1class");
                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else {
                System.out.println("success iD1class");
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

            System.out.println("listClass " + inComming.get(i));

            if (inComming.get(i).equals(",")) {

                i++;

                //for null pointer exception end nh hskta ispy to false
                if (i >= inComming.size()) {
                    System.out.println("hello");
                    return false;
                }

                if (inComming.get(i).equals("ID")) {

                    i++;

                    //for last element
                    if (i >= inComming.size()) {
                        System.out.println("Hello");
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
                            System.out.println("success lisclass");
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
                System.out.println("success listclass");
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

            System.out.println("bodyClass " + inComming.get(i));

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

                        System.out.println("success bodyclass");
                        return true;

                    } else {
                        //    errorIndex = i;
                    }
                } else {
                    //errorIndex = i;
                }
            } else {
                System.out.println("success bodyclass");
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

            System.out.println("s_stClass " + inComming.get(temp));

            if (amS_ST()) {

                if (s_stLeftFactor()) {

                    System.out.println("s_stclass success");
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

            System.out.println("m_stClass " + inComming.get(i));

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

                        System.out.println("m_stclass success");
                        return true;
                    } else {
                        //     errorIndex = i;
                    }

                } else {
                    //  errorIndex = i;
                }

            } else {
                System.out.println("m_stClass success");
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

            System.out.println("ams_stClass " + inComming.get(temp));
            if (inComming.get(temp).equals("AM")) {

                temp++;

                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                System.out.println("AMs_St success");

                return true;
            } else {
                System.out.println("AMs_St success");
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

            System.out.println("astables_stClass " + inComming.get(temp));
            if (inComming.get(temp).equals("stable")) {

                temp++;
                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                System.out.println("stables_ST success");

                return true;
            } else {
                System.out.println("stables_ST success");
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

            System.out.println("absolutes_stClass " + inComming.get(temp));
            if (inComming.get(temp).equals("absolute")) {

                temp++;
                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                System.out.println("absoluteS_ST success");

                return true;
            } else {
                System.out.println("absoluteS_ST success");
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

            System.out.println("s_stleftfctr  " + inComming.get(temp));
            if (inComming.get(temp).equals("ID")) {

                temp++;
                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }
                if (s_stLeftFactorPrimePrime()) {

                    System.out.println("SST left factor success");
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

                            System.out.println("s_stLEftFActor success");
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

            System.out.println("sstLeftfctrPrime " + inComming.get(temp));
            if (inComming.get(temp).equals("ID")) {
                temp++;

                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }
                if (s_stLeftFactorPrimePrime()) {

                    System.out.println("s_stLeftFactorPrime success");
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

                    System.out.println("s_stLeftFactorPrime success");
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
                    System.out.println("sending for function declaration");
                    System.out.println("s_stLeftFactorPrime success");
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
                    System.out.println("s_stLeftFactorPrime success" + inComming.get(i));
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
                || inComming.get(temp).equals("=")||
                inComming.get(temp).equals("Inc_Dec")) {

            System.out.println("sstLeftfctrPrimePrime " + inComming.get(temp));
            if(decisionForFunDecl()){
                
                if(i>=inComming.size()){
                    return false;
                }
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
//                System.out.println("man hun "+inCommingVp.get(i));
//                //calling declaration
//                if (xDecl()) {     //LASTCHANGE by doing this functions with id return type are allowed
//                    i++;
//                    if (i >= inComming.size()) {
//                        return false;
//                    }
//                    System.out.println("sending for declaration"+inCommingVp.get(i));
//                    System.out.println("s_stLeftFactorPrimePrime success");
//                    return true;
//                } else {
//                    //   errorIndex = i;
//                }
//            }   LASTCHANGE ENDING HERE
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
                    System.out.println("sending for constructer declaration");
                    System.out.println("s_stLeftFactorPrimePrime success");
                    return true;
                } else {
                    //    errorIndex = i;
                }
            } else if (inComming.get(temp).equals(".")
                    || inComming.get(temp).equals(":")
                    || inComming.get(temp).equals("AO")
                    || inComming.get(temp).equals("=")||
                inComming.get(temp).equals("Inc_Dec")) //errorIndex = i;
            { 
                //assignment
                if (xDecl()) {
                    i++;
                    if (i >= inComming.size()) {
                        return false;
                    }
                    System.out.println("Assignment success");
                    return true;

                }
            }
        } else {
            //  errorIndex = i;
        }

        return false;
    }

    boolean decisionForFunDecl() {
        System.out.println("decisionfor funcdeccl " + inComming.get(temp));
        if (inComming.get(temp).equals("ID")) {
            System.out.println("decisionfor funcdeccl " + inComming.get(temp));
            temp++;
            if (checkBracket()) {
                System.out.println("decision success");
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
                System.out.println("function declaration" + inComming.get(i));
                //  System.out.println("checkBracket success" + inComming.get(i + 1));
                return true;
            } else {
                //      errorIndex = i;
            }
        } else if (inComming.get(temp).equals("=") || inComming.get(temp).equals("]")||inComming.get(temp).equals(":")) {
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
                System.out.println("sending for declaration");
                System.out.println("checkBracket success");
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
            System.out.println("constructorDeclarationClass " + inComming.get(i));

            if (inComming.get(i).equals("AM")) {
                i++;
                //null poiner 
                if (i >= inComming.size()) {
                    return false;
                }

            }

            if (inComming.get(i).equals("ID")) {
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
                    if (para_decl()) {
                        if (inComming.get(i).equals(")")) {
                            i++;
                            if (inComming.get(i).equals("/")) {

                                i++;
                                //null poiner 
                                if (i >= inComming.size()) {
                                    return false;
                                }
                                    flagForFunctionVariable=true; //if true means variable shud nt have AM 
                                if (body()) {
                                    flagForFunctionVariable=false;
                                    
                                    if (inComming.get(i).equals("\\")) {
                                        System.out.println("Constructor declaration Class success ");
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
            System.out.println("functionDeclarationClass " + inComming.get(i));
            if (STABLE_norm()) {
                if (FINAL_norm()) {
                    if (inComming.get(i).equals("ID") || inComming.get(i).equals("DT") || inComming.get(i).equals("empty")) {
                        i++;
                        //null poiner 
                        if (i >= inComming.size()) {
                            return false;
                        }
                        if (inComming.get(i).equals("ID")) {
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
                                if (para_decl()) {
                                    if (inComming.get(i).equals(")")) {
                                        i++;
                                        if (inComming.get(i).equals("/")) {

                                            i++;
                                            //null poiner 
                                            if (i >= inComming.size()) {
                                                return false;
                                            }
                                            flagForFunctionVariable=true; // AM will only be allowed in declaration if this flag is false
                                            if (body()) {
                                                  flagForFunctionVariable=false;
                                                if (inComming.get(i).equals("\\")) {
                                                    System.out.println("Function declaration Class success");
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
            System.out.println("AM1 norm " + inComming.get(i));
            if (inComming.get(i).equals("AM")) {
                i++;

                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                System.out.println("AM_norm succcess");
                return true;
            } else if (inComming.get(i).equals("stable") || inComming.get(i).equals("absolute")
                    || inComming.get(i).equals("DT") || inComming.get(i).equals("ID") || inComming.get(i).equals("empty")) {
                System.out.println("AM_norm succcess");
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
            System.out.println("stable norm " + inComming.get(i));
            if (inComming.get(i).equals("stable")) {
                i++;
                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }
                System.out.println("stable_norm succcess");
                return true;
            } else if (inComming.get(i).equals("absolute")
                    || inComming.get(i).equals("DT") || inComming.get(i).equals("ID") || inComming.get(i).equals("empty")) {
                System.out.println("stable_norm succcess");
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
            System.out.println("absolute " + inComming.get(i));

            if (inComming.get(i).equals("absolute")) {
                i++;
                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                System.out.println("absolute_norm succcess");
                return true;

            } else if (inComming.get(i).equals("DT") || inComming.get(i).equals("ID") || inComming.get(i).equals("empty")) {
                System.out.println("absolute_norm succcess");
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

            System.out.println("Class " + inComming.get(i));
            if (amMainClass()) {

                if (FinalMainClass()) {

                    if (inComming.get(i).equals("novitachocolate")) {

                        i++;
                        //for null pointer
                        if (i >= inComming.size()) {
                            System.out.println("MainClass failed");
                            return false;
                        }

                        if (inComming.get(i).equals("ID")) {

                            i++;
                            //for null pointer
                            if (i >= inComming.size()) {
                                System.out.println("Mainclass failed");
                                return false;
                            }

                            if (parent()) {

                                if (inComming.get(i).equals("/")) {
                                    i++;
                                    //for null pointer
                                    if (i >= inComming.size()) {
                                        System.out.println("Mainclass faield");
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

                                            System.out.println("Success MainClass");
                                            return true;
                                        }
                                    } else {
                                        failedCfgs.add("Main Class");
                                        errorIndex = i;
                                        System.out.println("MainClass FAiled");
                                    }
                                } else {
                                    failedCfgs.add("Main Class");
                                    errorIndex = i;
                                    System.out.println("MainClass FAiled");
                                }
                            } else {
                                failedCfgs.add("Main Class");
                                errorIndex = i;
                                System.out.println("MainClass FAiled");
                            }
                        } else {
                            failedCfgs.add("Main Class");
                            errorIndex = i;
                            System.out.println("MainClass FAiled");
                        }
                    } else {
                        failedCfgs.add("Main Class");
                        errorIndex = i;
                        System.out.println("MainClass FAiled");
                    }
                } else {
                    failedCfgs.add("Main Class");
                    errorIndex = i;
                    System.out.println("MainClass FAiled");
                }

            } else {
                failedCfgs.add("Main Class");
                errorIndex = i;
                System.out.println("MainClass FAiled");
            }
        } else {
            failedCfgs.add("Main Class");
            errorIndex = i;
            System.out.println("Mainclass Failed");
        }

        return false;
    }

    private boolean amMainClass() {

        if (inComming.get(i).equals("absolute")
                || inComming.get(i).equals("AM")
                || inComming.get(i).equals("novitachocolate")) {

            if (inComming.get(i).equals("AM")) {

                i++;
                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                System.out.println("AMMainClass success");

                return true;
            } else {
                System.out.println("AMMainClass success");
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

                i++;
                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                System.out.println("FinalMAinClass success");

                return true;
            } else {
                System.out.println("FinalMAinClass success");
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

            System.out.println("bodyMainClass " + inComming.get(i));

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

                        System.out.println("success bodyMainclass");
                        return true;

                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else {
                System.out.println("success bodyMainclass");
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

            System.out.println("s_stMainClass " + inComming.get(temp));

            if (amS_ST()) {

                if (s_stLeftFactorMainClass()) {

                    System.out.println("s_stclass success");
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

            System.out.println("m_stMainClass " + inComming.get(i));

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

                        System.out.println("m_stMainclass success");
                        return true;
                    } else {
                        errorIndex = i;
                    }

                } else {
                    errorIndex = i;
                }

            } else {
                System.out.println("m_stMainClass success");
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

            System.out.println("s_stleftfctrMainClass  " + inComming.get(temp));
            if (inComming.get(temp).equals("absolute")
                    || inComming.get(temp).equals("DT")
                    || inComming.get(temp).equals("empty")
                    || inComming.get(temp).equals("stable")
                    || inComming.get(temp).equals("class")) {

                if (stableS_St()) {

                    if (FinalS_ST()) {

                        if (s_stLeftFactorPrimeMainClass()) {

                            System.out.println("s_stLEftFActorMainClass success");
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

                    System.out.println("SST left factorMainClass success");
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

            System.out.println("sstLeftfctrPrimeMainCLass " + inComming.get(temp));
            if (inComming.get(temp).equals("ID")) {
                temp++;

                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }
                if (s_stLeftFactorPrimePrimeMainClass()) {

                    System.out.println("s_stLeftFactorPrimeMainClass success");
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

                    System.out.println("s_stLeftFactorPrimeMainClass success");
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

                    System.out.println("s_stLeftFactorPrimeMainClass success");
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

                    System.out.println("s_stLeftFactorPrimeMainClass success" + inComming.get(i));
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
                || inComming.get(i).equals("AO")||
                inComming.get(i).equals("Inc_Dec")) {

            System.out.println("sstLeftfctrPrimePrimeMainClass " + inComming.get(temp));
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
                 if(i>=inComming.size()){
                     return false;
                 }
                    System.out.println("sending for declaration");
                    System.out.println("s_stLeftFactorPrimePrimeMainClass success");
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
                    System.out.println("sending for constructer declaration");
                    System.out.println("s_stLeftFactorPrimePrimeMainClass success");
                    return true;
                } else {
                    //         errorIndex = i;
                }
            } else //calling assignment and declaration
             if (xDecl()) {
                    i++;
                    if (i >= inComming.size()) {
                        return false;
                    }
                    return true;
                } //        errorIndex = i;
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
                System.out.println("decisionMAinClass success");
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
                System.out.println("send to mainfunction call");
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
                System.out.println("Send for function call1");
                System.out.println("mainOrNormalFunction success");
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
                System.out.println("Send for main function call");
                System.out.println("mainOrNormalFunction success");
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
            System.out.println("MainfunctionDeclaration " + inComming.get(i));
            if (STABLE_norm()) {
                if (FINAL_norm()) {
                    if (inComming.get(i).equals("empty")) {
                        i++;
                        //null poiner 
                        if (i >= inComming.size()) {
                            return false;
                        }
                        if (inComming.get(i).equals("novita")) {
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
                                if (para_decl()) {
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
                                                    System.out.println("Main Function declaration  success");
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

        if (inComming.get(i).equals("interface")) {
            System.out.println("Interfcae " + inComming.get(i));
            i++;
            //null pointer
            if (i >= inComming.size()) {
                return false;
            }

            if (inComming.get(i).equals("ID")) {
                i++;
                //null pointer
                if (i >= inComming.size()) {
                    System.out.println("Failed Interface");
                    return false;
                }
                if (extendsInterface()) {

//                    i++;
//                    //null pointer
//                    if (i >= inComming.size()) {
//                        System.out.println("Failed interface");
//                        return false;
//                    }
                    if (inComming.get(i).equals("/")) {

                        i++;
                        //null pointer
                        if (i >= inComming.size()) {
                            System.out.println("Failed Interface");
                            return false;
                        }
                        if (bodyInterface()) {

                            if (inComming.get(i).equals("\\")) {

                                System.out.println("Success Interface");
                                return true;
                            } else {
                                failedCfgs.add("Interface");
                                errorIndex = i;
                            }
                        } else {
                            failedCfgs.add("Interface");
                            errorIndex = i;
                            System.out.println("Failed Interface");
                        }
                    } else {
                        failedCfgs.add("Interface");
                        errorIndex = i;
                        System.out.println("Failed Interface");
                    }
                } else {
                    failedCfgs.add("Interface");
                    errorIndex = i;
                    System.out.println("Failed Interface");
                }
            } else {
                failedCfgs.add("Interface");
                errorIndex = i;
                System.out.println("Failed Interface");
            }
        } else {
            failedCfgs.add("Interface");
            errorIndex = i;
            System.out.println("Failed Interface");
        }
        return false;
    }

    private boolean extendsInterface() {

        if (inComming.get(i).equals("extends")
                || inComming.get(i).equals("/")) {

            System.out.println("extendsInterface " + inComming.get(i));

            if (inComming.get(i).equals("extends")) {

                i++;

                //for null pointer
                if (i >= inComming.size()) {
                    return false;
                }

                if (inComming.get(i).equals("ID")) {

                    i++;
                    //for null pointer
                    if (i >= inComming.size()) {
                        return true;
                    }

                    if (iD1Interface()) {

                        if (listInterface()) {

                            System.out.println("extendsInterface success");
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
                System.out.println("extendsInterface succes");
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

            System.out.println("listInterface " + inComming.get(i));

            if (inComming.get(i).equals(",")) {

                i++;

                //for null pointer exception end nh hskta ispy to false
                if (i >= inComming.size()) {
                    System.out.println("hello");
                    return false;
                }

                if (inComming.get(i).equals("ID")) {

                    i++;

                    //for last element
                    if (i >= inComming.size()) {
                        System.out.println("Hello");
                        return true;
                    }
                    if (iD1Interface()) {

                        if (listInterface()) {
                            System.out.println("success listInterfcace");
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
                System.out.println("success listInterfcce");
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
          //      || inComming.get(i).equals("interface")   LASTCHANGE to get rid of interface inner interface
                || inComming.get(i).equals("\\")
                || inComming.get(i).equals("empty")) {

            System.out.println("bodyInterface " + inComming.get(i));

            if (inComming.get(i).equals("ID")
                    || inComming.get(i).equals("DT")
          //          || inComming.get(i).equals("interface")   LASTCHANGE to get rid of interface inner interface
                    || inComming.get(i).equals("empty")) {

                temp = i;
                if (s_stInterface()) {

                    if (m_stInterface()) {

                        System.out.println("success bodyInterface");
                        return true;

                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else {
                System.out.println("success bodyInterface");
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

            System.out.println("iD1Interface " + inComming.get(i));

            if (inComming.get(i).equals(".")) {
                
                if(1==1)
                return false;              // LASTCHANGE to get rid of id.id in extends of interface  if is useless means when return statement is removed if should also remove
                
                i++;

                //for null pointer exception end nh hskta ispy to false
                if (i >= inComming.size()) {
                    System.out.println("hello");
                    return false;
                }

                if (inComming.get(i).equals("ID")) {

                    i++;

                    //for last element
                    if (i >= inComming.size()) {
                        System.out.println("Hello");
                        return true;
                    }
                    if (iD1Interface()) {

                        System.out.println("success iD1Interface");
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
         //       || inComming.get(temp).equals("interface")    LASTCHANGE to get rid of interface inner interface
                || inComming.get(temp).equals("empty")) {

            System.out.println("s_stInterface " + inComming.get(temp));

            if (inComming.get(i).equals("interface")) {

                if (Interface()) {
                    i++;
                    if (i >= inComming.size()) {
                        return false;
                    }
                    System.out.println("s_stInterfae success");
                    return true;
                } else {
                    errorIndex = i;
                }
            } else if (inComming.get(i).equals("DT")
                    || inComming.get(i).equals("ID")) {

                temp++;

                if (s_stForInterface()) {

                    System.out.println("s_stInterface success");
                    return true;
                } else {
                    errorIndex = i;
                }
            } else {
                errorIndex = i;
            }
            if (inComming.get(i).equals("empty")) {

                if (functionDeclInterface()) {
                    System.out.println(inComming.get(i));
                    System.out.println("Sending for interface function declration");
                    System.out.println("s_stInterfae success");
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
         //       || inComming.get(i).equals("interface")      LASTCHANGE to get rid of interface inner interface
                || inComming.get(i).equals("empty")
                || inComming.get(i).equals("\\")) {

            System.out.println("m_stInterface " + inComming.get(i));

            if (inComming.get(i).equals("ID")
                    || inComming.get(i).equals("DT")
                    || inComming.get(i).equals("interface")
                    || inComming.get(i).equals("empty")) {

                temp = i;
                if (s_stInterface()) {

                    if (m_stInterface()) {

                        System.out.println("m_stInterface success");
                        return true;
                    } else {
                        errorIndex = i;
                    }

                } else {
                    errorIndex = i;
                }

            } else {
                System.out.println("m_stInterface success");
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
            System.out.println(inComming.get(temp));
            if (decision()) {
                System.out.println("Success decision");
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

            System.out.println("Function declaration " + inComming.get(i));
            i++;
            //null pointer 
            if (i >= inComming.size()) {
                return false;
            }
            if (inComming.get(i).equals("ID")) {
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

                    if (para_decl()) {

                        if (inComming.get(i).equals(")")) {
                            i++;
                            //null pointer 
                            if (i >= inComming.size()) {
                                return false;
                            }

                            if (inComming.get(i).equals("]")) {
                                i++;
                                //null pointer 
                                if (i >= inComming.size()) {
                                    return false;
                                }
                                System.out.println("Interface Function decclrataion success" + inComming.get(i));
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
            System.out.println("Declaaration x " + inComming.get(i));
            looptemp = i;
            if (AM1_Decl()) {
                if (STABLE_Decl()) {  //decl
                    if (FINAL_Decl()) {
                        System.out.println("hello");
                        if (DT_loop()) {
                            System.out.println("hello");
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
                || inComming.get(temp).equals("=")||
                inComming.get(temp).equals(":")) {
            if (inComming.get(temp).equals("(")) {

                if (functionDeclInterface()) {
//                    i++;
//                    if(i>=inComming.size()){
//                        return true;
//                    }
                    System.out.println("sending for interface function declaartion");
                    System.out.println("Success decision");
                    return true;
                } else {
                    errorIndex = i;
                }
            } else if (inComming.get(temp).equals("=")|| inComming.get(temp).equals(":")) {

                if (variableDeclarationInterface()) {
                    i++;
                    if (i >= inComming.size()) {
                        return true;
                    }
                    System.out.println("sending for interface  declaration");
                    System.out.println("Success decision");
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
        if (inComming.get(i).equals(":") || inComming.get(i).equals("=") || inComming.get(i).equals(",")  ) {
            System.out.println("arr_decl " + inComming.get(i) +Roshaan);
            if (inComming.get(i).equals(":")) {
                i++;
                semiColon.push(":");
                if (a()) {
                    if (inComming.get(i).equals(";")) {
                        i++;
                        Roshaan++;
                        if (arr_VariableInterface()) {
                            return true;
                        }
                    }
                }
            } else if (inComming.get(i).equals("=") || inComming.get(i).equals(",") ) {
                return true;
            }

        } else if (inComming.get(i).equals(")")) {

            return true;
        }
        return false;
    }
     
      boolean assign1VariableInterface() {
          Roshaan=0;
        if (inComming.get(i).equals("ID")) {
            System.out.println("assign 1 Interface " + inComming.get(i));
            if (inComming.get(i).equals("ID")) {
                i++;
                //  if(ID1_decl()){
                if (arr_VariableInterface()) {
                    if (assign11()) {
                        System.out.println("assign 1 success");
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
            if (DT3()) {
                if (inComming.get(i).equals("ID")) {
                    i++;
                    if (i >= inComming.size()) {
                        System.out.println("para_deccInterface failed");
                        return false;
                    }

                    if (arr()) {
                        if (oparaInterfaceFuncDecl()) {
                            System.out.println("para_deccInterface succcess");
                            return true;
                        } else {
                            errorIndex = i;
                            System.out.println("para_decclInterface failed");
                        }
                    } else {
                        errorIndex = i;
                        System.out.println("para_decclInterface failed");
                    }
                } else {
                    errorIndex = i;
                    System.out.println("para_decclInterface failed");
                }
            } else {
                errorIndex = i;
                System.out.println("para_decclInterface failed");
            }
        } else if (inComming.get(i).equals(")")) {
            System.out.println("para_deccInterface succcess");
            return true;
        }
        return false;
    }

    boolean DT3() {
        if (inComming.get(i).equals("DT") || inComming.get(i).equals("ID")) {
            System.out.println("T2InterfaceDecl " + inComming.get(i));
            if (inComming.get(i).equals("DT") || inComming.get(i).equals("ID")) {
                i++;
                if (i >= inComming.size()) {
                    return false;
                }
                System.out.println("DT3 success");
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
            System.out.println("arrInterfaceDecl " + inComming.get(i));
            if (inComming.get(i).equals(":")) {
                i++;
                if (i >= inComming.size()) {
                    return false;
                }
                if (inComming.get(i).equals(";")) {
                    i++;

                    if (arr()) {
                        System.out.println("arr success");
                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else if (inComming.get(i).equals(",")) {
                System.out.println("arrInterfaceDecl " + inComming.get(i));
                System.out.println("arr success");
                return true;
            } else {
                errorIndex = i;
            }

        } else if (inComming.get(i).equals(")")) {
            System.out.println("arrInterfaceDecl " + inComming.get(i));
            System.out.println("arr success");
            return true;
        } else {
            errorIndex = i;
        }
        return false;
    }

    boolean oparaInterfaceFuncDecl() {
        if (inComming.get(i).equals(",")) {
            System.out.println("oparaInterfaceDecl " + inComming.get(i));
            if (inComming.get(i).equals(",")) {
                i++;
                if (i >= inComming.size()) {
                    return false;
                }
                if (DT3()) {
                    if (inComming.get(i).equals("ID")) {
                        i++;
                        System.out.println(inComming.get(i));
                        if (i >= inComming.size()) {
                            return false;
                        }
                        if (arr()) {
                            if (oparaInterfaceFuncDecl()) {
                                System.out.println("oparaIntDecl success");
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
            System.out.println("oparaInterfaceDecl " + inComming.get(i));
            return true;
        }
        return false;
    }
    //Salman ka code for para decl is ending here
    ///yahan interface function declaration ends horh he

    /////////// PAKAGE /////////////////
    boolean Pakage() {

        if (inComming.get(temp).equals("pakage")) {
            System.out.println("Pakage " + inComming.get(i));
            i++;
            //null pointer
            if (i >= inComming.size()) {
                System.out.println("Pakage failed");
                return false;
            }

            if (inComming.get(i).equals("ID")) {

                i++;
                //null pointer
                if (i >= inComming.size()) {
                    System.out.println("Pakage failed");
                    return false;
                }
                if (iD1Pakage()) {

                    //null pointer
                    if (i >= inComming.size()) {

                        System.out.println("Pakage success");

                        return true;

                    }
                    if (inComming.get(i).equals("]")) {

                        i++;
                        //null pointer
                        if (i >= inComming.size()) {
                            System.out.println("Pakage success");

                            return true;

                        }
                        if (Import()) {
                            //  System.out.println(inComming.get(i));
                            //null pointer

                            if (i >= inComming.size()) {
                                System.out.println("Pakage success");

                                return true;

                            }
                            if (bodyPakage()) {

                                System.out.println("Success Pakage");
                                return true;
                            } else {
                                failedCfgs.add("Pakage");
                                errorIndex = i;
                                System.out.println("Pakage Failed");
                            }
                        } else {
                            failedCfgs.add("Pakage");
                            errorIndex = i;
                            System.out.println("Pakage Failed");
                        }
                    } else {
                        failedCfgs.add("Pakage");
                        errorIndex = i;
                        System.out.println("Pakage Failed");
                    }
                } else {
                    failedCfgs.add("Pakage");
                    errorIndex = i;
                    System.out.println("Pakage Failed");
                }

            } else {
                failedCfgs.add("Pakage");
                errorIndex = i;
                System.out.println("Pakage Failed");
            }
        } else {
            failedCfgs.add("Pakage");
            errorIndex = i;
            System.out.println("Pakage Failed");
        }
        return false;
    }

    boolean iD1Pakage() {

        if (inComming.get(i).equals(".")
                || inComming.get(i).equals("]")) {

            System.out.println("iD1Pakage " + inComming.get(i));

            if (inComming.get(i).equals(".")) {

                i++;

                //for null pointer exception end nh hskta ispy to false
                if (i >= inComming.size()) {
                    System.out.println("hello");
                    return false;
                }

                if (inComming.get(i).equals("ID")) {

                    i++;

                    //for last element
                    if (i >= inComming.size()) {
                        System.out.println("Helloo");
                        return false;
                    }
                    if (iD1Pakage()) {

                        System.out.println("success iD1Pakage");
                        return true;
                    } else {
                        errorIndex = i;
                    }
                } else {
                    errorIndex = i;
                }
            } else {
                System.out.println("success iD1Pakage");
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

            System.out.println("Import " + inComming.get(i));
            if (inComming.get(i).equals("import")) {

                i++;
                //null pointer
                if (i >= inComming.size()) {
                    System.out.println("Import failed");
                    return false;
                }

                if (stablePakage()) {

                    if (inComming.get(i).equals("novita")) {

                        i++;
                        //null pointer
                        if (i >= inComming.size()) {
                            System.out.println("Import failed");
                            return false;
                        }

                        if (iD1Pakage()) {

                            //null pointer
                            if (i >= inComming.size()) {
                                System.out.println("Pakage success");

                                return false;

                            }

                            if (inComming.get(i).equals("]")) {
                                i++;
                                //null pointer
                                if (i >= inComming.size()) {
                                    //System.out.println("Pakage success");

                                    return true;

                                }
                                if (Import()) {

                                    System.out.println("Success Import");
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
                System.out.println("Import success");
                return true;
            }
        }

        return false;
    }

    boolean bodyPakage() {
        if (inComming.get(i).equals("absolute")
                || inComming.get(i).equals("novitachocolate")
                || inComming.get(i).equals("AM")) {
            System.out.println("bodyPakage " + inComming.get(i));
            if (mainClass()) {

                i++;
                if (i >= inComming.size()) {
                    System.out.println("sucess bodyPakage");
                    return true;
                }
                if (CI()) {
                    System.out.println("bodyPakage success");
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
            System.out.println("stablePkge " + inComming.get(i));
            if (inComming.get(i).equals("stable")) {
                i++;
                //null pointer
                if (i >= inComming.size()) {
                    return false;
                }
                System.out.println("Stable succcess");
                return true;
            } else {
                System.out.println("stable success");
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

            System.out.println("CI " + inComming.get(i));
            if (inComming.get(i).equals("$")) {
                System.out.println("CI success");
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

                    System.out.println("Success CI");
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

            System.out.println("s_stPakage " + inComming.get(i));
            if (inComming.get(i).equals("AM")
                    || inComming.get(i).equals("stable")
                    || inComming.get(i).equals("absolute")
                    || inComming.get(i).equals("class")) {

                if (Class()) {

                    i++;
                    if (i >= inComming.size()) {
                        System.out.println("sucess bodyPakage");
                        return true;
                    }
                    System.out.println("s_stPakage success" + inComming.get(i));
                    return true;
                } else {
                    errorIndex = i;
                }
            } else if (Interface()) {
                i++;
                if (i >= inComming.size()) {
                    System.out.println("sucess bodyPakage");
                    return true;
                }
                System.out.println("s_stPakage success");
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
            System.out.println("m_stPakage " + inComming.get(i));
            if (inComming.get(i).equals("$")) {
                System.out.println("m_stPakage success");
                return true;
            } else if (s_stPakage()) {

                // i++;
                if (i >= inComming.size()) {
                    System.out.println("sucess m_stPakage");
                    return true;
                }

                if (m_stPakage()) {

                    System.out.println("Success m_stPakage");
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
            System.out.println("Declaaration x " + inComming.get(i));
            looptemp = i;
            if (AM1_Decl()) {
                System.out.println("hello1");
                if (STABLE_Decl()) {
                    System.out.println("Hello2"); //decl
                    if (FINAL_Decl()) {
                        System.out.println("hello");
                        if (DT_loop()) {
                            System.out.println("hello");
                            if (assign1()) {

                                if (/*list_decl()*/1==1) {
                                    if (inComming.get(i).equals("]")) {
                                        //    i++;
                                        return true;

                                    }
                                }
                            }
                        } else //  System.out.println("tt");
                        {
                              if (assign()) {
                                // i++;
                                return true;
                            }
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
                        if(inComming.get(i).equals(")")){
                            bracket.pop();
                        }
                        if (parameters()) {
                            System.out.println(""+inComming.get(i));
                            if (inComming.get(i).equals("]")) {
                               
                            
                                      i++;
                                    return true;
                                 
                            } else {
                                i = looptemp;
                            }
                        } else {
                            i = looptemp;
                        }
                    } else{ 
                        if (inComming.get(i).equals("Inc_Dec")) {
                        i++;
                        if (inComming.get(i).equals("]")) {
                              i++;
                            return true;
                        } else {
                            i = looptemp;
                        }
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
            System.out.println(i+" "+inComming.get(i));
            if (inComming.get(i).equals("AM")) {
                i++;
                
                //if flag is true it means that it is function body and Am is not allowed in funcition body
                if(  flagForFunctionVariable==true){
                 
                    return false;
                }
                
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
            System.out.println(inComming.get(i));
            if (inComming.get(i).equals("DT")) {

                i++;
                return true;
            } else if (inComming.get(i).equals("ID")) {
                i++;
                if (inComming.get(i).equals("ID")) {
                    System.out.println(inComming.get(i));

                    return true;
                } else {
                    System.out.println("babababa");
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
        Roshaan=0;
        if (inComming.get(i).equals("ID")) {
            System.out.println("assign 1 " + inComming.get(i));
            if (inComming.get(i).equals("ID")) {
                i++;
                //  if(ID1_decl()){
                if (arr_decl()) {
                    if (assign11()) {
                        System.out.println("assign 1 success");
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
            System.out.println("assign 11 " + inComming.get(i));
            if (inComming.get(i).equals("=")) {
                i++;
                if (assign111()) {
                    System.out.println("assign 11 success");
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
            System.out.println("assign 111 " + inComming.get(i));
            if (inComming.get(i).equals("Inc_Dec") || inComming.get(i).equals("new")
                    || inComming.get(i).equals("ID") || inComming.get(i).equals("(") || inComming.get(i).equals("ninta") || inComming.get(i).equals("nfloata")
                    || inComming.get(i).equals("nchara") || inComming.get(i).equals("nstringa ") || inComming.get(i).equals("boolean_constant")) {
                  //oexp srf tb call krega jb  = sy pehly delimeter na ho
                  //Roshaan 
                  System.out.println(Roshaan);
                if (Roshaan==0&&oexp_decl()) {
                   
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
            System.out.println("aexp_decl " + inComming.get(i));
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
     Roshaan=0;
        if (inComming.get(i).equals("new") || inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals("ID") || inComming.get(i).equals("(") || inComming.get(i).equals("ninta") || inComming.get(i).equals("nfloata")
                || inComming.get(i).equals("nchara") || inComming.get(i).equals("nstringa ") || inComming.get(i).equals("boolean_constant")) {

            System.out.println("oexp_decl " + inComming.get(i));

            if (inComming.get(i).equals("Inc_Dec")
                    || inComming.get(i).equals("ID") || inComming.get(i).equals("(") || inComming.get(i).equals("ninta") || inComming.get(i).equals("nfloata")
                    || inComming.get(i).equals("nchara") || inComming.get(i).equals("nstringa ") || inComming.get(i).equals("boolean_constant")) {
                squareBracket.push("]");

                if (a()) {

                    return true;
                }
            } else if (inComming.get(i).equals("new")) {
                i++;
                if (inComming.get(i).equals("ID")) {
                    i++;
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
                                System.out.println("oexp_decl success");
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
            System.out.println("list_decl " + inComming.get(i));
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

            System.out.println("para_decl " + inComming.get(i));
            if (inComming.get(i).equals("Inc_Dec")
                    || inComming.get(i).equals("ID") || inComming.get(i).equals("(") || inComming.get(i).equals("ninta") || inComming.get(i).equals("nfloata")
                    || inComming.get(i).equals("nchara") || inComming.get(i).equals("nstringa ") || inComming.get(i).equals("boolean_constant")) {
                if (a()) {
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
            System.out.println("arr_decl " + inComming.get(i));
            if (inComming.get(i).equals(":")) {
                i++;
                semiColon.push(":");
                if (a()) {
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
            System.out.println("opera_decl " + inComming.get(i));
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
            System.out.println("Declaaration x " + inComming.get(i));
            looptemp = i;
            if (AM1_Decl()) {
                if (STABLE_Decl()) {  //decl
                    if (FINAL_Decl()) {
                        System.out.println("hello");
                        if (DT_loop()) {
                            System.out.println("hello");
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
                            System.out.println("success DT loop");
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
            System.out.println("loopFor " + inComming.get(i));
            i++;
            if (inComming.get(i).equals("(")) {
                i++;
                if (xLoop()) {
                    System.out.println("HEllo");
                    if (y()) {
                        System.out.println("HEllo");
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
                                                System.out.println("loopFor success");
                                                return true;
                                            } else {
                                                failedCfgs.add("loopfor ");
                                                System.out.println("loopfor failed");
                                                errorIndex = i;
                                            }
                                        } else {
                                            failedCfgs.add("loopfor ");
                                            System.out.println("loopfor failed");
                                            errorIndex = i;
                                        }
                                    } else {
                                        failedCfgs.add("loopfor ");
                                        System.out.println("loopfor failed");
                                        errorIndex = i;
                                    }
                                } else {
                                    failedCfgs.add("loopfor ");
                                    System.out.println("loopfor failed");
                                    errorIndex = i;
                                }
                            } else {
                                failedCfgs.add("loopfor ");
                                System.out.println("loopfor failed");
                                errorIndex = i;
                            }
                        } else {
                            failedCfgs.add("loopfor ");
                            System.out.println("loopfor failed");
                            errorIndex = i;
                        }
                    } else {
                        failedCfgs.add("loopfor ");
                        System.out.println("loopfor failed");
                        errorIndex = i;
                    }
                } else {
                    failedCfgs.add("loopfor ");
                    System.out.println("loopfor failed");
                    errorIndex = i;
                }
            } else {
                failedCfgs.add("loopfor ");
                System.out.println("loopfor failed");
                errorIndex = i;
            }
        } else {
            failedCfgs.add("loopfor ");
            System.out.println("loopfor failed");
            errorIndex = i;
        }
        return false;
    }

    boolean y() {
        if (inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals("ID") || inComming.get(i).equals("(") || inComming.get(i).equals("ninta") || inComming.get(i).equals("nfloata")
                || inComming.get(i).equals("nchara") || inComming.get(i).equals("nstringa ") || inComming.get(i).equals("boolean_constant")) {
            System.out.println("loopFory " + inComming.get(i));
            squareBracket.push("]");
            if (a()) {

                return true;
            }
        } else if (inComming.get(i).equals("]")) {
            System.out.println("loopFory " + inComming.get(i));
            return true;
        }
        return false;
    }

    boolean z() {
        if (inComming.get(i).equals("Inc_Dec")
                || inComming.get(i).equals("ID")) {
            System.out.println("loopForz " + inComming.get(i));
            if (inComming.get(i).equals("Inc_Dec")) {
                i++;
                if (inComming.get(i).equals("ID")) {
                    i++;
                    if (ID1_z()) {
                        System.out.println(inComming.get(i));
                        if(ARR_indec_call()){
                        if (inComming.get(i).equals("]")) {

                            return true;
                        }
                    }
                    }
                }
            } else if (inComming.get(i).equals("ID")) {
                System.out.println("loopForz " + inComming.get(i));
                newtemp = i;
                i++;
                if (ID1_z()) {
                    if(ARR_indec_call()){
                    if (inComming.get(i).equals("Inc_Dec")) {

                        i++;
                        if (inComming.get(i).equals("]")) {
                            System.out.println("loop z success");
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
System.out.println("Success Z");
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
        if (inComming.get(i).equals(".") || inComming.get(i).equals("AO") || inComming.get(i).equals("=") || inComming.get(i).equals(")") || inComming.get(i).equals("Inc_Dec") || inComming.get(i).equals("]")|| inComming.get(i).equals(":")) {
            if (inComming.get(i).equals(".")) {
                System.out.println("ID1Z" + inComming.get(i));
                i++;
                if (inComming.get(i).equals("ID")) {
                    i++;
                    if (ID1_z()) {
                        return true;
                    }
                }
            } else if (inComming.get(i).equals("AO") || inComming.get(i).equals("=")) {
                System.out.println("ID1Z" + inComming.get(i));
                return true;
            } else if (inComming.get(i).equals("]") || inComming.get(i).equals("Inc_Dec")||inComming.get(i).equals(":")) {
                System.out.println("ID1Z" + inComming.get(i));
                return true;
            }

        }
        return false;
    }

    //Salman ka for loop ending here
//Salman ka checkspare 
    boolean checkspare() {

        if (inComming.get(i).equals("check")) {
            System.out.println("checkspare " + inComming.get(i));
            i++;

            if (inComming.get(i).equals("(")) {
                i++;
                bracket.push("(");

                if (a()) {
                    if (inComming.get(i).equals(")")) {
                        i++;

                        if (inComming.get(i).equals("/")) {
                            i++;

                            if (body()) {
                                if (inComming.get(i).equals("\\")) {
                                    i++;

                                    if (ospare()) {

                                        System.out.println("checkspare success");
                                        return true;

                                    } else {
                                        failedCfgs.add("checkspare ");
                                        System.out.println("checkspare failed");
                                        errorIndex = i;

                                    }
                                } else {
                                    failedCfgs.add("checkspare ");
                                    System.out.println("checkspare failed");
                                    errorIndex = i;

                                }
                            } else {
                                failedCfgs.add("checkspare ");
                                System.out.println("checkspare failed");
                                errorIndex = i;

                            }
                        } else {
                            failedCfgs.add("checkspare ");
                            System.out.println("checkspare failed");
                            errorIndex = i;

                        }
                    } else {
                        failedCfgs.add("checkspare ");
                        System.out.println("checkspare failed");
                        errorIndex = i;

                    }
                } else {
                    failedCfgs.add("checkspare ");
                    System.out.println("checkspare failed");
                    errorIndex = i;

                }
            } else {
                failedCfgs.add("checkspare ");
                System.out.println("checkspare failed");
                errorIndex = i;

            }
        } else {
            failedCfgs.add("checkspare ");
            System.out.println("checkspare failed");

            errorIndex = i;

        }

        return false;
    }

    boolean ospare() {

        if (inComming.get(i).equals("spare")) {
            System.out.println("spare " + inComming.get(i));
            i++;
            if (inComming.get(i).equals("/")) {
                i++;

                if (body()) {
                    if (inComming.get(i).equals("\\")) {

                        i++;
                        System.out.println("spare success");
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

            System.out.println("spare " + inComming.get(i));

            System.out.println("spare success");
            return true;
        } else {
            errorIndex = i;
        }

        return false;
    }

    boolean loopwhile() {

        if (inComming.get(i).equals("loopwhile")) {
            i++;
            System.out.println("loopwhile " + inComming.get(i));

            if (inComming.get(i).equals("(")) {
                i++;
                bracket.push("(");
                if (a()) {
                    if (inComming.get(i).equals(")")) {
                        i++;
                        if (inComming.get(i).equals("/")) {
                            i++;
                            if (body()) {
                                if (inComming.get(i).equals("\\")) {
                                    i++;

                                    System.out.println("loopwhile success");
                                    return true;
                                } else {
                                    failedCfgs.add("loopwhile ");
                                    System.out.println("loopwhile failed");
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
            System.out.println("loopdowhile " + inComming.get(i));
            i++;
            if (inComming.get(i).equals("/")) {
                i++;
                if (m_st()) {
                    if (inComming.get(i).equals("\\")) {
                        i++;
                        if (inComming.get(i).equals("while")) {
                            i++;
                            if (inComming.get(i).equals("(")) {
                                bracket.push("(");
                                i++;
                                if (a()) {
                                    if (inComming.get(i).equals(")")) {
                                        i++;
                                        if (inComming.get(i).equals("]")) {
                                            i++;
                                            System.out.println("loopdowhile success");
                                            return true;
                                        } else {
                                            failedCfgs.add("loopdowhile ");
                                            System.out.println("loopdowhile failed");
                                            errorIndex = i;
                                        }
                                    } else {
                                        failedCfgs.add("loopdowhile ");
                                        System.out.println("loopdowhile failed");
                                        errorIndex = i;
                                    }
                                } else {
                                    failedCfgs.add("loopdowhile ");
                                    System.out.println("loopdowhile failed");
                                    errorIndex = i;
                                }
                            } else {
                                failedCfgs.add("loopdowhile ");
                                System.out.println("loopdowhile failed");
                                errorIndex = i;
                            }
                        } else {
                            failedCfgs.add("loopdowhile ");
                            System.out.println("loopdowhile failed");
                            errorIndex = i;
                        }
                    } else {
                        failedCfgs.add("loopdowhile ");
                        System.out.println("loopdowhile failed");
                        errorIndex = i;
                    }
                } else {
                    failedCfgs.add("loopdowhile ");
                    System.out.println("loopdowhile failed");
                    errorIndex = i;
                }
            } else {
                failedCfgs.add("loopdowhile ");
                System.out.println("loopdowhile failed");
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

                System.out.println("body " + inComming.get(i));
                // i++;
                System.out.println("body success");
                return true;
            } else if (s_st()) {
                if (m_st()) {
                    System.out.println("body success");
                    return true;

                } else {
                    errorIndex = i;
                }
            } else {
                errorIndex = i;
            }
        } else {
            System.out.println("body " + inComming.get(i));

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
            System.out.println("s_st " + inComming.get(i));
            if (inComming.get(i).equals("stop")) {
                i++;
                if(inComming.get(i).equals("]")){
                    i++;
                    
                    return true;
                }
            } else if (inComming.get(i).equals("resume")) {
                i++;
                if(inComming.get(i).equals("]")){
                    i++;
                    return true;
                }
            } //else if(decl()){return true;}
            else if (inComming.get(i).equals("check")) {
                if (checkspare()) {
                    System.out.println("s_st success");
                    return true;
                }
            } else if (inComming.get(i).equals("loopfor")) {
                if (loopfor()) {

                    System.out.println("s_st success");
                    return true;
                }
            } else if (inComming.get(i).equals("loopwhile")) {
                if (loopwhile()) {
                    System.out.println("s_st success");
                    return true;
                }
            } else if (inComming.get(i).equals("loopdo")) {
                if (loopdowhile()) {
                    System.out.println("s_st success");
                    return true;
                }
            } else if (xBody()) {
                System.out.println("s_st success");
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
            if (a()) {
                if (inComming.get(i).equals(";")) {
                    i++;
                    if (ID1_indec_call()) {
                        if (ARR_indec_call()) {
                            return true;
                        }
                    }
                }
            }
        } else if (inComming.get(i).equals("(") || inComming.get(i).equals("]") || inComming.get(i).equals("Inc_Dec")||inComming.get(i).equals(".")||inComming.get(i).equals("AO")) {
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
            System.out.println("m_st " + inComming.get(i));

            if (inComming.get(i).equals("\\")) {

                System.out.println("body " + inComming.get(i));
                // i++;
                System.out.println("body success");
                return true;
            } else if (s_st()) {
                if (m_st()) {
                    System.out.println("body success");
                    return true;

                } else {
                    errorIndex = i;
                }
            } else {
                errorIndex = i;
            }
        } else {
            System.out.println("m_st " + inComming.get(i));

            errorIndex = i;

            return false;
        }
        return false;
    }
//for declaration and assignment in 

    boolean xBody() {
        if (inComming.get(i).equals("AM") || inComming.get(i).equals("stable") || inComming.get(i).equals("absolute") || inComming.get(i).equals("DT")
                || inComming.get(i).equals("ID")||inComming.get(i).equals("Inc_Dec")) {
            System.out.println("Declaaration x " + inComming.get(i));
            looptemp = i;
            if (AM1_Body()) {
                if (STABLE_Body()) {  //decl
                    if (FINAL_Body()) {
                        System.out.println("hello");
                        if (DT_loop()) {
                            System.out.println("hello");
                            if (assign1()) {

                                if (list_decl()) {
                                    if (inComming.get(i).equals("]")) {
                                        i++;
                                        return true;

                                    }
                                }
                            } else {
                                System.out.println("xBody Failed");
                            }
                        } else {
                            
                            if(INC_DEC_CALL()){
                                return true;
                            }
                            else if(assign()){
                                
                            System.out.println("call assign");
                            
                                i++;
                                return true;
                            }
                        }

                    } else {
                        System.out.println("xBody Failed");
                    }
                }
            }
        }

        return false;
    }
    
     boolean AM1_Body() {
        if (inComming.get(i).equals("AM") || inComming.get(i).equals("stable") || inComming.get(i).equals("absolute")
                || inComming.get(i).equals("DT") || inComming.get(i).equals("ID")||inComming.get(i).equals("Inc_Dec")) {
            if (inComming.get(i).equals("AM")) {
                i++;

                return true;
            } else if (inComming.get(i).equals("stable") || inComming.get(i).equals("absolute")
                    || inComming.get(i).equals("DT") || inComming.get(i).equals("ID")||inComming.get(i).equals("Inc_Dec")) {
                return true;
            }
        }
        return false;
    }

    boolean STABLE_Body() {
        if (inComming.get(i).equals("stable") || inComming.get(i).equals("absolute")
                || inComming.get(i).equals("DT") || inComming.get(i).equals("ID")||inComming.get(i).equals("Inc_Dec")) {
            if (inComming.get(i).equals("stable")) {
                i++;

                return true;
            } else if (inComming.get(i).equals("absolute")
                    || inComming.get(i).equals("DT") || inComming.get(i).equals("ID")||inComming.get(i).equals("Inc_Dec")) {
                return true;
            }
        }
        return false;
    }

    boolean FINAL_Body() {
        if (inComming.get(i).equals("absolute")
                || inComming.get(i).equals("DT") || inComming.get(i).equals("ID")||inComming.get(i).equals("Inc_Dec")) {
            if (inComming.get(i).equals("absolute")) {
                i++;
                // flag = 1;
                return true;
            } else if (inComming.get(i).equals("DT") || inComming.get(i).equals("ID")||inComming.get(i).equals("Inc_Dec")) {
                return true;
            }
        }
        return false;
    }
}
