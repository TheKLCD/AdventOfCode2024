import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

public class Day5 {
    public static void main(String[] args) throws IOException{
        File input = new File("2024/Day5/input.txt");
        Scanner reader = new Scanner(input);
        int[][] rules = new int[1176][2];
        int total = 0;
        int total2 = 0;

        for(int i = 0; i < rules.length; i++){
            String[] rule = reader.nextLine().split("|");
            rules[i][0] = Integer.parseInt(rule[0]+rule[1]);
            rules[i][1] = Integer.parseInt(rule[3]+rule[4]);
        }

        reader.nextLine();

        while(reader.hasNextLine()){
            String[] update = reader.nextLine().split(",");
            boolean pass = true;
            String problem = "";

            for(int i = 0; i < update.length-1; i++){
                for(int j = i+1; j < update.length; j++){
                    int num1 = Integer.parseInt(update[i]);
                    int num2 = Integer.parseInt(update[j]);

                    for(int[] rule:rules){
                        if(num1 == rule[1] && num2 == rule[0]){
                            pass = false;
                            problem = problem+Arrays.toString(rule);
                        }
                    }
                }
            }

            if(pass){
                total += Integer.parseInt(update[update.length/2]);
            }
            else{
                while(!pass){
                    pass = true;

                    for(int i = 0; i < update.length-1; i++){
                        for(int j = i+1; j < update.length; j++){
                            int num1 = Integer.parseInt(update[i]);
                            int num2 = Integer.parseInt(update[j]);
        
                            for(int[] rule:rules){
                                if(num1 == rule[1] && num2 == rule[0]){
                                    pass = false;
                                    
                                    String hold = update[i];
                                    update[i] = update[j];
                                    update[j] = hold;
                                    num1 = rule[0];
                                    num2 = rule[1];
                                }
                            }
                        }
                    }
                }

                total2 += Integer.parseInt(update[update.length/2]);
            }
        }
        
        System.out.println(total);
        System.out.println(total2);
        reader.close();
    }
}
