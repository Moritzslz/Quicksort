package gad.simplesort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class SortingComparison {
    private static final int SEED = 1337;
    private static final Random random = new Random(SEED);

    private static final int ARRAY_SIZE = 5_000_000;
    private static final int GUARANTEED_MULTIPLES = 10;
    private static final int SORTED_BLOCK_SIZE = 100;
    private static final double SORTED_RANDOM_ELEMENTS_PERCENT = 0.1;
    private static final int REPETITIONS = 5;

    private SortingComparison() {

    }

    public static void main(String[] args) {
        int[] numbers = new int[] {9, 1, 8, 5, 2, 3, 4, 6, 0, 7};
        DualPivotFinder pivotFinder = DualPivotFinder.getMedianPivotFront(3);
        int[] pivots = pivotFinder.findPivot(numbers, 0, numbers.length-1);
        System.out.println(Arrays.toString(pivots));



        /*
        int[] numbers = new int[] {9, 1, 8, 5, 2, 3, 4, 6, 0, 7};
        int length = 10;
        int leftPivot = 3;
        int rightPivot = 5;
        int[] left = new int[10];
        int[] middle = new int[10];
        int[] right = new int[10];
        int l = 0;
        int m = 0;
        int r = 0;

        for (int i = 0; i < length; i++) {
            if (numbers[i] < leftPivot) {
                // Element less than left pivot
                left[l] = numbers[i];
                l++;
            } else if (numbers[i] > rightPivot) {
                // Element bigger than right pivot
                right[r] = numbers[i];
                r++;
            } else {
                // Element >= left pivot but <= right pivot
                middle[m] = numbers[i];
                m++;
            }
        }

        System.out.println("Left " + Arrays.toString(left));
        System.out.println("Middle " + Arrays.toString(middle));
        System.out.println("Right " + Arrays.toString(right));
        // Reordering the numbers array according to the now sorted sub arrays
        System.arraycopy(left, 0, numbers, 0, l);
        System.arraycopy(middle, 0, numbers, l, m);
        System.arraycopy(right, 0, numbers, l + m, r);

        System.out.println("New Array " + Arrays.toString(numbers));

        StudentResult result = new StudentResult();
        DualPivotQuicksort dualPivotQuicksort = new DualPivotQuicksort(DualPivotFinder.getFirstLastPivot(), 0);
        dualPivotQuicksort.sort(numbers , result, 0, 9);
         */

        // comparePivotSelectionQuicksort();
        // compareMergesortHelperArray();
        // compareDualPivotQuicksort();
        comparePivotSelectionDualPivotQuicksort();
        // compareSelectionSortSizeQuicksort();
        // compareSelectionSortSizeMergesort();
        // compareSelectionsortSizeDualPivotQuicksort();
        // compareBestAlgorithms();
        // comparePivotSelectionQuicksort();
    }

    private static void compareAlgorithms(SortAlgorithm[] algorithms) {
        int[] numbers = getRandomArray(ARRAY_SIZE);
        int[] numbersWithMultiples = getRandomArray(ARRAY_SIZE, GUARANTEED_MULTIPLES);
        // partiallySortArrayBlocks(numbers, SORTED_BLOCK_SIZE);
        // partiallySortArrayRandomValues(numbers, SORTED_RANDOM_ELEMENTS_PERCENT);
        // reverseArray(numbers)
        compareAlgorithms(algorithms, numbers, REPETITIONS);


        // Own testing
        /*
        int[] array = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] array2 = new int[] {9, 1, 8, 5, 2, 3, 5, 1, 0, 7};
        int[] array3 = new int[] {5, 3, 6, 7, 9};
        int[] array4 = new int[] {6, 7, 8};
        int[] array5 = new int[] {5, 5, 5, 5, 5, 5, 5, 5};
        int[] array6 = new int[]{1, 5, 5, 5, 5, 5, 5, 7, 8};
        int[] array7 = new int[]{1, 2, 3, 4, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 9};

        System.out.println(PivotFinder.getMedianPivotDistributed(3).findPivot(array, 0 ,9));
        System.out.println(PivotFinder.getMedianPivotDistributed(5).findPivot(array2, 0 ,9));
        System.out.println(PivotFinder.getMedianPivotDistributed(5).findPivot(array, 1 ,9));
        System.out.println(PivotFinder.getMedianPivotDistributed(2).findPivot(array6, 0 ,array6.length-1));
        System.out.println(PivotFinder.getMedianPivotDistributed(4).findPivot(array7, 0 ,array7.length-1));
        System.out.println(PivotFinder.getMedianPivotDistributed(5).findPivot(array7, 0 ,array7.length-1));
        System.out.println(PivotFinder.getMedianPivotFront(3).findPivot(array, 0 ,9));
        System.out.println(PivotFinder.getMedianPivotFront(5).findPivot(array2, 0 ,9));
        System.out.println(PivotFinder.getMedianPivotFront(5).findPivot(array2, 2 ,8));
        System.out.println(PivotFinder.getMedianPivotDistributed(3).findPivot(array, 0 ,9));
        System.out.println(PivotFinder.getMedianPivotDistributed(5).findPivot(array2, 0 ,9));
        System.out.println(PivotFinder.getMedianPivotDistributed(5).findPivot(array2, 1 ,9));
        System.out.println("#");
        System.out.println(PivotFinder.getMedianPivotDistributed(3).findPivot(array5, 3 ,5));
        System.out.println(PivotFinder.getMedianPivotDistributed(5).findPivot(array5, 0 ,5));
        System.out.println(PivotFinder.getMedianPivotDistributed(3).findPivot(array3, 0 ,4));
        System.out.println(PivotFinder.getMedianPivotDistributed(5).findPivot(array3, 0 ,4));
        System.out.println(PivotFinder.getMedianPivotDistributed(5).findPivot(array3, 2 ,4));
        System.out.println("##");
        System.out.println(PivotFinder.getMedianPivotDistributed(3).findPivot(array4, 0 ,2));
        System.out.println(PivotFinder.getMedianPivotDistributed(3).findPivot(array4, 0 ,1));
        System.out.println(PivotFinder.getMedianPivotDistributed(5).findPivot(array4, 0 ,2));
         */
    }

    private static void comparePivotSelectionQuicksort() {
        SortAlgorithm[] algorithms = new SortAlgorithm[8];
        int selectionSortSize = 60;

        algorithms[0] = new Quicksort(PivotFinder.getLastPivot(), selectionSortSize);
        algorithms[1] = new Quicksort(PivotFinder.getMidPivot(), selectionSortSize);
        algorithms[2] = new Quicksort(PivotFinder.getRandomPivot(SEED), selectionSortSize);
        algorithms[3] = new Quicksort(PivotFinder.getMedianPivotFront(3), selectionSortSize);
        algorithms[4] = new Quicksort(PivotFinder.getMedianPivotFront(5), selectionSortSize);
        algorithms[5] = new Quicksort(PivotFinder.getMedianPivotDistributed(3), selectionSortSize);
        algorithms[6] = new Quicksort(PivotFinder.getMedianPivotDistributed(5), selectionSortSize);
        algorithms[7] = new JavaSort();

        compareAlgorithms(algorithms);
    }

    private static void compareMergesortHelperArray() {
        SortAlgorithm[] algorithms = new SortAlgorithm[3];
        algorithms[0] = new MergesortSimple(80);
        algorithms[1] = new Mergesort(80);
        algorithms[2] = new JavaSort();

        compareAlgorithms(algorithms);
    }

    private static void compareDualPivotQuicksort() {
        SortAlgorithm[] algorithms = new SortAlgorithm[3];
        int selectionSortSize = 0;

        algorithms[0] = new Quicksort(PivotFinder.getLastPivot(), selectionSortSize);
        algorithms[1] = new DualPivotQuicksort(DualPivotFinder.getFirstLastPivot(), selectionSortSize);
        algorithms[2] = new JavaSort();

        compareAlgorithms(algorithms);
    }

    private static void comparePivotSelectionDualPivotQuicksort() {
        SortAlgorithm[] algorithms = new SortAlgorithm[5];
        int selectionSortSize = 60;

        algorithms[0] = new DualPivotQuicksort(DualPivotFinder.getFirstLastPivot(), selectionSortSize);
        algorithms[1] = new DualPivotQuicksort(DualPivotFinder.getRandomPivot(SEED), selectionSortSize);
        algorithms[2] = new DualPivotQuicksort(DualPivotFinder.getMedianPivotFront(5), selectionSortSize);
        algorithms[3] = new DualPivotQuicksort(DualPivotFinder.getMedianPivotDistributed(5), selectionSortSize);
        algorithms[4] = new JavaSort();

        compareAlgorithms(algorithms);
    }

    private static void compareSelectionSortSizeQuicksort() {
        SortAlgorithm[] algorithms = new SortAlgorithm[26];

        for (int i = 0; i < algorithms.length - 1; i++) {
            algorithms[i] = new Quicksort(PivotFinder.getLastPivot(), i * 5);
        }

        algorithms[algorithms.length - 1] = new JavaSort();

        compareAlgorithms(algorithms);
    }

    private static void compareSelectionSortSizeMergesort() {
        SortAlgorithm[] algorithms = new SortAlgorithm[26];

        for (int i = 0; i < algorithms.length - 1; i++) {
            algorithms[i] = new Mergesort(i * 5);
        }

        algorithms[algorithms.length - 1] = new JavaSort();

        compareAlgorithms(algorithms);
    }

    private static void compareSelectionsortSizeDualPivotQuicksort() {
        SortAlgorithm[] algorithms = new SortAlgorithm[26];

        for (int i = 0; i < algorithms.length - 1; i++) {
            algorithms[i] = new DualPivotQuicksort(DualPivotFinder.getFirstLastPivot(), i * 5);
        }

        algorithms[algorithms.length - 1] = new JavaSort();

        compareAlgorithms(algorithms);
    }

    private static void compareBestAlgorithms() {
        SortAlgorithm[] algorithms = new SortAlgorithm[4];
        algorithms[0] = new Quicksort(PivotFinder.getMedianPivotDistributed(3), 35);
        algorithms[1] = new DualPivotQuicksort(DualPivotFinder.getMedianPivotDistributed(5), 35);
        algorithms[2] = new Mergesort(35);
        algorithms[3] = new JavaSort();

        compareAlgorithms(algorithms);
    }

    private static void compareAlgorithms(SortAlgorithm[] algorithms, int[] numbers, int repetitions) {
        Result silent = new SilentResult();
        int longestDescription = Arrays.stream(algorithms).map(SortAlgorithm::toString).mapToInt(String::length).max()
                .orElse(0);
        int[][] arraysToSort = new int[algorithms.length * repetitions][];
        for (int i = 0; i < arraysToSort.length; i++) {
            arraysToSort[i] = Arrays.copyOf(numbers, numbers.length);
        }

        printFormatted("Algorithm:", "Time", longestDescription);
        for (int i = 0; i < algorithms.length; i++) {
            long startTime = System.currentTimeMillis();
            for (int j = 0; j < repetitions; j++) {
                algorithms[i].sort(arraysToSort[i * repetitions + j], silent);
            }

            int time = (int) (System.currentTimeMillis() - startTime);
            printFormatted(algorithms[i].toString(), Integer.toString(time), longestDescription);
        }
    }

    private static void printFormatted(String firstString, String secondString, int longestFirst) {
        System.out.println(firstString + " ".repeat(longestFirst - firstString.length()) + " ┃ " + secondString);
    }

    private static int[] getRandomArray(int length) {
        int[] randomArray = new int[length];
        for (int i = 0; i < length; i++) {
            randomArray[i] = random.nextInt();

        }

        return randomArray;
    }

    private static int[] getRandomArray(int length, int guaranteedMultiples) {
        List<Integer> randomList = new ArrayList<>();
        for (int i = 0; i * guaranteedMultiples < length; i++) {
            int randomValue = random.nextInt();
            for (int j = 0; j < guaranteedMultiples; j++) {
                randomList.add(randomValue);
            }
        }

        // Why is there no simple way to do this?
        Collections.shuffle(randomList);
        return randomList.stream().mapToInt(i -> i).toArray();
    }

    private static void partiallySortArrayBlocks(int[] numbers, int lengthOfSortedBlocks) {
        for (int i = 0; (i + 1) * lengthOfSortedBlocks < numbers.length; i++) {
            Arrays.sort(numbers, i * lengthOfSortedBlocks, (i + 1) * lengthOfSortedBlocks);
        }
    }

    private static void partiallySortArrayRandomValues(int[] numbers, double percentOfSortedValues) {
        int[] sortedArray = Arrays.copyOf(numbers, numbers.length);
        Arrays.sort(sortedArray);
        for (int i = 0; i < numbers.length * percentOfSortedValues; i++) {
            putIntoRightPlace(numbers, sortedArray, random.nextInt(numbers.length));
        }
    }

    private static void putIntoRightPlace(int[] numbers, int[] sortedArray, int index) {
        int correctIndex = Arrays.binarySearch(sortedArray, numbers[index]);
        swap(numbers, index, correctIndex);
    }

    private static void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    private static void reverseArray(int[] numbers) {
        for (int i = 0; i < numbers.length / 2; i++) {
            swap(numbers, i, numbers.length - i - 1);
        }
    }
}