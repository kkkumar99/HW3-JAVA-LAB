package HW3;
import java.io.*;
import java.util.*;
public class Keyword_match {
	public static String[] sort(String[]s,int n) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n-i-1;j++) {
				if(s[j].compareToIgnoreCase(s[j+1])>0) {
					String temp=s[j];
					s[j]=s[j+1];
					s[j+1]=temp;
				}
			}
		}
		return s;
	}
	public static int search(String[]s,String d,int n) {
		int i=0,j=n-1,m,f=-1;
		while(i<=j)
		{
			m=(i+j)/2;
			if(d.compareToIgnoreCase(s[m])==0) {
				f=m;break;
			}
			else if(d.compareToIgnoreCase(s[m])>0) {
				i=m+1;
			}
			else
				j=m-1;
		}
		return f;
	}
     public static void main(String[]args) throws IOException
     {
    	 int k=0;   //to store no. of keywords
    	 File keyw=new File("E:\\KishoreJava\\files\\HW3-unsorted-keywords.txt"); //to read a input filereader
    	 Scanner key=new Scanner(keyw);
    	 while(key.hasNextLine()) {
    		 k++;
    		 key.nextLine();
    	 }
    	 key.close();
    	 String[] keywords=new String[k];  //to store the keywords
    	 Scanner key1=new Scanner(keyw); 
    	 int i=0;
    	 while(key1.hasNextLine())
    	 {
    		 keywords[i]=key1.nextLine(); //reading and assigning keywords line by line
    		 i++;
    	 }
    	 key1.close();
    	 keywords=sort(keywords,k); //to sort the keywords using bubble sort
    	 File input=new File("E:\\KishoreJava\\files\\HW3-input-code.cpp");
    	 FileWriter output=new FileWriter("E:\\KishoreJava\\\\files\\HW3-output.txt",true);
    	 Scanner input2=new Scanner(input);
    	 i=0;
    	 int count=0;
    	 while(input2.hasNextLine()) {
    		String lines=input2.nextLine();
            int counter=0;
           	for(int l=0;l<k;l++) {
            		int index=lines.indexOf(keywords[l]);
            		int index1=lines.indexOf("//");
            		if(index1!=-1&&index1>index) {break;}
            		int len=keywords[l].length();
            		if((index!=-1)) {
            			if((index!=0)&&(lines.charAt(index-1)>='a'&&lines.charAt(index-1)<='z')&&(lines.charAt(index+len-1)>='a'&&lines.charAt(index+len-1)<='z')) {continue;}
            			if(counter==0)
            			{
            				counter=1;count++;
            				output.write("Line "+(i+1)+":"+keywords[l]+"("+(index)+")");
            				
            			}
            			else {
            				count++;
            				output.write(" "+keywords[l]+"("+(index)+")");
         
            			}
            		
            		}
            	}
            if(counter!=0)
            	output.write(System.getProperty( "line.separator" ));
    		i++;
    	 }
         output.write("No. of keywords found:"+(count-2));
    	 output.close();
    	 input2.close();
    }
}
