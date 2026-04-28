import java.util.Random;
import java.util.Arrays;

public class InsertionSortAnalysis {
  public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
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

    public static void testInsertionSort(int size, String dataType) {
        int[] arr;

        switch (dataType) {
            case "random":
                arr = generateRandomArray(size); 
                break;
            case "sorted":
                arr = new int[size];
                for (int i = 0; i < size; i++) arr[i] = i; 
                break;
            case "reverse":
                arr = new int[size];
                for (int i = 0; i < size; i++) arr[i] = size - i; 
                break;
            default:
                arr = generateRandomArray(size);
        }

        int[] copy = arr.clone();

        System.out.printf("Dataset: %d elements, %s%n", size, dataType);

        long startTime = System.nanoTime();
        insertionSort(arr);
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
        System.out.println("=== Insertion Sort Performance Analysis ===");

        int[] sizes = {1000, 5000, 10000, 20000, 50000};

        System.out.println("\n--- Random Data (Average Case) ---");
        for (int size : sizes) {
            testInsertionSort(size, "random");
        }

        System.out.println("\n--- Sorted Data (Best Case) ---");
        testInsertionSort(50000, "sorted");

        System.out.println("\n--- Reverse Data (Worst Case) ---");
        testInsertionSort(50000, "reverse");
    }
}
