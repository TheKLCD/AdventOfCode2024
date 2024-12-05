/*
 * Solution for Advent Of Code 2024 Day 5 https://adventofcode.com/2024/day/5
 * Solution by KLCD (Matthew Yeager)
 * Last Updated: 12/5/2024
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day5 {
    public static void main(String[] args) throws IOException{
        // Initiation
        File input = new File("2024/Day5/input.txt");
        Scanner reader = new Scanner(input);
        String ruleInput = "";
        boolean done = false;

        int total = 0;
        int total2 = 0;

        // Loop through the input section for the rules and add it into the string, stop when you get to the blank line
        while(!done){
            String rule = reader.nextLine();

            // If blank, move to next step
            if(!rule.equals("")){
                ruleInput = ruleInput+rule+"\n";
            }
            else{
                done = true;
            }
        }

        // Cut off the last new line from the input
        ruleInput = ruleInput.substring(0, ruleInput.length()-1);

        // Make arrays to hold the rule, and numbers to the rule
        String[] ruleCombo = ruleInput.split("\n");
        int[][] rules = new int[ruleCombo.length][2];

        for(int i = 0; i < rules.length; i++){
            // Add each number from the rule to rules (72|63 --> [72, 63])
            rules[i][0] = Integer.parseInt(ruleCombo[i].substring(0, 2));
            rules[i][1] = Integer.parseInt(ruleCombo[i].substring(3));
        }

        // Check each update against the rules
        while(reader.hasNextLine()){
            String[] update = reader.nextLine().split(",");
            
            // Save the result of the check
            int result = checkUpdate(update, rules, false);

            //If the result is good the first time, add to part 1 total
            if(result != -1){
                total += result;
            }
            else{
                // If fails the first time, set it to part 2 for part 2 total
                total2 += checkUpdate(update, rules, true);
            }
        }
        
        // Print out the totals for both parts
        System.out.println("Total for Part 1: "+total+"\nTotal for Part 2: "+total2);

        reader.close();
    }

    // Checks if current update of numbers follows all the rules
    public static int checkUpdate(String[] update, int[][] rules, boolean part2){
        boolean pass = true;
        int[] problem = new int[2];

        // Loop through each number in the update, check if any number after it is part 2 to a rule
        for(int i = 0; i < update.length-1; i++){
            for(int j = i+1; j < update.length; j++){
                int num1 = Integer.parseInt(update[i]);
                int num2 = Integer.parseInt(update[j]);

                for(int[] rule:rules){
                    // If the two nums are in a rule and in the wrong order, set pass to false
                    if(num1 == rule[1] && num2 == rule[0] && pass){
                        pass = false;
                        problem[0] = i;
                        problem[1] = j;
                    }
                }
            }
        }

        // If in part 1 and fails return -1 to show it failed, otherwise pass the middle number of the update
        if(!part2 && !pass){
            return -1;
        }
        else if(pass){
            return Integer.parseInt(update[update.length/2]);
        }
        
        // If in part 2 and failed, switch the numbers were the rule break occurred, and try again
        String hold = update[problem[0]];
        update[problem[0]] = update[problem[1]];
        update[problem[1]] = hold;

        // Recursively loops until the update passes
        return checkUpdate(update, rules, part2);
    }
}
