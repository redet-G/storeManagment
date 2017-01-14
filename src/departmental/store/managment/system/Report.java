
package departmental.store.managment.system;

import static departmental.store.managment.system.GUI.DEFAULT_UI;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

public final class Report implements displayable{
    private final Package[] list;
    private final String header;
    private final String bodyText;
    private double TotalAsset;
    private double costOFissuedItems;
    private double costOFdispatched;
    private final Object[][] comparePackagesTable;
    
    public Report(Package[] list,String header,String bodyText){
        this.list=list;
        this.header=header;
        this.bodyText=bodyText;
        comparePackagesTable = new Object[list.length+1][2];//two columes, pluse one row for columes titles
        comparePackagesTable[0][0]="code";
        comparePackagesTable[0][1]="percentage";
        calculate();
    }
    @Override
     public void display(){
        if(DEFAULT_UI!=userInterface.TERMINAL){
            gShow();
        }else{
            tShow();
        }
    }
    @Override
    public String toString(){
        String temp=header+"\n"+bodyText+"\n"+
                    "the following table shows property model with its\n" +
                    "corresponding percentage of its price compared with\n" +
                    "the total asset of the store. note that the percentage\n" +
                    "is rounded.\n"+
                    "____________________________\n";
        temp+=String.format("%15s|%5s %n",comparePackagesTable[0][0],comparePackagesTable[0][1]);
        for(int i=1,listWithColumen=list.length+1;i<listWithColumen;i++){
            temp+=String.format("%15s|%10f %n",comparePackagesTable[i][0],(double)comparePackagesTable[i][1]);
        }
        temp+="____________________________\n"
                +"total asset of the store is "+TotalAsset+" birr\n"
                + "the issued items will cost "+costOFissuedItems+" birr\n"
                + "the disptched items cost "+costOFdispatched+" birr\n\n"
                + "this is genetered using store management program.";
        return temp;
    }
    
    public double getTotalAsset(){
          return TotalAsset; 
    }
    public void calculate(){
        TotalAsset=0;
        costOFdispatched=0;
        costOFissuedItems=0;
        for (Package l : list) {
            TotalAsset += l.getAsset();
            costOFdispatched += l.getDispatched() * l.getPrice();
            costOFissuedItems += l.getIssued() * l.getPrice();
        }
        for(int i=0;i<list.length;i++){
            comparePackagesTable[i+1][0]=list[i].getCode();
            comparePackagesTable[i+1][1]=((list[i].getAsset() * 100)/TotalAsset);
        }
    }

    @Override
    public void gShow() {
        String temp="<html><h3>"+header+"</h3><br>"+bodyText+"<br>the table shows property model with its<br/>" +
            "corresponding percentage of its price compared with<br/>" +
            "the total asset of the store. note that the percentage<br/>" +
            "is rounded.</html>";
        String shortSummery="<html>total asset of the store is "+TotalAsset+" birr<br/>"
                + "the issued items will cost "+costOFissuedItems+" birr<br/>"
                + "the disptched items cost "+costOFdispatched+" birr<br/>"
                + "this is genetered using store management program.<html>";
        JPanel panel = new JPanel();
        JLabel jl = new JLabel(temp);
        JLabel jl2 = new JLabel(shortSummery);
        JTable t = new JTable(comparePackagesTable,new String[]{"code","percentage"});
        panel.add(jl);
        panel.add(t);
        panel.add(jl2);        
        JOptionPane.showMessageDialog(null,panel,"Report",JOptionPane.INFORMATION_MESSAGE);

    }

     public void exportTo(FileType type) throws IOException{
            switch(type){
                case HTML:{
                    String HTMLData="<!DOCTYPE html><html><head><title>Departmental Store Management System</title><style>td{border: solid 2px #ddd;}tr:hover{background:#ddd;}</style></head><body><h2>"+header+"</h2>"+bodyText+"<table>";
                    for(int i=1,listWithColumen=list.length+1;i<listWithColumen;i++){                        
                        HTMLData+="<tr><td>"+comparePackagesTable[i][0]+"</td><td>"+comparePackagesTable[i][0]+"</td></tr>";
                    }
                    HTMLData+="</table><br/> this file is generated using store management software.</body></html>";
                    File HTMLfile = new File("html-report-exported.html");
                    FileWriter file = new FileWriter(HTMLfile);
                    file.write(HTMLData);
                    file.close();
                    Desktop.getDesktop().open(HTMLfile);
                }
                    break;
                case CSV:{
                    String CSVData=header+"\n"+bodyText+"\n";
                    for(int i=1,listWithColumen=list.length+1;i<listWithColumen;i++){  
                        CSVData+=comparePackagesTable[i][0]+","+comparePackagesTable[i][1]+"\n";
                    }
                    CSVData+="\nthis file is generated using store management software.";
                    File CSVfile = new File("csv-report-exported.csv");
                    FileWriter file = new FileWriter(CSVfile);
                    file.write(CSVData);
                    file.close();
                    Desktop.getDesktop().open(CSVfile);
                }
                    break;
                case TXT:{
                    File TXTfile = new File("txt-report-exported.txt");
                    FileWriter file = new FileWriter(TXTfile);
                    file.write(toString());
                    file.close();
                    Desktop.getDesktop().open(TXTfile);
                }
                    break;
            }
    }
    @Override
    public void tShow() {
        System.out.println(toString());
    }
    
    
}
