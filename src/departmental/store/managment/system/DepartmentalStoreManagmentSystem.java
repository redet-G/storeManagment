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
        
         UoGStore = new Store("Univerty of Gondar");
         Manager uogStoreManager = new Manager(UoGStore,"store.db");
         DEFAULT_UI=userInterface.GRAPHICAL;
        while(uogStoreManager.update()!=0);
    }
   
}