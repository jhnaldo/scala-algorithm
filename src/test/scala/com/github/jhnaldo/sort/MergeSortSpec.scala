import org.scalatest.matchers.should._
import org.scalatest.flatspec._
import com.github.jhnaldo.sort.MergeSort

class MergeSortSpec extends AnyFlatSpec with Matchers {
  "The MergeSort.sort" should "sort a given list of elements" in {
    val list = List(1, 4, 3, 5, 2, 8, 6, 9, 7)
    val sorted = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    MergeSort.sort(list) should be (sorted)
  }
}
