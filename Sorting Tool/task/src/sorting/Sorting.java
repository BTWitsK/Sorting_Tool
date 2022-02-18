package sorting;
import java.util.*;

public class Sorting {
    static Scanner scanner = new Scanner(System.in);

    public static void printMap(String output, SortedMap<String, Integer> map) {
        int total = 0;

        for (int value : map.values()){
            total += value;
        }

        System.out.printf("Total %s: %d.\n", output, total);

        List<Map.Entry<String, Integer>> outputList = new ArrayList<>(map.entrySet());

        outputList.sort(Map.Entry.comparingByValue());

        for (var entry : outputList) {
            double percent = (double) entry.getValue() / total * 100;
            System.out.printf("%s: %d times(s), %.0f%%\n"
                    , entry.getKey(), entry.getValue(), percent);
        }
    }

    public static void countSort(String output) {
        SortedMap<Integer, Integer> map = new TreeMap<>();
        while (scanner.hasNext()) {
            map.merge(Integer.valueOf(scanner.next()), 1, Integer::sum);
        }

        int total = 0;

        for (int value : map.values()){
            total += value;
        }

        System.out.printf("Total %s: %d.\n", output, total);

        List<Map.Entry<Integer, Integer>> outputList = new ArrayList<>(map.entrySet());

        outputList.sort(Map.Entry.comparingByValue());

        for (var entry : outputList) {
            double percent = (double) entry.getValue() / total * 100;
            System.out.printf("%d: %d times(s), %.0f%%\n"
                    , entry.getKey(), entry.getValue(), percent);
        }
    }

    public static void countWordSort(String output) {
        SortedMap<String, Integer> map = new TreeMap<>();
        while (scanner.hasNext()) {
            map.merge(scanner.next(), 1, Integer::sum);
        }

        printMap(output, map);
    }

    public static void countLineSort(String output) {
        SortedMap<String, Integer> map = new TreeMap<>();
        while (scanner.hasNext()) {
            map.merge(scanner.nextLine(), 1, Integer::sum);
        }

        printMap(output, map);
    }

    public static void naturalLongSort() {
        List<Integer> intList = new ArrayList<>();
        while (scanner.hasNextInt()) {
            intList.add(scanner.nextInt());
        }

        int[] arrayInt = intList.stream().mapToInt(Integer::intValue).toArray();
        mergeSort(arrayInt, arrayInt.length);

        System.out.printf("Total numbers: %d.\nSorted data: ", arrayInt.length);

        for (int number : arrayInt) {
            System.out.printf("%d ", number);
        }
    }

    public static void naturalWordSort() {
        List<String> wordList = new ArrayList<>();
        while (scanner.hasNext()) {
            wordList.add(scanner.next());
        }

        //.toArray takes an array of string as a parameter and fills it
        String[] wordArray = wordList.toArray(new String[0]);
        mergeSort(wordArray, wordArray.length);

        System.out.printf("Total words: %d.\nSorted data: ", wordArray.length);

        for (String word: wordArray) {
            System.out.printf("%s ", word);
        }
    }

    public static void naturalLineSort() {
        List<String> lineList = new ArrayList<>();
        while (scanner.hasNext()) {
            lineList.add(scanner.nextLine());
        }

        //.toArray takes an array of string as a parameter and fills it
        String[] lineArray = lineList.toArray(new String[0]);
        mergeSort(lineArray, lineArray.length);

        System.out.printf("Total lines: %d.\nSorted data: ", lineArray.length);

        for (String line: lineArray) {
            System.out.printf("%s \n", line);
        }
    }

    public static void mergeSort(int[] unsortedArray, int size) {

        if (size < 2) {
            return;
        }

        int middle = size / 2;
        int[] left = new int[middle];
        int[] right = new int[size - middle];

        for (int i = 0; i < middle; i++) {
            left[i] = unsortedArray[i];
        }

        for (int i = middle; i < size; i++) {
            right[i - middle] = unsortedArray[i];
        }

        mergeSort(left, left.length);
        mergeSort(right, right.length);

        merge(unsortedArray, left, right, left.length, right.length);
    }

    public static void mergeSort(String[] unsortedArray, int size) {

        if (size < 2) {
            return;
        }

        int middle = size / 2;
        String[] left = new String[middle];
        String[] right = new String[size - middle];

        for (int i = 0; i < middle; i++) {
            left[i] = unsortedArray[i];
        }

        for (int i = middle; i < size; i++) {
            right[i - middle] = unsortedArray[i];
        }

        mergeSort(left, left.length);
        mergeSort(right, right.length);

        merge(unsortedArray, left, right, left.length, right.length);
    }

    public static void merge(int[] unsortedArray, int[] left, int[] right, int leftLength, int rightLength) {

        int leftIndex = 0;
        int rightIndex = 0;
        int mainIndex = 0;

        while (leftIndex < leftLength && rightIndex < rightLength) {
            if (left[leftIndex] <= right[rightIndex]) {
                unsortedArray[mainIndex++] = left[leftIndex++];
            } else {
                unsortedArray[mainIndex++] = right[rightIndex++];
            }
        }

        while (leftIndex < leftLength) {
            unsortedArray[mainIndex++] = left[leftIndex++];
        }

        while (rightIndex < rightLength) {
            unsortedArray[mainIndex++] = right[rightIndex++];
        }
    }

    public static void merge(String[] unsortedArray, String[] left, String[] right, int leftLength, int rightLength) {

        int leftIndex = 0;
        int rightIndex = 0;
        int mainIndex = 0;

        while (leftIndex < leftLength && rightIndex < rightLength) {
            if (left[leftIndex].compareTo(right[rightIndex]) < 1 ) {
                unsortedArray[mainIndex++] = left[leftIndex++];
            } else {
                unsortedArray[mainIndex++] = right[rightIndex++];
            }
        }

        while (leftIndex < leftLength) {
            unsortedArray[mainIndex++] = left[leftIndex++];
        }

        while (rightIndex < rightLength) {
            unsortedArray[mainIndex++] = right[rightIndex++];
        }
    }
}
