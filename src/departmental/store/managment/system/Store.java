
package departmental.store.managment.system;

import static departmental.store.managment.system.GUI.DEFAULT_UI;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 * store class is the class responsible to manage packages
 * @author redet
 */
public class Store {
    private int top;
    private final int size=100;
    private Package[] list;
    private String name;
    public Store() {
        top=0;
        this.list = new Package[size];
    }
    /**
     * initialize the store object
     * @param name name of the store
     **/
    public Store(String name){
        this();
        this.name=name;
    }
    
    /**
     * this method is used to display the list of packages that the store has
     */
    public void display(){
        if(DEFAULT_UI!=userInterface.TERMINAL){
            gShow();
        }else{
            tShow();
        }
    }
    /**
     * this function return true if it successfully adds the package i.e. the package is unique.
     * else it return false if the package already exits with the same code
     *
     * @param p the package to register.
     * @return true success full addition of package. false on filer 
     */
    public boolean append(Package p){
        if(!isInTheStore(p.getCode())){
            list[top++]=p;
            return true;
        }
        return false;
    }
    /**
     * calculates the total asset of the packages and returns it as double
     * @return total asset of the packages in the store
     */    
    public double calculateAsset(){
       double totalAsset=0.0d;
       for(int i=0;i<top;i++){
            totalAsset+=list[i].getAsset();
        }
       return totalAsset;
    }

/**
 * register a package with proper UI
 */
   public void enterPackage(){
        if(DEFAULT_UI!=userInterface.TERMINAL){
            gEnter();
        }else{
            tEnter();
        }
    }
    
    public void gEnter(){
        Package newpackage= new Package();
        newpackage.gEnter();
        append(newpackage);
    }  
    public void tEnter(){
        Package newpackage= new Package();
        newpackage.tEnter();
        append(newpackage);
    }
    public void tShow(){
        System.out.println(toString());
    }
    public void gShow(){
        if(top!=0){
            String[] columen = {"code", "name ", "discription" , "price" , "amount" , "dispatched"};
            int row = top+1;
            Object[][] table = new Object[row][6];
            table[0]=columen;
            for(int i=0;i<top;i++){
                table[i+1]=list[i].toArray();
            }
            JTable t = new JTable(table,columen);
            JOptionPane.showMessageDialog(null,t,"summery of the store",JOptionPane.INFORMATION_MESSAGE);
        }else{            
            JOptionPane.showMessageDialog(null,toString(),"summery of the store",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    @Override
    public String toString(){
        String summeryStr;
        summeryStr = "";
        if(top==0){
              summeryStr +="the store is empty\n";
        }else{
            summeryStr+=String.format("%-4s | %-5s | %-10s | %-9s | %-4s | %-4s%n","code", "name ", "discription" , "price" , "amount" , "dispatched");
            summeryStr+="-------------------------------------------------------------\n";
            for(int i=0;i<top;i++)
               summeryStr+=list[i];
            summeryStr+="-------------------------------------------------------------";
        }
        return summeryStr;
    }
    public boolean isInTheStore(String code){
        if(top==0||list==null){
            return false;
        }else{
            for(int i=0;i<top;i++){
                String temp = list[i].getCode();
                if(temp!=null && temp.equalsIgnoreCase(code))
                    return true;            
            }
        }
        
        return false;
    }
    public void sort(){
        Package[] temp= new Package[top];
        System.arraycopy(list,0,temp,0,top);
        Arrays.sort(temp);
    }
    /**
     * function for setting the UI.
     */
    public void setting(){
        if(GUI.DEFAULT_UI==userInterface.GRAPHICAL){
            gSetting();
        }else{
            tSetting();            
        }
    }
    
    public void gSetting(){
        int b;
        b = JOptionPane.showConfirmDialog(null, "do you want to change the user interface.","change the UI",JOptionPane.YES_NO_OPTION);
        if(b==0)
            GUI.toggle();
    }
    
    public void tSetting(){
        Scanner in=new Scanner(System.in);
        System.out.println("do you want to change the user interface? (Y/n)");
        String isTrue = in.next();
        boolean temp=isTrue.equalsIgnoreCase("Y");
        if(temp){
           GUI.toggle();
        }else if(!isTrue.equalsIgnoreCase("n")){
            System.out.println("invalid input. try again");
            tSetting();
        }
         
    }
    public void dispatch(){
         if(GUI.DEFAULT_UI==userInterface.GRAPHICAL){
             //do the graphical dipatch here
             String prompt = "what package do you went to dispatch?(insert the unique identifer-code)";
             String code = JOptionPane.showInputDialog(null, prompt,"dipatch window",JOptionPane.QUESTION_MESSAGE);
             if(!isInTheStore(code)){
                 JOptionPane.showMessageDialog(null,"the packge is not found.","error",JOptionPane.ERROR_MESSAGE);
             }else{
                 Package dispachable = packageWith(code);
                 int amount = Integer.parseInt(JOptionPane.showInputDialog(null,"package found! \n"+dispachable.toString()+"\n insert the amount of packge to dispache."));               
                 if(amount > dispachable.getAmount())
                 {
                     JOptionPane.showMessageDialog(null,"invalid amount.","ERROR",JOptionPane.ERROR_MESSAGE);
                 }else{
                     dispachable.dispatch(amount);
                 }                
                 
             }
             
         }else{
             //do the terminal dispatch
             Scanner in = new Scanner(System.in);
             String prompt = "what package do you went to dispatch?";
             System.out.println(prompt);
             String code =  in.next();
             if(!isInTheStore(code)){
                 System.out.println("the packge is not found.");
             }else{
                 System.out.println("package found!");
                 Package dispachable = packageWith(code);
                 dispachable.show();
                 System.out.println("insert the amount of packge to dispache.");
                 int amount = in.nextInt();
                 if(amount > dispachable.getAmount())
                 {
                     System.out.println("invalid amount.");
                 }else{
                     dispachable.dispatch(amount);
                 }  
             }
         }
    }
    public Package packageWith(String code){
        for(Package l:list){
            if(l.getCode().equalsIgnoreCase(code))
                return l;
        }
        return null;
    }
}
