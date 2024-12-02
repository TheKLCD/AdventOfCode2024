package methods;

public class Methods {
    // Sort list from low to high
    public static int[] sort(int[] list){
        // Bubble sort
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
