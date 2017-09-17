package org.anstreth.pcl


import org.scalatest.{FlatSpec, Matchers}

class CombineCombinatorTest extends FlatSpec with Matchers {
  private val aParser = SimpleToken("a")
  private val bParser = SimpleToken("b")
  private val combined = Combinators.combine(aParser, bParser)

  "A compose of two TokenParsers" should "match two tokens one after another" in {
    combined.parse("abc") should be("c", Success(List("a", "b")))
  }

  it should "fail if first parser cannot parse" in {
    combined.parse("bc") should be("bc", Error())
  }

  it should "fail if second parser cannot parse" in {
    combined.parse("ac") should be("ac", Error())
  }

  "A compose of three TokenParsers" should "match three tokens one after another" in {
    val threeCombined = Combinators.combine(
      SimpleToken("a"),
      SimpleToken("b"),
      SimpleToken("c")
    )

    threeCombined.parse("abc") should be ("", Success(List("a", "b", "c")))
    threeCombined.parse("bcd") should be ("bcd", Error())
  }

}
