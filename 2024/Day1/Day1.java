/*
 * Solution for Advent Of Code 2024 Day 1 https://adventofcode.com/2024/day/1
 * Solution by KLCD (Matthew Yeager)
 * Last Updated: 12/4/2024
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import methods.Methods;

public class Day1{
    public static void main(String[] args) throws IOException{
        // Initialization
        File file = new File("2024/Day1/input.txt");
        Scanner reader = new Scanner(file);

        // Make Arrays to hold each list of data, list 1 for left side list 2 for right side
        int[] list1 = new int[1000];
        int[] list2 = new int[1000];

        // Take in data
        for(int i = 0; i < 1000; i++){
            String[] line = reader.nextLine().split("   ");
            list1[i] = Integer.parseInt(line[0]);
            list2[i] = Integer.parseInt(line[1]);
        }

        // Sort so we can compare in order from least to most
        list1 = Methods.sortLowToHigh(list1);
        list2 = Methods.sortLowToHigh(list2);

        // Go through the list comparing the matching data from list 1 and 2 to find the difference in each line
        int total = 0;
        
        for(int i = 0; i < 1000; i++){
            total += Math.abs(list1[i]-list2[i]);
        }

        // Output the total for part 1
        System.out.println("Total for Part 1: "+total);

        // Reset total for part 2
        total = 0;

        // Get the similarity score for each value by multiplying the amount of times it appears on list 2 by its base value (ex: 3 appears 4 times, for a score of 12)
        for(int i = 0; i < 1000; i++){
            // Get the current value from list 1
            int current = list1[i];
            int amount = 0;

            //Loop through list 2 and count each time the value appears
            for(int j = 0; j < 1000; j++){
                if(current == list2[j]){
                    amount++;
                }
            }

            // Multiply the amount it appears by the value to get the similarity score for this value
            total += current*amount;
        }

        // Output the total for part 2
        System.out.println("Total for Part 2: "+total);

        reader.close();
    }
}