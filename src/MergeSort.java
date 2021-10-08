import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * A class representing the sorting of an array of integers, which are read from a file, using the Merge Sort technique
 * The Merge Sort algorithm was taken from the following link:
 * @link https://www.geeksforgeeks.org/counting-inversions/
 * @author XarisGeorgiadis
 */
public class MergeSort {

    // Integer that holds the number of inversions where A[i] = A[j] + 1, where A is an array of integers
    private int two_costInversions;

    // Constructor
    public MergeSort() {
        two_costInversions = 0;
    }

    /**
     * Recursive function that performs the division of an array into 2 sub-arrays in order to perform Merge Sorting,
     * and calculates the number of required inversions in order to complete the sorting of each array
     * @param array Array of integers
     * @param l Integer holding the left index of the current array
     * @param r Integer holding the right index of the current array
     * @return Long variable that holds the number of required inversions in order to complete the sorting of each array
     */
    public long mergeSort(int[] array, int l, int r) {
        // Initializing the number of inversions for each node of the recursion tree
        long inversions = 0;
        // Checking whether an array can be divided into 2 sub-arrays or not
        if (l < r) {
            // Calculating the array's the midpoint, where the inversion will occur
            int m = (l + r) / 2;
            // Recursively dividing an array into a left and right sub-array until each node has one element
            inversions += mergeSort(array, l, m);
            inversions += mergeSort(array, m + 1, r);
            // Sorting each sub-array and merging them into one
            inversions += merge(array, l, m, r);
        }
        return inversions;
    }

    /**
     * Functions that performs the sorting and merging of two arrays
     * @param a Current array
     * @param l Integer holding the left index of the current array
     * @param m Integer holding the midpoint of the current array
     * @param r Integer holding the right index of the current array
     * @return Long variable that holds the number of required inversions in order to complete the sorting of each array
     */
    public long merge(int[] a, int l, int m, int r) {
        // Initializing the number of inversions for the current node of the recursion tree
        long inversions = 0;
        // Dividing the array into two sub-arrays based on the left, midpoint and right indexes that are given
        int[] b = Arrays.copyOfRange(a, l, m + 1);
        int[] c = Arrays.copyOfRange(a, m + 1, r + 1);
        // Initializing the first index of the left sub-array
        int i = 0;
        // Initializing the first index of the right sub-array
        int j = 0;
        // Initializing the first index of the merged array that does not contain an element
        int k = l;
        // Loop that performs the sorting and merging of the two arrays, while also counting the number of inversions
        while (i < b.length && j < c.length) {
            if (b[i] <= c[j]) {
                a[k++] = b[i++];
            }
            else {
                // Checking for each remaining element of the left sub-array whether A[i] = A[j] + 1. If the condition
                // is true, then the two_costInversions variable is increased by one. The loop is stopped once this
                // condition is false
                for (int n = i; n < b.length; n++) {
                    if (b[n] == c[j] + 1)
                        this.two_costInversions++;
                    else
                        break;
                }
                // Increasing the number of inversions based on the number of elements left in the left sub-array
                inversions += (m + 1) - (l + i);
                a[k++] = c[j++];
            }
        }
        // If one of the two sub-arrays is empty, all the elements of the other sub-array are added to the merged array
        while (i < b.length)
            a[k++] = b[i++];
        while (i < c.length)
            a[k++] = c[i++];
        return inversions;
    }


    /**
     * Main function of the program
     * @param args Contains command line arguments
     * @throws FileNotFoundException if a file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        // Scanner containing the contents of a file given as a command line argument
        Scanner scn = new Scanner(new File(args[0]));
        // Adding the integers contained in the Scanner into an ArrayList of integers.
        ArrayList<Integer> integers = new ArrayList<>();
        while (scn.hasNext()) {
            integers.add(scn.nextInt());
        }
        // Adding the integers contained in the ArrayList into an Array of integers
        int[] integerArray = new int[integers.size()];
        for (int i = 0; i < integerArray.length; i++)
            integerArray[i] = integers.get(i);
        MergeSort mergeObject = new MergeSort();
        // Initializing the number of inversions
        long inversions;
        // Performing Merge Sorting to the Array
        inversions = mergeObject.mergeSort(integerArray, 0, integerArray.length - 1);
        // Calculating the total cost of the Merge Sort algorithm
        long cost = 3 * inversions - mergeObject.two_costInversions;
        // Displaying the sorted integers
        for (int i : integerArray)
            System.out.print(i + " ");
        System.out.println("\nTotal Inversion Cost: " + cost);
    }
}
