package com.github.jhnaldo.search

import com.github.jhnaldo.Spec

class LinearSearchSpec extends Spec {
  "The LinearSearch.search" should "return the index of an element" in {
    val list = List(1, 4, 3, 5, 2, 8, 6, 9, 7)
    LinearSearch.search(3, list) should be (2)
    LinearSearch.search(9, list) should be (7)
    LinearSearch.search(42, list) should be (-1)
  }
}
