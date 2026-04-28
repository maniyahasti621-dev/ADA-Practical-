import java.util.Arrays;
import java.util.Random;

public class SearchAnalysis {

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; 
            }
        }
        return -1; 
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; 

            if (arr[mid] == target) {
                return mid; 
            } else if (arr[mid] < target) {
                left = mid + 1; 
            } else {
                right = mid - 1; 
            }
        }
        return -1; 
    }

    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) return -1;

        int mid = left + (right - left) / 2;
        if (arr[mid] == target) return mid;
        else if (arr[mid] < target) return binarySearchRecursive(arr, target, mid + 1, right);
        else return binarySearchRecursive(arr, target, left, mid - 1);
    }

    public static int[] generateSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i * 2; 
        }
        return arr;
    }

    public static void testSearch(int size) {
        int[] arr = generateSortedArray(size);
        Random rand = new Random();

        int[] targets = {
                arr, 
                arr[size / 2], 
                arr[size - 1], 
                arr[size - 1] + 1 
        };
        String[] cases = {"First element", "Middle element", "Last element", "Not found"};

        System.out.printf("=== Dataset: %d elements ===%n", size);

        for (int i = 0; i < targets.length; i++) {
            int target = targets[i];
            System.out.println("\nCase: " + cases[i] + ", Target: " + target);

            long start = System.nanoTime();
            int linearIdx = linearSearch(arr, target);
            long linearTime = System.nanoTime() - start;

            start = System.nanoTime();
            int binaryIdx = binarySearch(arr, target);
            long binaryTime = System.nanoTime() - start;

            System.out.printf("Linear Search: Index = %d, Time = %d ns%n", linearIdx, linearTime);
            System.out.printf("Binary Search: Index = %d, Time = %d ns%n", binaryIdx, binaryTime);
            System.out.printf("Speedup: %.2fx%n", (double) linearTime / binaryTime);
        }
        System.out.println("==========================================");
    }

    public static void main(String[] args) {
        System.out.println("=== Linear Search vs Binary Search Analysis ===");

        int[] sizes = {1000, 10000, 100000, 1000000};

        for (int size : sizes) {
            testSearch(size);
        }

        System.out.println("\nKey Differences:");
        System.out.println("Linear Search: O(n) - works on unsorted data, scans each element");
        System.out.println("Binary Search: O(log n) - requires sorted data, halves search space each step");
    }
}
