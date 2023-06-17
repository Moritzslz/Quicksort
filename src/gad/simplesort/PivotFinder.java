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

				median = MedianFinder.getMedian(temp);

				for (int i = 0; i < length; i++) {
					index = from + i;
					if (numbers[from + i] == median) {
						break;
					}
				}

				if (index >= from && index <= to) {
					return index;
				} else {
					return from;
				}
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
				//if (numberOfConsideredElements > to - from + 1) {length = to - from + 1;}
				//else {length = numberOfConsideredElements;}
				length = numberOfConsideredElements;
				if (length > numbers.length) {length = numbers.length;}
				if (from == to || from == to - 1) {return from;}

				temp = new int[length];

				int k = 0;
				for (int i = from; i < numbers.length && k < length; i += (gap + 1)) {
					temp[k] = numbers[i];
					k++;
				}

				//System.out.println("Temp: " + Arrays.toString(temp));

				median = MedianFinder.getMedian(temp);

				for (int i = 0; from + i < numbers.length; i += (gap + 1)) {
					index = from + i;
					if (numbers[from + i] == median) {
						break;
					}
				}

				if (index >= from && index <= to) {
					return index;
				} else {
					return from;
				}
			}

			@Override
			public String toString() {
				return "The medianValue of " + numberOfConsideredElements + " elements distributed throughout the array";
			}
		};
	}
}