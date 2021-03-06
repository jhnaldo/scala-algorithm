package com.github.jhnaldo.heap

/**
 * A heap is a specialized tree-based data structure which is essentially an almost
 * complete tree that satisfies the heap property: in a max heap, for any given node C,
 * if P is a parent node of C, then the key (the value) of P is greater than or
 * equal to the key of C. In a min heap, the key of P is less than or equal to the key of C.
 * The node at the "top" of the heap (with no parents) is called the root node.
 *
 * @tparam A the type of each element
 *
 * @see [[https://en.wikipedia.org/wiki/Heap_(data_structure)]]
 */
trait Heap[A] {
  /**
   * Find a minimum item of a min-heap (a.k.a. peek)
   *
   * @return the minimum item
   */
  def findMin: Option[A]

  /**
   * Add a new key to the heap (a.k.a., push)
   *
   * @param x a new item
   * @return unit value
   */
  def insert(x: A): Unit

  /**
   * Return the node of minimum value from a min-heap after
   * removing it from the heap (a.k.a., pop)
   *
   * @return the minimum item
   */
  def deleteMin: Option[A]

  /**
   * Check whether the heap is empty or not.
   *
   * @return true if the heap is empty, false otherwise
   */
  def isEmpty: Boolean = size == 0

  /**
   * The size of the heap
   *
   * @return the size of the heap
   */
  def size: Int


  /**
   * Beautified form
   *
   * @return Beautified form of the heap
   */
  override def toString: String
}

/**
 * A factory for heap structures
 */
trait HeapFactory {
  /**
   * The name of the factory
   */
  protected val name: String

  /**
   * A factory to construct a new heap
   *
   * @return a new heap
   */
  protected def factory[A](implicit ev: A => Ordered[A]): Heap[A]

  /**
   * Creates a binomial heap with a given items
   *
   * @param list a list of items
   * @return a binomial heap
   */
  def apply[A](list: List[A])(implicit ev: A => Ordered[A]): Heap[A] = {
    val heap = factory[A]
    list.foreach(heap insert _)
    heap
  }

  /**
   * Creates a binomial heap with a given items
   *
   * @param seq a sequence of items
   * @return a binomial heap
   */
  def apply[A](seq: A*)(implicit ev: A => Ordered[A]): Heap[A] = apply(seq.toList)(ev)

  override def toString: String = name
}
