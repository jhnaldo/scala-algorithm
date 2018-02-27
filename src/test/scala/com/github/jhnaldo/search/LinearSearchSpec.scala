import org.scalatest._
import com.github.jhnaldo.search.LinearSearch

class LinearSearchSpec extends FlatSpec with Matchers {
  "The LinearSearch.search" should "return the index of an element" in {
    val list = List(1, 4, 3, 5, 2, 8, 6, 9, 7)
    LinearSearch.search(3, list) should be (2)
    LinearSearch.search(9, list) should be (7)
    LinearSearch.search(42, list) should be (-1)
  }
}
