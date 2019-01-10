package sjsu.Katariya.cs146.project3;



import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;


public class RBTTester {

	
    
	
	
	
	

	   @Test
	   //Test the Red Black Tree
		public void testTree() {
			RedBlackTree rbt = new RedBlackTree();
	       rbt.insert("D");
	       rbt.insert("B");
	       rbt.insert("A");
	       rbt.insert("C");
	       rbt.insert("F");
	       rbt.insert("E");
	       rbt.insert("H");
	       rbt.insert("G");
	       rbt.insert("I");
	       rbt.insert("J");
	       assertEquals("DBACFEHGIJ", makeString(rbt));
	       String str=     "Color: 1, Key:D Parent: \n"+
	                       "Color: 1, Key:B Parent: D\n"+
	                       "Color: 1, Key:A Parent: B\n"+
	                       "Color: 1, Key:C Parent: B\n"+
	                       "Color: 1, Key:F Parent: D\n"+
	                       "Color: 1, Key:E Parent: F\n"+
	                       "Color: 0, Key:H Parent: F\n"+
	                       "Color: 1, Key:G Parent: H\n"+
	                       "Color: 1, Key:I Parent: H\n"+
	                       "Color: 0, Key:J Parent: I\n";
			assertEquals(str, makeStringDetails(rbt));
	           
	   }
   
	
    
    public static String makeString(RedBlackTree t)
    {
       class MyVisitor implements RedBlackTree.Visitor {
          String result = "";
          public void visit(RedBlackTree.Node n)
          {
             result = result + n.key;
          }
       };
       MyVisitor v = new MyVisitor();
       t.preOrderVisit(v);
       return v.result;
    }

    public static String makeStringDetails(RedBlackTree t) {
    	{
    	       class MyVisitor implements RedBlackTree.Visitor {
    	          String result = "";
    	          public void visit(RedBlackTree.Node n)
    	          {
    	        	  if(n.parent == null) // n is the root 
    	        		  result = result +"Color: "+n.color+", Key:"+n.key+" Parent: "+""+"\n";    
    	        	  else if(!(n.key).equals(""))
    	        		  result = result +"Color: "+n.color+", Key:"+n.key+" Parent: "+n.parent.key+"\n";
    	             
    	          }
    	       };
    	       MyVisitor v = new MyVisitor();
    	       t.preOrderVisit(v);
    	       return v.result;
    	 }
    }
 
    
    
    
    
    //add tester for spell checker
   @Test
  	public void testDictionary() throws IOException
   	{
   		RedBlackTree rbt = new RedBlackTree();
   		
   		File  file = new File("//Users//jay//CS146//Project3//Dictionary//src//sjsu//Katariya//cs146//project3//dictionary.txt");
   		FileReader fr = new FileReader(file);
   		BufferedReader br = new BufferedReader(fr);
	
 		
   		long dictionaryIntitiationTime = System.currentTimeMillis();
   		
   		String dictionaryWord = null;
   		while ((dictionaryWord = br.readLine()) != null) {
   			rbt.insert(dictionaryWord);
   	
   		}
		
   		
   		
          long dictionaryCompletionTime = System.currentTimeMillis();
   		
         long readingTime = dictionaryCompletionTime - dictionaryIntitiationTime;
         System.out.println("The time taken to complete the dictionary: " + readingTime);
   	 
   		br.close();
   		fr.close();
   		rbt.printTree();
   		long searchingInitTime = System.currentTimeMillis();
   		File  oneMorefile = new File("//Users//jay//CS146//Project3//Dictionary//src//sjsu//Katariya//cs146//project3//you fit into me.txt");
   		Scanner input = new Scanner(oneMorefile); 
 		int mistakes = 0;
   		while (input.hasNext()) {
   		      String word  = input.next();
   		      //System.out.println(word);
   		      if(!rbt.lookup(word))
   		      {
   		    	  System.out.println("mistake: "+word);
   		    	  mistakes++;
   		      }
   		    }
   		long searchingFinsihTime = System.currentTimeMillis();
   		
   		
   		long searchingTime = searchingFinsihTime - searchingInitTime;
   		System.out.println("The time taken to complete the searching: " + searchingTime);
   		System.out.println("Total number of mistakes:"+ mistakes);
   		input.close();
 		
   	}
    
   
   
   
    
    
    
 }
  
