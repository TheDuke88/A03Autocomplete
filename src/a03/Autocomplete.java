package a03;

/**
 * Authors: Ryan Wheeler, Whitney Cahoon
 * Class: CSIS 2420
 */

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Autocomplete {

	private Term[] terms;

	// Initialize the data structure from the given array of terms.
	public Autocomplete(Term[] terms) {

		this.terms = terms;
		Arrays.sort(terms);
	}

	// Return all terms that start with the given prefix, in descending order of
	// weight.
	public Term[] allMatches(String prefix) {

		Term t = new Term(prefix, 0);

		int i = BinarySearchDeluxe.firstIndexOf(terms, t, Term.byPrefixOrder(prefix.length()));
		int j = BinarySearchDeluxe.lastIndexOf(terms, t, Term.byPrefixOrder(prefix.length()));

		Term[] m = new Term[j - i + 1];
		
		for (int k = 0; k < m.length; k++) {
			m[k] = terms[i++];
		}
		
		Arrays.sort(m, Term.byReverseWeightOrder());
		return m;
	}

	// Return the number of terms that start with the given prefix.
	public int numberOfMatches(String prefix) {

		Term t = new Term(prefix, 0);

		int i = BinarySearchDeluxe.firstIndexOf(terms, t, Term.byPrefixOrder(prefix.length()));
		int j = BinarySearchDeluxe.lastIndexOf(terms, t, Term.byPrefixOrder(prefix.length()));

		return j - i + 1;
	}

	public static void main(String[] args) {

		// read in the terms from a file
		String filename = args[0];
		In in = new In(filename);
		int N = in.readInt();
		Term[] terms = new Term[N];
		for (int i = 0; i < N; i++) {
			double weight = in.readDouble(); // read the next weight
			in.readChar(); // scan past the tab
			String query = in.readLine(); // read the next query
			terms[i] = new Term(query, weight); // construct the term
		}

		// read in queries from standard input and print out the top k matching terms
		int k = Integer.parseInt(args[1]);
		Autocomplete autocomplete = new Autocomplete(terms);
		while (StdIn.hasNextLine()) {
			String prefix = StdIn.readLine();
			Term[] results = autocomplete.allMatches(prefix);
			for (int i = 0; i < Math.min(k, results.length); i++)
				StdOut.println(results[i]);
		}
	}
}
