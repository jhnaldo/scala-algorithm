package com.github.jhnaldo.sort

abstract class Sort(name: String) {
  /**
   * Sorts a list of elements
   *
   * @tparam A the type of each element
   * @param list a list of elements
   * @return the sorted list of the elements
   */
  def sort[A](list: List[A])(implicit ev: A => Ordered[A]): List[A]

  override def toString: String = name
}
