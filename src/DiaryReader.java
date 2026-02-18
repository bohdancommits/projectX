///
///
/// Core Topics: This assignment will give you practice with reading from a file, writing to a file, and String methods.
///
/// This program will reads diary.txt performs simple
/// analysis by counting good and bad words, and writes
/// a formatted monthly summary of the results to summary.txt
import java.io.*;
import  java.util.*;



public class DiaryReader {
    // i will create an arrays of bad and good words
    public static final String[] BAD_WORD = {"terrible","horrible","awful"};
    public static final String[] GOOD_WORD = {"good","great","yay"};


    // main method and i will use throws FileNotFoundException
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("diary.txt"));
        PrintStream out = new PrintStream(new File("summary.txt"));

        // track when month change
        String lastMonth = "";
        int totalDays = 0;
        while (input.hasNextLine()) {
            String line = input.nextLine();
            boolean valid = line.length() >= 5;
            if (valid) {


                totalDays++;
                // date
                String month = line.substring(0, 3);
                String day = line.substring(3, 5);
                String date = line.substring(0, 5);

                // extract diary text
                String text = line.substring(5);

                // month change
                if (!month.equals(lastMonth)) {
                    if (!lastMonth.equals("")) {
                        out.println();
                    }
                    out.println(month);
                    lastMonth = month;
                }
                // analyze the entry
                String result = analyzingDiary(text);
                // result
                out.println(" - " + day + " " + result);
            }
        }
        out.println();
        out.println(totalDays + " total days analyze.");
        input.close();
        out.close();
    }

        // this method will analyze one diary entry and return some symbols depending on counts
        public static String analyzingDiary(String text) {
            text = text.toLowerCase();
            Scanner tokenR = new Scanner(text);
            int goodCount = 0;
            int badCount = 0;
            while(tokenR.hasNext()) {
                String word = tokenR.next();
                if(containsK(word,GOOD_WORD)) {
                    goodCount++;
                }
                if(containsK(word,BAD_WORD)) {
                    badCount++;
                }
            }
            // compare counts
            if(goodCount > badCount) {
                return ":)";
            } else if (badCount > goodCount) {
                return ":(";
            } else {
                return ":|";
            }

        }
    public static boolean containsK(String token, String[] keys) {
        for (String key : keys) {
            if (token.contains(key)) {
                return true;
            }

        }
        return false;
    }

}
/*



Paste Program Output Below (from summary.txt)
Jan
 - 01 :)
 - 14 :)

Feb
 - 10 :|
 - 21 :(
 - 23 :)

May
 - 02 :(
 - 15 :)

Jun
 - 01 :)
 - 11 :)

9 total days analyze.
*/
