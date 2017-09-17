package org.anstreth.pcl

import org.anstreth.pcl.Success.success
import org.scalatest.{FlatSpec, Matchers}

class RepeatCombinatorTest extends FlatSpec with Matchers {
  private val defaultValue = "default"
  private val aParser = SimpleTokenParser("a")
  private val repeat = Combinators.repeat(aParser, defaultValue)(_ + _)
  "Repeat combinator of TokenCombinator" should "parse missing token as default value" in {
    repeat.parse("bbb") should be ("bbb", success(defaultValue))
  }

  "Repeat combinator of TokenCombinator" should "parse one token with default appendix" in {
    repeat.parse("abbb") should be ("bbb", success("a" + defaultValue))
  }

  "Repeat combinator of TokenCombinator" should "parse many tokens with default appendix" in {
    repeat.parse("aaabbb") should be ("bbb", success("aaa" + defaultValue))
  }

}
