package org.anstreth.pcl


import org.scalatest.{FlatSpec, Matchers}

class AltCombinatorTest extends FlatSpec with Matchers {
  private val aParser = SimpleToken("a")
  private val bParser = SimpleToken("b")
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

  "An alternative of three tokens" should "parse any of three tokens successfully" in {
    val parser = Combinators.alt(
      SimpleToken("0"),
      SimpleToken("1"),
      SimpleToken("2")
    )

    parser.parse("0") should be ("", Success(List("0")))
    parser.parse("1") should be ("", Success(List("1")))
    parser.parse("2") should be ("", Success(List("2")))
    parser.parse("3") should be ("3", Error())
  }

}
