    import javax.swing.*;    
    public class TableExample {    
        JFrame f;    
        TableExample(){    
        f=new JFrame();    
        String data[][]={ {"PANT","5"},    
                             {"SHIRT","1"},    
                              {"SHORTS","15"}};    
        String column[]={"ITEM","COUNT"};         
        JTable jt=new JTable(data,column);    
        jt.setBounds(30,40,200,300);          
        JScrollPane sp=new JScrollPane(jt);    
        f.add(sp);          
        f.setSize(300,400);    
        f.setVisible(true);    
    }     
    public static void main(String[] args) {    
        new TableExample();    
    }    
    }  