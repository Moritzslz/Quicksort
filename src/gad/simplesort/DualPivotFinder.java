package gad.simplesort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public interface DualPivotFinder {

	int[] findPivot(int[] numbers, int from, int to);

	static DualPivotFinder getFirstLastPivot() {
		return new DualPivotFinder() {

			@Override
			public int[] findPivot(int[] numbers, int from, int to) {
				int[] pivots = new int[2];
				pivots[0] = from;
				pivots[1] = to;
				return pivots;
			}

			@Override
			public String toString() {
				return "The first and last element";
			}
		};
	}

	static DualPivotFinder getRandomPivot(int seed) {
		Random random = new Random(seed);

		return new DualPivotFinder() {
			@Override
			public int[] findPivot(int[] numbers, int from, int to) {
				int[] pivots = new int[2];
				pivots[0] = random.nextInt(from, to+1);
				pivots[1] = random.nextInt(from, to+1);
				while (pivots[1] == pivots[0]) {
					pivots[1] = random.nextInt(from, to+1);
				}
				return pivots;
			}

			@Override
			public String toString() {
				return "Two random elements";
			}
		};
	}

	static DualPivotFinder getMedianPivotFront(int numberOfConsideredElements) {
		return new DualPivotFinder() {
			@Override
			public int[] findPivot(int[] numbers, int from, int to) {
				int[] pivots = new int[2];
				int[] temp = new int[numberOfConsideredElements];
				int length;

				int gapSize = (to - from) / (numberOfConsideredElements - 1);

				// Edge cases
				if (numberOfConsideredElements > to - from + 1) {length = to - from + 1;}
				else {length = numberOfConsideredElements;}
				if (length > numbers.length) {length = numbers.length;}

				for (int i = 1; i <= pivots.length; i++) {
					pivots[i-1] = numbers[from+gapSize * i];
				}

				return pivots;
			}

			@Override
			public String toString() {
				return "The thirds of the first " + numberOfConsideredElements + " elements";
			}
		};
	}

	static DualPivotFinder getMedianPivotDistributed(int numberOfConsideredElements) {
		return new DualPivotFinder() {
			@Override
			public int[] findPivot(int[] numbers, int from, int to) {
				int[] indices = new int[numberOfConsideredElements];
				for (int i = 0; i < numberOfConsideredElements; i++) {
					indices[i] = from + i;
				}

				// Sort the indices based on the corresponding values in numbers
				Arrays.sort(indices);

				int pivot1 = indices[(numberOfConsideredElements - 1) / 3];
				int pivot2 = indices[(2 * (numberOfConsideredElements - 1)) / 3];

				return new int[]{pivot1, pivot2};
			}

			@Override
			public String toString() {
				return "The thirds of " + numberOfConsideredElements + " elements distributed thoughout the array";
			}
		};
	}
}