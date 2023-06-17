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
				int index = from;
				int median;
				int[] temp;
				int length;

				// Edge cases
				if (numberOfConsideredElements > to - from + 1) {length = to - from + 1;}
				else {length = numberOfConsideredElements;}
				if (length > numbers.length) {length = numbers.length;}
				if (from == to || from == to - 1) {return from;}

				temp = new int[length];

				for (int i = 0; i < length; i++) {
					temp[i] = numbers[from + i];
				}

				median = PivotFinder.getMedian(temp);

				for (int i = 0; i < length; i++) {
					index = from + i;
					if (numbers[from + i] == median) {
						break;
					}
				}

				return index;
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
				int index = from;
				int median;
				int[] temp;
				int length;
				int gap = (int) Math.ceil((double) (to - from + 1) / numberOfConsideredElements) - 1;

				//System.out.println("Gap: " + gap);

				// Edge cases
				if (numberOfConsideredElements > to - from + 1) {length = to - from + 1;}
				else {length = numberOfConsideredElements;}
				if (length > numbers.length) {length = numbers.length;}
				if (from == to || from == to - 1) {return from;}

				temp = new int[length];

				int k = 0;
				for (int i = from; i < numbers.length; i += (gap + 1)) {
					temp[k] = numbers[i];
					k++;
				}

				//System.out.println("Temp: " + Arrays.toString(temp));

				median = PivotFinder.getMedian(temp);

				for (int i = 0; i < numbers.length; i += (gap + 1)) {
					index = from + i;
					if (numbers[from + i] == median) {
						break;
					}
				}

				return index;


			}

			@Override
			public String toString() {
				return "The medianValue of " + numberOfConsideredElements + " elements distributed throughout the array";
			}
		};
	}

	private static int getMedian(int[] array) {
		int median;
		Arrays.sort(array);

		if (array.length % 2 == 0) {
			// Length is even
			// Smaller middle value for [1, 2, 3, 4] it would be 2 at index 1
			median = array[array.length / 2 - 1];
		} else {
			// Length is odd
			// Middle value for [1, 2, 3, 4, 5] it would be 3 at index 2
			median = array[array.length / 2];
		}

		return median;
	}
}