package sorting;

import java.util.*;

public class Main {
    public static void main(final String[] args) {
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

        System.out.printf("Total numbers: %d.\n The greatest number: %d (%d times (s).)", intCount, max, maxCount);
    }
}
