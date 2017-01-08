/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departmental.store.managment.system;

/**
 * these functions should be implemented to support graphical user interface
 * @author redet
 */
public abstract class GUI {
    
    /**
     * this is the default user interface used. change this to affect the user interface
     */
    static userInterface DEFAULT_UI=userInterface.GRAPHICAL;
    void gShow(){};
    void gEnter(){};
    
    static void toggle(){
        if(DEFAULT_UI==userInterface.GRAPHICAL)
            DEFAULT_UI=userInterface.TERMINAL;
        else
            DEFAULT_UI=userInterface.GRAPHICAL;
    }
}

