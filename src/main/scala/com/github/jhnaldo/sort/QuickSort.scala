package com.github.jhnaldo.sort

import scala.collection.mutable.ArrayBuffer

/**
 * Quicksort (sometimes called partition-exchange sort) is
 * an efficient sorting algorithm, serving as a systematic
 * method for placing the elements of a random access file
 * or an array in order.
 *
 * Time Complexity:
 *  - Best - O(n log n)
 *  - Worst - O(n log n)
 *  - Average - O(n^2)
 *
 * @see [[https://en.wikipedia.org/wiki/Quick_sort]]
 */
object QuickSort extends Sort("QuickSort") {
  private def quicksort[A](buffer: ArrayBuffer[A], lo: Int, hi: Int)(implicit ev: A => Ordered[A]): Unit = if (lo < hi) {
    val p = partition(buffer, lo, hi)
    quicksort(buffer, lo, p - 1)
    quicksort(buffer, p + 1, hi)
  }
  private def partition[A](buffer: ArrayBuffer[A], lo: Int, hi: Int)(implicit ev: A => Ordered[A]): Int = {
    def swap(i: Int, j: Int): Unit = {
      val temp = buffer(i); buffer(i) = buffer(j); buffer(j) = temp
    }
    val pivot = buffer(hi)
    var i = lo
    for (j <- lo to hi) if (buffer(j) < pivot) {
      swap(i, j)
      i += 1
    }
    swap(i, hi)
    i
  }

  /**
   * Sorts a list of elements
   *
   * @tparam A the type of each element
   * @param list a list of elements
   * @return the sorted list of n elements
   */
  def sort[A](list: List[A])(implicit ev: A => Ordered[A]): List[A] = {
    val buffer = list.to(ArrayBuffer)
    quicksort(buffer, 0, list.length - 1)
    buffer.toList
  }
}
