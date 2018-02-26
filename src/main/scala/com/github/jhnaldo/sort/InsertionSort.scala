package com.github.jhnaldo.sort

object InsertionSort {
  /**
   * Sorts a list of elements using insertion sort algorithm
   *
   * @param list a list of n elements
   * @return the sorted list of n elements
   */
  def sort[A <% Ordered[A]](list: List[A]): List[A] = {
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
