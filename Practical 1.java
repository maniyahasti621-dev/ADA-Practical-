import java.util.Random;
import java.util.Arrays;

public class SelectionSortAnalysis {
  
    public static void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }
  
    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100000);
        }
        return arr;
    }

    public static void testSelectionSort(int size) {
        int[] arr = generateRandomArray(size);

        int[] copy = arr.clone();

        System.out.println("Dataset size: " + size);

        long startTime = System.nanoTime();
        selectionSort(arr);
        long endTime = System.nanoTime();

        long durationNano = endTime - startTime;
        double durationMs = durationNano / 1_000_000.0;
        double durationSec = durationMs / 1000.0;

        System.out.printf("Time taken: %.3f ms | %.6f sec%n", durationMs, durationSec);

        Arrays.sort(copy);
        boolean isCorrect = Arrays.equals(arr, copy);
        System.out.println("Sorted correctly: " + isCorrect);
        System.out.println("--------------------------------------");
    }

    public static void main(String[] args) {
        System.out.println("=== Selection Sort Performance Analysis ===");

        int[] sizes = {1000, 5000, 10000, 20000, 30000};

        for (int size : sizes) {
            testSelectionSort(size);
        }

        System.out.println("\nNote: Selection Sort is O(n²). Time roughly quadruples when size doubles.");
    }
}
