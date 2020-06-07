//P15/101552/2017		SAMUEL KIPLAGAT
//P15/101561/2017		IVY ANGELA
//P15/101938/2017		BRIAN MELITA

package lexicalscanner;
		
		import java.io.BufferedWriter;
		import java.io.File;  // Import the File class
		import java.io.FileNotFoundException;  // Import this class to handle errors
		import java.io.FileWriter;
		import java.io.IOException;
		import java.util.ArrayList;
		import java.util.Scanner; // Import the Scanner class to read text files
		import java.util.StringTokenizer;
		import java.util.regex.Matcher;
		import java.util.regex.Pattern;

		public class LexicalScanner {
			
			public static enum TokenType { 
			    // Token types cannot have underscores
				NUMBER("[1-9]+[0-9]*"),
				MULTIPLICATIONOP("[*]"),
				EQUALOP("[=]+[=]"),
				ASSIGNOP("[=]"),
				ADDITIONOP("[+]+[=]"),
				SUBTRACTIONOP("[-]"),
				MODULUSOP("[%]"),
				COMMENT("//.*"),
				OROP("[|]+[|]"),
				OPBRACKET("\\("),
				CLOSEBRACKET("\\)"), 
				STRING("\"[^\"]+\""), 
				KEYWORD("if|else|while|do|import|package|public|java|next|util|Scanner|println|void|class|console|continue|default|break|this|out|System|int|char|static|const|main]"),
				VARIABLE("[a-zA-Z]+[a-zA-Z0-9_]*");

			    public final String pattern;
 
			    private TokenType(String pattern) { 
			      this.pattern = pattern;
			    }  
			  }
			public static class Token {
			    public TokenType type; 
			    public String data;
			    

			    public Token(TokenType type, String data) {
			      this.type = type;
			      this.data = data;
			    }

			    @Override
			    public String toString() {
			    return String.format("(%s %s)", type.name(), data);
			    }
			  }
			
			  public static ArrayList<Token> lex(String input) {
				    // The tokens to return
				    ArrayList<Token> tokens = new ArrayList<Token>();

				    // Lexer logic begins here
				    StringBuffer tokenPatternsBuffer = new StringBuffer(); 
				    for (TokenType tokenType : TokenType.values())
				      tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
				    Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));

				    // Begin matching tokens
				    Matcher matcher = tokenPatterns.matcher(input);  
				    while (matcher.find()) {
				      if (matcher.group(TokenType.NUMBER.name()) != null) {
				        tokens.add(new Token(TokenType.NUMBER, matcher.group(TokenType.NUMBER.name())));
				        continue;
				      } else if (matcher.group(TokenType.ADDITIONOP.name()) != null) {
				        tokens.add(new Token(TokenType.ADDITIONOP, matcher.group(TokenType.ADDITIONOP.name())));
				        continue;
				        } else if (matcher.group(TokenType.MULTIPLICATIONOP.name()) != null) {
					        tokens.add(new Token(TokenType.MULTIPLICATIONOP, matcher.group(TokenType.MULTIPLICATIONOP.name())));
					        continue;
				        } else if (matcher.group(TokenType.SUBTRACTIONOP.name()) != null) {
					        tokens.add(new Token(TokenType.SUBTRACTIONOP, matcher.group(TokenType.SUBTRACTIONOP.name())));
					        continue;
				        } else if (matcher.group(TokenType.KEYWORD.name()) != null) {
					        tokens.add(new Token(TokenType.KEYWORD, matcher.group(TokenType.KEYWORD.name())));
					        continue;
				        } else if (matcher.group(TokenType.MODULUSOP.name()) != null) {
					        tokens.add(new Token(TokenType.MODULUSOP, matcher.group(TokenType.MODULUSOP.name())));
					        continue;
				        } else if (matcher.group(TokenType.COMMENT.name()) != null) {
					        tokens.add(new Token(TokenType.COMMENT, matcher.group(TokenType.COMMENT.name())));
					        continue;
				        } else if (matcher.group(TokenType.ASSIGNOP.name()) != null) {
					        tokens.add(new Token(TokenType.ASSIGNOP, matcher.group(TokenType.ASSIGNOP.name())));
					        continue;
				        } else if (matcher.group(TokenType.OPBRACKET.name()) != null) {
					        tokens.add(new 	Token(TokenType.OPBRACKET, matcher.group(TokenType.OPBRACKET.name())));
					        continue;
				        } else if (matcher.group(TokenType.CLOSEBRACKET.name()) != null) {
					        tokens.add(new Token(TokenType.CLOSEBRACKET, matcher.group(TokenType.CLOSEBRACKET.name())));
					        continue;
				        } else if (matcher.group(TokenType.STRING.name()) != null) {
					        tokens.add(new Token(TokenType.STRING, matcher.group(TokenType.STRING.name())));
					        continue;
				        } else if (matcher.group(TokenType.VARIABLE.name()) != null) {
					        tokens.add(new Token(TokenType.VARIABLE, matcher.group(TokenType.VARIABLE.name())));
					        continue;
				        } else if (matcher.group(TokenType.EQUALOP.name()) != null) {
					        tokens.add(new Token(TokenType.EQUALOP, matcher.group(TokenType.EQUALOP.name())));
					        continue;
				        } else if (matcher.group(TokenType.OROP.name()) != null) {
					        tokens.add(new Token(TokenType.OROP, matcher.group(TokenType.OROP.name())));
					        continue;
				      }
				    }
				    return tokens;
				  }
		  public static void main(String[] args)throws IOException {
		    try {
		      File myObj = new File("C:\\Users\\user\\eclipse-workspace\\compiler\\src\\compiler\\compilerread.txt");
		      String outfilename = "C:\\\\Users\\\\user\\\\eclipse-workspace\\\\compiler\\\\token_file";
		      
		      
		      File file =new File(outfilename);
		      file.createNewFile(); 
		      Scanner myReader = new Scanner(myObj);   
		        
		   // create a buffer writer tokDataB 
		      FileWriter tokData = new FileWriter(outfilename,false);
		      BufferedWriter tokDataB = new BufferedWriter(tokData);
		      
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		      
		        StringTokenizer tokenizer=new StringTokenizer(data,"\n");
		        while(tokenizer.hasMoreTokens()){ 
		          System.out.println(tokenizer.nextToken());
		       
		      
		          // Create tokens and print them
		          ArrayList<Token> tokens = lex(data);
		          for (Token token : tokens)
		        	  System.out.println("\n"+token);
		          
		       // write one token per line to output file
		            tokDataB.write(tokens.toString() + "\n");
		            tokDataB.write("\n");
		             		          
		      }
		        
		      }
		      tokDataB.close();
		      myReader.close(); 
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		    ParserHelper.helper(args);
		  
		  }

		}

	




	


