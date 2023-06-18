package gad.simplesort;

public class DualPivotQuicksort extends SortAlgorithm {
	private DualPivotFinder pivotFinder;
	private int selectionSortSize;
	private Selectionsort selectionSort;

	public DualPivotQuicksort(DualPivotFinder pivotFinder, int selectionSortSize) {
		this.pivotFinder = pivotFinder;
		this.selectionSortSize = selectionSortSize;
		// TODO: Selectionsort Optimierung
		selectionSort = new Selectionsort();
	}

	@Override
	public void sort(int[] numbers, Result result, int from, int to) {
		if (from >= to) {
			return;
		}
		result.startQuicksort(numbers, from, to);

		// SelectionSort Optimierung
		if (to - from + 1 <= selectionSortSize) {
			selectionSort.sort(numbers, result, from, to);
			return;
		}

		int mid = numbers.length / 2;

		int[] selectedPivot = pivotFinder.findPivot(numbers, from, to);
		// Switching the higher Pivot to the last index
		swap(numbers, selectedPivot[1], to);
		// Switching the lower Pivot to the first index
		swap(numbers, selectedPivot[0], from);

		int p1 = numbers[to];
		int indexL = from - 1;
		int indexR = mid;
		while (indexL < indexR) {
			do {
				indexL++;
			} while (numbers[indexL] < p1);
			do {
				indexR--;
			} while (numbers[indexR] > p1 && indexR > from);
			if (indexL < indexR) {
				swap(numbers, indexL, indexR);
			}
		}
		// Pivot an richtige Stelle verschieben
		swap(numbers, indexL, to);

		int p2 = numbers[from];
		int indexL2 = mid + 1;
		int indexR2 = to;
		while (indexL2 < indexR2) {
			do {
				indexL2++;
			} while (numbers[indexL2] < p2);
			do {
				indexR--;
			} while (numbers[indexR2] > p2 && indexR2 > from);
			if (indexL2 < indexR2) {
				swap(numbers, indexL2, indexR2);
			}
		}
		// Pivot an richtige Stelle verschieben
		swap(numbers, indexL2, from);

		// Rekursiv beide Teile weiter sortieren
		result.logPartialArray(numbers, from, indexL - 1);
		result.logPartialArray(numbers, indexL + 1, to);
		sort(numbers, result, from, indexL - 1);
		sort(numbers, result, indexL + 1, to);
	}

	@Override
	public String toString() {
		return "DualPivotQuicksort (Pivot: " + pivotFinder + ", Selectionsort for: " + selectionSortSize + ")";
	}
}