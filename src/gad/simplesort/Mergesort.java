package gad.simplesort;

import java.util.Arrays;

public class Mergesort extends SortAlgorithm {
	private int selectionSortSize;
	private Selectionsort selectionSort;

	private MergesortSimple mergesortSimple;

	public Mergesort(int selectionSortSize) {
		this.selectionSortSize = selectionSortSize;
		// TODO: Selectionsort Optimierung
		selectionSort = new Selectionsort();
		mergesortSimple = new MergesortSimple(selectionSortSize);

	}

	@Override
	public void sort(int[] numbers, Result result, int from, int to) {
		int[] helper =  new int[numbers.length];
		sort(numbers, result, from, to, helper);
	}

	public void sort(int[] numbers, Result result, int from, int to, int[] helper) {
		if (from >= to) {
			return;
		}
		int[] sorted = Arrays.copyOfRange(numbers, from, to+1);
		int[] subArray = Arrays.copyOfRange(numbers, from, to+1);

		Arrays.sort(sorted);
		if (Arrays.equals(sorted, subArray)) {
			return;
		}

		result.startMergesort(numbers, from, to);

		// SelectionSort Optimierung
		if (numbers.length <= selectionSortSize) {
			selectionSort.sort(numbers, result, from, to);
			return;
		}

		int mid = (from + to) / 2;

		// Recursive call
		sort(numbers, result, from, mid, helper);
		sort(numbers, result, mid + 1, to, helper);

		mergesortSimple.merge(numbers, from, mid, to);

		result.logPartialArray(numbers, from, to);
	}

	@Override
	public String toString() {
		return "Mergesort with helper array (Selectionsort for: " + selectionSortSize + ")";
	}
}