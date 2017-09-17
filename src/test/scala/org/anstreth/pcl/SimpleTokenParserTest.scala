package org.anstreth.pcl


import org.scalatest.{FlatSpec, Matchers}

class SimpleTokenParserTest extends FlatSpec with Matchers {
  "A TokenParser" should "return success if passed string starts with its token" in {
    SimpleTokenParser("a").parse("abcd") should be(("bcd", Success(List("a"))))
  }

  it should "return success if passed string contains only its token" in {
    SimpleTokenParser("a").parse("a") should be(("", Success(List("a"))))
  }

  it should "return Error if passed string starts with other token" in {
    SimpleTokenParser("a").parse("bbcd") should be(("bbcd", Error()))
  }

  it should "return Error if passed string is empty" in {
    SimpleTokenParser("a").parse("") should be(("", Error()))
  }
}
