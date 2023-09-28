// Author      : William Blaylock, AJ Ross, and Abby Wall
// Class     : CS4500 section 1
// Date          : September 22, 2023
// Files     : No external files used. Main.java houses main function, indata is read from,
//           and outdata is written to.
// References  : Website's  are indicated throughout program, Used mainly for syntax and structure.
//
// Description : Two people will be initialized using an input file called indata.txt. it will read the data
//              and use it to perform five repetitions for three experiments, using specified values for maxmoves,  
//              max grid coords, the movement protocol, and repetions. When the experiments are complete, data is written to a
//              file with data sorted in the proper tabled format.      
// Description of data structure: Person and PersonGPS classes
//Resources:
//for the random number generator; random number generator originally written in C++ 17 by Abby Wall for HW 1;
//rewritten for Java language; additional source:
// https://www.geeksforgeeks.org/generating-random-numbers-in-java/
//allow for input chars to be read; source:
// https://www.geeksforgeeks.org/java-io-bufferedreader-class-java/#:~:text=Reads%20text%20from%20a%20character,large%20enough%20for%20most%20purposes.
//final declarations to keep protocol values from being altered in any way by program
//source: https://www.theserverside.com/video/Why-we-use-static-final-in-Java-for-constants#:~:text=The%20static%20keyword%20means%20the,to%20create%20a%20constant%20value.
//- Website explaining proper file handeling in java
//https://www.geeksforgeeks.org/file-handling-in-java/
//- Website with RegEx resources and tester
// https://regexr.com/
//allow for input chars to be read; source: 
// https://www.geeksforgeeks.org/java-io-bufferedreader-class-java/#:~:text=Reads%20text%20from%20a%20character,large%20enough%20for%20most%20purposes.
//============================================================================
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;
import java.io.BufferedReader;
import java.util.Scanner;               //import for reading user input
import java.io.File;                    //import for reading file
import java.io.FileNotFoundException;   //import for handling file errors
import java.io.IOException;             //import to handle excpetion errors
import java.io.PrintWriter;             //import to write to output file

class MovementMechanics {
    private int maxCoord;
    private int maxMoves;
    private int repetitions;
    private int [][] cGrid; //cGrid represents Cartesian Grid of the woods
    private Random random;
    
    //final declarations to keep protocol values from being altered in any way by program
    private static final int North = 1;
    private static final int East = 2;
    private static final int South = 3;
    private static final int West = 4;
    private static final int NorthEast = 5;
    private static final int SouthEast = 6;
    private static final int SouthWest = 7;
    private static final int NorthWest = 8;
    
    static class Person {
        
        private int x;
        private int y;
        
        public Person(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
    }


public MovementMechanics(int maxMoves, int maxCoord, int repetitions){
    this.maxCoord = maxCoord;
    this.maxMoves = maxMoves;
    this.repetitions = repetitions;
    this.cGrid = new int[maxCoord + 1][maxCoord + 1];
    this.random = new Random();
}


private void protocol4Movement(Person person) {
    //protocol 4 here
    //update position
    int direction = random.nextInt(4) + 1;
    switch (direction) {
        case North: //North case statement 
            if (person.getY() < maxCoord) {
                person.y++;
            }
            break;
            case East: //East case statement
                if (person.getX() < maxCoord) {
                    person.x++;
                }
                break;
            case South: //South case statement
                if (person.getY() > 0) {
                    person.y--;
                }
                break;
            case West: // West case statement
            if (person.getX() > 0) {
                person.x--;
            }
            break;
    }
}


private void protocol8Movement(Person person) {
   int direction = random.nextInt(8) + 1;
   switch (direction) {
       case North: //North (0,1) Cartesian
        if (person.getY() < maxCoord) {
            person.y++;
        }
        break;
       case East: //East  (1,0 Cartesian)
        if (person.getX() < maxCoord) {
            person.x++;
        }
        break;
       case South: //South (0,-1 Cartesian)
           if (person.getY() > 0) {
               person.y--;
           }
       break;
       case West: //West (-1,0 Cartesian)
            if (person.getX() > 0) {
                person.x--;
            }
        break;
       case NorthEast: //NorthEast (1,1 Cartesian)
        if (person.getX() < maxCoord && person.getY() < maxCoord) {
            person.x++;
            person.y++;
        }
        break;
       case SouthEast: //SouthEast (1, -1 Cartesian)
        if (person.getX() < maxCoord && person.getY() > 0) {
            person.x++;
            person.y--;
        }
           break;
       case SouthWest: //SouthWest (-1, -1 Cartesian)
        if (person.getX() > 0 && person.getY() > 0) {
            person.x--;
            person.y--;
        }
        break;
       case NorthWest: //NorthWest (-1, 1 Cartesian)
        if (person.getX() > 0 && person.getY() < maxCoord) {
            person.x--;
            person.y++;
        }
        break;
   }
}

public int simulate(int D, int protocol, int maxMoves, int repetitions) {
   int totalMoves = 0;
   //variables and data structures
   //outer loops
   for (int rep = 0; rep < repetitions; rep++) {
       //rep variables
       int movesThisRep = 0;
       Person personA = new Person(0,0);
       Person personB = new Person(D, D);
       //inner loop 
       for (int move = 0; move < maxMoves; move++) {
           if (protocol == 4) {
               protocol4Movement(personA);
           } else if (protocol == 8) {
               protocol8Movement(personA);
           }
       
            if (protocol == 4) {
               protocol4Movement(personB);
           } else if (protocol == 8) {
               protocol8Movement(personB);
           }
           //update any variables or data structures
       }
       
       //calculate rep total moves 
       movesThisRep = Math.abs(personA.getX() - 0) + Math.abs(personA.getY() - 0)
                + Math.abs(personB.getX() - D) + Math.abs(personB.getY() - D);
       totalMoves += movesThisRep;
   }
    
    int avgMoves  = totalMoves / repetitions; 
    return avgMoves;
}

}

public class Main { 
    static int protocol;
    static int maxMoves;
    static int reps;
    static int[] gridSizes = new int[5];
    
    public static void main(String[] args) {
    if (!validateAndExtractParams()) {
        System.out.println("Invalid input data. Exiting program.");
        return; // Exit if the data is invalid
    }
        ArrayList<String> simulationResults = new ArrayList<>(); // To store simulation results
        try {
            BufferedReader reader = new BufferedReader(new FileReader("indata.txt"));
            // Read grid dimensions (D0, D1, D2, D3, D4) from the first line
            int[] gridDimensions = Arrays.stream(reader.readLine().split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            // Read protocol (P), maximum moves (M), and repetitions (R) from the second line
         
            String[] protocolData = reader.readLine().split(",");
            int protocol = Integer.parseInt(protocolData[0]);
            int maxMoves = Integer.parseInt(protocolData[1]);
            int repetitions = Integer.parseInt(protocolData[2]);

            // Experiment #1: Varying grid dimensions
            simulationResults.add("Experiment #1 changes the dimensions of the grid. Other variables are held constant.");
            simulationResults.add("DIMENSIONS");
            simulationResults.add("Maximum Moves Number of Repeats Protocol Lowest Moves Highest Moves Average Moves");

            for (int D : gridDimensions) {
                int avgMoves = new MovementMechanics(maxMoves, D, repetitions).simulate(D, protocol, maxMoves, repetitions);
                simulationResults.add(String.format("%d %d %d %d %d %d %d", D, maxMoves, repetitions, protocol, avgMoves, avgMoves, avgMoves));
            }
            // Read repetitions (R0, R1, R2, R3, R4) from the third line
            int[] repetitionsValues = Arrays.stream(reader.readLine().split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
                    
            // Read dimensions (D), maximum moves (M), and protocol (P) for Experiment #2
            String[] experiment2Data = reader.readLine().split(",");
            int D2 = Integer.parseInt(experiment2Data[0]);
            protocol = Integer.parseInt(experiment2Data[1]);
            maxMoves = Integer.parseInt(experiment2Data[2]);

            // Experiment #2: Varying repetitions
            simulationResults.add("\nExperiment #2 changes the number of wanderings (repeats) for each row. Other variables are held constant.");
            simulationResults.add("NUMBER OF REPEATS Dimensions Maximum Moves Protocol Lowest Moves Highest Moves Average Moves");

            for (int R : repetitionsValues) {
                int avgMoves = new MovementMechanics(maxMoves, D2, R).simulate(D2, protocol, maxMoves, R);
                simulationResults.add(String.format("%d %d %d %d %d %d %d", R, D2, maxMoves, protocol, avgMoves, avgMoves, avgMoves));
            }
            
            // Read protocol (P0, P1, P2, P3) from the fifth line
            int[] protocolValues = Arrays.stream(reader.readLine().split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            // Read dimensions (D), maximum moves (M), and repetitions (R) for Experiment #3
            String[] experiment3Data = reader.readLine().split(",");
            int D3 = Integer.parseInt(experiment3Data[0]);
            maxMoves = Integer.parseInt(experiment3Data[1]);
            repetitions = Integer.parseInt(experiment3Data[2]);

            // Experiment #3: Varying protocols
            simulationResults.add("\nExperiment #3 changes the protocols. Other variables are held constant.");
            simulationResults.add("PROTOCOL Dimensions Maximum Moves Repeats Lowest Moves Highest Moves Average Moves");

            for (int P : protocolValues) {
                int avgMoves = new MovementMechanics(maxMoves, D3, repetitions).simulate(D3, P, maxMoves, repetitions);
                simulationResults.add(String.format("PROTOCOL %d %d %d %d %d %d %d", P, D3, maxMoves, repetitions, avgMoves, avgMoves, avgMoves));
            }

            reader.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        // Write results to the output file "outdata.txt"
        try {
            FileWriter writer = new FileWriter("outdata.txt");
            for (String result : simulationResults) {
                writer.write(result + "\n");
            }
            writer.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    
    private static boolean validateAndExtractParams() {
        try {
	        File Obj = new File("indata.txt");
	        Scanner reader = new Scanner(Obj);
		    PrintWriter writer = new PrintWriter("outdata.txt");
		    int lineNum = 0;
		    
	        while (reader.hasNextLine()) {
	            lineNum++;
	            String data = reader.nextLine();
	            System.out.println("Line #" + lineNum + ": " + data); //Print Current Line
	            
	            String validationError = isValidData(data, lineNum);
	            if (!validationError.isEmpty()) {
	                System.out.println("Error in 'indata.txt' on line #" +
	                                    lineNum + ": " + validationError);
	                reader.close();
	                writer.close();
	                return false;            //Halts the program on invalid data.
	            }
	            
	            //Parse data by line number specifications
	            if (lineNum == 1) {         //Extract gridSizes from line1
	                String[] line1Data = data.split(",");
	                for (int i = 0; i < 5; i++) {
	                    gridSizes[i] = Integer.parseInt(line1Data[i]);
	                }
	            } else if (lineNum == 2) {  //Extract proto and maxMoves from line2
	                String[] line2Data = data.split(",");
	                protocol = Integer.parseInt(line2Data[0]);
	                maxMoves = Integer.parseInt(line2Data[1]);
	                reps = Integer.parseInt(line2Data[2]);
	            }
	        }
	      
	        if (lineNum != 6) {             //Checking for exactly 6 lines.
	            System.out.println("Error: 'indata.txt' should have exactly 6 lines of data.");
	            return false;               //Halts the program on invalid data.
	        }
	        
	        //Displays extracted variables on valid input file for testing
	        System.out.println("Valid Input Data.");
    	    System.out.println("Protocol: " + protocol + "| MaxMoves: " + maxMoves
    	                        + "| Repitions : " + reps);
            System.out.println("Grid Sizes:");
            for (int i = 0; i < 5; i++) {
                System.out.println("Grid #" + (i + 1) + " Size: " + gridSizes[i]);
            }
            System.out.println("Please check outdata.txt after completion for results.");
                
	    } catch (FileNotFoundException e) {
    	    System.out.println("An error occured. File not Found");
    	    e.printStackTrace();
    	}
    	return true;
    }
    
    //Validation Logic
	private static String isValidData(String data, int lineNum) {
	    if (data.endsWith(",")) {               //Checks if line ends with a comma
	        return "Line ends with a comma.";
	    }
	    
	    //checks if the line contains chars other than (+/-) number or commas
	    if (!data.matches("^-?[0-9]+(,-?[0-9]+)*$")) {   
	        return "Line has characters besides numbers and commas, or a comma with no value.";
	    }
	    
	    //Split data by commas & check if all numbers are positive
	    String[] numbers = data.split(","); //splits data by comma
	    for (String number : numbers) {     //Checks for non-numeric chars
	        if (!number.matches("\\d+$") || Integer.parseInt(number) <= 0) {  
	            return "Line contains non-positive integer.";               
	        }
	       
	    }
	    
	    //Specific Validaiton for lines 1 and 2
	    if (lineNum == 1) {
	        String[] vals  = data.split(",");
	        if (vals.length != 5) {         //Checking for 5 ints on line 1
	            return "Line 1 should have exactly 5 postive integers.";
	        }
	        for (int i = 1; i < vals.length; i++) {
	            if (Integer.parseInt(vals[i]) <= Integer.parseInt(vals[i - 1])) {
	                return "Integer in line 1 should increse from left to right";
	            }
	        }
	    } else if (lineNum == 2) {
    	        String[] vals = data.split(",");
    	        if (vals.length != 3) {         //Checking for 3 ints on line 2
    	            return "Line 2 should have exactly 3 positive integers.";
    	        }
    	        //Checks that protocol is either 4 or 8
    	        int protocolVal = Integer.parseInt(vals[0]);
    	        if (protocolVal != 4 && protocolVal != 8) {   //Checking for proto 4 or 8
    	            return "The first integer of line 2 should be either 4 or 8.";
    	        }
    	        //Checks that maxMoves is <= 1,000,000
    	        int maxMovesVal = Integer.parseInt(vals[1]);
    	        if (maxMovesVal > 1000000) {
    	            return "Maximum Moves should not be above 1,000,000.";
    	        }
    	        //Checks that repititions is <= 10,000
    	        int repsVal = Integer.parseInt(vals[2]);
    	        if (repsVal > 10000) {
    	            return "Repitions should not be above 10,000.";
    	        }
	    } 
	    return "";                          //Valid Line data 
	}
}