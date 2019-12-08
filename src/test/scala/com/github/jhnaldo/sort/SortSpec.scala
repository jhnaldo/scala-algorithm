package com.github.jhnaldo.sort

import com.github.jhnaldo.Spec

abstract class SortSpec(Sort: Sort) extends Spec {
  s"The $Sort.sort" should "sort a given list of elements" in {
    val list = randomSeq(1e9.toInt, 1e4.toInt)
    Sort.sort(list) should be (list.sorted)
  }
}

class BuiltinSortSpec extends SortSpec(new Sort("BuiltinSort") {
  def sort[A](list: List[A])(implicit ev: A => Ordered[A]): List[A] = list.sorted
})

class BubbleSortSpec extends SortSpec(BubbleSort)
class HeapSortSpec extends SortSpec(HeapSort)
class BinomialSortSpec extends SortSpec(BinomialHeapSort)
class FibonacciHeapSortSpec extends SortSpec(FibonacciHeapSort)
class InsertionSortSpec extends SortSpec(InsertionSort)
class MergeSortSpec extends SortSpec(MergeSort)
class SelectionSortSpec extends SortSpec(SelectionSort)
class QuickSortSpec extends SortSpec(QuickSort)
