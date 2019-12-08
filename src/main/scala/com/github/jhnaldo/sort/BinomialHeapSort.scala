package com.github.jhnaldo.sort

import com.github.jhnaldo.heap.BinomialHeap

/**
 * Heapsort is a comparison-based sorting algorithm. Heapsort can be thought of
 * as an improved selection sort: like that algorithm, it divides its input into
 * a sorted and an unsorted region, and it iteratively shrinks the unsorted region
 * by extracting the smallest element and moving that to the sorted region.
 * The improvement consists of the use of a heap data structure rather than
 * a linear-time search to find the maximum.
 *
 * Time Complexity:
 *  - Best - O(n log n)
 *  - Worst - O(n log n)
 *  - Average - O(n log n)
 *
 * @see [[https://en.wikipedia.org/wiki/Heapsort]]
 */
object BinomialHeapSort extends Sort("BinomialHeapSort") {
  /**
   * Sorts a list of elements
   *
   * @tparam A the type of each element
   * @param list a list of elements
   * @return the sorted list of the elements
   */
  def sort[A](list: List[A])(implicit ev: A => Ordered[A]): List[A] = {
    val heap = BinomialHeap(list)

    def aux(list: List[A]): List[A] = heap.deleteMin match {
      case None => list
      case Some(x) => aux(x :: list)
    }

    aux(Nil).reverse
  }
}
