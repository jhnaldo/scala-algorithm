import org.scalatest.matchers.should._
import org.scalatest.flatspec._
import com.github.jhnaldo.dp.MaxSubArray

class MaxSubArraySpec extends AnyFlatSpec with Matchers {
  "The MaxSubArray.search" should "return the maximum sum among subarrays" in {
    val list = List[Long](
      13, -3, -25, 20, -3, -16, -23, 18, 20,
      -7, 12, -5, -22, 15, -4, 7
    )
    MaxSubArray.search(list) should be (43)
  }
}
