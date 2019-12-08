package com.github.jhnaldo.search

import com.github.jhnaldo.Spec

class BinarySearchSpec extends Spec {
  "The BinarySearch.search" should "return the index of an element" in {
    val list = List(2, 5, 7, 13, 20, 52, 100, 525)
    BinarySearch.search(13, list) should be (3)
    BinarySearch.search(100, list) should be (6)
    BinarySearch.search(42, list) should be (-1)
  }
}
