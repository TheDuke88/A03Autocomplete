package a03;

/**
 * Authors: Ryan Wheeler, Whitney Cahoon
 * Class: CSIS 2420
 */

import java.util.Comparator;

public class BinarySearchDeluxe {

	// Return the index of the first key in a[] that equals the search key, or -1 if
	// no such key.
	public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

		if (a == null || key == null || comparator == null)
			throw new IllegalArgumentException("arguments cannot be null");

		int low = 0;
		int high = a.length - 1;

		while (low <= high) {
			int mid = low + (high - low) / 2;
			int comp = comparator.compare(a[mid], key);

			if (comp > 0)
				high = mid - 1;
			else if (comp < 0)
				low = mid + 1;
			else if (comparator.compare(a[mid - 1], a[mid]) == 0)
				high = mid - 1;
			else
				return mid;
		}
		return -1;
	}

	// Return the index of the last key in a[] that equals the search key, or -1 if
	// no such key.
	public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

		if (a == null || key == null || comparator == null)
			throw new IllegalArgumentException("arguments cannot be null");

		int low = 0;
		int high = a.length;

		while (low <= high) {
			int mid = low + (high - low) / 2;
			int comp = comparator.compare(a[mid], key);

			if (comp > 0)
				high = mid - 1;
			else if (comp < 0)
				low = mid + 1;
			else if (comparator.compare(a[mid + 1], a[mid]) == 0)
				low = mid + 1;
			else
				return mid;
		}
		return -1;
	}
}
