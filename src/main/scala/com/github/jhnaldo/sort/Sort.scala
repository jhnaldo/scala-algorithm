package com.github.jhnaldo.sort

trait Sort {
  /**
   * Sorts a list of elements
   *
   * @tparam A the type of each element
   * @param list a list of elements
   * @return the sorted list of the elements
   */
  def sort[A](list: List[A])(implicit ev: A => Ordered[A]): List[A]
}
