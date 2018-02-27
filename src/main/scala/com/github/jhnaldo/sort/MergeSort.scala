package com.github.jhnaldo.sort

/**
 * Conceptually, a merge sort works as follows:
 *  - 1. Divide the unsorted list into n sublists, each containing 1 element
 *  (a list of 1 element is considered sorted).
 *  - 2. Repeatedly merge sublists to produce new sorted sublists until there is
 *  only 1 sublist remaining. This will be the sorted list.
 *
 * Time Complexity:
 *  - Best - O(n log n)
 *  - Worst - O(n log n)
 *  - Average - O(n log n)
 *
 * @see [[https://en.wikipedia.org/wiki/Merge_sort]]
 */
object MergeSort {
  /**
   * Sorts a list of elements
   *
   * @tparam A the type of each element
   * @param list a list of elements
   * @return the sorted list of n elements
   */
  def sort[A <% Ordered[A]](list: List[A]): List[A] = {
    val len = list.length
    if (len > 1){
      val (left, right) = list.splitAt(len / 2)
      merge(sort(left), sort(right), Nil)
    } else list
  }

  /**
   * Merges two sorted lists of elements in sorted order
   *
   * @tparam A the type of each element
   * @param left a sorted list of elements
   * @param right a sorted list of elements
   * @param sorted a sorted list in the reversed order
   * @return the sorted list merged the left and right sorted lists
   */
  def merge[A <% Ordered[A]](
    left: List[A],
    right: List[A],
    sorted: List[A]
  ): List[A] = (left, right) match {
    case (lhd :: ltl, rhd :: rtl) =>
      if (lhd < rhd) merge(ltl, right, lhd :: sorted)
      else merge(left, rtl, rhd :: sorted)
    case (Nil, hd :: tl) => merge(Nil, tl, hd :: sorted)
    case (hd :: tl, Nil) => merge(tl, Nil, hd :: sorted)
    case (Nil, Nil) => sorted.reverse
  }
}
