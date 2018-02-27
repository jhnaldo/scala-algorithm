package com.github.jhnaldo.search

import math.max

/**
 * The maximum subarray problem is the task of finding the contiguous subarray
 * within a one-dimensional array, a[1...n], of numbers which has the largest sum.
 * It is based on Kadane's algorithm with dynamic programming.
 *
 * Time Complexity: O(n)
 *
 * @see [[https://en.wikipedia.org/wiki/Maximum_subarray_problem#Kadane's_algorithm_(Algorithm_3:_Dynamic_Programming)]]
 */
object MaxSubArray {
  /**
   * Finds the contiguous subarray which has the largest sum
   *
   * @param list a list of Long values
   * @return the sum of the subarray has the largest sum
   */
  def search(list: List[Long]): Long = {
    def loop(list: List[Long], maxTotal: Long, maxEnding: Long): Long = list match {
      case Nil => maxTotal
      case value :: remain =>
        val newME = max(value, maxEnding + value)
        val newMT = max(maxTotal, newME)
        loop(remain, newMT, newME)
    }

    loop(list, 0, 0)
  }
}
