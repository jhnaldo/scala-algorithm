package com.github.jhnaldo.heap

import scala.collection.mutable.ArrayBuffer

/**
 * A Fibonacci heap is a data structure for priority queue operations,
 * consisting of a collection of heap-ordered trees. It has a better
 * amortized running time than many other priority queue data structures
 * including the binary heap and binomial heap.
 *
 * Time Complexity:
 *  - findMin - O(1)
 *  - insert - O(1)
 *  - deleteMin - O(log n) (amortized)
 *
 * @tparam A the type of each element
 *
 * @see [[https://en.wikipedia.org/wiki/Fibonacci_heap]]
 */
class FibonacciHeap[A](implicit val ev: A => Ordered[A]) extends Heap[A] {
  import FibonacciHeap._

  private var min: Option[Node[A]] = None

  def findMin: Option[A] = min.map(_.item)

  private def insertLeft(left: Node[A], node: Node[A]): Unit = {
    node.left.right = left
    left.left = node.left
    node.left = left
    left.right = node
  }

  def insert(item: A): Unit = {
    val node: Node[A] = Node[A](item, 0, None, None, null, null)
    node.left = node
    node.right = node
    min match {
      case None => min = Some(node)
      case Some(min) =>
        insertLeft(node, min)
        if (item < min.item) this.min = Some(node)
    }
    count += 1
  }

  private def link(x: Node[A], y: Node[A]): Node[A] = {
    val (parent, newChild) = if (x.item < y.item) (x, y) else (y, x)
    parent.child match {
      case None =>
        parent.child = Some(newChild)
        newChild.left = newChild
        newChild.right = newChild
      case Some(child) =>
        insertLeft(newChild, child)
    }
    newChild.parent = Some(parent)
    parent
  }

  private def removeParents(node: Node[A]): Unit = {
    var ptr = node
    while (!ptr.parent.isEmpty) {
      ptr.parent = None
      ptr.right = ptr
    }
  }

  private def updateMin: Unit = min match {
    case None =>
    case Some(min) =>
      var newMin = min
      var ptr = min.right
      while (!(ptr eq min)) {
        if (ptr.item < newMin.item) newMin = ptr
        ptr = ptr.right
      }
      this.min = Some(newMin)
  }

  def deleteMin: Option[A] = min match {
    case None => None
    case Some(min) =>
      val res = min.item

      // meld children of min into root list
      if (min.left eq min) min.child match {
        case None => this.min = None
        case Some(child) =>
          removeParents(child)
          this.min = Some(child)
      } else min.child match {
        case None =>
          min.left.right = min.right
          min.right.left = min.left
          this.min = Some(min.left)
        case Some(child) =>
          removeParents(child)
          min.left.right = child
          min.right.left = child.left
          child.left.right = min.right
          child.left = min.left
          this.min = Some(child)
      }

      // update min
      updateMin

      // consolidate trees
      var rankMap = Map[Int, Node[A]]()
      this.min.map(min => {
        var node = min
        while (!(node.right eq min)) {
          while (rankMap.contains(node.rank)) {
            val old = rankMap(node.rank)
            rankMap -= node.rank
            old.left.right = old.right
            old.right.left = old.left
            if (node eq node.left) {
              node = link(old, node)
              node.left = node
              node.right = node
            } else {
              val left = node.left
              val right = node.right
              node = link(old, node)
              node.left = left
              node.right = right
            }
          }
          node = node.right
        }
      })

      count -= 1
      Some(res)
  }

  private var count: Int = 0
  def size: Int = count

  // override def toString: String
}

/** Factory for [[com.github.jhnaldo.heap.FibonacciHeap]] instances. */
object FibonacciHeap extends HeapFactory {
  private case class Node[A](
    var item: A,
    var rank: Int,
    var parent: Option[Node[A]],
    var child: Option[Node[A]],
    var left: Node[A],
    var right: Node[A]
  )

  protected val name: String = "FibonacciHeap"
  protected def factory[A](implicit ev: A => Ordered[A]): Heap[A] = new FibonacciHeap[A]
}
