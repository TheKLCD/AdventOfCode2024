import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Day2{
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        // Initialization
        File input = new File("2024/Day2/input.txt");
        Scanner reader = new Scanner(input);

        int total = 0;
        int totalWithRemoval = 0;

        // Go through each line of data
        while(reader.hasNextLine()){
            // Take in the current line and split it into each value
            String[] line = reader.nextLine().split(" ");
            ArrayList<String> nums = new ArrayList<String>();

            // Put the values into the arraylist
            for(String num:line){
                nums.add(num);
            }

            // See if the list of numbers pass the test (-1 for pass, or returns where it fails)
            int test = checkSafe(nums);

            if(test == -1){
                // If Passed without change add to part 1 total
                total++;
            }else{
                //Make a copy removing the one on, the one after, and the one before
                ArrayList<String> removedOn = (ArrayList<String>)nums.clone();
                removedOn.remove(test);

                ArrayList<String> removedAfter = (ArrayList<String>)nums.clone();
                removedAfter.remove(test+1);

                ArrayList<String> removedBefore = (ArrayList<String>)nums.clone();

                // Make sure test != 0 before removing one before
                if(test != 0){
                    removedBefore.remove(test-1);
                }

                // If any of those pass, add 1 to the removal total
                if(checkSafe(removedOn) == -1 || checkSafe(removedBefore) == -1 || checkSafe(removedAfter) == -1){
                    totalWithRemoval++;
                }
            }

        }
        
        // Print out results for each Part
        System.out.println("Total for Part 1: "+total+"\nTotal For Part 2: "+(total+totalWithRemoval));

        reader.close();
    }

    // Check if the list of data follows all of them increasing or all decreasing in ranges of 1 to 3 (ex 7 6 4 2 1)
    public static int checkSafe(ArrayList<String> level){
        // Get values to track increasing or decreasing and final output
        int changeDir = 0;
        int problem = -1;

        // Check to see if the pattern is increasing or decreasing from the first 2 values, 1 for decreasing, 2 for increasing
        if(Integer.parseInt(level.get(0)) > Integer.parseInt(level.get(1))){
            changeDir = 1;
        }
        else if(Integer.parseInt(level.get(0)) < Integer.parseInt(level.get(1))){
            changeDir = 2;
        }
        else{
            problem = 0;
        }

        // Loop through each value in the array and check the difference between the current and next value
        for(int i = 0; i < level.size()-1; i++){
            // Find the difference between the current and next value
            int difference = Integer.parseInt(level.get(i))-Integer.parseInt(level.get(i+1));

            // Make sure its 1 to 3 more or less depending on the direction, if it doesn't work set problem to current i value
            if(changeDir == 1 && problem == -1){
                if(difference < 1 || difference > 3){
                    problem = i;
                }
            }
            else if(changeDir == 2 && problem == -1){
                if(difference < -3 || difference > -1){
                    problem = i;
                }
            }
        }

        // Return where the rule breaks, if it doesn't break return -1
        return problem;
    }
}