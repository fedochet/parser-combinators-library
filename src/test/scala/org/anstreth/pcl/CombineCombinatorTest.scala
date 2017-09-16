package org.anstreth.pcl

import org.scalatest.{FlatSpec, Matchers}

class CombineCombinatorTest extends FlatSpec with Matchers {
  private val aParser = SymbolParser('a')
  private val bParser = SymbolParser('b')
  private val composed = Combinators.combine(aParser, bParser)((a, b) => "" + a + b)

  "A combination of two SymbolParsers" should "match two symbols" in {
    composed.parse("abc".toList) should be ("c".toList, Success("ab"))
  }

  it should "fail if first symbol does not match" in {
    composed.parse("bc".toList) should be ("bc".toList, Error())
  }

  it should "fail if second symbol does not match" in {
    composed.parse("ac".toList) should be ("ac".toList, Error())
  }

}
