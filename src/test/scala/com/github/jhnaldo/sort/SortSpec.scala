package com.github.jhnaldo.sort

import com.github.jhnaldo.Spec

abstract class SortSpec(Sort: Sort) extends Spec {
  s"The $Sort.sort" should "sort a given list of elements" in {
    val list = randomSeq(1000000, 1000)
    Sort.sort(list) should be (list.sorted)
  }
}

class BubbleSortSpec extends SortSpec(BubbleSort)
class HeapSortSpec extends SortSpec(HeapSort)
class InsertionSortSpec extends SortSpec(InsertionSort)
class MergeSortSpec extends SortSpec(MergeSort)
class SelectionSortSpec extends SortSpec(SelectionSort)
