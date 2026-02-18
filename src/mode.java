 public class mode {


    public static int modeX(int[] array) {
        int[] tally = new int[101];

        for (int index = 0; index < array.length; index++) {
            tally[array[index]] = tally[array[index]] + 1;
        }
        int modeValue = -1;
        int modeIndex = -1;
        for(int index = 0; index < tally.length; index++){
            if(tally[index] > modeValue){
                modeValue = tally[index];
                modeIndex = index;
            }
        }
        return modeIndex;

    }
}

