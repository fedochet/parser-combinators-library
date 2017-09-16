package org.anstreth.pcl

import org.scalatest.{FlatSpec, Matchers}

class TokenParserTest extends FlatSpec with Matchers {
  "A TokenParser" should "return success if passed string starts with its token" in {
    TokenParser("a").parse("abcd") should be (("bcd", Success("a")))
  }

  it should "return success if passed string contains only its token" in {
    TokenParser("a").parse("a") should be (("", Success("a")))
  }

  it should "return Error if passed string starts with other token" in {
    TokenParser("a").parse("bbcd") should be (("bbcd", Error()))
  }

  it should "return Error if passed string is empty" in {
    TokenParser("a").parse("") should be ("", Error())
  }
}
