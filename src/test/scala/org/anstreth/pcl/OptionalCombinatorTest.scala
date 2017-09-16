package org.anstreth.pcl

import org.scalatest.{FlatSpec, Matchers}

class OptionalCombinatorTest extends FlatSpec with Matchers {
  private val aParser = SimpleTokenParser("a")
  val defaultValue = "default"
  private val optional = Combinators.opt(aParser, defaultValue)

  "Optinal token parser" should "return success if it parsed something" in {
    optional.parse("abc") should be ("bc", Success("a"))
  }

  it should "return success with default value if is didn't parse anything" in {
    optional.parse("bbc") should be ("bbc", Success(defaultValue))
  }

}
