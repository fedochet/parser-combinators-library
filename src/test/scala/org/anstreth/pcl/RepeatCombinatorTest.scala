package org.anstreth.pcl


import org.scalatest.{FlatSpec, Matchers}

class RepeatCombinatorTest extends FlatSpec with Matchers {
  private val aParser = SimpleTokenParser("a")
  private val repeat = Combinators.repeat(aParser)
  "Repeat combinator of TokenCombinator" should "parse missing token as default value" in {
    repeat.parse("bbb") should be("bbb", Success(Nil))
  }

  "Repeat combinator of TokenCombinator" should "parse one token with default appendix" in {
    repeat.parse("abbb") should be("bbb", Success(List("a")))
  }

  "Repeat combinator of TokenCombinator" should "parse many tokens with default appendix" in {
    repeat.parse("aaabbb") should be("bbb", Success(List("a", "a", "a")))
  }

}
