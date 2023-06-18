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
		if (sorted.equals(subArray)) {
			return;
		}

		result.startMergesort(numbers, from, to);

		// SelectionSort Optimierung
		if (numbers.length <= selectionSortSize) {
			selectionSort.sort(numbers, result, from, to);
		}

		int mid = (from + to) / 2;

		// Recursive call
		sort(numbers, result, from, mid, helper);
		sort(numbers, result, mid + 1, to, helper);

		// Merging
		int indexL = from;
		int indexR = mid + 1;
		int length = to - from + 1;

		for (int i = 0; i < length; i++) {
			if (indexL > mid) { // linker Teil leer
				helper[i] = numbers[indexR];
				indexR++;
			} else if (indexR > to) { // rechter Teil leer
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
			numbers[from + i] = helper[i];
		}

		result.logPartialArray(numbers, from, to);
	}

	@Override
	public String toString() {
		return "Mergesort with helper array (Selectionsort for: " + selectionSortSize + ")";
	}
}