package test;

import java.util.Scanner;

public class StringMatching {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Pattern: ");
		String p = scanner.nextLine();
		System.out.print("Text: ");
		String t = scanner.nextLine();
		
		scanner.close();
		
		System.out.printf("Best match: %s", stringMatching(p, t));
	}
	
	/**
	 * find the substring from t with min distance from p
	 * @param p - pattern
	 * @param t - text
	 */
	protected static String stringMatching(String p, String t) {
		int n = t.length();
		int[][] dist = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			// analyze strings starting with t[i]
			for ( int j = 0; j < n; j++) {
				
 			}
		}
		
		return null;
	}
	
	/**
	 * possible operation for string matching
	 */
	public enum Operation {
		SUBSTITUTE {
			@Override
			public int cost() {
				// TODO Auto-generated method stub
				return 1;
			}
			
		},
		DELETE{
			@Override
			public int cost() {
				// TODO Auto-generated method stub
				return 1;
			}
			
		},
		INSERT{
			@Override
			public int cost() {
				// TODO Auto-generated method stub
				return 1;
			}
			
		};
		
		public abstract int cost() ;
	}
}
