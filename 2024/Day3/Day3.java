import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) throws IOException {
        // Initialization
        File input = new File("2024/Day3/input.txt");
        Scanner reader = new Scanner(input);
        boolean enabled = true;
        String line = "";

        int total = 0;
        int disabled = 0;
        
        // Take in all input into one string
        while(reader.hasNextLine()){
            line = line+reader.nextLine();
        }

        // Loop while we still have part of the input unread
        while(line.length() > 0){
            // Find the index of the next instance of do, dont, and mult to know which is closest
            int mulIndex = line.indexOf("mul(");
            int doIndex = line.indexOf("do()");
            int dontIndex = line.indexOf("don't()");

            // Check which of do, dont, or mult is next in the input
            if(doIndex != -1 && (doIndex < dontIndex || dontIndex == -1) && doIndex < mulIndex){
                // do() is next in the line, move 1 past it and change enabled to true
                line = line.substring(line.indexOf("do()")+1);
                enabled = true;
            }
            else if(dontIndex != -1 && (dontIndex < doIndex || doIndex == -1) && dontIndex < mulIndex){
                // don't() is next in the line, move 1 past it and change enabled to false
                line = line.substring(line.indexOf("don't()")+1);
                enabled = false;
            }
            else if(line.indexOf("mul(") != -1 && line.indexOf(",") != -1 && line.indexOf(")") != -1){
                // mult( is next in the line, move past it to get the nums
                line = line.substring(line.indexOf("mul(")+4);

                // nums to multiply and failed to see if its valid
                int num1 = 0;
                int num2 = 0;
                boolean failed = false;

                // Try to convert up to the next comma and the next comma to the next ) to ints
                try{
                    num1 = Integer.parseInt(line.substring(0, line.indexOf(",")));
                    num2 = Integer.parseInt(line.substring(line.indexOf(",")+1, line.indexOf(")")));
                }catch(Exception e){
                    // If fails, change failed to true to signal its not valid
                    failed = true;
                }

                // Make sure the nums are no longer then 3 nums 
                if(num1 > 999 || num2 > 999){
                    failed = true;
                }

                // If the num conversion didn't fail, multiply them and add to total
                if(!failed){
                    // Split into two totals depending if enabled or not for part 2
                    if(enabled){
                        total += (num1*num2);
                    }
                    else{
                        disabled += (num1*num2);
                    }

                    // Move the line after the ) if the mult was valid
                    line = line.substring(line.indexOf(")")+1);
                }
            }
            else{
                line = "";
            }
        }

        // Print out the totals for both parts
        System.out.println("Total for Part 1: "+(total+disabled)+"\nTotal for Part 2: "+total);

        reader.close();
    }
}
