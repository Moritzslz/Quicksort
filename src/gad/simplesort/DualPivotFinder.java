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

				// Edge cases
				if (numberOfConsideredElements > to - from + 1) {length = to - from + 1;}
				else {length = numberOfConsideredElements;}
				if (length > numbers.length) {length = numbers.length;}

				int gapSize = (to - from) / (numberOfConsideredElements - 1) - 1;
				System.out.println("Gap: " + gapSize);

				pivots[0] = from + gapSize;
				pivots[1] = from + 2 * gapSize;

				/*
				if (pivots[0] > pivots[1]) {
					int temp = pivots[0];
					pivots[0] = pivots[1];
					pivots[1] = temp;
				}

				 */

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
				int[] pivots = new int[2];
				int length;

				// Edge cases
				if (numberOfConsideredElements > to - from + 1) {length = to - from + 1;}
				else {length = numberOfConsideredElements;}
				if (length > numbers.length) {length = numbers.length;}

				int gapSize = length / (numberOfConsideredElements - 1);
				System.out.println("Gap: " + gapSize);

				pivots[0] = numbers[from + gapSize];
				pivots[1] = numbers[from + 2 * gapSize];

				if (pivots[0] > pivots[1]) {
					int temp = pivots[0];
					pivots[0] = pivots[1];
					pivots[1] = temp;
				}

				return pivots;
			}

			@Override
			public String toString() {
				return "The thirds of " + numberOfConsideredElements + " elements distributed thoughout the array";
			}
		};
	}
}