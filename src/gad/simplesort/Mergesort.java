package gad.simplesort;

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
		if (from >= to) {
			return;
		}
		result.startMergesort(numbers, from, to);

		// SelectionSort Optimierung
		if (numbers.length <= selectionSortSize) {
			selectionSort.sort(numbers, result, from, to);
		}

		int mid = (from + to) / 2;
		//TODO
		//sort(numbers, result, from, mid);
		//sort(numbers, result, mid + 1, to);
		//merge(numbers, from, mid, to);
		result.logPartialArray(numbers, from, to);
	}

	public void sort(int[] numbers, Result result, int from, int to, int[] helper) {

	}

	@Override
	public String toString() {
		return "Mergesort with helper array (Selectionsort for: " + selectionSortSize + ")";
	}
}