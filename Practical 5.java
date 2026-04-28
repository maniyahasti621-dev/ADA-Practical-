import java.util.Random;
import java.util.Arrays;

public class QuickSortAnalysis {

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
      
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
    public static void quickSortRandomPivot(int[] arr, int low, int high) {
        if (low < high) {
            int randomIdx = low + new Random().nextInt(high - low + 1);
            swap(arr, randomIdx, high);

            int pi = partition(arr, low, high);
            quickSortRandomPivot(arr, low, pi - 1);
            quickSortRandomPivot(arr, pi + 1, high);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1000000);
        }
        return arr;
    }

    public static void testQuickSort(int size, String dataType) {
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
        quickSortRandomPivot(arr, 0, arr.length - 1);
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
        System.out.println("=== Quick Sort Performance Analysis ===");

        int[] sizes = {10000, 50000, 100000, 500000, 1000000, 5000000};

        System.out.println("\n--- Random Data (Average Case O(n log n)) ---");
        for (int size : sizes) {
            testQuickSort(size, "random");
        }

        System.out.println("\n--- Sorted Data (Random Pivot) ---");
        testQuickSort(1000000, "sorted");

        System.out.println("\nNote: Quick Sort avg O(n log n). Random pivot avoids O(n²) worst case.");
    }
}
