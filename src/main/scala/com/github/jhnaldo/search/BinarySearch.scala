package com.github.jhnaldo.search

object BinarySearch {
  /**
   * Searches a specific element in a given sorted list of elements
   * using binary search and return the index of the element
   *
   * @param elem a specific element
   * @param sortedList a sorted list of elements
   * @return the index of the element or -1 if it does not exist in the list
   */
  def search[A <% Ordered[A]](elem: A, list: List[A]): Int = {
    def findIndex(list: List[A], idx: Int): Int = {
      val midIdx = list.length / 2
      list.splitAt(midIdx) match {
        case (_, Nil) => -1
        case (left, mid :: right) =>
          if (elem < mid) findIndex(left, idx)
          else if (elem > mid) findIndex(right, idx + midIdx + 1)
          else idx + midIdx
      }
    }

    findIndex(list, 0)
  }
}
