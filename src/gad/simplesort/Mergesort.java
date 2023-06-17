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

		int mid = (from + to) / 2;
		sort(numbers, result, from, mid);
		sort(numbers, result, mid + 1, to);
		result.logPartialArray(numbers, from, to);
	}

	public void sort(int[] numbers, Result result, int from, int to, int[] helper) {
		int mid = (from + to) / 2;
		int indexL = from;
		int indexR = mid + 1;
		int length = from - to + 1;

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
	}

	@Override
	public String toString() {
		return "Mergesort with helper array (Selectionsort for: " + selectionSortSize + ")";
	}
}