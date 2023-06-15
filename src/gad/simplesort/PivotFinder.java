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

				int medianValue = -1;
				int medianIndex = -1;
				int length = to - from + 1;
				int[] temp = new int[length];

				if (length > numberOfConsideredElements)
					length = numberOfConsideredElements;

				for(int i = 0; i < length; i++) {
					temp[i] = numbers[from+i];
				}

				Arrays.sort(temp);

				if (length % 2 == 0) {
					medianValue = (temp[length/2-1] + temp[length/2]) / 2;
				} else {
					medianValue = temp[length/2];
				}

				for(int i = from; i < from+length; i++) {
					if(numbers[from] == medianValue) {
						medianIndex = from;
						break;
					}
				}
				return medianIndex;
				 /*
				// Approach: calculating the average of all elements and then looping through
				// all elements searching for the biggest value which is still smaller than the average.
				int medianValue = -1;
				int length = to - from + 1;

				if (length > numberOfConsideredElements)
					length = numberOfConsideredElements;

				//
				for(int i = from; i < length; i++) {
					temp[i] = numbers[to+i];
				}

				  */
			}

			@Override
			public String toString() {
				return "The medianValue of the first " + numberOfConsideredElements + " elements";
			}
		};
	}

	static PivotFinder getmedianValuePivotDistributed(int numberOfConsideredElements) {
		return new PivotFinder() {
			@Override
			public int findPivot(int[] numbers, int from, int to) {
				// TODO
				return 0;
			}

			@Override
			public String toString() {
				return "The medianValue of " + numberOfConsideredElements + " elements distributed throughout the array";
			}
		};
	}
}