import org.scalatest._
import com.github.jhnaldo.sort.InsertionSort

class InsertionSortSpec extends FlatSpec with Matchers {
  "The InsertionSort.sort" should "sort a given list of elements" in {
    val list = List(1, 4, 3, 5, 2, 8, 6, 9, 7)
    val sorted = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    InsertionSort.sort(list) should be (sorted)
  }
}