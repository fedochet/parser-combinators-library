package org.anstreth.pcl

import org.scalatest.{FlatSpec, Matchers}

class CombineCombinatorTest extends FlatSpec with Matchers {
  private val aParser = TokenParser("a")
  private val bParser = TokenParser("b")
  private val composed = Combinators.combine(aParser, bParser)((a, b) => a + b)

  "A combination of two TokenParsers" should "match two tokens one after another" in {
    composed.parse("abc") should be ("c", Success("ab"))
  }

  it should "fail if first parser cannot parse" in {
    composed.parse("bc") should be ("bc", Error())
  }

  it should "fail if second parser cannot parse" in {
    composed.parse("ac") should be ("ac", Error())
  }

}
