package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


/**
 * comment added from test alg 2
 * @author Kristanna
 *
 */
public class Algo {
	public static class Pair<T> {
		private T first;
		private T second;

		public Pair() {
			first = null;
			second = null;
		}

		public Pair(T first, T second) {
			this.first = first;
			this.second = second;
		}

		public T getFirst() {
			return first;
		}

		public T getSecond() {
			return second;
		}

		public void setFirst(T newValue) {
			first = newValue;
		}

		public void setSecond(T newValue) {
			second = newValue;
		}

	}
	
	public static class Tuple<K, V, X> {
		private K x;
		private V y;
		private X z;

		public Tuple(K x, V y, X z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public K getX() {
			return x;
		}

		public void setX(K x) {
			this.x = x;
		}

		public V getY() {
			return y;
		}

		public void setY(V y) {
			this.y = y;
		}

		public X getZ() {
			return z;
		}

		public void setZ(X z) {
			this.z = z;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Dynamic programming - Maximum Subarray - Kadane algorithm
		// int [] a = new int[]{-2, -30, -3, -4, -1, -2, -1, -5, -4};
		int[] a = new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		Tuple<Integer, Integer, Integer> maxSub = maximumSubarray(a);
		System.out.println("Maximum subarray sum: " + maxSub.getX()
				+ " start position " + maxSub.getY() + " end position "
				+ maxSub.getZ());

		// insertion sort
		a = new int[] { 1, -2, -17 };
		insertionSort(a);
		System.out.println("Insertion Sort");
		printArray(a);

		// quick sort
		a = new int[] { 1, -2, -17, 20, 1, -3, 4, -1, 2, 1, -5 };
		System.out.println("\nPartition " + partition(a, 0, a.length));
		quickSort(a);
		System.out.println("Quick Sort");
		printArray(a);
		
		// words of maximum length 
		String [] words = new String[] {"abc", "acbb", "caab", "xyz"};
		char[] letters = new char[] {'a', 'c', 'a', 'x', 'b', 'b', 'c'};
		
		System.out.println();
		List<String> result = wordsOfMaxLength(words, letters);
		for (String r : result) {
			System.out.println(r);
		}
		
		// min spanning tree
//		Graph g = readGraph();
//		Set<Graph.Edge> s = minSpanningTree(g);
//		if (s != null) {
//			for (Graph.Edge edge : s) {
//				System.out.println("(" + edge.i + ", " + edge.j + ") w = " + edge.weight);
//			}
//		}
		// Scanner in = new Scanner(System.in);
		// String s1 = in.next();
		// String s2 = in.next();
		// System.out.println("Longest common subsequence length " + s1 + " " + s2 + " LENGTH: " + longestCommonSubsequence(s1, s2));
		int n = 7;
		int[][] arr = new int [n][n];
		for (int i = 1; i <= n * n; i++) {
			Pair<Integer> xy = getXY(n, i, true);
			arr[xy.getFirst() - 1][xy.getSecond() - 1] = i;
			// System.out.println("i = " + i + " has X = " + xy.getFirst() + " Y = " + xy.getSecond());
		}
		for (int i = 0; i < n; i ++) {
			StringBuffer buf = new StringBuffer();
			for (int j = 0; j < n; j ++) {
				if (arr[i][j] < 10) {
					buf.append(" ");
				}
				buf.append(arr[i][j] + " ");
			}
			System.out.println(buf);
		}
	}

	/**
	 * Maximum subarray problem (Kadane?) maxsubarray ending in i = max (a[i],
	 * a[i] + max subarray ending in (i - 1))
	 * 
	 * @param a
	 * @return
	 */
	public static Tuple<Integer, Integer, Integer> maximumSubarray(int[] a) {
		Tuple<Integer, Integer, Integer> result = new Tuple<Integer, Integer, Integer>(
				Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
		if (a == null || a.length == 0) {
			return result;
		}

		int prevMax = a[0];
		int maxSoFar = a[0];
		int maxEndPosition = 0;
		int maxStartPosition = 0;

		for (int i = 1; i < a.length; i++) {
			if (prevMax > 0) {
				prevMax += a[i];
			} else {
				prevMax = a[i];
				maxStartPosition = i;
			}

			if (prevMax > maxSoFar) {
				maxSoFar = prevMax;
				maxEndPosition = i;
			}
		}

		result.setX(maxSoFar);
		result.setY(maxStartPosition);
		result.setZ(maxEndPosition);

		return result;
	}

	public static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}

	/**
	 * for each i insert the value in the previously sorted 1..i-1 array
	 * 
	 * @param a
	 */
	public static void insertionSort(int[] a) {
		if (a == null || a.length == 0) {
			return;
		}

		for (int i = 1; i < a.length; i++) {
			int current = a[i];
			int j = i - 1;
			while ((j >= 0) && (a[j] > current)) {
				a[j + 1] = a[j--];
			}
			a[j + 1] = current;
		}

	}

	/**
	 * Quicksort
	 * 
	 * @param a
	 */
	public static void quickSort(int[] a) {
		if (a == null || a.length == 0) {
			return;
		}
		quickSort(a, 0, a.length);
	}

	public static void quickSort(int[] a, int l, int r) {
		if (l == r) {
			return;
		}

		// place a[l] in its place
		int pos = partition(a, l, r);
		if (pos > l) {
			quickSort(a, l, pos - 1);
		}
		if (pos < r) {
			quickSort(a, pos + 1, r);
		}
	}

	public static int partition(int[] a, int l, int r) {
		int val = a[r - 1];

		// index of less than val items
		int ltIdx = l;
		for (int i = l; i < r - 1; i++) {
			if (a[i] <= val) {
				if (i != ltIdx) {
					// swap
					int tmp = a[i];
					a[i] = a[ltIdx];
					a[ltIdx] = tmp;
				}
				ltIdx++;
			}
		}

		// swap ltIdx and r positions
		int tmp = a[r - 1];
		a[r - 1] = a[ltIdx];
		a[ltIdx] = tmp;

		return ltIdx;
	}

	/**
	 * Find Kth smallest element in two sorted arrays
	 */

	/**
	 * Given a list of words and a list of letters return the words of maximum
	 * length which can be formed with those letters.
	 */
	public static List<String> wordsOfMaxLength(String[] words, char[] letters) {
		if ((words == null) || (letters == null)) {
			return null;
		}

		short[] letterFrequency = new short[32];
		for (int i = 0; i < letters.length; i++) {
			letterFrequency[letters[i] - 'a']++;
		}
		
		int maxLenSoFar = 0;
		List<String> wordsWithMaxLenSoFar = null;
		for (String word : words) {
			if (canBeFormed(word, letterFrequency)) {
				if (maxLenSoFar < word.length()) {
					// new max
					maxLenSoFar = word.length();
					// reset words with max len so far
					wordsWithMaxLenSoFar = new ArrayList<String>();
					wordsWithMaxLenSoFar.add(word);
				} else if (maxLenSoFar == word.length()) {
					wordsWithMaxLenSoFar.add(word);
				}
			}
		}

		return wordsWithMaxLenSoFar;
	}

	public static boolean canBeFormed(String word, short[] paramLetterFrequency) {
		// copy letterFrequency;
		short[] letterFrequency = new short[paramLetterFrequency.length];
		for (int i = 0; i < letterFrequency.length; i++) {
			letterFrequency[i] = paramLetterFrequency[i];
		}

		for (char c : word.toCharArray()) {
			if (letterFrequency[c - 'a'] == 0) {
				return false;
			} else {
				letterFrequency[c - 'a']--;
			}
		}
		return true;
	}
	
	/**
	 * Attempt to define a Graph
	 * @author Kristanna
	 *
	 */
	public static class Graph {
		public static class Node {
			protected List<Pair<Integer>> edges = new ArrayList<Pair<Integer>>();
		}
		public static class Edge {
			public int i;
			public int j;
			public int weight;
		}
		List<Node> nodes;
		
		public Graph(int n) {
			nodes = new ArrayList<Node>(n);
			for (int i = 0 ; i < n; i++) {
				Node node = new Node();
				nodes.add(node);
			}
		}
		
		public int size() {
			return nodes.size();
		}
		
		public void addEdge(int i, int j, int weight) {
			if ((i >= 0) && (i < nodes.size())) {
				Node node = nodes.get(i);
				
				Pair<Integer> edge = new Pair<Integer>(j, weight);
				node.edges.add(edge);
			}
		}
		
		public int getEdge(int i, int j) {
			if ((i >= 0) && (i < nodes.size())) {
				Node node = nodes.get(i);
				for (Pair<Integer> edge : node.edges) {
					if (edge.getFirst() == j) {
						return edge.getSecond();
					}
				}
			}
			return Integer.MIN_VALUE;
		}
		
		public Set<Graph.Edge> getEdges(int i) {
			if ((i >= 0) && (i < nodes.size())) {
				Set<Graph.Edge> set = new TreeSet<Graph.Edge>();
				Node node = nodes.get(i);
				for (Pair<Integer> edge : node.edges) {
					Edge newEdge = new Edge();
					newEdge.i = i;
					newEdge.j = edge.first;
					newEdge.weight = edge.second;
					set.add(newEdge);
				}
				return set;
			}
			return null;
		}
	}
	
	public static Graph readGraph() {
		System.out.println("Number of vertices: ");
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		Graph g = new Graph(num);
		
		System.out.println("Number of edges: ");
		num = in.nextInt();
		
		System.out.println("Edges i j w ");
		for (int k = 0; k < num; k ++) {
			int i = in.nextInt();
			int j = in.nextInt();
			int w = in.nextInt();
			g.addEdge(i, j, w);
		}		
		return g;
	}
	
	public static Set<Graph.Edge> minSpanningTree(Graph g) {
		Set<Graph.Edge> set = new TreeSet<Graph.Edge>();
		
		for (int i = 0; i < g.size(); i++) {
			// find min edge from i to the rest of the graph G(i+1, ... n)
			Set<Graph.Edge> nodeEdges = g.getEdges(i);
			Graph.Edge minWeightEdge = null;
			int minWeight = Integer.MAX_VALUE;
			
			for (Graph.Edge edge : nodeEdges) {
				if ((edge.j > i) && (minWeight > edge.weight)) {
					minWeight = edge.weight;
					minWeightEdge = edge;
				}
			}
			
			if (minWeightEdge == null) {
				return null;
			} else {
				set.add(minWeightEdge);
			}
			
		}
		return set;
	}
	
	public static int longestCommonSubsequence(String s1, String s2) {
		int[] posLcs = new int[s2.length()];
		int[] lenLcs = new int[s2.length()];
		int[] prevIdx = new int[s2.length()];
		
		for (int j = 0; j < s2.length(); j++) {
			prevIdx[j] = -1;
		}
		
		// build the array of longest common subsequence between S1 and s2 till position j, containing
		// character s2[j]
		for (int j = 0; j < s2.length(); j++) {
			// parse S1 for char ch
			char ch = s2.charAt(j);
			ArrayList<Integer> chIndexes = new ArrayList<Integer>();
			for (int i = 0; i < s1.length(); i++) {
				if (ch == s1.charAt(i)) {
					chIndexes.add(i);
				}
			}
			
			if (chIndexes.isEmpty()) {
				posLcs[j] = 0;
				lenLcs[j] = 0;
			} else {
				int tmpMax = 1;
				
				// search the previous values
				for (int i = 0; i < j; i++) {
					if (lenLcs[i] >= tmpMax) {
						// TODO binary search
						// find an index higher than the pos of longest common subsequence ending in i
						int k = 0;
						boolean found = false;
						while ((k < chIndexes.size()) && !found) {
							if (posLcs[i] < chIndexes.get(k)) {
								found = true;
							} else {
								k++;
							}
						}
						
						if (found) {
							posLcs[j] = chIndexes.get(k);
							lenLcs[j] = ++tmpMax;
							prevIdx[j] = i;
						}
					}
				}
			
				if (tmpMax == 1) {
					posLcs[j] = chIndexes.get(0);
					lenLcs[j] = 1;
				}
			}
		}
		
		// find the solution (get max of lenLcs
		int maxLen = lenLcs[0];
		int endPos = posLcs[0];
		for (int j = 1; j < s2.length(); j++) {
			if (lenLcs[j] > maxLen) {
				maxLen = lenLcs[j];
				endPos = posLcs[j];
			}
		}
		
		if (maxLen != 0) {
			while (endPos != -1) {
				System.out.println(s1.charAt(endPos));
				endPos = prevIdx[endPos];
			}
		}
		return maxLen;
	}
	
	/**
	 * get coordinates in cube 
	 * @param N
	 * @param v
	 * @param dir
	 * @return
	 */
	public static Pair<Integer> getXY(int N, int v, boolean dir) {
		if (N == 1){
			return new Pair<Integer>(1, 1);
		} 
		
		// from outside
		int aux = v;
		if (!dir) {
			aux = N * N + 1 - v;
		} 
		
		if (aux <= N) {
			return new Pair<Integer>(1, aux);
		} else if (aux <= (2 * N - 1)) {
			return new Pair<Integer>(aux - N + 1, N);
		} else if (aux <= (3 * N - 2)) {
			return new Pair<Integer>(N, N - (aux - 2 * N + 1));
		} else if (aux <= (4 * N - 4)) {
			return new Pair<Integer>(N - (aux - 3 * N + 2), 1);
		} else {
			Pair<Integer> inner = getXY (N - 2, aux - 4 * N + 4, true);
			return new Pair<Integer>(inner.getFirst()+ 1, inner.getSecond() + 1);
		}
	}
}
