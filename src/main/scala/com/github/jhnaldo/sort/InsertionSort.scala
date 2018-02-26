package com.github.jhnaldo.sort

object InsertionSort {
  /**
   * Insertion sorting algorithm
   *
   * @param list a list of n elements
   * @return the sorted list of n elements
   */
  def sort[A <% Ordered[A]](list: List[A]): List[A] = {
    def insertList(list: List[A], sorted: List[A]): List[A] = list match {
      case hd :: tl => insertList(tl, insertElem(hd, sorted))
      case Nil => sorted
    }

    def insertElem(elem: A, sorted: List[A]): List[A] = sorted match {
      case hd :: tl if elem > hd => hd :: insertElem(elem, tl)
      case _ => elem :: sorted
    }

    insertList(list, Nil)
  }
}
