package com.github.jhnaldo.sort

object BubbleSort {
  /**
   * Sorts a list of elements using bubble sort algorithm
   *
   * @param list a list of n elements
   * @return the sorted list of n elements
   */
  def sort[A <% Ordered[A]](list: List[A]): List[A] = {
    def bubble(list: List[A], acc: List[A]): List[A] = list match {
      case a :: b :: tl =>
        if (a < b) bubble(b :: tl, a :: acc)
        else bubble(a :: tl, b :: acc)
      case _ => list ++ acc
    }

    def loop(list: List[A], sorted: List[A]): List[A] = bubble(list, Nil) match {
      case max :: remain => loop(remain.reverse, max :: sorted)
      case Nil => sorted
    }

    loop(list, Nil)
  }
}
