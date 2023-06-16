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

				if (numberOfConsideredElements == 1) {
					return from;
				} else if (from == to) {
					return from;
				} else if (from == to - 1) {
					return from;
				} else if (to - from + 1 < numberOfConsideredElements) {
					length = to - from + 1;
				}

				int[] temp = new int[length];

				for(int i = 0; from + i <= to && i < length; i ++) {
					temp[i] = numbers[from + i];
				}

				Arrays.sort(temp);

				if (length % 2 == 0) {
					medianValue = (temp[length/2-1] + temp[length/2]) / 2;
				} else {
					medianValue = temp[(length-1)/2];
				}

				for(int i = from; i <= to; i++) {
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
				double number = (double) (to - from + 1) / numberOfConsideredElements;
				double distance = Math.ceil(number);

				int length = numberOfConsideredElements;

				if (numberOfConsideredElements == 1) {
					return from;
				} else if (from == to) {
					return from;
				} else if (from == to - 1) {
					return from;
				} else if (to - from + 1 < numberOfConsideredElements) {
					length = to - from + 1;
				}

				int[] temp = new int[length];

				int k = 0;
				for(int i = 0; from + i <= to && k < length; i += distance) {
					temp[k] = numbers[from+i];
					k++;
				}

				Arrays.sort(temp);

				if (length % 2 == 0) {
					medianValue = (temp[length/2-1] + temp[length/2]) / 2;
				} else {
					medianValue = temp[(length-1)/2];
				}

				for(int i = from; i <= to; i += distance) {
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