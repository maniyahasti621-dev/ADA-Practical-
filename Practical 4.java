import java.util.Random;
import java.util.Arrays;

public class MergeSortAnalysis {

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1000000);
        }
        return arr;
    }

    public static void testMergeSort(int size) {
        int[] arr = generateRandomArray(size);
        int[] copy = arr.clone();

        System.out.printf("Dataset: %d elements%n", size);

        long startTime = System.nanoTime();
        mergeSort(arr, 0, arr.length - 1);
        long endTime = System.nanoTime();

        long durationNano = endTime - startTime;
        double durationMs = durationNano / 1_000_000.0;

        Arrays.sort(copy);
        boolean isCorrect = Arrays.equals(arr, copy);

        System.out.printf("Time taken: %.3f ms | %.6f sec%n", durationMs, durationMs / 1000.0);
        System.out.println("Sorted correctly: " + isCorrect);
        System.out.println("--------------------------------------");
    }

    public static void main(String[] args) {
        System.out.println("=== Merge Sort Performance Analysis ===");

        int[] sizes = {10000, 50000, 100000, 500000, 1000000, 5000000};

        for (int size : sizes) {
            testMergeSort(size);
        }

        System.out.println("\nNote: Merge Sort is O(n log n). Time roughly doubles when size doubles.");
    }
}
