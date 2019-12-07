package com.github.jhnaldo.sort

/**
 * Bubble sort, sometimes referred to as sinking sort, is a simple sorting algorithm
 * that repeatedly steps through the list to be sorted, compares each pair of adjacent
 * items and swaps them if they are in the wrong order.
 *
 * Time Complexity:
 *  - Best - O(n)
 *  - Worst - O(n^2^)
 *  - Average - O(n^2^)
 *
 * @see [[https://en.wikipedia.org/wiki/Bubble_sort]]
 */
object BubbleSort extends Sort {
  /**
   * Sorts a list of elements
   *
   * @tparam A the type of each element
   * @param list a list of elements
   * @return the sorted list of the elements
   */
  def sort[A](list: List[A])(implicit ev: A => Ordered[A]): List[A] = {
    def bubble(list: List[A], acc: List[A]): List[A] = list match {
      case a :: b :: tl =>
        if (a < b) bubble(b :: tl, a :: acc)
        else bubble(a :: tl, b :: acc)
      case _ => list ++ acc
    }

    def loop(list: List[A], sorted: List[A]): List[A] = bubble(list, Nil) match {
      case max :: remain => loop(remain.reverse, max :: sorted)
      case Nil => sorted
    }

    loop(list, Nil)
  }
}
