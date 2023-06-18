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
		if (from >= to) {
			return;
		}

		result.startMergesort(numbers, from, to);

		// SelectionSort Optimierung
		if (to - from + 1 <= selectionSortSize) {
			selectionSort.sort(numbers, result, from, to);
			return;
		}

		int[] helper = new int[numbers.length];
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

		int mid = (from + to) / 2;
		sort(numbers, result, from, mid, helper);
		sort(numbers, result, mid + 1, to, helper);
		merge(numbers, from, mid, to, helper);
		result.logPartialArray(numbers, from, to);
	}

	public void merge(int[] numbers, int left, int mid, int right, int[] helper) {
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

	@Override
	public String toString() {
		return "Mergesort with helper array (Selectionsort for: " + selectionSortSize + ")";
	}
}