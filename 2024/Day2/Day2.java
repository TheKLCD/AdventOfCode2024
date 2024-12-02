import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Day2{
    public static void main(String[] args) throws IOException {
        File input = new File("2024/Day2/input.txt");
        Scanner reader = new Scanner(input);

        int total = 0;
        int check = 0;

        while(reader.hasNextLine()){
            String inLine = reader.nextLine();
            String[] line = inLine.split(" ");
            ArrayList<String> nums = new ArrayList<String>();
            boolean passed = true;
            boolean passedWithChange = false;

            for(String num:line){
                nums.add(num);
            }

            int test = checkSafe(nums);

            if(test == -1){
                total++;
            }else{
                String x = nums.remove(test);
                int y = checkSafe(nums);

                if(y == -1){
                    total++;
                    passedWithChange = true;
                }
                else{
                    String e = nums.toString();
                    nums.add(test, x);
                    x = nums.remove(test+1);
                    
                    int z = checkSafe(nums);

                    if(z == -1){
                        total++;
                        passedWithChange = true;
                    }
                    else{
                        int a = -2;

                        if(test!=0){
                            nums.add(test+1, x);
                            x = nums.remove(test-1);
                    
                            a = checkSafe(nums);
                        }

                        if(a == -1){
                            total++;
                            passedWithChange = true;
                        }
                        else{
                            check++;
                            passed = false;
                            System.out.println(inLine+" | "+test+" "+(a+1)+" "+(y+1)+" "+(z));
                        }
                    }
                }
            }

        }
        
        System.out.println(total+" "+check);

        reader.close();
    }

    public static int checkSafe(ArrayList<String> level){
            int increasing = 0;
            boolean safe = true;
            int problem = -1;

            if(Integer.parseInt(level.get(0)) > Integer.parseInt(level.get(1))){
                increasing = 1;
            }
            else if(Integer.parseInt(level.get(0)) < Integer.parseInt(level.get(1))){
                increasing = 2;
            }
            else{
                problem = 0;
                safe = false;
            }

            for(int i = 0; i < level.size()-1; i++){
                int differnce = Integer.parseInt(level.get(i))-Integer.parseInt(level.get(i+1));

                if(increasing == 1 && problem == -1){
                    if(differnce < 1 || differnce > 3){
                        safe = false;
                        problem = i;
                    }
                }
                else if(increasing == 2 && problem == -1){
                    if(differnce < -3 || differnce > -1){
                        safe = false;
                        problem = i;
                    }
                }
            }

            return problem;
    }
}