/*
 * Solution for Advent Of Code 2024 Day 4 https://adventofcode.com/2024/day/4
 * Solution by KLCD (Matthew Yeager)
 * Last Updated: 12/4/2024
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Day4 {
    public static void main(String[] args) throws IOException {
        // Initiation
        File input = new File("2024/Day4/input.txt");
        Scanner reader = new Scanner(input);

        char[][] map = new char[140][140];

        // Get the input from the file and map each character to a 2d array
        for(int i = 0; i < map.length; i++){
            String line = reader.nextLine();

            for(int j = 0; j < map[i].length; j++){
                map[i][j] = line.charAt(j);
            }
        }

        // Loop through each character and check if it starts with x for xmas
        int total = 0;

        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                // If the char is x, check to see if it is surrounded by mas in any direction
                if(map[i][j] == 'X'){
                    total += checkMap(i, j, map);
                }
            }
        }

        // Print out the total amount of times it says xmas
        System.out.println("Total for Part 1: "+total);
        total = 0;

        // Check each for each character is A for the center of X-MAS
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                // If A, check for the double mas
                if(map[i][j] == 'A'){
                    total += checkXMAS(i, j, map);
                }
            }
        }

        // Print total for part 2
        System.out.println("Total for Part 2: "+total);

        reader.close();
    }

    // Checks if mas is many in any direction from a point
    public static int checkMap(int x, int y, char[][] map){
        int total = 0;

        // Check if you can go in each direction, and if so see if it spells out MAS
        //Up left
        if(x >= 3 && y >= 3){
            if(map[x-1][y-1] == 'M' && map[x-2][y-2] == 'A' && map[x-3][y-3] == 'S'){
                total++;
            }
        }
        //Up
        if(y >= 3){
            if(map[x][y-1] == 'M' && map[x][y-2] == 'A' && map[x][y-3] == 'S'){
                total++;
            }
        }
        //Up right
        if(x <= 136 && y >= 3){
            if(map[x+1][y-1] == 'M' && map[x+2][y-2] == 'A' && map[x+3][y-3] == 'S'){
                total++;
            }
        }
        //Left
        if(x >= 3){
            if(map[x-1][y] == 'M' && map[x-2][y] == 'A' && map[x-3][y] == 'S'){
                total++;
            }
        }
        //Right
        if(x <= 136){
            if(map[x+1][y] == 'M' && map[x+2][y] == 'A' && map[x+3][y] == 'S'){
                total++;
            }
        }
        //Down left
        if(x >= 3 && y <= 136){
            if(map[x-1][y+1] == 'M' && map[x-2][y+2] == 'A' && map[x-3][y+3] == 'S'){
                total++;
            }
        }
        //Down
        if(y <= 136){
            if(map[x][y+1] == 'M' && map[x][y+2] == 'A' && map[x][y+3] == 'S'){
                total++;
            }
        }
        //Down Right
        if(x <= 136 && y <= 136){
            if(map[x+1][y+1] == 'M' && map[x+2][y+2] == 'A' && map[x+3][y+3] == 'S'){
                total++;
            }
        }

        // Return the total times it makes XMAS for that point
        return total;
    }

    // Check if the current center point can make the X-MAS structure
    public static int checkXMAS(int x, int y, char[][] map){
        int total = 0;

        // Track if each diagonal is a M or S
        boolean mUpLeft = false;
        boolean sUpLeft = false;
        boolean mUpRight = false;
        boolean sUpRight = false;
        boolean mDownLeft = false;
        boolean sDownLeft = false;
        boolean mDownRight = false;
        boolean sDownRight = false;

        //Up left
        if(x >= 1 && y >= 1){
            if(map[x-1][y-1] == 'M'){
                mUpLeft = true;
            }
            else if(map[x-1][y-1] == 'S'){
                sUpLeft = true;
            }
        }
        //Up right
        if(x <= 138 && y >= 1){
            if(map[x+1][y-1] == 'M'){
                mUpRight = true;
            }
            else if(map[x+1][y-1] == 'S'){
                sUpRight = true;
            }
        }
        //Down left
        if(x >= 1 && y <= 138){
            if(map[x-1][y+1] == 'M'){
                mDownLeft = true;
            }
            else if(map[x-1][y+1] == 'S'){
                sDownLeft = true;
            }
        }
        //Down Right
        if(x <= 138 && y <= 138){
            if(map[x+1][y+1] == 'M'){
                mDownRight = true;
            }
            else if(map[x+1][y+1] == 'S'){
                sDownRight = true;
            }
        }

        // See if MAS was made, if it was increase the total of MAS
        if(mUpLeft && sDownRight){
            total++;
        }
        if(mUpRight && sDownLeft){
            total++;
        }
        if(mDownLeft && sUpRight){
            total++;
        }
        if(mDownRight && sUpLeft){
            total++;
        }

        // If 2 MAS were made, return 1, if else return 0
        return total/2;
    }
}
