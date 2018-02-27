package com.github.jhnaldo.search

object LinearSearch {
  /**
   * Searches a specific element in a given list of elements from the front
   * and return the index of the element
   *
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
