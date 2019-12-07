package com.github.jhnaldo.sort

import scala.collection.mutable.Buffer

/**
 * selection sort is a sorting algorithm, specifically an in-place comparison sort.
 *
 * Time Complexity:
 *  - Best - O(n^2^)
 *  - Worst - O(n^2^)
 *  - Average - O(n^2^)
 *
 * @see [[https://en.wikipedia.org/wiki/Selection_sort]]
 */
object SelectionSort extends Sort {
  /**
   * Sorts a list of elements
   *
   * @tparam A the type of each element
   * @param list a list of elements
   * @return the sorted list of the elements
   */
  def sort[A](list: List[A])(implicit ev: A => Ordered[A]): List[A] = {
    val buffer: Buffer[A] = list.toBuffer
    val size: Int = buffer.size

    def swap(i: Int, j: Int): Unit = {
      val temp: A = buffer(i)
      buffer(i) = buffer(j)
      buffer(j) = temp
    }

    def selectMin(from: Int, min: Int): Int = if (from < size) selectMin(
      from + 1,
      if (buffer(from) < buffer(min)) from else min
    ) else min

    for (i <- 0 until size) swap(i, selectMin(i, i))
    buffer.toList
  }
}
