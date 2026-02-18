//
//
// Core Topics: File I/O, String processing, methods, arrays
//
// This program reads diary entries from diary.txt, performs simple
// sentiment analysis by counting good and bad keywords, and writes
// a formatted monthly summary of the results to summary.txt.

import java.io.*;
import java.util.*;

public class DiaryAnalyzer {

    // ---------------------------------------------
    // Arrays of good and bad keywords
    // ---------------------------------------------
    public static final String[] GOOD_WORDS = {"good", "great", "yay"};
    public static final String[] BAD_WORDS = {"terrible", "horrible", "awful"};

    // ---------------------------------------------
    // main method
    // ---------------------------------------------
    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(new File("diary.txt"));
        PrintStream output = new PrintStream(new File("summary.txt"));

        String lastMonth = "";       // Track when month changes
        int totalDays = 0;           // Count entries

        while (input.hasNextLine()) {
            String line = input.nextLine().trim();
            if (line.length() < 5) {
                continue; // skip malformed line
            }

            totalDays++;

            // Extract date
            String date = line.substring(0, 5);
            String month = date.substring(0, 3);
            String day = date.substring(3, 5);

            // Extract diary text
            String text = line.substring(5).trim();

            // Detect month change
            if (!month.equals(lastMonth)) {
                if (!lastMonth.equals("")) {
                    output.println();
                }
                output.println(month);
                lastMonth = month;
            }

            // Analyze the entry
            String result = analyzeEntry(text);

            // Write result
            output.println(" - " + day + " " + result);
        }

        output.println();
        output.println(totalDays + " total days analyzed.");

        input.close();
        output.close();
    }

    // ---------------------------------------------
    // This method analyzes one diary entry and returns
    // :), :(, or :| depending on counts of keywords.
    // ---------------------------------------------
    public static String analyzeEntry(String text) {
        text = text.toLowerCase();
        Scanner tokenReader = new Scanner(text);

        int goodCount = 0;
        int badCount = 0;

        while (tokenReader.hasNext()) {
            String token = tokenReader.next();

            if (containsKeyword(token, GOOD_WORDS)) {
                goodCount++;
            }
            if (containsKeyword(token, BAD_WORDS)) {
                badCount++;
            }
        }

        // Compare counts
        if (goodCount > badCount) {
            return ":)";
        } else if (badCount > goodCount) {
            return ":(";
        } else {
            return ":|";
        }
    }

    // ---------------------------------------------
    // This method checks if a token contains any keyword
    // from a given array of keywords.
    // ---------------------------------------------
    public static boolean containsKeyword(String token, String[] keywords) {
        for (String word : keywords) {
            if (token.contains(word)) {
                return true;
            }
        }
        return false;
    }
}

/*
---------------------------------------------------------
Reflection Questions (answer these in your submission)
---------------------------------------------------------

1. What parts of the assignment were the easiest to complete?
   - YOUR ANSWER:

2. What are some things that went wrong when completing this assignment?
   - YOUR ANSWER:

3. How did you deal with these setbacks?
   - YOUR ANSWER:

4. About how many hours did you spend working on this assignment?
   - YOUR ANSWER:


---------------------------------------------------------
Paste Program Output Below (from summary.txt)
---------------------------------------------------------

<Your pasted output here>

*/

