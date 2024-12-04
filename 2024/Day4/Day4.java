import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Day4 {
    public static void main(String[] args) throws IOException {
        File input = new File("2024/Day4/input.txt");
        Scanner reader = new Scanner(input);

        char[][] map = new char[140][140];
        
        for(int i = 0; i < map.length; i++){
            String line = reader.nextLine();

            for(int j = 0; j < map[i].length; j++){
                map[i][j] = line.charAt(j);
            }
        }

        int total = 0;

        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j] == 'X'){
                    total += checkMap(i, j, map);
                }
            }
        }

        System.out.println(total);

        int total2 = 0;

        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j] == 'A'){
                    total2 += checkXMAS(i, j, map);
                }
            }
        }

        System.out.println(total2);
    }

    public static int checkMap(int x, int y, char[][] map){
        int total = 0;

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

        return total;
    }

    public static int checkXMAS(int x, int y, char[][] map){
        int total = 0;
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

        return total/2;
    }
}
