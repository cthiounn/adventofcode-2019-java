package aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7 {

	public static void main(String[] args) throws IOException {

		long timeStart = System.currentTimeMillis();
		List<String> input = Files.readAllLines(Paths.get("src/main/resources/day7-input.file"));
//		doPart1(input, "01234", false);
		doPart1(input, "56789", true);
		System.out.println("runned time : " + (System.currentTimeMillis() - timeStart) + " ms");

	}

	private static void doPart1(List<String> input, String param, boolean partTwo) {
		List<String> myList = permutations(param);
		int[] listOutput = new int[5];
		List<Integer> myListOutput = new ArrayList<Integer>();
		Map<Integer, String> myMap = new HashMap<>();

		int max = 0;
		for (String s : myList) {
			int[] n1 = Arrays.asList(input.get(0).split(",")).stream().mapToInt(Integer::parseInt).toArray();
			int[] n2 = Arrays.asList(input.get(0).split(",")).stream().mapToInt(Integer::parseInt).toArray();
			int[] n3 = Arrays.asList(input.get(0).split(",")).stream().mapToInt(Integer::parseInt).toArray();
			int[] n4 = Arrays.asList(input.get(0).split(",")).stream().mapToInt(Integer::parseInt).toArray();
			int[] n5 = Arrays.asList(input.get(0).split(",")).stream().mapToInt(Integer::parseInt).toArray();
			int b0 = 0, b1 = 0, b2 = 0, b3 = 0, b4 = 0;
			int a0 = 0, a1 = 0, a2 = 0, a3 = 0, a4 = 0;
			try {
				a0 = 0;
				a1 = listOutput[0];
				a2 = listOutput[1];
				a3 = listOutput[2];
				a4 = listOutput[3];
				listOutput[0] = part1(n1, Integer.parseInt(s.substring(0, 1)), 0);
				listOutput[1] = part1(n2, Integer.parseInt(s.substring(1, 2)), listOutput[0]);
				listOutput[2] = part1(n3, Integer.parseInt(s.substring(2, 3)), listOutput[1]);
				listOutput[3] = part1(n4, Integer.parseInt(s.substring(3, 4)), listOutput[2]);
				listOutput[4] = part1(n5, Integer.parseInt(s.substring(4, 5)), listOutput[3]);
				b0 = a0;
				b1 = a2;
				b2 = a2;
				b3 = a3;
				b4 = a4;

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {

				if (listOutput[4] > max) {
					myMap.put(listOutput[4], s);
					myListOutput.add(listOutput[4]);
					max = listOutput[4];
				}
			}

			int i = 0;
			while (i < 1000 && partTwo) {
				System.out.println(i);
				i++;
				try {
					a0 = listOutput[4];
					a1 = listOutput[0];
					a2 = listOutput[1];
					a3 = listOutput[2];
					a4 = listOutput[3];
					listOutput[0] = part1(n1, b0, listOutput[4]);
					listOutput[1] = part1(n2, b1, listOutput[0]);
					listOutput[2] = part1(n3, b2, listOutput[1]);
					listOutput[3] = part1(n4, b3, listOutput[2]);
					listOutput[4] = part1(n5, b4, listOutput[3]);
					if (listOutput[4] > max) {
						myMap.put(listOutput[4], s);
						myListOutput.add(listOutput[4]);
						max = listOutput[4];
					}
					b0 = a0;
					b1 = a2;
					b2 = a2;
					b3 = a3;
					b4 = a4;
				} catch (Exception e) {
					if (listOutput[4] > max) {
						myMap.put(listOutput[4], s);
						myListOutput.add(listOutput[4]);
						max = listOutput[4];
					}
				}
			}

		}

		System.out.println(myListOutput);
		System.out.println(myListOutput.stream().max(Comparator.comparing(Integer::valueOf)).get());
		System.out.println(myMap.get(myListOutput.stream().max(Comparator.comparing(Integer::valueOf)).get()));
	}

//	public static int part1(int[] n, int beginInput) {
//		int i = 0;
//		int r2 = 0;
//		int r3 = 0;
//		int r4 = 0;
//		int p1 = 0;
//		int p2 = 0;
//		int c = 0;
//		boolean firstInput = true;
//		boolean stop = false;
//		while (!stop) {
//			r2 = i + 1 < n.length ? n[i + 1] : 0;
//			r3 = i + 2 < n.length ? n[i + 2] : 0;
//			r4 = i + 3 < n.length ? n[i + 3] : 0;
//			c = n[i] % 10;
//			p1 = n[i] % 1000 >= 100 || c == 3 || c == 9 ? r2 : n[r2];
//			p2 = n[i] % 10000 >= 1000 ? r3 : c == 3 || c == 4 || c == 9 ? 0 : n[r3];
//			if (c == 1) {
//				n[r4] = p1 + p2;
//				i += 4;
//			} else if (c == 2) {
//				n[r4] = p1 * p2;
//				i += 4;
//			} else if (c == 3) {
//				n[r2] = beginInput;
//				i += 2;
//			} else if (c == 4) {
//				beginInput = p1;
//				i += 2;
//			} else if (c == 5) {
//				if (p1 != 0) {
//					i = p2;
//				} else {
//					i += 3;
//				}
//			} else if (c == 6) {
//				if (p1 == 0) {
//					i = p2;
//				} else {
//					i += 3;
//				}
//			} else if (c == 7) {
//				n[r4] = p1 < p2 ? 1 : 0;
//				i += 4;
//			} else if (c == 8) {
//				n[r4] = p1 == p2 ? 1 : 0;
//				i += 4;
//			} else if (c == 9) {
//				stop = true;
//			} else {
//				System.out.println("? " + n[i]);
//				stop = true;
//			}
//		}
//		return beginInput;
//	}

	public static int part1(int[] n, int beginInput, int input2) throws Exception {
		int i = 0;
		int r2 = 0;
		int r3 = 0;
		int r4 = 0;
		int p1 = 0;
		int p2 = 0;
		int c = 0;
		boolean firstInput = true;
		boolean stop = false;
		while (!stop) {
			r2 = i + 1 < n.length ? n[i + 1] : 0;
			r3 = i + 2 < n.length ? n[i + 2] : 0;
			r4 = i + 3 < n.length ? n[i + 3] : 0;
			c = n[i] % 10;
			p1 = n[i] % 1000 >= 100 || c == 3 || c == 9 ? r2 : n[r2];
			p2 = n[i] % 10000 >= 1000 ? r3 : c == 3 || c == 4 || c == 9 ? 0 : n[r3];
			if (c == 1) {
				n[r4] = p1 + p2;
				i += 4;
			} else if (c == 2) {
				n[r4] = p1 * p2;
				i += 4;
			} else if (c == 3) {
				if (firstInput) {
					n[r2] = beginInput;
					firstInput = false;
				} else {
					n[r2] = input2;
				}
				i += 2;
			} else if (c == 4) {
				beginInput = p1;
				i += 2;
			} else if (c == 5) {
				if (p1 != 0) {
					i = p2;
				} else {
					i += 3;
				}
			} else if (c == 6) {
				if (p1 == 0) {
					i = p2;
				} else {
					i += 3;
				}
			} else if (c == 7) {
				n[r4] = p1 < p2 ? 1 : 0;
				i += 4;
			} else if (c == 8) {
				n[r4] = p1 == p2 ? 1 : 0;
				i += 4;
			} else if (c == 9) {
				stop = true;
			} else {
				System.out.println("? " + n[i]);
				stop = true;

				throw new Exception();
			}
		}
		return beginInput;
	}

//	public static int part1(List<String> lines, int beginInput) {
//		int[] n = Arrays.asList(lines.get(0).split(",")).stream().mapToInt(Integer::parseInt).toArray();
//		int i = 0;
//		int r2 = 0;
//		int r3 = 0;
//		int r4 = 0;
//		int p1 = 0;
//		int p2 = 0;
//		int c = 0;
//		boolean stop = false;
//		while (!stop) {
//			r2 = i + 1 < n.length ? n[i + 1] : 0;
//			r3 = i + 2 < n.length ? n[i + 2] : 0;
//			r4 = i + 3 < n.length ? n[i + 3] : 0;
//			c = n[i] % 10;
//			p1 = n[i] % 1000 >= 100 || c == 3 || c == 9 ? r2 : n[r2];
//			p2 = n[i] % 10000 >= 1000 ? r3 : c == 3 || c == 4 || c == 9 ? 0 : n[r3];
//			if (c == 1) {
//				n[r4] = p1 + p2;
//				i += 4;
//			} else if (c == 2) {
//				n[r4] = p1 * p2;
//				i += 4;
//			} else if (c == 3) {
//				n[r2] = beginInput;
//				i += 2;
//			} else if (c == 4) {
//				beginInput = p1;
//				i += 2;
//			} else if (c == 5) {
//				if (p1 != 0) {
//					i = p2;
//				} else {
//					i += 3;
//				}
//			} else if (c == 6) {
//				if (p1 == 0) {
//					i = p2;
//				} else {
//					i += 3;
//				}
//			} else if (c == 7) {
//				n[r4] = p1 < p2 ? 1 : 0;
//				i += 4;
//			} else if (c == 8) {
//				n[r4] = p1 == p2 ? 1 : 0;
//				i += 4;
//			} else if (c == 9) {
//				stop = true;
//			} else {
//				System.out.println("? " + n[i]);
//				stop = true;
//			}
//		}
//		return beginInput;
//	}

	public static List<String> permutations(String s) {
		// create an empty ArrayList to store (partial) permutations
		List<String> partial = new ArrayList<>();

		// initialize the list with the first character of the string
		partial.add(String.valueOf(s.charAt(0)));

		// do for every character of the specified string
		for (int i = 1; i < s.length(); i++) {
			// consider previously constructed partial permutation one by one

			// (iterate backwards to avoid ConcurrentModificationException)
			for (int j = partial.size() - 1; j >= 0; j--) {
				// remove current partial permutation from the ArrayList
				String str = partial.remove(j);

				// Insert next character of the specified string in all
				// possible positions of current partial permutation. Then
				// insert each of these newly constructed string in the list

				for (int k = 0; k <= str.length(); k++) {
					// Advice: use StringBuilder for concatenation
					partial.add(str.substring(0, k) + s.charAt(i) + str.substring(k));
				}
			}
		}

		return partial;
	}
}
