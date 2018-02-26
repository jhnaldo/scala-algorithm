package com.github.jhnaldo.sort

object MergeSort {
  /**
   * Sorts a list of elements using merge sort algorithm
   *
   * @param list a list of n elements
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
   * @param left a list of n elements
   * @param right a list of m elements
   * @param sorted a sorted list in the reversed order
   * @return the sorted list of n+m elements
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
