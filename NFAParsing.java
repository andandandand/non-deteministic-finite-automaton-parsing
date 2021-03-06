

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.IOException; 
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;


class Automaton {

    HashSet <Character> alphabet;
    Set <Object> finalStates;
    
    int initialStateIndex;
    int currentStateIndex;
    int numberOfStates;
    
    //1st dim = origin index
    //2dn dim = destiny index
    //string: alphabet symbols for transition
    HashSet<Character> [][] transitions; 
    
    
           
    Automaton(int numberOfStates) {
    
              this.numberOfStates =  numberOfStates;
              alphabet = new HashSet<>();
              finalStates = new HashSet<>();
              
              transitions = new HashSet[numberOfStates][numberOfStates];
              
              for (int i = 0; i < numberOfStates; i++){
                  for (int j = 0; j < numberOfStates; j++ ){
                  
                      transitions[i][j] = new HashSet<Character>();
                  }
              }
    }
    
    
    public String parseString(char [] inputString, int initialState){
    
        HashSet <Integer> currentStates;
        currentStates = new HashSet<Integer>();
        HashSet <Integer> nextStates;
        nextStates = new HashSet<Integer>();
        
       currentStates.add(initialState);
       
       //for each char in inputString
       for (int charIndex = 0; charIndex < inputString.length; charIndex++){
       
           char currentTransitionChar = inputString[charIndex];

           //for each state in currentStates        
           for (Integer originState: currentStates){
               
               //for each transition starting from originState, current char 
               for (int destinyStateIndex = 0; 
                         destinyStateIndex < numberOfStates; 
                         destinyStateIndex++){
                   
                   if (transitions[originState][destinyStateIndex]
                           .contains(currentTransitionChar)){
                   
                       nextStates.add(destinyStateIndex);
                   }
               }
            
           }
           currentStates.clear();
           currentStates.addAll(nextStates);
           nextStates.clear();
       }
       
       //test intersection between currentStates and finalStates
       
       //copy currentStates first
       Set <Integer> intersectionCurrentFinal = new HashSet<Integer>(currentStates); 
       intersectionCurrentFinal.retainAll(finalStates);
        
      if (intersectionCurrentFinal.size() > 0)
          return "Accepted";
      else
          return "Rejected";
        
    }
    

}

 public class NFAParsing {

    public static void main(String[] args) throws IOException {
      
        String line;
        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
	 
 
          //BufferedReader br = new BufferedReader(new FileReader("input.txt"));
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));          
	   try {
                
                 line = br.readLine();
                 
                 int numberOfCases = Integer.parseInt(line);
             
                 //loop for each case
                 for(int caseCounter = 1;
                         caseCounter <= numberOfCases;
                         caseCounter++) {

			  
                          String caseReport = "\nCase " +
                                               Integer.toString(caseCounter)
                                               +":\n";
                          
                          writer.write(caseReport); 
			  System.out.println("****************");	                   
			  System.out.println(caseReport);	                          

                          String [] StatesTransitionsFinals 
                                  = br.readLine().split(" ");
                          
                          
                          int numberOfStates 
                                  = Integer.parseInt(StatesTransitionsFinals[0]);
                                                                              
                          int numberOfTransitions
                                  = Integer.parseInt(StatesTransitionsFinals[1]);
                          
                          int numberOfFinalStates
                                  = Integer.parseInt(StatesTransitionsFinals[2]);
                          
                          
                          Automaton automaton = new Automaton(numberOfStates);
                          
                          //read final states
                          for (int finalSCounter=0; 
                                   finalSCounter < numberOfFinalStates;
                                   finalSCounter++){
                              
                              int finalState  = 
                                      Integer.parseInt(br.readLine());
                              
                              automaton.finalStates.add(finalState);
                                                          
                          }
                          //read transitions
                          for (int transitionsCounter = 0;
                                   transitionsCounter < numberOfTransitions;
                                   transitionsCounter++){
                          
                              String [] nodesAndChar = 
                                      br.readLine().split(" ");
                                   
                              int originNode = 
                                      Integer.parseInt(nodesAndChar[0]);
                              
                              int destinyNode = 
                                      Integer.parseInt(nodesAndChar[1]);
                              
                              char transitionChar =
                                      nodesAndChar[2].charAt(0);
                              
                              automaton.alphabet.add(transitionChar);
                             
                              automaton.transitions[originNode][destinyNode]
                                      .add(new Character(transitionChar));
                           
                                       
                          }
                          
                          int numberOfStrings = 
                                  Integer.parseInt(br.readLine());
                          
                          //read strings
                          for(int stringCounter = 0;
                                  stringCounter < numberOfStrings;
                                  stringCounter++) {
                          
                             char [] inputString =
                                     br.readLine().toCharArray();
                             
			     System.out.println("Input: " + new String(inputString));
                             writer.write("Input: " + new String(inputString) + "\n");
                             
                            int initialState = 0;
                            
                            String result =  
                                    automaton
                                    .parseString(inputString, 
                                                 initialState);
                            
			    System.out.println(result + "\n"); 
                            writer.write(result +"\n");
                          }
                          
                                
                 }
                 
               
                }

             finally {

                  br.close();
                  writer.close();

             }
 
    
    }
    
 }


