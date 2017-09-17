package org.anstreth.pcl


import org.scalatest.{FlatSpec, Matchers}

class AltCombinatorTest extends FlatSpec with Matchers {
  private val aParser = SimpleTokenParser("a")
  private val bParser = SimpleTokenParser("b")
  private val altParser = Combinators.alt(aParser, bParser)

  "An alternative of two TokenParsers" should "parse first token successfully" in {
    altParser.parse("abc") should be("bc", Success(List("a")))
  }

  it should "parse second token successfully" in {
    altParser.parse("bbc") should be("bc", Success(List("b")))
  }

  it should "fail if neither of two tokens is present" in {
    altParser.parse("cbc") should be("cbc", Error())
  }

}
