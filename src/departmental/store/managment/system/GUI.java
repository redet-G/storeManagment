
package departmental.store.managment.system;

/**
 * these functions should be implemented to support graphical user interface
 * @author redet
 */
public abstract class GUI {
    
    /**
     * this is the default user interface used. change this to affect the user interface
     */
    static userInterface DEFAULT_UI;
    abstract void gShow();
    abstract void gEnter();
   /**
    * this will toggle between user interfaces.
    */
    static void toggle(){
        if(DEFAULT_UI==userInterface.GRAPHICAL)
            DEFAULT_UI=userInterface.TERMINAL;
        else
            DEFAULT_UI=userInterface.GRAPHICAL;
    }
}

