import java.util.Random;

public class ParalleArrays {

    public static void main(String[] args) {
        String[] names = {"Bob","Frank","Yein","Jose","Palak","Mia","Mila","Charlie"};
        int[] ages = new int[0]; // null is not in these 8 spots, 0 0 0 0
        for(int i = 0; i < ages.length; i++){
            ages[i] = new Random().nextInt(1,123);
        }
        Object[] objects = new Object[8];
        // traverse names and print the name assiciated age
        for(int i = 0; i < names.length; i++){
            System.out.println( " Name " + names[i] + " is " + ages[i] + "years old");

        }

    }
}
