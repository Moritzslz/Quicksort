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

				int medianValue = 0;
				int medianIndex = 0;

				int length = numberOfConsideredElements;
				int[] temp = new int[length];

				if (numberOfConsideredElements == 1) {
					return from;
				}


				for(int i = 0; i < length && i <= to; i++) {
					temp[i] = numbers[from+i];
				}

				Arrays.sort(temp);

				if (length % 2 == 0) {
					medianValue = (temp[length/2-1] + temp[length/2]) / 2;
				} else {
					medianValue = temp[(length-1)/2];
				}

				for(int i = from; i < from+length; i++) {
					if(numbers[i] == medianValue) {
						medianIndex = i;
						break;
					}
				}
				return medianIndex;

			}

			@Override
			public String toString() {
				return "The medianValue of the first " + numberOfConsideredElements + " elements";
			}
		};
	}

	static PivotFinder getMedianPivotDistributed(int numberOfConsideredElements) {
		return new PivotFinder() {
			@Override
			public int findPivot(int[] numbers, int from, int to) {

				int medianValue = 0;
				int medianIndex = 0;
				int distance = (int) Math.ceil((to - from + 1) / numberOfConsideredElements);

				int length = numberOfConsideredElements;
				int[] temp = new int[length];

				if (numberOfConsideredElements == 1) {
					return from;
				}


				for(int i = 0; i < length && i <= to; i += distance) {
					temp[i] = numbers[from+i];
				}

				Arrays.sort(temp);

				if (length % 2 == 0) {
					medianValue = (temp[length/2-1] + temp[length/2]) / 2;
				} else {
					medianValue = temp[(length-1)/2];
				}

				for(int i = from; i < from+length; i++) {
					if(numbers[i] == medianValue) {
						medianIndex = i;
						break;
					}
				}
				return medianIndex;

			}

			@Override
			public String toString() {
				return "The medianValue of " + numberOfConsideredElements + " elements distributed throughout the array";
			}
		};
	}
}