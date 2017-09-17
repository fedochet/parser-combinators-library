package org.anstreth.pcl


import org.scalatest.{FlatSpec, Matchers}

class CombineCombinatorTest extends FlatSpec with Matchers {
  private val aParser = SimpleTokenParser("a")
  private val bParser = SimpleTokenParser("b")
  private val composed = Combinators.combine(aParser, bParser)

  "A compose of two TokenParsers" should "match two tokens one after another" in {
    composed.parse("abc") should be("c", Success(List("a", "b")))
  }

  it should "fail if first parser cannot parse" in {
    composed.parse("bc") should be("bc", Error())
  }

  it should "fail if second parser cannot parse" in {
    composed.parse("ac") should be("ac", Error())
  }

}
