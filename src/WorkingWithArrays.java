import java.util.*;
/**
 *
 * <p>
 *
 * <p>
 * Core Topics: Creating Arrays of Tallies, Shifting Elements in Arrays, Swapping
 * Elements in Arrays, Tracing Array Algorithms, Primitive Char Array
 * Parallel Arrays
 */

public class WorkingWithArrays {
    public static final int ALPHABET_SIZE = 26;

    public static void main(String[] args) {
        int a = -64;
        int b = 42;
        System.out.printf("Before Swap a = %d, b = %d \n", a, b);
        swap(a, b);
        System.out.printf("After Swap a = %d, b = %d \n\n", a, b);

        // TODO swap the a and b values in main variables
        int temp = a;
        a = b;
        b = temp;
        System.out.printf("True Swap a = %d, b = %d \n\n", a, b);


        int[] numbers = {1, -20, 3, -4, 10, 7, 3};
        System.out.println("Before Swap: " + Arrays.toString(numbers));
        swap(numbers[0], numbers[1]);
        System.out.println("After Swap: " + Arrays.toString(numbers));
        System.out.println();


        System.out.println("Before Swap: " + Arrays.toString(numbers));
        swap(numbers, 0, 1);
        System.out.println("After Swap: " + Arrays.toString(numbers));
        System.out.println();

        System.out.println("Before Mystery: " + Arrays.toString(numbers));
        arrayMystery(numbers);
        System.out.println("After Mystery: " +Arrays.toString(numbers));
        System.out.println();

        String[] words = {"hello", "hi", "hola", "jambo"};
        rotateLeft(words);
        System.out.println("Rotate Left: " + Arrays.toString(words));

        rotateRight(words);
        System.out.println("Rotate Right: " + Arrays.toString(words));

        int[] times = {2, 4};
        String[] letters = {"a", "b"};
        repeatTimes(letters, times);
        System.out.println(Arrays.toString(letters));

        char[] alphabet = new char[ALPHABET_SIZE];
        int index = 0;
        for (char letter = 'a'; letter <= 'z'; letter++) {
            alphabet[index] = letter;
            index++;
        }
        System.out.println(Arrays.toString(alphabet));
        System.out.println(Arrays.toString(letterFrequency("abzcdefgaaa")));
        System.out.println(Arrays.toString(letterFrequency(" AB##xzZ!!45")));
        System.out.println(Arrays.toString(letterFrequency("The quick brown fox jumped over the lazy dogs.")));

        // parallel arrays - similar items grouped by same index
        double[] prices = {1.99, 7.95, 8.99, 3.95};
        String[] snacks = {"gum", "figs", "cashews", "candy bar"};
        boolean[] onSale = {false,true,true,false};

       snackReport(prices,snacks,onSale);
    }


    /**
     * This method will swap the values of a and b in this method only
     * because primitive data types are passed by value not by reference (in java)
     * @param a
     * @param b
     */
    public static void swap(int a, int b) {

        int temp = b;
        b = a;
        a = temp;
        System.out.printf("In Swap: a = %d, b = %d \n", a, b);
    }


    /**
     * This method will swap the values of arr[index1] with arr[index2]
     * permanently because arrays are passed by reference
     * @param arr
     * @param index1
     * @param index2
     */
    public static void swap(int[] arr, int index1, int index2) {
        if(index1 >= arr.length || index2 >= arr.length){
            throw new IllegalArgumentException("Index is greater than length of array ");
        }
        if(index1 < 0 || index2 < 0) {
            throw new IllegalArgumentException("Index is less than 0 ");
        }

        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
        System.out.println("In Swap: " + Arrays.toString(arr));
    }

    // trace through this, what is the result at the end?
    // Example        length = 7
    // elements       -20   1    3   -4   10   7   3
    // index           0    1    2    3    4   5   6
    // 1st iteration
    // 2nd iteration
    // 3rd iteration
    // 4th iteration
    // 5th iteration

    public static void arrayMystery(int[] a) {
        for (int i = 1; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                a[i] = a[i + 1] + a[i - 1];
            }
            if (a[i] < 0) {
                a[i] *= 2;
            }
        }
    }


    /**
     * This method should shift elements in an array by rotating all values left by one
     * the first value should rotate to the end of the array
     * {"at","bat","cat"} ==> {"bat","cat","at"}
     *
     * @param words - array of strings to rotate
     */
    public static void rotateLeft(String[] words) {
        if(words.length > 0){
            String first = words[0];
            // move all other array element to the left
            for(int i = 1; i < words.length; i++){
                words[i -1] = words[i];
            }
            // put first
            words[words.length - 1] = first;

        }



    }

    /**
     * This method should shift elements in an array by rotating all values right by one
     * the last value should rotate to the front of the array
     * {"at","bat","cat"} ==> {"at","bat","cat"}
     *
     * @param words - array of strings to rotate
     */
    public static void rotateRight(String[] words) {
        if(words.length > 0){
            String last = words[words.length - 1];
            for(int i = words.length - 1; i > 0; i--){
                words[i] = words[i-1];
            }
            words[0] = last;
        }



    }
    /**
     * This method should alter each element in the letters array to repeat the number
     * of times that appears in the times array
     * if we pass {"a","b"},{2,4}
     * ==> letters should become {"aa","bbbb"}
     *
     * @param letters
     * @param times
     */
    public static void repeatTimes(String[] letters, int[] times) {
        for(int i = 0; i < times.length; i++){
            String result = "";
            for(int j = 0; j < times[i]; j++){
                result = result + letters[i];
            }
            letters[i] = result;
        }



    }

    /**
     * This method should produce an array of tallies by counting the frequency of each letter in phrase
     * and placing this count into a talley array where the index will represent a letter
     * from a to z
     * letterCount     4  1  0  0      0
     * index          0a 1b 2c 3d ... 25z
     * For the letterCount array values above we can see that
     * a occurs 4 times
     * b occurs 1 times
     * and the rest occur 0 times.
     *
     * @param phrase text to analyze
     * @return an array of letter counts
     */
    public static int[] letterFrequency(String phrase) {
        int[] letterFrequency = new int[ALPHABET_SIZE];

        for (int index = 0; index < phrase.length() ; index++) {
            char character = Character.toLowerCase(phrase.charAt(index));
            if (Character.isLetter(character)) {
                // update the count / frequency in the tally array
                letterFrequency[character - 97]++;
            }
        }

        return letterFrequency;

    }

    /**
     * This method utilizes the idea of parallel arrays by printing out the name of the
     * snack and it's current price.  If the snack is on sale, the snack is 25% less than
     * the price with the same index in the prices array. It is assumed that all arrays are of the same
     * length since a common index links them together
     * Example:
     * index     0     1      2         3
     * snacks = gum   figs   cashews    candy bar
     * prices = 1.99  7.95    8.99      3.95
     * boolean= false true    true      false
     * Output:
     * gum costs $1.99
     * figs costs $7.95 and are on sale for $5.96
     * cashews costs $8.99 and are on sale for $6.74
     * candy bar costs $3.95
     *
     * @param prices array of prices
     * @param snacks array of snack items
     * @param sales array of boolean values,true if on sale, false otherwise
     */
    public static void snackReport(double[] prices, String[] snacks, boolean[] sales){
        for( int index = 0; index <prices.length; index++){
            if(sales[index]) {
                double price = prices[index] * 0.75; // 25% off
                System.out.printf("%s cost $%.2f and are on sale for $%.2f\n", snacks[index], prices[index], price);
            } else {
                System.out.printf("%s cost $%.2f\n", snacks[index], prices[index]);
            }
        }

    }

}
