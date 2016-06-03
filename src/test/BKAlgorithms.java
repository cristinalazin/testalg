package test;

import java.util.Scanner;

public class BKAlgorithms {
	public static long SOL = 1;
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
//		int n = 10;
//		int[] a = new int[n];
//		BK(a, n, 0);
		
		int n = 9;
		int[][] tableSudoku = new int[n][n];
		readDoubleArr(tableSudoku, n);
		printDoubleArr(tableSudoku, n);
	}

	
	public static void BK(int[] a, int n, int k) {
		if (k == n) {
			// print solution
			printArr(a, n);
		} else {
			for (int j = 0; j < n; j++) {
				if (isGoodChoice(a, k, j)) {
					a[k] = j;
					BK(a, n, k + 1);
				}
			}
		}
	}
	
	public static boolean isGoodChoice(int[] a, int k, int val) {
		for (int j = 0; j < k; j++) {
			if (a[j] == val) {
				return false;
			}
		}
		return true;
	}
	
	public static void printArr(int[] a, int n) {
		// System.out.print(SOL++);
		for (int i = 0; i < n; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	public static void printDoubleArr(int[][] a, int n) {
		System.out.print(SOL++);
		for (int i = 0; i < n; i++) {
			printArr(a[i], n);
		}
		
	}
	
	public static void readDoubleArr(int[][] a, int n) throws Exception{
		Scanner scan = new Scanner( System.in );
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++){
				a[i][j] = scan.nextInt();
			}
		}
		scan.close();
	}
}
