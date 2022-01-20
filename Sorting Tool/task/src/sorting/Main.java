package sorting;

import java.util.*;

enum argType {
    LONG("long"),
    LINE("line"),
    WORD("word");

    argType(String param) {
        this.param = param;
    }

    String param;
}
public class Main {

    public static void longCount() {
        Scanner scanner = new Scanner(System.in);
        long max = Long.MIN_VALUE;
        long intCount = 0;
        long maxCount = 0;

        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            intCount++;

            if (number == max) {
                maxCount++;
            }
            if (number > max) {
                max = number;
                maxCount = 1;
            }
        }
        System.out.printf("Total numbers: %d.\nThe greatest number: %d (%d times (s), %f.2%%).",
                intCount, max, maxCount, (double) maxCount / intCount * 100);
        scanner.close();
    }

    public static void lineCount() {
        Scanner scanner = new Scanner(System.in);
        int max = Integer.MIN_VALUE;
        String maxLine = "";
        int lineCount = 0;
        int maxCount = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lineCount++;

            if (line.length() == max) {
                maxCount++;
            }
            if (line.length() > max) {
                maxLine = line;
                max = line.length();
                maxCount = 1;
            }
        }
        System.out.printf("Total lines: %d.\nThe longest line:\n%s\n(%d time(s), %.0f%%).",
                lineCount, maxLine, maxCount, (double) maxCount / lineCount * 100);
        scanner.close();
    }

    public static void wordCount() {
        Scanner scanner = new Scanner(System.in);
        int max = Integer.MIN_VALUE;
        String maxWord = "";
        int wordCount = 0;
        int maxCount = 0;

        while (scanner.hasNext()) {
            String word = scanner.next();
            wordCount++;

            if (word.length() == max) {
                maxCount++;
            }
            if (word.length() > max) {
                maxWord = word;
                max = word.length();
                maxCount = 1;
            }
        }
        System.out.printf("Total words: %d.\nThe longest word: %s (%d time(s), %.0f%%).",
                wordCount, maxWord, maxCount, (double) maxCount / wordCount * 100);
        scanner.close();
    }

    public static void main(final String[] args) {
        argType argument;

        try {
            argument = argType.valueOf(args[1].toUpperCase());
        } catch (ArrayIndexOutOfBoundsException error) {
            argument = argType.WORD;
        }

        switch (argument) {
            case LONG:
                longCount();
                break;
            case LINE:
                lineCount();
                break;
            case WORD:
                wordCount();
                break;
            default:
                break;
        }
    }
}
