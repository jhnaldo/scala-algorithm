package com.github.jhnaldo.heap

import scala.collection.mutable.ArrayBuffer

/**
 * A binomial heap is a data structure that acts as a priority queue
 * but also allows pairs of heaps to be merged together. It is important
 * as an implementation of the mergeable heap abstract data type
 * (also called meldable heap), which is a priority queue supporting merge
 * operation. It is implemented as a heap similar to a binary heap
 * but using a special tree structure that is different from the complete binary
 * trees used by binary heaps. Binomial heaps were invented in 1978 by Jean Vuillemin.
 *
 * Time Complexity:
 *  - findMin - O(1)
 *  - insert - O(1) (amortized)
 *  - deleteMin - O(log n)
 *
 * @tparam A the type of each element
 *
 * @see [[https://en.wikipedia.org/wiki/Binomial_heap]]
 */
class BinomialHeap[A](implicit val ev: A => Ordered[A]) extends Heap[A] {
  private type Node = BinomialHeap.BinomialNode[A]
  private var head: Option[Node] = None
  private var min: Option[A] = None
  private def link(y: Node, z: Node): Unit = {
    y.p = Some(z)
    y.sibling = z.child
    z.child = Some(y)
    z.degree += 1
  }
  private def merge(that: BinomialHeap[A]): BinomialHeap[A] = {
    var prev: Option[Node] = None
    var root: Option[Node] = None
    def add(x: Option[Node]): Unit = prev match {
      case None => prev = x; root = x
      case Some(p) => p.sibling = x; prev = x;
    }
    def aux(l: Option[Node], r: Option[Node]): Unit = (l, r) match {
      case (l, None) => add(l)
      case (None, r) => add(r)
      case (Some(lnode), Some(rnode)) =>
        if (lnode.degree <= rnode.degree) { add(l); aux(lnode.sibling, r) }
        else { add(r); aux(l, rnode.sibling) }
    }
    aux(this.head, that.head)
    val H = new BinomialHeap[A]
    H.head = root
    H
  }
  private def union(that: BinomialHeap[A]): BinomialHeap[A] = {
    val H = this merge that
    def aux(prev: Option[Node], x: Node, next: Option[Node]): Unit = next match {
      case None =>
      case Some(next) =>
        if (x.degree != next.degree || (next.sibling.fold(false)(_.degree == x.degree))) {
          aux(Some(x), next, next.sibling)
        } else if (x.item <= next.item) {
          x.sibling = next.sibling
          link(next, x)
          aux(prev, x, next.sibling)
        } else {
          prev match {
            case None => H.head = Some(next)
            case Some(prev) => prev.sibling = Some(next)
          }
          link(x, next)
          aux(prev, next, next.sibling)
        }
    }
    H.head.map(x => aux(None, x, x.sibling))
    H.min = (this.min, that.min) match {
      case (l, None) => l
      case (None, r) => r
      case (Some(l), Some(r)) => Some(if (l < r) l else r)
    }
    H
  }

  def findMin: Option[A] = min

  def insert(item: A): Unit = {
    val H = new BinomialHeap[A]
    H.head = Some(BinomialHeap.BinomialNode[A](None, item, 0, None, None))
    this.head = (this union H).head
    count += 1
  }

  def deleteMin: Option[A] = head.map(head => {
    def find(cur: (Option[Node], Node), min: (Option[Node], Node)): (Option[Node], Node) = {
      val ((_, curNode), (_, minNode)) = (cur, min)
      val newMin = if (curNode.item < minNode.item) cur else min
      curNode.sibling.fold(newMin)(next => find((Some(curNode), next), newMin))
    }
    def rev(prev: Option[Node], cur: Option[Node]): Option[Node] = cur.fold(prev)(cur => {
      val next = cur.sibling
      cur.sibling = prev
      rev(Some(cur), next)
    })
    val (prev, x) = find((None, head), (None, head))
    prev.fold(this.head = x.sibling)(_.sibling = x.sibling)
    val H = new BinomialHeap[A]
    H.head = rev(None, x.child)
    this.head = (this union H).head
    this.min = this.head.map(head => find((None, head), (None, head))._2.item)
    count -= 1
    x.item
  })

  private var count: Int = 0
  def size: Int = count

  private def beautify(x: Node): String =
    x.item.toString + beautify(x.child) + x.sibling.fold("")(", " + beautify(_))
  private def beautify(x: Option[Node]): String = "[" + x.fold("")(beautify) + "]"
  override def toString: String = beautify(head)
}

/** Factory for [[com.github.jhnaldo.heap.BinomialHeap]] instances. */
object BinomialHeap extends HeapFactory {
  private case class BinomialNode[A](
    var p: Option[BinomialNode[A]],
    var item: A,
    var degree: Int,
    var child: Option[BinomialNode[A]],
    var sibling: Option[BinomialNode[A]]
  )

  protected def factory[A](implicit ev: A => Ordered[A]): Heap[A] = new BinomialHeap[A]
}
