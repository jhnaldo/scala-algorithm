import org.scalatest.matchers.should._
import org.scalatest.flatspec._
import com.github.jhnaldo.heap.BinomialHeap

class BinomialHeapSpec extends AnyFlatSpec with Matchers {
  "The BinomialHeap" should "be a priority queue" in {
    val heap = BinomialHeap(1, 2, 25, 3, 8, 342, 475, 75, 634, 7437, 34, 865, 4, 5)
    heap.deleteMin should be (Some(1))
    heap.deleteMin should be (Some(2))
    heap.deleteMin should be (Some(3))
    heap.deleteMin should be (Some(4))
    heap.deleteMin should be (Some(5))
    heap.deleteMin should be (Some(8))
    heap.deleteMin should be (Some(25))

    heap.insert(5236)
    heap.insert(423)
    heap.insert(1)
    heap.insert(24)
    heap.insert(253)

    heap.deleteMin should be (Some(1))
    heap.deleteMin should be (Some(24))
    heap.deleteMin should be (Some(34))
    heap.deleteMin should be (Some(75))
    heap.deleteMin should be (Some(253))
    heap.deleteMin should be (Some(342))
    heap.deleteMin should be (Some(423))
    heap.deleteMin should be (Some(475))
    heap.deleteMin should be (Some(634))
    heap.deleteMin should be (Some(865))
    heap.deleteMin should be (Some(5236))
    heap.deleteMin should be (Some(7437))
    heap.deleteMin should be (None)
  }
}
