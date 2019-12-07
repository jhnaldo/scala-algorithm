package com.github.jhnaldo.search

/**
 * Binary search works on sorted arrays. Binary search begins by comparing
 * the middle element of the array with the target value. If the target value
 * matches the middle element, its position in the array is returned.
 * If the target value is less than or greater than the middle element,
 * the search continues in the lower or upper half of the array, respectively,
 * eliminating the other half from consideration.
 *
 * Time Complexity:
 *  - Best - O(1)
 *  - Worst - O(log n)
 *  - Average - O(log n)
 *
 * @see [[https://en.wikipedia.org/wiki/Binary_search_algorithm]]
 */
object BinarySearch {
  /**
   * Searches a specific element in a given list of elements
   *
   * @tparam A the type of each element
   * @param elem a specific element
   * @param sortedList a sorted list of elements
   * @return the index of the element or -1 if it does not exist in the list
   */
  def search[A](elem: A, sortedList: List[A])(implicit ev: A => Ordered[A]): Int = {
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

    findIndex(sortedList, 0)
  }
}
