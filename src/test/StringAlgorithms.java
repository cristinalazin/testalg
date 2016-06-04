package test;

import java.util.BitSet;

/**
 * another test from testalg unstaged
 * @author Kristanna
 *
 */
public class StringAlgorithms {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(firstNonRepeatChar("teeter"));
		
		System.out.println(numberOfWords(" fdfd fdsfs "));
		
		String hello = "Hello", lo = "lo";
        System.out.print((hello == "Hello") + " ");
        System.out.print((Other.hello == hello) + " ");
        //System.out.print((other.Other.hello == hello) + " ");
        System.out.print((hello == ("Hel"+"lo")) + " ");
        System.out.print((hello == ("Hel"+lo)) + " ");
        System.out.println(hello == ("Hel"+lo).intern());
        System.out.println(Long.MAX_VALUE);
	}

	public static class Other { static String hello = "Hello"; }
	
	/**
	 * bit set solution O(n)
	 * @param s
	 * @return
	 */
	public static Character firstNonRepeatChar(String s) {
		if (s == null) {
			return null;
		}
		
		// set occurence and  repetition flags
		BitSet occurFlags = new BitSet(Character.MAX_VALUE);
		BitSet repeatFlags = new BitSet(Character.MAX_VALUE);
		
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (! occurFlags.get(ch)) {
				occurFlags.set(ch);
			} else {
				repeatFlags.set(ch);
			}
		}
		
		int i = 0;
		
		while ((i < s.length()) && repeatFlags.get(s.charAt(i))) {
			i++;
		}
		
		if (i < s.length()) {
			return s.charAt(i);
		}
		return null;
	}
	
	public static int numberOfWords(String s) {
		if (s == null) {
			return 0;
		}

		int i = 0;
		int wordCount = 0;
		
		while ( i < s.length()) {
			// skip white spaces
			while (( i < s.length()) && (' ' == s.charAt(i))){
				i++;
			}
			
			if (i < s.length()) {
				// new word
				wordCount++;
				
				// skip word
				while (( i < s.length()) && !(' ' == s.charAt(i))){
					i++;
				}
			}
		}
		
		return wordCount;
	}
}
