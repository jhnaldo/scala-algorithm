package com.github.jhnaldo

import org.scalatest.matchers.should._
import org.scalatest.flatspec._
import scala.util.Random

trait Spec extends AnyFlatSpec with Matchers {
  def randomSeq(mod: Int, length: Int): List[Int] =
    (0 until length).map(_ => Random.nextInt % mod).toList
}
