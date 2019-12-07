package com.github.jhnaldo.sort

/**
 * Insertion sort is a simple sorting algorithm that builds the final sorted array
 * (or list) one item at a time.
 *
 * Time Complexity:
 *  - Best - O(n)
 *  - Worst - O(n^2^)
 *  - Average - O(n^2^)
 *
 * @see [[https://en.wikipedia.org/wiki/Insertion_sort]]
 */
object InsertionSort extends Sort {
  /**
   * Sorts a list of elements
   *
   * @tparam A the type of each element
   * @param list a list of elements
   * @return the sorted list of the elements
   */
  def sort[A](list: List[A])(implicit ev: A => Ordered[A]): List[A] = {
    def insertList(list: List[A], sorted: List[A]): List[A] = list match {
      case hd :: tl => insertList(tl, insertElem(Nil, hd, sorted))
      case Nil => sorted
    }

    def insertElem(pre: List[A], elem: A, sorted: List[A]): List[A] = sorted match {
      case hd :: tl if elem > hd => insertElem(hd :: pre, elem, tl)
      case _ => pre.reverse ++ (elem :: sorted)
    }

    insertList(list, Nil)
  }
}
