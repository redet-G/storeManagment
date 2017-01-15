
package departmental.store.managment.system;

import static departmental.store.managment.system.GUI.DEFAULT_UI;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class Manager {
    public Store UoGStore;
    private final String FileName;//the file name to store the Store class to.
    public Manager(Store a,String FileName) throws IOException, ClassNotFoundException{
        this.FileName=FileName;
        File StoreData= new File(FileName);
        if(StoreData.exists()){
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(StoreData));
            UoGStore = (Store)ois.readObject();
        }else{
            UoGStore=a;
        }
    }
     public static int mainMenu(){
        String menu = "MAIN MENU\n"
                    + "       1)store menu\n"
                    + "       2)help\n"
                    + "       3)about\n"
                    + "       4)change user interface\n"
                    + "       0)exit";
       return getInt(menu,"Main Menu",0,4);        
    }
    public static int storeMenu(){
        String menu = " STORE MENU\n" +
                        "       1)register package\n" +
                        "       2)display packages\n" +
                        "       3)dispatch packges\n" +
                        "       4)issue packages\n" +
                        "       5)sort packages\n" +
                        "       6)REPORT MENU\n" +
                        "       7)export package list to other file format\n" +
                        "       0)back to MAIN MENU\n";

        return getInt(menu,"STORE MENU",0,7);    

    }
    public static int reportMenu(){
                String menu = " REPORT MENU\n" +
                              "      1)display report\n" +
                              "      2)export report to other file formats\n" +
                              "      0)back to MAIN MENU";
        return getInt(menu,"REPORT MENU",0,2);    
    }
        public static int exportListMenu(){
                String menu = " EXPORTING LIST OF PACKAGES\n" +
                              "      1)HTML (web page)\n" +
                              "      2)CSV(spreed sheet)\n" +
                              "      3)TXT(text file)\n" +
                              "      0)back to MAIN MENU";
        return getInt(menu,"REPORT MENU",0,3);    
    }
    public int update() throws IOException{
        int sle=mainMenu();
        switch(sle){
            case 0:
                return 0;
            case 1:
                {
                    int seleSto;
                    do{
                        seleSto = storeMenu();
                        switch(seleSto){
                            // list all the cases here
                            case 0:
                                 return 1;
                            case 1:
                                register();
                                break;
                            case 2:
                                UoGStore.display();
                                break;
                            case 3:
                                //dispatch pakage after searching it
                                 UoGStore.dispatch();
                                 break;
                            case 4:
                                UoGStore.issue();
                                break;
                            case 5:
                                UoGStore.sort();
                                UoGStore.saveStoreTo(FileName);//save the Store class after sorting it's packages
                                break;
                            case 6:{
                                int selRep;
                                do{                                    
                                Report StoreReport = UoGStore.toReport();
                                if(StoreReport==null){
                                    if(DEFAULT_UI==userInterface.GRAPHICAL){
                                        JOptionPane.showMessageDialog(null,"some packages are needed to generate report.","the store is empty.",JOptionPane.ERROR_MESSAGE);
                                   }else{
                                        System.out.println("some packages are needed to generate report.\n but the store is empty.");
                                    }
                                    break;
                                }
                                selRep = reportMenu();
                                switch(selRep){
                                    // list all the cases here
                                    case 0:
                                        break;
                                    case 1:
                                        StoreReport.display();
                                    case 2:
                                    
                                    }
                                }while(selRep!=0);
                            }
                            break;
                            case 7:
                            {
                                int selRep;
                                do{
                                selRep = exportListMenu();
                                switch(selRep){
                                    // list all the cases here
                                    case 0:
                                        break;
                                    case 1:
                                        UoGStore.exportTo(FileType.HTML);
                                        break;
                                    case 2:
                                        UoGStore.exportTo(FileType.CSV);
                                        break;
                                    case 3:
                                        UoGStore.exportTo(FileType.TXT);
                                        break;
                                    }
                                }while(selRep!=0);
                            }
                        }            
                 }while(seleSto!=0);
                }
                 break;
            case 2:
                help();
                return 1;
            case 3:
                about();
                return 1;           
            case 4:
                UoGStore.setting();
                return 1;
        }        
        return 0;
    }
    public static int getInt(String prompt,String promptTitle, int startInt, int endInt){
        if(DEFAULT_UI!=userInterface.TERMINAL){
           try{
                int temp=Integer.parseInt(JOptionPane.showInputDialog(null,prompt,promptTitle,JOptionPane.INFORMATION_MESSAGE));
                while(true){
                    if(temp>=startInt&&temp<=endInt){
                        return temp;
                    }else{
                        JOptionPane.showMessageDialog(null,"invalid input","invalid input detected",JOptionPane.ERROR_MESSAGE);
                        temp=Integer.parseInt(JOptionPane.showInputDialog(null,prompt,promptTitle,JOptionPane.INFORMATION_MESSAGE));
                    }
                }   
                
           }catch(HeadlessException | NumberFormatException e){
                JOptionPane.showMessageDialog(null,"invalid input","invalid input detected",JOptionPane.ERROR_MESSAGE);
                return getInt(prompt,promptTitle,startInt,endInt);
           }
        }else{
             System.out.println(prompt);
             Scanner in = new Scanner(System.in);
             int temp;
             try{
                temp =in.nextInt();
                while(true){
                    if(temp>=startInt&&temp<=endInt){
                        return temp;
                    }else{
                        System.out.println("invalid input detected, try again.");
                        temp = in.nextInt();
                    }
                }
             }catch(InputMismatchException e){
                System.out.println("invalid input try again.");
                return getInt(prompt,promptTitle,startInt,endInt);
             }
             
         }
    }
    public static void about(){
        String MESSAGE = " ABOUT\n" +
                        "                                                 \n" +
                        "       Name :Departmental Store Management System\n" +
                        "       Version: 0.01                             \n" +
                        "       Developers:REDET GETACHEW(3928/08)        \n" +
                        "                  ZELALEM ZERFU(4046/08)         \n" +
                        "                  TAMIRESILASSIE TILAHUN(3974/08)\n" +
                        "                  YOHANNES MENGISTU(4034/08)     \n" +
                        "                  YOHANNES NIGUSSIE(4033/08)     \n" +
                        "                  SAMUEL KASSAW(07486/09)        \n" +
                        "       (c) Copyright 2017. All Rights Reserved.";
        if(DEFAULT_UI==userInterface.GRAPHICAL){
            JOptionPane.showMessageDialog(null,MESSAGE,"ABOUT",JOptionPane.INFORMATION_MESSAGE);
        }else{
            System.out.println(MESSAGE);
            System.out.println("press enter to continue.");
            try {
                System.in.read();
            } catch (IOException ex) {
                System.out.println("there was an error reading the input.");
            }
        }
    }
    public static void help(){
                String MESSAGE = "Departmental store management system is a program          \n" +
                                "that helps to manage the store of a certain orga-          \n" +
                                "nization, for example in university of Gondar              \n" +
                                "there is a big store that other departments use            \n" +
                                "and depend on in order to function properly. This          \n" +
                                "program is intended to facilitate the function of          \n" +
                                "such unit of an organization by providing the              \n" +
                                "following functions.                                       \n" +
                                "                                                           \n" +
                                "-Managing recodes of properties that the store has:-       \n" +
                                " this has a certain advantage over the paper paced         \n" +
                                " system. By using this program store manager or any        \n" +
                                " other employee can instantly access the available         \n" +
                                " products that the store contain. By doing so not          \n" +
                                " only this system can save time but also money that        \n" +
                                " was wasted in the older system.                           \n" +
                                "                                                           \n" +
                                "-Managing the issued properties that the store doesn't     \n" +
                                " have:- this is important in a way that the issued pr-     \n" +
                                " oducts can be managed easly.                              \n" +
                                "                                                           \n" +
                                "-Recoding dispatched properties:- dispatched properties    \n" +
                                " are products that have been sent to other departments.    \n" +
                                " this way the store manager knows how it has performed     \n" +
                                " and how much money the store have served.                 \n" +
                                "                                                           \n" +
                                "-Producing and exporting reports to other file formats:-   \n" +
                                " one of the main feature of this program is its ability    \n" +
                                " to produce reports. This way one can easily understand    \n" +
                                " the performance of the store. And this reports can        \n" +
                                " easily exported to other file formats, like word docu-    \n" +
                                " ments and spread sheet, for further analysis.\n";
        if(DEFAULT_UI==userInterface.GRAPHICAL){
            JOptionPane.showMessageDialog(null,MESSAGE,"HELP",JOptionPane.INFORMATION_MESSAGE);
        }else{
            System.out.println(MESSAGE);
            System.out.println("press enter to continue.");
            try {
                System.in.read();
            } catch (IOException ex) {
                System.out.println("there was an error reading the input.");
            }
        }
    }
    public void register() throws IOException{
        //register a pakcage here
        boolean isCont;
        do{
            isCont=false;
            UoGStore.enterPackage();
            if(DEFAULT_UI==userInterface.GRAPHICAL){
               if(0==JOptionPane.showConfirmDialog(null,"do you want to continue registering?","registering",JOptionPane.YES_NO_OPTION)){
                  isCont=true;
               }                
            }else{
                Scanner in=new Scanner(System.in);
                System.out.println("do you want to continue registering? (Y/n)");
                String isTrue = in.next();
                isCont=isTrue.equalsIgnoreCase("Y");
            }
        }while(isCont);
        UoGStore.saveStoreTo(FileName);//save the Store to a file after the registering some packages
    }
}
