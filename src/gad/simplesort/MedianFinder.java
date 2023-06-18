package gad.simplesort;

import java.util.Arrays;

public class MedianFinder {

    public static int getMedian(int[] array) {
        int median;
        Arrays.sort(array);

        if (array.length % 2 == 0) {
            // Length is even
            // Smaller middle value for [1, 2, 3, 4] it would be 2 at index 1
            median = array[array.length / 2 - 1];
        } else {
            // Length is odd
            // Middle value for [1, 2, 3, 4, 5] it would be 3 at index 2
            median = array[array.length / 2];
        }

        return median;
    }
}
