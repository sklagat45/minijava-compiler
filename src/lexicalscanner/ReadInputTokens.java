package lexicalscanner;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public class ReadInputTokens {

	public static void readInput(File f) {
		// TODO Auto-generated method stub
		 // This function reads input tokens from a file and stores it in a global string array called inputTokens
	    
	    
	        FileInputStream fis = null; 
	        BufferedInputStream bis = null;
	        DataInputStream dis = null;
	        try {
	            fis = new FileInputStream(f);
	            bis = new BufferedInputStream(fis);
	            dis = new DataInputStream(bis);
	            int count=0;
	            String s="";
	            while (dis.available() != 0) {
	                s += dis.readLine()+" ";
	            }
	            s+="$";  // Insert $ at end of input for matching wit $ in symbol in stack
	            inputTokens = s.split(" ");
	            for(int j=0;j<inputTokens.length;j++){
	                inputTokens[j]  = inputTokens[j].replaceAll("\\u00a0", "");
	            }
	            fis.close();
	            bis.close();
	            dis.close();
	        }
	        catch (Exception ex) {}
	    }

	}


