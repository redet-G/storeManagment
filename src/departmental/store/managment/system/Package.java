
package departmental.store.managment.system;

import java.awt.HeadlessException;
import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * this class will encapsulate a package of the objects stored in the store;
 */
public class Package extends GUI implements Comparable,Serializable, displayable {
    private String code;
    private long amount=0;
    private String name;
    private String discription;
    private double price;
    private long dispatched=0;
    private long issued=0;

    public Package(String code, String name, long amount, double price, String discription) {
        this.code = code;
        this.amount = amount;
        this.name = name;
        this.discription = discription;
        this.price = price;
    }
    public Package(){
        this(null,null,0,0,null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
    public void addAmount(long amount){
        if(amount>0){
            this.amount+=amount;
            //fulfile the issued items
            if(issued>0){
                issued = issued-amount;
                if(issued<0)
                    issued=0;
            }
        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getIssued() {
        return issued;
    }

    public boolean Issue(long amount) {
        if(amount > 0){
           this.issued += amount;
           return true;
        }else
           return false;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public double getPrice() {
        return price;
    }

    public boolean setPrice(double price) {
        if(0<=price){
            this.price = price;
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return String.format("%-4s | %-5s | %-10s | %4f | %4d | %4d | %4d%n", code, name , discription , price , amount , dispatched, issued);
    }
    
    /**
     * convert the package information and return it.
     * @return array of package information
     */
    public Object[] toArray(){
        return new Object[]{code, name , discription , price , amount , dispatched, issued};
    }
    
    public long getDispatched() {
        return dispatched;
    }

    public boolean dispatch(int a){
        if(amount>a){
            dispatched+=a;        
            amount-=a;
           return true;
        }
        return false;
    }
    
    public double getAsset(){
        return price*amount;
    }
    /**
     * this function is meant to support GUT registration of this package;
     */
    @Override
    public void gEnter(){
        String[] fileds = {"code","name","discription","amount","price"};
        int i=0;
        String displayablemsg="insert package infomation \n";       
        try {
            String temp=JOptionPane.showInputDialog(null,displayablemsg +fileds[i]+"=","package registration",JOptionPane.PLAIN_MESSAGE);
            setCode(temp);
            displayablemsg+=fileds[i]+": "+temp+"\n";
            i++;
            temp=JOptionPane.showInputDialog(null,displayablemsg +fileds[i]+"=","package registration",JOptionPane.PLAIN_MESSAGE);
            setName(temp);
            displayablemsg+=fileds[i]+": "+temp+"\n";
            i++;
            temp=JOptionPane.showInputDialog(null,displayablemsg +fileds[i]+"=","package registration",JOptionPane.PLAIN_MESSAGE);
            setDiscription(temp);
            displayablemsg+=fileds[i]+": "+temp+"\n";
            i++;
            temp=JOptionPane.showInputDialog(null,displayablemsg +fileds[i]+"=","package registration",JOptionPane.PLAIN_MESSAGE);
            setAmount(Long.parseLong(temp));
            displayablemsg+=fileds[i]+": "+temp+"\n";
            i++;
            temp=JOptionPane.showInputDialog(null,displayablemsg +fileds[i]+"=","package registration",JOptionPane.PLAIN_MESSAGE);
            setPrice(Double.parseDouble(temp));
            displayablemsg+=fileds[i]+": "+temp+"\n";
         } catch (HeadlessException | NumberFormatException e) {
             JOptionPane.showMessageDialog(null,"invalid value inserted. please try again","error",JOptionPane.ERROR_MESSAGE);
             gEnter();
        }
    }
    @Override
    public void gShow(){
                         String info = "code: "+code+"\n"
                         + "name: " +getName()+"\n"
                         + "discription: "+getDiscription()+"\n"
                         + "price: "+getPrice()+"\n"
                         + "amount: "+getAmount()+"\n"
                         + "dispatched: "+getDispatched()+"\n";
        JOptionPane.showMessageDialog(null,info,"package information",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void tEnter(){
        Scanner in = new Scanner(System.in);
        String[] fileds = {"code","name","discription","amount","price"};
        int i=0;
        String displayablemsg="insert package infomation \n";
            System.out.print(displayablemsg +fileds[i]+"=");
            setCode(in.next());
            i++;
            System.out.print(displayablemsg +fileds[i]+"=");
            setName(in.next());
            i++;
            System.out.print(displayablemsg +fileds[i]+"=");
            setDiscription(in.next());
            i++;
            System.out.print(displayablemsg +fileds[i]+"=");
            setAmount(in.nextLong());
            i++;
            System.out.print(displayablemsg +fileds[i]+"=");
            setPrice(in.nextDouble());
    }
    @Override
    public void tShow(){
           String info = "code: "+code+"\n"
                         + "name: " +getName()+"\n"
                         + "discription: "+getDiscription()+"\n"
                         + "price: "+getPrice()+"\n"
                         + "amount: "+getAmount()+"\n"
                         + "dispatched: "+getDispatched()+"\n";
        System.out.println(info);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.code.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Package other = (Package) obj;
        if(!Objects.equals(this.code, other.getCode())) {
            return false;
        }
        return true;
    }
    public int compareTo(Package otherPackage){
        return compare(this,otherPackage);
    }
    
     @Override
    public int compareTo(Object o) {
        if(o instanceof Package) {
            Package aPackage = (Package) o;
            return this.compareTo(aPackage);
        }
        throw new UnsupportedOperationException("incomparable object type");
    }
    
    private static int compare(Package aThis, Package otherPackage) {
        char[] acode = aThis.getCode().toCharArray();
       char[] ocode = otherPackage.getCode().toCharArray();
       int acodeV=0;
       int ocodeV=0;
       for(int x:acode)
           acodeV+=x;
       for(int x:ocode)
           ocodeV+=x;
       if(ocodeV>acodeV){
           return -1;
       }
       if(ocodeV<acodeV){
           return 1;
       }
       return 0;
    }
    
    @Override
    public void display(){
       if(GUI.DEFAULT_UI==userInterface.GRAPHICAL){
           gShow();
       }else{
           tShow();
       }
    }
}