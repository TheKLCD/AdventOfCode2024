import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Day6 {
    public static void main(String[] args) throws IOException{
        File input = new File("2024/Day6/input.txt");
        Scanner reader = new Scanner(input);
        String mapString = "";

        while(reader.hasNextLine()){
            mapString = mapString+reader.nextLine()+"\n";
        }

        mapString = mapString.substring(0, mapString.length()-1);

        String[] lines = mapString.split("\n");
        char[][] map = new char[lines.length][lines[0].length()];
        int[] position = new int[2];
        int facing = 0;

        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                map[i][j] = lines[i].charAt(j);

                if(map[i][j] == '^'){
                    position[0] = i;
                    position[1] = j;
                }
            }
        }

        boolean done = false;
        int total = 1;
        int total2 = 0;
        int check = 0;
        int origanal0 = position[0];
        int origanal1 = position[1];
        ArrayList<int[]> options = new ArrayList<int[]>();
        boolean test = true;
        char[][] mapOrignal = new char[map.length][map[0].length];

        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                mapOrignal[i][j] = map[i][j];
            }
        }

        options.add(new int[]{position[0], position[1]});

        while(!done){
            Object[] update = updateMap(map, position, facing);

            if(update[0] != null && update[0].equals(-1)){
                done = true;
            }
            else if(update[0] != null && update[0].equals(1)){
                total++;
            }
            else{
                map = (char[][]) update[1];
                position = (int[]) update[2];
                facing = (int) update[3];
            }
        }

        for(int i = 0; i < mapOrignal.length; i++){
            for(int j = 0; j < mapOrignal[i].length; j++){
                if(mapOrignal[i][j] == '.'){
                    char[][] tempMap = new char[mapOrignal.length][mapOrignal[0].length];

                    for(int k = 0; k < mapOrignal.length; k++){
                        for(int m = 0; m < mapOrignal[k].length; m++){
                            if(k == i && m == j){
                                tempMap[k][m] = 'O';
                            }
                            else{
                                tempMap[k][m] = mapOrignal[k][m];
                            }
                        }
                    }

                    done = false;
                    boolean loops = false;
                    position = new int[]{origanal0, origanal1};
                    facing = 0;
                    ArrayList<int[]> history = new ArrayList<int[]>();

                    while(!done){
                        history.add(new int[]{position[0], position[1], facing});
                        Object[] update = updateMap(tempMap, position, facing);

                        tempMap = (char[][]) update[1];
                        position = (int[]) update[2];
                        facing = (int) update[3];
            
                        if(update[0] != null && update[0].equals(-1)){
                            done = true;
                        }
                        else{
                            for(int[] hist:history){
                                if(hist[0] == position[0] && hist[1] == position[1] && hist[2] == facing){
                                    tempMap[position[0]][position[1]] = '8';
                                    loops = true;
                                    done = true;
                                }
                            }
                        }

                        if(loops){
                            total2++;
                        }
                    }
                }
                else{
                    check++;
                }
            }
        }

        System.out.println(total);
        System.out.println(total2);

        reader.close();
    }

    public static Object[] updateMap(char[][] map, int[] position, int facing) throws IOException{
        Object[] result = new Object[6];

        if(facing == 0){
            if(position[0] == 0){
                result[0] = -1;
            }
            else if(map[position[0]-1][position[1]] == '#' || map[position[0]-1][position[1]] == 'O'){
                facing = 1;
                result[0] = 2;
            }
            else{
                if(map[position[0]-1][position[1]] == '.'){
                    result[0] = 1;
                }

                map[position[0]-1][position[1]] = '^';
                map[position[0]][position[1]] = 'X';

                position[0]--;
            }
        }
        else if(facing == 1){
            if(position[1] == map[0].length-1){
                result[0] = -1;
            }
            else if(map[position[0]][position[1]+1] == '#' || map[position[0]][position[1]+1] == 'O'){
                facing = 2;
                result[0] = 2;
            }
            else{
                if(map[position[0]][position[1]+1] == '.'){
                    result[0] = 1;
                }

                map[position[0]][position[1]+1] = '^';
                map[position[0]][position[1]] = 'X';

                position[1]++;
            }
        }
        else if(facing == 2){
            if(position[0] == map.length-1){
                result[0] = -1;
            }
            else if(map[position[0]+1][position[1]] == '#' || map[position[0]+1][position[1]] == 'O'){
                facing = 3;
                result[0] = 2;
            }
            else{
                if(map[position[0]+1][position[1]] == '.'){
                    result[0] = 1;
                }

                map[position[0]+1][position[1]] = '^';
                map[position[0]][position[1]] = 'X';

                position[0]++;
            }
        }
        else{
            if(position[1] == 0){
                result[0] = -1;
            }
            else if(map[position[0]][position[1]-1] == '#' || map[position[0]][position[1]-1] == 'O'){
                facing = 0;
                result[0] = 2;
            }
            else{
                if(map[position[0]][position[1]-1] == '.'){
                    result[0] = 1;
                }

                map[position[0]][position[1]-1] = '^';
                map[position[0]][position[1]] = 'X';

                position[1]--;
            }
        }

        result[1] = map;
        result[2] = position;
        result[3] = facing;
        result[4] = new int[]{-1};
        result[5] = map;

        return result;
    }
}
