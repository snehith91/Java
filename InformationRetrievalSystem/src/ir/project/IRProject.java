package ir.project;
import java.io.*;
public class IRProject {
    public void main() throws IOException,FileNotFoundException {
        File file=new File("C:/Users/Snehith/Desktop/dataset.txt"); //Path of cran dataset
        BufferedReader br=new BufferedReader(new FileReader(file));//opening a file
        PrintWriter pw=null;
        String str;
        int i=1;
        str=br.readLine();
        while(str!=null){
            if(str.equals(".I "+i)){ //checking whether its a new docc in dataset or not
                File f=new File("D:/IR Proj/ParsedDocc/I"+i+".txt"); //if new doccument create a new file 
                FileWriter fw=new FileWriter(f);
                pw=new PrintWriter(fw);
                pw.flush(); //clears the printstream
                i++;
            }else{
                pw.println(str); //If line in existing docc then add it to file
                pw.flush();
            }  
            str=br.readLine();
        }
        System.out.println("Parsing done");
    }  
}
