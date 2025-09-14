import java.util.Arrays;
import java.util.Random;

public class SortingComparison {
        
        // QuickSort using the last element of the subarray as the pivot
        // @param array the array to be sorted
        // @param left the starting index of the subarray
        // @param right the ending index of the subarray
        // @return N/A
        public static void quickSortLastPivot(int[] array, int left, int right) {
                if (left < right) {
                        int partitionPos = partitionLastPivot(array, left, right);

                        quickSortLastPivot(array, left, partitionPos - 1);
                        quickSortLastPivot(array, partitionPos + 1, right);
                }

        }

        // Partition function for QuickSort (last pivot)
        // Moves all elements smaller than pivot to the left, greater to the right
        // @param array the array being sorted
        // @param left the starting index of the subarray
        // @param right the ending index of the subarray
        // @return the final position of the pivot
        static int partitionLastPivot(int[] array, int left, int right) {
                int pivot = array[right];
                int i = left - 1;

                for (int j = left; j <= right; j++) {
                        if (array[j] < pivot) {
                                i++;
                                swap(array, i, j);
                        }
                }

                swap(array, i + 1, right);
                return i + 1;
        }

        // QuickSort using the first element as the pivot
        // Swaps the first element to the end, then reuses last-pivot partitioning
        // @param array the array to be sorted
        // @param left the starting index of the subarray
        // @param right the ending index of the subarray
        // @return N/A
        public static void quickSortFirstPivot(int[] array, int left, int right) {
                if (left < right) {
                        // put first element at end
                        swap(array, left, right); 

                        int partitionPos = partitionLastPivot(array, left, right);

                        quickSortFirstPivot(array, left, partitionPos - 1);
                        quickSortFirstPivot(array, partitionPos + 1, right);
                }
        }

        // QuickSort using the middle element as the pivot
        // Swaps the middle element to the end, then reuses last-pivot partitioning
        // @param array the array to be sorted
        // @param left the starting index of the subarray
        // @param right the ending index of the subarray
        // @return N/A
        public static void quickSortMedianPivot(int[] array, int left, int right) {
                if (left < right) {
                        // Find the middle element of the array
                        int middle = left + (right - left) / 2;

                        // Swap the middle element with the last element
                        swap(array, middle, right);

                        int partitionPos = partitionLastPivot(array, left, right);

                        quickSortMedianPivot(array, left, partitionPos - 1);
                        quickSortMedianPivot(array, partitionPos + 1, right);
                }
        }

        // Method for swapping elements in arrays
        // @param array the array containing elements
        // @param i index of first element
        // @param j index of second element
        // @return N/A
        static void swap(int[] array, int i, int j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
        }

        // Recursive divide and conquer algorithm
        // @param array the array to be sorted
        // @param l the left index
        // @param r the right index
        // @return N/A
        public static void mergeSort(int[] array, int l, int r) {
                if (l < r) {
                        // Find the middle point
                        int m = l + (r - l) / 2;

                        // Sort first and second halves
                        mergeSort(array, l, m);
                        mergeSort(array, m + 1, r);

                        // Merge the sorted halves
                        merge(array, l, m, r);
                }

        }

        // Merge step for Merge Sort.
        // Combines two sorted subarrays into a single sorted subarray.
        // @param arr the main array
        // @param l left index
        // @param m middle index
        // @param r right index
        // @return N/A
        static void merge(int arr[], int l, int m, int r) {
                // Find sizes of two subarrays to be merged
                int n1 = m - l + 1;
                int n2 = r - m;

                // Create temp arrays
                int L[] = new int[n1];
                int R[] = new int[n2];

                // Copy data to temp arrays
                for (int i = 0; i < n1; ++i)
                L[i] = arr[l + i];
                for (int j = 0; j < n2; ++j)
                R[j] = arr[m + 1 + j];

                // Merge the temp arrays
                // Initial indices of first and second subarrays
                int i = 0;
                int j = 0;

                // Initial index of merged subarray array
                int k = l;

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

                // Copy remaining elements of L[] if any
                while (i < n1) {
                        arr[k] = L[i];
                        i++;
                        k++;
                }

                // Copy remaining elements of R[] if any
                while (j < n2) {
                        arr[k] = R[j];
                        j++;
                        k++;
                }
        }

        // Runs Merge Sort while measuring memory and runtime usage.
        // @param arr the input array to sort
        // @return N/A
        public static void runMergeSortWithMemory(int[] arr) {
                int[] copy = Arrays.copyOf(arr, arr.length);

                // gets the current rutime
                Runtime runtime = Runtime.getRuntime();
                // clears the current memory
                runtime.gc();

                long beforeMem = runtime.totalMemory() - runtime.freeMemory();
                long startTime = System.currentTimeMillis();

                mergeSort(copy, 0, copy.length - 1);

                long endTime = System.currentTimeMillis();
                long afterMem = runtime.totalMemory() - runtime.freeMemory();

                // Print results
                System.out.println("Before: " + Arrays.toString(arr));
                System.out.println("After:  " + Arrays.toString(copy));
                System.out.println("Runtime: " + (endTime - startTime) + " ms");
                System.out.println("Memory before: " + beforeMem + " bytes");
                System.out.println("Memory after:  " + afterMem + " bytes");
                System.out.println("Memory used:   " + (afterMem - beforeMem) + " bytes");
        }

        // Hybrid sorting algorithm: QuickSort + InsertionSort.
        // If the subarray size is less than threshold, use InsertionSort.
        // @param arr the array to sort
        // @param left left index
        // @param right right index
        // @param threshold cutoff point to switch from QuickSort to InsertionSort
        // @return N/A
        public static void hybridSort(int[] arr, int left, int right, int threshold) {
                if (right - left + 1 <= threshold) {
                        insertionSort(arr, left, right);
                        return;
                }

                if (left < right) {
                        int pivotIndex = partitionLastPivot(arr, left, right);
                        hybridSort(arr, left, pivotIndex - 1, threshold);
                        hybridSort(arr, pivotIndex + 1, right, threshold);
                }
        }

        // Insertion Sort for a subarray
        // @param arr the array to sort
        // @param left left index
        // @param right right index
        // @return N/A
        public static void insertionSort(int[] arr, int left, int right) {
                for (int i = left + 1; i <= right; i++) {
                        int temp = arr[i];
                        int j = i - 1;

                        while (j >= left && arr[j] > temp) {
                                arr[j + 1] = arr[j];
                                j--;
                        }

                        arr[j + 1] = temp;
                }
        }

        // Test to see what the optimal threshold for a very large array
        public static void testThresholds(int[] arr) {
                int[] thresholds = {2, 5, 10, 20, 50};

                // Loop to go through each value and test it against the passed in array
                for (int t : thresholds) {
                        int[] copy = Arrays.copyOf(arr, arr.length);
                        long start = System.currentTimeMillis();

                        hybridSort(copy, 0, copy.length - 1, t);

                        long time = System.currentTimeMillis() - start;
                        System.out.println("Threshold " + t + ": " + time + " ms");
                }
        }

        public static void main(String[] args) {
                long startTime;
                double timeTaken;

                // Test arrays
                int[] empty = {};
                int[] single = {5};
                int[] nearlySorted = {1, 2, 4, 3, 5, 9, 7, 10, 8, 6};
                int[] sorted = {1, 2, 3, 4, 5};
                int[] random = new Random().ints(20, 0, 100).toArray();
                int[] reversed = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
                int[] duplicates = {2, 3, 2, 1, 3, 1, 5, 7, 6, 4, 5, 6, 7, 4};
                int[] arr = {1, 2, 6, 7, 9, 3, 10, 4, 5, 8};
                int[] small = {5, 2, 9, 1, 5, 6};
                int[] large = new Random().ints(20, 0, 1000).toArray();

                System.out.println("__ QuickSortLastPivort __");

                startTime = System.currentTimeMillis();
                int[] arr1 = Arrays.copyOf(empty, empty.length);
                System.out.println("Empty Before: " + Arrays.toString(arr1));
                quickSortLastPivot(arr1, 0, arr1.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Empty After: " + Arrays.toString(arr1));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr2 = Arrays.copyOf(single, single.length);
                System.out.println("Single Before: " + Arrays.toString(arr2));
                quickSortLastPivot(arr2, 0, arr2.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Single After: " + Arrays.toString(arr2));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr3 = Arrays.copyOf(nearlySorted, nearlySorted.length);
                System.out.println("Nearly Sorted Before: " + Arrays.toString(arr3));
                quickSortLastPivot(arr3, 0, arr3.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Nearly Sorted After: " + Arrays.toString(arr3));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr4 = Arrays.copyOf(sorted, sorted.length);
                System.out.println("Sorted Before: " + Arrays.toString(arr4));
                quickSortLastPivot(arr4, 0, arr4.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Sorted After: " + Arrays.toString(arr4));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr5 = Arrays.copyOf(random, random.length);
                System.out.println("Random Before: " + Arrays.toString(arr5));
                quickSortLastPivot(arr5, 0, arr5.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Random After: " + Arrays.toString(arr5));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr6 = Arrays.copyOf(reversed, reversed.length);
                System.out.println("Reversed Before: " + Arrays.toString(arr6));
                quickSortLastPivot(arr6, 0, arr6.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Reversed After: " + Arrays.toString(arr6));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr25 = Arrays.copyOf(duplicates, duplicates.length);
                System.out.println("Duplicates Before: " + Arrays.toString(arr25));
                quickSortLastPivot(arr25, 0, arr25.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Duplicates After: " + Arrays.toString(arr25));
                System.out.println("Runtime: " + timeTaken + " ms");


                System.out.println("__ QuickSortFirstPivort __");

                startTime = System.currentTimeMillis();
                int[] arr7 = Arrays.copyOf(empty, empty.length);
                System.out.println("Empty Before: " + Arrays.toString(arr7));
                quickSortFirstPivot(arr7, 0, arr7.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Empty After: " + Arrays.toString(arr7));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr8 = Arrays.copyOf(single, single.length);
                System.out.println("Single Before: " + Arrays.toString(arr8));
                quickSortFirstPivot(arr8, 0, arr8.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Single After: " + Arrays.toString(arr8));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr9 = Arrays.copyOf(nearlySorted, nearlySorted.length);
                System.out.println("Nearly Sorted Before: " + Arrays.toString(arr9));
                quickSortFirstPivot(arr9, 0, arr9.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Nearly Sorted After: " + Arrays.toString(arr9));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr10 = Arrays.copyOf(sorted, sorted.length);
                System.out.println("Sorted Before: " + Arrays.toString(arr10));
                quickSortFirstPivot(arr10, 0, arr10.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Sorted After: " + Arrays.toString(arr10));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr11 = Arrays.copyOf(random, random.length);
                System.out.println("Random Before: " + Arrays.toString(arr11));
                quickSortFirstPivot(arr11, 0, arr11.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Random After: " + Arrays.toString(arr11));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr12 = Arrays.copyOf(reversed, reversed.length);
                System.out.println("Reversed Before: " + Arrays.toString(arr12));
                quickSortFirstPivot(arr12, 0, arr12.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Reversed After: " + Arrays.toString(arr12));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr26 = Arrays.copyOf(duplicates, duplicates.length);
                System.out.println("Duplicates Before: " + Arrays.toString(arr26));
                quickSortFirstPivot(arr26, 0, arr26.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Duplicates After: " + Arrays.toString(arr26));
                System.out.println("Runtime: " + timeTaken + " ms");


                System.out.println("__ QuickSortMedianPivort __");

                startTime = System.currentTimeMillis();
                int[] arr13 = Arrays.copyOf(empty, empty.length);
                System.out.println("Empty Before: " + Arrays.toString(arr13));
                quickSortMedianPivot(arr13, 0, arr13.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Empty After: " + Arrays.toString(arr13));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr14 = Arrays.copyOf(sorted, sorted.length);
                System.out.println("Single Before: " + Arrays.toString(arr14));
                quickSortMedianPivot(arr14, 0, arr14.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Single After: " + Arrays.toString(arr14));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr15 = Arrays.copyOf(nearlySorted, nearlySorted.length);
                System.out.println("Nearly Sorted Before: " + Arrays.toString(arr15));
                quickSortMedianPivot(arr15, 0, arr15.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Nearly Sorted After: " + Arrays.toString(arr15));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr16 = Arrays.copyOf(sorted, sorted.length);
                System.out.println("Sorted Before: " + Arrays.toString(arr16));
                quickSortMedianPivot(arr16, 0, arr16.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Sorted After: " + Arrays.toString(arr16));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr17 = Arrays.copyOf(random, random.length);
                System.out.println("Random Before: " + Arrays.toString(arr17));
                quickSortMedianPivot(arr17, 0, arr17.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Random After: " + Arrays.toString(arr17));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr18 = Arrays.copyOf(reversed, reversed.length);
                System.out.println("Reversed Before: " + Arrays.toString(arr18));
                quickSortMedianPivot(arr18, 0, arr18.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Reversed After: " + Arrays.toString(arr18));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr27 = Arrays.copyOf(duplicates, duplicates.length);
                System.out.println("Duplicates Before: " + Arrays.toString(arr27));
                quickSortMedianPivot(arr27, 0, arr27.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Duplicates After: " + Arrays.toString(arr27));
                System.out.println("Runtime: " + timeTaken + " ms");


                System.out.println("__ MergeSort __");

                startTime = System.currentTimeMillis();
                int[] arr19 = Arrays.copyOf(empty, empty.length);
                System.out.println("Empty Before: " + Arrays.toString(arr19));
                mergeSort(arr19, 0, arr19.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Empty After: " + Arrays.toString(arr19));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr20 = Arrays.copyOf(single, single.length);
                System.out.println("Single Before: " + Arrays.toString(arr20));
                mergeSort(arr20, 0, arr20.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Single After: " + Arrays.toString(arr20));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr21 = Arrays.copyOf(nearlySorted, nearlySorted.length);
                System.out.println("Nearly Sorted Before: " + Arrays.toString(arr21));
                mergeSort(arr21, 0, arr21.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Nearly Sorted After: " + Arrays.toString(arr21));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr22 = Arrays.copyOf(sorted, sorted.length);
                System.out.println("Sorted Before: " + Arrays.toString(arr22));
                mergeSort(arr22, 0, arr22.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Sorted After: " + Arrays.toString(arr22));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr23 = Arrays.copyOf(random, random.length);
                System.out.println("Random Before: " + Arrays.toString(arr23));
                mergeSort(arr23, 0, arr23.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Random After: " + Arrays.toString(arr23));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr24 = Arrays.copyOf(reversed, reversed.length);
                System.out.println("Reversed Before: " + Arrays.toString(arr24));
                mergeSort(arr24, 0, arr24.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Reversed After: " + Arrays.toString(arr24));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr28 = Arrays.copyOf(duplicates, duplicates.length);
                System.out.println("Duplicates Before: " + Arrays.toString(arr28));
                mergeSort(arr28, 0, arr28.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Duplicates After: " + Arrays.toString(arr28));
                System.out.println("Runtime: " + timeTaken + " ms");
        

                System.out.println("__ MergeSortMemory __");

                int[] arr29 = Arrays.copyOf(arr, arr.length);
                runMergeSortWithMemory(arr29);


                System.out.println("__ HybridSort __");

                startTime = System.currentTimeMillis();
                int[] arr30 = Arrays.copyOf(small, small.length);
                System.out.println("Small Before: " + Arrays.toString(arr30));
                hybridSort(arr30, 0, arr30.length - 1, 10);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Small After: " + Arrays.toString(arr30));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr31 = Arrays.copyOf(reversed, reversed.length);
                System.out.println("Reversed Before: " + Arrays.toString(arr31));
                mergeSort(arr31, 0, arr31.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Reversed After: " + Arrays.toString(arr31));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr32 = Arrays.copyOf(sorted, sorted.length);
                System.out.println("Sorted Before: " + Arrays.toString(arr32));
                mergeSort(arr32, 0, arr32.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Sorted After: " + Arrays.toString(arr32));
                System.out.println("Runtime: " + timeTaken + " ms");

                startTime = System.currentTimeMillis();
                int[] arr33 = Arrays.copyOf(large, large.length);
                System.out.println("Large Before: " + Arrays.toString(arr33));
                mergeSort(arr33, 0, arr33.length - 1);
                timeTaken = System.currentTimeMillis() - startTime;
                System.out.println("Large After: " + Arrays.toString(arr33));
                System.out.println("Runtime: " + timeTaken + " ms");

                System.out.println("Optimal Threshold test");
                int[] arr34 = new Random().ints(100000, 0, 100000).toArray();
                testThresholds(arr34);
        }
}
