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

		result.startDualPivotQuicksort(numbers, from, to);

		int length = to - from + 1;

		// SelectionSort Optimierung
		if (length <= selectionSortSize) {
			selectionSort.sort(numbers, result, from, to);
			return;
		}

		int pivots[] = pivotFinder.findPivot(numbers, from, to);

		if (pivots[1] == from) {
			pivots[1] = pivots[0];
		}

		int leftPivot = pivots[0];
		int rightPivot = pivots[1];

		// Switching the Pivot elements to the first/last index
		swap(numbers, leftPivot, from);
		swap(numbers, rightPivot, to);
		if (numbers[from] > numbers[to]) {
			swap(numbers, from, to);
		}

		int l = from + 1;
		int g = to - 1;
		int k = from + 1;
		int p = numbers[from];
		int q = numbers[to];

		while (k <= g) {
			if (numbers[k] < p) {
				swap(numbers, k, l);
				l++;
			} else if (numbers[k] >= q) {
				while (numbers[g] > q && k < g) {
					g--;
				}
				swap(numbers, k, g);
				g--;

				if (numbers[k] < p) {
					swap(numbers, k, l);
					l++;
				}
			}
			k++;
		}

		l--;
		g++;

		swap(numbers, from, l);
		swap(numbers, to, g);

		// Logging the new sub arrays
		result.logPartialArray(numbers, from, l - 1);
		result.logPartialArray(numbers, l + 1, g - 1);
		result.logPartialArray(numbers, g + 1, to);

		// Recursively sorting the sub arrays
		sort(numbers, result, from, l - 1);
		sort(numbers, result, l + 1, g - 1);
		sort(numbers, result, g + 1, to);
	}

	@Override
	public String toString() {
		return "DualPivotQuicksort (Pivot: " + pivotFinder + ", Selectionsort for: " + selectionSortSize + ")";
	}
}