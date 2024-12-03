import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) throws IOException {
        File input = new File("2024/Day3/input.txt");
        Scanner reader = new Scanner(input);
        boolean enabled = true;

        int total = 0;
        int check = 0;

        while(reader.hasNextLine()){
            String line = reader.nextLine();
            
            while(line.length() > 0){
                System.out.print(line.indexOf("do()")+" "+line.indexOf("don't()")+" "+line.indexOf("mul(")+" ");
                if(line.indexOf("do()") != -1 && (line.indexOf("do()") < line.indexOf("don't()") || line.indexOf("don't()") == -1) && line.indexOf("do()") < line.indexOf("mul(")){
                    line = line.substring(line.indexOf("do()")+1);
                    enabled = true;
                    System.out.println("do");
                }
                else if(line.indexOf("don't()") != -1 && (line.indexOf("don't()") < line.indexOf("do()") || line.indexOf("do()") == -1) && line.indexOf("don't()") < line.indexOf("mul(")){
                    line = line.substring(line.indexOf("don't()")+1);
                    enabled = false;
                    System.out.println("don't");
                }
                else if(line.indexOf("mul(") != -1 && line.indexOf(",") != -1 && line.indexOf(")") != -1){
                    System.out.println("mul");
                    line = line.substring(line.indexOf("mul(")+4);

                    int num1 = 0;
                    int num2 = 0;
                    boolean failed = false;

                    try{
                        num1 = Integer.parseInt(line.substring(0, line.indexOf(",")));
                        num2 = Integer.parseInt(line.substring(line.indexOf(",")+1, line.indexOf(")")));
                    }catch(Exception e){
                        failed = true;
                    }


                    if(num1 > 999 || num2 > 999){
                        failed = true;
                    }

                    if(!failed){
                        if(enabled){
                            total += (num1*num2);
                        }
                        else{
                            check += (num1*num2);
                        }
                        line = line.substring(line.indexOf(")")+1);
                    }
                }
                else{
                    line = "";
                }
            }
        }

        System.out.println(total+" "+check+" "+(total+check));

        reader.close();
    }
}
