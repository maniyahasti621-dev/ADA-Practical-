import java.util.Random;
import java.util.Arrays;

public class BubbleSortAnalysis {

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
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
  
    public static void testBubbleSort(int size, String dataType) {
        int[] arr;

        if (dataType.equals("random")) {
            arr = generateRandomArray(size);
        } else if (dataType.equals("sorted")) {
            arr = new int[size];
            for (int i = 0; i < size; i++) arr[i] = i;
        } else { 
            arr = new int[size];
            for (int i = 0; i < size; i++) arr[i] = size - i;
        }

        int[] copy = arr.clone(); 

        System.out.printf("Dataset: %d elements, %s%n", size, dataType);

        long startTime = System.nanoTime();
        bubbleSort(arr);
        long endTime = System.nanoTime();

        long durationNano = endTime - startTime;
        double durationMs = durationNano / 1_000_000.0;

        System.out.printf("Time taken: %.3f ms | %.6f sec%n", durationMs, durationMs / 1000.0);
        System.out.println("Sorted correctly: " + Arrays.equals(arr, Arrays.stream(copy).sorted().toArray()));
        System.out.println("--------------------------------------");
    }

    public static void main(String[] args) {
        System.out.println("=== Bubble Sort Performance Analysis ===");

        int[] sizes = {1000, 5000, 10000, 20000};

        System.out.println("\n--- Random Data (Average Case) ---");
        for (int size : sizes) {
            testBubbleSort(size, "random");
        }

        System.out.println("\n--- Sorted Data (Best Case) ---");
        testBubbleSort(20000, "sorted");

        System.out.println("\n--- Reverse Data (Worst Case) ---");
        testBubbleSort(20000, "reverse");
    }
}
