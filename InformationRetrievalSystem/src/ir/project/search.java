package ir.project;
import java.io.*;
public class search {
    public static void main(String args[]) throws IOException,FileNotFoundException{
        IRProject p1=new IRProject();
        p1.main(); //for parsing the doccument
        tokenize t=new tokenize(); 
        t.tokenizing(); //for tokenizing the doccuments and eliminating stopwords
        t.consoleDisplay();
        
    }
    
}
