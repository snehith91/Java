package ir.project;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;
public class tokenize {
    Stemming stem=new Stemming();
    HashMap<String, HashMap<String, Integer>> M1 = new HashMap<String, HashMap<String, Integer>>(); //creating HashMap for storing Term Frequency
    public void tokenizing() throws IOException,FileNotFoundException {
        ArrayList a1=new ArrayList();//creating Array list for storing stopwords
        PrintWriter pw=null;
        BufferedReader br=null;
        File f1=new File("C:/Users/Snehith/Desktop/stopwords.txt"); //path for stopwords list
        br=new BufferedReader(new FileReader(f1));
        String str1;
        str1=br.readLine();
        while(str1!=null)
        {
            String s=str1.trim(); //removing blank spaces
            a1.add(s); //adding string to arraylist
            str1=br.readLine();
        }//storing all stopwords in array to check whether token is a stopword or not
        File f2=new File("D:/IR Proj/ParsedDocc"); //path of folder which contains the parsed doccuments of cran file
        File[] files=f2.listFiles();//storing list of paths of parsed doccuments
        for(File f:files){
            if (f.isFile()) {
            br = new BufferedReader(new FileReader(f));
            StringBuilder sb = new StringBuilder();
            String str4;
            String str2  = br.readLine();
            while (str2 != null) {
                sb.append(str2);
                sb.append("\n");
                str2 = br.readLine();
                }//stringbuilder contains one file at a time
            StringTokenizer st = new StringTokenizer(sb.toString());
            while (st.hasMoreTokens()) {
                String str3 = st.nextToken().replaceAll("[^a-zA-Z]", "");//as there are no numbers and special symbols in stopwordlist
                str3 = str3.toLowerCase();
                HashMap<String, Integer> M2;
                if (str3 != null && str3.length() > 1 && !a1.contains(str3)) {//checking whether it is a stopword or not
                    str4 = stem.stripAffixes(str3);//applying stemming to token
                    if (!M1.containsKey(str4)) {
                        M2 = new HashMap<String, Integer>();
                        M2.put(f.toString(), 1);//docc id,count
                        M1.put(str4, M2);//string,docc id,count
                    }
                    else {
                            if (((HashMap<String, Integer>) M1.get(str4)).containsKey(f.toString())) {//already existing docc id
                                int c = ((HashMap<String, Integer>) M1.get(str4)).get(f.toString());
                                    c =c+1;
                                    M1.get(str4).put(f.toString(), c);
                            } 
                            else {//adding to new docc id
                                M1.get(str4).put(f.toString(), 1);
                            }

                        }
                }
            }
            
                
            }
        }
        System.out.println("Tokenizing done,stored in HashMap along with their Term Frequency");
       // Set<Entry< String,HashMap<String,Integer>>> h1=M1.entrySet();
        //int p=1;
        //for(Entry key:h1){
                   // System.out.println("1. "+key.getKey()+"2. "+key.getValue());
                  // p++;
       // }
       // System.out.println(p);//number of terms in HashMap
    }
    public void consoleDisplay() throws IOException,FileNotFoundException{
        BufferedReader br=new BufferedReader(new InputStreamReader (System.in));
        System.out.println("Enter a word/query to search:");
        String str1;
        str1=br.readLine();
        ArrayList a2=new ArrayList();
        File f1=new File("C:/Users/Snehith/Desktop/stopwords.txt");//path for stopwords list
        br=new BufferedReader(new FileReader(f1));
        String str2;
        str2=br.readLine();
        while(str2!=null)
        {
            String s=str2.trim();
            a2.add(s);
            str2=br.readLine();
        }
        StringTokenizer st=new StringTokenizer(str1);
        String str5;
        ArrayList a3=new ArrayList();
        while(st.hasMoreTokens()){
            String token = st.nextToken().replaceAll("[^a-zA-Z]", "");//as there are no numbers and special symbols in stopwordlist
            token = token.toLowerCase();
            str5=stem.stripAffixes(token);
            a3.add(str5);
        }
        System.out.println("Stemming done to query along with elimination of stopword list");
        for(int i=0;i<a3.size();i++)
        {
            String str6=(String)a3.get(i);
            if(M1.containsKey(str6)){
                HashMap<String,Integer> M3=M1.get(str6);
                Set<String> keys= M3.keySet();
                int k=1;
                for(String key:keys)
                {
                    
                    System.out.println(key.substring(22));
                    k++;
                    if(k>=20) break;
                }
                //System.out.println("k is:"+k);
                
            }
            else{
                System.out.println("no files found for the corresponding token in query");
            }
                
        }
        }
}
    
   

            

    
        
        
        
        
         
 
    

    

