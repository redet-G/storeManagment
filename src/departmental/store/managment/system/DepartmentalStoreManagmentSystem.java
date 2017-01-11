/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departmental.store.managment.system;

import static departmental.store.managment.system.GUI.DEFAULT_UI;
import java.io.IOException;


/**
 *
 * @author redet
 */
public class DepartmentalStoreManagmentSystem {

    /**
     * @param args the command line arguments
     */
    private static Store UoGStore;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
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
        
         UoGStore = new Store("Univerty of Gondar");
         Manager uogStoreManager = new Manager(UoGStore,"store.db");
         DEFAULT_UI=userInterface.GRAPHICAL;
        while(uogStoreManager.update()!=0);
    }
   
}