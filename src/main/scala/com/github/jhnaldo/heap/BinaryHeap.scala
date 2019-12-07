package com.github.jhnaldo.heap

import scala.collection.mutable.ArrayBuffer

/**
 * A binary heap is a heap data structure that takes the form of a binary tree.
 *
 * @tparam A the type of each element
 *
 * @see [[https://en.wikipedia.org/wiki/Binary_heap]]
 */
class BinaryHeap[A](implicit val ev: A => Ordered[A]) extends Heap[A] {
  private val tree: ArrayBuffer[A] = ArrayBuffer()
  private def valid(index: Int): Option[Int] =
    if (0 <= index && index < size) Some(index) else None
  private def parentOf(index: Int): Option[Int] =
    if (index == 0) None else Some((index - 1) / 2)
  private def leftOf(index: Int): Option[Int] = valid(index * 2 + 1)
  private def rightOf(index: Int): Option[Int] = valid(index * 2 + 2)
  private def swap(x: Int, y: Int): Unit = {
    val temp = tree(x)
    tree(x) = tree(y)
    tree(y) = temp
  }

  /**
   * Adding a new key to the heap (a.k.a., push)
   *
   * @param x a new item
   * @return unit value
   */
  def insert(x: A): Unit = {
    val cur = size
    // 1. Add the element to the bottom level of the heap at the most left.
    tree += x
    // 2. Compare the added element with its parent; if they are in the correct order, stop.
    // 3. If not, swap the element with its parent and return to the previous step.
    def upHeap(cur: Int): Unit =
      parentOf(cur).map(parent => if (tree(parent) > tree(cur)) {
        swap(cur, parent)
        upHeap(parent)
      })
    upHeap(cur)
  }

  /**
   * Returns the node of minimum value from a min-heap after
   * removing it from the heap (a.k.a., pop)
   *
   * @return the minimum item
   */
  def extractMin: Option[A] = size match {
    case 0 => None
    case 1 => Some(tree.remove(0))
    case _ => 
      // 1. Replace the root of the heap with the last element on the last level.
      val res = tree(0)
      tree(0) = tree.remove(tree.size - 1)
      // 2. Compare the new root with its children; if they are in the correct order, stop.
      // 3. If not, swap the element with its smaller child and return to the previous step.
      def downHeap(cur: Int): Unit = (leftOf(cur), rightOf(cur)) match {
        case (Some(left), None) if tree(left) < tree(cur) => swap(cur, left); downHeap(left)
        case (Some(left), Some(right)) =>
          if (tree(left) < tree(cur) && tree(left) <= tree(right)) {
            swap(cur, left)
            downHeap(left)
          } else if (tree(right) < tree(cur) && tree(right) <= tree(left)) {
            swap(cur, right)
            downHeap(right)
          }
        case _ =>
      }
      downHeap(0)
      Some(res)
  }

  /**
   * The size of the heap
   *
   * @return the size of the heap
   */
  def size: Int = tree.length

  /**
   * Beautified form
   *
   * @return Beautified form of the heap
   */
  override def toString: String = tree.mkString("[", ", ", "]")
}

/** Factory for [[com.github.jhnaldo.heap.BinaryHeap]] instances. */
object BinaryHeap {
  /**
   * Creates a binary heap with a given items
   *
   * @param list a list of items
   * @return a binary heap
   */
  def apply[A](list: List[A])(implicit ev: A => Ordered[A]): BinaryHeap[A] = {
    val heap = new BinaryHeap[A]
    list.foreach(heap insert _)
    heap
  }

  /**
   * Creates a binary heap with a given items
   *
   * @param seq a sequence of items
   * @return a binary heap
   */
  def apply[A](seq: A*)(implicit ev: A => Ordered[A]): BinaryHeap[A] = apply(seq.toList)(ev)
}
