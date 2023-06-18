package gad.simplesort;

import java.util.Arrays;
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
				int length;

				if (numberOfConsideredElements <= 3 || to - from + 1 <= 3) {
					if (numbers[from] < numbers[to]) {
						pivots[0] = from;
						pivots[1] = to;
					} else {
						pivots[0] = to;
						pivots[1] = from;
					}
				}

				int gapSize = (to - from) / (numberOfConsideredElements - 1);

				// Edge cases
				if (numberOfConsideredElements > to - from + 1) {length = to - from + 1;}
				else {length = numberOfConsideredElements;}
				if (length > numbers.length) {length = numbers.length;}

				for (int i = 1; i <= pivots.length; i++) {
					pivots[i-1] = numbers[from+gapSize * i];
				}

				if (numbers[pivots[0]] < numbers[pivots[1]]) {
					return pivots;
				} else {
					int temp = pivots[0];
					pivots[0] = pivots[1];
					pivots[1] = temp;
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
				int[] indices = new int[2];
				int length = numberOfConsideredElements;

				if (length < 3 || from >= to) {
					indices[0] = from;
					indices[1] = to;
					return indices;
				}

				int[] temp;

				int gapSize = (to - from) / (numberOfConsideredElements - 1);

				// Edge cases
				if (numberOfConsideredElements > to - from + 1) {length = to - from + 1;}
				else {length = numberOfConsideredElements;}
				if (length > numbers.length) {length = numbers.length;}

				temp = new int[length];

				int k = 0;
				for (int i = from; i < numbers.length && k < length; i += gapSize) {
					temp[k] = numbers[i];
					k++;
				}

				Arrays.sort(temp);

				int smallerPivotIndex = from + length / 3 - 1;
				int largerPivotIndex = from + 2 * length / 3 - 1;

				indices[0] = smallerPivotIndex;
				indices[1] = largerPivotIndex;

				return indices;
			}

			@Override
			public String toString() {
				return "The thirds of " + numberOfConsideredElements + " elements distributed thoughout the array";
			}
		};
	}
}