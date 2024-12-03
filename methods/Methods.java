package methods;

public class Methods {
    // Sort int array from low to high
    public static int[] sortLowToHigh(int[] list){
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

    // Sort int array from high to low
    public static int[] sortHighToLow(int[] list) {
        // Bubble sort
        boolean sorted = false;

        while(!sorted){
            sorted = true;

            for(int i = 0; i < list.length; i++){
                for(int j = i+1; j < list.length; j++){
                    if(list[i] < list[j]){
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

    // Remove an element from an array, returns the new array
    public static String[] removeIndex(String[] arr, int removeIndex){
        String[] newArr = new String[arr.length-1];

        for(int i = 0; i < arr.length; i++){
            if(i < removeIndex){
                newArr[i] = arr[i];
            }
            else if(i > removeIndex){
                newArr[i-1] = arr[i];
            }
        }

        return newArr;
    }

    public static int[] removeIndex(int[] arr, int removeIndex){
        int[] newArr = new int[arr.length-1];

        for(int i = 0; i < arr.length; i++){
            if(i < removeIndex){
                newArr[i] = arr[i];
            }
            else if(i > removeIndex){
                newArr[i-1] = arr[i];
            }
        }

        return newArr;
    }
}
