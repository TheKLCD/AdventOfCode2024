import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Day1{
    public static void main(String[] args) throws IOException{
        File file = new File("2024/Day1/input.txt");
        Scanner reader = new Scanner(file);

        int[] list1 = new int[1000];
        int[] list2 = new int[1000];

        for(int i = 0; i < 1000; i++){
            String[] line = reader.nextLine().split("   ");
            list1[i] = Integer.parseInt(line[0]);
            list2[i] = Integer.parseInt(line[1]);
        }

        list1 = sort(list1);
        list2 = sort(list2);

        int total = 0;
        
        for(int i = 0; i < 1000; i ++){
            total += Math.abs(list1[i]-list2[i]);
        }

        System.out.println(total);

        total = 0;

        for(int i = 0; i < 1000; i++){
            int current = list1[i];
            int amount = 0;

            for(int j = 0; j < 1000; j++){
                if(current == list2[j]){
                    amount++;
                }
            }

            total += current*amount;
        }

        System.out.println(total);

        reader.close();
    }

    public static int[] sort(int[] list){
        boolean sorted = false;

        while(!sorted){
            sorted = true;

            for(int i = 0; i < list.length; i++){
                for(int j = i+1; j < list.length; j++){
                    if(list[i] > list[j]){
                        sorted = false;
                        int temp = list[i];
                        list[i] = list[j];
                        list[j] = temp;
                    }
                }
            }
        }

        return list;
    }
}