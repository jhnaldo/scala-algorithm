package com.github.jhnaldo.search

/**
 * It sequentially checks each element of the list for the target value
 * until a match is found or until all the elements have been searched.
 *
 * Time Complexity:
 *  - Best - O(1)
 *  - Worst - O(n)
 *  - Average - O(n)
 *
 * @see [[https://en.wikipedia.org/wiki/Linear_search]]
 */
object LinearSearch {
  /**
   * Searches a specific element in a given list of elements
   *
   * @tparam A the type of each element
   * @param elem a specific element
   * @param list a list of elements
   * @return the index of the element or -1 if it does not exist in the list
   */
  def search[A](elem: A, list: List[A]): Int = {
    def findIndex(list: List[A], idx: Int): Int = list match {
      case hd :: tl =>
        if (hd == elem) idx
        else findIndex(tl, idx + 1)
      case Nil => -1
    }

    findIndex(list, 0)
  }
}
