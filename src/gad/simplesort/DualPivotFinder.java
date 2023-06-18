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

				if (to - from + 1 <= 3) {
					pivots[0] = from;
					pivots[1] = to;
				}

				if (numberOfConsideredElements == 5) {
					pivots[0] = from;
					pivots[1] = from + 1;
				} else if (numberOfConsideredElements == 4) {
					pivots[0] = from;
					pivots[1] = from + 1;
				} else if (numberOfConsideredElements <= 3) {
					int gap = to - from / (numberOfConsideredElements-1);
					pivots[0] = from + gap;
					pivots[1] = from + 2 * gap;
				}

				if (pivots[1] == from) {
					pivots[1] = pivots[0];
				}
				return pivots;
				/*
				// TODO
				int lowerBound = Math.min(numberOfConsideredElements, to - from + 1);
				int[] bufArray;


				bufArray = Arrays.copyOfRange(numbers, from, from + lowerBound);
				Arrays.sort(bufArray);


				int stepLength = (bufArray.length - (bufArray.length - 2) % 3) / 3;

				int piv1 = bufArray[stepLength];
				int piv2 = -1;

				if (bufArray.length == 1) {
					piv2 = bufArray[0];
				} else {
					if ((bufArray.length-2) % 3 == 2) {
						piv2 = bufArray[2 * stepLength + 2];
					} else piv2 = bufArray[2 * stepLength + 1];
				}


				int rs1 = 0;
				int rs2 = 0;
				for (int i = from; i < numbers.length; i++) {
					if (numbers[i] == piv1) {
						rs1 = i;
						break;
					}
				}
				for (int i = from; i < numbers.length; i++) {
					if (numbers[i] == piv2 && rs1 != i) {
						rs2 = i;
						break;
					}
				}
				if (rs2 == -1) rs2 = rs1;

				piv1 = Math.min(rs1, rs2);
				piv2 = Math.max(rs1, rs2);
				if (piv2 == from) {
					piv2 = piv1;
				}
				return new int[] {piv1, piv2};
				 */
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

				if (to - from + 1 <= 3) {
					pivots[0] = from;
					pivots[1] = to;
				}

				if (numberOfConsideredElements == 5) {
					pivots[0] = from;
					pivots[1] = from + 1;
				} else if (numberOfConsideredElements == 4) {
					pivots[0] = from;
					pivots[1] = from + 1;
				} else if (numberOfConsideredElements <= 3) {
					int gap = to - from / (numberOfConsideredElements-1);
					pivots[0] = from + gap;
					pivots[1] = from + 2 * gap;
				}

				if (pivots[1] == from) {
					pivots[1] = pivots[0];
				}
				return pivots;
				/*
				// TODO
				int lowerBound = Math.min(numberOfConsideredElements, to - from + 1);
				int range = to - from;
				int steplength = range / (numberOfConsideredElements-1);
				int steps = 0;
				if (numbers.length == numberOfConsideredElements) steplength = 1;
				int[] bufArray = new int[lowerBound];

				for (int runner = from; steps < bufArray.length && runner < numbers.length; runner += steplength) {
					bufArray[steps] = numbers[runner];
					steps+=1;
				}
				Arrays.sort(bufArray);
				int stepLength = (bufArray.length - (bufArray.length-2) % 3 - 2) / 3;

				int piv1 = bufArray[stepLength];
				int piv2;

				if (bufArray.length == 1) {
					piv2 = bufArray[0];
				} else {
					if ((bufArray.length-2) % 3 == 2) {
						piv2 = bufArray[2 * stepLength + 2];

					} else {
						piv2 = bufArray[2 * stepLength + 1];

					}
				}


				int rs1 = -1;
				int rs2 = -1;
				for (int i = from; i < numbers.length; i++) {
					if (numbers[i] == piv1) {
						rs1 = i;
						break;
					}
				}
				for (int i = from; i < numbers.length; i++) {
					if (numbers[i] == piv2 && rs1 != i) {
						rs2 = i;
						break;
					}
				}

				if (rs2 == -1) {
					rs2 = rs1;
				}

				piv1 = Math.min(rs1, rs2);
				piv2 = Math.max(rs1, rs2);
				if (piv2 == from) {
					piv2 = piv1;
				}
				return new int[] {piv1, piv2};

				 */
			}

			@Override
			public String toString() {
				return "The thirds of " + numberOfConsideredElements + " elements distributed thoughout the array";
			}
		};
	}
}