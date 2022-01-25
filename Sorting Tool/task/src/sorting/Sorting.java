package sorting;
import java.util.*;

public class Sorting {

    public static void sortIntList() {
        Scanner scanner = new Scanner(System.in);
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

    public static void mergeSort(int[] unsortedArray, int size) {

        if (size < 2) {
            return ;
        }

        int middle = size / 2;
        int[] left = new int[middle];
        int[] right = new int[size - middle];

        System.arraycopy(unsortedArray, 0, left, 0, middle);
        System.arraycopy(unsortedArray, middle, right, middle, size);

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
}
