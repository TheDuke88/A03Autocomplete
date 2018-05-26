package a03;

/**
 * Authors: Ryan Wheeler, Whitney Cahoon
 * Class: CSIS 2420
 */

import java.util.Comparator;

public class Term implements Comparable<Term> {

	private final String query;
	private final double weight;

	// Initialize a term with the given query string and weight.
	public Term(String query, double weight) {
		
		if (query == null) {
			throw new java.lang.NullPointerException();
		}
		if (weight < 0) {
			throw new java.lang.IllegalArgumentException();
		}
		this.query = query;
		this.weight = weight;

	}

	// Compare the terms in descending order by weight.
	public static Comparator<Term> byReverseWeightOrder() {
		
		return new ReverseWeight();
	}

	// Compare the terms in lexicographic order but using only the first r
	// characters of each query.
	public static Comparator<Term> byPrefixOrder(int r) {
		
		if (r < 0) {
			throw new java.lang.IllegalArgumentException();
		}
		return new PrefixOrder(r);
	}

	// Compare the terms in lexicographic order by query.
	public int compareTo(Term that) {
		
		return this.query.compareTo(that.query);
	}

	// Return a string representation of the term in the following format:
	// the weight, followed by a tab, followed by the query.
	@Override
	public String toString() {
		
		return String.format("%.1f \t %s", (weight/100), query);

	}

	private static class ReverseWeight implements Comparator<Term> {

		@Override
		public int compare(Term arg0, Term arg1) {
			if (arg0.weight == arg1.weight) {
				return 0;
			} else if (arg0.weight > arg1.weight) {
				return -1;
			} else {
				return 1;
			}

		}
	}

	private static class PrefixOrder implements Comparator<Term> {
		
		private int r;

		private PrefixOrder(int r) {
			this.r = r;
		}

		@Override
		public int compare(Term o1, Term o2) {
			
			if (o1.query.length() < r && o2.query.length() < r) {
				return o1.query.compareTo(o2.query);
			} else if (o1.query.length() < r) {
				return o1.query.compareTo(o2.query.substring(0, r));
			} else if (o2.query.length() < r) {
				return o1.query.substring(0, r).compareTo(o2.query);
			} else {
				return o1.query.substring(0, r).compareTo(o2.query.substring(0, r));
			}
		}
	}
}