package gad.simplesort;

import java.util.Arrays;
import java.util.Random;

public interface PivotFinder {

	int findPivot(int[] numbers, int from, int to);

	static PivotFinder getLastPivot() {
		return new PivotFinder() {

			@Override
			public int findPivot(int[] numbers, int from, int to) {
				return to;
			}

			@Override
			public String toString() {
				return "The last element";
			}
		};
	}

	static PivotFinder getMidPivot() {
		return new PivotFinder() {

			@Override
			public int findPivot(int[] numbers, int from, int to) {
				return (to - from) / 2 + from;
			}

			@Override
			public String toString() {
				return "The middle element";
			}
		};
	}

	static PivotFinder getRandomPivot(int seed) {
		Random random = new Random(seed);

		return new PivotFinder() {
			@Override
			public int findPivot(int[] numbers, int from, int to) {
				return random.nextInt(from, to+1);
			}

			@Override
			public String toString() {
				return "A random element";
			}
		};
	}

	static PivotFinder getMedianPivotFront(int numberOfConsideredElements) {
		return new PivotFinder() {
			@Override
			public int findPivot(int[] numbers, int from, int to) {
				int median = -1;
				int length = to - from + 1;
				int[] temp = new int[length];

				if (length > numberOfConsideredElements)
					length = numberOfConsideredElements;

				for(int i = 0; i < length; i++) {
					temp[i] = numbers[to+i];
				}

				Arrays.sort(temp);

				if (length % 2 == 0) {
					median = (temp[length/2-1] + temp[length/2]) / 2;
				} else {
					median = temp[length/2];
				}

				return median;
			}

			@Override
			public String toString() {
				return "The median of the first " + numberOfConsideredElements + " elements";
			}
		};
	}

	static PivotFinder getMedianPivotDistributed(int numberOfConsideredElements) {
		return new PivotFinder() {
			@Override
			public int findPivot(int[] numbers, int from, int to) {
				// TODO
				return 0;
			}

			@Override
			public String toString() {
				return "The median of " + numberOfConsideredElements + " elements distributed throughout the array";
			}
		};
	}
}