/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departmental.store.managment.system;

import static departmental.store.managment.system.GUI.DEFAULT_UI;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author redet
 */
public class DepartmentalStoreManagmentSystem {

    /**
     * @param args the command line arguments
     */
    private static Store UoGStore;
    public static void main(String[] args) {
//        // TODO code application logic here
//        Store s = new Store("Univerty of Gondar");
//        Package one = new Package("1","laptop",4550.0);
//                one.setAmount(1343);
//        s.append(one);
//     //   s.enterPackage();
//        
//        s.append(new Package("10","laptop",4550.0));
//        s.append(new Package("2","laptop",4550.0));
//        s.append(new Package("8","laptop",4550.0));
//        s.display();
//        s.sort();
//        s.display();
//        s.gSetting();
//        s.dispatch();
        startUp();
        while(update()!=0);
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
                        "       1)register/import property\n" +
                        "       2)display property\n" +
                        "       3)dispatch property\n" +
                        "       4)issue property\n" +
                        "       5)process and sort the store\n" +
                        "       6)REPORT MENU\n" +
                        "       7)export property list to other file format\n" +
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
    public static void startUp(){
        UoGStore = new Store("Univerty of Gondar");
        DEFAULT_UI=userInterface.GRAPHICAL;
    }
    public static int update(){
        int sle=mainMenu();
        switch(sle){
            case 0:
                return 0;
            case 1:
                {
                    int seleSto = storeMenu();
                    switch(seleSto){
                        // list all the cases here
                        case 0:
                             return 1;
                        case 6:
                            int selRep = reportMenu();
                            switch(selRep){
                                // list all the cases here
                                case 0:
                                    return 2;

                            }
                    }
                }
            case 2:
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
             int temp = in.nextInt();
             while(true){
                if(temp>=startInt&&temp<=endInt){
                    return temp;
                }else{
                    System.out.println("invalid input detected, try again.");
                    temp = in.nextInt();
                }
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
                        "                  YOHANNES TADESSE(4036/08)      \n" +
                        "                  YOHANNES NIGUSSIE(4033/08)     \n" +
                        "                                                 \n" +
                        "       (c) Copyright 2016. All Rights Reserved.";
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
}
