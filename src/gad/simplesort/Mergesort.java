package gad.simplesort;

import java.util.Arrays;

public class Mergesort extends SortAlgorithm {
	private int selectionSortSize;
	private Selectionsort selectionSort;

	public Mergesort(int selectionSortSize) {
		this.selectionSortSize = selectionSortSize;
		// TODO: Selectionsort Optimierung
		selectionSort = new Selectionsort();
	}

	@Override
	public void sort(int[] numbers, Result result, int from, int to) {
		int[] helper = new int[numbers.length];
		sort(numbers, result, from, to, helper);
	}

	public void sort(int[] numbers, Result result, int from, int to, int[] helper) {
		if (from >= to) {
			return;
		}

		result.startMergesort(numbers, from, to);

		// Check is subarray is already in order
		if (isSorted(numbers, from, to)) {
			return;
		}

		// SelectionSort Optimierung
		if (to - from + 1 <= selectionSortSize) {
			selectionSort.sort(numbers, result, from, to);
			return;
		}

		int mid = (from + to) / 2;
		sort(numbers, result, from, mid, helper);
		sort(numbers, result, mid + 1, to, helper);
		merge(numbers, from, mid, to, helper);
		result.logPartialArray(numbers, from, to);
	}

	public void merge(int[] numbers, int left, int mid, int right, int[] helper) {
		// Check is subarray is already in order
		if (isSorted(numbers, left, right)) {
			return;
		}

		int indexL = left;
		int indexR = mid + 1;
		int length = right - left + 1;

		for (int i = 0; i < length; i++) {
			if (indexL > mid) { // linker Teil leer
				helper[i] = numbers[indexR];
				indexR++;
			} else if (indexR > right) { // rechter Teil leer
				helper[i] = numbers[indexL];
				indexL++;
			} else if (numbers[indexL] <= numbers[indexR]) {
				helper[i] = numbers[indexL];
				indexL++;
			} else {
				helper[i] = numbers[indexR];
				indexR++;
			}
		}

		// Zurückkopieren
		for (int i = 0; i < length; i++) {
			numbers[left + i] = helper[i];
		}
	}

	public boolean isSorted (int[] numbers, int from, int to) {
		for (int i = from + 1; i <= to; i++) {
			if (numbers[i-1] > numbers[i]) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "Mergesort with helper array (Selectionsort for: " + selectionSortSize + ")";
	}
}