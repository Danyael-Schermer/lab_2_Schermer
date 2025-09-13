package Code;

public class SortingComparison {
        
        public void quickSort(int[] array, int left, int right) {
                if (left < right) {
                        int partitionPos = partition(array, left, right);

                        quickSort(array, left, partitionPos - 1);
                        quickSort(array, partitionPos + 1, right);
                }

        }

        static int partition(int[] array, int left, int right) {
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

        static void swap(int[] array, int i, int j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
        }

        public void mergeSort(int[] array, int l, int r) {
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
                int i = 0, j = 0;

                // Initial index of merged subarray array
                int k = l;
                while (i < n1 && j < n2) {
                if (L[i] <= R[j]) {
                        arr[k] = L[i];
                        i++;
                }
                else {
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
}
